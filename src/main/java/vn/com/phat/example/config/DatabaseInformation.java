package vn.com.phat.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

@Component
@DependsOn("dataSource")
@RequiredArgsConstructor
@Slf4j
public class DatabaseInformation {

    private final DataSource dataSource;

    @PostConstruct
    void showDataBaseInformation() {

        try {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            log.info("Database Product Name: {}", metaData.getDatabaseProductName());
            log.info("Database Product Version: {}", metaData.getDatabaseProductVersion());
            log.info("Database Driver Name: {}", metaData.getDriverName());
            log.info("Database Driver Version: {}", metaData.getDriverVersion());
            log.info("Database URL: {}", metaData.getURL());
            log.info("Database User: {}", metaData.getUserName());
        } catch (Exception e) {
            log.error("Error retrieving database information", e);
        }
    }

}
