package com.maple.oauth.service.component;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.oauth.entity.Code;
import com.maple.oauth.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeComponent {

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 获取不重复的code
     *
     * @return
     */
    public String generateCode(String clientId) {
        //生成code
        String code = SecureUtil.md5().digestHex(IdUtil.fastSimpleUUID());

        //查询数据库是否有相同值
        int count = codeMapper.selectCount(new QueryWrapper<>(new Code(code, clientId)));
        if (count > 0) {
            code = generateCode(clientId);
        }

        return code;
    }

}
