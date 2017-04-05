package edu.nyu.cs9053.homework8;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JobList {

    private Collection<Job> jobList;

    public JobList() {
        jobList = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job firstJob, Job secondJob) {
                return firstJob.getStartTime().compareTo(secondJob.getStartTime());
            }
        });
    }

    public void addJob(Job job) {
        jobList.add(job);
    }

    public Collection<Job> getAllJobs() {
        return jobList;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return jobList.size();
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobList that = (JobList) o;
        return jobList == null ? that.jobList == null : jobList.equals(that.jobList);
    }

    @Override public int hashCode() {
        return jobList == null ? 0 : jobList.hashCode();
    }
}
