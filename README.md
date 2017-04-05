# CS9053-Homework-8

## Introduction

This repo is for homework 8 of NYU CS9053 Introduction to Java. Basically, I implemented a job scheduler that do the below two tasks.

* Task 1: Derivate a job schedule that has maximum number of non-overlapped jobs can be run from given job lists for a single container.

* Task 2: Make an extension to the job scheduler that derivate a job schedule that has maximum total value of non-overlapped jobs from given job lists, now every job has an associated cost or weight, and the goal is to optimize for the largest cost.

## Implementation

There are five java files (one is tester for demo) in this implementation:

```
--- Job.java
	POJO type of job, has String jobId, Long startTime, Long endTime, and double type of cost
	
--- JobList.java
	Job list that sort by job's start time

--- LambdaScheduler.java
	has static method getJobScheduleForMaxNumberOfJobs(JobList)

--- LambdaWeightedScheduler.java
	has static method getJobScheduleForMaxValue(JobList)

--- SchedulerTester.java
	Tester for several test cases
```
