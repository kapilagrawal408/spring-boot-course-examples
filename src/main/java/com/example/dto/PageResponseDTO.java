package com.example.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageResponseDTO<T> {
	private List<T> content;
	private int totalPages;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	
	@JsonProperty("isFirstPage")		//if not used then JSON key will be firstPage 
	private boolean isFirstPage;		//the setter method is setFirstPage() and getter is isFirstPage()
	@JsonProperty("isLastPage")  	//if not used then JSON key will be lastPage
	private boolean isLastPage;		//the setter method is setFirstPage() and getter is isFirstPage()
	
	private int totalRecordsThisPage;
	private long totalRecords;
}
