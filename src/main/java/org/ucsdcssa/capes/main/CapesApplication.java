package org.ucsdcssa.capes.main;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**** imports ****/
//定义Spring Boot扫描包路径
@SpringBootApplication(scanBasePackages = {"org.ucsdcssa.capes"})
//定义实体Bean扫描包路径
@EntityScan(basePackages = "org.ucsdcssa.capes.pojo")
@MapperScan(
        //指定扫描包
        basePackages = "org.ucsdcssa.capes.*",
        //指定SqlSessionFactory，如果sqlSessionTemplate被指定，则作废
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定sqlSessionTemplate，将忽略sqlSessionFactory的配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
        //markerInterface = Class.class,//限定扫描接口，不常用
        annotationClass = Repository.class
)
public class CapesApplication extends WebMvcConfigurerAdapter {
//
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfig() {
//		//定义扫描器实例
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		//加载SqlSessionFactory,Spring Boot会自动生产，SqlSessionFactory实例，无需我们敢于
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		//定义扫描的包
//		mapperScannerConfigurer.setBasePackage("com.springboot.chapter5.*");
//		//限定被标注@Repository的接口才被扫描
//		mapperScannerConfigurer.setAnnotationClass(Repository.class);
//		//通过继承某个接口限制扫描，一般使用不多
//		//mapperScannerConfigurer.setMarkerInterface(。。。。。。);
//		return mapperScannerConfigurer;
//	}

//	@Autowired
//	SqlSessionFactory sqlSessionFactory = null;
//
//    @Bean
//    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
//    	MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//    	bean.setMapperInterface(MyBatisUserDao.class);
//    	bean.setSqlSessionFactory(sqlSessionFactory);
//    	return bean;
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CapesApplication.class, args);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty
        );
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //处理字符串, 避免直接返回字符串的时候被添加了引号
        StringHttpMessageConverter smc = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(smc);

        converters.add(fastConverter);
    }

}