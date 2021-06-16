package dao.impl;

import dao.BlackListFoodsDao;
import utils.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlackListFoodsDaoImpl implements BlackListFoodsDao {
    private DbManager db = null;

    public BlackListFoodsDaoImpl() {
        db = new DbManager();
    }

    @Override
    public boolean add(int uid, int fid) {

        String sql = "insert into BlackListFoods(`uid`,`fid`) values(?,?)";
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, uid);
            ps.setObject(2, fid);
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
    public boolean exist(int uid, int fid) {
        String sql = "select * from BlackListFoods where uid=? and fid=?";
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, uid);
            ps.setObject(2, fid);
            boolean res = ps.executeQuery().next();
            ps.close();
            db.getConnect().close();
            if (res)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
