/**
 * Created by Ben on 8/26/15.
 * 
 * Unit test for Matrix4f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Matrix4fTest extends TestCase
{
    public void testAddition() throws Exception
    {
        Matrix4f left = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f right = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f expected = new Matrix4f(
            2, 4, 6, 8,
            10, 12, 14, 16,
            18, 20, 22, 24,
            26, 28, 30, 32);

        Matrix4f result = left.add(right);
        assertEquals(expected, result);
    }

    public void testSubtraction() throws Exception
    {
        Matrix4f left = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f right = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f expected = new Matrix4f(
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0);

        Matrix4f result = left.sub(right);
        assertEquals(expected, result);
    }

    public void testScalarMultiplication() throws Exception
    {
        float op = 2.0f;
        Matrix4f matrix = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f expected = new Matrix4f(
            2, 4, 6, 8,
            10, 12, 14, 16,
            18, 20, 22, 24,
            26, 28, 30, 32);

        Matrix4f result = matrix.mul(op);

        assertEquals(expected, result);
    }

    public void testVectorMultiplication() throws Exception
    {
        Matrix4f op = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Vector4f vec = new Vector4f(7, -6, 2, -1);
        Vector4f expected = new Vector4f(7, 35, 63, 91)
                .add(new Vector4f(-12, -36, -60, -84))
                .add(new Vector4f(6, 14, 22, 30))
                .add(new Vector4f(-4, -8, -12, -16));

        Vector4f result = op.mul(vec);
        assertEquals(expected, result);
    }

    public void testMatrixMultiplication() throws Exception
    {
        Matrix4f left = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f right = new Matrix4f(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16);
        Matrix4f expected = new Matrix4f(
            90, 100, 110, 120,
            202, 228, 254, 280,
            314, 356, 398, 440,
            426, 484, 542, 600);

        Matrix4f result = left.mul(right);
        assertEquals(expected, result);

        expected = new Matrix4f(
                90, 100, 110, 120,
                202, 228, 254, 280,
                314, 356, 398, 440,
                426, 484, 542, 600);
        Matrix4f result2 = right.mul(left);
        assertEquals(expected, result2);
    }
}
