首页接口
================================

获取首页封面推荐列表
--------------------------------

这个是获取P1中滑动的歌曲和背景图片

- GET home/gethome

- 请求参数::

    无

- 返回参数::

    {
        "data": [
            {
                "id": 1,
                "image": {  # 图片对象
                    "id": 1, #图片ID
                    "key": "asda", # 七牛云的key
                    "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX," #总的地址
                },
                "song": {
                    "id": 1, #歌曲名称
                    "name": "起风了", #歌曲名称
                    "describe": null, #歌曲表述
                    "key": "asda", # 歌曲封面图的key
                    "url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3", # 歌曲封面图的实际地址
                    "song_key": null,   # 歌曲的key
                    "song_url": null,# 歌曲的实际地址
                    "category_id": 0, #分类ID
                    "insert_tm": null, # 插入时间
                    "update_tm": null # 更新时间
                }
            },
        ],
        "message": "success",
        "status": 200
    }


获取日贴
--------------------------------



- GET home/getCalendarByDate

- 请求参数::

    {
        "date": "2021-12-12", #日期 ｜ 字符型｜ 必填
    }


- 返回参数::

    {
        "id": 1, # 日历ID
        "image": {
            "id": 2, # 图片id
            "key": "asda", # 图片key
            "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX," # 图片url
        },
        "date": "2021-09-11", # 日历的时间
        "word": "{\"word\":\"人世漫长得转瞬即逝，有人见尘埃，有人见星辰\",\"author\":\"小说家，毛姆\"}\n",
        "wordObject": {
            "author": "小说家，毛姆", #名言的作者
            "word": "人世漫长得转瞬即逝，有人见尘埃，有人见星辰" # 名言
        },
        "insert_tm": "2021-09-11T13:08:53.000+08:00", # 插入时间
        "update_tm": "2021-09-11T13:08:55.000+08:00"  # 更新时间
    }


获取首页下面的分类推荐
--------------------------------

这个是获取首页下拉P2中的数据

- GET home/getHomeRecommend

- 请求参数::

    无


- 返回参数::

    {
        "data": [
            {
                "name": "轻松时刻",
                "data": [
                    {
                        "song_id": 1,
                        "song_key": "asda",
                        "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3",
                        "key": "asda",
                        "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,",
                        "name": "起风了",
                        "describe": "5-10分钟"
                    },
                    {
                        "song_id": 2,
                        "song_key": "asd",
                        "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3",
                        "key": "asd",
                        "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,",
                        "name": "减压曲1",
                        "describe": "5-10分钟"
                    }
                ]
            },
            {
                "name": "情绪调节",
                "data": [
                    {
                        "song_id": 3,
                        "song_key": "asd",
                        "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3",
                        "key": "asd",
                        "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,",
                        "name": "减压曲2",
                        "describe": "5-10分钟"
                    },
                    {
                        "song_id": 1,
                        "song_key": "asda",
                        "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3",
                        "key": "asda",
                        "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,",
                        "name": "起风了",
                        "describe": "5-10分钟"
                    }
                ]
            }
        ],
        "message": "success",
        "status": 200
    }


获取首页推荐和分类推荐
--------------------------------

- GET home/getRecommendCategory

这个获取首页P2和推荐页P4的分类名称

type字段为home的时候，就是首页的分类，如果是discovery就是推荐页的分类

- 请求参数::

    无

- 返回参数::

    {
        "data": [
            {
                "id": 1, #分类ID
                "name": "轻松时刻", # 分类名称
                "type": "home" # 分类所属类型
            },
            {
                "id": 3,
                "name": "精选冥想",
                "type": "discovery"
            },
        ],
        "message": "success",
        "status": 200
    }


获取推荐中的歌曲列表
--------------------------------

- GET home/getHomeRecommendByName

这个获取首页P2和推荐页P4的中分类的列表数据


- 请求参数::

    {
        "name": "情绪调节", #名称 ｜ 字符型｜ 必填
        "page": 1, #页码 | 整形 | 选填
        "size": 10, #页数  | 整形 | 选填
    }

- 返回参数::

    {
        "data": {
            "total": 2, #总共的条数
            "list": [
                {
                    "song_id": 3, #歌曲ID
                    "song_key": "asd", #歌曲的Key
                    "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3", #歌曲的URL
                    "key": "asd", #图片的key
                    "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
                    "recommend_name": "情绪调节", #首页推荐的名称
                    "name": "减压曲2", #歌曲名
                    "describe": "5-10分钟" #歌曲描述
                },

            ],
            "pageNum": 1, #当前页数
            "pageSize": 10, #当前页码
            "size": 2, #总共的条数
            "startRow": 1,
            "endRow": 2,
            "pages": 1, #总共的页数
            "prePage": 0, #上一页
            "nextPage": 0, #下一页
            "isFirstPage": true, #是否是第一页
            "isLastPage": true, #是否是最后一页
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1
        },
        "message": "success",
        "status": 200
    }


