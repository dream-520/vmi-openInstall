package com.tigerjoys.shark.miai.enums;

/**
 * @author: mouzhanpeng.
 * @date: created in [2018/9/26 16:44].
 */
public enum SlotMachine {

  SINGLE(1, "一倍", "", 1 * SlotMachine.EACH_AWARD, 355),
  DOUBLE(2, "两倍", "", 2 * SlotMachine.EACH_AWARD, 70),
  TRIPLE(3, "三倍", "", 3 * SlotMachine.EACH_AWARD, 45),
  QUADRUPLE(4, "四倍", "", 4 * SlotMachine.EACH_AWARD, 27),
  QUINTUPLE(5, "五倍", "", 5 * SlotMachine.EACH_AWARD, 2),
  TENFOLD(6, "十倍", "", 10 * SlotMachine.EACH_AWARD, 1),
  ZERO(7, "零倍", "", 0, 500),
      ;

  /**
   * 单倍奖励（钻）
   */
  private static final int EACH_AWARD = 2;
  /**
   * id
   */
  private int index;
  /**
   * 描述
   */
  private String desc;
  /**
   * 图标
   */
  private String icon;
  /**
   * 奖励（钻）
   */
  private int award;
  /**
   * 频数占比
   */
  private int occupy;

  SlotMachine(int index, String desc, String icon, int award, int occupy) {
    this.index = index;
    this.desc = desc;
    this.icon = icon;
    this.award = award;
    this.occupy = occupy;
  }

  public int getIndex() {
    return index;
  }

  public String getDesc() {
    return desc;
  }

  public String getIcon() {
    return icon;
  }

  public int getAward() {
    return award;
  }

  public int getOccupy() {
    return occupy;
  }
}
