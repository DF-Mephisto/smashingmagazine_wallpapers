package app.parsers;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlParser {

    private String url;

    public UrlParser(String year, String month) {
        setUrl(year, month);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String year, String month) {
        String parsedMonth = Integer.valueOf(month) - 1 > 0 ?
                Integer.valueOf(month) - 1 < 10 ? "0" + (Integer.valueOf(month) - 1) :
                        Integer.toString(Integer.valueOf(month) - 1) : Integer.toString(12);

        String parsedYear = Integer.valueOf(month) - 1 > 0 ?
                year : Integer.toString(Integer.valueOf(year) - 1);

        String monthName = new DateFormatSymbols(Locale.ENGLISH).getMonths()[Integer.valueOf(month) - 1];

        url = "https://www.smashingmagazine.com/" + parsedYear + "/" + parsedMonth
                + "/desktop-wallpaper-calendars-" + monthName.toLowerCase() + "-" + year + "/";
    }

    public List<String> parseUrl(String resolution)
    {
        List<String> imgLinks =  new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements links = doc.body().getElementsByTag("a");
            for (Element e : links)
            {
                String href = e.attr("abs:href");

                int iEnd = href.lastIndexOf('.');

                if (iEnd >= 0)
                {
                    int iStart = href.substring(0, iEnd).lastIndexOf('-');

                    if (iStart >= 0)
                    {
                        String res = href.substring(iStart + 1, iEnd);

                        if (res.equals(resolution))
                        imgLinks.add(href);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("HTTP Status 404 Not Found");
        }

        return imgLinks;
    }
}
