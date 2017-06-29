package com.rivi.controllers;

import com.rivi.model.GitHubHook;
import com.rivi.services.interfaces.JobService;
import com.rivi.utils.Constants;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by rivi on 26/06/17.
 *
 */

@RestController()
public class CfiController {

    private JobService jobService;

    @Inject
    public CfiController(JobService jobService){
        this.jobService = jobService;
    }

    @RequestMapping(method = RequestMethod.POST, value = Constants.JOBS_URL)
    public List<String> execute(@RequestBody GitHubHook gitHubHook){
        return jobService.createJob(gitHubHook);
    }

    @RequestMapping(method = RequestMethod.GET, value = Constants.JOBS_URL)
    public String status(){
        return jobService.getStatus();
    }

    @RequestMapping(method = RequestMethod.GET, value = Constants.JOBS_REPORT_URL)
    public List<String> report(){
        return jobService.getReport();
    }
}
