package com.atmyteam.fresh.Controller;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.User;
import com.atmyteam.fresh.Service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class RequestUser {

    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    public RequestUser(UserService userService, StringRedisTemplate stringRedisTemplate) {
        this.userService = userService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 用户登录
     * @param user 用户信息
     * @param httpServletRequest 获得用户电话号码
     * @return user 用户信息
     */

    @RequestMapping(value = "/login") //添加路由映射
    public Response login(User user,HttpServletRequest httpServletRequest){ //从requestbody里面取出的东西放到了User对象里面
        System.out.println("接收到了login情求");
        Response<User> userResponse= new Response<>();
        User myUser = userService.getUser(user);
        if (myUser!=null){
            if (myUser.getPassword().equals(user.getPassword())){
                userResponse.setCode(100);
                userResponse.setMessage("验证通过！");
                myUser.setPassword(null);
                userResponse.setData(myUser);
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("Userid",myUser.getTelephone());
                stringRedisTemplate.opsForValue().set(myUser.getTelephone(),httpSession.getId());
            }
            else {
                userResponse.setCode(402);
                userResponse.setMessage("密码错误！");
            }
        }else {
            userResponse.setCode(401);
            userResponse.setMessage("用户未注册！");
        }
        return userResponse;
    }

    /**
     * 注册
     * @param user 用户名
     * @return 返回登录结果
     */
    @RequestMapping(value = "/create")
    public Response create(User user,HttpServletRequest httpServletRequest){
        System.out.println("接收到了Create请求");
        HttpSession httpSession = httpServletRequest.getSession();
        Response<User> newuser = new Response<>();
        User olduser = userService.getUser(user);
        if (olduser!=null){
            newuser.setCode(300);
            newuser.setMessage("电话号码已被注册！");
        }else{
            User myuser = userService.createUser(user);
            if (myuser==null){
                newuser.setCode(200);
                newuser.setMessage("注册失败，服务器发生错误！");
            }else{
                newuser.setCode(100);
                newuser.setMessage("注册成功！");
                newuser.setData(myuser);
                httpSession.setAttribute("Userid",myuser.getTelephone());
                stringRedisTemplate.opsForValue().set(myuser.getTelephone(),httpSession.getId());
            }
        }
        return  newuser;
    }

    /**
     * 用户登出
     * @param httpServletRequest 请求头取出telephone到Redis删除信息
     * @return 返回成功
     */
    @RequestMapping(value = "/exit")
    public Response exit(HttpServletRequest httpServletRequest){
        System.out.println("收到exit请求");
        HttpSession httpSession = httpServletRequest.getSession();
        String telephone = (String) httpSession.getAttribute("Userid");
        stringRedisTemplate.delete(telephone);
        httpSession.invalidate();
        return new Response(100,"退出成功！");
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 返回是否成功
     */
    @RequestMapping(value = "/updatauser")
    public Response updateUser(HttpServletRequest httpServletRequest, User user){
        System.out.println("收到了updateuser请求");
        HttpSession httpSession = httpServletRequest.getSession();
        user.setTelephone((String)httpSession.getAttribute("Userid"));
        return userService.upUser(user);
    }
}

