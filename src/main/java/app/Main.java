package app;

import app.parsers.ConsoleParser;
import app.parsers.UrlParser;

public class Main {
    public static void main(String[] args)
    {
        ConsoleParser consoleParser = new ConsoleParser();
        UrlParser urlParser = new UrlParser(consoleParser.getYear(), consoleParser.getMonth(), consoleParser.getResolution());
    }
}
