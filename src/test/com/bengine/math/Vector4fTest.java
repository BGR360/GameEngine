/**
 * Created by Ben on 8/26/15.
 * 
 * Unit test for Vector4f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Vector4fTest extends TestCase
{
    public void testMagnitude() throws Exception
    {
        Vector4f vec = new Vector4f(1, 2, 3, 4);
        float expected = (float)Math.sqrt(30);
        float result = vec.magnitude();
        assertEquals(expected, result);
    }

    public void testDot() throws Exception
    {
        Vector4f vec1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f vec2 = new Vector4f(-3.5f, 0.0f, 1.0f, -2.0f);
        float expected = -8.5f;
        float result = vec1.dot(vec2);
        assertEquals(expected, result);
    }

    public void testNormalize() throws Exception
    {
        Vector4f vec = new Vector4f(5, 5, 5, 5);
        Vector4f expected = new Vector4f(
                0.5f,
                0.5f,
                0.5f,
                0.5f
        );
        Vector4f result = vec.normalized();
        vec.normalize();
        assertEquals(expected, result);
        assertEquals(result, vec);

        Vector4f zeroVec = new Vector4f();
        try
        {
            zeroVec.normalize();
            fail("normalize() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}

        try
        {
            Vector4f normalized = zeroVec.normalized();
            fail("normalized() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}
    }

    public void testAdd() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        Vector4f right = new Vector4f(0.0f, 42.0f, -2.0f, 1.0f);
        Vector4f expected = new Vector4f(1.0f, 38.86f, 0.0f, 2.0f);
        Vector4f result = left.add(right);
        assertEquals(expected, result);
    }

    public void testSub() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        Vector4f right = new Vector4f(0.0f, 42.0f, -2.0f, 1.0f);
        Vector4f expected = new Vector4f(1.0f, -45.14f, 4.0f, 0.0f);
        Vector4f result = left.sub(right);
        assertEquals(expected, result);
    }

    public void testMul() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        Vector4f right = new Vector4f(0.0f, 2.0f, -2.0f, 1.0f);
        Vector4f expected = new Vector4f(0.0f, -6.28f, -4.0f, 1.0f);
        Vector4f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testScalarMul() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        float right = 2.0f;
        Vector4f expected = new Vector4f(2.0f, -6.28f, 4.0f, 2.0f);
        Vector4f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testDiv() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        Vector4f right = new Vector4f(0.0f, 2.0f, -2.0f, 1.0f);

        try
        {
            Vector4f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch(IllegalArgumentException ignored) {}

        right = new Vector4f(2.0f, 2.0f, -2.0f, 1.0f);
        Vector4f expected = new Vector4f(0.5f, -1.57f, -1.0f, 1.0f);
        Vector4f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testScalarDiv() throws Exception
    {
        Vector4f left = new Vector4f(1.0f, -3.14f, 2.0f, 1.0f);
        float right = 0.0f;

        try
        {
            Vector4f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch (IllegalArgumentException ignored) {}

        right = 2.0f;
        Vector4f expected = new Vector4f(0.5f, -1.57f, 1.0f, 0.5f);
        Vector4f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testEquals() throws Exception
    {
        Vector4f vec1 = new Vector4f();
        Vector4f vec2 = new Vector4f();
        boolean equals = vec1.equals(vec2);
        assertTrue(equals);
    }

    public void testCompareTo() throws Exception
    {
        Vector4f vec1 = new Vector4f(-1.0f, 0.0f, -3.14f, 1.0f);
        Vector4f vec2 = new Vector4f(0.0f, 5.0f, 1.0f, 1.0f);
        Vector4f vec3 = new Vector4f(2.0f, 5.0f, 1.0f, 2.0f);

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