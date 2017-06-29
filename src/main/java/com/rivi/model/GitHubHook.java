package com.rivi.model;

/**
 * Created by dviyi01 on 6/27/2017.
 *
 */

public class GitHubHook {

    String action;
    PullRequest pull_request;
    Repository repository;

    public String getAction(){return action;}
    public void setAction(String action){this.action = action;}

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
