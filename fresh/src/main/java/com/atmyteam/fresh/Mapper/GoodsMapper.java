package com.atmyteam.fresh.Mapper;

import com.atmyteam.fresh.Mode.Goods;
import com.atmyteam.fresh.Mode.ShopCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface GoodsMapper {
    /**
     * 查询商品
     * @param goods 要查询的商品信息
     * @return 返回商品列表
     */
    @SelectProvider(type = MySQL.class,method = "getGoods")
    ArrayList<Goods> getGoods(Goods goods);

    @Select("SELECT * FROM goods where shelves = 1 order by sales DESC limit 5")
    ArrayList<Goods> getHotGoods();

    /**
     * 商品销量+1
     * @param shopCarts 购物车里的商品列表
     */
    @UpdateProvider(type = MySQL.class,method = "addSales")
    void addSales(@Param(value = "shopCarts") List<ShopCart> shopCarts);
}
