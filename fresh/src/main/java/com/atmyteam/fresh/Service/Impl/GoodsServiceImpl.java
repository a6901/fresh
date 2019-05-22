package com.atmyteam.fresh.Service.Impl;
import com.atmyteam.fresh.Mapper.GoodsMapper;
import com.atmyteam.fresh.Mode.Goods;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Service.GoodsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsMapper goodsMapper;

    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 按类别查询商品
     * @param goods 要查询的商品信息
     * @return 返回Response类
     */
    @Cacheable(value = "usergoods",key = "'user'+#goods.type")
    public Response getGoods(Goods goods){
        ArrayList<Goods> list;
        Response<ArrayList<Goods>> response = new Response<>();
        try {
            list = goodsMapper.getGoods(goods);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询异常");
        }
        if(list!=null){
            if (list.size()>0){
                response.setCode(100);
                response.setMessage("查询到了");
                System.out.println(list);
                response.setData(list);
            }
            else {
                response.setCode(200);
                response.setMessage("查询失败！");
            }
        }else{
            response.setCode(500);
            response.setMessage("服务器异常！");
        }
        return response;
    }

    /**
     * 模糊匹配商品
     */
    @Cacheable(value = "goods",key = "#goods")
    public Response selectGoods(Goods goods){
        Response<ArrayList<Goods>> response = new Response<>();
        ArrayList<Goods> myGoods;
        myGoods = goodsMapper.getGoods(goods);
        if (myGoods!=null && myGoods.size()>0){
            response.setCode(100);
            response.setMessage("已经查找到商品了！");
            response.setData(myGoods);
        }else{
            response.setCode(200);
            response.setMessage("查询结果为空！");
        }
        return response;
     }

    /**
     * 搜索前几位的商品
     * @return 商品数组
     */
    @Cacheable(value = "goods",key = "'hotgoods'")
     public Response getHotGoods(){
        Response<ArrayList<Goods>> response = new Response<>();
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            goods = goodsMapper.getHotGoods();
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("搜索时发生异常！");
        }
        response.setCode(100);
        response.setMessage("搜索成功！");
        response.setData(goods);
        return response;
     }
}
