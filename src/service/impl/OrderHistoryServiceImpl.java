package service.impl;

import dao.impl.OrderHistoryDaoImpl;
import dao.impl.PrivateFoodsDaoImpl;
import dao.impl.PublicFoodsDaoImpl;
import entity.OrderHistory;
import service.OrderHistoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public int recommend(int uid) {
        int ret = -1;
        List<OrderHistory> allOrder = new OrderHistoryDaoImpl().getAllRecently();
        List<Integer> indexList = new ArrayList<Integer>();
        List<Integer> numList = new ArrayList<Integer>();
        if (0 != allOrder.size()) {
            for (OrderHistory order : allOrder) {
                if (indexList.contains(order.getFid())) {
                    int index = indexList.indexOf(order.getFid());
                    numList.set(index,numList.get(index)+1);
                }else{
                    indexList.add(order.getFid());
                    numList.add(1);
                }
            }
            ret = indexList.get(numList.indexOf(Collections.max(numList)));
            System.out.println(indexList.toString());
            System.out.println(numList.toString());
        }else {
            ret = new PublicFoodsServiceImpl().generate(uid, "%", 0, 100, 0, 100);
        }
        return ret;
    }
}
