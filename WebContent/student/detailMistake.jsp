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
			<li Class="active">
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
%>
<div  class="jumbotron">
<h2 class="text-center"><%=mp.getPaperName(paperid) %></h2>	
<h4>一、选择题(5分)。</h4>
<%
int i = 1;
List<Mistakes> record = (List<Mistakes>)session.getAttribute("choice");
%>
<%for (Mistakes r : record) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i%>、<%=r.getC_question() %></li>
    <li class="list-group-item">A、<%=r.getC_choiceA() %></li>
    <li class="list-group-item">B、<%=r.getC_choiceB() %></li>
    <li class="list-group-item">C、<%=r.getC_choiceC() %></li>
    <li class="list-group-item">D、<%=r.getC_choiceD() %></li>
    <li class="list-group-item">正确答案：<%=r.getC_answer() %></li>
    <li class="list-group-item">你的答案：<%=r.getMisanswer() %></li>
       </ul>
<%
		i++;}
   %>
<h4>二、填空题(5分)。</h4>
<%
List<Mistakes> record1 = (List<Mistakes>)session.getAttribute("fill");
%>
<% for (Mistakes r : record1) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i%>、<%=r.getF_question() %></li>
    <li class="list-group-item">正确答案：<%=r.getF_answer() %></li>
    <li class="list-group-item">你的答案：<%=r.getMisanswer() %></li>
       </ul>
<%
		i++;}
   %>
<h4>三、判断题(5分)。</h4>
  <%
List<Mistakes> record2 = (List<Mistakes>)session.getAttribute("judge");
%>
<% for (Mistakes r : record2) {	
	%>
   <ul class="list-group">
    <li class="list-group-item"><%=i%>、<%=r.getJ_question() %></li>
    <li class="list-group-item">正确答案：<%=r.getJ_answer() %></li>
    <li class="list-group-item">你的答案：<%=r.getMisanswer() %></li>
       </ul>
<%
		i++;}
   %>
   </div>

	</div>
</div>
</body>
</html>