package com.maple.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("oauth_client_details")
public class ClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户端Id
     */
    private String clientId;

    /**
     * 客户端secret
     */
    private String clientSecret;

    /**
     * 预留字段
     */
    private String additionalInformation;

    /**
     * 客户端的access_token的有效时间值(单位:秒)
     */
    private Long accessTokenValidity;

    /**
     * 描述
     */
    private String description;

    /**
     * 默认为’0’(即不受信任的,1为受信任的)
     */
    private Boolean trusted;

    /**
     * 如果不受信任，该字段必填，关联不受信任表
     */
    private String trustType;


}
