package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.Effortssubtype;

public class EffortsSubTypeDAO extends BaseDAO<Effortssubtype> {
	private static Logger logger = Logger.getLogger(EffortsSubTypeDAO.class);
	
	private static final String EFFORTS_SUB_TYPE_ID="effortsSubTypeId";
	
private EntityManager em;
	
	
	
	public EffortsSubTypeDAO(EntityManager em) {
		super(Effortssubtype.class);
		this.em = em;
	}



	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	public Effortssubtype getEffortssubtypeById(Long effortsubtypeId) {
		logger.debug("Getting the Efforts sub type of:" + effortsubtypeId);
		Query query = getEntityManager().createNamedQuery(
				Effortssubtype.GET_EFFORTSSUBTYPE_BY_ID, Effortssubtype.class);
		query.setParameter(EFFORTS_SUB_TYPE_ID, effortsubtypeId);
		Effortssubtype effortsInfo = (Effortssubtype) query.getSingleResult();

		return effortsInfo;
	}

	public List<Effortssubtype> getAllEffortssubtype() {
		logger.debug("Getting all the efforts sub type");

		Query query = getEntityManager().createNamedQuery(
				Effortssubtype.GET_ALL_EFFORTSSUBTYPE, Effortssubtype.class);

		List<Effortssubtype> list = (List<Effortssubtype>) query.getResultList();

		return list;

	}

}
