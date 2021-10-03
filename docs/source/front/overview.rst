通用说明
================================

- 接口设计需遵循REST格式。

- 所有接口的请求前缀为 `/api`

- 协议编码采用utf-8格式

- 响应数据为JSON格式:

    正常响应::

       {
         "message": "SUCCESS",
         "code": 200,
         "data": {}
       }

    出错响应::

       {
         "message": "error_msg",
         "code": 100
       }


- 客户端GET请求时，可增加 _rnd 字段 以避免缓存问题

- 协议字段统一由小写字母和下划线组成

- 使用JWT认证，需要先请求登陆接口获取token，然后请求头中带有

```Authorization: bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjI0NTE3NzQ3LCJqdGkiOiI1YzJkY2IxOTU2MzI0NjI1ODBiMzI0YmE4MmM0MjA4YyIsInVzZXJfaWQiOjEsIm5hbWUiOiJmZW5neHVhbiJ9.DBDzUd79KdumARRwLenvR6b_iaXvWia995xU2Q2lBxo
```
进行认证
