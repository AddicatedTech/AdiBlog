/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 14:02:41
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-20 11:37:14
 */
package com.adi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;
import com.adi.NotFoundException;
import com.adi.dao.BlogRepository;
import com.adi.po.Blog;
import com.adi.po.Type;
import com.adi.vo.BlogQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery,
                    CriteriaBuilder criteriaBuilder) {
                // // query 查询条件的容器对象 ，，criteriaBuilder 设置组合查询的对象
                // List<Predicate> predicate = new ArrayList<>();
                // if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                // // 如果标题不为空也不为null
                // // 将构造好的查询条件添加到 predicate对象中 第一个为比对参数，第二个为实际查询到的值
                // // 同时，需要对其进行处理，，类似 动态sql
                // predicate.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() +
                // "%"));
                // }
                // if (blog.getType().getId() != null) {
                // predicate.add(cb.equal(root.<Type>get("type").get("id"),
                // blog.getType().getId()));
                // }
                // if(blog.isRecommend()){
                // predicate.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                // }
                // // where 后需求的是一个数组，所以需要一个类型转换的过程
                // cq.where(predicate.toArray(new Predicate[predicate.size()]));
                // return null;
                // }
                List<Predicate> predicates = new ArrayList<>();
                if (blog.getTitle() != null && !blog.getTitle().equals("")) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }

        }, pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            // 取不到id，即证明是新增
            blog.setCreateTime(new Date()); // 初始化新增时间
            blog.setUpdateTime(new Date()); // 初始化更新时间
            blog.setViews(0); // 初始化新增浏览数0

        }else{
            blog.setUpdateTime(new Date()); // 初始化更新时间

        }

        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFoundException("不存在该blog");
        }
        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

}
