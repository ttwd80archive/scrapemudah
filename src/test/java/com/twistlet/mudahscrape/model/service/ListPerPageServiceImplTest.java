package com.twistlet.mudahscrape.model.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.twistlet.mudahscrape.model.service.ListPerPageServiceImpl;

public class ListPerPageServiceImplTest {

	@Test
	public void testListing() throws IOException {
		Resource resource = new ClassPathResource(
				"html/page_listing_kl_rent_today");
		String html = FileUtils.readFileToString(resource.getFile());
		ListPerPageServiceImpl sut = new ListPerPageServiceImpl();
		Document document = Jsoup.parse(html);
		Map<String, String> map = sut.listPerPage(document);
		String actual = map.toString();
		String expected = "{http://www.mudah.my/Sungai+Besi+The+DALE+3sty+superlink+20x70-28727705.htm=Today, http://www.mudah.my/2sty+Damai+Perdana+4r3b+Cheras+Len+Sen-28727701.htm=Today, http://www.mudah.my/Sri+Jelatek+Fu11+furnish-28727665.htm=Today, http://www.mudah.my/Sunway+Rydgeway+Semi+D+Taman+melawati-28727626.htm=Today, http://www.mudah.my/Titiwangsa+Sentral+Titiwangsa-28727625.htm=Today, http://www.mudah.my/Damai+Perdana+Alam+Damai+Balakong+Len+Seng+KL-28727622.htm=Today, http://www.mudah.my/Cheras+Pertama+Midah+Maluri+LRT+Tun+Razak+KL-28727620.htm=Today, http://www.mudah.my/PV128+Setapak+Genting+Klang+Shop+loT-28727602.htm=Today, http://www.mudah.my/2nd+and+half+sty+Desa+Setapak+Wangsa+Maju-28727596.htm=Today, http://www.mudah.my/Platinum+Victory+20+PV20+Setapak-28727556.htm=Today, http://www.mudah.my/Cheras+D+alamanda-28727535.htm=Today, http://www.mudah.my/SD+Apartment+Sri+Damansara+KL-28727460.htm=Today, http://www.mudah.my/KLCC+Marc+Residence+1323sf+High+Floor-28727445.htm=Today, http://www.mudah.my/New+Platinum+Lake+Condo+PV20+Setapak+Genting+Klang-28727385.htm=Today, http://www.mudah.my/Bukit+Bintang+City+Garden+Condo+Jln+Alor-28727380.htm=Today, http://www.mudah.my/City+Garden+Bukit+Bintang+KL+Alor-28727379.htm=Today, http://www.mudah.my/Dtr+Dwitasik+2+floor+Endlot+office+Bdr+Permaisuri+-28727340.htm=Today, http://www.mudah.my/Desa+Petaling+Ground+Floor+Shop+lot-28727338.htm=Today, http://www.mudah.my/1+Sentul+Condo+Sentul+-28727244.htm=Today, http://www.mudah.my/Cheras+Selatan+Balakong+C180+Studio+RM1500+Kajang-28727235.htm=Today, http://www.mudah.my/Setapak+Green+high+floor+Partly+furnish+1580SF-28727231.htm=Today, http://www.mudah.my/Pandan+Height+Condo+Forest+view+Pd+Perdana+Cheras-28727230.htm=Today, http://www.mudah.my/PV20+Platinium+Lake+Condo+Jln+GentingKlang+Setapak-28727228.htm=Today, http://www.mudah.my/Mont+kiara+pelangi+kuala+lumpur-28727221.htm=Today, http://www.mudah.my/Flat+Connaught+Partly+Furnished+and+Reno+Cheras-28727217.htm=Today, http://www.mudah.my/Mont+kiara+pelangi+kuala+lumpur-28727214.htm=Today, http://www.mudah.my/Lestari+Apartment+cheras+must+view-28727213.htm=Today, http://www.mudah.my/NEW+SHOP+Zone+J6+24x85+Sri+Petaling-28727212.htm=Today, http://www.mudah.my/Cheras+Business+Centre+Apt+Convenience+area+Cheras-28727210.htm=Today, http://www.mudah.my/D+ALAMANDA+CONDO+Maluri+PGRM+Brand+New+Furnishd-28727207.htm=Today, http://www.mudah.my/Balakong+Livia+C180+Cheras+Selatan-28727204.htm=Today, http://www.mudah.my/Sri+Shamelin+Apt+new+paints+Pandan+Perdana-28727202.htm=Today, http://www.mudah.my/Taman+pusat+kepong-28727199.htm=Today, http://www.mudah.my/Kiara+residence+partly+furnisehd+OUG+1050sf-28727197.htm=Today, http://www.mudah.my/Taman+Connaught+Angkasa+condo+Next+UCSI-28727196.htm=Today, http://www.mudah.my/Riana+green+East+Condominium-28727194.htm=Today, http://www.mudah.my/Condo+Greenpark+Taman+Yarl+Oug+jalan+Puchong-28727190.htm=Today, http://www.mudah.my/Pantai+Hillpark+bangsar+south-28727183.htm=Today, http://www.mudah.my/Kiara+Designer+Suites+condominium+Mont+Kiara-28727182.htm=Today, http://www.mudah.my/Andalucia+pantai+hillpark+bangsar+south-28727180.htm=Today}";
		assertEquals(expected, actual);
	}
}
