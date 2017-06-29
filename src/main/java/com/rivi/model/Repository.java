package com.rivi.model;

/**
 * Created by dviyi01 on 6/27/2017.
 *
 */

public class Repository {
    String full_name;
    String clone_url;

    public String getFull_name(){ return full_name;}
    public void setFull_name(String full_name) { this.full_name = full_name;}

    public String getClone_url(){ return clone_url;}
    public void setClone_url(String clone_url) { this.clone_url = clone_url;}


}
