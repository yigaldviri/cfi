package com.rivi.services;
import com.rivi.dao.JobsDao;
import com.rivi.model.GitHubHook;
import com.rivi.services.interfaces.JobService;
import com.rivi.utils.Constants;
import com.rivi.utils.Utils;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Job;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rivi on 26/06/17.
 *
 */

@Service
public class JobServiceImpl implements JobService {

    private CreateJobAction createJobAction;
    private JobsStatusAction jobsStatusAction;
    private JobsDao jobsDao;

    @Inject
    public JobServiceImpl(CreateJobAction createJobAction,
                          JobsStatusAction jobsStatusAction,
                          JobsDao jobsDao){
        this.createJobAction = createJobAction;
        this.jobsStatusAction = jobsStatusAction;
        this.jobsDao = jobsDao;
    }

    @Override
    public List<String> createJob(GitHubHook gitHubHook){
        if (!shouldCreateJob(gitHubHook)) {
            return Collections.EMPTY_LIST;
        }

        audit(Utils.parseGitHubData(gitHubHook));
        Job jobObject = Utils.createJobObject(gitHubHook);
        return this.createJobAction.execute(toMetadatas(jobObject));
    }

    @Override
    public String getStatus() {
        return jobsStatusAction.getStatus();
    }

    @Override
    public List<String> getReport() {
        return jobsDao.getRecords();
    }

    @Override
    public void audit(String record) {
        jobsDao.addRecord(record);
    }

    private ArrayList<HasMetadata> toMetadatas(Job jobObject) {
        ArrayList<HasMetadata> hasMetadatas = new ArrayList<>();
        hasMetadatas.add(jobObject);
        return hasMetadatas;
    }

    private Boolean shouldCreateJob(GitHubHook gitHubHook) {
        return
                Constants.MERGED_CLOSED.equalsIgnoreCase(gitHubHook.getAction()) &&
                Boolean.TRUE.equals(gitHubHook.getPull_request().getMerged());
    }

}
