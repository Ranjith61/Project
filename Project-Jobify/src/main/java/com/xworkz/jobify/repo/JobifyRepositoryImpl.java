package com.xworkz.jobify.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.xworkz.jobify.dto.JobifyEntity;

@Repository
public class JobifyRepositoryImpl implements JobifyRepository {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	@Transactional
	public boolean save(JobifyEntity dto) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			dto = em.merge(dto);
			em.persist(dto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}

		return false;
	}

	@Override
	public List<JobifyEntity> readAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JobifyEntity> query = em.createNamedQuery("findAllJobifyEntities", JobifyEntity.class);
		return query.getResultList();

	}

	@Override
	public JobifyEntity findByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JobifyEntity> query = em.createNamedQuery("findEmail", JobifyEntity.class);
		query.setParameter("em", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;

		}
	}

}
