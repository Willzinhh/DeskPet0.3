//package br.cspi.config;
//
//
//import br.cspi.security.AutorizadorInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public WebMvcConfigurer mvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new AutorizadorInterceptor());
//            }
//        };
//    }
//}
