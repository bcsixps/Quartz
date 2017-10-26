package com.panshuai.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestParseHtml {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://cai.163.com/article/17/0930/13/CVJ9RP3R00052DT2.html";
//		String url ="http://news.qq.com/a/20170907/018645.htm";
//		Document doc= Jsoup.parse(new URL(url), 0);
		Document doc = Jsoup.connect(url).timeout(1000).get();
//		String content = DynamicHtmlSelect.htmlUnitSelect(url,2000);
//		Document doc = Jsoup.parse(content);
		
		//1�õ�����
		Elements elements = doc.select("div#endText");
		
		System.out.println(elements.toString());
		System.out.println("---------------------------------------------------------------------------");
		
		System.out.println(elements.select("div:last-child").toString());
//		for(Element element :elements ){
//			String href = element.attr("href");
//			System.out.println("element"+element.toString());
//		}
		
		
//		System.out.println("-------------------------------------------------------------");
//		//2��ȡ��ҳ
//		Elements listPage =  elements.select(".listPage");
//		System.out.println(listPage.toString());
		
//		System.out.println(filter(elements));
//		for( String src : getImgStr(elements)){
//			System.out.println(src);
//		}
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
	 
	 private static String filter(String text) {
			if(!(text == null || "".equals(text))) {
				Pattern pattern = Pattern.compile("<script.*?>.*?</script\\s*?>", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(text);
				text = matcher.replaceAll("");
				
				pattern = Pattern.compile("<style.*?>.*?</style\\s*?>", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.UNICODE_CASE);
				matcher = pattern.matcher(text);
				text = matcher.replaceAll("");
				
				pattern = Pattern.compile("<!--.*?-->", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.UNICODE_CASE);
				matcher = pattern.matcher(text);
				text = matcher.replaceAll("");
				text = text.replaceAll("��", "");
				text = text.replaceAll("\\s+<", "<").replaceAll(">\\s+", ">");
			}
			return text;
		}
	
	
}
