package dao;

import entity.PrivateFoods;

import java.util.List;

public interface PrivateFoodsDao {
    boolean add(PrivateFoods food);
    boolean delete(int fid);
    boolean modify(int fid, PrivateFoods food);
    List<PrivateFoods> getAll();
    PrivateFoods getFoodById(int fid);
}
