package com.newer.spider2;
/**
 * 爬取豆瓣250的电影图片保存着本地文件夹中
 * @author LIUTAN
 *
 */

public class App {
	public static void main(String[] args) {
		//得到豆瓣的十个页面
		for(int i=0;i<=9;i++){
		 Spider spider=new Spider("https://movie.douban.com/top250?start="+i*25+"&filter="); 	 
		 spider.start();
		}		
		System.out.println("下载完成");
	}
}
