/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.team;

import com.project.api.models.Attribute;
import com.project.api.models.submission.Submission;
import com.project.api.models.entitylongid.EntityLongId;
import com.project.api.models.entitylongid.EntityLongIdAttribute;
import com.project.api.models.problem.Problem;
import com.project.api.models.submission.Submission.Verdict;
import com.project.api.models.submission.SubmissionAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Team extends EntityLongId {

    private final Long PENALTY_MINUTE = 20L;
    private String schoolName;
    private String teamName;
    private final Map<Problem, List<Submission>> submissions;
    private final Map<Problem, Long> problemsSolved;

    public Team(Long id, String teamName, String schoolName) {
        super(id, teamName + ":" + schoolName);
        this.schoolName = schoolName;
        this.teamName = teamName;
        submissions = new HashMap<>();
        problemsSolved = new HashMap<>();
    }

    @Override
    public Object getAttribute(Attribute attribute) {
        TeamAttribute attrib = (TeamAttribute) attribute;
        switch (attrib) {
            case ATTRIBUTE_TEAM_ID:
                return getSuperAttribute(EntityLongIdAttribute.ENTITY_LONG_ID_ID);
            case ATTRIBUTE_TEAM_SCHOOL_NAME:
                return getSchoolName();
            case ATTRIBUTE_TEAM_TEAM_NAME:
                return getTeamName();
            case ATTRIBUTE_TEAM_SUBMISSIONS:
                return getSubmissions();
            case ATTRIBUTE_TEAM_PROBLEM_SOLVED:
                return getProblemsSolved();
            default:
                return null;
        }
    }

    private String getSchoolName() {
        return schoolName;
    }

    private String getTeamName() {
        return teamName;
    }

    private Map<Problem, List<Submission>> getSubmissions() {
        return submissions;
    }

    private Map<Problem, Long> getProblemsSolved() {
        return problemsSolved;
    }

    @Override
    public void setAttribute(Attribute attribute, Object value) {
        TeamAttribute attrib = (TeamAttribute) attribute;
        switch (attrib) {
            case ATTRIBUTE_TEAM_ID:
                setSuperAttribute(EntityLongIdAttribute.ENTITY_LONG_ID_ID, value);
            case ATTRIBUTE_TEAM_SCHOOL_NAME:
                setSchoolName((String) value);
            case ATTRIBUTE_TEAM_TEAM_NAME:
                setTeamName((String) value);
        }
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addSubmission(Problem problem, Submission submission) {
        if (!submissions.containsKey(problem)) {
            submissions.put(problem, new ArrayList<>());
        }

        if (!isProblemSolved(problem)) {
            submissions.get(problem).add(submission);
        } else if (submission.getAttribute(SubmissionAttribute.ATTRIBUTE_SUBMISSION_VERDICT) == Verdict.ACCEPTED) {
            addSolvedProblem(problem, calculatePenaltyPoints(problem));
        }
    }

    public List<Submission> getProblemSubmissions(Problem problem) {
        return submissions.get(problem);
    }

    private void addSolvedProblem(Problem problem, Long penaltyPoints) {
        problemsSolved.put(problem, penaltyPoints);
    }

    private Long calculatePenaltyPoints(Problem problem) {
        List<Submission> subs = submissions.get(problem);
        int size = subs.size();
        return (size - 1) * PENALTY_MINUTE + (Long) subs.get(size - 1).getAttribute(SubmissionAttribute.ATTRIBUTE_SUBMISSION_TIME);
    }

    private Boolean isProblemSolved(Problem problem) {
        return problemsSolved.containsKey(problem);
    }
}
