package dao.impl;

import dao.OrderHistoryDao;
import entity.OrderHistory;
import utils.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OrderHistory> getAllRecently() {

        String sql = "select * from OrderHistory where orderTime>?";
        List<OrderHistory> retAll = new ArrayList<OrderHistory>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, new Timestamp(System.currentTimeMillis()-48*60*60*1000));
            ResultSet res = ps.executeQuery();
            while (res.next())
            {
                OrderHistory orderHis = new OrderHistory();
                orderHis.setFid(res.getInt("fid"));
                orderHis.setUid(res.getInt("uid"));
                orderHis.setOrderTime(res.getTimestamp("orderTime"));
                retAll.add(orderHis);
            }
            ps.close();
            db.getConnect().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retAll;
    }
}
