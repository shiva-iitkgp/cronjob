import constants.Constants;
import job.CronParser;
import model.CronResponse;

import java.util.Scanner;

public class CronJobApp {
    public static void main(String [] strings)
    {
        runCronParser();
    }

    /*
        While loop runs indefinitely taking the
        input from the user.
     */
    private static void runCronParser(){
        while(true){
            System.out.println("Provide valid cron Job");
            Scanner scanner = new Scanner(System.in);
            String cronExpression = scanner.nextLine();

            CronParser parser = new CronParser();

            try{

                CronResponse cronResponse = parser.getCronExpressionToCronResponse(cronExpression);
                System.out.println(Constants.MINUTES + cronResponse.getMinute());
                System.out.println(Constants.HOURS+ cronResponse.getHour());
                System.out.println(Constants.DAY_OF_MONTH+ cronResponse.getDayOfMonth());
                System.out.println(Constants.MONTH + cronResponse.getMonth());
                System.out.println(Constants.DAY_OF_WEEK + cronResponse.getDayOfWeek());
                System.out.println(Constants.COMMAND+ cronResponse.getCommand());

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

}
