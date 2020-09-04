package com.maple.oauth.mapper;

import com.maple.oauth.entity.Code;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 三方接口接入（存储code值） Mapper 接口
 * </p>
 *
 * @author yxf
 * @since 2020-08-20
 */
@Repository
public interface CodeMapper extends BaseMapper<Code> {

    /**
     * 查询clientId和code且过期时间在second时间范围内的
     *
     * @param clientId
     * @param code
     * @return
     */
    @Select(" SELECT * FROM  oauth_code WHERE code=#{code} AND authentication=#{clientId} " +
            " AND (TIME_TO_SEC(NOW()) -TIME_TO_SEC(create_time)) <= #{second} ")
    Code checkClientIdAndCodeInExpire(@Param("clientId") String clientId,
                                      @Param("code") String code,
                                      @Param("second") Integer second);
}
