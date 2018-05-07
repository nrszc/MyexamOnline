package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.ManagementStudentIF;
import henu.IF.ManagementSubjectIF;
import henu.bean.Classbean;
import henu.bean.Student;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ManagementStudent
 */
@WebServlet("/ManagementStudent")
public class ManagementStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myway(request, response);
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		      case "addStudent"    : addStudent(request, response);     break;
		      case "deleteStudent" : deleteStudent(request, response);  break;
		      case "updateStudent" : updateStudent(request, response);  break;
		      case "findStudent"   : findStudent(request, response);    break;
		}
	}
	private void findStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementStudentIF ms = DaoFactory.getManagementStudentDaoInstance();
		List<Student> list = ms.findstudent();
		request.getSession().setAttribute("studentList", list);
		response.sendRedirect("admin/findStudent.jsp");	
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementStudentIF ms = DaoFactory.getManagementStudentDaoInstance();
		Student s=new Student();
		s.setSid(request.getParameter("sid"));
		s.setNewsid(request.getParameter("newsid"));
		s.setSname(request.getParameter("sname"));
		boolean result=ms.updatestudent(s);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		     List<Student> list = ms.findstudent();
		     request.getSession().setAttribute("studentList", list);
		     out.println("<script>alert('修改成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findStudent.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('修改失败!');history.back();</script>");
		}	
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementStudentIF ms = DaoFactory.getManagementStudentDaoInstance();
		String sid = request.getParameter("sid");
		boolean result = ms.deletestudent(sid);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Student> list = ms.findstudent();
		     request.getSession().setAttribute("studentList", list);
		     out.println("<script>alert('删除成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findStudent.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementStudentIF ms = DaoFactory.getManagementStudentDaoInstance();
		Student s=new Student();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setClassid(request.getParameter("classid"));
		s.setPwd(request.getParameter("pwd"));
		boolean r=ms.addstudent(s);
		PrintWriter   out   =   response.getWriter(); 
		if(r==true)
		{
            out.println("<script>alert('添加成功!');</script>");
            out.println("<script>window.location.href='/MyexamOnline/admin/addStudent.jsp'</script>");
        }
		else 
		{
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
