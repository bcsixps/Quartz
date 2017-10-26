package com.panshuai.rss;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;  
import java.util.Random;  
  
/** 
 * 如何抓取Js动态生成数据且以滚动页面方式分页的网页 
 * 以抓取今日头条为例说明：http://toutiao.com/ 
 * Created by ysc on 10/13/15. 
 */  
public class TestPage {  
    public static void main(String[] args) throws Exception{  
    	
    	String url ="http://cai.163.com/article/17/0930/13/CVJ9RP3R00052DT2.html";
//    	System.setProperty("webdriver.edge.driver", "./chromedriver.exe");
  
        //等待数据加载的时间  
        //为了防止服务器封锁，这里的时间要模拟人的行为，随机且不能太短  
        long waitLoadBaseTime = 3000;  
        int waitLoadRandomTime = 3000;  
        Random random = new Random(System.currentTimeMillis());  
  
        //火狐浏览器  
        WebDriver driver = new ChromeDriver();  
        //要抓取的网页  
        driver.get(url);  
  
        //等待页面动态加载完毕  
        Thread.sleep(waitLoadBaseTime+random.nextInt(waitLoadRandomTime));  
  
        //要加载多少页数据  
        int pages=5;  
        for(int i=0; i<pages; i++) {  
            //滚动加载下一页  
            driver.findElement(By.className("btnRight")).click();  
            //等待页面动态加载完毕  
            Thread.sleep(waitLoadBaseTime+random.nextInt(waitLoadRandomTime));  
        }  
  
        //输出内容  
        //找到标题元素  
        List<WebElement> elements = driver.findElements(By.id("infoTxt"));  
        int j=1;  
        for(int i=0;i<elements.size();i++) {  
            try {  
                WebElement element = elements.get(i).findElement(By.tagName("p"));  
                //输出标题  
                System.out.println((j++) + "、" + element.getText() + " ");  
            }catch (Exception e){  
                System.out.println("ignore "+elements.get(i).getText()+" because "+e.getMessage());  
            }  
        }  
  
        //关闭浏览器  
        driver.close();  
    }  
}  