package com.wenhua.readbook.datagenerator;

import com.wenhua.readbook.datagenerator.entity.*;
import com.wenhua.readbook.datagenerator.handler.dataachieve.RandomReadbook;
import com.wenhua.readbook.datagenerator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/19
 * @version: 1.0
 */
@ComponentScan
@Component
public class AddData {
    @Autowired
    private RandomReadbook randomReadbook;

    @Autowired
    private ReadbookArticleService readbookArticleService;
    @Autowired
    private ReadbookBookService readbookBookService;
    @Autowired
    private ReadbookBookrackService readbookBookrackService;
    @Autowired
    private ReadbookBookTypeService readbookBookTypeService;
    @Autowired
    private ReadbookCommentService readbookCommentService;
    @Autowired
    private ReadbookCommentArticleService readbookCommentArticleService;
    @Autowired
    private ReadbookEmblemService readbookEmblemService;
    @Autowired
    private ReadbookEvaluateService readbookEvaluateService;
    @Autowired
    private ReadbookInfoService readbookInfoService;
    @Autowired
    private ReadbookPermissionRoleService readbookPermissionRoleService;
    @Autowired
    private ReadbookPermissionService readbookPermissionService;

    //@Autowired
    //private ReadbookMenuService readbookMenuService;
    @Autowired
    private ReadbookRoleService readbookRoleService;
    @Autowired
    private ReadbookRoleUserService readbookRoleUserService;
    //@Autowired
    //private ReadbookRoleMenuService readbookRoleMenuService;
    @Autowired
    private ReadbookTypeService readbookTypeService;
    @Autowired
    private ReadbookUserService readbookUserService;
    @Autowired
    private ReadbookUserEmblemService readbookUserEmblemService;



    @Autowired
    private ReadbookArticle readbookArticle;
    @Autowired
    private ReadbookBook readbookBook;
    @Autowired
    private ReadbookBookrack readbookBookrack;
    @Autowired
    private ReadbookBookType readbookBookType;
    @Autowired
    private ReadbookComment readbookComment;
    @Autowired
    private ReadbookCommentArticle readbookCommentArticle;
    @Autowired
    private ReadbookEmblem readbookEmblem;
    @Autowired
    private ReadbookEvaluate readbookEvaluate;
    @Autowired
    private ReadbookInfo readbookInfo;

    @Autowired
    private ReadbookPermission readbookPermission;

    @Autowired
    private ReadbookPermissionRole readbookPermissionRole;
    //@Autowired
    //private ReadbookMenu readbookMenu;
    @Autowired
    private ReadbookRole readbookRole;

    @Autowired
    private ReadbookRoleUser readbookRoleUser;
    //@Autowired
    //private ReadbookRoleMenu readbookRoleMenu;
    @Autowired
    private ReadbookType readbookType;
    @Autowired
    private ReadbookUser readbookUser;
    @Autowired
    private ReadbookUserEmblem readbookUserEmblem;



    /**
     * 14.生成user表数据
     * 测试通过
     * @return
     */
    public Boolean AddUser(){
        String username = randomReadbook.RandomName();
        String password = randomReadbook.RandomString();
        String telphone = randomReadbook.RandomString();
        String email = randomReadbook.RandomEmail();
        Boolean sex = true;
        Integer age = randomReadbook.RandomHundredNumber();
        //Integer roleId = 1;
        String avatar = randomReadbook.RandomString();

        readbookUser.setUsername(username)
                .setPassword(password).setTelphone(telphone)
                .setEmial(email).setSex(sex).setAge(age)
                .setAvatar(avatar);

        readbookUserService.save(readbookUser);

        return true;
    }

    /**
     * 2.生成ReadbookBook
     * 测试通过
     */
    public Boolean AddReadbookBook(){
        String name = randomReadbook.RandomString();
        String author = randomReadbook.RandomString();
        String intro = randomReadbook.RandomString();
        String publisher = randomReadbook.RandomString();


        readbookBook.setName(name).setAuthor(author)
                .setIntro(intro).setPublisher(publisher);

        readbookBookService.save(readbookBook);

        return true;
    }

    /**
     * 10.生成ReadbookMenu
     * 成功
     */
    //public Boolean AddReadbookMenu(){
    //    String name = randomReadbook.RandomString();
    //    readbookMenu.setName(name);
    //    readbookMenuService.save(readbookMenu);
    //    return true;
    //}

    /**
     * 5.生成ReadbookComment
     * 测试成功
     */
    public Boolean AddReadbookComment(){
        String comment = randomReadbook.RandomString();

        readbookComment.setComment(comment);

        readbookCommentService.save(readbookComment);

        return true;
    }

