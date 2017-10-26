package com.panshuai.rss;


import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * ��̬��ҳץȡ������ץȡ֮�������
 * @author MaoRenwei
 *
 */
public class DynamicHtmlSelect {
	
	public static final WebClient webClient = new WebClient();
	
	public static HtmlPage htmlUnitSelect(String url,int time) throws InterruptedException {

		HtmlPage page = null;
		try {
			WebClientOptions webClientOptions = webClient.getOptions();
			// 启动JS
			webClientOptions.setJavaScriptEnabled(true);
			// 禁用Css，可避免自动二次请求CSS进行渲染
			webClientOptions.setCssEnabled(false);
			// 启动客户端重定向
			webClientOptions.setRedirectEnabled(false);
			// js运行错误时，是否抛出异常
			webClientOptions.setThrowExceptionOnScriptError(false);
			// 设置超时
			webClientOptions.setTimeout(1000);
			// 设置是否丢出异常的状态码
			webClientOptions.setThrowExceptionOnFailingStatusCode(false);
			//是否打印出内容
			webClientOptions.setPrintContentOnFailingStatusCode(false);
	        // 设置JavaScript时间
			webClient.setJavaScriptTimeout(2000);
			
			page = webClient.getPage(url);
			
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	} 
	
	public static void main(String[] args) throws InterruptedException, IOException {
//		String url ="http://news.qq.com/a/20170907/018645.htm";
		String url = "https://baike.baidu.com/item/%E5%A4%A7%E6%95%B0%E6%8D%AE/1356941?fr=aladdin";
		HtmlPage page = DynamicHtmlSelect.htmlUnitSelect(url,2000);
//		String content = handlerpage(page,13,500,"#BtnRight","#infoTxt");
//		if(content == null){
//			content = page.getBody().asXml();
//		}
//		System.out.println(content);
//		Document doc = Jsoup.parse(content);
//		Elements elements = doc.select("#infoTxt p:eq(0)");
//		System.out.println(elements.toString());
		System.out.println(page.getBody().asXml());
	}

	
	public static String  handlerpage(HtmlPage page ,int allPage,int time,String eventpag,String filter) throws IOException{
		StringBuilder content = new StringBuilder();
		int i=0;
		while(i<allPage){
			i++;
			webClient.waitForBackgroundJavaScript(time);
			content.append(Jsoup.parse(page.getBody().asXml()).select(filter).toString());
//			HtmlElement element = (HtmlElement) page.getElementById(eventpag);
			HtmlElement element = (HtmlElement) page.querySelector(eventpag);
			if(element == null){
				return null;
			}
			page = element.click();
		}
		return content.toString();
	}
}
