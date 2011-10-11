package com.goodylabs.sitemapgen;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.goodylabs.sitemapgen.enums.ChangeFreq;

public class GoogleSitemapGeneratorTest {

	private GoogleSitemapGenerator generator;

	List<String> links;
	String host;
	
	@Before
	public void setUp() {
		generator = new GoogleSitemapGenerator();
		
		links = new ArrayList<String>();
		links.add("/firstUri");
		links.add("/second-very-long-uri");
		links.add("/third/deep/uri");
		links.add("/uri?withParams=yes");
		
		host = "http://www.somesite.com";
	}

	@Test
	public void shouldGenerateProperSitemap() {

		String expectedContent = convertStreamToString(GoogleSitemapGenerator.class
				.getClassLoader().getResourceAsStream("sitemap.xml"));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2011);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DATE, 11);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 20);
		cal.set(Calendar.SECOND, 41);
		cal.set(Calendar.MILLISECOND, 0);
		cal.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
		
		assertEquals(expectedContent, generator.generateSitemap(host, links, cal.getTime(), ChangeFreq.always));

	}

	private String convertStreamToString(InputStream is) {
		try {
			if (is != null) {
				Writer writer = new StringWriter();

				char[] buffer = new char[1024];
				try {
					Reader reader = new BufferedReader(new InputStreamReader(
							is, "UTF-8"));
					int n;
					while ((n = reader.read(buffer)) != -1) {
						writer.write(buffer, 0, n);
					}
				} finally {
					is.close();
				}
				return writer.toString();
			} else {
				return "";
			}
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}

}
