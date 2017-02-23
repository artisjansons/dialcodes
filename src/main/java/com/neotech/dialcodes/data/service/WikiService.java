package com.neotech.dialcodes.data.service;

import com.neotech.dialcodes.data.model.Country;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Artis on 2/21/2017.
 */
@Component
public class WikiService {

    public List<Country> loadCountries(String url) throws IOException {
        List<Country> countries = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = getTables(doc);
        Elements rows = getRows(tables.get(0));

        for (int i=1; i<rows.size(); i++) {
            Elements cols = getCols(rows.get(i));
            String name = getElementAt(cols, 0).text();
            Elements codes = getElementAt(cols, 1).select(":not(sup) > a");
            for (Element code : codes) {
                String[] split = code.text().split(",");
                for (String c : split) {
                    countries.add(new Country(name, c.substring(1).replaceAll("\\s+","")));
                }
            }
        }

        // sort by code and return
        return countries.stream().sorted(
                Comparator.comparing(country -> ((Country) country).getDialCode().length()).reversed()
        ).collect(Collectors.toList());
    }

    private Elements getTables(Document document) {
        Elements tables = document.select("table.wikitable.sortable");
        if (tables.size()==0) {
            throw new RuntimeException("Cannot extract tables from wiki page");
        }

        return tables;
    }

    private Elements getRows(Element table) {
        Elements rows = table.select("tr");
        if (rows.size()==0) {
            throw new RuntimeException("Cannot extract rows from table");
        }

        return rows;
    }

    private Elements getCols(Element row) {
        Elements cols = row.select("td");
        if (cols.size()==0) {
            throw new RuntimeException("Cannot extract cols from table row");
        }

        return cols;
    }

    private Element getElementAt(Elements e, int index) {
        if (e.size()>index) {
            return e.get(index);
        }

        throw new RuntimeException("Cannot find element at index " + index);
    }
}
