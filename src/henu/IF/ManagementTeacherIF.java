package henu.IF;

import java.util.List;

import henu.bean.Teacher;

public interface ManagementTeacherIF {
	public boolean addteacher(Teacher tc);
    public List<Teacher> findteacher();
    public boolean deleteteacher(String tid);
    public boolean updateteacher(Teacher tc);
}
