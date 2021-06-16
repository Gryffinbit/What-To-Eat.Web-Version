package dao;

import entity.Users;

import java.util.List;

public interface UsersDao {
    boolean add(Users user);

    boolean delete(int uid);

    boolean adminModify(int uid, Users user);

    boolean userModify(int uid, Users user);

    boolean pwdModify(int uid, Users user);

    List<Users> getAll();

    Users getUserById(int uid);

    Users getUserByName(String userName);

    Users getUserByEmail(String email);

}