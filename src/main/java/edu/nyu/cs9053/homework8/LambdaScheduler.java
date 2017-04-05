package edu.nyu.cs9053.homework8;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LambdaScheduler {

    public static List<Job> getMaxNumberOfJobs(JobList jobList) {
        List<Job> optimalJobSchedule = new LinkedList<>();
        if (jobList == null || jobList.isEmpty()) {
            return optimalJobSchedule;
        }
        Queue<Job> endTimeJobHeap = new PriorityQueue<>(jobList.size(), new Comparator<Job>() {
            @Override public int compare(Job firstJob, Job secondJob) {
                return firstJob.getEndTime().compareTo(secondJob.getEndTime());
            }
        });
        for (Job job : jobList.getAllJobs()) {
            endTimeJobHeap.offer(job);
        }
        optimalJobSchedule.add(endTimeJobHeap.peek());
        for (Job job : jobList.getAllJobs()) {
            Long earliestEndTime = endTimeJobHeap.peek().getEndTime();
            Long jobStartTime = job.getStartTime();
            if (lessThanEqual(earliestEndTime, jobStartTime)) {
                endTimeJobHeap.poll();
                optimalJobSchedule.add(job);
            }
        }
        return optimalJobSchedule;
    }

    private static boolean lessThanEqual(Long small, Long large) {
        return small.compareTo(large) <= 0;
    }
}