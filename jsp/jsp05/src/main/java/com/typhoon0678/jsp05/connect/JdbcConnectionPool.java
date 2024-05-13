package com.typhoon0678.jsp05.connect;

import jakarta.servlet.ServletContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnectionPool {
    
    private final Connection connection;
    private final PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public JdbcConnectionPool(String sql, String[] values) {
        try {
            Context initContext = new InitialContext();
            Context context = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("dbcp_oracle");
            
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }

            System.out.println("dbcp_oracle connection created");
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet() throws SQLException {
        this.resultSet = this.preparedStatement.executeQuery();
    }

}
