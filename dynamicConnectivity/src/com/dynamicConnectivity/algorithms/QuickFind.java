package com.dynamicConnectivity.algorithms;

public class QuickFind implements ConnectivityAlgorithm{
    private int[] id=null;
    private int N=0;
    public QuickFind(){
    }
    @Override
    public boolean connected(int p,int q) throws IndexOutOfBoundsException{
        return id[p]==id[q];
    }
    @Override
    public void union(int p,int q) throws IndexOutOfBoundsException{
        int destComponent=id[q];
        int srcComponent=id[p];
        for(int i=0;i<N;i++)if(id[i]==srcComponent)id[i]=destComponent;
    }
    @Override
    public int find(int p){
        return id[p];
    }
    @Override
    public int length(){
        return N;
    }

    @Override
    public void Init(int _N) throws IndexOutOfBoundsException{
        if(_N<=0) throw new IndexOutOfBoundsException("Number of Elements Should be larger than 0");
        N=_N;
        id=new int[N];
        for(int i=0;i<N;i++)id[i]=i;
        
    }
	@Override
	public int get(int p) {
		return id[p];
	}
}
