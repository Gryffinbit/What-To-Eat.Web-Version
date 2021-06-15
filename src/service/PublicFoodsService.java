package service;

import entity.PublicFoods;

import java.util.Dictionary;
import java.util.List;

public interface PublicFoodsService {
    List<Dictionary> getAllVerifiedFoods();

    List<Dictionary> getAllUnverifiedFoods();

    Dictionary getFoodById(int fid);

    boolean deleteFoodById(int fid);

    boolean addFood(PublicFoods food);

    boolean modifyFood(int fid, PublicFoods food);

    boolean doVerify(int fid, boolean verify);

    boolean exist(int fid);
}
