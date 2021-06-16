package dao;

public interface BlackListFoodsDao {
    public boolean add(int uid, int fid);

    public boolean exist(int uid, int fid);
}
