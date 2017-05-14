package com.tom.test;

public class Scorer {

	public Scorer() {

	}

	public Scorer(Game aGame) {
		this.setGame(aGame);
	}

	public void printGameScoring() {
		System.out.println("======统计得分" + "======");
		for (int i = 0; i < game.getRounds().size(); i++) {
			System.out.println("第" + (i + 1) + "局得分：" + getRoundScore(i));
		}
	}

	public void calcRoundScore(Round round) {

		if (game != null && round != null) {

			if (round.isFinished()) {
				// 为第1局计分
				if (round.getRoundIndex() == 0) {
					if (!round.isSpare() && !round.isStrike()) {
						round.setScore(round.getRoundPins());
					} else if (round.isSpare()
							&& round.getNext1ThrowPins() != Constants.NA) {

						round.setScore(round.getRoundPins()
								+ round.getNext1ThrowPins());
					} else if (round.isStrike()
							&& round.getNext2ThrowPins() != Constants.NA) {
						round.setScore(round.getRoundPins()
								+ round.getNext2ThrowPins());
					}
				}
				// 为第2-9局计分
				else if (round.getRoundIndex() > 0 && round.getRoundIndex() < 9) {
					if (!round.isSpare() && !round.isStrike()) {
						if (round.getLastRound().getScore() != Constants.NA) {
							round.setScore(round.getLastRound().getScore()
									+ round.getRoundPins());
						} else {
							calcRoundScore(round.getLastRound());
							if (round.getLastRound().getScore() != Constants.NA) {
								calcRoundScore(round);
							}
						}

					} else if (round.isSpare()) {
						if (round.getLastRound().getScore() != Constants.NA) {
							if (round.getNext1ThrowPins() != Constants.NA) {
								round.setScore(round.getLastRound().getScore()
										+ round.getRoundPins()
										+ round.getNext1ThrowPins());
							}

						} else {
							calcRoundScore(round.getLastRound());
							if (round.getLastRound().getScore() != Constants.NA) {
								calcRoundScore(round);
							}
						}

					} else if (round.isStrike()) {

						if (round.getLastRound().getScore() != Constants.NA) {
							if (round.getNext2ThrowPins() != Constants.NA) {
								round.setScore(round.getLastRound().getScore()
										+ round.getRoundPins()
										+ round.getNext2ThrowPins());
							}

						} else {
							calcRoundScore(round.getLastRound());
							if (round.getLastRound().getScore() != Constants.NA) {
								calcRoundScore(round);
							}
						}

					}

				}
				// 为第10局计分
				else if (round.getRoundIndex() == 9) {
					if (round.getLastRound().getScore() != Constants.NA) {
						round.setScore(round.getLastRound().getScore()
								+ round.getRoundPins());
					} else {
						calcRoundScore(round.getLastRound());
						if (round.getLastRound().getScore() != Constants.NA) {
							calcRoundScore(round);
						}
					}

				}

			}

		}

	}

	public int getRoundScore(int roundIndex) {
		return game.getRounds().get(roundIndex).getScore();
	}

	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
