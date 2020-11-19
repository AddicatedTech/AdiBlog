/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 20:01:26
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 20:15:20
 */
package com.adi.vo;

public class BlogQuery {
    
    private String title;
    private Long typeId;
    private boolean recommend;
    
    public BlogQuery(){
        
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

	
    
}
