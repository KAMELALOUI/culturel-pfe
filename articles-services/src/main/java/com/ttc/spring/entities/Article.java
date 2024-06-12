package com.ttc.spring.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

@Entity
@Table(name = "articles")
public class Article {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  
	  private String title;

	  
	  @Column(nullable=false, length= 3000)
	  private String content;
	  
	  
	  @Column(nullable=false, length= 3000)
	  private LocalDateTime createdAt = LocalDateTime.now();
	  
	  
	  @Column(nullable=false, length= 3000)
	  private String imageURL;
	  
	  
	  private String uid;
	  
	  
	  private String author = "";
	  
	  
	  


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public Article() {
		super();
	}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	  
}
