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
      <a class="navbar-brand" href="adminHome.jsp">2333在线考试系统</a>
    </div>
     <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">班级管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addClass.jsp">添加班级</a>
								</li>
								<li>
									 <a href="../ManagementClass?method=findClass">班级维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">学生管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addStudent.jsp">添加学生</a>
								</li>
								<li>
									 <a href="../ManagementStudent?method=findStudent">学生维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown active">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">教师管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addTeacher.jsp">添加教师</a>
								</li>
								<li>
									 <a href="../ManagementTeacher?method=findTeacher">教师维护</a>
								</li>
							</ul>
			</li>
			<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">学科管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="addSubject.jsp">添加学科</a>
								</li>
								<li>
									 <a href="../ManagementSubject?method=findSubject">学科维护</a>
								</li>
							</ul>
			</li>
        </ul>
    </div>
    <ul class="nav navbar-nav navbar-right">
           <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>我的<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="../PersonalInfo?method=findAdminInfo">个人信息</a>
								</li>
								<li>
									 <a href="updateAPW.jsp">修改密码</a>
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
<span style="color:red;">*如果删除或修改教师，与该教师相关的信息（如该教师发布的试卷、试卷的成绩、错题等）也会做出相应的删除或者修改</span>
			
<%
List<Teacher> record = (List<Teacher>)session.getAttribute("teacherList");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有试题哦~</strong></center>");						
		}
	else {%>
	
	<table class="table table-hover table-bordered" id="table">
    <thead>
    <tr>
    <th>账号</th>
    <th>教师名</th>
    <th>性别</th>
    <th>出生日期</th>
    <th>邮件</th>
    <th>手机</th> 
    <th>地址</th>
    <th>操作</th>
    </tr>
    </thead>
			<tbody>
		<% for (Teacher r : record) {	
	%>
     <tr  class="success">
     <td><%=r.getTid() %></td>
     <td><%=r.getTname() %></td> 
     <td><% if(r.getSex()==null) out.print("-"); else out.print(r.getSex()); %> </td>  
     <td><% if(r.getBirthdate()==null) out.print("-"); else out.print(r.getBirthdate()); %> </td> 
     <td><% if(r.getEmail()==null) out.print("-"); else out.print(r.getEmail()); %> </td> 
     <td><% if(r.getPhone()==null) out.print("-"); else out.print(r.getPhone()); %> </td> 
     <td><% if(r.getAddress()==null) out.print("-"); else out.print(r.getAddress()); %> </td> 
     <td>
         <a href="#" data-toggle="modal" data-target="#myModal" onclick="values(<%=r.getTid()%>)">修改</a>
         <a href="../ManagementTeacher?method=deleteTeacher&tid=<%=r.getTid() %>">删除</a>
     </td>
    </tr>
    
   <!--orderList/-->
   <%
		}}
   %>
  	</tbody>
 
</table>

  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">选择题</h4>
            </div>
            <form method="post" action="../ManagementTeacher?method=updateTeacher">
            <div class="modal-body">
            <input type="hidden" name="tid" id="tid" /> 
          &#12288;账号：<input type="text" name="newtid" id="newtid" /> <br>
                 教师名：<input type="text" name="tname" id="tname" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary">提交更改</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

  
	</div>
</div>
<script type="text/javascript">
function values(subjectid) {  
    var id=0;  
    while(1)
    	{
    	if(subjectid==document.getElementById("table").rows[id].cells[0].innerText)
    		break;
    	id++;
    	}
    //获取表格中的一行数据  
    var tid = document.getElementById("table").rows[id].cells[0].innerText;  
    var tname = document.getElementById("table").rows[id].cells[1].innerText;  
    
    //向模态框中传值  
    document.getElementById("tid").value = tid;
    document.getElementById("newtid").value = tid;
    document.getElementById("tname").value = tname;
  
}  
</script>
</body>
</html>