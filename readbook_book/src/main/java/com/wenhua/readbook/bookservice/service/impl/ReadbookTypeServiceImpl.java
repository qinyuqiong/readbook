package com.wenhua.readbook.bookservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhua.readbook.bookservice.entity.ReadbookType;
import com.wenhua.readbook.bookservice.mapper.ReadbookTypeMapper;
import com.wenhua.readbook.bookservice.service.ReadbookTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 规则表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
@Service
public class ReadbookTypeServiceImpl extends ServiceImpl<ReadbookTypeMapper, ReadbookType> implements ReadbookTypeService {

    /**
     * 根据书籍类型返回类型id
     * @param readbookType
     * @return
     */
    @Override
    public Integer selectTypeId(ReadbookType readbookType) {
        QueryWrapper<ReadbookType> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(readbookType.getName()) && !StringUtils.isEmpty(readbookType.getNickname())){
            queryWrapper.eq("name",readbookType.getName());
            queryWrapper.eq("nickname",readbookType.getNickname());
            ReadbookType readbookType1 = baseMapper.selectOne(queryWrapper);
            return readbookType1.getId();
        }
        return null;
    }
}
