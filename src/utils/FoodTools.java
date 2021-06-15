package utils;

import entity.PublicFoods;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class FoodTools {
    public static boolean setFoodPrimByReq(PublicFoods food, HttpServletRequest request) {
        try {
            String foodName = request.getParameter("foodName");
            String area = request.getParameter("area");
            int minNum = Integer.parseInt(request.getParameter("minNum"));
            int maxNum = Integer.parseInt(request.getParameter("maxNum"));
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            // int submitter = (int) request.getSession().getAttribute("uid");
            int submitter = 1;
            food.setFoodName(foodName);
            food.setArea(area);
            food.setMinNum(minNum);
            food.setMaxNum(maxNum);
            food.setMinPrice(minPrice);
            food.setMaxPrice(maxPrice);
            food.setSubmitter(submitter);
            food.setModifyTime(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
