package com.example.localmessagetranscation.task;

import com.example.localmessagetranscation.entity.UserMessage;
import com.example.localmessagetranscation.entity.UserMessageExample;
import com.example.localmessagetranscation.mapper.jimisun.UserMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jimisun
 * @create 2022-08-31 12:47
 **/
@Component
public class UserMessageTask {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Scheduled(fixedRate = 3000)
    public void main(){
        //查找操作失败并且重试次数未达到上线的
        UserMessageExample userMessageExample = new UserMessageExample();
        userMessageExample.createCriteria().andResultEqualTo(0)
                .andRetryCountLessThan(5);
        List<UserMessage> userMessages = userMessageMapper.selectByExample(userMessageExample);

        //进行操作业务
        for (UserMessage userMessage : userMessages) {
            if (userMessage.getUserId()==222){
                userMessage.setResult(1);
            }else {
                userMessage.setRetryCount(userMessage.getRetryCount()+1);
            }
            userMessageMapper.updateByPrimaryKey(userMessage);
        }
    }
}
