package com.keke.config;

import com.keke.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.parse("1024MB"));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1024MB"));
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")){
            String imagesPath = WebConfiguration.class.getClassLoader().getResource("").getPath();
            System.out.print("1.上传配置类imagesPath=="+imagesPath+"\n");
            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/productImg/";
            mImagesPath = imagesPath;
        }
        System.out.print("imagesPath============="+mImagesPath+"\n");
        //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
        registry.addResourceHandler("/productImg/**").addResourceLocations("file:///Users/uu/Documents/MyProjects/upload/");
        System.out.print("2.上传配置类mImagesPath=="+mImagesPath+"\n");
        WebMvcConfigurer.super.addResourceHandlers(registry);
        //registry.addResourceHandler("/courseRes/**").addResourceLocations("file:/Users/uu/Documents/MyProjects/upload/");
    }

    //构造方法
    public WebConfiguration(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/oneOrderDetail");
        excludePath.add("/categories");
        excludePath.add("/category/**");
        excludePath.add("/user/register");  //登录
        excludePath.add("/user/login");     //注册
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
        excludePath.add("/uploadFile");
        excludePath.add("/productImg/**");
        excludePath.add("/unpayedOrders");
        excludePath.add("/orderDetail");
        excludePath.add("/undeliveredOrders");
        excludePath.add("/searchOrder");
        excludePath.add("/products");
        excludePath.add("/product");
        excludePath.add("/abc");
        excludePath.add("/productCategory");
        excludePath.add("/addresses");
        excludePath.add("/orderProductss");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
