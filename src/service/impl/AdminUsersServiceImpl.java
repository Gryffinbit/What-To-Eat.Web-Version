package service.impl;

import entity.Users;
import service.AdminUsersService;

import java.util.Dictionary;
import java.util.List;

public class AdminUsersServiceImpl implements AdminUsersService {
    @Override
    public List<Dictionary> getAllUsers() {
        return null;
    }

    @Override
    public Dictionary getUserById(int uid) {
        return null;
    }

    @Override
    public boolean getUsers(int uid) {
        return false;
    }

    @Override
    public boolean modifyUsers(int uid, Users user) {
        return false;
    }

    @Override
    public boolean addUsers(Users user) {
        return false;
    }

    @Override
    public boolean deleteUsers(int uid) {
        return false;
    }
}
