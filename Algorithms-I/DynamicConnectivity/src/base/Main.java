package base;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import test.QuickUnionTest;

/**
 * Created by arash on 2016-09-03.
 * This is where I will run all my tests for week 1
 */
public class Main {
    public static void main(String[] args) {
        //TEST RUNNER
        Result result = JUnitCore.runClasses(QuickUnionTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
