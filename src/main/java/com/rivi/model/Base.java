package com.rivi.model;

/**
 * Created by dviyi01 on 6/27/2017.
 *
 */

public class Base {

    String ref;
    String sha;
    User user;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
