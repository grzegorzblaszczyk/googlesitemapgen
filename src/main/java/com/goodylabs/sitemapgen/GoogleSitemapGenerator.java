package com.goodylabs.sitemapgen;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.goodylabs.sitemapgen.enums.ChangeFreq;

public class GoogleSitemapGenerator {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public GoogleSitemapGenerator() {
	}

	public String generateSitemap(String host, List<String> links,
			Date lastMod, ChangeFreq changeFreq) {

		Document doc = DocumentHelper.createDocument();

		Namespace defaultNamespace = new Namespace("", "http://www.sitemaps.org/schemas/sitemap/0.9");
		
		Element urlset = doc.addElement(new QName("urlset", defaultNamespace));
		
		Namespace xsiNamespace = new Namespace("xsi",
				"http://www.w3.org/2001/XMLSchema-instance");

		urlset.add(xsiNamespace);
		urlset.addAttribute(
				"xsi:schemaLocation",
				"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");

		for (String link : links) {

			Element url = urlset.addElement(new QName("url", defaultNamespace));

			Element loc = url.addElement("loc");
			loc.setText(host + link);
			Element lastmod = url.addElement("lastmod");

			String formattedDate = formatter.format(lastMod);
			formattedDate = formattedDate.subSequence(0,
					formattedDate.length() - 2)
					+ ":"
					+ formattedDate.substring(formattedDate.length() - 2);
			lastmod.setText(formatter.format(lastMod));

			Element changefreq = url.addElement("changefreq");
			changefreq.setText(changeFreq.name());
		}

		Writer out = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(out, format);
		try {
			writer.write(doc);
		} catch (IOException e) {
			System.err.println(e);
			return "";
		}
		return out.toString();
	}

}
