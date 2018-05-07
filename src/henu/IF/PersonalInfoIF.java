package henu.IF;

import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;

public interface PersonalInfoIF {
     public Student getStudentInfo(String sid);
     public Teacher getTeacherInfo(String tid);
     public Admin getAdminInfo(String aid);
     public boolean updateStudentInfo(Student s);
     public boolean updateAdminInfo(Admin a);
     public boolean updateTeacherInfo(Teacher t);
}
