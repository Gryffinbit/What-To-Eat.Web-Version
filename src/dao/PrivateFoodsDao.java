package dao;

import entity.PrivateFoods;
import entity.PublicFoods;

import java.util.List;

public interface PrivateFoodsDao {
    boolean add(PrivateFoods food);
    boolean delete(int fid);
    boolean modify(int fid, PrivateFoods food);
    List<PrivateFoods> getAll();
    PrivateFoods getFoodById(int fid);
    List<PrivateFoods> generate(String area, int minPrice, int maxPrice, int minNum, int maxNum);

}
