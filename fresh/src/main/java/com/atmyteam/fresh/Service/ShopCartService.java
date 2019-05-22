package com.atmyteam.fresh.Service;

import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.ShopCart;
import java.util.ArrayList;

public interface ShopCartService {
     Response<ArrayList<ShopCart>> getShopCart(String telephone);
     Response insertShopCart(ShopCart shopCart);
     Response removeShopCart(ShopCart shopCart);
}
