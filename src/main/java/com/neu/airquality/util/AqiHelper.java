package com.neu.airquality.util;

public class AqiHelper {
        private static final double[][] BREAKPOINTS = {
                {0, 50, 150, 475, 800, 1600},
                {0, 5, 10, 35, 60, 90},
                {0, 35, 75, 115, 150, 250}
        };
        private static final int[] AQI_VALUES = {1, 2, 3, 4, 5, 6};

        public static int calculateAQI(double so2Concentration, double coConcentration, double pmConcentration) {
            int aqiSo2 = calculateAQISubIndex(so2Concentration, BREAKPOINTS[0]);
            int aqiCo = calculateAQISubIndex(coConcentration, BREAKPOINTS[1]);
            int aqiPm = calculateAQISubIndex(pmConcentration, BREAKPOINTS[2]);
            return Math.max(Math.max(aqiSo2, aqiCo), aqiPm);
        }

        private static int calculateAQISubIndex(double concentration, double[] breakpoints) {
            int index = 0;
            while (index < breakpoints.length - 1 && concentration > breakpoints[index + 1]) {
                index++;
            }
            double leftPoint = breakpoints[index];
            double rightPoint = breakpoints[index + 1];
            int leftAqi = AQI_VALUES[index];
            int rightAqi = AQI_VALUES[index + 1];
            return (int) Math.round((rightAqi - leftAqi) / (rightPoint - leftPoint) * (concentration - leftPoint) + leftAqi);
        }
    }
