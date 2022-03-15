# cronjob
Cron Job takes the expression and converts to readable format of when the job has to schedule.

There are five fields in the job and each has the start and  end limit which can be seen in the constants.

## Business Logic:

Logic is implemented in the getCronExpressionToCronResponse method of CronParser.class
we split the input expression to string array as the expression is separated by the space. 
Then evaluate one by one within the specified limits of each value in expression.


## To run: 
run mvn clean install




