package com.typhoon0678.jspmodel2replyboard.connect;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnectionPool {

    public Connection conn;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public JdbcConnectionPool() {

        try {
            Context initContext = new InitialContext();
            Context context = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("dbcp_oracle");

            conn = dataSource.getConnection();
            System.out.println("dbConnectionPool connected");

        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();

            System.out.println("jdbc disconnect");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
