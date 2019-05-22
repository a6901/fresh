package com.atmyteam.fresh.Mapper;

import com.atmyteam.fresh.Mode.Goods;
import com.atmyteam.fresh.Mode.OrderList;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Mode.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class MySQL {
    /**
     * 更新用户信息
     * @param user 用户要更改的信息
     * @return 返回一个SQL语句
     */
    public String upUser(User user){
        return new SQL(){{
            UPDATE("user");
            if (user.getUsername()!=null){
                SET("username = #{username}");
            }
            if (user.getEmail()!=null){
                SET("email = #{email}");
            }
            if (user.getPassword()!=null){
                SET("password = #{password}");
            }
            if (user.getAddress()!=null){
                SET("address =#{address}");
            }
            WHERE("telephone = #{telephone}");
        }}.toString();
    }

    /**
     * 模糊查询获取商品
     * @param goods 商品的部分信息
     */
    public String getGoods(Goods goods){
        return new SQL(){{
            SELECT("*");
            FROM("goods");
            if (goods.getItem()!=null){
                WHERE("item = #{item}");
            }
            if (goods.getType()!=null){
                WHERE("type = #{type}");
            }
            if (goods.getName()!=null){
                WHERE("name LIKE CONCAT(CONCAT('%',#{name}),'%')");
            }
            WHERE("shelves = 1");
        }}.toString();
    }

    /**
     * 获取订单
     * @param orderList 要创建的订单的信息
     */
    public  String getOrder(OrderList orderList){
        return new SQL(){{
            SELECT("*");
            FROM("market");
            if (orderList.getTelephone()!=null){
                WHERE("telephone = #{telephone}");
            }
        }}.toString();
    }

    /**
     * 查看自己的订单
     * @param shopCart 要查询订单的电话号码
     * @return 返回一个SQL
     */
    public String getShopCart(ShopCart shopCart){
        return new SQL(){{
            SELECT("*");
            FROM("goodscart");
            if (shopCart.getItem()!=null){
                WHERE("item = #{item}");
            }
            WHERE("telephone = #{telephone}");
        }}.toString();
    }

    /**
     * 给商品增加销量
     * @param shopCarts 购物车里面的商品
     * @return 返回一个SQL语句
     */
    public String addSales(List<ShopCart> shopCarts) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE goods SET sales = sales +1 WHERE item IN (");
        for (int i = 0; i < shopCarts.size(); i++) {
            sb.append("'").append(shopCarts.get(i).getItem()).append("'");
            if (i < shopCarts.size() - 1)
                sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}
