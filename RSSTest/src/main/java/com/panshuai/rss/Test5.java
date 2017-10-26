package com.panshuai.rss;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test5 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        String url = "http://comment.console.cngoldshop.com/back/vote/vote/api_vote_getstyle.htm";
		Document doc = Jsoup.connect(url).ignoreContentType(true).timeout(1000).get();
		System.out.println(doc);
	}

}
