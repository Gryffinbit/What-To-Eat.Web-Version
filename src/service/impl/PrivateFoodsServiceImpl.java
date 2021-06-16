package service.impl;

import dao.impl.PrivateFoodsDaoImpl;
import dao.impl.PublicFoodsDaoImpl;
import entity.PrivateFoods;
import entity.PublicFoods;
import service.PrivateFoodsService;

import java.util.*;

public class PrivateFoodsServiceImpl implements PrivateFoodsService {

    @Override
    public List<Dictionary> getAllFoods() {
        PrivateFoodsDaoImpl pbFdDaoImpl = new PrivateFoodsDaoImpl();
        List<Dictionary> retFoods = new ArrayList<Dictionary>();
        List<PrivateFoods> allFoods = pbFdDaoImpl.getAll();

        for (int i = 0; i < allFoods.size(); i++) {
            retFoods.add(allFoods.get(i).toDictionary());
        }
        return retFoods;
    }

    @Override
    public Dictionary getFoodById(int fid) {
        PrivateFoodsDaoImpl pbFdDaoImpl = new PrivateFoodsDaoImpl();
        Dictionary retFood = new Hashtable();
        PrivateFoods tmp = pbFdDaoImpl.getFoodById(fid);
        if (null != tmp) {
            retFood.put("fid", tmp.getFid());
            retFood.put("foodName", tmp.getFoodName());
            retFood.put("area", tmp.getArea());
            retFood.put("minNum", tmp.getMinNum());
            retFood.put("maxNum", tmp.getMaxNum());
            retFood.put("minPrice", tmp.getMinPrice());
            retFood.put("maxPrice", tmp.getMaxPrice());
            retFood.put("owner", tmp.getOwner());
            retFood.put("modifyTime", tmp.getModifyTime());
        }
        return retFood;
    }

    @Override
    public boolean deleteFoodById(int fid) {
        PrivateFoodsDaoImpl pbFdDaoImpl = new PrivateFoodsDaoImpl();
        return pbFdDaoImpl.delete(fid);
    }

    @Override
    public boolean addFood(PrivateFoods food) {
        PrivateFoodsDaoImpl pbFdDaoImpl = new PrivateFoodsDaoImpl();
        return pbFdDaoImpl.add(food);
    }

    @Override
    public boolean modifyFood(int fid, PrivateFoods food) {
        PrivateFoodsDaoImpl pbFdDaoImpl = new PrivateFoodsDaoImpl();
        if (exist(fid))
            return pbFdDaoImpl.modify(fid, food);
        else return false;
    }

    @Override
    public boolean exist(int fid) {
        return 0 != getFoodById(fid).size();
    }

    @Override
    public int generate(String area, int minPrice, int maxPrice, int minNum, int maxNum) {
        List<PrivateFoods> foods = new PrivateFoodsDaoImpl().generate(area, minPrice, maxPrice, minNum, maxNum);
        int rand = new Random().nextInt(foods.size());
        return foods.get(rand).getFid();
    }
}
