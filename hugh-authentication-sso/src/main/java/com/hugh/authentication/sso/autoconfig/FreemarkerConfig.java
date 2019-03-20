package com.hugh.authentication.sso.autoconfig;

import com.hugh.om.menu.template.RemoteTemplateLoader;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class FreemarkerConfig {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private FreeMarkerProperties freeMarkerProperties;

//    @PostConstruct
//    public void freeMarkerConfigurer() {
//        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
//
//        //远程模版加载
//        RemoteTemplateLoader remoteTemplateLoader = new RemoteTemplateLoader("http://127.0.0.1:7070");
//        //本地模版加载
//        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(getClass(), "/templates/");
//
//        MultiTemplateLoader templateLoader = new MultiTemplateLoader(new TemplateLoader[]{remoteTemplateLoader, classTemplateLoader});
//        configuration.setTemplateLoader(templateLoader);
//    }
}
