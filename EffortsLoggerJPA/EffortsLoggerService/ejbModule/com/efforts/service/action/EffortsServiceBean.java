package com.efforts.service.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.efforts.dao.EffortsInfoDAO;
import com.efforts.dao.EffortsSubTypeDAO;
import com.efforts.dao.EffortsTypeDAO;
import com.efforts.model.EffortsInfo;
import com.efforts.model.Effortssubtype;
import com.efforts.model.Effortstype;

/**
 * Session Bean implementation class EffortsServiceBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EffortsServiceBean implements EffortsServiceBeanLocal {

	@PersistenceContext(unitName = "EffortsLoggerJPA")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public EffortsServiceBean() {

	}

	@Override
	public List<Effortstype> getAllEffortstype() {

		return getEffortsTypeDAO().getAllEffortstype();
	}

	@Override
	public List<Effortssubtype> getAllEffortssubtype() {

		return getEffortsSubTypeDAO().getAllEffortssubtype();
	}

	@Override
	public Effortstype getEffortTypeById(Long Id) {

		return getEffortsTypeDAO().getEffortstypeById(Id);
	}

	@Override
	public Effortssubtype getEffortssubtypeById(Long Id) {

		return getEffortsSubTypeDAO().getEffortssubtypeById(Id);
	}

	@Override
	public List<EffortsInfo> getAllEfforts() {

		return getEffortsInfoDAO().getAllEfforts();
	}

	@Override
	public EffortsInfo getEffortsInfoById(Long Id) {

		return getEffortsInfoDAO().getEffortsInfoById(Id);
	}

	private EffortsInfoDAO getEffortsInfoDAO() {
		return new EffortsInfoDAO(em);
	}

	private EffortsSubTypeDAO getEffortsSubTypeDAO() {
		return new EffortsSubTypeDAO(em);
	}

	private EffortsTypeDAO getEffortsTypeDAO() {
		return new EffortsTypeDAO(em);
	}

	@Override
	public void addEffortsInfo(EffortsInfo efforts) {
		getEffortsInfoDAO().create(efforts);
	}

	@Override
	public void addEffortsSubType(Effortssubtype subtype) {

		getEffortsSubTypeDAO().create(subtype);
	}

	@Override
	public void addEffortsType(Effortstype type) {

		getEffortsTypeDAO().create(type);
	}

	@Override
	public List<EffortsInfo> getAllEmpEfforts(Long empId) {

		return getEffortsInfoDAO().getAllEmpEfforts(empId);
	}

	@Override
	public List<EffortsInfo> getEmpOfManagerEfforts(Long mgrId) {

		return getEffortsInfoDAO().getEmpOfManagerEfforts(mgrId);
	}

	@Override
	public void updateEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfoDAO().edit(effortsInfo);

	}

	@Override
	public void updateEffortsSubType(Effortssubtype subtype) {
		getEffortsSubTypeDAO().edit(subtype);

	}

	@Override
	public void updateEffortsType(Effortstype type) {
		getEffortsTypeDAO().edit(type);

	}
	
	@Override
	public List<EffortsInfo> getAllEmpEfforts(Long empId, boolean fullList,
			int maxResults, int firstResult) {
		return getEffortsInfoDAO().getAllEmpEfforts(empId, fullList, maxResults, firstResult);
	}

}
