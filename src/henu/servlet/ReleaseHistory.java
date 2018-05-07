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

import henu.IF.ManagementQuestionIF;
import henu.IF.ReleaseHistoryIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.bean.Paper;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ReleaseHistory
 */
@WebServlet("/ReleaseHistory")
public class ReleaseHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseHistory() {
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
		      case "findpaperHistory" : findpaperHistory(request, response); break;
		      case "watchPaper"       : watchPaper(request, response);       break;
		}
	}

	private void watchPaper(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int paperid = Integer.parseInt(request.getParameter("paperid"));
		ReleaseHistoryIF rh = DaoFactory.getReleaseHistoryDaoInstance();
		List<ChoiceQuestion> choiceQPaper = rh.getChoiceQPaper(paperid);
		request.getSession().setAttribute("choiceQPaper", choiceQPaper);
		List<FillQuestion> fillQPaper = rh.getFillQPaper(paperid);
		request.getSession().setAttribute("fillQPaper", fillQPaper);
		List<JudgeQuestion> judgeQPaper = rh.getJudgeQPaper(paperid);
		request.getSession().setAttribute("judgeQPaper", judgeQPaper);
		request.getSession().setAttribute("paperid", request.getParameter("paperid"));
		response.sendRedirect("teacher/watchPaper.jsp");
	}

	private void findpaperHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = (String)request.getSession().getAttribute("tid");
		ReleaseHistoryIF rh = DaoFactory.getReleaseHistoryDaoInstance();
		List<Paper> list = rh.findpaperHistory(tid);
		request.getSession().setAttribute("paperList", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/releaseHistory.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
