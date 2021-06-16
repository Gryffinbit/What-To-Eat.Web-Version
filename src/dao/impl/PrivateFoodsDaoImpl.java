package dao.impl;

import dao.PrivateFoodsDao;
import entity.PrivateFoods;
import entity.PublicFoods;
import utils.DbManager;
import utils.FoodTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrivateFoodsDaoImpl implements PrivateFoodsDao {
    private DbManager db = null;

    public PrivateFoodsDaoImpl() {
        db = new DbManager();
    }

    @Override
    public boolean add(PrivateFoods food) {
        String sql = "insert into PrivateFoods(`foodName`,`area`,`minNum`,`maxNum`,`minPrice`,`maxPrice`," +
                "`owner`,`modifyTime`) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = db.prepSql(sql);
            if (FoodTools.setPSByPriFood(ps, food)) {
                int res = ps.executeUpdate();
                ps.close();
                db.getConnect().close();
                if (0 != res)
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int fid) {
        String sql = "delete from PrivateFoods where fid=?";
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, fid);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if (res != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modify(int fid, PrivateFoods food) {
        String sql = "update PrivateFoods set `foodName`=?,`area`=?,`minNum`=?,`maxNum`=?,`minPrice`=?," +
                "`maxPrice`=?,`owner`=?,`modifyTime`=? where fid=?";
        try {
            PreparedStatement ps = db.prepSql(sql);
            if (FoodTools.setPSByPriFood(ps, food)) {
                ps.setObject(9, fid);
                int res = ps.executeUpdate();
                ps.close();
                db.getConnect().close();
                if (0 != res)
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PrivateFoods> getAll() {
        String sql = "select * from PrivateFoods";
        List<PrivateFoods> foods = new ArrayList<PrivateFoods>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                PrivateFoods food = FoodTools.createPriFoodByRS(result);
                if (null != food)
                    foods.add(food);
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public PrivateFoods getFoodById(int fid) {
        String sql = "select * from PrivateFoods where fid=?";
        PrivateFoods food = null;
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, fid);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                food = FoodTools.createPriFoodByRS(result);
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
            food = null;
        }
        return food;
    }

    @Override
    public List<PrivateFoods> generate(String area, int minPrice, int maxPrice, int minNum, int maxNum) {
        String sql = "select * from PrivateFoods where area like ? " +
                "and minPrice>=? and maxPrice <=? and minNum>=? and maxNum<=?";
        List<PrivateFoods> foods = new ArrayList<PrivateFoods>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, area);
            ps.setObject(2, minPrice);
            ps.setObject(3, maxPrice);
            ps.setObject(4, minNum);
            ps.setObject(5, maxNum);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                PrivateFoods food = FoodTools.createPriFoodByRS(result);
                if (null != food)
                    foods.add(food);
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }


}
