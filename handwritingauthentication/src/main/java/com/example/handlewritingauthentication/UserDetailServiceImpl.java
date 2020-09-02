package com.example.handlewritingauthentication;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hanliukui
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    /**模拟存储用户信息*/
    private List<UserDetail> userList = new ArrayList<>();

    /**在构造函数中把用户信息放入到userList*/
    public UserDetailServiceImpl(){
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        authorities1.add("p2");
        userList.add(new UserDetail(1L,"libai","aaaaaa",12,authorities1));

        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p1");
        userList.add(new UserDetail(2L,"zhangfei","aaaaaa",12,authorities2));

        userList.add(new UserDetail(3L,"zhaoyun","aaaaaa",12,null));
    }

    @Override
    public UserDetail getUserByName(String name) {
        for (UserDetail user:userList){
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }
}
