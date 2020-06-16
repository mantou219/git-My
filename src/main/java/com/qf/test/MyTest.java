package com.qf.test;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.pojo.UserExample;
import com.qf.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test1(){
        User user = MyBatisUtil.getMapper(UserMapper.class).selectByPrimaryKey(1);
        System.out.println(user);


    }
    @Test
    public void test2(){
        String name="狗";
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameLike("%"+name+"%");
        List<User> users = MyBatisUtil.getMapper(UserMapper.class).selectByExample(userExample);
        System.out.println(users);

    }



}
