import StacksQueues.Deque;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by arash_000 on 2016-11-18.
 */
public class TestDeque {
    @Test
    public void testTextbook(){
        Deque<Integer> dq = new Deque<Integer>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            dq.addFirst(i);
        }

    }
}
