package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.MakePaperIF;
import henu.bean.Paper;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class MakePaper
 */
@WebServlet("/MakePaper")
public class MakePaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePaper() {
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
		makePaper(request, response);
	}

	private void makePaper(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = (String)request.getSession().getAttribute("tid");
		int classid = Integer.parseInt(request.getParameter("classPaper"));
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String papername = request.getParameter("papername");
		System.out.println(endtime);
		int subjectid = Integer.parseInt(request.getParameter("subjectid"));
		Paper p = new Paper();
		p.setTid(tid);
		p.setClassid(classid);
		p.setStarttime(starttime);
		p.setEndtime(endtime);
		p.setSubjectid(subjectid);
		p.setPapername(papername);
		MakePaperIF mp = DaoFactory.getMakePaperDaoInstance();
		boolean result = mp.makePaper(p);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('发布成功!');</script>"); 
		else
			out.println("<script>alert('发布失败，可能该学科题库不足!');</script>"); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/makePaper.jsp'</script>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
