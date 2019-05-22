package com.atmyteam.fresh.Service.Impl;

import com.atmyteam.fresh.Mapper.GoodsMapper;
import com.atmyteam.fresh.Mode.ShopCart;
import com.atmyteam.fresh.Service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AsyncServiceImpl implements AsyncService {
    private final
    GoodsMapper goodsMapper;

    public AsyncServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Async
    public void addSale(ArrayList<ShopCart> shopCarts){
        try {
            goodsMapper.addSales(shopCarts);
        }catch(Exception e) {
            System.out.println("销量增加出错!");
        };
    }
}
