package com.newer.spider2;
/**
 * ��ȡ����250�ĵ�ӰͼƬ�����ű����ļ�����
 * @author LIUTAN
 *
 */

public class App {
	public static void main(String[] args) {
		//�õ������ʮ��ҳ��
		for(int i=0;i<=9;i++){
		 Spider spider=new Spider("https://movie.douban.com/top250?start="+i*25+"&filter="); 	 
		 spider.start();
		}		
		System.out.println("�������");
	}
}
