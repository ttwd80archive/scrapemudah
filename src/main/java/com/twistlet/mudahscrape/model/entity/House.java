package com.twistlet.mudahscrape.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "scrapemudah", type = "house")
public class House {

	@Id
	private String url;

	@Field(type = FieldType.Date, format = DateFormat.date)
	private String postedDate;

	@Field(type = FieldType.String)
	private String title;

	@Field(type = FieldType.Long)
	private long price;

	@Field(type = FieldType.String)
	private String state;

	@Field(type = FieldType.String)
	private String district;

	@Field(type = FieldType.String)
	private String propertyType;

	@Field(type = FieldType.String)
	private String titleType;

	@Field(type = FieldType.Integer)
	private Integer bedroom;

	@Field(type = FieldType.Integer)
	private Integer bathroom;

	@Field(type = FieldType.Boolean)
	private Boolean bumi;

	@Field(type = FieldType.String)
	private String postedBy;

	@Field(type = FieldType.String)
	private String content;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(final String postedDate) {
		this.postedDate = postedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(final long price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(final String district) {
		this.district = district;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(final String propertyType) {
		this.propertyType = propertyType;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(final String titleType) {
		this.titleType = titleType;
	}

	public Integer getBedroom() {
		return bedroom;
	}

	public void setBedroom(final Integer bedroom) {
		this.bedroom = bedroom;
	}

	public Integer getBathroom() {
		return bathroom;
	}

	public void setBathroom(final Integer bathroom) {
		this.bathroom = bathroom;
	}

	public Boolean getBumi() {
		return bumi;
	}

	public void setBumi(final Boolean bumi) {
		this.bumi = bumi;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(final String postedBy) {
		this.postedBy = postedBy;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
