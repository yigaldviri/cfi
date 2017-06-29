package com.rivi.services.interfaces;

import com.rivi.model.GitHubHook;

import java.util.List;

/**
 * Created by rivi on 26/06/17.
 *
 */

public interface JobService {

    List<String> createJob(GitHubHook gitHubHook);

    String getStatus();

    List<String> getReport();

    void audit(String aud);
}
