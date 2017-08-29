package com.panshuai.rss;


import java.net.URL;
import java.text.SimpleDateFormat;
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
		//String rss = "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0]http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss&sub=0";
		String rss = "http://news.qq.com/newsgn/rss_newsgn.xml";
//		String rss = "http://www.cngold.org/data/rss/3683.xml";
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
				System.out.println("----------------------------"+i+"------------------------------------");
				Document doc= Jsoup.parse(new URL(entry.getLink()), 0);
//				Elements elements =  doc.getElementsByClass("qq_article").select(".bd");
				String elements = doc.select(".Cnt-Main-Article-QQ p").html();
				
				System.out.println(elements);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				Set<String> srcs = getImgStr(elements);
				if(srcs.size()>0){
					for(String src :srcs ){
						System.out.println(src);
					}
				}
				
				System.out.println("-----------------------------"+i+"-------------------------------------------");
				SyndContent description = entry.getDescription();
				System.out.println("�����飺" + description.getValue());
				System.out.println("����ʱ�䣺" + entry.getPublishedDate());	
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//				String formatdate = format.format(entry.getPublishedDate());
//				System.out.println(formatdate);
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