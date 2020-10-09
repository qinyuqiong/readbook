package com.wenhua.readbook.bookservice.entity.query;

import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.ReadbookType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 传输实现类
 * @author LENOVO
 * @version 1.0 2020/6/14
 */
@Data
public class QueryBook {

    @ApiModelProperty(value = "书籍id")
    private Integer bookId;

    @ApiModelProperty(value = "书籍名称")
    private String name;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "出版社名")
    private String publisher;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "创建最早时间")
    private Date firstCreateTime;

    @ApiModelProperty(value = "创建最晚时间")
    private Date lastCreateTime;

    @ApiModelProperty(value = "类型名称")
    private String TypeName;

    @ApiModelProperty(value = "别名")
    private String TypeNickname;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getFirstCreateTime() {
        return firstCreateTime;
    }

    public void setFirstCreateTime(Date firstCreateTime) {
        this.firstCreateTime = firstCreateTime;
    }

    public Date getLastCreateTime() {
        return lastCreateTime;
    }

    public void setLastCreateTime(Date lastCreateTime) {
        this.lastCreateTime = lastCreateTime;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypeNickname() {
        return TypeNickname;
    }

    public void setTypeNickname(String typeNickname) {
        TypeNickname = typeNickname;
    }

    public QueryBook(ReadbookBook readbookBook, ReadbookType readbookType) {
        this.bookId = readbookBook.getId();
        this.name = readbookBook.getName();
        this.author = readbookBook.getAuthor();
        this.intro = readbookBook.getIntro();
        this.publisher = readbookBook.getPublisher();
        this.typeId = readbookType.getId();
        this.firstCreateTime = readbookBook.getCreateTime();
        this.lastCreateTime = readbookBook.getUpdateTime();
        TypeName = readbookType.getName();
        //TypeNickname = readbookType.getNickname();
    }

}
