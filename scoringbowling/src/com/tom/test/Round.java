package com.tom.test;

import java.util.ArrayList;
import java.util.List;

public class Round {

	public Round(Round lastRound) {

		this.setLastRound(lastRound);
		if (lastRound != null) {
			this.lastRound.setNextRound(this);
		}

		int count = 0;
		Round tempRound = this.lastRound;
		while (tempRound != null) {
			tempRound = tempRound.lastRound;
			count++;
		}

		this.roundIndex = count;

	}

	public void throwBall(ThrowBall ball) {

		if (!isFinished()) {
			throwBalls.add(ball);
			calcLeftPins(ball);
			String status = "";
			if (ball.isSpare()) {
				status = "补中";
			} else if (ball.isStrike()) {
				status = "全中";
			}

			System.out.println("第" + (this.getRoundIndex() + 1) + "局，第"
					+ (throwBalls.indexOf(ball) + 1) + "投，击倒："
					+ ball.getPinsDown() + " " + status);

			shouldFinishRound(ball);
		}

	}

	private boolean shouldFinishRound(ThrowBall thisShoot) {
		if (this.roundIndex < 9) {
			if (thisShoot.isStrike() || throwBalls.size() == 2) {
				isFinished = true;
			}
		} else if (this.roundIndex == 9) {
			if (throwBalls.size() == 2 && !thisShoot.isSpare()
					&& !thisShoot.isStrike()) {
				isFinished = true;
			}

			else if (throwBalls.size() == 3) {
				isFinished = true;
			}
		}

		return isFinished;
	}

	private void calcLeftPins(ThrowBall aShoot) {

		if (!aShoot.isStrike() && this.throwBalls.size() == 1) {
			setPinsLeft(pinsLeft - aShoot.getPinsDown());
		}

		if (getRoundIndex() == 9) {
			if (aShoot.isStrike() || aShoot.isSpare()) {
				setPinsLeft(10);
			}
			if (throwBalls.get(0).isStrike() && aShoot.getPinsDown() < 10) {
				setPinsLeft(10 - aShoot.getPinsDown());
			}
		}

	}

	public int getNext2ThrowPins() {
		int pins = Constants.NA;
		if (nextRound != null && nextRound.getThrowBalls().size() > 1) {
			pins = nextRound.getThrowBalls().get(0).getPinsDown()
					+ nextRound.getThrowBalls().get(1).getPinsDown();
		} else if (nextRound != null && nextRound.getThrowBalls().size() == 1) {
			if (nextRound.getNextRound() != null
					&& nextRound.getNextRound().getThrowBalls().size() > 0) {
				pins = nextRound.getThrowBalls().get(0).getPinsDown()
						+ nextRound.getNextRound().getThrowBalls().get(0)
								.getPinsDown();
			}
		}
		return pins;
	}

	public int getNext1ThrowPins() {
		int pins = Constants.NA;
		if (nextRound != null && nextRound.getThrowBalls().size() > 0) {
			pins = nextRound.getThrowBalls().get(0).getPinsDown();
		}
		return pins;
	}

	public int getRoundPins() {
		int pins = 0;
		for (ThrowBall b : throwBalls) {
			pins += b.getPinsDown();
		}
		return pins;
	}

	public boolean isSpare() {
		if (isFinished()) {
			for (ThrowBall t : throwBalls) {
				{
					if (t.isSpare()) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public boolean isStrike() {
		if (isFinished()) {
			for (ThrowBall t : throwBalls) {
				{
					if (t.isStrike()) {
						return true;
					}
				}
			}

		}
		return false;
	}

	private int roundIndex;
	private int pinsLeft = 10;
	private Round lastRound;
	private Round nextRound;
	private int score = Constants.NA;
	private boolean isFinished;
	private List<ThrowBall> throwBalls = new ArrayList<ThrowBall>();

	public int getRoundIndex() {
		return roundIndex;
	}

	public void setRoundIndex(int roundIndex) {
		this.roundIndex = roundIndex;
	}

	public Round getLastRound() {
		return lastRound;
	}

	public void setLastRound(Round lastRound) {
		this.lastRound = lastRound;
	}

	public Round getNextRound() {
		return nextRound;
	}

	public void setNextRound(Round nextRound) {
		this.nextRound = nextRound;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public List<ThrowBall> getThrowBalls() {
		return throwBalls;
	}

	public void setThrowBalls(List<ThrowBall> throwBalls) {
		this.throwBalls = throwBalls;
	}

	public int getPinsLeft() {
		return pinsLeft;
	}

	public void setPinsLeft(int pinsLeft) {
		this.pinsLeft = pinsLeft;
	}

}
