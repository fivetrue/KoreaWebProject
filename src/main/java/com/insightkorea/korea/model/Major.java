package com.insightkorea.korea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "major")
public class Major extends BaseEntity{
	
	@Column(name="name")
	private String majorName;

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

}
