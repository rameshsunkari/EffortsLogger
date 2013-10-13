package com.efforts.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.efforts.model.Effortssubtype;
import com.efforts.model.Effortstype;

public class EffortsTypeDAO extends BaseDAO<Effortstype> {
	private static final String EFFORTS_TYPE_ID = "effortsTypeId";
	private static Logger logger = Logger.getLogger(EffortsTypeDAO.class);
	private EntityManager em;

	public EffortsTypeDAO(EntityManager em) {
		super(Effortstype.class);
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	public Effortstype getEffortstypeById(Long effortstypeId) {
		logger.debug("Getting the Efforts type of:" + effortstypeId);
		Query query = getEntityManager().createNamedQuery(
				Effortstype.GET_EFFORTSTYPE_BY_ID, Effortssubtype.class);
		query.setParameter(EFFORTS_TYPE_ID, effortstypeId);
		Effortstype effortsInfo = (Effortstype) query.getSingleResult();

		return effortsInfo;
	}

	public List<Effortstype> getAllEffortstype() {
		logger.debug("Getting all the efforts type");

		Query query = getEntityManager().createNamedQuery(
				Effortstype.GET_ALL_EFFORTSTYPE, Effortstype.class);

		List<Effortstype> list = (List<Effortstype>) query.getResultList();

		return list;

	}

}
