<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="java.sql.*"
    import="henu.bean.*"
    import="henu.others.*"
    import="java.util.Date"
    import="java.text.SimpleDateFormat"
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
			<li  >
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
List<StatisticsTable> record = (List<StatisticsTable>)session.getAttribute("Statistics");
Date day=new Date();    
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有统计情况哦~</strong></center>");						
		}
	else {%>
	<table id="table" class="table table-hover table-bordered">
    <thead>
    <tr>
     <th>试卷名称</th>
     <th>学科</th>
     <th>开考时间</th>
     <th>结束时间</th>
     <th>考试班级</th>
     <th>发布教师</th>
     <th>最低分</th>
     <th>最高分</th>
     <th>平均分</th>
     <th>操作</th>
    </tr>
    </thead>
    <tbody>
		<% for (StatisticsTable r : record) {	
	%>
    <tr class="warning">
     <td><%=r.getPapername() %></td>
     <td><%=r.getSubjectname() %></td>
     <td><%=r.getStarttime() %></td>
     <td><%=r.getEndtime() %></td>
     <td><%=r.getClassname() %></td>
     <td><%=r.getTeachername() %></td>
     <td><%=r.getLowestscore() %></td>
     <td><%=r.getHighestscore() %></td>
     <td><%=r.getAverscore() %></td>
     <td>
      <%Date bt=df.parse(r.getEndtime());
     if(df.parse(df.format(day)).before(bt)){
     %>
     请耐心等待
     <%}else{ %>
         <a href="../Statistics?method=detailStatistics&paperid=<%=r.getPaperid() %>">查看成绩单</a>
          <%}%>
     </td>
    </tr>
    
   <!--orderList/-->
   <%
		}}
   %>
   </tbody>
   </table>
</div>

  </div >
	</div>
</div>
</body>
</html>