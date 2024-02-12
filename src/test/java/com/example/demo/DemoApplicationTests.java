package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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
        user.setName("Jimmy");
        user.setAge(35);
        user.setEmail("Jimmy@gmail.com");

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

    //Test Delete Multi users By ID
    @Test
    public void DeleteMultiIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
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

    //Test simple conditional query
    @Test
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Alice");
        map.put("age", 28);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    //Test select page
    @Test
    public void selectPage(){
        //every page have 3 results
        Page<User> page = new Page<>(1,3);

        userMapper.selectPage(page, null);

        System.out.println(page.getCurrent());//Current page
        System.out.println(page.getRecords());//List
        System.out.println(page.getSize());//Results number every page
        System.out.println(page.getTotal());//Total results numbers
        System.out.println(page.getPages());//Total page numbers

        System.out.println(page.hasNext());//Next page
        System.out.println(page.hasPrevious());//Previous page
    }

    @Test
    public void selectQuery(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //ge gt lt le || eq ne || orderBy groupBy between like
//        wrapper.ge("age", 30);
//        wrapper.eq("name", "Alice");
//        wrapper.between("age", 20,30);
        wrapper.like("name", "j");
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }


}
