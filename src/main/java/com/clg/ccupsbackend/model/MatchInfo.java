package com.clg.ccupsbackend.model;

/**
 * MatchInfo
 */
public class MatchInfo {

    private int startIndex;
    private int endIndex;
    private String fieldName;
    private String fullMatch;
    private String value;

    public int getStartIndex() {
        return startIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFullMatch() {
        return fullMatch;
    }

    public void setFullMatch(String fullMatch) {
        this.fullMatch = fullMatch;
    }

    

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}