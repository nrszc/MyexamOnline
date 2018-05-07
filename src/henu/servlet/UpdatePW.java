package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.UpdatePWIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/UpdatePW")
public class UpdatePW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePW() {
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
		switch(method){
		     case "updateAPW"  : updateAPW(request, response);   break;
		     case "updateTPW"  : updateTPW(request, response);   break;
		     case "updateSPW"  : updateSPW(request, response);   break;
		}
		
	}

	private void updateSPW(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sid = (String)request.getSession().getAttribute("sid");
		String old_pwd = request.getParameter("oldcode");
		String new_pwd = request.getParameter("newcode1");
		String new_pwd1 = request.getParameter("newcode2");
		UpdatePWIF upw = DaoFactory.getUpdatePWDaoInstance(); 
		PrintWriter   out   =   response.getWriter(); 
		int result = 0;
		if(new_pwd.equals(new_pwd1))
		{ 
			Student sd = new Student();
			sd.setSid(sid);
			sd.setPwd(old_pwd);
			sd.setNewpwd(new_pwd1);
			result = upw.updatesPW(sd);
			if(result==0)
			{
				out.println("<script>alert('原密码错误，请重新输入确认!');history.back();</script>");
			}
			else if(result==1)
			{
				out.println("<script>alert('修改失败!');history.back();</script>");
			}
			else
			{
				out.println("<script>alert('修改成功!');</script>");
				out.println("<script>window.location.href='/MyexamOnline/student/updateSPW.jsp'</script>");
			}	
		}
		else   
		{
			out.println("<script>alert('两次输入密码不一致!');history.back();</script>"); 
		}
	}

	private void updateTPW(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = (String)request.getSession().getAttribute("tid");
		String old_pwd = request.getParameter("oldcode");
		String new_pwd = request.getParameter("newcode1");
		String new_pwd1 = request.getParameter("newcode2");
		UpdatePWIF upw = DaoFactory.getUpdatePWDaoInstance(); 
		PrintWriter   out   =   response.getWriter(); 
		int result = 0;
		if(new_pwd.equals(new_pwd1))
		{ 
			Teacher tc = new Teacher();
			tc.setTid(tid);
			tc.setPwd(old_pwd);
			tc.setNewpwd(new_pwd1);
			result = upw.updatetPW(tc);
			if(result==0)
			{
				out.println("<script>alert('原密码错误，请重新输入确认!');history.back();</script>");
			}
			else if(result==1)
			{
				out.println("<script>alert('修改失败!');history.back();</script>");
			}
			else
			{
				out.println("<script>alert('修改成功!');</script>");
				out.println("<script>window.location.href='/MyexamOnline/teacher/updateTPW.jsp'</script>");
			}	
		}
		else   
		{
			out.println("<script>alert('两次输入密码不一致!');history.back();</script>"); 
		}
	}

	private void updateAPW(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aid = (String)request.getSession().getAttribute("aid");
		String old_pwd = request.getParameter("oldcode");
		String new_pwd = request.getParameter("newcode1");
		String new_pwd1 = request.getParameter("newcode2");
		UpdatePWIF upw = DaoFactory.getUpdatePWDaoInstance(); 
		PrintWriter   out   =   response.getWriter(); 
		int result = 0;
		if(new_pwd.equals(new_pwd1))
		{ 
			Admin ad = new Admin();
			ad.setAid(aid);
			ad.setPwd(old_pwd);
			ad.setNewpwd(new_pwd1);
			result = upw.updateaPW(ad);
			if(result==0)
			{
				out.println("<script>alert('原密码错误，请重新输入确认!');history.back();</script>");
			}
			else if(result==1)
			{
				out.println("<script>alert('修改失败!');history.back();</script>");
			}
			else
			{
				out.println("<script>alert('修改成功!');</script>");
				out.println("<script>window.location.href='/MyexamOnline/admin/updateAPW.jsp'</script>");
			}	
		}
		else   
		{
			out.println("<script>alert('两次输入密码不一致!');history.back();</script>"); 
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
