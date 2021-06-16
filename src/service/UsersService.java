package service;

import entity.Users;

public interface UsersService {

        boolean register(String userName, String password,String email);

        boolean login(String userName,String email,String password);


}
