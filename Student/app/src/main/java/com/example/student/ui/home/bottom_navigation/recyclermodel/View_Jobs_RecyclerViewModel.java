package com.example.student.ui.home.bottom_navigation.recyclermodel;

public class View_Jobs_RecyclerViewModel {

    String JobList;
    String Jobs;

    public View_Jobs_RecyclerViewModel(String jobList, String jobs) {
        JobList = jobList;
        Jobs = jobs;
    }

    public String getJobList() {
        return JobList;
    }

    public void setJobList(String jobList) {
        JobList = jobList;
    }

    public String getJobs() {
        return Jobs;
    }

    public void setJobs(String jobs) {
        Jobs = jobs;
    }
}
