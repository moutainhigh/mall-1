package com.yunxin.core.web;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class HtmlEscape {

	public static String decodeHtml(String html) {
		if(StringUtils.isNotBlank(html)){
			html = html.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
					.replaceAll("&apos;", "\'").replaceAll("&quot;", "\"").replaceAll("&nbsp;", " ")
					.replaceAll("&copy;", "@").replaceAll("&reg;", "?");
		}
		return html;
	}
	public static void main(String[] args) {
		String html="<img alt=\"\" src=\"../data/pics/commodity/imagebrowser/aaa.jpg\" />"
				+ "<img alt=\"\" src=\"../data/pics/commodity/imagebrowser/bbb.jpg\" />sadsads";
		getSrcs(html);
	}
	
	public static List<String> getSrcs(String html){
//		if(LogicUtils.isNotNullAndEmpty(html)){
//			String srcs[]=html.split("src=\"");
//			if(LogicUtils.isNotNullAndEmpty(srcs)){
//				List<String> rs=new ArrayList<String>();
//				for(String src:srcs){
//					String s=src.split("\"")[0];
//					System.out.println(s);
//				}
//			}
//
//		}
		return null;
	}
}
