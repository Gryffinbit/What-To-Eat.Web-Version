package dao.impl;

import dao.UsersDao;
import entity.PublicFoods;
import entity.Users;
import utils.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private DbManager db = null;

    public UsersDaoImpl(){
        db = new DbManager();
    }
    @Override
    public boolean add(Users user) {
        String sql = "insert into Users(`userName`,`password`,`email`,`regTime`,`isAdmin`)values(?,?,?,?,?)";
        try{
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1,user.getUserName());
            ps.setObject(2,user.getPassword());
            ps.setObject(3,user.getEmail());
            ps.setObject(4,user.getRegTime());
            ps.setObject(5,user.isAdmin());
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if(0 != res)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int uid) {
        String sql = "delete from Users where uid=?";
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1,uid);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if(0 != res)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean adminModify(int uid, Users user) {
        String sql = "Update Users set `userName`=?,`password`=?,`email`=?,`isAdmin`=? where uid=?";
        try{
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1,user.getUserName());
            ps.setObject(2,user.getPassword());
            ps.setObject(3,user.getEmail());
            ps.setObject(4,user.isAdmin());
            ps.setObject(5,uid);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if (0 != res)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean userModify(int uid, Users user) {
        String sql = "Update Users set `userName`=?,`email`=? where uid=?";
        try{
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1,user.getUserName());
            ps.setObject(2,user.getEmail());
            ps.setObject(3,uid);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if (0 != res)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pwdModify(int uid, Users user) {
        String sql = "Update Users set `password`=? where uid=?";
        try{
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1,user.getPassword());
            ps.setObject(2,uid);
            int res = ps.executeUpdate();
            ps.close();
            db.getConnect().close();
            if (0 != res)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Users> getAll() {
        String sql = "select * from Users";
        List<Users> users = new ArrayList<Users>();   //用来存放查询到的单个user，组成一个users表
        try{
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ResultSet result = ps.executeQuery();
            System.out.println(result);
            while (result.next()){
                Users user = new Users();
                user.setUid(result.getInt("uid"));
                user.setUserName(result.getString("userName"));
                user.setEmail(result.getString("email"));
                user.setRegTime(result.getTimestamp("regTime"));
                user.setAdmin(result.getBoolean("isAdmin"));
                users.add(user);
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public Users getUserById(int uid) {
        String sql = "select * from Users where uid=?";
        Users user = null;
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, uid);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new Users();
                user.setUid(result.getInt("uid"));
                user.setUserName(result.getString("userName"));
                user.setEmail(result.getString("email"));
                user.setRegTime(result.getTimestamp("regTime"));
                user.setAdmin(result.getBoolean("isAdmin"));
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            user = null;
        }
        return user;
    }

    @Override
    public Users getUserByName(String userName) {
        String sql = "select * from Users where userName=?";
        Users user = null;
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, userName);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new Users();
                user.setUid(result.getInt("uid"));
                user.setUserName(result.getString("userName"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setAdmin(result.getBoolean("isAdmin"));
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            user = null;
        }
        return user;
    }

    @Override
    public Users getUserByEmail(String email) {
        String sql = "select * from Users where email=?";
        Users user = null;
        try {
            if(!db.getConnect().isValid(1)) db = new DbManager();
            PreparedStatement ps = db.prepSql(sql);
            ps.setObject(1, email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new Users();
                user.setUid(result.getInt("uid"));
                user.setUserName(result.getString("userName"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setAdmin(result.getBoolean("isAdmin"));
            }
            ps.close();
            db.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            user = null;
        }
        return user;
    }
}
