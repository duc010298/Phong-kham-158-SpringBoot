package com.github.duc010298.clinic158.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class CSSInline {

    public String convert(String html) {
        final String style = "style";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select(style);
        for (Element e : els) {
            String styleRules = e.getAllElements().get(0).data().replaceAll("\n", "").trim();
            String delims = "{}";
            StringTokenizer st = new StringTokenizer(styleRules, delims);
            while (st.countTokens() > 1) {
                String selector = st.nextToken();
                String properties = st.nextToken();
                Elements selectedElements;
                try {
                    selectedElements = doc.select(selector);
                } catch (Exception ex) {
                    continue;
                }
                for (Element selElem : selectedElements) {
                    String oldProperties = selElem.attr(style);
                    selElem.attr(style, oldProperties.length() > 0 ?
                            concatenateProperties(oldProperties, properties) : properties);
                }
            }
            e.remove();
        }
        els = doc.getAllElements();
        for (Element e : els) {
            e.removeAttr("id");
            e.removeAttr("rows");
            e.removeAttr("class");
            e.removeAttr("disabled");
        }
        return doc.html();
    }

    private String concatenateProperties(String oldProp, String newProp) {
        oldProp = oldProp.trim();
        if (!newProp.endsWith(";"))
            newProp += ";";
        return newProp + oldProp;
    }

}
