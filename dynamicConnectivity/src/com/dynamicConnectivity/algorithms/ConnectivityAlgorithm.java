package com.dynamicConnectivity.algorithms;

public interface ConnectivityAlgorithm {
    public void Init(int N);
    public boolean connected(int p,int q);
    public void union(int p,int q);
    public int find(int p);
    public int length();

}
