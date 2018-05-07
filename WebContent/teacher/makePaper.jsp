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
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css">
<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.fr.js"></script>
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
      <a class="navbar-brand" href="teacherHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">试题管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addQuestion.jsp">添加试题</a>
								</li>
								<li>
									 <a href="../ManagementQuestion?method=findchoiceQuestion">选择题维护</a>
								</li>
								<li>
									 <a href="../ManagementQuestion?method=findfillQuestion">填空题维护</a>
								</li>
								<li>
									 <a href="../ManagementQuestion?method=findjudgeQuestion">判断题维护</a>
								</li>
							</ul>
			</li>
			<li  Class="active">
							 <a href="makePaper.jsp">发布试卷</a>
			</li>
			<li>
							 <a href="../ReleaseHistory?method=findpaperHistory">历史发布</a>
			</li>
			<li>
							 <a href="../Statistics?method=findStatistics">统计情况</a>
			</li>
        </ul>
    </div>
    <ul class="nav navbar-nav navbar-right">
           <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>我的<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="../PersonalInfo?method=findTeacherInfo">个人信息</a>
								</li>
								<li>
									 <a href="updateTPW.jsp">修改密码</a>
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
		<%
		subject sj = new subject();
		List<Subject> records = sj.getSubject();
		myClass c = new myClass();
		List<Classbean> cl = c.getmyClass();
		%>
		<div class="col-md-4 col-md-offset-4">
		 <h1 class="page-header text-center"><font color="white">试卷发布</font></h1>
		<form action="../MakePaper" method="post">
		<div class="form-group">
	<label class="col-sm-4 control-label"><font color="white">所属学科：</font></label>
	<div class="col-sm-8">
	<select name="subjectid" class="btn btn-default dropdown-toggle">
	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject r : records) {	
	%>
	<option value=<%=r.getSubjectid() %> > <%=r.getSubjectname() %></option>
	<%}} %>
	</select><br>
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-4 control-label"><font color="white">考试班级：</font></label>
	<div class="col-sm-8">
	<select name="classPaper" class="btn btn-default dropdown-toggle">
	<%if (cl == null || cl.size() == 0) {
		out.println("<option>还没有班级~</option>");						
		}
	else {
		for (Classbean r : cl) {	
	%>
	<option value=<%=r.getClassid() %> > <%=r.getClassname() %></option>
	<%}} %>
	</select><br>
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-4 control-label"><font color="white">开始时间：</font></label>
	<div class="col-sm-8">
	<input name="starttime" type="text" readonly class="form_datetime" /><br>
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-4 control-label"><font color="white">结束时间：</font></label>
	<div class="col-sm-8">
	<input name="endtime" type="text" readonly class="form_datetime" /><br>
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-4 control-label"><font color="white">试卷名：</font></label>
	<div class="col-sm-8">
	<input type="text" name="papername" /> <br>
	</div>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<br><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="submit" class="btn btn-success btn-sm " >发布</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="reset" class="btn btn-primary btn-sm ">重置</button>
	
	</form>
	</div>
	</div>
	</div>
<script type="text/javascript">
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>         
</body>
</html>