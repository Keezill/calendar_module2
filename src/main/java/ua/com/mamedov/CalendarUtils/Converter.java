package ua.com.mamedov.CalendarUtils;

public class Converter {

    DateFormat dateFormat = new DateFormat();

    public long yearToSec(int year) {
        long sec;
        if (year % 4 == 0 && year % 100 != 0 && year % 400 == 0) {
            sec = year * 31622400L;
        } else {
            sec = year * 31536000L;
        }
        return sec;
    }

    public long monthToSec(int month, int year) {
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

    public long monthToSec(int month) {
        return (long) month * 30 * 86400;
    }

    public int daysToSec(int number) {
        return number * 86400;
    }

    public int hoursToSec(int number) {
        return number * 3600;
    }

    public int minToSec(int number) {
        return number * 60;
    }

    public String convertFromSecToDate(long sec) {
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

        if (dateFormat.isDdMmYy()) {
            String yearNew = String.valueOf(year);
            return day + "/" + month + "/" + (yearNew.length() - 1) + (yearNew.length() - 2);
        } else if (dateFormat.isMDYyyy()) {
            return month + "/" + day + "/" + year;
        } else if (dateFormat.isMmmDYy()) {
            String monthString = "Unknown";
            switch (month) {
                case 1:
                    monthString = "January";
                    break;
                case 2:
                    monthString = "February";
                    break;
                case 3:
                    monthString = "March";
                    break;
                case 4:
                    monthString = "April";
                    break;
                case 5:
                    monthString = "May";
                    break;
                case 6:
                    monthString = "June";
                    break;
                case 7:
                    monthString = "July";
                    break;
                case 8:
                    monthString = "August";
                    break;
                case 9:
                    monthString = "September";
                    break;
                case 10:
                    monthString = "October";
                    break;
                case 11:
                    monthString = "November";
                    break;
                case 12:
                    monthString = "December";
                    break;
            }
            return monthString + "-" + day + "-" + year;
        }
        return day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
    }
}

