package com.example.restprototype.web.input;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST APIへのリクエストオブジェクト
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceReq {
	/** ID */
	private String id;
	/** 名前 */
	private String name;
	/** とある日付 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate hogeDate;
}
