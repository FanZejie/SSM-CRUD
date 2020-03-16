package com.fzj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fzj.bean.Employee;
import com.fzj.bean.Msg;
import com.fzj.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工crud请求
 * 
 * @author Mike-laptop model会放在请求与中,给他的数据都会带给页面
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	
	/**
	 * 单个,批量删除和一
	 * 批量删除:1-2-3
	 * 单个删除:1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids")String ids) {
		if (ids.contains("-")) {
			String[] str_ids = ids.split("-");
			List<Integer> del_ids = new ArrayList<Integer>();
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
				
			}
			employeeService.deleteBatch(del_ids);
		}else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
		
	}
	
	/**
	 * 单个删除
	 * @return
	 */
	/*@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("id")Integer id) {
		employeeService.deleteEmp(id);
		return Msg.success();
		
	}*/
	
	/**员工更新方法
	 * 
	 * 如果直接发送Ajax=put的请求
	 * 请求体重有数据,但是employee对象封装不上
	 * update tbl_emp where emp_id = 4;sql语句有问题
	 * 原因:
	 * tomcat :
	 * 1.将请求体重的数据封装一个map
	 * 2.request.getParameter("empName")就会从这个map中取直
	 * 3.springmvc封装POJO对象的时候,会把POJO中每个属性的值request.getParamter("email");
	 * AJAX发送PUT请求引发的血案
	 *    put请求体中的数据request.getParameter("empName")拿不到
	 *    tomcat一看是put,他就不会封装请求体中的数据为map,只有post形式的请求才封装请求体为map
	 *    
	 * springmvc也提供了一个过滤器HttpPutFormContentFilter
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {//这里springmvc会把员工数据自动封装
		System.out.println(employee.toString());
		employeeService.updateEmp(employee);
		return Msg.success();
		
	}
	
	/**
	 * @return
	 * @PathVariable指定这个id是来自路径
	 */
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}

	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping(value = "/checkuser",method = RequestMethod.POST)
	@ResponseBody
	public Msg checkuser(@RequestParam("empName") String empName) {
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名可以是2-5位中文或者3-16位英文和数字的组合");
		}
		//数据库用户名重复校验
		//System.out.println(empName);
		boolean b = employeeService.checkUser(empName);
		//System.out.println(b);
		if (b) {
			
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}
		
	}
	
	/**
	 * 保存员工信息
	 * @return
	 */
	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if (result.hasErrors()) {//校验失败
			//在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println("错误的字段名:"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
			
		}
	}
	
	
	/**
	   * 分页查询员工信息
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		//System.out.println("11111");
		// 在查询之前只需要调用,传入页码,以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用PageInfo包装查询后的结果,只需要将pageInfo交给页面就行了
		// 封装了详细的分页信息,包括有我们查询出来的数据,5是传入需要连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo",page);
	}

	/*@RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
	// 在查询之前只需要调用,传入页码,以及每页的大小
	PageHelper.startPage(pn, 5);
	// startPage后面紧跟的这个查询就是一个分页查询
	List<Employee> emps = employeeService.getAll();
	// 使用PageInfo包装查询后的结果,只需要将pageInfo交给页面就行了
	// 封装了详细的分页信息,包括有我们查询出来的数据,5是传入需要连续显示的页数
	PageInfo page = new PageInfo(emps, 5);
	model.addAttribute("pageInfo", page);
	return "list";
	
	}*/
}
