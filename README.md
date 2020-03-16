# SSM-CRUD
## 项目简介
该项目使用SSM框架实现最基本的增删改查,可以帮助刚刚学习完SSM框架的朋友进行复习  
### 项目完成预览图
![image](https://raw.githubusercontent.com/FanZejie/SSM-CRUD/master/img/index.png)
### 项目亮点
关于项目亮点只能说是我个人认为,可能大家已经对此习以为常.  
1. MyBatis逆向工程,自动生成JavaBean,以及Mapper文件  
    当数据表比较多时就能体会到他的好处了,手写JavaBean不但要对比数据表担心有遗漏,而且给你十几张表,直接写到心态爆炸.  
    自动生成的Mapper不用我们写很多的sql语句,它里面会自动生成很对的增删改查的方法,当然需要连表查询的话,复制修改部分代码就好.  
2. 清晰的业务逻辑关系:  
    1.前端发送Ajax请求到后端,SpringMvc前端控制器拦截下请求,跳转到相应的Controller,  
    2.Controller->Service->Dao通过Spring 的 @Autowired注解实现自动装配  
    3.最后SpringMvc前端控制器再通过@ResponseBody返回给前端一个json字符串  
    4.前端使用js解析json字符串在前端进行展示  
3. 使用pageHelper分页插件+bootstrap框架快速实现分页功能  
4. 使用Spring测试模块提供的测试请求功能,测试crud请求的正确性  
5. 前端数据校验+后端数据校验  
