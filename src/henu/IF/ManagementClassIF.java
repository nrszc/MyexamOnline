package henu.IF;

import java.util.List;

import henu.bean.Classbean;

public interface ManagementClassIF {
		public boolean addclass(Classbean mc);
	    public List<Classbean> findclass();
	    public boolean deleteclass(int classid);
	    public boolean updateclass(Classbean mc);

}
