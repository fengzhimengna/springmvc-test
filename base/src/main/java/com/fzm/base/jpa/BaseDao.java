package com.fzm.base.jpa;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fzm.base.dto.ResultPage;

@NoRepositoryBean
public interface BaseDao<T,ID extends Serializable> extends JpaRepository<T, ID>,
	PagingAndSortingRepository<T, ID>,JpaSpecificationExecutor<T> {

	public ResultPage<T> getPageInfo(Integer pageIndex, Integer pageSize, Map<String,Object> conditions);
}
