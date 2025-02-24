package com.example.restprototype.web.input;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * リソース検索用オブジェクト
 */
@Data
public class ResourceQuery2 {
	/** 名前 */
	@NotNull
	private String name;
	/** とある日付 */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate hogeDate;
}
