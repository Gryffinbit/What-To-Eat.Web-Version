package utils;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ConnDb {
    private static final String DSName = "java:comp/env/jdbc/what2eat";
    private Connection conn = null;

    public ConnDb() {
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
}
