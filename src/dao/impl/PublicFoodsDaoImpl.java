package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PublicFoods;
import dao.PublicFoodsDao;
import utils.DbManager;
import utils.FoodTools;

public class PublicFoodsDaoImpl implements PublicFoodsDao {
    private DbManager db = null;

    public PublicFoodsDaoImpl() {
        db = new DbManager();
    }

    @Override
    public boolean add(PublicFoods food) {
        String sql = "insert into PublicFoods(`foodName`,`area`,`minNum`,`maxNum`,`minPrice`,`maxPrice`," +
                "`submitter`,`modifyTime`, `verify`) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = db.prepSql(sql);
            if (FoodTools.setPSByPbFood(ps, food)) {
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
        String sql = "delete from PublicFoods where fid=?";
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
    public boolean modify(int fid, PublicFoods food) {
        String sql = "update PublicFoods set `foodName`=?,`area`=?,`minNum`=?,`maxNum`=?,`minPrice`=?," +
                "`maxPrice`=?,`submitter`=?,`modifyTime`=?,`verify`=? where fid=?";
        try {
            PreparedStatement ps = db.prepSql(sql);
            if (FoodTools.setPSByPbFood(ps, food)) {
                ps.setObject(10, fid);
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
    public PublicFoods getFoodById(int fid) {
        String sql = "select * from PublicFoods where fid=?";
        PublicFoods food = null;
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, fid);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                food = FoodTools.createPbFoodByRS(result);
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
    public boolean doVerify(int fid, boolean verify) {
        String sql = "update PublicFoods set `verify`=? where fid=?";
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, verify);
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
    public List<PublicFoods> generate(int uid, String area, int minPrice, int maxPrice, int minNum, int maxNum) {
        String sql = "select * from PublicFoods where area like ? " +
                "and minPrice>=? and maxPrice <=? and minNum>=? and maxNum<=?";
        List<PublicFoods> foods = new ArrayList<PublicFoods>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, area);
            ps.setObject(2, minPrice);
            ps.setObject(3, maxPrice);
            ps.setObject(4, minNum);
            ps.setObject(5, maxNum);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                PublicFoods food = FoodTools.createPbFoodByRS(result);
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
    public List<PublicFoods> getAll() {
        String sql = "select * from PublicFoods";
        List<PublicFoods> foods = new ArrayList<PublicFoods>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ResultSet result = ps.executeQuery();
            System.out.println(result);
            while (result.next()) {
                PublicFoods food = FoodTools.createPbFoodByRS(result);
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
