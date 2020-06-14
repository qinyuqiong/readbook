package com.wenhua.readbook.bookservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook.bookservice.entity.ReadbookBookType;
import com.wenhua.readbook.bookservice.mapper.ReadbookBookTypeMapper;
import com.wenhua.readbook.bookservice.service.ReadbookBookTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 书籍类型关联表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
@Service
public class ReadbookBookTypeServiceImpl extends ServiceImpl<ReadbookBookTypeMapper, ReadbookBookType> implements ReadbookBookTypeService {

    @Override
    public int updateByBook(ReadbookBookType readbookBookType) {
        QueryWrapper<ReadbookBookType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id",readbookBookType.getBookId());
        int update = baseMapper.update(readbookBookType, queryWrapper);
        return update;
    }

    /**
     * 根据类型id分页查询
     * @param eduBookPage
     * @param readbookBookType
     * @return
     */
    @Override
    public IPage<ReadbookBookType> pageListCondition(Page<ReadbookBookType> eduBookPage, ReadbookBookType readbookBookType) {
        //1.判断readbookuser是否为空
        if (StringUtils.isEmpty(readbookBookType)) {
            IPage<ReadbookBookType> readbookBookTypeIPage = baseMapper.selectPage(eduBookPage, null);
            return readbookBookTypeIPage;
        }
        Integer typeId = readbookBookType.getTypeId();
        //类型id
        QueryWrapper<ReadbookBookType> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(typeId)){
            queryWrapper.eq("type_id",typeId);
        }
        IPage<ReadbookBookType> readbookBookTypeIPage = baseMapper.selectPage(eduBookPage, queryWrapper);
        return readbookBookTypeIPage;
    }
}
