package com.szwg.dynamicdatasource.template;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceGen {
    @Autowired
    private Configuration configuration;
}
