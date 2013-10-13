package com.test.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.efforts.model.UserInfo;

public class TestJPA2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EffortsLoggerJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Query for Select all user from table users
		javax.persistence.Query q = em.createQuery("SELECT u FROM UserInfo u");
		List<UserInfo> userlist = ((javax.persistence.Query) q).getResultList();
		if (userlist.size() == 0) {
			System.out.println("Empty List");
		} else {
			for (UserInfo u : userlist) {
				System.out.println(u.toString());
			}
		}
		
//		UserInfo ui = new UserInfo("2", "hyd", "India", "rams4u87@gmail.com",
//				"ram", "s", new BigDecimal(1322425), "ram", "", new BigDecimal(1322425), "aP", "2", "");
//		
//		em.persist(ui);
//		em.flush();
		
		em.getTransaction().commit();
		
		

	}

}
