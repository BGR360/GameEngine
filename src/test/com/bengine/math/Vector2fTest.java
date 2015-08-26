/**
 * Created by Ben on 8/26/15.
 *
 * Unit test for Vector2f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Vector2fTest extends TestCase
{

    public void testMagnitude() throws Exception
    {
        Vector2f vector2f = new Vector2f(3, 4);
        float expected = 5.0f;
        float result = vector2f.magnitude();
        assertEquals(expected, result);
    }

    public void testDot() throws Exception
    {
        Vector2f vec1 = new Vector2f(1.0f, 2.0f);
        Vector2f vec2 = new Vector2f(-3.5f, 0.0f);
        float expected = -3.5f;
        float result = vec1.dot(vec2);
    }

    public void testNormalize() throws Exception
    {

    }

    public void testNormalized() throws Exception
    {

    }

    public void testAdd() throws Exception
    {

    }

    public void testAdd1() throws Exception
    {

    }

    public void testSub() throws Exception
    {

    }

    public void testSub1() throws Exception
    {

    }

    public void testMul() throws Exception
    {

    }

    public void testMul1() throws Exception
    {

    }

    public void testDiv() throws Exception
    {

    }

    public void testDiv1() throws Exception
    {

    }

    public void testEquals() throws Exception
    {

    }

    public void testCompareTo() throws Exception
    {

    }
}