package job;

import constants.Constants;
import exceptions.CronLengthException;
import exceptions.IllegalexpressionException;
import model.CronResponse;

public class CronParser {

    public CronParser() {
    }

    /*
        This function adds the required components
     */
    public CronResponse getCronExpressionToCronResponse(String expression) throws Exception {
        String[] exps = expression.split(" ");
        if(exps.length != Constants.CRON_EXPRESSION_LENGTH) {
            throw  new CronLengthException("Incorrect expression");
        }

        CronResponse cronResponse = new CronResponse();
        cronResponse.setMinute(parseCronExpressionToValue(exps[0], Constants.MINUTES_LIMIT_START,
                Constants.MINUTES_LIMIT ));
        cronResponse.setHour(parseCronExpressionToValue(exps[1], Constants.HOURS_LIMIT_START, Constants.HOURS_LIMIT));
        cronResponse.setDayOfMonth(parseCronExpressionToValue(exps[2],Constants.DAY_OF_MONTH_LIMIT_START,
                Constants.DAY_OF_MONTH_LIMIT ));
        cronResponse.setMonth(parseCronExpressionToValue(exps[3],Constants.MONTH_LIMIT_START,
                Constants.MONTH_LIMIT ));
        cronResponse.setDayOfWeek(parseCronExpressionToValue(exps[4],Constants.DAY_OF_WEEK_LIMIT_START,
                Constants.DAY_OF_WEEK_LIMIT ));
        cronResponse.setCommand(exps[5]);

        return cronResponse;

    }

    private String parseCronExpressionToValue(String expression, int start, int end) throws IllegalexpressionException
    {
        StringBuilder parser = new StringBuilder();
        if(expression.equals("*")) {
            for(int i = start; i<= end; i++) parser.append(i).append(" ");
        }else if(expression.contains("/")){
            int divider = Integer.parseInt(expression.substring(2));
            for(int i = start; i<= end; )
            {
                parser.append(i).append(" ");
                i += divider;
            }
        }else if(expression.contains(",")){
            String[] split = expression.split(",");

            for(String str : split) {
                if(Integer.parseInt(str) < start || Integer.parseInt(str) > end)
                    throw new IllegalexpressionException("Limits are more than allowed");
                parser.append(str).append(" ");
            }
        }else if(expression.contains("-")){
            String[] split = expression.split("-");
            if(Integer.parseInt(split[0]) < start || Integer.parseInt(split[0]) > end||
                    Integer.parseInt(split[1]) < start || Integer.parseInt(split[1]) > end)
            {
                throw new IllegalexpressionException("Limits are more than allowed");
            }
            for(int i = Integer.parseInt(split[0]) ; i<= Integer.parseInt(split[1]); i++)
                parser.append(i).append(" ");
        }
        else
            parser.append(expression);

        return parser.toString();
    }




}
