package com.atmyteam.fresh.Service.Impl;

import com.atmyteam.fresh.Mapper.OrderMapper;
import com.atmyteam.fresh.Mapper.ShopCartMapper;
import com.atmyteam.fresh.Mode.OrderDetail;
import com.atmyteam.fresh.Mode.OrderList;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Service.AsyncService;
import com.atmyteam.fresh.Service.OrderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final AsyncService asyncService;
    private final ShopCartMapper shopCartMapper;

    public OrderServiceImpl(OrderMapper orderMapper, AsyncService asyncService, ShopCartMapper shopCartMapper) {
        this.orderMapper = orderMapper;
        this.asyncService = asyncService;
        this.shopCartMapper = shopCartMapper;
    }

    /**
     * 创建订单
     * @param orderList 订单的详情，直接插到订单列表里面
     * @return 返回订单创建结果Response类
     */
    @CacheEvict(value = "order",key = "'userorder'+#orderList.get(0).telephone")
    public Response insertOrder(ArrayList<OrderList> orderList){
        Response response = new Response();
        Boolean x;
        x = orderMapper.insertOrder(orderList);
        if(x!=null&&x){
            response.setCode(100);
            response.setMessage("插入成功了！");
        }else {
            response.setCode(200);
            response.setMessage("插入失败了");
        }
        return response;
    }

    /**
     * 从购物车创建订单
     * @param shopCart 购物车信息，（用户电话号码，商品item，数量）
     * @return 创建订单的结果
     */
    public Response insertMyOrder(ShopCart shopCart){
        Response response = new Response();
        ArrayList<ShopCart> shopCarts;
        shopCarts = shopCartMapper.getShopCart(shopCart); //根据电话号码获取用户购物车里的商品
        ArrayList<OrderList> orderLists = new ArrayList<>();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String insertTime = time.format(new Date());  //订单创建的时间
        if (shopCarts!=null && shopCarts.size()!=0){ //如果购物车不是空的
            for (int i=0;i<shopCarts.size();){
                OrderList mylist = new OrderList();
                mylist.setTelephone(shopCart.getTelephone());
                mylist.setItem(shopCarts.get(i).getItem());
                mylist.setNumber(shopCarts.get(i).getNum());
                mylist.setOrdertime(insertTime);
                orderLists.add(mylist);
                i++;
            }
            Boolean x;
            x = orderMapper.insertOrder(orderLists);
            //异步地给商品增加销量销量
            asyncService.addSale(shopCarts);
            if (x!=null && x){
                Boolean y = shopCartMapper.removeShopCarts(shopCart.getTelephone());
                if (y!=null && y){
                    response.setCode(100);
                    response.setMessage("创建订单成功！");
                }else {
                    response.setCode(110);
                    response.setMessage("订单创建成功，但是清空购物车失败！");
                }
            }else{
                response.setCode(400);
                response.setMessage("创建订单失败！");
            }
        }else{
            response.setCode(120);
            response.setMessage("购物车为空！");
        }
        return  response;
    }

    /**
     * 获取订单
     * @param orderList 把用户的电话号码放到orderList中
     * @return 返回查询结果 Response类
     */
    @Cacheable(value = "order",key = "'userorder'+#orderList.telephone")
    public Response getOrder(OrderList orderList){
        Response<ArrayList<OrderDetail>> response = new Response<>();
        ArrayList<OrderDetail> arrayList ;
        arrayList =orderMapper.getOrder(orderList);
        if (arrayList!=null){
            response.setCode(100);
            response.setMessage("查询成功！");
            response.setData(arrayList);
        }else{
            response.setCode(200);
            response.setMessage("查询失败!");
        }
        return  response;
    }
}
