package xin.utong.configurer;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

import static xin.utong.core.ProjectConstant.MAPPER_INTERFACE_REFERENCE;
import static xin.utong.core.ProjectConstant.MAPPER_PACKAGE;
import static xin.utong.core.ProjectConstant.MODEL_PACKAGE;

/**
 * Mybatis & Mybatis Plus 配置
 * Created by apple on 2017/7/6.
 */
@Configuration
public class MyBatisConfigurer {
    @Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(MODEL_PACKAGE);

        // 分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);

        // 添加插件
        bean.setPlugins(new Interceptor[]{pageInterceptor});

        // 添加xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Configuration
    @AutoConfigureAfter(MyBatisConfigurer.class)
    public static class MyBatisMapperScannerConfigurer {
        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer configurer = new MapperScannerConfigurer();
            configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
            configurer.setBasePackage(MAPPER_PACKAGE);

            // 配置通用mappers
            Properties properties = new Properties();
            properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");
            configurer.setProperties(properties);
            return configurer;
        }
    }
}
