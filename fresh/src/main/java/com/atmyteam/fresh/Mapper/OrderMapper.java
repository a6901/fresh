package com.atmyteam.fresh.Mapper;

import com.atmyteam.fresh.Mode.OrderDetail;
import com.atmyteam.fresh.Mode.OrderList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface OrderMapper {
    /**
     * 批量查入订单
     * @param orderList 订单的信息
     * @return 返回创建的结果，成功或失败
     */
    @Insert({
            "<script>",
            "insert into orderlist(telephone,item,ordertime,number,delivery) values",
            "<foreach collection = 'orderList' item='item' index='index' separator=','>",
            "(#{item.telephone},#{item.item},#{item.ordertime},#{item.number},0)",
            "</foreach>",
            "</script>"
    })
    Boolean insertOrder(@Param(value = "orderList") List<OrderList> orderList);


    /**
     * 获得订单详情
     * @param orderList 订单的电话号码
     * @return 返回该电话号码的所有订单
     */
    @SelectProvider(type = MySQL.class,method = "getOrder")
    ArrayList<OrderDetail> getOrder(OrderList orderList);
}
