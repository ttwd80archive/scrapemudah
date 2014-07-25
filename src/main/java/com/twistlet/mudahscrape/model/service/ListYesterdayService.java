package com.twistlet.mudahscrape.model.service;

import java.net.URI;
import java.util.List;

public interface ListYesterdayService {

	List<URI> listYesterday(String urlPattern);
}
