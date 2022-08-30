package com.jimisun.xatranscation.service;

import com.jimisun.xatranscation.entity.User;
import com.jimisun.xatranscation.mapper.jimisun.JimisunUserMapper;
import com.jimisun.xatranscation.mapper.jimisun2.Jimisun2UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author jimisun
 * @create 2022-08-30 13:54
 **/
@Service
public class UserService {

    @Resource
    private JimisunUserMapper userMapper;

    @Resource
    private Jimisun2UserMapper userMapper2;

    @Transactional
    public void testXaTranscation(){
        User user = new User();
        user.setId("4444");
        user.setName("4444");
        user.setPassword("4444");
        userMapper.insert(user);
        int i = 1/0;
        userMapper2.insert(user);
    }
}
