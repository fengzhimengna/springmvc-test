package com.fzm.core.dao;

import org.springframework.stereotype.Repository;

import com.fzm.base.jpa.BaseDao;
import com.fzm.core.domain.User;

@Repository
public interface UserDao extends BaseDao<User,Long>{

}
