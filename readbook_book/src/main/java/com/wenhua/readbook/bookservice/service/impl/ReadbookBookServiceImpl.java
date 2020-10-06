package com.wenhua.readbook.bookservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.ReadbookBookType;
import com.wenhua.readbook.bookservice.entity.ReadbookType;
import com.wenhua.readbook.bookservice.entity.query.QueryBook;
import com.wenhua.readbook.bookservice.handler.ConstantPropertiesUtil;
import com.wenhua.readbook.bookservice.mapper.ReadbookBookMapper;
import com.wenhua.readbook.bookservice.service.ReadbookBookService;
import com.wenhua.readbook.bookservice.service.ReadbookBookTypeService;
import com.wenhua.readbook.bookservice.service.ReadbookTypeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 书籍信息表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-12
 */
@Service
public class ReadbookBookServiceImpl extends ServiceImpl<ReadbookBookMapper, ReadbookBook> implements ReadbookBookService {

    @Autowired
    private ReadbookBookTypeService readbookBookTypeService;

    @Autowired
    private ReadbookTypeService readbookTypeService;

    /**
     * 上传书籍
     * @param file
     * @return
     */
    @Override
    public String uploadBookFile(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String yourBucketName = ConstantPropertiesUtil.BUCKETNAME;

        try {
            //1.获取上传文件MultipartFile file
            //2.获取上传文件的名称，获取上传文件输入流
            String filename = file.getOriginalFilename();
            //在文件名之前添加uuid值，获取上传文件输入流,相同文件使用不同名称进行区分
            String uuid = UUID.randomUUID().toString();
            filename = uuid + filename;

            //获取当前日期并将2019.04.03==》 2019/04/13,
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //拼接文件完整路径
            //2019/04/03/sdfsd01.txt
            filename = "book/"+filePath+"/"+filename;

            InputStream inputStream = file.getInputStream();

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。上传的bucket名字，文件的路径，文件内容
            ossClient.putObject(yourBucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回上传之后oss存储路径
            //https://xueyuan-edudome111.oss-cn-hangzhou.aliyuncs.com/1.txt
            String url = "https://"+yourBucketName+"."+endpoint+"/"+filename;
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 添加书籍，同时添加书籍类型关联
     * @param queryBook
     * @return
     */
    @Override
    public boolean addBook(QueryBook queryBook) {
        //将信息提取出来
        ReadbookBook readbookBook = new ReadbookBook();
        readbookBook.setName(queryBook.getName());
        readbookBook.setAuthor(queryBook.getAuthor());
        readbookBook.setIntro(queryBook.getIntro());
        readbookBook.setUrl(queryBook.getUrl());
        readbookBook.setPublisher(queryBook.getPublisher());

        ReadbookType readbookType = new ReadbookType();
        readbookType.setName(queryBook.getTypeName());
        //readbookType.setNickname(queryBook.getTypeNickname());

        //判断书籍信息是否完善
        if (StringUtils.isEmpty(readbookBook.getName()) && StringUtils.isEmpty(readbookBook.getAuthor()) && StringUtils.isEmpty(readbookBook.getIntro()) && StringUtils.isEmpty(readbookBook.getPublisher())&& StringUtils.isEmpty(readbookType.getName()) /**&& StringUtils.isEmpty(readbookType.getNickname())*/){
            return false;
        }

        //将书籍信息添加的书籍表中
        int insert = baseMapper.insert(readbookBook);
        //查书籍id
        QueryWrapper<ReadbookBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",readbookBook.getName());
        queryWrapper.eq("author",readbookBook.getAuthor());
        queryWrapper.eq("intro",readbookBook.getIntro());
        queryWrapper.eq("publisher",readbookBook.getPublisher());
        queryWrapper.eq("url",readbookBook.getUrl());
        ReadbookBook readbookBookById = baseMapper.selectOne(queryWrapper);

        //根据根据名称或别名找出id
        Integer typeId = readbookTypeService.selectTypeId(readbookType);
        if (typeId == null){
            return false;
        }

        //为关联表添加信息
        ReadbookBookType readbookBookType = new ReadbookBookType();
        readbookBookType.setBookId(readbookBookById.getId());
        readbookBookType.setTypeId(typeId);
        boolean save = readbookBookTypeService.save(readbookBookType);
        return save;
    }

    /**
     * 更新
     * @param queryBook
     * @return
     */
    @Override
    public boolean updateBook(QueryBook queryBook) {
        //将信息提取出来
        ReadbookBook readbookBook = new ReadbookBook();
        readbookBook.setId(queryBook.getBookId());
        readbookBook.setName(queryBook.getName());
        readbookBook.setAuthor(queryBook.getAuthor());
        readbookBook.setIntro(queryBook.getIntro());
        readbookBook.setPublisher(queryBook.getPublisher());

        ReadbookBookType readbookBookType = new ReadbookBookType();
        readbookBookType.setTypeId(queryBook.getTypeId());
        readbookBookType.setBookId(queryBook.getBookId());

        //判断两个id是否存在
        if (StringUtils.isEmpty(readbookBook.getId()) && StringUtils.isEmpty(readbookBookType.getBookId())){
            return false;
        }
        int i = baseMapper.updateById(readbookBook);
        int updateByBook = readbookBookTypeService.updateByBook(readbookBookType);

        return i > 0 && updateByBook > 0;
    }

    /**
     * 多条件分页查询
     * @param readbook
     * @return
     */
    @Override
    public IPage<ReadbookBook> pageListCondition(@PathVariable Long page, @PathVariable Long limit , QueryBook readbook) {
        //创建page对象，传递参数
        Page<ReadbookBook> eduBookPage = new Page<>(page,limit);

        //1.判断readbookuser是否为空
        if (StringUtils.isEmpty(readbook)) {
            IPage<ReadbookBook> readbookUserIPage = baseMapper.selectPage(eduBookPage, null);
            return readbookUserIPage;
        }
        //2.不为空，获取条件值
        String author = readbook.getAuthor();
        Integer bookId = readbook.getBookId();
        String intro = readbook.getIntro();
        String name = readbook.getName();
        String publisher = readbook.getPublisher();
        Integer typeId = readbook.getTypeId();
        String typeName = readbook.getTypeName();
        //String typeNickname = readbook.getTypeNickname();
        //Date firstCreateTime = readbook.getFirstCreateTime();
        //Date lastCreateTime = readbook.getLastCreateTime();

        //创建条件构造器
        QueryWrapper<ReadbookBook> queryWrapper = new QueryWrapper<>();

        //先判断条件是否为空，再加入构造器
        //作者
        if (!StringUtils.isEmpty(author)){
            queryWrapper.like("author",author);
        }
        //书id
        if (!StringUtils.isEmpty(bookId)){
            queryWrapper.eq("id",bookId);
        }
        //简介
        if (!StringUtils.isEmpty(intro)){
            queryWrapper.like("intro",intro);
        }
        //书名
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        //出版社
        if (!StringUtils.isEmpty(publisher)){
            queryWrapper.like("publisher",publisher);
        }

        //最早创建时间
        //if (!StringUtils.isEmpty(firstCreateTime)){
        //    queryWrapper.ge("create_time",firstCreateTime);
        //}
        ////最晚创建时间
        //if (!StringUtils.isEmpty(lastCreateTime)){
        //    queryWrapper.le("create_time",lastCreateTime);
        //}

        //与或只能查2个是否满足条件
        if(!StringUtils.isEmpty(typeId)) {
            //根据类型信息获取书籍id
            // 1.类型id
            ReadbookBookType readbookBookType = new ReadbookBookType();
            readbookBookType.setTypeId(typeId);
            Page<ReadbookBookType> eduBookTypePage = new Page<>(page, limit);
            IPage<ReadbookBookType> readbookBookTypeIPage = readbookBookTypeService.pageListCondition(eduBookTypePage, readbookBookType);

            List<ReadbookBookType> records = readbookBookTypeIPage.getRecords();

            for (ReadbookBookType readbookBookType2 : records) {
                Integer typeId1 = readbookBookType2.getTypeId();
                queryWrapper.eq("id", typeId1);
            }

            if (!StringUtils.isEmpty(typeName)){
            //2.名称，别名
            ReadbookType readbookType = new ReadbookType();
            readbookType.setName(typeName);
            //readbookType.setNickname(typeNickname);
            Integer selectTypeId = readbookTypeService.selectTypeId(readbookType);
            ReadbookBookType readbookBookType1 = new ReadbookBookType();
            readbookBookType1.setTypeId(selectTypeId);
            IPage<ReadbookBookType> bookTypeIPage = readbookBookTypeService.pageListCondition(eduBookTypePage, readbookBookType1);

            //通过书籍id查找
            List<ReadbookBookType> records1 = bookTypeIPage.getRecords();

             for (ReadbookBookType readbookBookType2 : records1) {
                 Integer typeId1 = readbookBookType2.getTypeId();
                 queryWrapper.eq("id", typeId1);
             }
            }
        }
        IPage<ReadbookBook> readbookBookIPage = baseMapper.selectPage(eduBookPage, queryWrapper);
        return readbookBookIPage;
    }

    @Override
    public String downloadBookFile(QueryBook queryBook) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String yourBucketName = ConstantPropertiesUtil.BUCKETNAME;
        //<yourObjectName>表示从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。

        ReadbookBook readbookBook = baseMapper.selectById(queryBook.getBookId());
        String path = readbookBook.getUrl().substring(49);
        String objectName = path;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            //OSSObject ossObject = ossClient.getObject(yourBucketName, objectName);

            File file = new File(readbookBook.getName());
            String canonicalPath = file.getCanonicalPath()+".epub";

            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(yourBucketName, objectName), file);

            // 关闭OSSClient。
            ossClient.shutdown();

            return canonicalPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
