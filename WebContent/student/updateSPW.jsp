<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <a class="navbar-brand" href="studentHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li>
							 <a href="../ExamOnline?method=examList">考试答题</a>
			</li>
			<li>
							 <a href="../MyScore">个人成绩</a>
			</li>
			<li>
							 <a href="../MyMistake?method=mistakeList">错题归纳</a>
			</li>
        </ul>
    </div>
    <ul class="nav navbar-nav navbar-right">
           <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>我的<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="../PersonalInfo?method=findStudentInfo">个人信息</a>
								</li>
								<li>
									 <a href="updateSPW.jsp">修改密码</a>
								</li>
								<li>
									 <a href="../LoginServlet?usertype=logout">注销</a>
								</li>
							</ul>
			</li>
    </ul>
</div>
</nav>
<div class="col-md-6 col-md-offset-3">
      <h1 class="page-header text-center" ><font color="white">修改密码</font></h1>
<form class="form-horizontal" role="form" action="../UpdatePW?method=updateSPW" method ="post">
<div class="form-group">
<label for="lastname" class="col-sm-5 control-label"><font color="white">旧密码</font></label>
<div class="col-sm-7">
<input type ="password" class="form-control" name = "oldcode" placeholder="请输入旧密码">
</div>
</div>
<div class="form-group">
<label for="lastname" class="col-sm-5 control-label"><font color="white">新密码</font></label>
<div class="col-sm-7">
<input type ="password" class="form-control" name = "newcode1" placeholder="请输入新密码">
 </div>
  </div>
  <div class="form-group">
<label for="lastname" class="col-sm-5 control-label"><font color="white">确定新密码</font></label>
    <div class="col-sm-7">
<input type ="password" class="form-control" name = "newcode2" placeholder="请再次输入新密码">
 </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-6">
 <button type="submit" class="btn btn-primary btn-lg btn-block" >提交</button>
    </div>
  </div>
</form>
	</div>
</div>
</body>
</html>