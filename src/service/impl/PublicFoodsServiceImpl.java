package service.impl;

import dao.impl.PublicFoodsDaoImpl;
import entity.PublicFoods;
import service.PublicFoodsService;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class PublicFoodsServiceImpl implements PublicFoodsService {

    @Override
    public List<Dictionary> getAllVerifiedFoods() {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        List<Dictionary> retFoods = new ArrayList<Dictionary>();
        List<PublicFoods> allFoods = pbFdDaoImpl.getAll();

        for (int i = 0; i < allFoods.size(); i++) {
            if(allFoods.get(i).isVerify()){
                retFoods.add(allFoods.get(i).toDictionary());
            }
        }
        return retFoods;
    }

    @Override
    public List<Dictionary> getAllUnverifiedFoods() {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        List<Dictionary> retFoods = new ArrayList<Dictionary>();
        List<PublicFoods> allFoods = pbFdDaoImpl.getAll();

        for (int i = 0; i < allFoods.size(); i++) {
            if(!allFoods.get(i).isVerify()){
                retFoods.add(allFoods.get(i).toDictionary());
            }
        }
        return retFoods;
    }

    @Override
    public Dictionary getFoodById(int fid) {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        Dictionary retFood = new Hashtable();
        PublicFoods tmp = pbFdDaoImpl.getFoodById(fid);
        if (null != tmp) {
            retFood.put("fid", tmp.getFid());
            retFood.put("foodName", tmp.getFoodName());
            retFood.put("area", tmp.getArea());
            retFood.put("minNum", tmp.getMinNum());
            retFood.put("maxNum", tmp.getMaxNum());
            retFood.put("minPrice", tmp.getMinPrice());
            retFood.put("maxPrice", tmp.getMaxPrice());
            retFood.put("submitter", tmp.getSubmitter());
            retFood.put("verify", tmp.isVerify());
            retFood.put("modifyTime", tmp.getModifyTime());
        }
        return retFood;
    }

    @Override
    public boolean deleteFoodById(int fid) {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        return pbFdDaoImpl.delete(fid);
    }

    @Override
    public boolean addFood(PublicFoods food) {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        return pbFdDaoImpl.add(food);
    }

    @Override
    public boolean modifyFood(int fid, PublicFoods food) {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        if (exist(fid))
            return pbFdDaoImpl.modify(fid, food);
        else return false;
    }

    @Override
    public boolean doVerify(int fid, boolean verify) {
        PublicFoodsDaoImpl pbFdDaoImpl = new PublicFoodsDaoImpl();
        if(exist(fid))
            return pbFdDaoImpl.doVerify(fid, verify);
        return false;
    }

    @Override
    public boolean exist(int fid) {
        return 0 != getFoodById(fid).size();
    }
}
