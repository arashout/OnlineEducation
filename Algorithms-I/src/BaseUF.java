/**
 * Created by arash on 2016-11-10.
 */
public class BaseUF {
        public int[] id;
        public int count;
        public int count(){
            return count;
        }
        public boolean connected(int p, int q){return false;}
        private int find(int p){return 0;}
        public void union(int p, int q){}
}
