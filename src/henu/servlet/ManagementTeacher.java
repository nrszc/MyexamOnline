package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.ManagementTeacherIF;
import henu.bean.Teacher;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ManagementTeacher
 */
@WebServlet("/ManagementTeacher")
public class ManagementTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementTeacher() {
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
		      case "addTeacher"    : addTeacher(request, response);     break;
		      case "deleteTeacher" : deleteTeacher(request, response);  break;
		      case "updateTeacher" : updateTeacher(request, response);  break;
		      case "findTeacher"   : findTeacher(request, response);    break;
		}
	}

	private void findTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementTeacherIF mt = DaoFactory.getManagementTeacherDaoInstance();
		List<Teacher> list = mt.findteacher();
		request.getSession().setAttribute("teacherList", list);
		response.sendRedirect("admin/findTeacher.jsp"); 
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Teacher tc = new Teacher();
		tc.setTid(request.getParameter("tid"));
		tc.setTname(request.getParameter("tname"));
		tc.setNewtid(request.getParameter("newtid"));
		ManagementTeacherIF mt = DaoFactory.getManagementTeacherDaoInstance();
		boolean result = mt.updateteacher(tc);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Teacher> list = mt.findteacher();
		     request.getSession().setAttribute("teacherList", list);
		     out.println("<script>alert('修改成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findTeacher.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('修改失败，可能是次账号已被注册，请重新输入账号!');history.back();</script>");
		}
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid= request.getParameter("tid");
		ManagementTeacherIF mt = DaoFactory.getManagementTeacherDaoInstance();
		boolean result = mt.deleteteacher(tid);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Teacher> list = mt.findteacher();
		     request.getSession().setAttribute("teacherList", list);
		     out.println("<script>alert('删除成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findTeacher.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Teacher tc = new Teacher();
		tc.setTname(request.getParameter("tname"));
		tc.setTid(request.getParameter("tid"));
		tc.setPwd(request.getParameter("pwd"));
		ManagementTeacherIF mt = DaoFactory.getManagementTeacherDaoInstance();
		boolean result = mt.addteacher(tc);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
            out.println("<script>alert('添加成功!');</script>");
            out.println("<script>window.location.href='/MyexamOnline/admin/addTeacher.jsp'</script>");
		}
		else{
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
