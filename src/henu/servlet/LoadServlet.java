package henu.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import henu.IF.LoadIF;
import henu.bean.ChoiceQuestion;
import henu.factory.DaoFactory;
import henu.others.MyExcel;

/**
 * Servlet implementation class LoadServlet
 */
@WebServlet("/LoadServlet")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletConfig servletconfig; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config)throws ServletException{
    	this.servletconfig = config;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			myway(request, response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		System.out.println(method);
		switch(method)
		{
		      case "upload"   : upLoad(request, response);     break;
		      case "download" : downLoad(request, response);   break;
		}
	}


	private void downLoad(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException, ServletException {
		int paperid = Integer.parseInt(request.getParameter("paperid"));
		MyExcel me = new MyExcel();
		String path = "D:/test/grade.xls";
		boolean result = me.exportExcel(path, paperid);
		if(result) {
			SmartUpload su = new SmartUpload();
			su.initialize(servletconfig, request, response);
			su.setContentDisposition(null);
			try{
				su.downloadFile(path);
			}catch(SmartUploadException e)
			{
				e.printStackTrace();
			}
			java.io.File file1 = new java.io.File(path);
			file1.delete();
		}
		else{
			PrintWriter   out   =   response.getWriter();
			out.println("<script>alert('导出失败');history.back();</script>"); 
		}
	}

	private void upLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SmartUpload su = new SmartUpload();
		try{
			su.initialize(servletconfig, request, response);
		}catch(ServletException e)
		{
			e.printStackTrace();
		}
		su.setAllowedFilesList("xls,xlsx");
		su.setMaxFileSize(10*1024*1024);
		su.setTotalMaxFileSize(12*1024*1024);
		try{
			su.upload();
		}catch(ServletException e1)
		{
			e1.printStackTrace();
		}catch(IOException e1)
		{
			e1.printStackTrace();
		}catch(SmartUploadException e1)
		{
			e1.printStackTrace();
		}
		Request req = su.getRequest();
		Files files = su.getFiles();
		File file = files.getFile(0);
		String extFile = file.getFileExt();
		Date curDate = new Date();
		long d = curDate.getTime();
		String mainFile = String.valueOf(d);
		String filename = "D:/test/" + mainFile + "." + extFile;
		try{
			file.saveAs(filename);
		}catch(IOException e1)
		{
			e1.printStackTrace();
		}
		catch(SmartUploadException e1){
			e1.printStackTrace();
		}
		int subjectid = Integer.parseInt(req.getParameter("subjectid"));
		System.out.println(subjectid+"ggggggggggg");
		MyExcel me = new MyExcel();
		boolean result = false;
		String questiontype = req.getParameter("questiontype");
		switch(questiontype)
		{
		      case "选择题"  : result = me.InsertChoice(filename, subjectid);  break;
		      case "填空题"  : result = me.InsertFill(filename, subjectid);   break;
		      case "判断题"  : result = me.InsertJudge(filename, subjectid);   break;
		}
		java.io.File file1 = new java.io.File(filename);
		file1.delete();
		PrintWriter   out   =   response.getWriter();
		if(result) {
            out.println("<script>alert('导入成功!');</script>"); 
    		out.println("<script>window.location.href='/MyexamOnline/teacher/addQuestion.jsp'</script>");
		}
		else
			out.println("<script>alert('导入失败，请确保文件内容格式一致!');history.back();</script>"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
