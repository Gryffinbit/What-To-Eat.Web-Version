package service.impl;

import dao.impl.BlackListFoodsDaoImpl;
import service.BlackListFoodsService;

public class BlackListFoodsServiceImpl implements BlackListFoodsService {
    @Override
    public boolean add(int uid, int fid) {
        BlackListFoodsDaoImpl blkListFoodsDaoImpl = new BlackListFoodsDaoImpl();
        if(!blkListFoodsDaoImpl.exist(uid, fid))
            return blkListFoodsDaoImpl.add(uid, fid);
        return false;
    }
}
