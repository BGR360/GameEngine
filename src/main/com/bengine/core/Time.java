/**
 * Created by Ben on 8/26/15.
 *
 * The Time class is a class of static methods
 * which allow the user to see how much time has
 * passed since the last frame.
 */

package com.bengine.core;

public class Time
{
    // The number of nanoseconds in one second
    private static final long ONE_SECOND = 1000000000;

    // The number of nanoseconds in one millisecond
    private static final long ONE_MILLISECOND = 1000000;

    // The time (in nanoseconds) when the application started
    private static final long START_TIME = System.nanoTime();

    // The time that it was when newFrame() was last called
    private static long mTimeLastFrame = START_TIME;

    // The last recorded deltaTime (the deltaTime of the previous frame)
    private static long mDeltaLastFrame = 0;

    /**
     * Returns the amount of time that has elapsed since
     * the application started.
     * @return The number of nanoseconds that have passed
     * since the application's start
     */
    public static long getTime()
    {
        return System.nanoTime() - START_TIME;
    }

    /**
     * The main Game loop calls this function at the beginning
     * of each frame. When this method is called, the difference
     * in time between {@link Time#mTimeLastFrame} and the current
     * time is calculated and stored in {@link Time#mDeltaLastFrame}.
     *
     * So, whenever {@link Time#getDelta()} is called in an update()
     * method, it will return the time it took for the <i>previous</i>
     * frame to execute, which should be a decent value to use for
     * timing.
     */
    public static void newFrame()
    {
        long currentTime = System.nanoTime();
        mDeltaLastFrame = currentTime - mTimeLastFrame;
        mTimeLastFrame = currentTime;
    }

    /**
     * Returns the amount of time since the last frame.
     * This method is one of the most important methods in the
     * engine because it is essential in providing convincing
     * and lag-immune motion and/or physics.
     * @return The number of nanoseconds that it took the last frame
     * to finish executing.
     */
    public static long getDelta()
    {
        return mDeltaLastFrame;
    }
}
