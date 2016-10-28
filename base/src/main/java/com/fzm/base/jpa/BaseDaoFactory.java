package com.fzm.base.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public class BaseDaoFactory<S, ID extends Serializable> extends JpaRepositoryFactory {

	public BaseDaoFactory(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "hiding" })
	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
			RepositoryInformation information, EntityManager entityManager) {
		// TODO Auto-generated method stub
//		return super.getTargetRepository(information, entityManager);
		return new BaseDaoImpl(information.getDomainType(),entityManager);
	}


	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		// TODO Auto-generated method stub
//		return super.getRepositoryBaseClass(metadata);
		return BaseDao.class;
	}


}
