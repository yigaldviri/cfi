package com.rivi.model;

/**
 * Created by dviyi01 on 6/27/2017.
 *
 */

public class PullRequest {

    Base base;
    Boolean merged;

    public Boolean getMerged() {
        return merged;
    }

    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
