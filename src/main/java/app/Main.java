package app;

import app.data.DataService;
import app.parsers.ConsoleParser;
import app.parsers.UrlParser;

public class Main {
    public static void main(String[] args)
    {
        ConsoleParser consoleParser = new ConsoleParser();
        UrlParser urlParser = new UrlParser(consoleParser.getYear(), consoleParser.getMonth());
        DataService dataService = new DataService(urlParser.parseUrl(consoleParser.getResolution()));
        dataService.saveImages();
    }
}
