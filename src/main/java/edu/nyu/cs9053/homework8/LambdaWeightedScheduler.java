package edu.nyu.cs9053.homework8;

import java.util.*;

public class LambdaWeightedScheduler {
    public static List<Job> getJobScheduleForMaxValue(JobList jobList) {
        List<Job> optimalJobSchedule = new LinkedList<>();
        if (jobList == null || jobList.isEmpty()) {
            return optimalJobSchedule;
        }
        double maxCost = 0d;
        Map<Job, Double> jobCostSoFarMap = new LinkedHashMap<>(); // order preserving (by start time)
        for (Job job : jobList.getAllJobs()) {
            double cost = job.getCost();
            jobCostSoFarMap.put(job, cost);
            if (cost > maxCost) {
                maxCost = cost;
                optimalJobSchedule.add(job);
            }
        }
        Map<Job, List<Job>> path = new HashMap<>();
        for (Job job : jobList.getAllJobs()) {
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
                    double costSoFar = job.getCost() + entry.getValue();
                    if (costSoFar > jobCostSoFarMap.get(job)) {
                        jobCostSoFarMap.put(job, costSoFar);
                    }
                    if (costSoFar > maxCost) {
                        maxCost = costSoFar;
                        optimalJobSchedule = path.get(entry.getKey());
                        optimalJobSchedule.add(job);
                    }
                }
            }
            possibleJobSchedule.add(job);
            path.put(job, possibleJobSchedule);
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
