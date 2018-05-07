package henu.IF;

import java.util.List;

import henu.bean.ScoreTable;

public interface MyScoreIF {
	public List<ScoreTable> getMyScore(String sid);
}
