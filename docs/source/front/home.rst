首页接口
================================

获取首页封面推荐列表
--------------------------------

这个是获取P1中滑动的歌曲和背景图片

- GET home/gethome


    这个image字段中的type如果是video就是说明是视频，就播放video_url否则就展示url这个图片


- 请求参数::

    无

- 返回参数::

    {
        "data": [
            {
                "id": 1,
                "image": {
                    "id": 1,
                    "url": "http://music.gitsort.com/image/%E4%B8%8B%E9%9B%A8.jpg",
                    "type": "image",
                    "video_url": "http://music.gitsort.com//video/2021101812312.mp4"
                },
                "song": {
                    "id": 1,
                    "name": "雨滴",
                    "isvip": 0,
                    "describe": null,
                    "url": "http://music.gitsort.com/music/%E9%A6%96%E9%A1%B5/%E9%9B%A8%E6%BB%B45%E5%88%86%E9%92%9F%E7%89%88%E6%9C%AC.mp3",
                    "song_url": null,
                    "category_id": 0,
                    "insert_tm": null,
                    "update_tm": null
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
                        "isvip": 0,
                        "image_url": null,
                        "name": "雨滴",
                        "describe": "5-10分钟"
                    },
                    {
                        "song_id": 2,
                        "isvip": 0,
                        "image_url": null,
                        "name": "海浪",
                        "describe": "5-10分钟"
                    }
                ]
            },
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
                    "isvip": 0 | 1,
                    "image_url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
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


根据ID获取推荐中的歌曲列表
--------------------------------

- GET home/getHomeRecommendByID

这个获取首页P2和推荐页P4的中分类的列表数据


- 请求参数::

    {
        "id": 2, #首页分类ID ｜ 整型｜ 必填
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
                    "isvip": 0 | 1,
                    "image_url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
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
                    "isvip": 0 | 1,
                    "image_url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
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
                    "id": 3, #歌曲ID
                    "song_url": null
                    "key": "asd", #图片的key
                    "isvip": 0 | 1,
                    "image_url": "https://file.zuopin.cloud/Fsod1dlzAyQ2kIkhcidFwJ9WVqiX,", #图片的url
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
                "id": 6, #喜欢表的ID
                "user_id": 1,
                "song": {
                    "id": 2, #歌曲ID
                    "name": "雨滴",
                    "isvip": 0, #是否是收费歌曲
                    "describe": "5-10分钟",
                    "image_url": null,
                    "song_url": null,
                    "category_id": 7,
                    "insert_tm": null,
                    "update_tm": null
                },
                "insert_tm": "2021-09-23T13:55:51.000+08:00",
                "update_tm": "2021-09-23T13:55:51.000+08:00"
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

    version字段为版本数字，当小程序自带的版本大于这个数的时候，隐藏支付。等于或者小于都开放支付

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
            },
            "version": 1 #数字
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

搜索名称
--------------------------------

- GET home/search

- 请求参数::

    {
        "name": "记忆力脑波音", # 搜索的名称 | yes |字符型
    }

- 返回参数::

    {
        "data": {
            "total": 1,
            "list": [
                {
                    "id": 0,
                    "image": {
                        "id": 17,
                        "url": "http://music.gitsort.com/image/10/09/fce927f3eeb0a16df010732ce2a06bbc.jpg"
                    },
                    "song": {
                        "id": 12,
                        "name": "记忆力脑波音",
                        "isvip": 0,
                        "describe": null,
                        "image_url": null,
                        "song_url": null,
                        "category_id": 13,
                        "insert_tm": "2021-09-11T21:22:21.000+08:00",
                        "update_tm": "2021-09-11T21:22:21.000+08:00"
                    }
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
        },
        "message": "success",
        "status": 200
    }


获取歌曲地址
--------------------------------

- GET home/getSong

这个接口请求一次，也算是一次播放记录！

    这个image字段中的type如果是video就是说明是视频，就播放video_url否则就展示url这个图片

- 请求参数::

    {
        "id": 1, # 歌曲的ID | yes | 整型
        "user_id": 10 #用户ID  | yes | 整型
    }

- 返回参数::

    {
        "data": {
            "status": 0,
            "home": {
                "id": 1,
                "image": {
                    "id": 0,
                    "url": "http://music.gitsort.com/image/%E4%B8%8B%E9%9B%A8.jpg",
                    "type": "video|image",
                    "video_url": "http://music.gitsort.com//video/2021101812312.mp4"
                },
                "song": {
                    "id": 0,
                    "name": "雨滴",
                    "isvip": 0,
                    "describe": null,
                    "url": null,
                    "image_url": null,
                    "song_url": "http://music.gitsort.com/music/%E9%A6%96%E9%A1%B5/%E9%9B%A8%E6%BB%B45%E5%88%86%E9%92%9F%E7%89%88%E6%9C%AC.mp3",
                    "category_id": 0,
                    "insert_tm": null,
                    "update_tm": null
                }
            }
        },
        "message": "success",
        "status": 200
    }



过审时 获取歌曲地址
--------------------------------

- GET home/getTemporarySong

    这个接口是过审的时候临时使用！

- 请求参数::

    {
        "id": 1, # 歌曲的ID | yes | 整型
        "user_id": 10 #用户ID  | yes | 整型
    }

- 返回参数::

    {
        "data": {
            "status": 0,
            "home": {
                "id": 1,
                "image": {
                    "id": 0,
                    "url": "http://music.gitsort.com/image/%E4%B8%8B%E9%9B%A8.jpg",
                    "type": "video|image",
                    "video_url": "http://music.gitsort.com//video/2021101812312.mp4"
                },
                "song": {
                    "id": 0,
                    "name": "雨滴",
                    "isvip": 0,
                    "describe": null,
                    "url": null,
                    "image_url": null,
                    "song_url": "http://music.gitsort.com/music/%E9%A6%96%E9%A1%B5/%E9%9B%A8%E6%BB%B45%E5%88%86%E9%92%9F%E7%89%88%E6%9C%AC.mp3",
                    "category_id": 0,
                    "insert_tm": null,
                    "update_tm": null
                }
            }
        },
        "message": "success",
        "status": 200
    }
