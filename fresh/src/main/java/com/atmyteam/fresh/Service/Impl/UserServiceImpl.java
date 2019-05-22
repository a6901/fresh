package com.atmyteam.fresh.Service.Impl;
import com.atmyteam.fresh.Mapper.UserMapper;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.User;
import com.atmyteam.fresh.Service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override

    @Cacheable(value = "user",key = "'telephone'+#user.telephone")
    public User getUser(User user){
        return userMapper.getUser(user);
    }

    @CachePut(value = "user",key ="'telephone'+#user.telephone")
    public  User createUser(User user){
        Boolean flag = userMapper.creatUser(user);
        if (flag==null||!flag){
            return null;
        }else{
            return userMapper.getUser(user);
        }
    }

    /**
     * 修改用户信息
     * @param user 输入的用户信息
     * @return 返回修改结果
     */
    @CacheEvict(value = "user",key = "'telephone'+#user.telephone")
    public Response upUser(User user){
        Boolean x;
        Response response = new Response();
        x = userMapper.upUser(user);
        if (x!=null && x){
            response.setCode(100);
            response.setMessage("修改成功！");
        }else {
            response.setCode(200);
            response.setMessage("修改失败！");
        }
        return  response;
    }
}
