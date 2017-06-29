package com.rivi.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dviyi01 on 6/27/2017.
 *
 */

// TODO should access MySql
@Repository
public class JobsDao {

    private static List<String> fakeDB = new ArrayList<>();

    public JobsDao(){}

    public List<String> getRecords() {
        return fakeDB;
    }

    public void addRecord(String record) {
        JobsDao.fakeDB.add(record);
    }
}
