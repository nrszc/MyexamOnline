<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>在线考试系统</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/b_login.css">
<script src="js/jquery-2.1.4.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>	
<div class="login">
	<div class="loginmain">
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户登录</h2>
			<form class="form-horizontal" role="form" action="LoginServlet" method="post">
		           <!-- 让表单在一行中显示form-horizontal -->
		          <div class="form-group">
		              <label for="username" class="col-lg-1 control-label">账号</label>
		              <div class="col-lg-4">
		                <input type="text" class="form-control" name ="userID" placeholder="请输入账号">
		              </div>              
		          </div>
				  <div class="form-group"></div>
				  <div class="form-group"></div>

		          <div class="form-group">
		              <label for="password" class="col-lg-1 control-label">密码</label>
		              <div class="col-lg-4">
		                <input type="password" class="form-control" name = "pwd" placeholder="请输入密码">
		              </div>              
		          </div>
		          <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label class="radio-inline">
        <input type="radio" name="usertype" id="optionsRadios3" value="student" checked> 学生
    </label>
    <label class="radio-inline">
        <input type="radio" name="usertype" id="optionsRadios4"  value="teacher"> 教师
    </label>
    <label class="radio-inline">
        <input type="radio" name="usertype" id="optionsRadios5"  value="admin"> 管理员
    </label>
      </div>
    </div>
  </div>
 
		          <div class="form-group">
		            <div class="col-lg-4 col-lg-offset-1">
		               <button type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
		                <span class="text"></span>              
		            </div>

		          </div>

		    </form>
	</div>
	<div class="rightpic">
		<div id="container">
			<!-- <img src="picture/1.jpg" alt="" width="500px" height="330px"> -->
		</div>
	</div>
</div>

</div>
	</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/delaunay.js"></script>
<script src="js/TweenMax.js"></script>
<script src="js/effect.js"></script>
</body>
</html>