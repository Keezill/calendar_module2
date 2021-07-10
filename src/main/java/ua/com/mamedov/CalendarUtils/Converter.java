package ua.com.mamedov.CalendarUtils;

public class Converter {

    public static long yearToSec(int year) {
        long sec;
        if (year % 4 == 0 && year % 100 != 0 && year % 400 == 0) {
            sec = year * 31622400L;
        } else {
            sec = year * 31536000L;
        }
        return sec;
    }

    public static long monthToSec(int month, int year) {
        if (month == 2 && year % 4 == 0) {
            return 29 * 86400;
        } else if (month == 2) {
            return 28 * 86400;
        } else if ((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)) {
            return 31 * 86400;
        } else {
            return 30 * 86400;
        }
    }

    public static long monthToSec(int month) {
        return (long) month * 30 * 86400;
    }

    public static int daysToSec(int number) {
        return number * 86400;
    }

    public static int hoursToSec(int number) {
        return number * 3600;
    }

    public static int minToSec(int number) {
        return number * 60;
    }

    public static String convertFromSecToDate(long sec) {
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int year = (int) (sec / 31536000L);
        int leap = 2419200;
        if (year % 4 == 0) {
            leap = 2505600;
        }
        long duration = sec % 31536000L;

        while (duration > leap) {
            month++;
            if (month == 2 && year % 4 == 0) {
                duration -= 29 * 86400;
            } else if (month == 2) {
                duration -= 28 * 86400;
            } else if ((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)) {
                duration -= 31 * 86400;
            } else {
                duration -= 30 * 86400;
            }

        }
        while (duration >= 86400) {
            day++;
            duration -= 86400;
        }

        while (duration >= 3600) {
            hour++;
            duration -= 3600;
        }
        while (duration >= 60) {
            minute++;
            duration -= 60;
        }
        while (duration > 0) {
            second++;
            duration -= 1;
        }
        if (day == 0) {
            day = 1;
        }
        if (month == 2 && year % 4 == 0) {
            while (day > 29) {
                day--;
            }
        } else if (month == 2) {
            while (day > 28) {
                day--;
            }
        } else if ((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)) {
            while (day >= 31) {
                day--;
            }
        } else {
            while (day > 30) {
                day--;
            }
        }
        if (month == 0) {
            year--;
            month = 12;
        }
        DateFormat dateFormat = new DateFormat();

        if (dateFormat.isDdMmYy()) {
            String yearNew = String.valueOf(year);
            return day + "/" + month + "/" + (yearNew.length() - 1) + (yearNew.length() - 2);
        } else if (dateFormat.isMDYyyy()) {
            return month + "/" + day + "/" + year;
        } else if (!dateFormat.isMmmDYy()) {
            String monthString = switch (month) {
                case 1 -> "January";
                case 2 -> "February";
                case 3 -> "March";
                case 4 -> "April";
                case 5 -> "May";
                case 6 -> "June";
                case 7 -> "July";
                case 8 -> "August";
                case 9 -> "September";
                case 10 -> "October";
                case 11 -> "November";
                case 12 -> "December";
                default -> "Unknown";
            };
            return monthString + "-" + day + "-" + year;
        }
        return day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
    }
}

