package henu.IF;

import java.util.List;

import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.bean.Paper;

public interface ReleaseHistoryIF {
     public List<Paper> findpaperHistory(String tid);
     public List<ChoiceQuestion> getChoiceQPaper(int paperid);
     public List<FillQuestion> getFillQPaper(int paperid);
     public List<JudgeQuestion> getJudgeQPaper(int paperid);
}
