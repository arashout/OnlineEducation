package UF;

import UF.BaseUF;

/**
 * Created by arash on 2016-11-10.
 */
public class WQUPathCompressionUF extends BaseUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WQUPathCompressionUF(int n){
        count = n;
        id = new int[n];
        for(int i =0;i < n; i++){
            id[i] = i;
        }
        sz = new int[n];
        for(int i =0;i < n; i++){
            sz[i] = 1;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    private int find(int p){
        int root = p;
        while (root != id[root]){
            root = id[root];
        }
        int temp;
        while (p != root){
            temp = p;
            p = id[p];
            id[temp] = root;
        }
        return root;
    }
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j) return; //If they are already the same
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
