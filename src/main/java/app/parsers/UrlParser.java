package app.parsers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;

public class UrlParser {
    private String year;
    private String month;
    private String resolution;

    private String url;
    private List<String> imgLinks;

    public UrlParser(String year, String month, String resolution) {
        this.year = year;
        this.month = month;
        this.resolution = resolution;
        imgLinks = new ArrayList<>();

        makeUrl();
        parseHtml();
        saveImages();
    }

    private void makeUrl()
    {
        String parsedMonth = Integer.valueOf(month) - 1 > 0 ?
                Integer.valueOf(month) - 1 < 10 ? "0" + (Integer.valueOf(month) - 1) :
                        Integer.toString(Integer.valueOf(month) - 1) : Integer.toString(12);

        String parsedYear = Integer.valueOf(month) - 1 > 0 ?
                year : Integer.toString(Integer.valueOf(year) - 1);

        String monthName = new DateFormatSymbols(Locale.ENGLISH).getMonths()[Integer.valueOf(month) - 1];

        url = "https://www.smashingmagazine.com/" + parsedYear + "/" + parsedMonth
                + "/desktop-wallpaper-calendars-" + monthName.toLowerCase() + "-" + year + "/";
    }

    private void parseHtml()
    {
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
            e.printStackTrace();
        }
    }

    private void saveImages()
    {
        URL url;
        BufferedImage img;
        File file;

        for (String href : imgLinks)
        {
            try {
                url = new URL(href);
                img = ImageIO.read(url);

                file = new File("F:\\" + href.substring(href.lastIndexOf('/') + 1));
                ImageIO.write(img, href.substring(href.lastIndexOf('.') + 1), file);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
