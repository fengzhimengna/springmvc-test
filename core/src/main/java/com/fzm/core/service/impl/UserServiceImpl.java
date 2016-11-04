package com.fzm.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzm.core.dao.UserDao;
import com.fzm.core.domain.User;
import com.fzm.base.dto.ResultPage;
import com.fzm.core.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	public List<User> getList() {
		// TODO Auto-generated method stub
		return this.userDao.findAll();
	}
	@Override
	public ResultPage<User> getPageInfo(Integer pageIndex, Integer pageSize, Map<String,Object> conditions) {
		// TODO Auto-generated method stub
		ResultPage<User> resultPage = this.userDao.getPageInfo(pageIndex, pageSize, conditions); // new ResultPage<User>();
		return resultPage;
	}
	@Override
	public boolean save(User entity) {
		// TODO Auto-generated method stub
		User newEntity = this.userDao.save(entity);
		return newEntity.getId()==null ? false : true;
	}
	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return this.userDao.findOne(id);
	}

}
