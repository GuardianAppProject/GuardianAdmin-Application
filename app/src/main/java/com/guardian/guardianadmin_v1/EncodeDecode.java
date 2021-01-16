package com.guardian.guardianadmin_v1;

public class EncodeDecode {

    public static String sleepDecode(double input) {
        int hour = (int)Math.round(input / 60);
        int minute = (int)Math.round(input % 60);

        if(hour<0 || minute<0){
            return "--";
        }
        return String.format("%dH:%dM",hour, minute);
    }

    public static String speedDecode(double input) {

        int speed = (int)Math.round(input);

        if(speed<0){
            return "--";
        }
        return String.format("%d (Km/H)", speed);
    }

    public static String accelerationDecode(double input) {

        if(input<0){
            return "--";
        }
        return String.format("%.2f (m2/s)", input);
    }

    public static String vibrationDecode(double input) {

        input = (int)Math.round(input);
        String vibrationOutput = "بدون لرزش";
        if(input == 0) {
            vibrationOutput = "بدون لرزش";
        } else if(input == 1) {
            vibrationOutput = "لرزش کم";
        } else if(input == 2) {
            vibrationOutput = "لرزش متوسط";
        } else if(input == 3) {
            vibrationOutput = "لرزش زیاد";
        } else if(input == 4) {
            vibrationOutput = "لرزش بسیار زیاد";
        } else {
            return "--";
        }
        return String.format("%s", vibrationOutput);
    }

    public static String timeDecode(double input) {
        int hour = (int)Math.round(input / 60);
        int start = hour - 1;
        int end = hour + 1;
        if(start<0 || end<0){
            return "--";
        }
        return String.format("%d:00 - %d:00", start, end);
    }

    public static String nearCitiesDecode(double input) {

        String nearCitiesOutput = "ایمن";
        if(input < 0) {
            return "--";
        }
        if(input <= 3) {
            nearCitiesOutput = "بسیار ایمن";
        } else if(input <= 5) {
            nearCitiesOutput = "ایمن";
        } else {
            nearCitiesOutput = "ناایمن";
        }
        return String.format("%s", nearCitiesOutput);
    }

    public static String monthDecode(double input) {

        input = Math.round(input);
        String monthOutput = "";
        if(input == 1) {
            monthOutput = "فروردین";
        } else if(input == 2) {
            monthOutput = "اردیبهشت";
        } else if(input == 3) {
            monthOutput = "خرداد";
        } else if(input == 4) {
            monthOutput = "تیر";
        } else if(input == 5) {
            monthOutput = "مرداد";
        } else if(input == 6) {
            monthOutput = "شهریور";
        } else if(input == 7) {
            monthOutput = "مهر";
        } else if(input == 8) {
            monthOutput = "آبان";
        } else if(input == 9) {
            monthOutput = "آذر";
        } else if(input == 10) {
            monthOutput = "دی";
        } else if(input == 11) {
            monthOutput = "بهمن";
        } else if(input == 12) {
            monthOutput = "اسفند";
        } else {
            monthOutput = "--";
        }
        return String.format("%s", monthOutput);
    }

    public static String weatherDecode(double input) {

        String weather = "مناسب";
        if(input < 0) {
            return "--";
        }
        if (input >= 92) {
            weather = "بسیار مناسب";
        } else if (input >= 85) {
            weather = "تقریبا مناسب";
        } else if (input >= 75) {
            weather = "مناسب";
        } else if (input >= 65) {
            weather = "نامناسب";
        } else if (input >= 55) {
            weather = "بسیار نامناسب";
        } else {
            weather = "خطرناک";
        }
        return String.format("%s", weather);
    }

    public static String withoutStopDecode(double input) {
        int hour = (int)Math.round(input / 60);
        int minute = (int)Math.round(input % 60);

        if(hour<0 || minute<0){
            return "--";
        }
        return String.format("%dH:%dM",hour, minute);
    }

//    public static String trafficDecode(double input) {
//        return ;
//    }

    public static String roadTypeDecode(double input) {

        String roadTypeOutput = "ایمن";
        if(input < 0){
            return "--";
        }

       if(input >= 95) {
           roadTypeOutput = "کاملا ایمن";
       } else if(input >= 90) {
           roadTypeOutput = "تقریبا ایمن";
       } else if(input >= 75) {
           roadTypeOutput = "ایمن";
       } else if(input >= 50) {
           roadTypeOutput = "ناایمن";
       } else if(input >= 35) {
           roadTypeOutput = "بسیار ناایمن";
       } else {
           roadTypeOutput = "خطرناک";
       }

        return String.format("%s", roadTypeOutput);
    }

    public static String calculateStatusAlgorithm(double percentage) {
        String status = "";
        if(percentage < 0) {
            return "اطلاعات ناموجود";
        }
        if(percentage >= 90) {
            status = "بسیار ایمن";
        } else if(percentage >= 70) {
            status = "ایمن";
        } else if(percentage >= 55) {
            status = "نیازمند دقت";
        } else if(percentage >= 48) {
            status = "نیازمند دقت بالا";
        } else if(percentage >= 40) {
            status = "ناایمن";
        } else if(percentage >= 30) {
            status = "ایمنی بسیار پایین";
        } else {
            status = "بسیار خطرناک";
        }

        return status;
    }
}
