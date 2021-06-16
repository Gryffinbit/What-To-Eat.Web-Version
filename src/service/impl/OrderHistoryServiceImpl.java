package service.impl;

import dao.impl.OrderHistoryDaoImpl;
import dao.impl.PrivateFoodsDaoImpl;
import dao.impl.PublicFoodsDaoImpl;
import service.OrderHistoryService;

public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Override
    public boolean add(int uid, int fid, boolean isPrivateMenu) {
        if (isPrivateMenu) {
            if (null == (new PrivateFoodsDaoImpl().getFoodById(fid)))
                return false;
        } else if (null == (new PublicFoodsDaoImpl().getFoodById(fid)))
            return false;
        return new OrderHistoryDaoImpl().add(uid, fid, isPrivateMenu);
    }
}
