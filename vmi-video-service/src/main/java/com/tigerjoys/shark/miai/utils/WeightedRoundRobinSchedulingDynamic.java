package com.tigerjoys.shark.miai.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 权重轮询调度算法(WeightedRound-RobinScheduling)-Java实现
 * @author huligong
 * */
public class WeightedRoundRobinSchedulingDynamic {

    private List<WeighttedServerDynamic> serverList; //服务器集合

    public WeighttedServerDynamic GetBestServer() {
    	WeighttedServerDynamic server = null;
    	WeighttedServerDynamic best = null;
        int total = 0;
        for(int i=0,len=serverList.size();i<len;i++){
            //当前服务器对象
            server = serverList.get(i);

            //当前服务器已宕机，排除
            if(server.down){
                continue;
            }

            server.currentWeight += server.effectiveWeight;
            total += server.effectiveWeight;
            
            if(server.effectiveWeight < server.weight){
                server.effectiveWeight++;
            }
            
            if(best == null || server.currentWeight>best.currentWeight){
                best = server;
            }
            
        }

        if (best == null) {
            return null;
        }

        best.currentWeight -= total;
        best.checkedDate = new Date();
        return best;
    }

    public WeightedRoundRobinSchedulingDynamic(List<WeighttedServerDynamic> list) {
    	serverList=list;
    }
    
    public void add(int i) {
    	WeighttedServerDynamic s = new WeighttedServerDynamic("192.168.0.1"+i, i-15);
        serverList.add(s);
    }
    
    public WeighttedServerDynamic getServer(int i) {
        if(i<serverList.size()){
            return serverList.get(i);
        }
       return null;
    }
    
    
    public static void main(String[] args) {
    	List<WeighttedServerDynamic> serverList=new ArrayList<WeighttedServerDynamic>();
    	WeighttedServerDynamic s1 = new WeighttedServerDynamic("192.168.0.100", 3);//3
    	WeighttedServerDynamic s2 = new WeighttedServerDynamic("192.168.0.101", 2);//2
    	WeighttedServerDynamic s3 = new WeighttedServerDynamic("192.168.0.102", 6);//6
    	WeighttedServerDynamic s4 = new WeighttedServerDynamic("192.168.0.103", 4);//4
    	WeighttedServerDynamic s5 = new WeighttedServerDynamic("192.168.0.104", 1);//1
    	WeighttedServerDynamic s6 = new WeighttedServerDynamic("192.168.0.105", 0);//0
    	WeighttedServerDynamic s7 = new WeighttedServerDynamic("192.168.0.106", 0);//0
    	WeighttedServerDynamic s8 = new WeighttedServerDynamic("192.168.0.107", 0);//0
    	WeighttedServerDynamic s9 = new WeighttedServerDynamic("192.168.0.108", 0);//0
        serverList.add(s1);
        serverList.add(s2);
        serverList.add(s3);
        serverList.add(s4);
        serverList.add(s5);
        serverList.add(s6);
        serverList.add(s7);
        serverList.add(s8);
        serverList.add(s9);
    	
    	WeightedRoundRobinSchedulingDynamic obj = new WeightedRoundRobinSchedulingDynamic(serverList);
       
        Map<String,Integer> countResult = new HashMap<String,Integer>();
        
        for (int i = 0; i < 100; i++) {
        	WeighttedServerDynamic s = obj.GetBestServer();
            String log = "ip:"+s.ip+";weight:"+s.weight;
            if(countResult.containsKey(log)){
                countResult.put(log,countResult.get(log)+1);
            }else{
                countResult.put(log,1);
            }
            System.out.println(log);
        
            //动态添加服务器
            if(i==20 || i==22){
                  obj.add(i);
            }
            
            //动态停止服务器
            if(i==30){
                obj.getServer(2).setDown(true);
                obj.getServer(3).setDown(true);
          }
        }
        
        for(Entry<String, Integer> map : countResult.entrySet()){
            System.out.println("服务器 "+map.getKey()+" 请求次数： "+map.getValue());
        }
    }

}