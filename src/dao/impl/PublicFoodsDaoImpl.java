package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PublicFoods;
import dao.PublicFoodsDao;
import utils.DbManager;

public class PublicFoodsDaoImpl implements PublicFoodsDao {
    private DbManager db = null;

    public PublicFoodsDaoImpl(){
        db = new DbManager();
    }
    @Override
    public boolean add(PublicFoods food) {
        String sql = "insert into PublicFoods(`foodName`,`area`,`minNum`,`maxNum`,`minPrice`,`maxPrice`," +
                "`submitter`,`verify`,`modifyTime`) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, food.getFoodName());
            ps.setObject(2, food.getArea());
            ps.setObject(3, food.getMinNum());
            ps.setObject(4, food.getMaxNum());
            ps.setObject(5, food.getMinPrice());
            ps.setObject(6, food.getMaxPrice());
            ps.setObject(7, food.getSubmitter());
            ps.setObject(8, food.isVerify());
            ps.setObject(9, food.getModifyTime());
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
                "`maxPrice`=?,`submitter`=?,`verify`=?,`modifyTime`=? where fid=?";
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, food.getFoodName());
            ps.setObject(2, food.getArea());
            ps.setObject(3, food.getMinNum());
            ps.setObject(4, food.getMaxNum());
            ps.setObject(5, food.getMinPrice());
            ps.setObject(6, food.getMaxPrice());
            ps.setObject(7, food.getSubmitter());
            ps.setObject(8, food.isVerify());
            ps.setObject(9, food.getModifyTime());
            ps.setObject(10, fid);
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
    public PublicFoods getFoodById(int fid) {
        String sql = "select * from PublicFoods where fid=?";
        PublicFoods food = null;
        try {
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, fid);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                food = new PublicFoods();
                food.setFid(result.getInt("fid"));
                food.setFoodName(result.getString("foodName"));
                food.setArea(result.getString("area"));
                food.setMinNum(result.getInt("minNum"));
                food.setMaxNum(result.getInt("maxNum"));
                food.setMinPrice(result.getInt("minPrice"));
                food.setMaxPrice(result.getInt("maxPrice"));
                food.setSubmitter(result.getInt("submitter"));
                food.setVerify(result.getBoolean("verify"));
                food.setModifyTime(result.getTimestamp("modifyTime"));
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
    public List<PublicFoods> getAll() {
        String sql = "select * from PublicFoods";
        List<PublicFoods> foods = new ArrayList<PublicFoods>();
        try {
            PreparedStatement ps = db.prepSql(sql);
            ResultSet result = ps.executeQuery();
            System.out.println(result);
            while (result.next()) {
                PublicFoods food = new PublicFoods();
                food.setFid(result.getInt("fid"));
                food.setFoodName(result.getString("foodName"));
                food.setArea(result.getString("area"));
                food.setMinNum(result.getInt("minNum"));
                food.setMaxNum(result.getInt("maxNum"));
                food.setMinPrice(result.getInt("minPrice"));
                food.setMaxPrice(result.getInt("maxPrice"));
                food.setSubmitter(result.getInt("submitter"));
                food.setVerify(result.getBoolean("verify"));
                food.setModifyTime(result.getTimestamp("modifyTime"));
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
