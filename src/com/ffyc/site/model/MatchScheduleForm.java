package com.ffyc.site.model;

import java.sql.Timestamp;

public class MatchScheduleForm {
	
	private String id;
	
	private TeamScore teamInfo;
	
	private Timestamp matchTime;
	
	private String matchPlace;
	
	private String isLocal;
	
	private String matchResult;
	
	private String score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TeamScore getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(TeamScore teamInfo) {
		this.teamInfo = teamInfo;
	}

	public Timestamp getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Timestamp matchTime) {
		this.matchTime = matchTime;
	}

	public String getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMatchPlace() {
		return matchPlace;
	}

	public void setMatchPlace(String matchPlace) {
		this.matchPlace = matchPlace;
	}
}
