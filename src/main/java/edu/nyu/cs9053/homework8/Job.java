package edu.nyu.cs9053.homework8;

public class Job {
    private final String jobId;
    private final Long startTime;
    private final Long endTime;
    private final double cost;

    public Job(String jobId, Long startTime, Long endTime) {
        this(jobId, startTime, endTime, 0d);
    }

    public Job(String jobId, Long startTime, Long endTime, double cost) {
        this.jobId = jobId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    public String getJobId() {
        return jobId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public double getCost() {
        return cost;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job that = (Job) o;
        return cost == that.cost
                && (jobId == null ? that.jobId == null : jobId.equals(that.jobId))
                && (startTime == null ? that.startTime == null : startTime.equals(that.startTime))
                && (endTime == null ? that.endTime == null : endTime.equals(that.endTime));
    }

    @Override public int hashCode() {
        int hash = Double.valueOf(cost).hashCode();
        int prime = 31;
        hash = prime * hash + (jobId == null ? 0 : jobId.hashCode());
        hash = prime * hash + (startTime == null ? 0 : startTime.hashCode());
        hash = prime * hash + (endTime == null ? 0 : endTime.hashCode());
        return hash;
    }
}
