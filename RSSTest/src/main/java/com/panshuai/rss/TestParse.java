package com.panshuai.rss;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;


//http://zmfkplj.iteye.com/blog/461398
public class TestParse {
	public static void main(String[] args) {
		TestParse test = new TestParse();
		test.parseRss();
	}
	public void parseRss() {
//		String rss = "http://news.qq.com/newsgn/rss_newsgn.xml";
//		String rss = "http://www.cngold.org/data/rss/3683.xml";
		String rss = "http://rss.sina.com.cn/news/society/focus15.xml";
		try {
			URL url = new URL(rss);
			// 读取Rss源   
			XmlReader reader = new XmlReader(url);		
			System.out.println("Rss源的编码格式为：" + reader.getEncoding());
			SyndFeedInput input = new SyndFeedInput();
			// 得到SyndFeed对象，即得到Rss源里的所有信息   
			SyndFeed feed = input.build(reader);	
			// 得到Rss新闻中子项列表   
			List entries = feed.getEntries();
			// 循环得到每个子项信息   
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);								
				// 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分   
				System.out.println("标题：" + entry.getTitle());
				System.out.println("连接地址：" + entry.getLink());
				System.out.println("----------------------------"+i+"------------------------------------");
				Document doc= Jsoup.parse(new URL(entry.getLink()), 0);
//				Elements elements =  doc.getElementsByClass("qq_article").select(".bd");
//				String elements = doc.select(".Cnt-Main-Article-QQ p").html();
				String elements = doc.select("#artibody p").html();
				System.out.println(elements);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				// 获取图片url
				Set<String> srcs = getImgStr(elements);
				if(srcs.size()>0){
					for(String src :srcs ){
						System.out.println(src);
					}
				}
				
				System.out.println("-----------------------------"+i+"-------------------------------------------");
				SyndContent description = entry.getDescription();
				System.out.println("标题简介：" + description.getValue());
				Date publishedDate = entry.getPublishedDate();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String formatdate = publishedDate == null ? null :  format.format(entry.getPublishedDate());
				System.out.println("发布时间：" + entry.getPublishedDate());
				// 以下是Rss源可先的几个部分   
				System.out.println("标题的作者：" + entry.getAuthor());				
				// 此标题所属的范畴   
				List categoryList = entry.getCategories();
				if (categoryList != null) {
					for (int m = 0; m < categoryList.size(); m++) {
						SyndCategory category = (SyndCategory) categoryList.get(m);
						System.out.println("此标题所属的范畴：" + category.getName());
					}
				}							
				// 得到流媒体播放文件的信息列表   
				List enclosureList = entry.getEnclosures();
				if (enclosureList != null) {
					for (int n = 0; n < enclosureList.size(); n++) {
						SyndEnclosure enclosure = (SyndEnclosure) enclosureList.get(n);
						System.out.println("流媒体播放文件：" + entry.getEnclosures());
					}
				}
				System.out.println();
			}
			System.out.println(entries.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

}