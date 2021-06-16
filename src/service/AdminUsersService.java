package service;

import entity.Users;

import java.util.Dictionary;
import java.util.List;

public interface AdminUsersService {
    List<Dictionary> getAllUsers();
    Dictionary getUserById(int uid);
    boolean modifyUsers(int uid, Users user);
    boolean pwdModify(int uid, Users user);
    boolean addUsers(Users user);
    boolean deleteUsers(int uid);
    boolean adminLogin(String userName,String email,String password);
    boolean exist(int uid);
}
