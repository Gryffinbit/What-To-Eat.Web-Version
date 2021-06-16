package service.impl;

import dao.impl.PrivateFoodsDaoImpl;
import dao.impl.UsersDaoImpl;
import entity.PrivateFoods;
import entity.Users;
import service.AdminUsersService;
import utils.SHA256;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class AdminUsersServiceImpl implements AdminUsersService {
    @Override
    public List<Dictionary> getAllUsers() {
        UsersDaoImpl getAllUser = new UsersDaoImpl();
        List<Users> allUsers = getAllUser.getAll();
        List<Dictionary> users = new ArrayList<Dictionary>();
        for (int i = 0; i < allUsers.size(); i++) {
            users.add(allUsers.get(i).toDictionary());
        }
        return users;
    }

    @Override
    public Dictionary getUserById(int uid) {
        UsersDaoImpl getUser = new UsersDaoImpl();

        Dictionary users = new Hashtable();
        Users tmp = getUser.getUserById(uid);
        if (null != tmp) {
            users.put("uid", tmp.getUid());
            users.put("userName",tmp.getUserName());
            users.put("email",tmp.getEmail());
            users.put("regTime",tmp.getRegTime());
            users.put("isAdmin",tmp.isAdmin());
        }
        return users;
    }

    @Override
    public boolean modifyUsers(int uid, Users user) {
        UsersDaoImpl modUsers = new UsersDaoImpl();
        if(exist(uid))
            return modUsers.userModify(uid,user);
        return false;
    }

    @Override
    public boolean pwdModify(int uid, Users user) {
        UsersDaoImpl modPwd = new UsersDaoImpl();
        if(exist(uid))
            return modPwd.pwdModify(uid,user);
        return false;
    }


    @Override
    public boolean addUsers(Users user) {
        UsersDaoImpl addUser = new UsersDaoImpl();
        return addUser.add(user);
    }

    @Override
    public boolean deleteUsers(int uid) {
        UsersDaoImpl delUser = new UsersDaoImpl();
        return delUser.delete(uid);
    }

    @Override
    public boolean adminLogin(String userName, String email, String password) {
        UsersDaoImpl userDao = new UsersDaoImpl();

        Users user = null;

        user = userDao.getUserByName(userName) != null ? userDao.getUserByName(userName) : userDao.getUserByEmail(email);

        if (user != null) {
            if(new SHA256(password).toString()== user.getPassword()&& user.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exist(int uid) {
        return 0 != getUserById(uid).size();
    }
}
