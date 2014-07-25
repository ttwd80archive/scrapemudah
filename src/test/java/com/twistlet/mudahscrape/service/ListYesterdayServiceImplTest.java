package com.twistlet.mudahscrape.service;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ListYesterdayServiceImplTest {

	Document documentToday;
	Document documentTodayAndYesterday;
	Document documentYesterday;
	Document documentYesterdayOld;

	@Mock
	DocumentService documentService;

	@Mock
	ListPerPageService listPerPageService;

	ListYesterdayServiceImpl sut;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new ListYesterdayServiceImpl(documentService, listPerPageService);
	}

	@Test(expected = RuntimeException.class)
	public void testNoSetup() {
		sut.listYesterday("page-{0}");
	}

}
