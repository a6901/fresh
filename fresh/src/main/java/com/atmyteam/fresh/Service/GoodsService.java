package com.atmyteam.fresh.Service;
import com.atmyteam.fresh.Mode.Goods;
import com.atmyteam.fresh.Mode.Response;

public interface GoodsService {
     Response getGoods(Goods goods);
     Response selectGoods(Goods goods);
     Response getHotGoods();
}
