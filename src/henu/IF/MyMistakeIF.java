package henu.IF;

import java.util.List;

import henu.bean.Errors;
import henu.bean.Mistakes;

public interface MyMistakeIF {
     public List<Errors> getmistakeList(String sid);
     public List<Mistakes> Mistakesc(String sid, int paperid);
     public List<Mistakes> Mistakesf(String sid, int paperid);
     public List<Mistakes> Mistakesj(String sid, int paperid);
}
