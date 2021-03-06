package ua.com.mamedov.Operation_with_date;

import ua.com.mamedov.Entity.TimeContainer;
import ua.com.mamedov.CalendarUtils.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeCalculation {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    TimeContainer timeContainer = new TimeContainer();
    private final Converter converter;

    public TimeCalculation(Converter converter) {
        this.converter = converter;
    }

    public List<Long> dateList = new ArrayList<>();

    public void create() throws IOException {
        try {
            String date = reader.readLine();
            String[] splitDate = new String[0];
            if (date.contains("/")) {
                splitDate = date.split("/");
            } else if (date.contains("-")) {
                splitDate = date.split("-");
            }
            long sum = 0;
            if (Integer.parseInt(splitDate[0]) <= 31 && Integer.parseInt(splitDate[0]) > 0
                    || Integer.parseInt(splitDate[1]) <= 12 && Integer.parseInt(splitDate[1]) > 0) {
                long months = 0;
                for (int i = 1; i <= Integer.parseInt(splitDate[1]); i++) {
                    months += converter.monthToSec(i, Integer.parseInt(splitDate[2]));
                }

                sum = converter.daysToSec(Integer.parseInt(splitDate[0])) +
                        months +
                        converter.yearToSec(Integer.parseInt(splitDate[2]));
            }
            if (date.length() <= 10) {
                timeContainer.setSeconds(sum);
                dateList.add(timeContainer.getSeconds());
            } else {
                parser(date, sum);
            }
        } catch (IllegalArgumentException |
                StringIndexOutOfBoundsException |
                ArrayIndexOutOfBoundsException |
                IOException e){
            System.out.println("Sorry, incorrect date. Try again!");
            create();
        }
    }

    private void parser(String date, long sum) throws IOException {
        try {
            String duration = date.substring(date.indexOf(" ") + 1);
            String[] partTimes = duration.split(":");
            if (partTimes.length < 3) {
                sum += converter.minToSec(Integer.parseInt(partTimes[0])) +
                        Integer.parseInt(partTimes[1]);
            } else {
                sum += converter.hoursToSec(Integer.parseInt(partTimes[0])) +
                        converter.minToSec(Integer.parseInt(partTimes[1])) +
                        Integer.parseInt(partTimes[2]);
            }

        } catch (StringIndexOutOfBoundsException |
                IllegalArgumentException |
                ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry, incorrect date. Try again!");
            create();
        }
    }


    public void add() throws IOException {
        System.out.println("Enter date to add. For example: 11/12/2013 or 11-12-2013");
        create();
        System.out.println();
        System.out.println("Enter what you want to add: ");
        System.out.println("1 - Seconds");
        System.out.println("2 - Minutes");
        System.out.println("3 - Hours");
        System.out.println("4 - Days");
        System.out.println("5 - Months");
        System.out.println("6 - Years");
        System.out.println("0 - Return");
        String read;
        try {
            while ((read = reader.readLine()) != null) {
                switch (read) {
                    case "1" -> {
                        System.out.println("Enter number of seconds you want to add:");
                        int second = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + second);
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "2" -> {
                        System.out.println("Enter number of minutes you want to add:");
                        int minute = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + converter.minToSec(minute));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "3" -> {
                        System.out.println("Enter number of hours you want to add:");
                        int hour = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + converter.hoursToSec(hour));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "4" -> {
                        System.out.println("Enter number of days you want to add:");
                        int days = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + converter.daysToSec(days));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "5" -> {
                        System.out.println("Enter number of months you want to add:");
                        int months = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + converter.monthToSec(months));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "6" -> {
                        System.out.println("Enter number of years you want to add:");
                        int years = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() + converter.yearToSec(years));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Incorrect choice. Try again!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Incorrect number. Try again");
            add();
        }
    }

    public void subtract() throws IOException {
        System.out.println("Enter date to subtract:");
        create();
        System.out.println();
        System.out.println("Enter what you want to subtract:");
        System.out.println("1 - Seconds");
        System.out.println("2 - Minutes");
        System.out.println("3 - Hours");
        System.out.println("4 - Days");
        System.out.println("5 - Months");
        System.out.println("6 - Years");
        System.out.println("0 - Return");
        String read;
        try {
            while ((read = reader.readLine()) != null) {
                switch (read) {
                    case "1" -> {
                        System.out.println("Enter number of seconds you want to subtract:");
                        int second = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - second);
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "2" -> {
                        System.out.println("Enter number of minutes you want to subtract:");
                        int minute = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - converter.minToSec(minute));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "3" -> {
                        System.out.println("Enter number of hours you want to subtract:");
                        int hour = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - converter.hoursToSec(hour));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "4" -> {
                        System.out.println("Enter number of days you want to subtract:");
                        int days = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - converter.daysToSec(days));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "5" -> {
                        System.out.println("Enter number of months you want to subtract:");
                        int months = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - converter.monthToSec(months));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "6" -> {
                        System.out.println("Enter number of years you want to subtract:");
                        int years = Integer.parseInt(reader.readLine());
                        timeContainer.setSeconds(timeContainer.getSeconds() - converter.yearToSec(years));
                        System.out.println(converter.convertFromSecToDate(timeContainer.getSeconds()));
                        return;
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Incorrect choice. Try again!");
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry! Incorrect number. Try again");
            subtract();
        }
    }

    public void difference() throws IOException {
        System.out.println("Please enter first date");
        create();
        System.out.println("Please enter second date");
        create();
        Collections.sort(dateList);

        System.out.println("Enter in what units you want to see difference:");
        System.out.println("1 - Seconds");
        System.out.println("2 - Minutes");
        System.out.println("3 - Hours");
        System.out.println("4 - Days");
        System.out.println("5 - Months");
        System.out.println("6 - Years");
        System.out.println("0 - Return");

        dateList.clear();
        String read;
        try {
            while ((read = reader.readLine()) != null) {
                switch (read) {
                    case "1" -> {
                        timeContainer.setSeconds(dateList.get(1) - dateList.get(0));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "2" -> {
                        timeContainer.setSeconds((dateList.get(1) / 60) - (dateList.get(0) / 60));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "3" -> {
                        timeContainer.setSeconds((dateList.get(1) / 3600) - (dateList.get(0) / 3600));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "4" -> {
                        timeContainer.setSeconds((dateList.get(1) / 86400) - (dateList.get(0) / 86400));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "5" -> {
                        timeContainer.setSeconds((dateList.get(1) / 2628000) - (dateList.get(0) / 2628000));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "6" -> {
                        timeContainer.setSeconds((dateList.get(1) / 31536000L) - (dateList.get(0) / 31536000L));
                        System.out.println(timeContainer.getSeconds());
                        return;
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Incorrect choice. Try again!");
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry! Incorrect number. Try again");
            difference();
        }
    }

    public void comparator() {
        System.out.println("1 - Add date to compare");
        System.out.println("2 - Clear the list");
        System.out.println("3 - Display dates in ascending order");
        System.out.println("4 - Display dates in descending order");
        System.out.println("0 - Return");

        String read;
        try {
            while ((read = reader.readLine()) != null) {
                switch (read) {
                    case "1" -> {
                        create();
                        comparator();
                    }
                    case "2" -> {
                        dateList.clear();
                        comparator();
                    }
                    case "3" -> {
                        Collections.sort(dateList);
                        for (Long aLong : dateList) {
                            System.out.println(converter.convertFromSecToDate(aLong));
                        }
                        return;
                    }
                    case "4" -> {
                        Collections.sort(dateList);
                        Collections.reverse(dateList);
                        for (Long aLong : dateList) {
                            System.out.println(converter.convertFromSecToDate(aLong));
                        }
                        return;
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Incorrect choice. Try again!");
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("Sorry! Incorrect number. Try again");
            comparator();
        }
    }
}

