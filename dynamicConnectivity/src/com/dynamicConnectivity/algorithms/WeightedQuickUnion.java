package com.dynamicConnectivity.algorithms;

public class WeightedQuickUnion extends QuickUnion implements
        ConnectivityAlgorithm {
    private int[] sz = null;
    private int count=0;

    @Override
    public void Init(int N) {
        super.Init(N);
        count=N;
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
    }

    @Override
    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        if (sz[rootp] < sz[rootq]) {
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];
        } else {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];
        }
        count--;
    }
    public int count(){
    	return this.count;
    }
}
