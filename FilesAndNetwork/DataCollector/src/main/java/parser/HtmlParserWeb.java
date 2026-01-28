package parser;

import model.MetroLine;
import model.MetroStation;
import model.WebMetroStation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParserWeb {
    private static final String METRO_URL = "https://skillbox-java.github.io/";

    public Document getHtmlDocument() throws IOException {
        return Jsoup.connect(METRO_URL)
                .userAgent("Mozilla/5.0")
                .get();
    }

    public List<MetroLine> parseLines(Document doc) {
        List<MetroLine> lines = new ArrayList<>();
        Elements lineElements = doc.select("span.js-metro-line");

        for (Element lineElement : lineElements) {
            String number = lineElement.attr("data-line").trim();
            String name = lineElement.text().trim();

            MetroLine line = new MetroLine(number, name);
            lines.add(line);
        }

        return lines;
    }

    public List<MetroStation> parseStations(Document doc) {
        List<MetroStation> stations = new ArrayList<>();
        Elements linesBlock = doc.select("div.js-metro-stations");

        for (Element lineBlock : linesBlock) {
            String numberLine = lineBlock.attr("data-line").trim();
            Elements nameStation = lineBlock.select("p.single-station");

            for (Element station : nameStation) {
                String name = station.select("span.name").text().trim();
                if (!name.isEmpty()) {
                    WebMetroStation metroStation = new WebMetroStation(name, numberLine);
                    if (station.select("span.t-icon-metroln").size() > 0) {
                        metroStation.setHasConnection(true);
                    }

                    stations.add(metroStation);

                }
            }

        }
        return stations;
    }

}