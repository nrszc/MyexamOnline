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

<div  class="jumbotron">
			<%
List<Errors> record = (List<Errors>)session.getAttribute("mistakeList");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有错题信息~</strong></center>");						
		}
else {%>
<table id="table" class="table table-hover table-bordered">
<thead>
<tr>
 <th>试卷名</th>
 <th>开考时间</th>
 <th>结束时间</th>
 <th>考试班级</th>
 <th>考试分数</th>
 <th>学科名</th>
 <th>教师</th>
 <th>错题数</th>
 <th>操作</th>
</tr>
</thead>
<tbody>
	<% for (Errors r : record) {	
%>
<tr class="warning">
 <td><%=r.getPapername() %></td>
 <td><%=r.getStarttime() %></td>
  <td><%=r.getEndtime() %></td>
  <td><%=r.getClassname() %></td>
 <td><%=r.getScore() %></td>
  <td><%=r.getSubjectname() %></td>
  <td><%=r.getTname() %></td>
  <td><%=r.getMistakessum() %></td>
 <td>
     <a href="../MyMistake?method=detailMistake&paperid=<%=r.getPaperid() %>">查看错题</a>
 </td>
</tr>

<!--orderList/-->
<%
	}}
%>
 </tbody>
</table>
	</div>
	</div>
</div>
</div>
</body>
</html>