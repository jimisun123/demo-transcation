package com.jimisun.xatranscation.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

/**
 * @author jimisun
 * @create 2022-08-30 13:36
 **/
@Configuration
public class XaConfig {

    @Bean(name = "xaTranscation")
    public JtaTransactionManager atomikosTransactionManager() throws Throwable {
        UserTransaction userTransaction = new UserTransactionImp();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        return new JtaTransactionManager(userTransaction,userTransactionManager);
    }



}
