支付接口
================================

获取支付
--------------------------------

- GET wechat/wxjspay

:type字段说明
- month 月度
- quarter 季度
- half 半年
- year 年度

- 请求参数::

    {
        "openid": "oeI0v5d42OtBQOQQ1Hw0ZnOgNSWE", #openid ｜ 字符型｜ 必填
        "user_id": 1, #用户ID | 整型 | 必填
        "type": "month|quarter|half|year"# 支付类型 ｜ 字符型 ｜ 必填
    }


- 返回参数::

    {
        "data": {
            "timeStamp": "1632808670",
            "package": "prepay_id=wx2813575050340563935965363071140000",
            "paySign": "53D4E5BEDD49465C01952BAC7A2E1B34",
            "appId": "wx91329a5154e6ced2",
            "signType": "MD5",
            "nonceStr": "5Av34iT"
        },
        "message": "success",
        "status": 200
    }