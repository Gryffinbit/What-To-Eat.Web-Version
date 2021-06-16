package dao;

public interface OrderHistoryDao {
    boolean add(int uid, int fid, boolean isPrivateMenu);
}
