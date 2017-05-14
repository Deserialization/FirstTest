package com.tom.test;

import java.util.Random;

public class ThrowBall {
	
	
	public ThrowBall(int pinsLeft) throws InterruptedException {
		Thread.sleep(100);
		Random rdm = new Random(System.currentTimeMillis());
		this.pinsDown = rdm.nextInt(pinsLeft + 1);
		if (this.pinsDown == 10) {
			isStrike = true;
		} else if (pinsLeft < 10 && pinsDown == pinsLeft) {
			isSpare = true;
		}
	}
	
	
	
	
	
	
	
	
	

	private boolean isStrike;
	private boolean isSpare;
	private int pinsDown;

	public int getPinsDown() {
		return pinsDown;
	}

	public void setPinsDown(int pins) {
		this.pinsDown = pins;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public boolean isSpare() {

		return isSpare;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

}
