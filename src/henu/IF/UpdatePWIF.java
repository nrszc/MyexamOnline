package henu.IF;

import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;

public interface UpdatePWIF {
    public int updatetPW(Teacher tc);
    public int updatesPW(Student sd);
    public int updateaPW(Admin ad);
}
