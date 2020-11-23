package com.example.EAS.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @PackageName:pamier.manage.AuthoritySystem.interceptor
 * @ClassName:InterceptorConfig
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/5/13 10:49
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    
    @Autowired
    AuthTokenInterceptor authTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authTokenInterceptor)
                //选择过滤哪些接口
                .addPathPatterns("/EAS/**")
                //选择忽略的接口
                .excludePathPatterns("/EAS/login/ByNameAndDept")
//                .excludePathPatterns("/EAS/login/getLoginData")
                .excludePathPatterns("/EAS/baseData/acceptHandle")
//                .excludePathPatterns("/EAS/login/getPersonIdentity")
//                .excludePathPatterns("/EAS/baseData/getChangeAuditList")
                .excludePathPatterns("/EAS/baseData/downLoadAttachment");
//                .excludePathPatterns("/EAS/**");
//                .excludePathPatterns("/EAS/baseData/addContractBill");
        super.addInterceptors(registry);
    }

}
