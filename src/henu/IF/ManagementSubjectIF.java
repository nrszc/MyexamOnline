package henu.IF;

import java.util.List;

import henu.bean.Subject;

public interface ManagementSubjectIF {

	public boolean addsubject(Subject sub);
    public List<Subject> findsubject();
    public boolean deletesubject(int subjectid);
    public boolean updatesubject(Subject sub);
}
