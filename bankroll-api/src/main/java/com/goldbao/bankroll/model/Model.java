package com.goldbao.bankroll.model;

import com.goldbao.bankroll.model.enums.EnumDr;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Model {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "add_time")
	private Date addTime;

	private EnumDr dr;

	@Version
	private Integer ver;
	 /**
     * 主键，自增
     */
	public Long getId() {
		return id;
	}
	 /**
     * 主键，自增
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
     * 删除标识
     */
	public EnumDr getDr() {
		return dr;
	}
	
	 /**
     * 删除标识
     */
	public void setDr(EnumDr dr) {
		this.dr = dr;
	}

	/**
	 * 乐观锁
	 */
	public Integer getVer() {
		return ver;
	}
	/**
	 * 乐观锁
	 */
	public void setVer(Integer ver) {
		this.ver = ver;
	}
}
