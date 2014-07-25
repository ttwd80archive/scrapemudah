package com.twistlet.mudahscrape.model.service;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import com.twistlet.mudahscrape.model.service.DocumentService;
import com.twistlet.mudahscrape.model.service.ListPerPageService;
import com.twistlet.mudahscrape.model.service.ListPerPageServiceImpl;
import com.twistlet.mudahscrape.model.service.ListYesterdayServiceImpl;

public class ListYesterdayServiceImplTest {

	Document documentToday;
	Document documentTodayAndYesterday;
	Document documentYesterday;
	Document documentYesterdayOld;

	ListPerPageService listPerPageService;

	@Mock
	DocumentService documentService;

	ListYesterdayServiceImpl sut;

	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);
		documentToday = Jsoup.parse(IOUtils.toString(new ClassPathResource(
				"html/page_listing_kl_rent_today").getInputStream()));

		documentTodayAndYesterday = Jsoup.parse(IOUtils
				.toString(new ClassPathResource(
						"html/page_listing_kl_rent_today_yesterday")
						.getInputStream()));
		documentYesterday = Jsoup.parse(IOUtils.toString(new ClassPathResource(
				"html/page_listing_kl_rent_yesterday").getInputStream()));
		documentYesterdayOld = Jsoup.parse(IOUtils
				.toString(new ClassPathResource(
						"html/page_listing_kl_rent_yesterday_old")
						.getInputStream()));
		listPerPageService = new ListPerPageServiceImpl();
		sut = new ListYesterdayServiceImpl(documentService, listPerPageService);
	}

	@Test(expected = RuntimeException.class)
	public void testNoSetup() {
		sut.listYesterday("page-{0}");
	}

	@Test
	public void testFullyOk() throws URISyntaxException {
		when(documentService.get(new URI("http://www.google.com/1")))
				.thenReturn(documentToday);
		when(documentService.get(new URI("http://www.google.com/2")))
				.thenReturn(documentTodayAndYesterday);
		when(documentService.get(new URI("http://www.google.com/3")))
				.thenReturn(documentYesterday);
		when(documentService.get(new URI("http://www.google.com/4")))
				.thenReturn(documentYesterdayOld);
		sut.listYesterday("http://www.google.com/{0}");
		verify(documentService).get(new URI("http://www.google.com/1"));
		verify(documentService).get(new URI("http://www.google.com/2"));
		verify(documentService).get(new URI("http://www.google.com/3"));
		verify(documentService).get(new URI("http://www.google.com/4"));
	}

	@Test(expected = RuntimeException.class)
	public void testExceptionOnFirst() throws URISyntaxException {
		sut.listYesterday("htp://www.google.com/{0}");
	}

}
