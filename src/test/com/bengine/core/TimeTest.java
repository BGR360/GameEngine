/**
 * Created by Ben on 8/26/15.
 *
 * Unit test for Time class
 */

package com.bengine.core;

import junit.framework.TestCase;

public class TimeTest extends TestCase
{
    private static final float LONG_TIME_THRESHOLD =    0.01f;
    private static final float SHORT_TIME_THRESHOLD =   0.01f;

    static class Util
    {
        public static void assertCloseEnoughLong(float expected, float actual)
        {
            float difference = Math.abs(expected - actual);
            if(difference > LONG_TIME_THRESHOLD)
            {
                fail(
                        "Float not within threshold: " +
                        "expected:<" + expected + "> " +
                        "but was:<" + actual + "> " +
                        "Difference: " + difference
                );
            }
        }

        public static void assertCloseEnoughShort(float expected, float actual)
        {
            float difference = Math.abs(expected - actual);
            if(difference > SHORT_TIME_THRESHOLD)
            {
                fail(
                        "Float not within threshold: " +
                        "expected:<" + expected + "> " +
                        "but was:<" + actual + "> " +
                        "Difference: " + difference
                );
            }
        }
    }

    public void testWaitOneSecond() throws Exception
    {
        double currentTime = Time.getTime();
        Time.newFrame();
        Thread.sleep(1000);
        Time.newFrame();
        double delta = Time.getDelta();
        double expected = 1.0f;
        System.out.println(delta);
        Util.assertCloseEnoughLong((float)expected, (float)delta);
    }

    public void testShortTimes() throws Exception
    {
        int numTests = (int)(Math.random() * 100);
        for(int i = 0; i < numTests; i++)
        {
            // Wait between 0 and 50 ms
            int waitTimeMillis = (int)(Math.random() * 100);
            Time.newFrame();
            Thread.sleep(waitTimeMillis);
            Time.newFrame();
            double delta = Time.getDelta();
            double expected = (double)waitTimeMillis / 1000;
            Util.assertCloseEnoughShort((float)expected, (float)delta);
        }
    }
}