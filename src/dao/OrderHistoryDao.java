package dao;

import entity.OrderHistory;

import java.util.List;

public interface OrderHistoryDao {
    boolean add(int uid, int fid, boolean isPrivateMenu);

    List<OrderHistory> getAllRecently();
}
