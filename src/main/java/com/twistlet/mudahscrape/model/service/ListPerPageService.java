package com.twistlet.mudahscrape.service;

import java.util.Map;

import org.jsoup.nodes.Document;

public interface ListPerPageService {
	Map<String, String> listPerPage(Document document);
}
