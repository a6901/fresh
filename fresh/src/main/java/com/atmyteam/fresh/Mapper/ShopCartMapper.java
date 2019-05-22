package com.atmyteam.fresh.Mapper;

import com.atmyteam.fresh.Mode.ShopCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface ShopCartMapper {
    /**
     * 查看自己的购物车
     * @param shopCart 用户的电话号码
     * @return 返回购物车商品列表
     */
    @SelectProvider(type = MySQL.class ,method = "getShopCart")
    ArrayList<ShopCart> getShopCart(ShopCart shopCart);

    /**
     * 插入购物车
     * @param shopCart 要插入购物的的信息
     * @return 插入的结果成功或失败
     */
    @Insert("INSERT INTO shopcart(telephone,item,num) VALUES(#{telephone},#{item},#{num})")
    Boolean insertShopCart(ShopCart shopCart);

    /**
     * 更新购物车商品数量
     * @param shopCart 商品信息用户信息
     * @return 更新的结果
     */
    @Update("Update shopcart set num = num +#{num} where telephone = #{telephone} and item = #{item}")
    Boolean updateShopCart(ShopCart shopCart);

    /**
     * 删除购物车里的一个商品
     * @param shopCart 要删除的商品信息
     * @return 删除结果成功或失败
     */
    @Delete("DELETE FROM shopcart where telephone = #{telephone} and item = #{item}")
    Boolean removeShopCart(ShopCart shopCart);

    /**
     * 清空自己的购物车
     * @param telephone 要清空购物车的用户的电话号码
     * @return 返回清空结果 成功或失败
     */
    @Delete("DELETE FROM shopcart where telephone = #{telephone}")
    Boolean removeShopCarts(@Param(value = "telephone") String telephone);
}
