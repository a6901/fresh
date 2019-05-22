package com.atmyteam.fresh.Controller;

import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Service.ShopCartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class RequestShopCart {
    private final ShopCartService shopCartService;

    public RequestShopCart(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    /**
     * 查看自己购物车商品
     * @param httpServletRequest 请求头
     * @return 商品列表和数量
     */
    @RequestMapping(value = "/getshopcart")
    public Response getShopCart(HttpServletRequest httpServletRequest){
        System.out.println("收到getshopcart请求！");
        HttpSession httpSession = httpServletRequest.getSession();
        return shopCartService.getShopCart((String) httpSession.getAttribute("Userid"));
    }

    /**
     * 往购物车添加商品
     * @param httpServletRequest  获取用户电话号码
     * @param shopCart 添加到购物车的商品
     * @return  返回添加的结果成功或失败
     */
    @RequestMapping(value = "/insertshopcart")
    public Response insertShopCart(HttpServletRequest httpServletRequest, ShopCart shopCart){
        System.out.println("收到insertshopcart请求");
        HttpSession httpSession = httpServletRequest.getSession();
        shopCart.setTelephone((String) httpSession.getAttribute("Userid"));
        return shopCartService.insertShopCart(shopCart);
    }

    /**
     * 移除购物车里的某一个商品
     * @param httpServletRequest 获取用户电话号码
     * @param shopCart 移除购物车的商品
     * @return 移除的结果
     */
    @RequestMapping(value = "/removeshopcart")
    public Response removeShopCart(HttpServletRequest httpServletRequest,ShopCart shopCart){
        System.out.println("收到removeshopcart请求");
        HttpSession httpSession = httpServletRequest.getSession();
        shopCart.setTelephone((String)httpSession.getAttribute("Userid"));
        return shopCartService.removeShopCart(shopCart);
    }
}
