package utils;

public class SuiteTimeTracker {

    private static long suiteStartTime;
    private static long suiteEndTime;

    public static void startSuite() {
        suiteStartTime = System.currentTimeMillis();
    }

    public static void endSuite() {
        suiteEndTime = System.currentTimeMillis();
    }

    public static String getSuiteExecutionTime() {
        long duration = suiteEndTime - suiteStartTime;

        long seconds = (duration / 1000) % 60;
        long minutes = (duration / (1000 * 60)) % 60;
        long hours   = (duration / (1000 * 60 * 60));

        return String.format("%02d:%02d:%02d (HH:MM:SS)", hours, minutes, seconds);
    }
}