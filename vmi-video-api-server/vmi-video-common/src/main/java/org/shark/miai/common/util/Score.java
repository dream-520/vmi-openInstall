package org.shark.miai.common.util;
/**
 * 分数对象
 * @author yangjunming
 *
 */
public class Score {
	private int fenZi;
	private int fenMu;
	private int unit;
	private double xiaoShu;
	public Score() {
	}
	public Score(int fenZi,int fenMu,int unit,double xiaoshu) {
		this.fenZi=fenZi;
		this.fenMu=fenMu;
		this.unit=unit;
		this.xiaoShu=xiaoshu;
	}
	public int getFenZi() {
		return fenZi;
	}
	public void setFenZi(int fenZi) {
		this.fenZi = fenZi;
	}
	public int getFenMu() {
		return fenMu;
	}
	public void setFenMu(int fenMu) {
		this.fenMu = fenMu;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public double getXiaoShu() {
		return xiaoShu;
	}
	public void setXiaoShu(double xiaoShu) {
		this.xiaoShu = xiaoShu;
	}


	
}
