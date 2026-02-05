package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TimeUtil {

    public static String getCurrentDateTime() {
       try {
    	   return LocalDateTime.now()
                   .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
       }
       catch(Exception e) {
    	   System.out.println(e.getMessage());
    	   return null;
       }
    }
    
    public static String getCurrentDateWithFormate(String format) {
        try {
        	DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(format);        	
        	String currentDate =
                    LocalDate.now().format(formatter);        	
     	   return currentDate;
        }
        catch(Exception e) {
     	   System.out.println(e.getMessage());
     	   return null;
        }
     }
    
    public static String getAddDaysToCurrentDate(String currentDate, String format, String daysToAdd) {
        try {
        	LocalDate date;
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                    .withResolverStyle(ResolverStyle.STRICT);
        	if(format.equalsIgnoreCase("yyyy-dd-MM")) {
        		 String[] parts = currentDate.split("-");
                 if (parts.length != 3) {
                     throw new IllegalArgumentException("Invalid date format: " + currentDate);
                 }

                 String isoDate = parts[0] + "-" + parts[2] + "-" + parts[1]; // yyyy-MM-dd
                  date = LocalDate.parse(isoDate);
        	} else {
        	  	 date = LocalDate.parse(currentDate.trim(), formatter);
        	}
 
        	LocalDate newDate = date.plusDays(Integer.parseInt(daysToAdd));
            return newDate.format(formatter);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static String getCurrentTimeWithFormate(String format) {
        try {
        	DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(format);
            String currentTime =
                    LocalTime.now().format(formatter);        	
     	   return currentTime.toUpperCase();
        }
        catch(Exception e) {
     	   System.out.println(e.getMessage());
     	   return null;
        }
     }
    
    
    
    public static String getAddHoursToCurrentTime(String actualTime, String addhours, String format) {
        try {
        	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, java.util.Locale.US);
//        	DateTimeFormatter formatter =
//                    DateTimeFormatter.ofPattern(format); 
        	 LocalTime time = LocalTime.parse(actualTime.toUpperCase().trim(), formatter);
        	 LocalTime newTime = time.plusHours(Integer.parseInt(addhours));
   		 	String addedTime = newTime.format(formatter);
   		 	return newTime.format(formatter).toUpperCase();
        }
        catch(Exception e) {
     	   System.out.println(e.getMessage());
     	   return null;
        }
     }
    
    public static String dateConversion(String date, String inputFormat, String outputFormat) {
    	try {
            DateTimeFormatter inputFormatter =
                    DateTimeFormatter.ofPattern(inputFormat);

            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern(outputFormat);

            // Try parsing date + time
            try {
                LocalDateTime dateTime =
                        LocalDateTime.parse(date, inputFormatter);
                return dateTime.format(outputFormatter);
            } catch (DateTimeParseException e) {
                // Fallback: date only
                LocalDate localDate =
                        LocalDate.parse(date, inputFormatter);
                return localDate.format(outputFormatter);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Invalid date or format. Date: " + date +
                    ", InputFormat: " + inputFormat +
                    ", OutputFormat: " + outputFormat, e);
        }
    }
}