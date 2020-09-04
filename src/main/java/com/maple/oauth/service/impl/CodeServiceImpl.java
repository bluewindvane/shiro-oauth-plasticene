package com.maple.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.oauth.entity.Code;
import com.maple.oauth.mapper.CodeMapper;
import com.maple.oauth.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public void deleteByCode(String code) {
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);

        codeMapper.delete(queryWrapper);
    }
}
