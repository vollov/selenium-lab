package org.demo.selenium.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XStreamUtils {
	public static String getStringFromFile(String fileName) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			InputStream inputStream = getInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	private static InputStream getInputStream(String fileName) throws IOException{
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(fileName);
		return inputStream;
	}
	
	public static RegionManager getRegionManager(){
		XStream xstream = new XStream(new StaxDriver());
		
		String xml = getStringFromFile("regions.xml");
		
		System.out.println(xml);
		
		RegionManager manager = (RegionManager) xstream.fromXML(xml);
		return manager;
	}
}
