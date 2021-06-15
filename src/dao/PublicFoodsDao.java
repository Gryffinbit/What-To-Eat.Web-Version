package dao;

import entity.PublicFoods;
import java.util.List;

public interface PublicFoodsDao {
    boolean add(PublicFoods food);
    boolean delete(int fid);
    boolean modify(int fid, PublicFoods food);
    List<PublicFoods> getAll();
    PublicFoods getFoodById(int fid);
    boolean doVerify(int fid, boolean verify);
}
