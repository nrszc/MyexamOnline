package henu.IF;

import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;

public interface LoginIF {
      public int studentLogin(Student std);
      public int adminLogin(Admin ad);
      public int teacherLogin(Teacher tc);
}
