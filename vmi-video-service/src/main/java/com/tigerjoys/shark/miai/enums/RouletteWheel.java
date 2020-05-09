package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: mouzhanpeng.
 * @date: created in [2018/7/25 16:45].
 */
public interface RouletteWheel {

  int getId();

  int getAmount();

  String getDesc();

  int getOccupy();

  enum Low implements RouletteWheel{
    THANKS(1, 0, "谢谢参与", 650),
    TWO_CASH(2, 2, "2金币", 300),
    FIVE_CASH(3, 5, "5金币", 40),
    FIFTEEN_CASH(4, 15, "15金币", 5),
    THIRTY_CASH(5, 30, "30金币", 3),
    FIFTY_CASH(6, 50, "50金币", 2),
    HUAWEI_P20(7, 0, "华为P20", 0),
    IPHONEX(8, 0, "iPhoneX", 0),
    ;

    /**
     * 轮盘项Id
     */
    private int id;
    /**
     * 奖励数量
     */
    private int amount;
    /**
     * 描述
     */
    private String desc;
    /**
     * 频数占比
     */
    private int occupy;

    private static final Map<Integer, Low> map = new HashMap<>();

    static{
      for (Low one : values()){
        map.put(one.getId(), one);
      }
    }

    Low(int id, int amount, String desc, int occupy) {
      this.id = id;
      this.amount = amount;
      this.desc = desc;
      this.occupy = occupy;
    }

    @Override
    public int getId() {
      return id;
    }

    @Override
    public int getAmount() {
      return amount;
    }

    @Override
    public String getDesc() {
      return desc;
    }

    @Override
    public int getOccupy() {
      return occupy;
    }

    /**
     * 根据id获得奖励额
     * @param id
     * @return
     */
    public static int getAmountById(int id){
      return map.get(id).getAmount();
    }
  }

  enum Middle implements RouletteWheel{
    THANKS(1, 0, "谢谢参与", 470),
    TWO_CASH(2, 2, "2金币", 400),
    FIVE_CASH(3, 5, "5金币", 120),
    FIFTEEN_CASH(4, 15, "15金币", 5),
    THIRTY_CASH(5, 30, "30金币", 3),
    FIFTY_CASH(6, 50, "50金币", 2),
    HUAWEI_P20(7, 0, "华为P20", 0),
    IPHONEX(8, 0, "iPhoneX", 0),
    ;

    /**
     * 轮盘项Id
     */
    private int id;
    /**
     * 奖励数量
     */
    private int amount;
    /**
     * 描述
     */
    private String desc;
    /**
     * 频数占比
     */
    private int occupy;

    private static final Map<Integer, Middle> map = new HashMap<>();

    static{
      for (Middle one : values()){
        map.put(one.getId(), one);
      }
    }

    Middle(int id, int amount, String desc, int occupy) {
      this.id = id;
      this.amount = amount;
      this.desc = desc;
      this.occupy = occupy;
    }

    @Override
    public int getId() {
      return id;
    }

    @Override
    public int getAmount() {
      return amount;
    }

    @Override
    public String getDesc() {
      return desc;
    }

    @Override
    public int getOccupy() {
      return occupy;
    }

    /**
     * 根据id获得奖励额
     * @param id
     * @return
     */
    public static int getAmountById(int id){
      return map.get(id).getAmount();
    }
  }

  enum High implements RouletteWheel{
    THANKS(1, 0, "谢谢参与", 350),
    TWO_CASH(2, 2, "2金币", 460),
    FIVE_CASH(3, 5, "5金币", 170),
    FIFTEEN_CASH(4, 15, "15金币", 10),
    THIRTY_CASH(5, 30, "30金币", 5),
    FIFTY_CASH(6, 50, "50金币", 5),
    HUAWEI_P20(7, 0, "华为P20", 0),
    IPHONEX(8, 0, "iPhoneX", 0),
    ;

