package com.example.restprototype.web.resources.common;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 入力チェックエラー情報クラス
 */
@Data
public class ValidateErrorInfo extends ErrorInfo {

	/** 入力チェック項目別エラーリスト */
	private List<Detail> errors = new ArrayList<>();

	/**
	 * エラー詳細設定
	 * @param target
	 * @param msg
	 */
	public void addDetails(String target, String msg) {
		errors.add(new Detail(target, msg));
	}

	/**
	 * 項目ごとのエラー詳細
	 * 内部クラス
	 */
	@Data
	@AllArgsConstructor
	private static class Detail {
		/** 入力項目 */
		private String target;
		/** エラーメッセージ */
		private String msg;
	}

}
