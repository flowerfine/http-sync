package cn.sliew.http.stream.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
@MapperScan(basePackages = "cn.sliew.http.stream.dao")
public class MybatisConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Component
    public static class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "creator", () -> "system", String.class);
            this.strictInsertFill(metaObject, "modifier", () -> "system", String.class);
            this.strictInsertFill(metaObject, "gmtCreate", () -> new Date(), Date.class);
            this.strictInsertFill(metaObject, "gmtModified", () -> new Date(), Date.class);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "modifier", () -> "system", String.class);
            this.strictUpdateFill(metaObject, "gmtModified", () -> new Date(), Date.class);
        }
    }
}