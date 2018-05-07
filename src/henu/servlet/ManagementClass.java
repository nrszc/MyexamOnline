package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.ManagementClassIF;
import henu.bean.Classbean;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ManagementClass
 */
@WebServlet("/ManagementClass")
public class ManagementClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementClass() {
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
		      case "addClass"    : addClass(request, response);     break;
		      case "deleteClass" : deleteClass(request, response);  break;
		      case "updateClass" : updateClass(request, response);  break;
		      case "findClass"   : findClass(request, response);    break;
		}
	}
	private void findClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementClassIF mc=DaoFactory.getManagementClassDaoInstance();
		List<Classbean> list = mc.findclass();
		request.getSession().setAttribute("classList", list);
		response.sendRedirect("admin/findClass.jsp");
	}

	private void updateClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementClassIF mc=DaoFactory.getManagementClassDaoInstance();
		int classid= Integer.parseInt(request.getParameter("classid"));
		String classname=request.getParameter("classname");
		String major=request.getParameter("major");
		String college=request.getParameter("college");
		String grade=request.getParameter("grade");
		Classbean s=new Classbean();
		s.setClassid(classid);
		s.setClassname(classname);
		s.setMajor(major);
		s.setCollege(college);
		s.setGrade(grade);
		boolean result=mc.updateclass(s);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		     List<Classbean> list = mc.findclass();
		     request.getSession().setAttribute("classList", list);
		     out.println("<script>alert('修改成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findClass.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('修改失败!');history.back();</script>");
		}
	}

	private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementClassIF mc=DaoFactory.getManagementClassDaoInstance();
		int classid = Integer.parseInt(request.getParameter("classid"));
		boolean result = mc.deleteclass(classid);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Classbean> list = mc.findclass();
		     request.getSession().setAttribute("classList", list);
		     out.println("<script>alert('删除成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findClass.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	private void addClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementClassIF mc=DaoFactory.getManagementClassDaoInstance();
		Classbean s=new Classbean();
		s.setClassname(request.getParameter("classname"));
		s.setCollege(request.getParameter("college"));
		s.setGrade(request.getParameter("grade"));
		s.setMajor(request.getParameter("major"));
		boolean result=mc.addclass(s);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            out.println("<script>window.location.href='/MyexamOnline/admin/addClass.jsp'</script>");
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