获取分类中的歌曲列表
--------------------------------

- GET home/getCategoryRecommendByName


- 请求参数::

    {
        "name": "情绪调节", #日期 ｜ 字符型｜ 必填
        "page": 1, #页码 | 整形 | 选填
        "size": 10, #页数  | 整形 | 选填
    }

- 返回参数::

    {
        "data": {
            "total": 2, #总共的条数
            "list": [
                {
                    "song_id": 3, #歌曲ID
                    "song_key": "asd", #歌曲的Key
                    "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3", #歌曲的URL
                    "key": "asd", #图片的key
                    "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
                    "recommend_name": "情绪调节", #首页推荐的名称
                    "name": "减压曲2", #歌曲名
                    "describe": "5-10分钟" #歌曲描述
                },

            ],
            "pageNum": 1, #当前页数
            "pageSize": 10, #当前页码
            "size": 2, #总共的条数
            "startRow": 1,
            "endRow": 2,
            "pages": 1, #总共的页数
            "prePage": 0, #上一页
            "nextPage": 0, #下一页
            "isFirstPage": true, #是否是第一页
            "isLastPage": true, #是否是最后一页
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1
        },
        "message": "success",
        "status": 200
    }


根据分类ID获取分类中的歌曲列表
--------------------------------

- GET home/getSongByCategoryId

    如果你传的是一级分类的ID，则会返回这个一级分类下的所有歌曲列表

    如果你传递的是二级分类的ID，则会返回二级分类下的所有歌曲列表

- 请求参数::

    {
        "id": 3, #分类ID ｜ 整形｜ 必填
        "page": 1, #页码 | 整形 | 选填
        "size": 10, #页数  | 整形 | 选填
    }


- 返回参数::

    {
        "data": {
            "total": 2, #总共的条数
            "list": [
                {
                    "song_id": 3, #歌曲ID
                    "song_key": "asd", #歌曲的Key
                    "song_url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3", #歌曲的URL
                    "key": "asd", #图片的key
                    "url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
                    "recommend_name": "情绪调节", #首页推荐的名称
                    "name": "减压曲2", #歌曲名
                    "describe": "5-10分钟" #歌曲描述
                },

            ],
            "pageNum": 1, #当前页数
            "pageSize": 10, #当前页码
            "size": 2, #总共的条数
            "startRow": 1,
            "endRow": 2,
            "pages": 1, #总共的页数
            "prePage": 0, #上一页
            "nextPage": 0, #下一页
            "isFirstPage": true, #是否是第一页
            "isLastPage": true, #是否是最后一页
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1
        },
        "message": "success",
        "status": 200
    }


获取用户喜欢列表
--------------------------------

- GET home/favourite/list


- 请求参数::

    {
        "user_id": 1, #用户ID ｜ 整形 ｜ 必填
        "page": 1, #页码 | 整形 | 选填
        "size": 10, #页数  | 整形 | 选填
    }

- 返回参数::

     {
        "total": 3,
        "list": [
            {
                "id": 1,
                "user_id": 1,
                "song": {
                    "id": 0,
                    "name": "减压曲2",
                    "describe": null,
                    "key": "asd",
                    "url": "https://mp32.9ku.com/upload/128/2018/02/09/875689.mp3",
                    "song_key": null,
                    "song_url": null,
                    "category_id": 0,
                    "insert_tm": null,
                    "update_tm": null
                },
                "insert_tm": "2021-09-23T11:28:09.000+08:00",
                "update_tm": "2021-09-23T11:28:09.000+08:00"
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }

获取配置
--------------------------------

- GET home/setting

- 请求参数::

    无

- 返回参数::

    {
        "data": {
            "id": 1,
            "payinfo": "{\"viptype\":{\"month\":{\"old\":18,\"now\":14},\"quarter\":{\"old\":39,\"now\":30},\"half\":{\"old\":70,\"now\":65},\"year\":{\"old\":120,\"now\":100}}}\n",
            "payinfobj": {
                "viptype": {
                    "half": {
                        "old": 70,
                        "now": 65
                    },
                    "month": {
                        "old": 18,
                        "now": 14
                    },
                    "year": {
                        "old": 120,
                        "now": 100
                    },
                    "quarter": {
                        "old": 39,
                        "now": 30
                    }
                }
            }
        },
        "message": "success",
        "status": 200
    }

获取所有分类
--------------------------------

- GET /categorys

- 请求参数::

    无

- 返回参数::

        [
            {
                "id": 2,
                "name": "减压",
                "child": [
                    {
                        "id": 6,
                        "name": "全部",
                        "parent_category_id": 2
                    }
                ]
            },
            {
                "id": 1,
                "name": "推荐",
                "child": [
                    {
                        "id": 4,
                        "name": "睡眠精选",
                        "parent_category_id": 1
                    }
                ]
            }
        ]