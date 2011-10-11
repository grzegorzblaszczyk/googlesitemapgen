package com.goodylabs.sitemapgen;

import java.util.List;


public interface SitemapGenerator {

	String generateSitemap(String host, List<Link> links);

}