    /**
     * 轮盘项Id
     */
    private int id;
    /**
     * 奖励数量
     */
    private int amount;
    /**
     * 描述
     */
    private String desc;
    /**
     * 频数占比
     */
    private int occupy;

    private static final Map<Integer, High> map = new HashMap<>();

    static{
      for (High one : values()){
        map.put(one.getId(), one);
      }
    }

    High(int id, int amount, String desc, int occupy) {
      this.id = id;
      this.amount = amount;
      this.desc = desc;
      this.occupy = occupy;
    }

    @Override
    public int getId() {
      return id;
    }

    @Override
    public int getAmount() {
      return amount;
    }

    @Override
    public String getDesc() {
      return desc;
    }

    @Override
    public int getOccupy() {
      return occupy;
    }

    /**
     * 根据id获得奖励额
     * @param id
     * @return
     */
    public static int getAmountById(int id){
      return map.get(id).getAmount();
    }
  }
  
  enum NewV implements RouletteWheel{
	  	IPHONEX(0, 0, "vivo x27", 0),
	  	TWO_CASH(1, 2*20, "2钻", 2500),
	  	FIVE_CASH(2, 10*20, "10钻", 50),
		FIFTEEN_CASH(3, 50*20, "50钻", 20),
		THIRTY_CASH(4, 100, "100钻", 30),
		FIFTY_CASH(5, 500*20, "500钻", 2),
	    HUAWEI_P20(6, 0, "华为Mate30", 0),
	    THANKS(7, 0, "谢谢参与", 7398), 
	    ;

	    /**
	     * 轮盘项Id
	     */
	    private int id;
	    /**
	     * 奖励数量
	     */
	    private int amount;
	    /**
	     * 描述
	     */
	    private String desc;
	    /**
	     * 频数占比
	     */
	    private int occupy;

	    private static final Map<Integer, NewV> map = new HashMap<>();

	    static{
	      for (NewV one : values()){
	        map.put(one.getId(), one);
	      }
	    }

	    NewV(int id, int amount, String desc, int occupy) {
	      this.id = id;
	      this.amount = amount;
	      this.desc = desc;
	      this.occupy = occupy;
	    }

	    @Override
	    public int getId() {
	      return id;
	    }

	    @Override
	    public int getAmount() {
	      return amount;
	    }

	    @Override
	    public String getDesc() {
	      return desc;
	    }

	    @Override
	    public int getOccupy() {
	      return occupy;
	    }

	    /**
	     * 根据id获得奖励额
	     * @param id
	     * @return
	     */
	    public static int getAmountById(int id){
	      return map.get(id).getAmount();
	    }
	  }
  
  enum NewV200 implements RouletteWheel{
	  	IPHONEX(0, 0, "vivo x27", 0),
	  	TWO_CASH(1, 2*20, "2钻", 2800),
	  	FIVE_CASH(2, 10*20, "10钻", 80),
		FIFTEEN_CASH(3, 50*20, "50钻", 30),
		THIRTY_CASH(4, 100, "100钻", 30),
		FIFTY_CASH(5, 500*20, "500钻", 2),
	    HUAWEI_P20(6, 0, "华为Mate30", 0),
	    THANKS(7, 0, "谢谢参与", 7058), 
	    ;

	    /**
	     * 轮盘项Id
	     */
	    private int id;
	    /**
	     * 奖励数量
	     */
	    private int amount;
	    /**
	     * 描述
	     */
	    private String desc;
	    /**
	     * 频数占比
	     */
	    private int occupy;

	    private static final Map<Integer, NewV200> map = new HashMap<>();

	    static{
	      for (NewV200 one : values()){
	        map.put(one.getId(), one);
	      }
	    }

	    NewV200(int id, int amount, String desc, int occupy) {
	      this.id = id;
	      this.amount = amount;
	      this.desc = desc;
	      this.occupy = occupy;
	    }

