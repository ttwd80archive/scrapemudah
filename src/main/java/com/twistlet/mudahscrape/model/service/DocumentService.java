package com.twistlet.mudahscrape.model.service;

import java.net.URI;

import org.jsoup.nodes.Document;

public interface DocumentService {
	Document get(final URI uri);

}
