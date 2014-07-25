package com.twistlet.mudahscrape.service;

import java.net.URI;

import org.jsoup.nodes.Document;

public interface DocumentService {
	Document get(final URI uri);

}
