/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-17 16:12:49
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 07:16:05
 */
package com.adi.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "name cannot be empty")  // 对此字段进行后端验证 ，同时应当在对应controller层进行编码
    private String name;

    // blog 与 type 是多对一，反过来 type 是1
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", name=" + name + "]";
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
