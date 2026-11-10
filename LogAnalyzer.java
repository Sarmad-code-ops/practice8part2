
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
        
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
{
    System.out.println("Hr: Count");
    int hour = 0;                                      // Q4: start
    while(hour < hourCounts.length) {                  // Q4: condition
        System.out.println(hour + ": " + hourCounts[hour]);
        hour++;                                        // Q4: step
    }
}
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
public int numberOfAccesses()
{
    int total = 0;
    // Add the value in each element of hourCounts to total.
    for(int i = 0; i < hourCounts.length; i++) {
        total += hourCounts[i];                 // Q6
    }
    return total;
}
public int busiestHour()
{
    int busiest = 0;
    for(int hour = 1; hour < hourCounts.length; hour++) {
        if(hourCounts[hour] > hourCounts[busiest]) {   // Q8
            busiest = hour;
        }
    }
    return busiest;
}
public int quiestHour()
{
    int quiest = 0;
    for(int hour =1; hour < hourCounts.length; hour++){
        if(hourCounts[hour] > hourCounts[quiest]) {
            quiest = hour;
        }
    }
    return quiest;
}
/**
 * Return the first hour of the busiest two-hour period.
 */
public int busiestTwoHour()
{
    int bestStart = 0;
    int bestSum = hourCounts[0] + hourCounts[1];
    for(int hour = 1; hour < hourCounts.length - 1; hour++) {
        int sum = hourCounts[hour] + hourCounts[hour + 1];   // Q11
        if(sum > bestSum) {
            bestSum = sum;
            bestStart = hour;
        }
    }
    return bestStart;
}
}