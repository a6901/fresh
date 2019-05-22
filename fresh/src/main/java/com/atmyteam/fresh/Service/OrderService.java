package com.atmyteam.fresh.Service;

import com.atmyteam.fresh.Mode.OrderList;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;

import java.util.ArrayList;

public interface OrderService {
     Response insertOrder(ArrayList<OrderList> orderList);
     Response getOrder(OrderList orderList);
     Response insertMyOrder(ShopCart shopCart);
}
