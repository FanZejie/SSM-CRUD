package com.fzj.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fzj.bean.Department;
import com.fzj.bean.Employee;
import com.fzj.dao.DepartmentMapper;
import com.fzj.dao.EmployeeMapper;

/**
 * 测试dao层的工作
 * 这里使用Spring的项目就可以使用Spring的单元测试,可以自动注入我们需要的组件
 * @author Mike-laptop
 *1.导入SpringTest模块
 *2.使用@ContextConfiguration指定Spring配置文件的位置
 *@RunWith(SpringJUnit4ClassRunner.class)指定那种测试方式
 *3.直接autowired创建所需组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
    /**
     * 测试DepartmentMapper
     */
	@Test
    public void testCRUD() {
    	System.out.println(departmentMapper);
    	//1.插入几个部门
    	//departmentMapper.insertSelective(new Department(null, "开发部"));
    	//departmentMapper.insertSelective(new Department(null, "测试部"));
    	
    	//2.生成员工数据,测试员工插入
    	//employeeMapper.insertSelective(new Employee(null,"Mike","M","Mike@163.com",1));
    	//3.批量插入多个员工方便测试,使用可以执行批量操作的sqlSession
		/*for () {这并不是一个批量的操作
			employeeMapper.insertSelective(new Employee(null,"Mike","M","Mike@163.com",1));
		}*/
    	EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
    	for (int i = 0; i < 1000; i++) {
    		String uid = UUID.randomUUID().toString().substring(0,5)+""+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@163.com",1));
		}
    	System.out.println("批量插入完成");
    }
}
