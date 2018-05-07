package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import henu.IF.LoginIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myway(request,response);
	}
	
	public void myway(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String usertype = request.getParameter("usertype");
		
		switch(usertype){
		     case "admin"  : adminLogin(request, response);   break;
		     case "teacher": teacherLogin(request, response); break;
		     case "student": studentLogin(request, response); break;
		     case "check"  : check(request, response);        break;
		     case "logout" : logout(request, response);       break;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);//防止创建Session  
        if(session == null){  
            response.sendRedirect("index.jsp");  
            return;  
        }   
        session.removeAttribute("aid");  
        session.removeAttribute("tid");
        session.removeAttribute("sid");
        response.sendRedirect("index.jsp");  
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aid = (String)request.getSession().getAttribute("aid");
		String sid = (String)request.getSession().getAttribute("sid");
		String tid = (String)request.getSession().getAttribute("tid");
		if(aid!=null)  //判断登录状态的角色
		{
			response.sendRedirect("admin/adminHome.jsp");
		}
		else if(sid!=null)
		{
			response.sendRedirect("student/studentHome.jsp");
		}
		else if(tid!=null)
		{
			response.sendRedirect("teacher/teacherHome.jsp");
		}
		else
			response.sendRedirect("login.jsp");
	}

	private void studentLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Student sd = new Student();
		sd.setSid(request.getParameter("userID"));
		sd.setPwd(request.getParameter("pwd"));
		LoginIF user = DaoFactory.getUserDaoInstance();
		int result = user.studentLogin(sd);
		if(result!=0)
		{
			request.getSession().setAttribute("sid", sd.getSid());
			response.sendRedirect("student/studentHome.jsp");
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('用户名或密码错误!');history.back();</script>"); 
		}
	}

	private void teacherLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Teacher tc = new Teacher();
		tc.setTid(request.getParameter("userID"));
		tc.setPwd(request.getParameter("pwd"));
		LoginIF user = DaoFactory.getUserDaoInstance();
		int result = user.teacherLogin(tc);
		if(result!=0)
		{
			request.getSession().setAttribute("tid", tc.getTid());
			response.sendRedirect("teacher/teacherHome.jsp");
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('用户名或密码错误!');history.back();</script>"); 
		}
	}

	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Admin ad = new Admin();
		ad.setAid(request.getParameter("userID"));
		ad.setPwd(request.getParameter("pwd"));
		LoginIF user = DaoFactory.getUserDaoInstance();
		int result = user.adminLogin(ad);
		if(result!=0)
		{
			request.getSession().setAttribute("aid", ad.getAid());
			response.sendRedirect("admin/adminHome.jsp");
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('用户名或密码错误!');history.back();</script>"); 
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
