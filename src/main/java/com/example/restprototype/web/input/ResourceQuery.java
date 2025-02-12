package com.example.restprototype.web.input;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * リソース検索用オブジェクト
 */
@Data
public class ResourceQuery {
	/** 名前 */
	private String name;
	/** とある日付 */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate hogeDate;
}
