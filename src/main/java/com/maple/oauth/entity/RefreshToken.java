package com.maple.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxf
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_refresh_token")
public class RefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    private String tokenId;

    /**
     * 存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
     */
    private String token;

    /**
     * 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    private Object authentication;


}
