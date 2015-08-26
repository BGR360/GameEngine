/**
 * Created by Ben on 8/26/15.
 *
 * Unit test for Vector2f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Vector2fTest extends TestCase
{

    public void testReferences() throws Exception
    {
        Vector2f vec1 = new Vector2f(1.0f, 2.0f);
        float x = vec1.x;
        float y = vec1.y;
        Vector2f vec1ref = vec1;
        Vector2f vec2 = new Vector2f(vec1.x, vec1.y);
        Vector2f vec3 = vec1.copy();

        vec1.x = 5;
        vec1.y = 0;


    }

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
        assertEquals(expected, result);
    }

    public void testNormalize() throws Exception
    {
        Vector2f vec = new Vector2f(5, 5);
        Vector2f expected = new Vector2f((float)Math.sqrt(2.0f) / 2.0f, (float)Math.sqrt(2.0f) / 2.0f);
        Vector2f result = vec.normalized();
        vec.normalize();
        assertEquals(expected, result);
        assertEquals(result, vec);

        Vector2f zeroVec = new Vector2f();
        try
        {
            zeroVec.normalize();
            fail("normalize() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}

        try
        {
            Vector2f normalized = zeroVec.normalized();
            fail("normalized() did not throw IllegalStateException for zero vector.");
        }
        catch(IllegalStateException ignored) {}
    }

    public void testAdd() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        Vector2f right = new Vector2f(0.0f, 42.0f);
        Vector2f expected = new Vector2f(1.0f, 38.86f);
        Vector2f result = left.add(right);
        assertEquals(expected, result);
    }

    public void testSub() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        Vector2f right = new Vector2f(0.0f, 42.0f);
        Vector2f expected = new Vector2f(1.0f, -45.14f);
        Vector2f result = left.sub(right);
        assertEquals(expected, result);
    }

    public void testMul() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        Vector2f right = new Vector2f(0.0f, 2.0f);
        Vector2f expected = new Vector2f(0.0f, -6.28f);
        Vector2f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testScalarMul() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        float right = 2.0f;
        Vector2f expected = new Vector2f(2.0f, -6.28f);
        Vector2f result = left.mul(right);
        assertEquals(expected, result);
    }

    public void testDiv() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        Vector2f right = new Vector2f(0.0f, 2.0f);

        try
        {
            Vector2f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch(IllegalArgumentException ignored) {}

        right = new Vector2f(2.0f, 2.0f);
        Vector2f expected = new Vector2f(0.5f, -1.57f);
        Vector2f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testScalarDiv() throws Exception
    {
        Vector2f left = new Vector2f(1.0f, -3.14f);
        float right = 0.0f;

        try
        {
            Vector2f result = left.div(right);
            fail("div() did not throw IllegalArgumentException for divide-by-zero");
        }
        catch (IllegalArgumentException ignored) {}

        right = 2.0f;
        Vector2f expected = new Vector2f(0.5f, -1.57f);
        Vector2f result = left.div(right);
        assertEquals(expected, result);
    }

    public void testEquals() throws Exception
    {
        Vector2f vec1 = new Vector2f();
        Vector2f vec2 = new Vector2f();
        boolean equals = vec1.equals(vec2);
        assertTrue(equals);
    }

    public void testCompareTo() throws Exception
    {
        Vector2f vec1 = new Vector2f(-1.0f, 0.0f);
        Vector2f vec2 = new Vector2f(-1.0f, 5.0f);
        Vector2f vec3 = new Vector2f(2.0f, -3.2f);

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