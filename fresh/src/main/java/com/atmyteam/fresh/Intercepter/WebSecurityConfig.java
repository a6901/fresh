package com.atmyteam.fresh.Intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {

    @Bean
    public UserIntercepter getSessionInterceptor() {
        return new UserIntercepter();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // 所有已/开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login,regist,loginOut接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        // 必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("/exit")
                .addPathPatterns("/updatauser")
                .addPathPatterns("/insertorder")
                .addPathPatterns("/getorder")
                .addPathPatterns("/getshopcart")
                .addPathPatterns("/insertshopcart");
//                .excludePathPatterns("/login");

        super.addInterceptors(registry);
    }
}
