package henu.others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import henu.IF.LoadIF;
import henu.bean.ChoiceQuestion;
import henu.bean.DetailStatistics;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.factory.DaoFactory;

public class MyExcel {
	
	 public static boolean exportExcel(String path, int paperid) throws InterruptedException, IOException{
		  HSSFWorkbook hwb = new HSSFWorkbook();//第一步，创建一个workbook（一个excel文件）
		  HSSFSheet hs = hwb.createSheet("信息");//第二步，在workbook中添加一个sheet，对应excel文件中sheet
		  HSSFRow hr = hs.createRow((int)0);//第三部，在sheet中添加表头第0行（相当于解释字段）
		  HSSFCellStyle hcs = hwb.createCellStyle();//第四步，设置第0行（表头）居中
		  hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建居中格式
		  //将表头的字段放入数组当中
		  String [] excelHeader = {"学号","姓名","分数"};
		  for (int i = 0; i < excelHeader.length; i++) {
		   HSSFCell hc = hr.createCell(i);//顺序创建
		   hc.setCellValue(excelHeader[i]);//顺序塞入
		   hc.setCellStyle(hcs);//居中
		   hs.autoSizeColumn(i);//设置 i 这一列为自动调整列宽
		  }
		  
		  HSSFRow row=null;  
		  HSSFCell cell=null;  
	    
          LoadIF c = DaoFactory.getLoadDaoInstance();     
		  List<DetailStatistics> record = c.saveExcel(paperid);

	      int index=1;
	    	  for (DetailStatistics r : record) { 
	    		  if(r==null) return false;
				row=hs.createRow(index);index++;  
			        
			      cell=row.createCell(0);  
			      cell.setCellType(SXSSFCell.CELL_TYPE_STRING);  
			      cell.setCellValue(r.getSid());  
			      System.out.println(r.getSid());
  
			      cell=row.createCell(1);  
			      cell.setCellType(SXSSFCell.CELL_TYPE_STRING);  
			      cell.setCellValue(r.getSname());  
			        
			      cell=row.createCell(2);  
			      cell.setCellType(SXSSFCell.CELL_TYPE_NUMERIC);  
			      cell.setCellValue(r.getScore());  
			        
			  }
		  
		  FileOutputStream fos = new FileOutputStream(path);//先 new 出文件存放的位置
		  hwb.write(fos);//写入
		  fos.close();//关闭资源
		  System.out.println("导出成功！    无任何异常。");
		  return true;
		 }
	
	public boolean InsertChoice(String path, int subjectid) throws IOException{
		InputStream in = new FileInputStream(new File(path));
		Workbook book = getWorkBook(in,path);   //1.获取工作簿  
        List<Sheet> sheets = getSheets(book);   //2.获取所有工作表  
        return ChoiceSheetIterator(sheets, subjectid);    //3.对所有工作表进行操作      
	}
	
	public boolean InsertFill(String path, int subjectid) throws IOException{
		InputStream in = new FileInputStream(new File(path));
		Workbook book = getWorkBook(in,path);   //1.获取工作簿  
        List<Sheet> sheets = getSheets(book);   //2.获取所有工作表  
        return FillSheetIterator(sheets, subjectid);    //3.对所有工作表进行操作      
	}
	
	public boolean InsertJudge(String path, int subjectid) throws IOException{
		InputStream in = new FileInputStream(new File(path));
		Workbook book = getWorkBook(in,path);   //1.获取工作簿  
        List<Sheet> sheets = getSheets(book);   //2.获取所有工作表  
        return JudgeSheetIterator(sheets, subjectid);    //3.对所有工作表进行操作      
	}
	
