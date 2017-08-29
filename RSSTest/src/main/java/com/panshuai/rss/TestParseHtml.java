package com.panshuai.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TestParseHtml {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://news.qq.com/a/20170828/106675.htm";
		Document doc= Jsoup.parse(new URL(url), 0);
//		Elements elements =  doc.getElementsByClass("qq_article").select(".bd");
		String elements = doc.select(".Cnt-Main-Article-QQ p").html();
		
		
		System.out.println(elements.toString());
		for( String src : getImgStr(elements)){
			System.out.println(src);
		}
		
		
//		System.out.println(doc.toString());
	}
	
	 public static Set<String> getImgStr(String htmlStr) {
	        Set<String> pics = new HashSet<String>();
	        String img = "";
	        Pattern p_image;
	        Matcher m_image;
	        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //ͼƬ���ӵ�ַ
	        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
	        p_image = Pattern.compile
	                (regEx_img, Pattern.CASE_INSENSITIVE);
	        m_image = p_image.matcher(htmlStr);
	        while (m_image.find()) {
	            // �õ�<img />����
	            img = m_image.group();
	            // ƥ��<img>�е�src����
	            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
	            while (m.find()) {
	                pics.add(m.group(1));
	            }
	        }
	        return pics;
	    }
	
	
}
