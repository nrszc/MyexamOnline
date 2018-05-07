package henu.IF;

import java.util.List;

import henu.bean.Student;


public interface ManagementStudentIF {
	public boolean addstudent(Student sd);
    public List<Student> findstudent();
    public boolean deletestudent(String sid);
    public boolean updatestudent(Student sd);
}
