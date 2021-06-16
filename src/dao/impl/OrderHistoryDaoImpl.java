package dao.impl;

import dao.OrderHistoryDao;
import utils.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderHistoryDaoImpl implements OrderHistoryDao {
    private DbManager db = null;

    public OrderHistoryDaoImpl() {
        db = new DbManager();
    }

    @Override
    public boolean add(int uid, int fid, boolean isPrivateMenu) {
        String sql = "insert into OrderHistory(uid, fid, orderTime, isPrivateMenu) values(?,?,?,?)";
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, uid);
            ps.setObject(2, fid);
            ps.setObject(3, new Timestamp(System.currentTimeMillis()));
            ps.setObject(4, isPrivateMenu);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if (0 != res)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
