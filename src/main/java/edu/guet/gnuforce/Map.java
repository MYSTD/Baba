package edu.guet.gnuforce;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Map {
	
	public Map() {
		
	}

	// 从网站读取地图
	public void ReadMap(String url_str) throws IOException {
		
		URL url=new URL(url_str);
        InputStreamReader ins= new InputStreamReader(url.openStream(), "UTF8");       
        char[] buf=new char[2024];       
        int len=0;
        while((len=ins.read(buf))!=-1)
        {
        String text=new String(buf,0,len);      
        System.out.println(text);
        }
        ins.close();
	}
}
