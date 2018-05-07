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
            <li class="dropdown active">
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
			<li class="dropdown">
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
<span style="color:red;">*如果删除或修改班级，与该班级相关的信息（如该班的学生、试卷、成绩等）也会做出相应的删除或者修改</span>
<%
List<Classbean> record = (List<Classbean>)session.getAttribute("classList");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有班级信息~</strong></center>");						
		}
	else {%>
<table class="table table-hover table-bordered" id="table">
<thead>
<tr>
<th>编号</th>
<th>班级名称</th>
<th>专业</th>
<th>所属学院</th>
<th>年级</th>
<th>操作</th>
</tr>
</thead>
<tbody>

		<% for (Classbean r : record) {	
	%>
   <tr  class="success">
     <td><%=r.getClassid() %></td>
     <td><%=r.getClassname() %></td>
     <td><%=r.getMajor() %></td>  
     <td><%=r.getCollege() %></td>  
     <td><%=r.getGrade() %></td> 
     <td>
         <a href="#" data-toggle="modal" data-target="#myModal" onclick="values(<%=r.getClassid()%>)">修改</a>
         <a href="../ManagementClass?method=deleteClass&classid=<%=r.getClassid() %>">删除</a>
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
                <h4 class="modal-title" id="myModalLabel">班级修改</h4>
            </div>
<form method="post" action="../ManagementClass?method=updateClass">
            <div class="modal-body">
           <input type="hidden" name="classid" id="classid" /> 
        班级名称：<input type="text" name="classname" id="classname" /><br>
       &#12288;&#12288;专业：<input type="text" name="major" id="major"  /> <br>
       所属学院：<input type="text" name="college" id="college" /> <br>
     &#12288;&#12288;年级：<input type="text" name="grade" id="grade" /> 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary">提交更改</button>
            </div>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </form>
       </div>
      </div>     
	</div>
	
	
	</div>
</div>
<script type="text/javascript">
function values(classid) {  
    var id=0;  
    while(1)
    	{
    	if(classid==parseInt(document.getElementById("table").rows[id].cells[0].innerText))
    		break;
    	id++;
    	}
    //获取表格中的一行数据  
    var classid= document.getElementById("table").rows[id].cells[0].innerText;  
    var classname = document.getElementById("table").rows[id].cells[1].innerText;  
    var major = document.getElementById("table").rows[id].cells[2].innerText;  
    var college= document.getElementById("table").rows[id].cells[3].innerText; 
    var grade= document.getElementById("table").rows[id].cells[4].innerText; 
    //alert(j_answer);
    //向模态框中传值  
    document.getElementById("classid").value = classid;
    document.getElementById("classname").value = classname;
    document.getElementById("major").value = major;
    document.getElementById("college").value = college;
    document.getElementById("grade").value = grade;
}
</script>
</body>
</html>