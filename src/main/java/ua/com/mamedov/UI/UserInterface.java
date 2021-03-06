package ua.com.mamedov.UI;

import ua.com.mamedov.CalendarUtils.Converter;
import ua.com.mamedov.CalendarUtils.DateFormat;
import ua.com.mamedov.Operation_with_date.TimeCalculation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final DateFormat dateFormat = new DateFormat();
    Converter converter = new Converter(dateFormat);
    TimeCalculation timeCalculation = new TimeCalculation(converter);


    public void run() {
        System.out.println("Select your output option");
        try {
            dateFormat();
            String c = reader.readLine();
            dateFormatChoice(c);
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong. Try again.");
        }

        System.out.println("Select your option");
        String position;
        try {
            runMainMenuNavigation();
            while ((position = reader.readLine()) != null) {
                choice(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong. Try again.");
        }
    }

    private void choice(String position) throws IOException {
        switch (position) {
            case "1" -> timeCalculation.add();
            case "2" -> timeCalculation.subtract();
            case "3" -> timeCalculation.difference();
            case "4" -> timeCalculation.comparator();
        }
        runMainMenuNavigation();
    }

    private void runMainMenuNavigation() {
        System.out.println();
        System.out.println("1 - Add seconds, minutes, hours, days, months or years to date");
        System.out.println("2 - Subtract seconds, minutes, hours, days, months or years from date");
        System.out.println("3 - Difference between dates");
        System.out.println("4 - Compare dates");
        System.out.println("0 - Exit");
        System.out.println();
    }

    private void dateFormatChoice(String choice) throws IOException {
        switch (choice) {
            case "1" -> dateFormat.setDdMmYy(true);
            case "2" -> dateFormat.setMDYyyy(true);
            case "3" -> dateFormat.setMmmDYy(true);
            case "4" -> dateFormat.setDdMmYyAndTime(true);
        }
    }

    private void dateFormat() throws IOException {
        System.out.println();
        System.out.println("Please choose date and time format:");
        System.out.println("1 - dd/mm/yy. For example 1/10/21");
        System.out.println("2 - m/d/yyyy. For example 3/4/2021");
        System.out.println("3 - mmm-d-yy. For example March-4-21");
        System.out.println("4 - dd-mmm-yyyy 00:00. For example 09-April-1789 00:00");
        System.out.println("0 - Continue and set default");
        System.out.println();
    }
}
