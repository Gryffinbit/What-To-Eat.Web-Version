package service.impl;

import dao.UsersDao;
import dao.impl.UsersDaoImpl;
import entity.Users;
import service.UsersService;
import utils.SHA256;

public class UsersServiceImpl implements UsersService {

    @Override
    public boolean register(String userName, String password, String email) {
        boolean register=false;
        UsersDaoImpl userDao = new UsersDaoImpl();
        if (userDao.getUserByName(userName)==null && userDao.getUserByEmail(email)==null) {
            Users user = new Users();
            user.setUserName(userName);
            user.setPassword(new SHA256(password).toString());
            user.setEmail(email);
            register = userDao.add(user);//如果没有就去注册
        }
        //然后把返回值返回给servlet
        return register;
    }

    @Override
    public boolean login(String userName,String email,String password) {

        UsersDaoImpl userDao = new UsersDaoImpl();
        Users user = null;
        user = userDao.getUserByName(userName) != null ? userDao.getUserByName(userName) : userDao.getUserByEmail(email);
        System.out.println(user.getPassword());
        if (user != null) {
            return new SHA256(password).toString().equals(user.getPassword());
        }
        return false;
    }
}
