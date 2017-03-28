package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by genius on 2017/3/14.
 */
@Service("userServiceImpl")
public class UserServiceImpl extends MyServiceImpl{

    @Resource
    private BaseDAO<User> baseDAO;


}
