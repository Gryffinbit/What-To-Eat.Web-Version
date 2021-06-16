package service;

import entity.PrivateFoods;

import java.util.Dictionary;
import java.util.List;

public interface PrivateFoodsService {
    List<Dictionary> getAllFoods();

    Dictionary getFoodById(int fid);

    boolean deleteFoodById(int fid);

    boolean addFood(PrivateFoods food);

    boolean modifyFood(int fid, PrivateFoods food);

    boolean exist(int fid);
}
