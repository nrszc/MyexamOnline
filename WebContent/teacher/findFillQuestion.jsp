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
		<h3>填空题</h3><span style="color:red;">*如果要删除或修改题目，可能会出现一些题目被用做试卷而删除或修改失败的情况</span><br>
		<form action="../ManagementQuestion?method=findfillQuestion" method="post">
	<%
		subject sj = new subject();
		List<Subject> records = sj.getSubject();
	%>
	<label>所属学科：</label><select name="subjectid" class="btn btn-default dropdown-toggle">
	<%if (records == null || records.size() == 0) {
		out.println("<option>还没有学科~</option>");						
		}
	else {
		for (Subject s : records) {	
	%>
	<option value=<%=s.getSubjectid() %> > <%=s.getSubjectname() %></option>
	<%}} %>
	</select>
	<input type="submit"class="btn btn-sm btn-success" value="搜索" />
	</form>
	<br>
	
	
	<div  class="jumbotron">	
<%
List<FillQuestion> record = (List<FillQuestion>)session.getAttribute("fillList");
%>
<%if (record == null || record.size() == 0) {
		out.println("<center><strong>还没有试题哦~</strong></center>");						
		}
	else {%>
	<table class="table table-hover table-bordered" id="table">
	<thead>
    <tr >
     <th>编号</th>
     <th>问题</th>
     <th>答案</th>
     <th>学科</th>
     <th>操作</th>
    </tr>
    </thead>
    <tbody>
		<% for (FillQuestion r : record) {	
	%>
    <tr class="warning">
     <td><%=r.getF_id() %></td>
     <td><%=r.getF_question() %></td>
     <td><%=r.getF_answer() %></td>   
     <td><%=r.getF_subjectname() %></td>    
     <td>
         <a href="#" data-toggle="modal" data-target="#myModal" onclick="values(<%=r.getF_id()%>)">修改</a>
         <a href="../ManagementQuestion?method=deletefillQuestion&f_id=<%=r.getF_id() %>">删除</a>
     </td>
    </tr>
    
   <!--orderList/-->
   <%
		}}
   %>
   </tbody>
   </table>
   </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">选择题</h4>
            </div>
            <form method="post" action="../ManagementQuestion?method=modifyfillQuestion">
            <div class="modal-body">
            <input type="hidden" name="f_id" id="id" /> 
          题目：<input type="text" name="fillQuestion" id="fillQuestion" /><br>
	 答案：<input type="text" name="fillAnswer" id="fillAnswer" />
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
function values(fid) {  
    var id=0;  
    while(1)
    	{
    	if(fid==parseInt(document.getElementById("table").rows[id].cells[0].innerText))
    		break;
    	id++;
    	}
    //获取表格中的一行数据  
    var f_id = document.getElementById("table").rows[id].cells[0].innerText;  
    var f_question = document.getElementById("table").rows[id].cells[1].innerText;  
    var f_answer = document.getElementById("table").rows[id].cells[2].innerText;  
    //alert(c_question);
    //向模态框中传值  
    document.getElementById("id").value = f_id;
    document.getElementById("fillQuestion").value = f_question;
    document.getElementById("fillAnswer").value = f_answer;
    
    /*$('#id').val(c_id);  
    $('#choiceQuestion').val(question);  
    $('#choiceA').val(choiceA); 
    $('#choiceB').val(choiceB);
    $('#choiceC').val(choiceC);
    $('#choiceD').val(choiceD);
    $('#answer').val(answer);
    */
}  
</script>
</body>
</html>