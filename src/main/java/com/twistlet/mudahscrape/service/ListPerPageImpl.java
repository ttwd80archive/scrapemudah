package com.twistlet.mudahscrape.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListPerPageImpl implements ListPerPage {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<URI> listPerPage(Document document) {
		List<URI> list = new ArrayList<URI>();
		Elements elements = document.select("div.list_ads div.list_title a");
		for (Element element : elements) {
			String href = element.attr("href");
			try {
				URI uri = new URI(href);
				list.add(uri);
			} catch (URISyntaxException e) {
				logger.error(e.toString());
			}

		}
		return list;
	}

}
