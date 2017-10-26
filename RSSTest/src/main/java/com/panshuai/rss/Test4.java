package com.panshuai.rss;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.javascript.host.Document;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temp = "<p class=\"aa bb\">请别说彩票造假~你没中500万是因为没看到【网易彩票】的微信公众号， ID:wangyicaipiao</p>";
		org.jsoup.nodes.Document doc = Jsoup.parse(temp);
		Elements elements = doc.select("p:contains(【网易彩票】的微)");
		System.out.println(elements.toString());
		
		Elements elements1 =doc.select(".aa.bb");
		System.out.println(elements1.toString());
		

	}

}
