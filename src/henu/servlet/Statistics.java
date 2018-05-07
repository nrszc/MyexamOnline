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

import henu.IF.ReleaseHistoryIF;
import henu.IF.StatisticsIF;
import henu.bean.DetailStatistics;
import henu.bean.Paper;
import henu.bean.StatisticsTable;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/Statistics")
public class Statistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistics() {
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
		      case "findStatistics"   : findStatistics(request, response);   break;
		      case "detailStatistics" : detailStatistics(request, response); break;
		}
	}

	private void detailStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int paperid = Integer.parseInt(request.getParameter("paperid"));
		StatisticsIF st = DaoFactory.getStatisticsDaoInstance();
		List<DetailStatistics> list = st.getdetailStatistics(paperid);
		request.getSession().setAttribute("detailStatistics", list);
		request.getSession().setAttribute("paperid",request.getParameter("paperid"));
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/detailStatistics.jsp'</script>");
	}

	private void findStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = (String)request.getSession().getAttribute("tid");
		StatisticsIF st = DaoFactory.getStatisticsDaoInstance();
		List<StatisticsTable> list = st.getStatistics(tid);
		request.getSession().setAttribute("Statistics", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/Statistics.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
