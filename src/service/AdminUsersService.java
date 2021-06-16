package service;

import entity.Users;

import java.util.Dictionary;
import java.util.List;

public interface AdminUsersService {
    List<Dictionary> getAllUsers();
    Dictionary getUserById(int uid);
    boolean getUsers(int uid);
    boolean modifyUsers(int uid, Users user);
    boolean addUsers(Users user);
    boolean deleteUsers(int uid);

}
