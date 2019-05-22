git https://github.com/a6901/fresh.git    //获取代码

###登录接口
~~~
URL：user/login
data：{
  telephone:
  passWord:
}
return:
{
    code: 状态码,
    message: "返回消息",
    data: {
	username:"用户名",
    telephone:"用户的电话号码"
}
~~~
code|message|data
:--|:--:|:--:   
100|登录成功|
200|账号或密码错误|

###退出登录
~~~
URL:user/exit
return:{
	code:"100",
    message:"退出成功！",
}
~~~
code|message|data
:--|:--:|:--:
100|退出成功|
500|未登录|

###修改用户信息
~~~
URL:user/updatauser,
data:{
	username:"用户名",
    email:"电子邮箱",
    password:"密码",
    address:"地址",
    (4选n)
}
~~~
code|message|data
:--|:--:|:--:
100|修改成功|
200|修改失败|
500|未登录|


###注册接口
~~~
url:user/create
data:{
	telephone:"用户电话号码",
    username:"用户名",
    password:"密码",
    email:"电子邮箱",
}
return：{
	code:"状态码",
    message:"状态信息",
    data:{
    	username:"用户名",
        telephone:"用户电话号码"
    }
}
~~~
code|message|data
:--|:--:|:--:
100|注册成功|
200|注册失败|
300|电话号码已被注册|


###货物获取接口
~~~
url:user/getgoods
data:{
    type:"商品的类型",
}
return：
{
	code:"状态码",
    message:"状态信息",
    data:{
    	item:"商品id",
        name:"商品的名字",
        price:"商品的价格",
        type:"商品的类型",
        content:"商品的内容",
    }
}
~~~
code|message|data
:--|:--:|:--:
100|查询成功|
200|查询失败|

###购买商品创建订单
~~~
url:user/insertorder
var list = ["商品1id","购买1数量","商品2id","购买2数量"];
data:{
		url: '/user/insertorder',
    	type: 'post',
        dataType: 'json',
        data: JSON.stringify(list),
        contentType:'application/json',
}
return:{
	code:"状态码",
    message:"状态信息",
}
~~~
code|message|data
:--|:--:|:--:
100|订单创建成功|
200|订单创建失败|
500|用户未登录|

###用户查看自己的订单
~~~
url:user/getorder
return:{
	code:"状态码",
    message:"状态信息",
    data:{
		orderNo:"订单id",
        ordertime:"订单创建时间",
        amount:"订单金额",
        delivery:"订单状态(0--未派送;1--订单已派送)",
        item:"商品id",
        goodsname:"商品名字",
        ...,
    }
}
~~~
code|message|data
:--|:--:|:--:
100|查询成功|
200|查询失败|
500|用户未登录|












