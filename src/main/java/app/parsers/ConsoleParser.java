package app.parsers;

import java.util.Scanner;

public class ConsoleParser {
    private String year;
    private String month;
    private String resolution;

    private Scanner scan;

    public ConsoleParser()
    {
        scan = new Scanner(System.in);
        setYear();
        setMonth();
        setResolution();
    }

    public String getYear() {
        return year;
    }

    public void setYear() {
        System.out.println("Enter year:");
        while(!(year = scan.nextLine()).matches("20[0-9]{2,2}"))
            System.out.println("Incorrect year, try again:");
    }

    public String getMonth() {
        return month;
    }

    public void setMonth() {
        System.out.println("Enter month:");
        while(!(month = scan.nextLine()).matches("0[1-9]|1[0-2]"))
            System.out.println("Incorrect month, try again:");
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution() {
        System.out.println("Enter resolution:");
        while(!(resolution = scan.nextLine()).matches("[0-9]{3,4}x[0-9]{3,4}"))
            System.out.println("Incorrect resolution, try again:");
    }
}
