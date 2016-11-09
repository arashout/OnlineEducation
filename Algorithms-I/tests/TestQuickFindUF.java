import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestQuickFindUF {

    //test to check appraisal
    @Test
    public void testTheTest() {
        QuickFindUF qf = new QuickFindUF(4);
        assertEquals(4, qf.a);
    }
}