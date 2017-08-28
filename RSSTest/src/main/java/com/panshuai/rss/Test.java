package com.panshuai.rss;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Guid;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.WireFeedOutput;
import com.rometools.rome.io.XmlReader;

//import com.sun.syndication.feed.rss.Channel;
//import com.sun.syndication.feed.rss.Description;
//import com.sun.syndication.feed.rss.Guid;
//import com.sun.syndication.feed.rss.Item;
//import com.sun.syndication.feed.synd.SyndEntry;
//import com.sun.syndication.feed.synd.SyndFeed;
//import com.sun.syndication.io.FeedException;
//import com.sun.syndication.io.SyndFeedInput;
//import com.sun.syndication.io.WireFeedOutput;
//import com.sun.syndication.io.XmlReader;

//http://rss.webofknowledge.com/rss?e=4f1b64b6b221ea05&c=8c6909b93bf3eb38a2066a826b61a412
public class Test {
	public static void main(String[] args) {
		try {
			// parseXml(new
			// URL("http://rss.webofknowledge.com/rss?e=4f1b64b6b221ea05&c=8c6909b93bf3eb38a2066a826b61a412"));
			parseXml(new URL("http://127.0.0.1:8080/xd/rss?q=java&t=atom&h=50&s=50"));
			String xml=createXml();
			parseString(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void parseString(String xml) throws Exception {
	
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		ByteArrayInputStream inputStream=new ByteArrayInputStream(xml.getBytes("UTF-8"));
		feed = input.build(new XmlReader(inputStream));
		List entries = feed.getEntries();// �õ����еı���<title></title>
		for (int i = 0; i < entries.size(); i++) {
			SyndEntry entry = (SyndEntry) entries.get(i);
			System.out.println(entry.getTitle());
		}
		System.out.println("feed size:" + feed.getEntries().size());
	}

	/**
	 * �������ӵ�ַ�õ�����
	 */
	public static void parseXml(URL url) throws IllegalArgumentException,
			FeedException {
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = null;
			URLConnection conn;
			conn = url.openConnection();
			String content_encoding = conn.getHeaderField("Content-Encoding");
			if (content_encoding != null && content_encoding.contains("gzip")) {
				System.out.println("conent encoding is gzip");
				GZIPInputStream gzin = new GZIPInputStream(
						conn.getInputStream());
				feed = input.build(new XmlReader(gzin));
			} else {
				feed = input.build(new XmlReader(conn.getInputStream()));
			}

			List entries = feed.getEntries();// �õ����еı���<title></title>
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);
				System.out.println(entry.getTitle());
			}
			System.out.println("feed size:" + feed.getEntries().size());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String createXml() throws Exception {      
        /* ����ChannelԴ���ṩ��Ӣ��,Channel������������������һ��Ĭ�ϵ��޲ι���������clone����һ�����вε�    
        * �����Լ�ָ���ı���ʹ���в����ģ���Ϊ������Ҫ���֤����ָ���췽������Ҫ����һ��type���汾�������type�������д������Ҫ��rss_��ͷ�İ汾��    
        * Licensed under the Apache License, Version 2.0 (the "License");    
        * ��Ϊ��ǰ�汾��2.0�����Ծ���rss_2.0��������rss_2.0��������쳣����Դ����д���Ѿ������ס�    
        */     
       Channel channel = new Channel("rss_2.0");      
       channel.setTitle("channel����");//��վ����      
        channel.setDescription("channel������");//��վ����      
        channel.setLink("www.shlll.net");//��վ��ҳ����      
        channel.setEncoding("utf-8");//RSS�ļ�����      
        channel.setLanguage("zh-cn");//RSSʹ�õ�����      
        channel.setTtl(5);//time to live�ļ�д����ˢ��ǰ��ǰRSS�ڻ����п��Ա���೤ʱ�䣨���ӣ�      
        channel.setCopyright("��Ȩ����");//��Ȩ����      
        channel.setPubDate(new Date());//RSS����ʱ��      
        List<Item> items = new ArrayList<Item>();//���list��Ӧrss�е�item�б�                
        Item item = new Item();//�½�Item���󣬶�Ӧrss�е�<item></item>      
       item.setAuthor("hxliu");//��Ӧ<item>�е�<author></author>      
       item.setTitle("���ű���");//��Ӧ<item>�е�<title></title>      
       item.setGuid(new Guid());//GUID=Globally Unique Identifier Ϊ��ǰ����ָ��һ��ȫ��Ψһ��ʾ��������Ǳ����      
        item.setPubDate(new Date());//���<item>��Ӧ�ķ���ʱ��      
        item.setComments("ע��");//����<item>�ڵ��е�<comments></comments>      
        //�½�һ��Description������Item����������      
        Description description = new Description();      
       description.setValue("��������");//<description>�е�����      
        item.setDescription(description);//��ӵ�item�ڵ���      
        items.add(item);//����һ������<item></item>��      
        channel.setItems(items);      
        //��WireFeedOutput�������rss�ı�      
        WireFeedOutput out = new WireFeedOutput();      
        try {      
        	String xml=out.outputString(channel);
            System.out.println(xml); 
            return xml;
        } catch (IllegalArgumentException e) {      
            e.printStackTrace();      
        } catch (FeedException e) {      
            e.printStackTrace();      
        }
        return null;
	}	
}
