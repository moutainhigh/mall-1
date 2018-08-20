package com.yunxin.core.meta;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public enum SimpleDateFormatEnum {
	sdf("yyyy-MM-dd"), 
	sdf2("yyyyMMdd"),
	sdf3("yyMMdd"),
	sdf4("yyyy-MM-dd HH:mm:ss"),
	sdf5("yyyyMMddHHmmss"),
	sdf6("yyyy年MM月dd日 HH:mm:ss"),
	sdf7("yyyy年MM月dd日 HH:mm"),
	sdf8("yyyy年MM月dd日"),
	sdf9("MM.dd"),
	sdf10("yyMMddHHmmss"),
	sdf11("YYYYMMddHHmmss"),
	sdf12("HH:mm:ss"),
	sdf13("yyyy年MM月"),
	sdf14("yyyyMM"),
	sdf15("yyMMddHHmmssSSS"),
	sdf16("yyyy-MM"),
	sdf17("MMddHHmmss");

	private final String pattern;

	private SimpleDateFormatEnum(String pattern) {
		this.pattern = pattern;
	}

	public java.util.Date parse(String source) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(source);
	}

	public String format(java.util.Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	public SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat(pattern);
	}
}
