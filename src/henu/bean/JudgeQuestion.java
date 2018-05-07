package henu.bean;

public class JudgeQuestion {
	private int j_id;
	private String j_question;
	private int j_answer;
	private int j_subjectid;
	private String j_subjectname;
	public String getJ_subjectname() {
		return j_subjectname;
	}
	public void setJ_subjectname(String j_subjectname) {
		this.j_subjectname = j_subjectname;
	}
	public int getJ_id() {
		return j_id;
	}
	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}
	public String getJ_question() {
		return j_question;
	}
	public void setJ_question(String j_question) {
		this.j_question = j_question;
	}
	public int getJ_answer() {
		return j_answer;
	}
	public void setJ_answer(int j_answer) {
		this.j_answer = j_answer;
	}
	public int getJ_subjectid() {
		return j_subjectid;
	}
	public void setJ_subjectid(int j_subjectid) {
		this.j_subjectid = j_subjectid;
	}

}
