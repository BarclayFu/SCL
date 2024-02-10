package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;
    //Get all user data
    @Test
    public void getAll() {
        List<User> users= userMapper.selectList(null);
        System.out.println(users);
    }
    //添加add
    @Test
    public void addUser(){
        User user = new User();
        user.setName("Amy");
        user.setAge(35);
        user.setEmail("Amy@gmail.com");

        int insert = userMapper.insert(user);
        System.out.println("insert" + insert);
    }
    @Test
    public void updatedUser(){
        User user = new User();

        user.setId(2L);
        user.setAge(120);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    @Test
    public void deleteUser() {
        Long userId = 1L; // userID to be deleted

        int rows = userMapper.deleteById(userId);
        System.out.println("Deleted " + rows + " user(s)");
    }

    //Test Optimistic locking
    @Test
    public void testOptimisticLocker(){
        //get the user
        User user = userMapper.selectById(1756132697928904705L);

        //update the data
        user.setAge(200);
        userMapper.updateById(user);
    }

    //Test get multiple users by ID
    @Test
    public void selectMultiIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }



}
