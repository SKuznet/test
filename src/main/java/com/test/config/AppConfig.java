package com.test.config;

import com.test.dao.DocumentDao;
import com.test.dao.DocumentItemDao;
import com.test.dao.impl.DocumentDaoImpl;
import com.test.dao.impl.DocumentItemDaoImpl;
import com.test.model.Document;
import com.test.model.DocumentItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Autowired
    HibernateConfig hibernateConfig;

    @Bean
    @Scope("")
    DocumentDao documentDao() {
        return  new DocumentDaoImpl(Document.class);
    }

    @Bean
    DocumentItemDao documentItemDao(){
        return new DocumentItemDaoImpl(DocumentItem.class);
    }
}
