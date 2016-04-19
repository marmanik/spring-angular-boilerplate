package com.springapp.mvc;

import com.springapp.mvc.config.AppConfig;
import com.springapp.mvc.config.DatabaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.*;
import java.util.EnumSet;

@Configuration
@EnableWebMvc
public class WebXmlInitializer extends WebMvcConfigurerAdapter implements WebApplicationInitializer {


        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("index");
        }

        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }


        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            AnnotationConfigWebApplicationContext context = getContext();
            context.setServletContext(servletContext);
            context.refresh();
            context.start();
            //dispatcher servlet
            ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
            servletContext.addListener(new ContextLoaderListener(context));
        }

        private AnnotationConfigWebApplicationContext getContext() {
            AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.register(AppConfig.class);
            return context;
        }


}