    /**
     * 1.生成ReadbookArticle
     * 在AddReadbookBook后使用
     * 通过
     */
    public Boolean AddReadbookArticle(){
        String title = randomReadbook.RandomString();
        String content = randomReadbook.RandomString();
        String contentText = randomReadbook.RandomString();
        //String summary = randomReadbook.RandomString();
        Integer bookId = randomReadbook.RandomNumber();
        Integer page = randomReadbook.RandomNumber();
        Integer view = randomReadbook.RandomNumber();
        Integer praise = randomReadbook.RandomHundredNumber();

        readbookArticle.setTitle(title).setBookId(bookId)
                .setContentText(contentText)
                .setPage(page).setContent(content)
                .setView(view).setPraise(praise)
        .setBookId(readbookBook.getId());

        readbookArticleService.save(readbookArticle);

        return true;
    }

    /**
     * 9.生成ReadbookInfo
     * 在readbookUser，readbookBook，readbookArticle之后生成
     */
    public Boolean AddReadbookInfo(){
        Integer schedule = randomReadbook.RandomHundredNumber();
        Integer userId = readbookUser.getId();
        Integer bookId = readbookBook.getId();
        Integer articleId = readbookArticle.getId();

        readbookInfo.setSchedule(schedule).setArticleId(articleId)
                .setBookId(bookId).setUserId(userId);

        readbookInfoService.save(readbookInfo);
        return true;
    }

    /**
     * 3.生成ReadbookBookrack
     * 在readbookUser，readbookBook之后生成
     */
    public Boolean AddReadbookBookrack(){
        Integer userId = readbookUser.getId();
        Integer bookId = readbookBook.getId();
        readbookBookrack.setBookId(bookId).setUserId(userId);
        readbookBookrackService.save(readbookBookrack);
        return true;
    }

    /**
     * 13.生成ReadbookType
     */
    public Boolean AddReadbookType(){
        String name = randomReadbook.RandomName();
        //String nickname= randomReadbook.RandomName();
        readbookType.setName(name);
        readbookTypeService.save(readbookType);
        return true;
    }

    /**
     * 4.生成ReadbookBookType
     * 在ReadbookType,ReadbookBook之后生成
     */
    public Boolean AddReadbookBookType(){
        Integer bookId = readbookBook.getId();
        Integer typeId = readbookType.getId();
        readbookBookType.setBookId(bookId).setTypeId(typeId);
        readbookBookTypeService.save(readbookBookType);
        return true;
    }

    /**
     * 6.生成ReadbookCommentArticle
     * 在ReadbookComment,ReadbookArticle之后
     */
    public Boolean AddReadbookCommentArticle(){
        Integer commentId = readbookComment.getId();
        Integer articleId = readbookArticle.getId();
        readbookCommentArticle.setArtilceId(articleId).setCommentId(commentId);
        readbookCommentArticleService.save(readbookCommentArticle);
        return true;
    }

    /**
     * 7.生成ReadbookEmblem
     */
    public Boolean AddReadbookEmblem(){
        String name = randomReadbook.RandomName();
        String introduce= randomReadbook.RandomString();

        readbookEmblem.setName(name).setIntroduce(introduce);
        readbookEmblemService.save(readbookEmblem);
        return true;
    }

    /**
     * 11.生成ReadbookRole
     */
    public Boolean AddReadbookRole(){
        return true;
    }

    /**
     * 此表没有设置外键
     * 12.生成ReadbookRoleMenu
     * ReadbookMenu,ReadbookRole之后
     */
    //public Boolean AddReadbookRoleMenu(){
    //    Integer roleId = 1;
    //    Integer menuId = readbookMenu.getId();
    //    readbookRoleMenu.setMenuId(menuId).setRoleId(roleId);
    //    readbookRoleMenuService.save(readbookRoleMenu);
    //    return true;
    //}


    /**
     * 此表没有设置外键
     * 15.生成ReadbookUserEmblem
     * ReadbookUser,ReadbookEmblem之后
     */
    public Boolean AddReadbookUserEmblem(){
        Integer userId = readbookUser.getId();
        Integer emblemId = readbookEmblem.getId();
        readbookUserEmblem.setUserId(userId).setEmblemId(emblemId);
        readbookUserEmblemService.save(readbookUserEmblem);
        return true;
    }


    /**
     * 8.生成ReadbookEvaluate,未成功
     */
    public Boolean AddReadbookEvaluate(){
        String text = randomReadbook.RandomString();
        Integer bookId = randomReadbook.RandomNumber();
        Integer userId = randomReadbook.RandomNumber();
        //String star = "1";
        readbookEvaluate.setText(text).setBookId(bookId).setUserId(userId);
        readbookEvaluateService.save(readbookEvaluate);
        return true;
    }
}
