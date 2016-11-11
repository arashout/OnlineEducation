/**
 * Created by arash on 2016-11-07.
 */
public class QuickUnionUF extends BaseUF {
    private int[] id;
    private int count;

    public QuickUnionUF(int n){
        count = n;
        id = new int[n];
        for(int i =0;i < n; i++){
            id[i] = i;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    private int find(int p){
        while (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return; //If they are already the same
        id[pRoot] = qRoot;
        count--;
    }
}