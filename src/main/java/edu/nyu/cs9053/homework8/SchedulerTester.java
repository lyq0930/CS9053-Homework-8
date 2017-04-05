package edu.nyu.cs9053.homework8;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SchedulerTester {
    public static void main(String[] args) {
        try {
            JobList jobList = new JobList();
            jobList.addJob(new Job("1", convertToEpoch("04/03/2016 08:00"), convertToEpoch("04/03/2016 08:15"), 20d));
            jobList.addJob(new Job("2", convertToEpoch("04/03/2016 08:10"), convertToEpoch("04/03/2016 08:35"), 9d));
            jobList.addJob(new Job("3", convertToEpoch("04/03/2016 08:20"), convertToEpoch("04/03/2016 08:30"), 30d));
            jobList.addJob(new Job("4", convertToEpoch("04/03/2016 08:28"), convertToEpoch("04/03/2016 08:34"), 40d));
            jobList.addJob(new Job("5", convertToEpoch("04/03/2016 08:31"), convertToEpoch("04/03/2016 08:33"), 5d));

            JobList jobList2 = new JobList();
            jobList2.addJob(new Job("1", convertToEpoch("04/03/2016 08:00"), convertToEpoch("04/03/2016 08:15"), 20d));
            jobList2.addJob(new Job("2", convertToEpoch("04/03/2016 08:10"), convertToEpoch("04/03/2016 08:35"), 9d));
            jobList2.addJob(new Job("3", convertToEpoch("04/03/2016 08:20"), convertToEpoch("04/03/2016 08:30"), 30d));
            jobList2.addJob(new Job("4", convertToEpoch("04/03/2016 08:28"), convertToEpoch("04/03/2016 08:34"), 40d));
            jobList2.addJob(new Job("5", convertToEpoch("04/03/2016 08:31"), convertToEpoch("04/03/2016 08:33"), 20d));

            JobList jobList3 = new JobList();
            jobList3.addJob(new Job("1", convertToEpoch("04/03/2016 08:00"), convertToEpoch("04/03/2016 08:15"), 20d));
            jobList3.addJob(new Job("2", convertToEpoch("04/03/2016 08:10"), convertToEpoch("04/03/2016 08:35"), 90d));
            jobList3.addJob(new Job("3", convertToEpoch("04/03/2016 08:20"), convertToEpoch("04/03/2016 08:30"), 30d));
            jobList3.addJob(new Job("4", convertToEpoch("04/03/2016 08:28"), convertToEpoch("04/03/2016 08:34"), 40d));
            jobList3.addJob(new Job("5", convertToEpoch("04/03/2016 08:31"), convertToEpoch("04/03/2016 08:33"), 20d));

            printTestCase(jobList);
            printTestCase(jobList2);
            printTestCase(jobList3);
        } catch (ParseException e) {
            System.out.print(e.getMessage());
        }
    }

    private static int numberOfJobsInSchedule(List<Job> schedule) {
        return schedule.size();
    }

    private static double valueOfJobsInSchedule(List<Job> schedule) {
        double value = 0d;
        for (Job job : schedule) {
            value += job.getCost();
        }
        return value;
    }

    private static long convertToEpoch(String dateTime) throws ParseException{
        return new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateTime).getTime() / 1000;
    }

    private static void printTestCase(JobList jobList) {
        List<Job> optimalJobScheduleForMaxNumber = LambdaScheduler.getMaxNumberOfJobs(jobList);
        List<Job> optimalJobScheduleForMaxValue = LambdaWeightedScheduler.getJobScheduleForMaxValue(jobList);
        System.out.printf("For the given %d jobs, the maximum number of jobs the container can accept is %d: ",
                jobList.size(), numberOfJobsInSchedule(optimalJobScheduleForMaxNumber));
        printJobSchedule(optimalJobScheduleForMaxNumber);

        System.out.printf("For the given %d jobs, the maximum value of jobs the container can accept is %.2f: ",
                jobList.size(), valueOfJobsInSchedule(optimalJobScheduleForMaxValue));
        printJobSchedule(optimalJobScheduleForMaxValue);
    }

    private static void printJobSchedule(List<Job> list) {
        System.out.printf("[ ");
        for (Job job : list) {
            System.out.printf("%s ", job.getJobId());
        }
        System.out.printf("]%n");
    }

}
