package com.example.restprototype.web.input;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST APIへのリクエストオブジェクト
 * Bean Validationまわりのアノテーションあり
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceReq2 {
	/** ID */
	@NotNull
	private String id;
	/** 名前 */
	@NotNull
	private String name;
	/** とある日付 */
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate hogeDate;
}
