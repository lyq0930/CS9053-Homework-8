package edu.nyu.cs9053.homework8;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LambdaWeightedScheduler {
    public static List<Job> getJobScheduleForMaxValue(JobList jobList) {
        List<Job> optimalJobSchedule = new LinkedList<>();
        if (jobList == null || jobList.isEmpty()) {
            return optimalJobSchedule;
        }
        Map<Job, Double> jobCostSoFarMap = new LinkedHashMap<>(); // order preserving (by start time)
        double maxCost = 0d;
        for (Job job : jobList.getAllJobs()) {
            double costSoFar = job.getCost();
            List<Job> possibleJobSchedule = new LinkedList<>();
            for (Map.Entry<Job, Double> entry : jobCostSoFarMap.entrySet()) {
                /*
                Since jobCostSoFarMap is order preserving (by start time),
                so if we reach to current Job, we know no more earlier job exists, break the iteration.
                 */
                if (entry.getKey() == job) {
                    break;
                }

                // The start earlier job is end before current job, non-overlapped
                if (isEarlierJobNonOverlapped(entry.getKey(), job)) {
                    possibleJobSchedule.add(entry.getKey());
                    double costAddFromThisEarlierJob = entry.getValue() + job.getCost();
                    costSoFar = costAddFromThisEarlierJob > costSoFar ? costAddFromThisEarlierJob : costSoFar;
                }
            }
            jobCostSoFarMap.put(job, costSoFar);
            possibleJobSchedule.add(job);
            if (costSoFar > maxCost) {
                maxCost = costSoFar;
                optimalJobSchedule = possibleJobSchedule;
            }
        }
        return optimalJobSchedule;
    }

    private static boolean isEarlierJobNonOverlapped(Job earlier, Job later) {
        return lessThanEqual(earlier.getEndTime(), later.getStartTime());
    }

    private static boolean lessThanEqual(Long small, Long large) {
        return small.compareTo(large) <= 0;
    }
}
