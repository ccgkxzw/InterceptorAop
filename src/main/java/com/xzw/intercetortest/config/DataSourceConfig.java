package com.xzw.intercetortest.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.xzw.intercetortest.mapper", sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig{
//    public DataSourceConfig(BasewebrfmExtendMybatisPlusProperties properties,
//                                      ObjectProvider<Interceptor[]> interceptorsProvider,
//                                      ResourceLoader resourceLoader,
//                                      ObjectProvider<DatabaseIdProvider> databaseIdProvider,
//                                      ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
//                                      ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
//                                      ApplicationContext applicationContext) {
//        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider, applicationContext);
//    }


    /**
     * 从库
     */
    @Bean(name = "slaveDb")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource slaveDb() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 主从动态配置
     */
    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("slaveDb") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setPlugins(new Interceptor[]{new DynamicDSInterceptor()});
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
//    @Bean(name = "dataSourceTx")
//    public DataSourceTransactionManager dataSourceTx(@Qualifier("slaveDb") DataSource dynamicDataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dynamicDataSource);
//        return dataSourceTransactionManager;
//    }





}
