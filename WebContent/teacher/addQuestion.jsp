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
      <a class="navbar-brand" href="teacherHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li class="dropdown active">
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
			<li>
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
		%>
		<div class="row clearfix">
		
		<div class="col-md-6 column">
		<div class="well well-lg">
			<h3>批量上传</h3>
			<form method="post" action="../LoadServlet?method=upload" enctype="multipart/form-data">
		<label>所属学科：</label><select name="subjectid"  class="btn btn-default dropdown-toggle">

	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject r : records) {	
	%>
	<option value=<%=r.getSubjectid() %> > <%=r.getSubjectname() %></option>
	<%}} %>
	   </select><br>
	 <label>题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label> <select name="questiontype"  class="btn btn-default dropdown-toggle">
	   <option value="选择题">选择题</option>
	   <option value="填空题">填空题</option>
	   <option value="判断题">判断题</option>
	   </select><br>
	<label>文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</label><input type="file" name="fileName" />
	  <br><br>
	  <button type="submit" class="btn btn-sm btn-success">添加</button>
		
	   </form>
	   <br><br>
	   
			</div>
		</div>
		<div class="col-md-6 column">
		<div class="well well-lg">
			<h3>
				选择题
			</h3>
				<form action="../ManagementQuestion?method=addchoiceQuestion" method="post">
	
	<label>所属学科：</label><select name="choiceST"  class="btn btn-default dropdown-toggle">
	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject r : records) {	
	%>
	<option value=<%=r.getSubjectid() %> > <%=r.getSubjectname() %></option>
	<%}} %>
	</select>
	
	<div class="input-group input-group-sm">
			<span class="input-group-addon">题目</span>
			<input type="text" class="form-control" name="choiceQuestion" >
		</div>
	<div class="input-group input-group-sm">
			<span class="input-group-addon">A</span>
			<input type="text" class="form-control" name="choiceA" >
		</div>
	<div class="input-group input-group-sm">
			<span class="input-group-addon">B</span>
			<input type="text" class="form-control" name="choiceB" >
		</div>
		<div class="input-group input-group-sm">
			<span class="input-group-addon">C</span>
			<input type="text" class="form-control" name="choiceC" >
		</div>
		<div class="input-group input-group-sm">
			<span class="input-group-addon">D</span>
			<input type="text" class="form-control" name="choiceD" >
		</div>
	
	<div class="form-group">
    <div class="col-sm-offset-1 col-sm-10">
      <div class="checkbox">
       <label> 答案：</label> 
        <label class="radio-inline"> 
        <input type="radio" name="choiceAnswer" id="choiceAnswerA" value="A" checked> A
    </label>
    <label class="radio-inline">
        <input type="radio" name="choiceAnswer" id="choiceAnswerB"  value="B"> B
    </label>
    <label class="radio-inline">
        <input type="radio" name="choiceAnswer" id="choiceAnswerC"  value="C"> C
    </label>
    <label class="radio-inline">
        <input type="radio" name="choiceAnswer" id="choiceAnswerD"  value="D"> D
    </label>
      </div>
    </div>
  </div>
  <input type="submit"class="btn btn-sm btn-success" value="添加" />
  </form>
			</div>
		</div>
	</div>
	
	<div class="row clearfix">
		
		<div class="col-md-6 column">
		<div class="well well-lg">
			<h3>
				  填空题
			</h3>
			 <form action="../ManagementQuestion?method=addfillQuestion" method="post">

  <label>所属学科：</label><select name="fillST" class="btn btn-default dropdown-toggle">
	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject r : records) {	
	%>
	<option value=<%=r.getSubjectid() %> > <%=r.getSubjectname() %></option>
	<%}} %>
	</select>
	<br>
  <div class="input-group input-group-sm">
			<span class="input-group-addon">题目</span>
			<input type="text" class="form-control" name="fillQuestion" >
		</div>
 <div class="input-group input-group-sm">
			<span class="input-group-addon">答案</span>
			<input type="text" class="form-control" name="fillAnswer" >
		</div>
 <input type="submit" value="添加" class="btn btn-sm btn-success"/>
	</form>
			</div>
		</div>
		<div class="col-md-6 column">
		<div class="well well-lg">
			<h3>
			判断题
			</h3>
			
		<form action="../ManagementQuestion?method=addjudgeQuestion" method="post">

 <label>所属学科：</label><select name="judgeST" class="btn btn-default dropdown-toggle">
	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject r : records) {	
	%>
	<option value=<%=r.getSubjectid() %> > <%=r.getSubjectname() %></option>
	<%}} %>
	</select>
	<div class="input-group input-group-sm">
			<span class="input-group-addon">题目</span>
			<input type="text" class="form-control" name="judgeQuestion" >
		</div>
 
 <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>答案：</label>  
        <label class="radio-inline">
        <input type="radio" name="judgeAnswer" id="True" value="True" checked> √
    </label>
    <label class="radio-inline">
        <input type="radio" name="judgeAnswer" id="False"  value="False"> ×
    </label>
      </div>
    </div>
  </div>
  <input type="submit" value="添加" class="btn btn-sm btn-success"/>
  </form>
			</div>
		</div>
	</div>
	</div>
</div>
</body>
</html>