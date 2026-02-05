package utils;

public class SuiteTimeTracker {

    private static long suiteStartTime;
    private static long suiteEndTime;

    public static void startSuite() {
        try {
        	suiteStartTime = System.currentTimeMillis();
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    public static void endSuite() {
       try {
    	   suiteEndTime = System.currentTimeMillis();
       }
       catch(Exception e) {
    	   System.out.println(e.getMessage());
       }
    }

    public static String getSuiteExecutionTime() {
      try {
    	  long duration = suiteEndTime - suiteStartTime;
          long seconds = (duration / 1000) % 60;
          long minutes = (duration / (1000 * 60)) % 60;
          long hours   = (duration / (1000 * 60 * 60));

          return String.format("%02d:%02d:%02d (HH:MM:SS)", hours, minutes, seconds);
      }
      catch(Exception e) {
    	  System.out.println(e.getMessage());
    	  return null;
      }
    }
}