package com.twistlet.mudahscrape.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twistlet.mudahscrape.model.entity.House;

public class HouseServiceImpl implements HouseService {

	private final DocumentService documentService;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public HouseServiceImpl(final DocumentService documentService) {
		this.documentService = documentService;
	}

	@Override
	public House toHouse(final String url) {
		try {
			Document document = documentService.get(new URI(url));
			House house = new House();
			house.setUrl(url);
			Date today = new Date();
			Date yesterday = DateUtils.addDays(today, -1);
			FastDateFormat df = FastDateFormat.getInstance("dd/MM/yyyy");
			house.setPostedDate(df.format(yesterday));
			house.setTitle(extractText(document, "#ad-title"));
			extractNumber(document, "#price").ifPresent(
					x -> house.setPrice(x.longValue()));

			Element div = document.getElementById("params");
			Map<String, String> map = tableToMap(div);
			String location = map.get("Location");
			if (location != null) {
				String[] locationElements = StringUtils.split(location, "-");
				house.setState(StringUtils.trimToNull(locationElements[0]));
				if (locationElements.length > 1) {
					house.setDistrict(StringUtils
							.trimToNull(locationElements[1]));
				}
			}
			Optional.ofNullable(map.get("Property Type")).ifPresent(
					house::setPropertyType);
			Optional.ofNullable(map.get("Title type")).ifPresent(
					house::setTitleType);
			Optional.ofNullable(map.get("Bedrooms")).ifPresent(
					x -> house.setBedroom(new Integer(x)));
			Optional.ofNullable(map.get("Bathroom")).ifPresent(
					x -> house.setBathroom(new Integer(x)));
			String otherInfo = map.get("Other Info");
			house.setBumi("Bumi Lot".equals(otherInfo));
			Element priceContact = document.select("#ad-author a").first();
			if (priceContact != null) {
				house.setPostedBy(priceContact.text());
			}
			Element p = document.select(".description p").first();
			if (p != null) {
				String content = p.text();
				content = StringUtils.remove(content, StringUtils.CR);
				content = StringUtils.remove(content, StringUtils.LF);
				house.setContent(content);
			}
			return house;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> tableToMap(final Element div) {
		List<String> numeric = Arrays.asList("Bedrooms", "Bathroom");
		Map<String, String> map = new LinkedHashMap<String, String>();
		Element table = div.getElementsByTag("table").first();
		Elements rows = table.select("tbody tr");
		for (Element row : rows) {
			Element label = row.select("td:eq(0)").first();
			Element value = row.select("td:eq(1)").first();
			if ((label != null) && (value != null)) {
				String labelText = label.text();
				String valueText = value.text();
				if (numeric.contains(labelText)) {
					Integer n = null;
					try {
						n = new Integer(valueText);
					} catch (NumberFormatException e) {
					}
					if (n == null) {
						valueText = null;
					}
				}
				map.put(labelText, valueText);
			}
		}
		return map;

	}

	private Optional<Number> extractNumber(final Document document,
			final String selector) {
		String text = extractText(document, selector);
		if (text != null) {
			text = StringUtils.remove(text, " ");
			text = StringUtils.remove(text, "RM");
			try {
				Number n = new Long(text);
				return Optional.of(n);
			} catch (NumberFormatException e) {
				logger.error("{} - {}", document.location(), e.toString());
			}
		}
		return Optional.empty();
	}

	private String extractText(final Document document, final String selector) {
		Element element = document.select(selector).first();
		if (element != null) {
			return element.text();
		}
		return null;
	}

}
