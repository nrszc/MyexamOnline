package henu.IF;

import java.util.List;

import henu.bean.DetailStatistics;
import henu.bean.StatisticsTable;

public interface StatisticsIF {
    public List<StatisticsTable> getStatistics(String tid);
    public List<DetailStatistics> getdetailStatistics(int paperid);
}
