package com.example.localmessagetranscation.service;

import com.example.localmessagetranscation.entity.User;
import com.example.localmessagetranscation.entity.UserMessage;
import com.example.localmessagetranscation.mapper.jimisun.JimisunUserMapper;
import com.example.localmessagetranscation.mapper.jimisun.UserMessageMapper;
import com.example.localmessagetranscation.mapper.jimisun2.Jimisun2UserMapper;
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


    @Resource
    private UserMessageMapper userMessageMapper;

    @Transactional(transactionManager = "jimisunTransactionManager")
    public void testXaTranscation(){
        //保存消息
        User user = new User();
        user.setId("222");
        user.setName("222");
        user.setPassword("222");
        userMapper.insert(user);


        //保存本地消息表
        UserMessage userMessage = new UserMessage();
        userMessage.setUserId(Integer.valueOf(user.getId()));
        userMessage.setResult(0);
        userMessage.setRetryCount(1);
        userMessageMapper.insert(userMessage);

    }
}
