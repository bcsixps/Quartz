package com.panshuai.rss;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

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
		//String rss = "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0]http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0";
//		String rss = "http://news.qq.com/newsgn/rss_newsgn.xml";
		String rss = "http://www.cngold.org/data/rss/3683.xml";
		try {
			URL url = new URL(rss);
			// ��ȡRssԴ   
			XmlReader reader = new XmlReader(url);		
			System.out.println("RssԴ�ı����ʽΪ��" + reader.getEncoding());
			SyndFeedInput input = new SyndFeedInput();
			// �õ�SyndFeed���󣬼��õ�RssԴ���������Ϣ   
			SyndFeed feed = input.build(reader);
			//System.out.println(feed);			
			// �õ�Rss�����������б�   
			List entries = feed.getEntries();
			// ѭ���õ�ÿ��������Ϣ   
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);								
				// ���⡢���ӵ�ַ�������顢ʱ����һ��RssԴ�����������ɲ���   
				System.out.println("���⣺" + entry.getTitle());
				System.out.println("���ӵ�ַ��" + entry.getLink());
				SyndContent description = entry.getDescription();
				System.out.println("�����飺" + description.getValue());
				System.out.println("����ʱ�䣺" + entry.getPublishedDate());	
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String formatdate = format.format(entry.getPublishedDate());
				System.out.println(formatdate);
				// ������RssԴ���ȵļ�������   
				System.out.println("��������ߣ�" + entry.getAuthor());				
				// �˱��������ķ���   
				List categoryList = entry.getCategories();
				if (categoryList != null) {
					for (int m = 0; m < categoryList.size(); m++) {
						SyndCategory category = (SyndCategory) categoryList.get(m);
						System.out.println("�˱��������ķ��룺" + category.getName());
					}
				}							
				// �õ���ý�岥���ļ�����Ϣ�б�   
				List enclosureList = entry.getEnclosures();
				if (enclosureList != null) {
					for (int n = 0; n < enclosureList.size(); n++) {
						SyndEnclosure enclosure = (SyndEnclosure) enclosureList.get(n);
						System.out.println("��ý�岥���ļ���" + entry.getEnclosures());
					}
				}
				System.out.println();
			}
			System.out.println(entries.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}