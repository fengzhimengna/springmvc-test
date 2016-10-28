package com.fzm.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzm.core.dao.UserDao;
import com.fzm.core.domain.User;
import com.fzm.core.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	public List<User> getList() {
		// TODO Auto-generated method stub
		return this.userDao.findAll();
	}

}
