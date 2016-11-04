package com.fzm.core.service;

import java.util.List;
import java.util.Map;

import com.fzm.core.domain.User;
import com.fzm.base.dto.ResultPage;

public interface UserService {
	
	List<User> getList();
	/**
	 * 获取分页信息
	 * @param resultPage
	 * @param conditions
	 */
	ResultPage<User> getPageInfo(Integer pageIndex, Integer pageSize, Map<String,Object> conditions);
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	boolean save(User entity);
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	User get(Long id);
}
