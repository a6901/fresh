package com.atmyteam.fresh.Controller;

import com.atmyteam.fresh.Mode.OrderList;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RequestOrder {
    private final OrderService orderService;

    public RequestOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     */
    @RequestMapping(value = "/insertorder")
    public Response insertOrder (HttpServletRequest request,@RequestBody List<String> list){
        System.out.println("收到insertOrder请求");
        ArrayList<OrderList> orderLists = new ArrayList<>();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String inserttime = time.format(new Date());
        int x = list.size();
        HttpSession httpSession = request.getSession();
        for (int i=0;i<x;){
            OrderList mylist = new OrderList();
            mylist.setTelephone((String) httpSession.getAttribute("Userid"));
            mylist.setItem(list.get(i++));
            mylist.setNumber(Double.parseDouble(list.get(i++)));
            mylist.setOrdertime(inserttime);
            orderLists.add(mylist);
        }
        return orderService.insertOrder(orderLists);
    }


    /**
     * 清空购物车并创建订单
     * @param httpServletRequest 获取用户的电话号码
     * @return 返回订单创建结果
     */
    @RequestMapping(value = "/insertmyorder")
    public Response insertMyOrder(HttpServletRequest httpServletRequest){
        System.out.println("收到insertMyOrder请求");
        HttpSession httpSession = httpServletRequest.getSession();
        ShopCart shopCart = new ShopCart();
        shopCart.setTelephone((String) httpSession.getAttribute("Userid"));
        return  orderService.insertMyOrder(shopCart);
    }


    /**
     * 用户查看自己的订单
     */
    @RequestMapping(value = "/getorder")
    public Response getOrder(HttpServletRequest request){
        System.out.println("收到getorder请求");
        HttpSession httpSession = request.getSession();
        OrderList orderList = new OrderList();
        orderList.setTelephone((String) httpSession.getAttribute("Userid"));
        return orderService.getOrder(orderList);
    }
}
