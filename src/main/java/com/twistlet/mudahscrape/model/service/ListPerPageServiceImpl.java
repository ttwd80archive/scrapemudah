package com.twistlet.mudahscrape.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListPerPageServiceImpl implements ListPerPageService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Map<String, String> listPerPage(final Document document) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		Elements elements = document.select("div.list_ads");
		for (Element element : elements) {
			URI uri = extractURI(element.select("div.list_title a").first());
			if (uri != null) {
				String date = extractDate(element.select("div.location")
						.first());
				if (date != null) {
					map.put(uri.toString(), date);
				}
			}

		}
		return map;
	}

	private String extractDate(final Element element) {
		if (element != null) {
			String dateAndTime = element.text();
			String[] components = StringUtils.split(dateAndTime, ",");
			if (components.length > 1) {
				return components[0];
			}
		}
		return null;
	}

	protected URI extractURI(final Element element) {
		if (element != null) {
			String href = element.attr("href");
			try {
				URI uri = new URI(href);
				return uri;
			} catch (URISyntaxException e) {
				logger.error(e.toString());
			}
		}
		return null;

	}

}
