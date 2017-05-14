package com.tom.test;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private List<Round> rounds=new ArrayList<Round>();
	
	public void startNewRound(Round round){
		rounds.add(round);
	}
	
	
	
	
	
	
	
	public List<Round> getRounds() {
		return rounds;
	}
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	
	
	
	

}
