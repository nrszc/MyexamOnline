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
body{background-image: url(../picture/bg3.jpg);
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
			<li>
							 <a href="makePaper.jsp">发布试卷</a>
			</li>
			<li  Class="active">
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
	<%
	int paperid = Integer.parseInt((String)session.getAttribute("paperid"));
	myPaper mp = new myPaper();
	String papername = mp.getPaperName(paperid);
	%>	
<div  class="jumbotron">
<h2 class="text-center"><%=papername %><h2>
<h4>一、选择题(5分)。</h4>
<%
int i = 1;
List<ChoiceQuestion> record = (List<ChoiceQuestion>)session.getAttribute("choiceQPaper");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有试题哦~</strong></center>");						
		}
	else {%>
		<% for (ChoiceQuestion r : record) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i %>、<%=r.getC_question() %></li>
    <li class="list-group-item">A、<%=r.getC_choiceA() %></li>
    <li class="list-group-item">B、<%=r.getC_choiceB() %></li>
    <li class="list-group-item">C、<%=r.getC_choiceC() %></li>
    <li class="list-group-item">D、<%=r.getC_choiceD() %></li>
    <li class="list-group-item">
    请选择：
        <input type="radio" name="choiceAnswer" > A
        <input type="radio" name="choiceAnswer" > B
        <input type="radio" name="choiceAnswer" > C
        <input type="radio" name="choiceAnswer" > D
    </li>
   
</ul>
   <%
		i++;}}
   %>
		<h4>二、填空题(5分)。</h4>
	<%
List<FillQuestion> records = (List<FillQuestion>)session.getAttribute("fillQPaper");
%>
<%if (records == null || records.size() == 0) {
		out.println("<center><strong>还没有试题哦~</strong></center>");						
		}
	else {%>
		<% for (FillQuestion r : records) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i %>、<%=r.getF_question() %></li>
    <li class="list-group-item">
    请填写:
        <input type="text" name="fillAnswer" id="fillAnswer" />
    </li>
   
</ul>
   <%
		i++;}}
   %>
<h4>三、判断题(5分)。</h4>
<%
List<JudgeQuestion> re = (List<JudgeQuestion>)session.getAttribute("judgeQPaper");
%>
<%if (re == null || re.size() == 0) {
		out.println("<center><strong>还没有试题哦~</strong></center>");						
		}
	else {%>
		<% for (JudgeQuestion r : re) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i %>、<%=r.getJ_question() %></li>
    <li class="list-group-item">
    请选择:
       <input type="radio" name="fillAnswer" id="True" value="True" > √
       <input type="radio" name="fillAnswer" id="False" value="False" > ×
    </li>
   
</ul>
   <%
		i++;}}
   %>

</div>

  </div >
	</div>
</div>
</body>
</html>