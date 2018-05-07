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

import henu.IF.ManagementSubjectIF;
import henu.bean.JudgeQuestion;
import henu.bean.Subject;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ManagementSubject
 */
@WebServlet("/ManagementSubject")
public class ManagementSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementSubject() {
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
		      case "addSubject"    : addSubject(request, response);     break;
		      case "deleteSubject" : deleteSubject(request, response);  break;
		      case "updateSubject" : updateSubject(request, response);  break;
		      case "findSubject"   : findSubject(request, response);    break;
		}
	}

	private void findSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ManagementSubjectIF ms = DaoFactory.getManagementSubjectDaoInstance();
		List<Subject> list = ms.findsubject();
		request.getSession().setAttribute("subjectList", list);
		response.sendRedirect("admin/findSubject.jsp"); 
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Subject sub = new Subject();
		sub.setSubjectid(Integer.parseInt(request.getParameter("subjectid")));
		sub.setSubjectname(request.getParameter("subjectname"));
		ManagementSubjectIF ms = DaoFactory.getManagementSubjectDaoInstance();
		boolean result = ms.updatesubject(sub);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Subject> list = ms.findsubject();
		     request.getSession().setAttribute("subjectList", list);
		     out.println("<script>alert('修改成功!');</script>");
            out.println("<script>window.location.href='/MyexamOnline/admin/findSubject.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('修改失败!');history.back();</script>");
		}
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int subjectid= Integer.parseInt(request.getParameter("subjectid"));
		ManagementSubjectIF ms = DaoFactory.getManagementSubjectDaoInstance();
		boolean result = ms.deletesubject(subjectid);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     List<Subject> list = ms.findsubject();
		     request.getSession().setAttribute("subjectList", list);
		     out.println("<script>alert('删除成功!');</script>");
             out.println("<script>window.location.href='/MyexamOnline/admin/findSubject.jsp'</script>");
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	private void addSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Subject sub = new Subject();
		sub.setSubjectname(request.getParameter("subjectname"));
		ManagementSubjectIF ms = DaoFactory.getManagementSubjectDaoInstance();
		boolean result = ms.addsubject(sub);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
            out.println("<script>alert('添加成功!');</script>");
            out.println("<script>window.location.href='/MyexamOnline/admin/addSubject.jsp'</script>");
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
