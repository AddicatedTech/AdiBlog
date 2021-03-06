/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 09:45:09
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-20 07:03:17
 */
package com.adi.service;

import com.adi.po.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;
public interface TagService {
    
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);
    
    Tag getTagByName(String name);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);

    List<Tag> listTag();

    List<Tag> listTag(String ids);
    
}
