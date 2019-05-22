package com.atmyteam.fresh.Mapper;

import com.atmyteam.fresh.Mode.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    /**
     * 登录查询
     * @param user 用户的电话号码
     * @return 用户的完整信息
     */
    @Select("select * from user where telephone = #{telephone}")
    User getUser(User user);

    /**
     * 用户注册
     * @param user 用户的信息
     * @return 创建结果成功或失败
     */
    @Insert("INSERT INTO user(username,password,email,telephone) VALUES(#{username}, #{password},#{email},#{telephone})")
    Boolean creatUser(User user);

    /**
     * 修改用户信息
     * @param user 修改后的用户信息
     * @return 修改结果成功或失败
     */
    @UpdateProvider(type = MySQL.class,method = "upUser")
    Boolean upUser(User user);
}
