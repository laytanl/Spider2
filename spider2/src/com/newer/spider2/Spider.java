package com.newer.spider2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {
	private String url;
	ExecutorService pool;
	
	ArrayList<String> src = new ArrayList<>();//用来存储图片的地址
	ArrayList<String> names = new ArrayList<>();//用来存储图片的名字

	public Spider(String url) {
		this.url = url;
	}

	public void start() {
		try {
			Document doc = Jsoup.connect(url).get();	
			Elements items = doc.select(".grid_view .item");
			for (int i = 0; i < items.size(); i++) {
				Element item = items.get(i);
				String img = item.select("img[src]").first().attr("src");//得到页面中所有的图片src
				src.add(img);
				String name = item.select(".info .title").first().text();//得到页面中所有的图片的名字
				names.add(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool = Executors.newCachedThreadPool();
		for(int i=0;i<src.size();i++){
			Download load = new Download(src.get(i), names.get(i));
			System.out.println(src.get(i)+ names.get(i));
			pool.execute(load);//通过线程池去完成
		}		
	}
}
