package com.typhoon0678.jsp05.connect;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import java.sql.*;

public class JdbcConnect {

    private final Connection connection;
    private final PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public JdbcConnect(ServletContext servletContext, String sql, String[] values) {

        String driver = servletContext.getInitParameter("OracleDriver");
        String url = servletContext.getInitParameter("OracleUrl");
        String user = servletContext.getInitParameter("OracleUser");
        String password = servletContext.getInitParameter("OraclePassword");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("db 연결");
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

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet() throws SQLException {
        this.resultSet = this.preparedStatement.executeQuery();
    }
}
