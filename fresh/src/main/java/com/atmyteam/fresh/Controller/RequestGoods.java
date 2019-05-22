package com.atmyteam.fresh.Controller;

import com.atmyteam.fresh.Mode.Goods;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestGoods {
    private final
    GoodsService goodsService;

    public RequestGoods(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 获取货物信息
     * @param goods 要搜索货物信息
     * @return 已经上架的货物信息
     */
    @RequestMapping(value = "/getgoods")
    public Response getGoods(Goods goods){
        System.out.println("接收到getgoods请求");
        if (goods.getType().length()==0){
            goods.setType(null);
        }
        return goodsService.getGoods(goods);
    }

    /**
     * 模糊查询一种商品
     * @param goods 商品的相关信息
     * @return  要搜索的商品信息
     */
    @RequestMapping(value = "/selectgoods")
    public Response selectGoods(Goods goods){
        System.out.println("收到selectgoods请求！");
        return goodsService.selectGoods(goods);
    }


    /**
     * 热门商品获取
     * @return 销量前五的商品
     */
    @RequestMapping(value = "/gethotgoods")
    public Response getHotGoods(){
        System.out.println("收到gethotgoods请求！");
        return goodsService.getHotGoods();
    }
}