	    @Override
	    public int getId() {
	      return id;
	    }

	    @Override
	    public int getAmount() {
	      return amount;
	    }

	    @Override
	    public String getDesc() {
	      return desc;
	    }

	    @Override
	    public int getOccupy() {
	      return occupy;
	    }

	    /**
	     * 根据id获得奖励额
	     * @param id
	     * @return
	     */
	    public static int getAmountById(int id){
	      return map.get(id).getAmount();
	    }
	  }
  
  enum NewV1000 implements RouletteWheel{
	  	IPHONEX(0, 0, "vivo x27", 0),
	  	TWO_CASH(1, 2*20, "2钻", 3000),
	  	FIVE_CASH(2, 10*20, "10钻", 100),
		FIFTEEN_CASH(3, 50*20, "50钻", 50),
		THIRTY_CASH(4, 100, "100钻", 30),
		FIFTY_CASH(5, 500*20, "500钻", 2),
	    HUAWEI_P20(6, 0, "华为Mate30", 0),
	    THANKS(7, 0, "谢谢参与", 6818), 
	    ;

	    /**
	     * 轮盘项Id
	     */
	    private int id;
	    /**
	     * 奖励数量
	     */
	    private int amount;
	    /**
	     * 描述
	     */
	    private String desc;
	    /**
	     * 频数占比
	     */
	    private int occupy;

	    private static final Map<Integer, NewV1000> map = new HashMap<>();

	    static{
	      for (NewV1000 one : values()){
	        map.put(one.getId(), one);
	      }
	    }

	    NewV1000(int id, int amount, String desc, int occupy) {
	      this.id = id;
	      this.amount = amount;
	      this.desc = desc;
	      this.occupy = occupy;
	    }

	    @Override
	    public int getId() {
	      return id;
	    }

	    @Override
	    public int getAmount() {
	      return amount;
	    }

	    @Override
	    public String getDesc() {
	      return desc;
	    }

	    @Override
	    public int getOccupy() {
	      return occupy;
	    }

	    /**
	     * 根据id获得奖励额
	     * @param id
	     * @return
	     */
	    public static int getAmountById(int id){
	      return map.get(id).getAmount();
	    }
	  }
  
  enum NewVPrize implements RouletteWheel{
	  	IPHONEX(0, 0, "vivo x27", 50),
	  	TWO_CASH(1, 2*20, "40钻", 6000),
	  	FIVE_CASH(2, 10*20, "200钻", 2000),
		FIFTEEN_CASH(3, 50*20, "1000钻", 1000),
		THIRTY_CASH(4, 100, "100元抵扣券", 800),
		FIFTY_CASH(5, 500*20, "10000钻", 100),
	    HUAWEI_P20(6, 0, "华为Mate30", 50),
	    ;

	    /**
	     * 轮盘项Id
	     */
	    private int id;
	    /**
	     * 奖励数量
	     */
	    private int amount;
	    /**
	     * 描述
	     */
	    private String desc;
	    /**
	     * 频数占比
	     */
	    private int occupy;

	    private static final Map<Integer, NewVPrize> map = new HashMap<>();

	    static{
	      for (NewVPrize one : values()){
	        map.put(one.getId(), one);
	      }
	    }

	    NewVPrize(int id, int amount, String desc, int occupy) {
	      this.id = id;
	      this.amount = amount;
	      this.desc = desc;
	      this.occupy = occupy;
	    }

	    @Override
	    public int getId() {
	      return id;
	    }

	    @Override
	    public int getAmount() {
	      return amount;
	    }

	    @Override
	    public String getDesc() {
	      return desc;
	    }

	    @Override
	    public int getOccupy() {
	      return occupy;
	    }

	    /**
	     * 根据id获得奖励额
	     * @param id
	     * @return
	     */
	    public static int getAmountById(int id){
	      return map.get(id).getAmount();
	    }
	  }
}