	 private static boolean ChoiceSheetIterator(List<Sheet> sheets, int subjectid) {  
	        for (int i = 0; i < sheets.size(); i++) {    //循环每一张工作表  
	            Sheet sheet = sheets.get(i);  
	            if (sheet.getLastRowNum() > 1) {    //判断是否为空表，获取有数据的最后一行的行数。如果为零则为空表  
	                System.out.println(sheet.getSheetName() + "=============");     //打印不为空的工作表名字  
	            }  
	            Iterator<Row> iterator = sheet.iterator();   //迭代器  
	            //用两个while循环遍历所有单元格  
	            while (iterator.hasNext()) {           //遍历每一行  
	                Row nextRow = iterator.next();  
	                if (nextRow.getRowNum() < 1) {  
	                    continue;      
	                    //nextRow.getRowNum()就是获取行数，由表中看出第一行(getRowNum()=0)为表头，直接跳过  
	                }  
	                  
	                //从第二行开始是有用的数据，要保存早数据库，第二行：nextRow.getRowNum()=1  
	                Iterator<Cell> cellIterator = nextRow.cellIterator();  
	                ChoiceQuestion CQ = new ChoiceQuestion();  
	                while (cellIterator.hasNext()) {         //遍历每一行的每一列  
	                    Cell cell = cellIterator.next();  
	                    switch(cell.getColumnIndex()){  
	                    case 0:  
	                        //将单元格内容设置为String类型，也可以这样写cell.setCellType(Cell.CELL_TYPE_STRING);  
	                        cell.setCellType(1);    
	                        CQ.setC_question(cell.getStringCellValue());  
	                        break;  
	                    case 1:   //第二列（系）  
	                        cell.setCellType(1);    
	                        CQ.setC_choiceA(cell.getStringCellValue());  
	                        break;  
	                    case 2:   //第三列（课程）  
	                        cell.setCellType(1);  
	                        CQ.setC_choiceB(cell.getStringCellValue());  
	                        break;  
	                    case 3:  
	                        cell.setCellType(1);  
	                        CQ.setC_choiceC(cell.getStringCellValue());  
	                        break;  
	                      
	                    case 4:  
	                        cell.setCellType(1);  
	                        CQ.setC_choiceD(cell.getStringCellValue());  
	                        break;  
	                    case 5:   //第六列是日期，需要进行特殊处理  
	                	    cell.setCellType(1);  
	                        CQ.setC_answer(cell.getStringCellValue());  
	                        break;
	                    }     
	                    System.out.print("   ");  
	                }  
	                boolean result = false;
	                try {  
	                    //到这里已经遍历完一行Execl的所有单元格，并存储到model里面了，现在调用方法保存到数据库  
	                    LoadIF c = DaoFactory.getLoadDaoInstance();     
	                    result =  c.saveCQ(CQ, subjectid);   
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	              if(result==false)
	            	  return false;
	            }  
	        }
	        return true;     
	 }
	 
	 private static boolean FillSheetIterator(List<Sheet> sheets, int subjectid) {  
	        for (int i = 0; i < sheets.size(); i++) {    //循环每一张工作表  
	            Sheet sheet = sheets.get(i);  
	            if (sheet.getLastRowNum() > 1) {    //判断是否为空表，获取有数据的最后一行的行数。如果为零则为空表  
	                System.out.println(sheet.getSheetName() + "=============");     //打印不为空的工作表名字  
	            }  
	            Iterator<Row> iterator = sheet.iterator();   //迭代器  
	            //用两个while循环遍历所有单元格  
	            while (iterator.hasNext()) {           //遍历每一行  
	                Row nextRow = iterator.next();  
	                if (nextRow.getRowNum() < 1) {  
	                    continue;      
	                    //nextRow.getRowNum()就是获取行数，由表中看出第一行(getRowNum()=0)为表头，直接跳过  
	                }  
	                  
	                //从第二行开始是有用的数据，要保存早数据库，第二行：nextRow.getRowNum()=1  
	                Iterator<Cell> cellIterator = nextRow.cellIterator();  
	                FillQuestion FQ = new FillQuestion();  
	                while (cellIterator.hasNext()) {         //遍历每一行的每一列  
	                    Cell cell = cellIterator.next();  
	                    switch(cell.getColumnIndex()){  
	                    case 0:  
	                        //将单元格内容设置为String类型，也可以这样写cell.setCellType(Cell.CELL_TYPE_STRING);  
	                        cell.setCellType(1);    
	                        FQ.setF_question(cell.getStringCellValue());  
	                        break;  
	                    case 1:   //第二列（系）  
	                        cell.setCellType(1);    
	                        FQ.setF_answer(cell.getStringCellValue());  
	                        break; 
	                    }     
	                    System.out.print("   ");  
	                }  
	                boolean result = false;
	                try {  
	                    //到这里已经遍历完一行Execl的所有单元格，并存储到model里面了，现在调用方法保存到数据库  
	                    LoadIF c = DaoFactory.getLoadDaoInstance();     
	                    result =  c.saveFQ(FQ, subjectid);   
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	              if(result==false)
	            	  return false;
	            }  
	        }
	        return true;     
	 }
	
	 private static boolean JudgeSheetIterator(List<Sheet> sheets, int subjectid) {  
	        for (int i = 0; i < sheets.size(); i++) {    //循环每一张工作表  
	            Sheet sheet = sheets.get(i);  
	            if (sheet.getLastRowNum() > 1) {    //判断是否为空表，获取有数据的最后一行的行数。如果为零则为空表  
	                System.out.println(sheet.getSheetName() + "=============");     //打印不为空的工作表名字  
	            }  
	            Iterator<Row> iterator = sheet.iterator();   //迭代器  
	            //用两个while循环遍历所有单元格  
	            while (iterator.hasNext()) {           //遍历每一行  
	                Row nextRow = iterator.next();  
	                if (nextRow.getRowNum() < 1) {  
	                    continue;      
	                    //nextRow.getRowNum()就是获取行数，由表中看出第一行(getRowNum()=0)为表头，直接跳过  
	                }  
	                  
	                //从第二行开始是有用的数据，要保存早数据库，第二行：nextRow.getRowNum()=1  
	                Iterator<Cell> cellIterator = nextRow.cellIterator();  
	                JudgeQuestion JQ = new JudgeQuestion();  
	                while (cellIterator.hasNext()) {         //遍历每一行的每一列  
	                    Cell cell = cellIterator.next();  
	                    switch(cell.getColumnIndex()){  
	                    case 0:  
	                        //将单元格内容设置为String类型，也可以这样写cell.setCellType(Cell.CELL_TYPE_STRING);  
	                        cell.setCellType(1);    
	                        JQ.setJ_question(cell.getStringCellValue());  
	                        break;  
	                    case 1:   //第六列是日期，需要进行特殊处理  
	                	    cell.setCellType(1);  
	                	    JQ.setJ_answer(Integer.parseInt(cell.getStringCellValue()));  
	                        break;
	                    }     
	                    System.out.print("   ");  
	                }  
	                boolean result = false;
	                try {  
	                    //到这里已经遍历完一行Execl的所有单元格，并存储到model里面了，现在调用方法保存到数据库  
	                    LoadIF c = DaoFactory.getLoadDaoInstance();     
	                    result =  c.saveJQ(JQ, subjectid);   
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	              if(result==false)
	            	  return false;
	            }  
	        }
	        return true;     
	 }
	 
	 public static Workbook getWorkBook(InputStream in,String path) throws FileNotFoundException, IOException {  
	        return path.endsWith(".xls") ? (new HSSFWorkbook(in))  
	                : (path.endsWith(".xlsx") ? (new XSSFWorkbook(in)) : (null));  
	    } 
		
		private static List<Sheet> getSheets(Workbook book) {  
	        int numberOfSheets = book.getNumberOfSheets();  
	        System.out.println("numberOfSheets:" + numberOfSheets);  
	        List<Sheet> sheets = new ArrayList<Sheet>();  
	        for (int i = 0; i < numberOfSheets; i++) {  
	            sheets.add(book.getSheetAt(i));  
	        }  
	        return sheets;  
	    }  
}
