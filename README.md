# SSM-CRUD
## 项目简介
该项目使用SSM框架实现最基本的增删改查,可以帮助刚刚学习完SSM框架的朋友进行复习  
其实感觉复杂的项目也就是由最基本的增删改查,数据展示组成的,所以这个项目很适合作为SSM的第一个入手项目  
### 项目完成预览图
![image](https://raw.githubusercontent.com/FanZejie/SSM-CRUD/master/img/index.png)
#### 说明
##### 查
页面加载时从数据库中取出员工信息分页展示,具备完整分页跳转功能(点击跳转,当前页高亮,首页时前一页禁用等)  
##### 增
点击新增按钮,弹出模态框,部门信息要从数据库中取出并展示在这里
![image](https://raw.githubusercontent.com/FanZejie/SSM-CRUD/master/img/add.png)
同时要进行数据校验,分为前端数据校验和后端数据校验  
每次点开弹窗要清空弹窗内容,初步防止重复提交数据  
点击保存按钮保存员工到数据库,并跳转到该页面,可以省去用户自己刷新  
##### 改
与新增差不多,有以下要注意  
![image](https://raw.githubusercontent.com/FanZejie/SSM-CRUD/master/img/edit.png)
点击按钮时,按钮有一个id用来标识当前员工,  
弹窗内有按钮id对应的员工信息,  
姓名不能修改,邮箱做前后端验证  
点击更新按钮,提交修改数据,跳转到修改员工的页面,相当于是刷新了下页面,提高用户体验  
##### 删
![image](https://raw.githubusercontent.com/FanZejie/SSM-CRUD/master/img/delete.png)
分为单一删除和批量删除  
点击删除弹出弹框询问是否确认删除[xxx,xxx]
点击确认完成删除,跳转到操作页面
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
### 后记
这个项目是我在B站上看到的雷丰阳雷大神讲解,觉得很好于是照着视频敲了一遍,  
之所以放在git并无侵权之意,只是想方便日后复制配置部分以及参考  
视频链接会放在这里:  
[视频链接](https://www.bilibili.com/video/av35988777?p=35)
期间有版本不兼容问题进行了修改,如果你照着视频敲也出现了不知道的错误可以去我的博客看关于这个项目的笔记(看完记得点赞就好)
[博客链接](https://blog.csdn.net/qq_42542609)
如果还是有错误可以博客下留言评论  
