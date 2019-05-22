package com.atmyteam.fresh.Service.Impl;

import com.atmyteam.fresh.Mapper.ShopCartMapper;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Service.ShopCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopCartServiceImpl implements ShopCartService {
    private final
    ShopCartMapper shopCartMapper;

    public ShopCartServiceImpl(ShopCartMapper shopCartMapper) {
        this.shopCartMapper = shopCartMapper;
    }

    /**
     * 查询自己的购物车商品
     */
    public Response<ArrayList<ShopCart>> getShopCart(String telephone){
        Response<ArrayList<ShopCart>> response = new Response<>();
        ArrayList<ShopCart> shopCarts ;
        ShopCart shopCart = new ShopCart();
        shopCart.setTelephone(telephone);
        shopCarts = shopCartMapper.getShopCart(shopCart);
        if (shopCarts!=null){
            response.setCode(100);
            response.setMessage("查询成功!");
            response.setData(shopCarts);
        }else{
            response.setCode(200);
            response.setMessage("购物车为空！");
        }
        return response;
    }

    /**
     * 给购物车添加商品
     */
    public Response insertShopCart(ShopCart shopCart){
        Response response = new Response();
       if (shopCartMapper.getShopCart(shopCart)!=null && shopCartMapper.getShopCart(shopCart).size()>0){
           Boolean x =shopCartMapper.updateShopCart(shopCart);
           if (x!=null && x){
               response.setCode(110);
               response.setMessage("更新商品数量！");
           }else{
               response.setCode(400);
               response.setMessage("商品已在购物车，但是修改数量时失败！！");
           }
       }else {
           Boolean y = shopCartMapper.insertShopCart(shopCart);
           if (y!=null && y){
               response.setCode(100);
               response.setMessage("商品加入到购物车");
           }else{
               response.setCode(410);
               response.setMessage("商品不在购物车，插入失败！！");
           }
       }
       return response;
    }

    /**
     * 删除购物车里的一个商品
     */
    public Response removeShopCart(ShopCart shopCart){
        Response response = new Response();
        if (shopCartMapper.getShopCart(shopCart)!=null && shopCartMapper.getShopCart(shopCart).size()>0){
            Boolean x = shopCartMapper.removeShopCart(shopCart);
            if (x!=null && x){
                response.setCode(100);
                response.setMessage("删除成功！");
            }else {
                response.setCode(400);
                response.setMessage("商品在购物车，但是删除失败了！");
            }
        }else{
            response.setCode(410);
            response.setMessage("要删除的商品不在购物车！");
        }
        return response;
    }
}
