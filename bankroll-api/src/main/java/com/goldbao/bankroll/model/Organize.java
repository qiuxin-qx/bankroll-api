package com.goldbao.bankroll.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 配资机构表
 * @author shuyu.fang
 *
 */
@Entity
@Table(name = "bk_organize")
@AttributeOverride(name = "id", column = @Column(name = "org_id"))
public class Organize extends Model implements Serializable {
	
	private static final long serialVersionUID = -7621311100777106634L;

	@Column(name = "org_name")
	private String orgName;
	@Column(name = "org_class")
	private String orgClass;
	@Column(name = "card_class")
	private String cardClass;
	@Column(name = "card_image_1")
	private String cardImage1; // 正
	@Column(name = "card_image_2")
	private String cardImage2; // 反
	@Column(name = "card_image_3")
	private String cardImage3; // 全
	@Column(name = "org_contact_name")
	private String orgContactName;
	@Column(name = "org_contact_tel")
	private String orgContactTel;
	@Column(name = "org_contact_qq")
	private String orgContactQQ;
	@Column(name = "org_contact_address")
	private String orgContactAddress;
//	private Long creator
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
//	private Long lastUpdater;
	
	/**
	 * 配资机构名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 配资机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	/**
	 * 机构类型
	 */
	public String getOrgClass() {
		return orgClass;
	}
	/**
	 * 机构类型
	 */
	public void setOrgClass(String orgClass) {
		this.orgClass = orgClass;
	}
	/**
	 * 证件类型
	 */
	public String getCardClass() {
		return cardClass;
	}
	/**
	 * 证件类型
	 */
	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}
	/**
	 * 证件图片（正面）
	 */
	public String getCardImage1() {
		return cardImage1;
	}
	/**
	 * 证件图片（正面）
	 */
	public void setCardImage1(String cardImage1) {
		this.cardImage1 = cardImage1;
	}
	/**
	 * 证件图片（反面）
	 */
	public String getCardImage2() {
		return cardImage2;
	}
	/**
	 * 证件图片（反面）
	 */
	public void setCardImage2(String cardImage2) {
		this.cardImage2 = cardImage2;
	}
	public String getCardImage3() {
		return cardImage3;
	}
	public void setCardImage3(String cardImage3) {
		this.cardImage3 = cardImage3;
	}
	/**
	 * 联系人姓名
	 */
	public String getOrgContactName() {
		return orgContactName;
	}
	/**
	 * 联系人姓名
	 */
	public void setOrgContactName(String orgContactName) {
		this.orgContactName = orgContactName;
	}
	/**
	 * 联系人电话
	 */
	public String getOrgContactTel() {
		return orgContactTel;
	}
	/**
	 * 联系人电话
	 */
	public void setOrgContactTel(String orgContactTel) {
		this.orgContactTel = orgContactTel;
	}
	/**
	 * 联系人QQ
	 */
	public String getOrgContactQQ() {
		return orgContactQQ;
	}
	/**
	 * 联系人QQ
	 */
	public void setOrgContactQQ(String orgContactQQ) {
		this.orgContactQQ = orgContactQQ;
	}
	/**
	 * 联系人地址
	 */
	public String getOrgContactAddress() {
		return orgContactAddress;
	}
	/**
	 * 联系人地址
	 */
	public void setOrgContactAddress(String orgContactAddress) {
		this.orgContactAddress = orgContactAddress;
	}
	/**
	 * 最后更新时间
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 最后更新时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
