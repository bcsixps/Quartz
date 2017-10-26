package com.panshuai.redis;

import java.io.Serializable;

/**
 * ClassName:Doc
 * 
 * @author shujun.lee
 * @version
 * @since Ver 1.1
 * @Date 2014-3-3
 */
public class Doc  implements Serializable {

	private static final long serialVersionUID = -7627147883155782796L;

	private String modelType;// 栏目模型
	public Doc(String modelType, String title, String shortTitle, String shortImage, String keywords,
			String description, String priority, String source, String author, String permitCommentFlag,
			String publishTime, String auditStatus) {
		super();
		this.modelType = modelType;
		this.title = title;
		this.shortTitle = shortTitle;
		this.shortImage = shortImage;
		this.keywords = keywords;
		this.description = description;
		this.priority = priority;
		this.source = source;
		this.author = author;
		this.permitCommentFlag = permitCommentFlag;
		this.publishTime = publishTime;
		this.auditStatus = auditStatus;
	}
	@Override
	public String toString() {
		return "Doc [modelType=" + modelType + ", title=" + title + ", shortTitle=" + shortTitle + ", shortImage="
				+ shortImage + ", keywords=" + keywords + ", description=" + description + ", priority=" + priority
				+ ", source=" + source + ", author=" + author + ", permitCommentFlag=" + permitCommentFlag
				+ ", publishTime=" + publishTime + ", auditStatus=" + auditStatus + "]";
	}
	private String title; // 网页标题
	private String shortTitle;// 简略标题(网页标题)
	private String shortImage;// 缩略图
	private String keywords;// 网页关键字
	private String description;// 网页描述
	private String priority;// 优先级
	private String source;// 来源
	private String author;// 作者
	private String permitCommentFlag;// 是否允许评论 "1默认", "2允许","3不允许"
	private String publishTime;// 发布时间
	private String auditStatus;// 审核状态
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getShortImage() {
		return shortImage;
	}
	public void setShortImage(String shortImage) {
		this.shortImage = shortImage;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPermitCommentFlag() {
		return permitCommentFlag;
	}
	public void setPermitCommentFlag(String permitCommentFlag) {
		this.permitCommentFlag = permitCommentFlag;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}


	
}