package com.rivi.utils;

/**
 * Created by rivi on 26/06/17.

 */

public class Constants {
    public static final String JOBS_URL = "jobs";
    public static final String JOBS_REPORT_URL = "jobs-report";

    public static final String MERGED_CLOSED = "closed";
    public static final String MERGED = "merged";

    public static int CONNECTION_TIMEOUT = 10000;
    public static final String CLUSTER_USER = "";
    public static final String CLUSTER_PASSWORD = "";
    public static final String CLUSTER_URL = "";
    public static final String CLUSTER_NAMESPACE = "yigal";

    public static final String[] JOB_POD_BASE_COMMANDS = {"docker", "build"};

    public static final String JOB_DOCKER_IMAGE = "yigaldviri/rivi-helper";
    public static final String JOB_POD_NAME = "cfi";
    public static final String JOB_POD_CONTAINER_NAME = "cfi";
    public static final String JOB_LABEL = "job-label";
    public static final String JOB_RESTART_POLICY = "Never";
    public static final String JOB_KIND = "Job";
    public static final String JOB_API = "batch/v1";
    public static final String IMAGE_PULL_POLICY = "Always";
    public static final Long JOB_TIMEOUT_SECONDS = 10L;

}
