package com.efforts.service.action;

import java.util.List;

import javax.ejb.Local;

import com.efforts.model.EffortsInfo;
import com.efforts.model.Effortssubtype;
import com.efforts.model.Effortstype;

@Local
public interface EffortsServiceBeanLocal {

	public List<Effortstype> getAllEffortstype();

	public List<Effortssubtype> getAllEffortssubtype();

	public Effortstype getEffortTypeById(Long Id);

	public Effortssubtype getEffortssubtypeById(Long Id);

	public List<EffortsInfo> getAllEfforts();

	public EffortsInfo getEffortsInfoById(Long Id);

	public void addEffortsInfo(EffortsInfo efforts);

	public void addEffortsSubType(Effortssubtype subtype);

	public void addEffortsType(Effortstype type);

	public void updateEffortsInfo(EffortsInfo effortsInfo);

	public void updateEffortsSubType(Effortssubtype subtype);

	public void updateEffortsType(Effortstype type);

	public List<EffortsInfo> getAllEmpEfforts(Long empId);

	public List<EffortsInfo> getEmpOfManagerEfforts(Long mgrId);
	
	public List<EffortsInfo> getAllEmpEfforts(Long empId, boolean fullList,
			int maxResults, int firstResult) ;

}
