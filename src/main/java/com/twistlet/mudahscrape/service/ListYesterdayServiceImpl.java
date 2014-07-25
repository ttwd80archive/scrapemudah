package com.twistlet.mudahscrape.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListYesterdayServiceImpl implements ListYesterdayService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final String FILTER_PREFIX = "Yesterday, ";

	private final DocumentService documentService;
	private final ListPerPageService listPerPageService;

	@Autowired
	public ListYesterdayServiceImpl(final DocumentService documentService,
			final ListPerPageService listPerPageService) {
		this.documentService = documentService;
		this.listPerPageService = listPerPageService;
	}

	@Override
	public List<URI> listYesterday(final String urlPattern) {
		List<Integer> nullDocuments = new ArrayList<Integer>();
		int pageNumber = 1;
		List<URI> list = new ArrayList<URI>();
		boolean seenYesterday = false;
		while (pageNumber <= 1_000) {
			String requestUrl = MessageFormat.format(urlPattern, pageNumber);
			Document document = null;
			try {
				document = documentService.get(new URI(requestUrl));
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
			if (document == null) {
				nullDocuments.add(pageNumber);
				if ((nullDocuments.lastIndexOf(pageNumber)
						- nullDocuments.indexOf(pageNumber)) > 1_000) {
					throw new RuntimeException("bad url: " + requestUrl);
				}
				continue;
			}
			Map<String, String> map = listPerPageService.listPerPage(document);
			addFilteredMapToList(list, map);
			if (seenYesterday == false) {
				if (mapContainsYesterday(map)) {
					seenYesterday = true;
				}
			}
			if (seenYesterday == true) {
				if (stopOnLastItemInMapIsYesterday(map)) {
					break;
				}
			}
			pageNumber++;
		}
		return list;
	}

	private void addFilteredMapToList(final List<URI> list,
			final Map<String, String> map) {
		Set<String> set = map.keySet();
		for (String key : set) {
			String value = map.get(key);
			if (value.startsWith(FILTER_PREFIX)) {
				try {
					list.add(new URI(key));
				} catch (URISyntaxException e) {
					logger.error(e.toString());
				}
			}

		}
	}

	private boolean stopOnLastItemInMapIsYesterday(final Map<String, String> map) {
		int size = map.size();
		if (size > 0) {
			List<String> keys = new ArrayList<String>(map.keySet());
			Collections.reverse(keys);
			String item = keys.get(size - 1);
			if (item.startsWith(FILTER_PREFIX)) {
				return false;
			}

		}
		return true;

	}

	private boolean mapContainsYesterday(final Map<String, String> map) {
		Collection<String> list = map.values();
		for (String item : list) {
			if (item.startsWith(FILTER_PREFIX)) {
				return true;
			}
		}
		return false;
	}
}
