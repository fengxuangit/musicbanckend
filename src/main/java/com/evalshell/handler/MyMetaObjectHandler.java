package com.evalshell.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final String INSERT_TIME_FIELD = "insert_tm";
    private final String UPDATE_TIME_FIELD = "update_tm";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // getFieldValByName 方法是父接口中的默认方法,用字段名字通过反射获取待插入对象中指定名字字段的值
        this.setFieldValByName(INSERT_TIME_FIELD, new Date(), metaObject);
        this.setFieldValByName(UPDATE_TIME_FIELD, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        setFieldValByName(UPDATE_TIME_FIELD, LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
