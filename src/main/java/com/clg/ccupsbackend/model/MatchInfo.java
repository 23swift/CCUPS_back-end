package com.clg.ccupsbackend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchInfo
 */
public class MatchInfo {

    private String fullMatch;
    public List<MatchInfoDetails> Details=new ArrayList<MatchInfoDetails>();

    public String getFullMatch() {
        return fullMatch;
    }

    public void setFullMatch(String fullMatch) {
        this.fullMatch = fullMatch;
    }
}