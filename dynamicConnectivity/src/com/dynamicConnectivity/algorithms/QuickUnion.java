package com.dynamicConnectivity.algorithms;

public class QuickUnion implements ConnectivityAlgorithm {
    int[] id=null;
    int N=0;
    public QuickUnion(){
    }
    @Override
    public void union(int p,int q){
        int srcRoot=root(p);
        int destRoot=root(q);
        id[srcRoot]=destRoot;
    }
    protected int root(int p){
        while(id[p]!=p)p=id[p];
        return p;
    }
    @Override
    public boolean connected(int p,int q) throws IndexOutOfBoundsException{
        return root(p)==root(q);
    }
    @Override
    public int find(int p){
        return root(p);
    }
    public int length(){
        return N;
    }
    @Override
    public void Init(int N) throws IndexOutOfBoundsException{
        // TODO Auto-generated method stub
        if(N<=0) throw new IndexOutOfBoundsException("Number of Elements Should be larger than 0");
        this.N=N;
        id=new int[this.N];
        for(int i=0;i<this.N;i++)id[i]=i;
    }
	@Override
	public int get(int p) throws IndexOutOfBoundsException{
		
		return id[p];
	}

}
