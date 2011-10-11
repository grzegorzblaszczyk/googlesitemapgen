package com.goodylabs.sitemapgen;

import java.util.List;

import com.goodylabs.sitemapgen.impl.Link;

public interface SitemapGenerator {

	String generateSitemap(String host, List<Link> links);

}