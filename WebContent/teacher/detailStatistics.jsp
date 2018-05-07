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
			<li >
							 <a href="../ReleaseHistory?method=findpaperHistory">历史发布</a>
			</li>
			<li Class="active">
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
		
<div  class="jumbotron">
<%
List<DetailStatistics> record = (List<DetailStatistics>)session.getAttribute("detailStatistics");
String paperid = (String)session.getAttribute("paperid");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>没有详细情况哦~</strong></center>");						
		}
	else {%>
	<table id="table" class="table table-hover table-bordered">
    <thead>
    <tr>
     <th>学生学号</th>
     <th>学生姓名</th>
     <th>分数</th>
    </tr>
    <thead>
    <tbody>
		<% for (DetailStatistics r : record) {	
	%>
    <tr class="warning">
     <td><%=r.getSid() %></td>
     <td><%=r.getSname() %></td>
     <td><%=r.getScore() %></td>
    </tr>
    
   <!--orderList/-->
   <%
		}}
   %>
   </tbody>
   </table>
   <form method="post" action="../LoadServlet?method=download&paperid=<%=paperid %>" >
   <button type="submit"  class="btn btn-primary">导出成绩单</button>
   </form>
</div>

  </div >
	</div>
</div>
</body>
</html>