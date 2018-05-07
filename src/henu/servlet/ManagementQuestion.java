package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.ManagementQuestionIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/ManagementQuestion")
public class ManagementQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myway(request,response);
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method){
		      case "addchoiceQuestion"   : addchoiceQuestion(request, response);    break;
		      case "addfillQuestion"     : addfillQuestion(request, response);      break;
		      case "addjudgeQuestion"    : addjudgeQuestion(request, response);     break;
		      case "findchoiceQuestion"  : findchoiceQuestion(request, response);   break; 
		      case "deletechoiceQuestion": deletechoiceQuestion(request, response); break;
		      case "modifychoiceQuestion": modifychoiceQuestion(request, response); break;
		      case "findfillQuestion"    : findfillQuestion(request, response);     break;
		      case "deletefillQuestion"  : deletefillQuestion(request, response);   break;
		      case "modifyfillQuestion"  : modifyfillQuestion(request, response);   break;
		      case "findjudgeQuestion"   : findjudgeQuestion(request, response);    break;
		      case "deletejudgeQuestion" : deletejudgeQuestion(request, response);  break;
		      case "modifyjudgeQuestion" : modifyjudgeQuestion(request, response);  break;
		}
		
	}

	private void modifyjudgeQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int j_id = Integer.parseInt(request.getParameter("j_id"));
		String judgeQuestion = request.getParameter("judgeQuestion");
		int judgeAnswer = Integer.parseInt(request.getParameter("judgeAnswer"));
		JudgeQuestion jq = new JudgeQuestion();
		jq.setJ_id(j_id);
		jq.setJ_question(judgeQuestion);
		jq.setJ_answer(judgeAnswer);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.modifyjudgeQuestion(jq);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('修改成功!');</script>");
		else
			out.println("<script>alert('修改失败!');</script>");
        findjudgeQuestion(request, response);
	}

	private void deletejudgeQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String j_id = request.getParameter("j_id");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.deletejudgeQuestion(j_id);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('删除成功!');</script>"); 
		else
			out.println("<script>alert('删除失败!');</script>"); 
		findjudgeQuestion(request, response);
	}

	private void findjudgeQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subjectid = request.getParameter("subjectid");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		List<JudgeQuestion> list = tc.findjudgeQuestion(subjectid);
		request.getSession().setAttribute("judgeList", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/findJudgeQuestion.jsp'</script>");
	}

	private void modifyfillQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int f_id = Integer.parseInt(request.getParameter("f_id"));
		String fillQuestion = request.getParameter("fillQuestion");
		String fillAnswer = request.getParameter("fillAnswer");
		FillQuestion fq = new FillQuestion();
		fq.setF_id(f_id);
		fq.setF_question(fillQuestion);
		fq.setF_answer(fillAnswer);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.modifyfillQuestion(fq);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('修改成功!');</script>");
		else
			out.println("<script>alert('修改失败!');</script>");
        findfillQuestion(request, response);
	}

	private void deletefillQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String f_id = request.getParameter("f_id");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.deletefillQuestion(f_id);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('删除成功!');</script>"); 
		else
			out.println("<script>alert('删除失败!');</script>"); 
        findfillQuestion(request, response);
	}

	private void findfillQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subjectid = request.getParameter("subjectid");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		List<FillQuestion> list = tc.findfillQuestion(subjectid);
		request.getSession().setAttribute("fillList", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/findFillQuestion.jsp'</script>");
	}

	private void modifychoiceQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String choiceQuestion = request.getParameter("choiceQuestion");
		String choiceA = request.getParameter("choiceA");
		String choiceB = request.getParameter("choiceB");
		String choiceC = request.getParameter("choiceC");
		String choiceD = request.getParameter("choiceD");
		String choiceAnswer = request.getParameter("choiceAnswer");
		ChoiceQuestion cq = new ChoiceQuestion();
		cq.setC_id(c_id);
		cq.setC_question(choiceQuestion);
		cq.setC_choiceA(choiceA);
		cq.setC_choiceB(choiceB);
		cq.setC_choiceC(choiceC);
		cq.setC_choiceD(choiceD);
		cq.setC_answer(choiceAnswer);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.modifychoiceQuestion(cq);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('修改成功!');</script>");
		else
			out.println("<script>alert('修改失败!');</script>");
        findchoiceQuestion(request, response);
	}

	private void deletechoiceQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String c_id = request.getParameter("c_id");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.deletechoiceQuestion(c_id);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
            out.println("<script>alert('删除成功!');</script>"); 
		else
			out.println("<script>alert('删除失败!');</script>"); 
        findchoiceQuestion(request, response);
	}

	private void findchoiceQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subjectid = request.getParameter("subjectid");
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		List<ChoiceQuestion> list = tc.findchoiceQuestion(subjectid);
		request.getSession().setAttribute("choiceList", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/MyexamOnline/teacher/findChoiceQuestion.jsp'</script>");
		//response.sendRedirect("teacher/findChoiceQuestion.jsp");
	}


	private void addjudgeQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String judgeQuestion = request.getParameter("judgeQuestion");
		String judgeAnswer = request.getParameter("judgeAnswer");
		int subjectId = Integer.parseInt(request.getParameter("judgeST"));
		int ja = 0;
		if(judgeAnswer.equals("True"))
			ja = 1;
		JudgeQuestion jq = new JudgeQuestion();
		jq.setJ_question(judgeQuestion);
		jq.setJ_answer(ja);
		jq.setJ_subjectid(subjectId);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.addjudgeQuestion(jq);
		if(result)
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('添加成功!');history.back();</script>"); 
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	private void addfillQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fillQuestion = request.getParameter("fillQuestion");
		String fillAnswer = request.getParameter("fillAnswer");
		int subjectId = Integer.parseInt(request.getParameter("fillST"));
		FillQuestion fq = new FillQuestion();
		fq.setF_question(fillQuestion);
		fq.setF_answer(fillAnswer);
		fq.setF_subjectid(subjectId);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.addfillQuestion(fq);
		if(result)
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('添加成功!');history.back();</script>"); 
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	private void addchoiceQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String choiceQuestion = request.getParameter("choiceQuestion");
		String choiceA = request.getParameter("choiceA");
		String choiceB = request.getParameter("choiceB");
		String choiceC = request.getParameter("choiceC");
		String choiceD = request.getParameter("choiceD");
		String choiceAnswer = request.getParameter("choiceAnswer");
		int subjectId = Integer.parseInt(request.getParameter("choiceST"));
		ChoiceQuestion cq = new ChoiceQuestion();
		cq.setC_question(choiceQuestion);
		cq.setC_choiceA(choiceA);
		cq.setC_choiceB(choiceB);
		cq.setC_choiceC(choiceC);
		cq.setC_choiceD(choiceD);
		cq.setC_answer(choiceAnswer);
		cq.setC_subjectid(subjectId);
		ManagementQuestionIF tc = DaoFactory.getTeacherDaoInstance();
		boolean result = tc.addchoiceQuestion(cq);
		if(result)
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('添加成功!');history.back();</script>"); 
		}
		else
		{
			PrintWriter   out   =   response.getWriter(); 
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
