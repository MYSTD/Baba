package edu.guet.gnuforce.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class HTTPClient {

	public HTTPClient () { }

	/**
	 * 向指定 URL 发送 GET 请求，并返回请求结果
	 *
	 * @param url URL
	 * @return 页面内容，如果出现错误则返回 null
	 */
	public String sendRequest(String url) {
		StringBuilder buffer = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			InputStreamReader ins = new InputStreamReader(urlObj.openStream(), "UTF-8");
			char[] buf = new char[1024];
			while (ins.read(buf) != -1) {
				buffer.append(buf);
			}
			ins.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	/**
	 * 向指定 URL 发送 POST 请求，并返回请求结果
	 * @param url URL
	 * @param data 要发送的数据
	 * @return 结果，如果出现错误则返回 null
	 */
	public String sendPostRequest(String url, Map<String, String> data) {
		StringBuilder buffer = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			URLConnection conn = urlObj.openConnection();
			HttpURLConnection http = (HttpURLConnection) conn;
			http.setRequestMethod("POST");
			http.setDoOutput(true);

			StringJoiner joiner = new StringJoiner("&");
			for (Map.Entry<String, String> entry : data.entrySet()) {
				joiner.add(URLEncoder.encode(entry.getKey(), "UTF-8")
								+ '='
								+ URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
			byte[] request = joiner.toString().getBytes(StandardCharsets.UTF_8);
			long len = request.length;
			http.setFixedLengthStreamingMode(len);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			http.connect();

			InputStreamReader reader = new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8);
			char[] buf = new char[1024];
			while (reader.read(buf) != -1) {
				buffer.append(buf);
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return buffer.toString();
	}
}
