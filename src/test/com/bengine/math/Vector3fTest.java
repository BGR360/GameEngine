/**
 * Created by Ben on 8/26/15.
 * 
 * Unit test for Vector3f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Vector3fTest extends TestCase
{

    public void testMagnitude() throws Exception
    {
        Vector3f vec = new Vector3f(3, 4, 5);
        float expected = 5.0f * (float)Math.sqrt(2);
        float result = vec.magnitude();
        assertEquals(expected, result);
    }

    public void testDot() throws Exception
    {
        Vector3f vec1 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f vec2 = new Vector3f(-3.5f, 0.0f, 1.0f);
        float expected = -0.5f;
        float result = vec1.dot(vec2);
        assertEquals(expected, result);
    }

    public void testCross() throws Exception
    {
        Vector3f vec1 = new Vector3f(1, 2, 3);
        Vector3f vec2 = new Vector3f(3, 4, 5);
        Vector3f expected = new Vector3f(-2, 4, -2);
        Vector3f result = vec1.cross(vec2);
        assertEquals(expected, result);
        expected = new Vector3f(2, -4, 2);
        result = vec2.cross(vec1);
        assertEquals(expected, result);
    }

    public void testNormalize() throws Exception
    {
        Vector3f vec = new Vector3f(5, 5, 5);
        Vector3f expected = new Vector3f(
                (float)Math.sqrt(3.0f) / 3.0f,
                (float)Math.sqrt(3.0f) / 3.0f,
                (float)Math.sqrt(3.0f) / 3.0f);
        Vector3f result = vec.normalized();
        vec.normalize();
        assertEquals(expected, result);
        assertEquals(result, vec);

        Vector3f zeroVec = new Vector3f();
        try
        {
            zeroVec.normalize();
            fail("normalize() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}

        try
        {
            Vector3f normalized = zeroVec.normalized();
            fail("normalized() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}
    }

    public void testAdd() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        Vector3f right = new Vector3f(0.0f, 42.0f, -2.0f);
        Vector3f expected = new Vector3f(1.0f, 38.86f, 0.0f);
        Vector3f result = left.add(right);
        assertEquals(expected, result);
    }

    public void testSub() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        Vector3f right = new Vector3f(0.0f, 42.0f, -2.0f);
        Vector3f expected = new Vector3f(1.0f, -45.14f, 4.0f);
        Vector3f result = left.sub(right);
        assertEquals(expected, result);
    }

    public void testMul() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        Vector3f right = new Vector3f(0.0f, 2.0f, -2.0f);
        Vector3f expected = new Vector3f(0.0f, -6.28f, -4.0f);
        Vector3f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testScalarMul() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        float right = 2.0f;
        Vector3f expected = new Vector3f(2.0f, -6.28f, 4.0f);
        Vector3f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testDiv() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        Vector3f right = new Vector3f(0.0f, 2.0f, -2.0f);

        try
        {
            Vector3f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch(IllegalArgumentException ignored) {}

        right = new Vector3f(2.0f, 2.0f, -2.0f);
        Vector3f expected = new Vector3f(0.5f, -1.57f, -1.0f);
        Vector3f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testScalarDiv() throws Exception
    {
        Vector3f left = new Vector3f(1.0f, -3.14f, 2.0f);
        float right = 0.0f;

        try
        {
            Vector3f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch (IllegalArgumentException ignored) {}

        right = 2.0f;
        Vector3f expected = new Vector3f(0.5f, -1.57f, 1.0f);
        Vector3f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testEquals() throws Exception
    {
        Vector3f vec1 = new Vector3f();
        Vector3f vec2 = new Vector3f();
        boolean equals = vec1.equals(vec2);
        assertTrue(equals);
    }

    public void testCompareTo() throws Exception
    {
        Vector3f vec1 = new Vector3f(-1.0f, 0.0f, -3.14f);
        Vector3f vec2 = new Vector3f(0.0f, 5.0f, 0.0f);
        Vector3f vec3 = new Vector3f(2.0f, 5.0f, 1.0f);

        int comp1_1 = vec1.compareTo(vec1);
        int comp1_2 = vec1.compareTo(vec2);
        int comp1_3 = vec1.compareTo(vec3);

        int comp2_1 = vec2.compareTo(vec1);
        int comp2_2 = vec2.compareTo(vec2);
        int comp2_3 = vec2.compareTo(vec3);

        int comp3_1 = vec3.compareTo(vec1);
        int comp3_2 = vec3.compareTo(vec2);
        int comp3_3 = vec3.compareTo(vec3);

        assertTrue(comp1_1 == 0);
        assertTrue(comp1_2 < 0);
        assertTrue(comp1_3 < 0);

        assertTrue(comp2_1 > 0);
        assertTrue(comp2_2 == 0);
        assertTrue(comp2_3 < 0);

        assertTrue(comp3_1 > 0);
        assertTrue(comp3_2 > 0);
        assertTrue(comp3_3 == 0);

    }
}
