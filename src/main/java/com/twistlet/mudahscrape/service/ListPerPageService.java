package com.twistlet.mudahscrape.service;

import java.net.URI;
import java.util.List;

import org.jsoup.nodes.Document;

public interface ListPerPage {
	List<URI> listPerPage(Document document);
}
