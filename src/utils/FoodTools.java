package utils;

import entity.PrivateFoods;
import entity.PublicFoods;
import service.impl.PrivateFoodsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FoodTools {
    public static boolean setPbFoodPrimByReq(PublicFoods food, HttpServletRequest request) {
        try {
            String foodName = request.getParameter("foodName");
            String area = request.getParameter("area");
            int minNum = Integer.parseInt(request.getParameter("minNum"));
            int maxNum = Integer.parseInt(request.getParameter("maxNum"));
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            int uid = (int) request.getSession().getAttribute("uid");
            food.setFoodName(foodName);
            food.setArea(area);
            food.setMinNum(minNum);
            food.setMaxNum(maxNum);
            food.setMinPrice(minPrice);
            food.setMaxPrice(maxPrice);
            food.setSubmitter(uid);
            food.setModifyTime(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setPriFoodPrimByReq(PrivateFoods food, HttpServletRequest request){
        try {
            String foodName = request.getParameter("foodName");
            String area = request.getParameter("area");
            int minNum = Integer.parseInt(request.getParameter("minNum"));
            int maxNum = Integer.parseInt(request.getParameter("maxNum"));
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            int uid = (int) request.getSession().getAttribute("uid");
            food.setFoodName(foodName);
            food.setArea(area);
            food.setMinNum(minNum);
            food.setMaxNum(maxNum);
            food.setMinPrice(minPrice);
            food.setMaxPrice(maxPrice);
            food.setOwner(uid);
            food.setModifyTime(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean setPSByPbFood(PreparedStatement ps, PublicFoods food) {
        try {
            ps.setObject(1, food.getFoodName());
            ps.setObject(2, food.getArea());
            ps.setObject(3, food.getMinNum());
            ps.setObject(4, food.getMaxNum());
            ps.setObject(5, food.getMinPrice());
            ps.setObject(6, food.getMaxPrice());
            ps.setObject(7, food.getSubmitter());
            ps.setObject(8, food.getModifyTime());
            ps.setObject(9, food.isVerify());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setPSByPriFood(PreparedStatement ps, PrivateFoods food) {
        try {
            ps.setObject(1, food.getFoodName());
            ps.setObject(2, food.getArea());
            ps.setObject(3, food.getMinNum());
            ps.setObject(4, food.getMaxNum());
            ps.setObject(5, food.getMinPrice());
            ps.setObject(6, food.getMaxPrice());
            ps.setObject(7, food.getOwner());
            ps.setObject(8, food.getModifyTime());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static PublicFoods createPbFoodByRS(ResultSet rs) {
        PublicFoods food = new PublicFoods();
        try {
            food.setFid(rs.getInt("fid"));
            food.setFoodName(rs.getString("foodName"));
            food.setArea(rs.getString("area"));
            food.setMinNum(rs.getInt("minNum"));
            food.setMaxNum(rs.getInt("maxNum"));
            food.setMinPrice(rs.getInt("minPrice"));
            food.setMaxPrice(rs.getInt("maxPrice"));
            food.setSubmitter(rs.getInt("submitter"));
            food.setVerify(rs.getBoolean("verify"));
            food.setModifyTime(rs.getTimestamp("modifyTime"));
        } catch (SQLException e) {
            e.printStackTrace();
            food = null;
        }
        return food;
    }

    public static PrivateFoods createPriFoodByRS(ResultSet rs) {
        PrivateFoods food = new PrivateFoods();
        try {
            food.setFid(rs.getInt("fid"));
            food.setFoodName(rs.getString("foodName"));
            food.setArea(rs.getString("area"));
            food.setMinNum(rs.getInt("minNum"));
            food.setMaxNum(rs.getInt("maxNum"));
            food.setMinPrice(rs.getInt("minPrice"));
            food.setMaxPrice(rs.getInt("maxPrice"));
            food.setOwner(rs.getInt("owner"));
            food.setModifyTime(rs.getTimestamp("modifyTime"));
        } catch (SQLException e) {
            e.printStackTrace();
            food = null;
        }
        return food;
    }


}
