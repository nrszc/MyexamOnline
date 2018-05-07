<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
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
            <li class="dropdown active">
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
<div class="col-md-6 col-md-offset-3">
      <h1 class="page-header text-center" ><font color="white">班级添加</font></h1>
<form class="form-horizontal" role="form" action="../ManagementClass?method=addClass" method="post">
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">班级名称</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "classname" placeholder="请输入班级名称">
    </div>
  </div>
<div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">专业</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "major" placeholder="请输入专业名称">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">所属学院</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "college" placeholder="请输入学院名称">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label"><font color="white">年级</font></label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name = "grade" placeholder="请输入年级名称">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-6">
      <button type="submit" class="btn btn-primary btn-lg btn-block" >添加</button>
    </div>
  </div>

</form>
	</div>
</div>

</body>
</html>