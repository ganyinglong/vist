package com.gyl.visit.core.util;

import com.nface.model.ShareUrlParseDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class UrlParser {

    public ShareUrlParseDTO parseShareUrl(String url) throws IOException {
        ShareUrlParseDTO parsed = new ShareUrlParseDTO();

        String domain = URLUtil.getHost(url);
        parsed.setDomain(domain);
        parsed.setUrl(url);
        Document document = Jsoup.connect(url).timeout(5000).method(Connection.Method.GET).followRedirects(true).get();
        Elements meta = document.head().getElementsByTag("meta");
        parsed.setAppName(document.select(".app-name").text());
        String desc = document.select(".desc").text();
        if (StringUtils.isNotEmpty(desc)) {
            parsed.setDescription(desc);
        }
        parsed.setTitle(document.head().getElementsByTag("title").text());

        for (Element link : document.getElementsByTag("link")) {
            if ("shortcut icon".equals(link.attr("rel"))) {
                parsed.setAppIcon(link.attr("href"));
                break;
            }
        }
        Iterator<Element> iterator = meta.iterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            String property = next.attr("property");
            String content = next.attr("content");
            String name = next.attr("name");
            if ("og:image".equals(property)) {
                parsed.setImgUrl(content);
                //网易云site_name
            } else if ("og:site_name".equals(property)) {
                parsed.setAppName(content);
            }
            if ("description".equals(name)) {
                parsed.setDescription(content);
            }
        }
        return parsed;
    }

}
