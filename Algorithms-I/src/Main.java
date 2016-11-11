import UF.BaseUF;
import UF.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void passChildObject(BaseUF bUF){
        bUF.union(1,2);
        StdOut.println(bUF.connected(1,2));
        StdOut.println(bUF.connected(0,1));
    }
    public static void main(String[] args) {
        QuickFindUF qf = new QuickFindUF(3);
        passChildObject(qf);

    }
}
