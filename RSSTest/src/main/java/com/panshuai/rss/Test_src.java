package com.panshuai.rss;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Test_src {
	public static void main(String[] args) {
		String content = " <link rel=\"apple-touch-icon image_src\" href=\"/static/img/quicktime.ico\"><p>dddd<a href=\"\">北极星太阳能光伏网讯</a>: 近日，中广核、华能、国电、华润电力、国家电投几大发电集团官网继续发布在建的光伏项目招标公告，招标仍以<a href=\"\" target=\"_blank\" title=\"光伏组件新闻专题\">光伏组件</a></<p>";
		 getShortImage(content);
		
	}
	
	public static  void getShortImage(String content){
		Document html = Jsoup.parse(content);
		System.out.println(html.toString());
		Elements link_elements = html.getElementsByTag("link");
		link_elements.remove();
		Elements a_elements = html.getElementsByTag("a");
		for(Element element : a_elements){
			element.before(element.text());
			element.remove();
		}
		
        System.out.println(html.toString());		
//		System.out.println(html.body().toString());
		
		
		
		
       
       
	}
	
	public static String text(Elements elements) {
        StringBuilder sb = new StringBuilder();
        for (Element element : elements) {
            if (sb.length() != 0)
                sb.append(" ");
            System.out.println(element.tagName());
            if(element.tagName().equals("a")){
            	System.out.println("22222");
            }
//            sb.append(element.text());
        }
        return sb.toString();
    }

}
