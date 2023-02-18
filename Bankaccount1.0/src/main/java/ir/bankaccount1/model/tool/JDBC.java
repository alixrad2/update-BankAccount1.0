package ir.bankaccount1.model.tool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBC {
    private static BasicDataSource basicDataSource= new BasicDataSource();

    public static Connection connection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("java");
        basicDataSource.setPassword("java123");
        return basicDataSource.getConnection();
    }
}
