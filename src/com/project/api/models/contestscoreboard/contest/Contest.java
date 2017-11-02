/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.contestscoreboard.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.api.models.Attribute;
import com.project.api.models.contestscoreboard.entity.EntityAttribute;
import com.project.api.models.contestscoreboard.entitylongid.EntityLongId;
import com.project.api.models.contestscoreboard.entitylongid.EntityLongIdAttribute;
import com.project.api.models.contestscoreboard.problem.Problem;
import com.project.api.models.contestscoreboard.submission.Submission;
import com.project.api.models.contestscoreboard.team.Team;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Contest extends EntityLongId {
    
    private String contestName;
    private List<Team> teams;
    private List<Problem> problems;
    private List<Submission> submissions;
    private Map<Problem, Team> firstProblemSolver;
    
    public Contest(Long id, String contestName) {
        super(id, contestName);
        teams = new ArrayList<>();
        problems = new ArrayList<>();
        submissions = new ArrayList<>();
        firstProblemSolver = new HashMap<>();
    }
    
    @Override
    public Object getAttribute(Attribute attribute) {
        ContestAttribute attrib = (ContestAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_CONTEST_ID:
                return getSuperAttribute(EntityLongIdAttribute.ENTITY_LONG_ID_ID);
            case ATTRIBUTE_CONTEST_NAME:
                return getSuperAttribute(EntityAttribute.ENTITY_ATTRIBUTE_DESCRIPTION);
            case ATTRIBUTE_CONTEST_TEAMS:
                return getTeams();
            case ATTRIBUTE_CONTEST_PROBLEMS:
                return getProblems();
            case ATTRIBUTE_CONTEST_SUBMISSIONS:
                return getSubmissions();
            case ATTRIBUTE_CONTEST_FIRST_PROBLEM_SOLVER:
                return getFirstProblemSolver();
            default:
                return null;
        }
    }
    
    private List<Team> getTeams() {
        return teams;
    }
    
    private List<Problem> getProblems() {
        return problems;
    }
    
    private List<Submission> getSubmissions() {
        return submissions;
    }
    
    private Map<Problem, Team> getFirstProblemSolver() {
        return firstProblemSolver;
    }
    
    @Override
    public void setAttribute(Attribute attribute, Object value) {
        ContestAttribute attrib = (ContestAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_CONTEST_TEAMS:
                setTeams();
                break;
            case ATTRIBUTE_CONTEST_PROBLEMS:
                setProblems();
                break;
            case ATTRIBUTE_CONTEST_SUBMISSIONS:
                setSubmissions();
                break;
            case ATTRIBUTE_CONTEST_FIRST_PROBLEM_SOLVER:
                setFirstProblemSolver();
                break;
        }
    }
    
    private void setTeams() {
        teams = new ArrayList<>();
    }
    
    private void setProblems() {
        problems = new ArrayList<>();
    }
    
    private void setSubmissions() {
        submissions = new ArrayList<>();
    }
    
    private void setFirstProblemSolver() {
        firstProblemSolver = new HashMap<>();
    }
    
    public void addTeam(Team team) {
        teams.add(team);
    }
    
    public void addProblem(Problem problem) {
        problems.add(problem);
    }
    
    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }
    
    public void addFirstProblemSolver(Problem problem, Team team) {
        firstProblemSolver.put(problem, team);
    }
    
    public Team getFirstProblemSolver(Problem problem) {
        return firstProblemSolver.get(problem);
    }
}
