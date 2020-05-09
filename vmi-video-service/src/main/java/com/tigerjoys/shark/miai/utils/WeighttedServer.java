package com.tigerjoys.shark.miai.utils;

public class WeighttedServer {
	public String name;
    public int weight;
    public WeighttedServer(String ip, int weight) {
        this.name = ip;
        this.weight = weight;
    }
    public String getIp() {
        return name;
    }
    public void setIp(String ip) {
        this.name = ip;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
