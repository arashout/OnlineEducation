/**
 * Created by arash on 2016-11-07.
 */
public class QuickFindUF {
    public int[] id;
    public int count;

    public QuickFindUF(int n){
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
    public int find(int p){
        return id[p];
    }
    public void union(int p, int q){
        int pId = id[p];
        int qId = id[q];
        for(int i = 0;i < count;i++){
            if(id[i] == qId) id[i] = pId;
        }
    }
}
