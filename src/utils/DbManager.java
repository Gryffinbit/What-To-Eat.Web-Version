package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DbManager {
    private static final String DSName = "java:comp/env/jdbc/what2eat";
    private Connection conn = null;

    public DbManager() {
        try {
            Context sourceCtx = new InitialContext();
            DataSource ds = (DataSource) sourceCtx.lookup(DSName);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnect() {
        return conn;
    }

    public PreparedStatement prepSql(String sql){
        try {
            return conn.prepareStatement(sql);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return  null;
        }
    }
}
