package com.twistlet.mudahscrape.model.service;

import java.io.IOException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentServiceImpl implements DocumentService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Document get(final URI uri) {
		try {
			return Jsoup.connect(uri.toString()).get();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return null;
	}
}
