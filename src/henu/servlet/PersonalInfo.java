package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.PersonalInfoIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class PersonalInfo
 */
@WebServlet("/PersonalInfo")
public class PersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInfo() {
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
			String method = request.getParameter("method");
			
			switch(method){
			     case "findStudentInfo"   : findStudentInfo(request, response);   break;
			     case "findTeacherInfo"   : findTeacherInfo(request, response);   break;
			     case "findAdminInfo"     : findAdminInfo(request, response);     break;
			     case "updateStudentInfo" : updateStudentInfo(request, response); break;
			     case "updateTeacherInfo" : updateTeacherInfo(request, response); break;
			     case "updateAdminInfo"   : updateAdminInfo(request, response);   break;
			}
		}
	private void updateAdminInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Admin stu = new Admin();
		stu.setAid((String)request.getSession().getAttribute("aid"));
		stu.setPhone(request.getParameter("phone"));
		stu.setSex(request.getParameter("sex"));
		stu.setAddress(request.getParameter("address"));
		stu.setBirthdate(request.getParameter("birthdate"));
		stu.setEmail(request.getParameter("email"));
		PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
        boolean result = pi.updateAdminInfo(stu);
        PrintWriter   out   =   response.getWriter(); 
        if(result)
        {
            out.println("<script>alert('修改成功!');</script>"); 
            findAdminInfo(request, response);
        }
        else
        {
            out.println("<script>alert('修改失败!');history.back();</script>"); 
        }
		}

	private void updateTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Teacher stu = new Teacher();
		stu.setTid((String)request.getSession().getAttribute("tid"));
		stu.setPhone(request.getParameter("phone"));
		stu.setSex(request.getParameter("sex"));
		stu.setAddress(request.getParameter("address"));
		stu.setBirthdate(request.getParameter("birthdate"));
		stu.setEmail(request.getParameter("email"));
		PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
        boolean result = pi.updateTeacherInfo(stu);
        PrintWriter   out   =   response.getWriter(); 
        if(result)
        {
            out.println("<script>alert('修改成功!');</script>"); 
            findTeacherInfo(request, response);
        }
        else
        {
            out.println("<script>alert('修改失败!');history.back();</script>"); 
        }
		}

	private void updateStudentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Student stu = new Student();
		stu.setSid((String)request.getSession().getAttribute("sid"));
		stu.setPhone(request.getParameter("phone"));
		stu.setSex(request.getParameter("sex"));
		stu.setAddress(request.getParameter("address"));
		stu.setBirthdate(request.getParameter("birthdate"));
		stu.setEmail(request.getParameter("email"));
		PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
        boolean result = pi.updateStudentInfo(stu);
        PrintWriter   out   =   response.getWriter(); 
        if(result)
        {
            out.println("<script>alert('修改成功!');</script>"); 
            findStudentInfo(request, response);
        }
        else
        {
            out.println("<script>alert('修改失败!');history.back();</script>"); 
        }
		}

	private void findAdminInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aid = (String)request.getSession().getAttribute("aid");
		PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
		Admin a = pi.getAdminInfo(aid);
		request.getSession().setAttribute("adminInfo", a);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/admin/adminInfo.jsp'</script>");
		}

	private void findTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = (String)request.getSession().getAttribute("tid");
		PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
		Teacher t = pi.getTeacherInfo(tid);
		request.getSession().setAttribute("teacherInfo", t);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/teacherInfo.jsp'</script>");
		}

	private void findStudentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String sid = (String)request.getSession().getAttribute("sid");
			PersonalInfoIF pi = DaoFactory.getPersonalInfoDaoInstance();
			Student s = pi.getStudentInfo(sid);
			request.getSession().setAttribute("studentInfo", s);
			PrintWriter   out   =   response.getWriter(); 
			out.println("<script>window.location.href='/MyexamOnline/student/studentInfo.jsp'</script>");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
