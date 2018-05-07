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

<script>

var myVar=setInterval(function(){myTimer()},1000);
function myTimer(){
	var min = document.getElementById("min").innerHTML;
	var s = document.getElementById("s").innerHTML;
	if(min==0&&s==0)
		{
		     alert("时间已到，系统将自动为您交卷!");
			 document.getElementById("examform").submit();
		}
	if(s>=0)
		{
		s--;
		document.getElementById("s").innerHTML=s;
		}
	if(s==-1&&min>0){
		s=59;
		min--;
		document.getElementById("s").innerHTML=s;
		document.getElementById("min").innerHTML=min;
	}
}
</script>
<style>
.div1{
position:fixed;
right:0;
top:7%;
height:100px;
width:100px;
background:white;
}
</style>

</head>
<body onload="init()">		
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="studentHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li Class="active">
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
		</div>
		
		<%
	int paperid = Integer.parseInt((String)session.getAttribute("paperid"));
	myPaper mp = new myPaper();
	String papername = mp.getPaperName(paperid);
	CalcTime ct = new CalcTime();
	%>	
<div  class="jumbotron">
<form action="../ExamOnline?method=countScore&paperid=<%=paperid %>" method="post" id="examform">
<div class="div1">
<h4>倒计时</h4><br>
<span id="min" style="color:red;"> <%=ct.getmin(paperid) %> </span>分
<span id="s" style="color:red;"> <%=ct.getseconds(paperid) %></span>秒
</div>
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
        <input type="radio" name="<%=i %>"  value="A" > A
        <input type="radio" name="<%=i %>"  value="B" > B
        <input type="radio" name="<%=i %>"  value="C" > C
        <input type="radio" name="<%=i %>"  value="D" > D
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
        <input type="text" name="<%=i %>" />
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
       <input type="radio" name="<%=i %>" value="1" > √
       <input type="radio" name="<%=i %>" value="0" > ×
    </li>
   
</ul>
   <%
		i++;}}
   %>
   <button class="btn btn-success" Onclick="return OutExam()">退出考试</button>
   <button class="btn btn-danger" Onclick="return Submit()">提交试卷</button>
</form>
</div>
	</div>
</div>

<script type="text/javascript">
function OutExam(){
	var con;
	con=confirm("您确定要退出考试吗?");
	if(con==true)
		window.location.href="studentHome.jsp";
	return false;
}
function Submit()
{
	var con1;
	con1 = confirm("确定要提交试卷吗?");
	if(con1==true)
		 document.getElementById("examform").submit();
	return false;
}
</script>

</body>
</html>