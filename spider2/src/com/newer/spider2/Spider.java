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
	
	ArrayList<String> src = new ArrayList<>();//�����洢ͼƬ�ĵ�ַ
	ArrayList<String> names = new ArrayList<>();//�����洢ͼƬ������

	public Spider(String url) {
		this.url = url;
	}

	public void start() {
		try {
			Document doc = Jsoup.connect(url).get();	
			Elements items = doc.select(".grid_view .item");
			for (int i = 0; i < items.size(); i++) {
				Element item = items.get(i);
				String img = item.select("img[src]").first().attr("src");//�õ�ҳ�������е�ͼƬsrc
				src.add(img);
				String name = item.select(".info .title").first().text();//�õ�ҳ�������е�ͼƬ������
				names.add(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool = Executors.newCachedThreadPool();
		for(int i=0;i<src.size();i++){
			Download load = new Download(src.get(i), names.get(i));
			System.out.println(src.get(i)+ names.get(i));
			pool.execute(load);//ͨ���̳߳�ȥ���
		}		
	}
}
