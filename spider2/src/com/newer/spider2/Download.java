package com.newer.spider2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download implements Runnable {

	private String s;
	private String name;
	public Download(String s,String name) {
		this.s = s;
		this.name=name;
	}

	@Override
	public void run() {
		URL url;
		try {
			url = new URL(s);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();//建立指定地址的连接
			FileOutputStream out = new FileOutputStream("D:/picture/"+name+".jpg");//存储到指定文件夹
			byte[] buf = new byte[1024 * 4];
			int size;
			while (-1 != (size = in.read(buf))) {
				out.write(buf, 0, size);
				out.flush();
			}
			out.close();
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
