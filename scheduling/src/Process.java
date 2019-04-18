// package com.nimbalkar.chetan;

public class Process {
	private String pName;
	private int at, bt, wt, tt, rt, ct, priority;
	private boolean calcRt = false;

	public Process(String pName, int... parameters) {
		this.pName = pName;
		this.at = parameters[0];
		this.bt = parameters[1];
		this.ct = parameters[2];
		this.tt = parameters[3];
		this.wt = parameters[4];
		this.rt = parameters[5];
		this.priority = parameters[6];
	}

	public int getAt() {
		return at;
	}

	public int getBt() {
		return bt;
	}

	public int getWt() {
		return wt;
	}

	public int getTt() {
		return tt;
	}

	public int getRt() {
		return rt;
	}

	public int getCt() {
		return ct;
	}

	public String getpName() {
		return pName;
	}

	public int getPriority() {
		return priority;
	}

	public void setBt(int bt) {
		this.bt = bt;
	}


	public void setWt(int wt) {
		this.wt = wt;
	}

	public void setTt(int tt) {
		this.tt = tt;
	}

	public void setRt(int rt) {
		this.rt = rt;
	}

	public void setCt(int ct) {
		this.ct = ct;
	}

	public boolean isCalcRt() {
		return calcRt;
	}

	public void setCalcRt(boolean calcRt) {
		this.calcRt = calcRt;
	}
	@Override
	public String toString() {
		return "Process{" +
				"pName='" + pName + '\'' +
				", at=" + at +
				", bt=" + bt +
				", wt=" + wt +
				", tt=" + tt +
				", rt=" + rt +
				", ct=" + ct +
				'}';
	}
}