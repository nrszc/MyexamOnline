package henu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.MyMistakeIF;
import henu.bean.Errors;
import henu.bean.Mistakes;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class MyMistake
 */
@WebServlet("/MyMistake")
public class MyMistake extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMistake() {
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
		     case "mistakeList"  : mistakeList(request, response);   break;
		     case "detailMistake": detailMistake(request, response); break;
		}
	}
	private void detailMistake(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int paperid = Integer.parseInt(request.getParameter("paperid"));
		String  sid =(String)request.getSession().getAttribute("sid");
		MyMistakeIF s = DaoFactory.getMyMistakeDaoInstance();
		List<Mistakes> list = s.Mistakesc(sid,paperid);
		List<Mistakes> list1 = s.Mistakesf(sid,paperid);
		List<Mistakes> list2 = s.Mistakesj(sid,paperid);
		request.getSession().setAttribute("choice", list);
		request.getSession().setAttribute("fill", list1);
		request.getSession().setAttribute("judge", list2);
		request.getSession().setAttribute("paperid", request.getParameter("paperid"));
		response.sendRedirect("student/detailMistake.jsp");	
		
	}

	private void mistakeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String  sid =(String)request.getSession().getAttribute("sid");
		MyMistakeIF s = DaoFactory.getMyMistakeDaoInstance();
		List<Errors> list = s.getmistakeList(sid);
		
		request.getSession().setAttribute("mistakeList", list);
		response.sendRedirect("student/mistakeList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
