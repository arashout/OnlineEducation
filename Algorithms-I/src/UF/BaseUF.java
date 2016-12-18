package UF;

/**
 * Created by arash on 2016-11-10.
 */
public class BaseUF {
        private int[] id;
        private int count;
        public int count(){
            return 0;
        }
        public boolean connected(int p, int q){return false;}
        private int find(int p){return 0;}
        public void union(int p, int q){}
}
