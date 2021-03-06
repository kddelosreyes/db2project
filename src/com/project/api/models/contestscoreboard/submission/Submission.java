package com.project.api.models.contestscoreboard.submission;


import com.project.api.models.Attribute;
import com.project.api.models.contestscoreboard.entitylongid.EntityLongId;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Submission extends EntityLongId {
    
    public enum Verdict {
        ACCEPTED,
        PRESENTATION_ERROR,
        WRONG_ANSWER,
        COMPILE_ERROR,
        RUNTIME_ERROR,
        TIME_LIMIT_EXCEEDED,
        MEMORY_LIMIT_EXCEEDED,
        OUTPUT_LIMIT_EXCEEDED,
        SUBMISSION_ERROR;
    }
    
    private Long timeSubmitted;
    private Verdict verdict;
    
    public Submission(Long id, Long timeSubmitted, Verdict verdict) {
        super(id, verdict.name() + ":" + timeSubmitted);
        this.timeSubmitted = timeSubmitted;
        this.verdict = verdict;
    }
    
    @Override
    public Object getAttribute(Attribute attribute) {
        SubmissionAttribute attrib = (SubmissionAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_SUBMISSION_VERDICT:
                return getVerdict();
            case ATTRIBUTE_SUBMISSION_TIME:
                return getTimeSubmitted();
            default:
                return null;
        }
    }
    
    private Verdict getVerdict() {
        return verdict;
    }
    
    private Long getTimeSubmitted() {
        return timeSubmitted;
    }
    
    @Override
    public void setAttribute(Attribute attribute, Object value) {
        SubmissionAttribute attrib = (SubmissionAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_SUBMISSION_VERDICT:
                setVerdict((Verdict) value);
                break;
            case ATTRIBUTE_SUBMISSION_TIME:
                setSubmissionTime((Long) value);
                break;
        }
    }
    
    private void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
    
    private void setSubmissionTime(Long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }
}
