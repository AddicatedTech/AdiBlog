/*
 * @Descripttion:  bolg 实体类属性相关
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-17 16:07:56
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-20 09:57:21
 */
package com.adi.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity // 被hibernate所识别为实体类
@Table(name = "t_blog") // 指定对应的表名 不进行指定的话会直接生成与实体类相同的表
public class Blog {

    @Id // 指定主键
    @GeneratedValue // 设置主键自增
    private Long id;

    private String title;
    @Basic(fetch = FetchType.LAZY)  // 设置加载类型为懒加载，用到的时候才加载，提高查询效率
    @Lob  // 标记为长文本
    private String content; // 内容
    
    private String firstPicture; // 首图
    private String flag;
    private Integer views; // 阅读量
    private boolean appreciation; // 赞赏是否开启
    private boolean shareStatement; // 转载声明是否开启
    private boolean commentabled; // 是否可评论
    private boolean published; // 发布状态，发布，或者是草稿
    private boolean recommend; // 推荐状态，推荐或者不推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @Transient  // 添加之后该属性不会涉及到数据库的操作
    private String tagIds;  // 标签集

    // blog 对 type 是多对一的关系,即，blog在关系中是多的一段，
    // 多的一方作为维护端
    // many 作为关系维护端 type作为被维护端
    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})  // 新增加一个tag 同时会级联新增给tag表中添加tag信息
    private List<Tag> tags = new ArrayList<>();


    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

 

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog [appreciation=" + appreciation + ", commentabled=" + commentabled + ", content=" + content
                + ", createTime=" + createTime + ", firstPicture=" + firstPicture + ", flag=" + flag + ", id=" + id
                + ", published=" + published + ", recommend=" + recommend + ", shareStatement=" + shareStatement
                + ", title=" + title + ", updateTime=" + updateTime + ", views=" + views + "]";
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

}
