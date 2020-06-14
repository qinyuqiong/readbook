package com.wenhua.readbook.bookservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.ReadbookBookrack;
import com.wenhua.readbook.bookservice.mapper.ReadbookBookrackMapper;
import com.wenhua.readbook.bookservice.service.ReadbookBookService;
import com.wenhua.readbook.bookservice.service.ReadbookBookrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书架表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
@Service
public class ReadbookBookrackServiceImpl extends ServiceImpl<ReadbookBookrackMapper, ReadbookBookrack> implements ReadbookBookrackService {

    @Autowired
    private ReadbookBookService readbookBookService;
    /**
     * 根据用户id,书籍id删除书架记录
     * @param readbookBookrack
     * @return
     */
    @Override
    public int delectBookToUser(ReadbookBookrack readbookBookrack) {
        QueryWrapper<ReadbookBookrack> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id",readbookBookrack.getBookId());
        queryWrapper.eq("user_id",readbookBookrack.getUserId());
        int delete = baseMapper.delete(queryWrapper);
        return delete;
    }

    /**
     * 分页查询用户的书架
     * @param userid
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Map<String,Object> pageById(Integer userid, Long page, Long limit) {
        //创建page的对象，传递两个参数
        IPage<ReadbookBookrack> bookrackPage = new Page<>(page,limit);
        //创建条件
        QueryWrapper<ReadbookBookrack> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        IPage<ReadbookBookrack> bookrackIPage = baseMapper.selectPage(bookrackPage, queryWrapper);
        //获取分页后的各种数据
        long pages = bookrackIPage.getPages();
        long current = bookrackIPage.getCurrent();
        long size = bookrackIPage.getSize();
        long total = bookrackIPage.getTotal();

        //获取查询到的书籍id
        List<ReadbookBookrack> getBookIdRecords = bookrackIPage.getRecords();
        //创建list存储书籍
        List<ReadbookBook> readbookBooks = new ArrayList<>();
        for (ReadbookBookrack readbookBookrack : getBookIdRecords){
            //获取到书籍id
            Integer bookId = readbookBookrack.getBookId();
            //根据书籍id查询书籍信息
            ReadbookBook book = readbookBookService.getById(bookId);
            readbookBooks.add(book);
        }
        //获取的数据整合
        Map<String, Object > bookMap = new HashMap<>();
        bookMap.put("pages",pages);
        bookMap.put("current",current);
        bookMap.put("size",size);
        bookMap.put("total",total);
        bookMap.put("readbookBooks",readbookBooks);

        return bookMap;
    }
}
