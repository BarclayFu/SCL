package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;
    //Get all user data
    @Test
    public void contextLoads() {
        List<User> users= userMapper.selectList(null);
        System.out.println(users);
    }
    //添加add
    @Test
    public void addUser(){
        User user = new User();
        user.setName("Lucy");
        user.setAge(30);
        user.setEmail("lucy@gmail.com");

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

}
