package com.maple.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 三方接口接入（存储code值）
 * </p>
 *
 * @author yxf
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_code")
@NoArgsConstructor
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 存储code接口生成的code值
     */
    @NotBlank(message = "bu neng wei kong")
    private String code;

    /**
     * 授权字段
     */
    private String authentication;

    public Code(String code, String authentication) {
        this.code = code;
        this.authentication = authentication;
    }

    public Code(String authentication) {
        this.authentication = authentication;
    }
}
