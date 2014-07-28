package com.twistlet.mudahscrape.model.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import com.twistlet.mudahscrape.model.entity.House;

public class HouseServiceImplTest {

	HouseServiceImpl sut;

	Document document1;

	Document document2;

	Document document3;

	Document document4;

	@Mock
	DocumentService documentService;

	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);
		document1 = toDocument("html/content-sale-strata-1");
		document2 = toDocument("html/content-sale-strata-2");
		document3 = toDocument("html/content-rent-strata-1");
		document4 = toDocument("html/content-rent-strata-2");
		sut = new HouseServiceImpl(documentService);
	}

	private Document toDocument(final String path) throws IOException {
		return Jsoup.parse(IOUtils.toString(new ClassPathResource(path)
				.getInputStream()));
	}

	@Test
	public void testToHouse1() throws URISyntaxException {
		when(documentService.get(new URI("http://www.google.com"))).thenReturn(
				document1);
		House house = sut.toHouse("http://www.google.com");
		String actual = ToStringBuilder.reflectionToString(house,
				ToStringStyle.SHORT_PREFIX_STYLE);
		String expected = "House[url=http://www.google.com,postedDate="
				+ yesterday()
				+ ",title=Pangsapuri Rimba, Seksyen 16, Shah Alam,price=60000,state=Selangor,district=Shah Alam,propertyType=Apartment/ Flat,titleType=Freehold,bedroom=3,bathroom=1,bumi=true,postedBy=En. Din,content=Berhampiran:- 1. 10 minit ke Pusat Bandar Shah Alam 2. Pasaraya Giant 3. Kawasan Industri 4. Sekolah Kebangsaan & Sekolah Menengah. Freehold Blok A, Tingkat 6 daripada 17 Tingkat 3 Bilik 1 Bilik Air *** Other Terms & Conditions: BUMI LOT. Keterangan lanjut sila hubungi 013-393 6299 (En. Din)]";
		assertEquals(expected, actual);
	}

	@Test
	public void testToHouse2() throws URISyntaxException {
		when(documentService.get(new URI("http://www.google.com"))).thenReturn(
				document2);
		House house = sut.toHouse("http://www.google.com");
		String actual = ToStringBuilder.reflectionToString(house,
				ToStringStyle.SHORT_PREFIX_STYLE);
		String expected = "House[url=http://www.google.com,postedDate="
				+ yesterday()
				+ ",title=Teratai Apartment Bukit Subang Shah Alam,price=73000,state=Selangor,district=Shah Alam,propertyType=Apartment/ Flat,titleType=Freehold,bedroom=3,bathroom=2,bumi=false,postedBy=Mohd Asyrin,content=Apartment Teratai Bukit Subang untuk dijual - Freehold - Non Bumi Lot - Tingkat 4, lot tepi - Saiz 650 k.p. - 3 bilik tidur, 2 bilik air -Berdekatan dengan laluan ke Kg Melayu Subang,Kota Damansara,Shah Alam dan K.L melalui highway Guthrie -8 min ke Giant Sek 13 Shah Alam Harga - RM73,000 (Bawah harga pasaran) Berminat... whatapps/sms/call - 014 711 8604 (Asyrin)]";
		assertEquals(expected, actual);
	}

	@Test
	public void testToHouse3() throws URISyntaxException {
		when(documentService.get(new URI("http://www.google.com"))).thenReturn(
				document3);
		House house = sut.toHouse("http://www.google.com");
		String actual = ToStringBuilder.reflectionToString(house,
				ToStringStyle.SHORT_PREFIX_STYLE);
		String expected = "House[url=http://www.google.com,postedDate="
				+ yesterday()
				+ ",title=Permai Puteri Apartment , Ampang, Selangor,price=325000,state=Selangor,district=Ampang,propertyType=Apartment/ Flat,titleType=Leasehold,bedroom=3,bathroom=2,bumi=true,postedBy=Jacky Siow,content=Permai Puteri Apartment For Sales -------------------------------------- -Partly Furnished (Almari) -833 sq.ft -3 bedroom, 2 bathroom -Facing KLCC -Basic unit -High Floor *DISCLAIMER Photo is just for a DISPLAY ,To protect owner's and tenants privacy, pictures shown may not be the actual unit, it is for illustration purpose only. Appreciate your understanding and viewing appointment on actual unit is always welcome. For personalized presentation, please contact me. It would be my pleasure to serve you. Jacky 012 2987231 GS Realty Sdn Bhd No.2-1, Jalan Metro Pudu, Off Jalan Loke Yew, 55100 Kuala Lumpur.]";
		assertEquals(expected, actual);
	}

	@Test
	public void testToHouse4() throws URISyntaxException {
		when(documentService.get(new URI("http://www.google.com"))).thenReturn(
				document4);
		House house = sut.toHouse("http://www.google.com");
		String actual = ToStringBuilder.reflectionToString(house,
				ToStringStyle.SHORT_PREFIX_STYLE);
		String expected = "House[url=http://www.google.com,postedDate="
				+ yesterday()
				+ ",title=Apt Sri Angkasa, Seksyen 28, Shah Alam,price=150000,state=Selangor,district=Shah Alam,propertyType=Apartment/ Flat,titleType=Freehold,bedroom=2,bathroom=1,bumi=true,postedBy=ZUL,content=Apt Sri Angkasa Taman Alam Megah Seksyen 28 Shah Alam -Freehold -688 kp -Tingkat 4 -2 bilik/1 bilik air -Bank nilai RM160K -Harga Jualan RM150 -Deposit 3%. Baki 7% & legal fee - kwsp. -Zul 016 2774287]";
		assertEquals(expected, actual);
	}

	private String yesterday() {
		FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd");
		Date today = new Date();
		return df.format(DateUtils.addDays(today, -1));
	}
}
