package com.example.tcctranscation.service;

import com.example.tcctranscation.entity.User;
import com.example.tcctranscation.mapper.jimisun.JimisunUserMapper;
import com.example.tcctranscation.mapper.jimisun2.Jimisun2UserMapper;
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

    @Transactional(transactionManager = "jimisunTransactionManager")
    public void testXaTranscation(){
        User user = new User();
        user.setId("8888");
        user.setName("8888");
        user.setPassword("8888");
        userMapper.insert(user);
        try {
            userMapper2.insert(user);
            int i = 1/0;
        }catch (RuntimeException runtimeException){
            userMapper2.deleteByPrimaryKey("8888");
            throw runtimeException;
        }

    }
}
