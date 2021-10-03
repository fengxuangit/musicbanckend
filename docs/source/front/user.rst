用户相关接口
================================

收藏
--------------------------------

- POST home/favourite/<userid>/<songid>

userid 表示用户id，songid表示歌曲ID

- 请求参数::

    无

- 返回参数::

    {
        "data": null,
        "message": "success",
        "status": 200
    }


取消收藏
--------------------------------

- DELETE home/favourite/<userid>/<songid>

userid 表示用户id，songid表示歌曲ID

- 请求参数::

    无

- 返回参数::

    {
        "data": null,
        "message": "success",
        "status": 200
    }


获取自己的播放列表
--------------------------------

- GET user/playrecord


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