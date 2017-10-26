package com.panshuai.rss;

import java.util.HashSet;
import java.util.Set;

public class Test2 {  
    public static void main(String[] args) {  
        String A="http://finance.huanqiu.com/china/2014-06/5010207_3.html";  
        String B="http://finance.huanqiu.com/china/2014-06/5010207_4.html";  
        char[] aarr = A.toCharArray();
        char[] barr = B.toCharArray();
        System.out.println(aarr);
        System.out.println(barr);
        int j=0;
        for(int i=0;i<aarr.length;i++){
        	if(aarr[i] == barr[i]){
        		continue ;
        	}else{
        		j = i;
        		break ;
        	}
        }
        System.out.println(j);
    }  
}  