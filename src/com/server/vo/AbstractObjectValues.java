package com.server.vo;

import java.util.Date;

import org.bson.types.ObjectId;

public class AbstractObjectValues {
	
	private ObjectId  id ;
	
	private Date createDate ;
	
	private Date updateDate ;

	private String createdBy;
	
	private String updatedBy;
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getGuid() {
		String guid = null;
		if(getId()!=null){
			guid = getId().toString();
		}
		return guid;
	}

}
