package vn.com.phat.example.config;

import jp.sf.amateras.mirage.SqlManager;
import jp.sf.amateras.mirage.SqlManagerImpl;
import jp.sf.amateras.mirage.dialect.PostgreSQLDialect;
import jp.sf.amateras.mirage.integration.spring.SpringConnectionProvider;
import jp.sf.amateras.mirage.naming.RailsLikeNameConverter;
import jp.sf.amateras.mirage.provider.ConnectionProvider;
import jp.sf.amateras.mirage.provider.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mirage.repository.config.EnableMirageRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableMirageRepositories(basePackages = "vn.com.phat.example.repository")
@EnableTransactionManagement
public class MirageConfig {

    @Value("${spring.datasource.url}")
    private String connectionUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;



    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource(connectionUrl, username, password);
        return dataSource;
    }

    @Bean
    public SqlManager sqlManager(ConnectionProvider connectionProvider) {
        SqlManagerImpl sqlManager = new SqlManagerImpl();
        sqlManager.setConnectionProvider(connectionProvider);
        sqlManager.setDialect(new PostgreSQLDialect());
        sqlManager.setNameConverter(railsLikeNameConverter());
        return sqlManager;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public SpringConnectionProvider connectionProvider(DataSourceTransactionManager transactionManager) {
        SpringConnectionProvider connectionProvider = new SpringConnectionProvider();
        connectionProvider.setTransactionManager(transactionManager);
        return connectionProvider;
    }

//    @Bean
//    public MySQLDialect dialect() {
//        return new MySQLDialect();
//    }

    @Bean
    public RailsLikeNameConverter railsLikeNameConverter() {
        return new RailsLikeNameConverter();
    }
}
