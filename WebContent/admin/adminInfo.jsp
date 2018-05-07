<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="java.sql.*"
    import="henu.bean.*"
    import="henu.others.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>在线考试系统</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
body{background-image: url(../picture/bg1.jpg);
	background-size: cover;
}
</style>
</head>
<body>		
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="adminHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">班级管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addClass.jsp">添加班级</a>
								</li>
								<li>
									 <a href="../ManagementClass?method=findClass">班级维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">学生管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addStudent.jsp">添加学生</a>
								</li>
								<li>
									 <a href="../ManagementStudent?method=findStudent">学生维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">教师管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addTeacher.jsp">添加教师</a>
								</li>
								<li>
									 <a href="../ManagementTeacher?method=findTeacher">教师维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">学科管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addSubject.jsp">添加学科</a>
								</li>
								<li>
									 <a href="../ManagementSubject?method=findSubject">学科维护</a>
								</li>
							</ul>
			</li>
        </ul>
    </div>
    <ul class="nav navbar-nav navbar-right">
           <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>我的<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="../PersonalInfo?method=findAdminInfo">个人信息</a>
								</li>
								<li>
									 <a href="updateAPW.jsp">修改密码</a>
								</li>
								<li>
									 <a href="../LoginServlet?usertype=logout">注销</a>
								</li>
							</ul>
			</li>
    </ul>
  </div>
</nav>
</div>


<%Admin r = (Admin)session.getAttribute("adminInfo"); %>		
<div class="col-md-4 col-md-offset-4">
      <h1 class="page-header text-center"><font color="white">个人信息</font></h1>
<form class="form-horizontal" role="form" action="../PersonalInfo?method=updateAdminInfo" method="post">
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label"><font color="white">账号</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name ="sid" readonly="readonly" value="<%=r.getAid() %>">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">姓名</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "sname" readonly="readonly" value="<%=r.getAname() %>">
    </div>
  </div>
 <%if(r.getSex()==null||r.getSex().equals("")){ %>
  <div>
   <label for="lastname" class="col-sm-2 control-label"><font color="white">性别</font></label>
	<label class="radio-inline">
	 &nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男" >男
	</label>
	<label class="radio-inline">
		<input type="radio" name="sex" value="女" > 女
	</label>
  </div>
<%}else if(r.getSex().equals("女")){ %>
<label for="lastname" class="col-sm-2 control-label"><font color="white">性别</font></label>
<div>
 
	<label class="radio-inline">
		&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男"> 男
	</label>
	<label class="radio-inline">
		<input type="radio" name="sex" value="女" checked> 女
	</label>
  </div>
<%}else{ %>
<div>
 <label for="lastname" class="col-sm-2 control-label"><font color="white">性别</font></label>
	<label class="radio-inline">
		&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男" checked> 男
	</label>
	<label class="radio-inline">
		<input type="radio" name="sex" value="女" > 女
	</label>
  </div>
<%} %>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">电话</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "phone" value="<%=r.getPhone() %>">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">住址</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "address" value="<%=r.getAddress() %>">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">生日</font></label>
    <div class="col-sm-10">
     <input type="date" class="form-control" name = "birthdate" value="<%=r.getBirthdate() %>" />
      
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">邮箱</font></label>
    <div class="col-sm-10">
    <input type="email" class="form-control" name = "email" value="<%=r.getEmail() %>" />
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary btn-lg btn-block"  onclick="myFunction()">保存修改</button>
    </div>
  </div>
</form>	
</div>
	</div>
</div>
</body>
</html>