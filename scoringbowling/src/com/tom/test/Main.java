package com.tom.test;

public class Main {
public static void main(String[] args) {

	Game game = new Game();
	Scorer scorer = new Scorer(game);
	Round lastRound = null;

	try {
		for (int i = 0; i < 10; i++) {
			Round round = new Round(lastRound);
			game.startNewRound(round);
			lastRound = round;
			while (!round.isFinished()) {
				ThrowBall shoot;
				shoot = new ThrowBall(round.getPinsLeft());	
				round.throwBall(shoot);
			}			
			scorer.calcRoundScore(round);
		}
		scorer.printGameScoring();
	} catch (Exception e) {
		e.printStackTrace();
	}


}
}
