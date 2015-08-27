/**
 * Created by Ben on 8/26/15.
 * 
 * Unit test for Matrix3f
 */

package com.bengine.math;

import junit.framework.TestCase;

public class Matrix3fTest extends TestCase
{

    static class Util
    {
        public static void assertFloatArrayEquals(float[][] expected, float[][] actual)
        {
            int rows = expected.length;
            int cols = expected[0].length;
            assertEquals("Rows: ", rows, actual.length);
            assertEquals("Cols: ", cols, actual[0].length);
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < cols; j++)
                {
                    assertEquals(
                            "At [" + i + "][" + j + "]: ",
                            expected[i][j], actual[i][j]
                    );
                }
            }
        }

        public static void assertCloseEnough(String message, float expected, float actual)
        {
            final float THRESHOLD = 0.000001f;

            if(Math.abs(expected - actual) > THRESHOLD)
            {
                fail(
                        message +
                        "Float not within threshold: " +
                        "expected:<" + expected + "> " +
                        "but was:<" + actual + ">"
                );
            }
        }

        public static void assertCloseEnough(float expected, float actual)
        {
            assertCloseEnough("", expected, actual);
        }

        public static void assertCloseEnough(Vector3f expected, Vector3f actual)
        {
            assertCloseEnough("x-component", expected.x, actual.x);
            assertCloseEnough("y-component", expected.y, actual.y);
            assertCloseEnough("z-component", expected.z, actual.z);
        }
    }

    public void testIndexOutOfBounds() throws Exception
    {
        Matrix3f mat = new Matrix3f();

        try
        {
            float value = mat.get(7, -1);
            fail("get() did not throw ArrayIndexOutOfBoundsException");
        }
        catch(ArrayIndexOutOfBoundsException ignored) {}

        try
        {
            mat.set(-1, 5, 0.0f);
            fail("set() did not throw ArrayIndexOutOfBoundsException");
        }
        catch(ArrayIndexOutOfBoundsException ignored) {}
    }

    public void testArrayCopy() throws Exception
    {
        Matrix3f src = new Matrix3f(
                1, 2, 0,
                0, 1, 0,
                4, 0, 1
        );
        float[][] arrayref = src.getArray();
        float[][] array = src.copyArray();
        Matrix3f copy = src.copy();

        float[][] newArray =
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        src.setArray(newArray);

        float[][] originalArray =
                {
                        {1, 2, 0},
                        {0, 1, 0},
                        {4, 0, 1}
                };

        Util.assertFloatArrayEquals(newArray, src.getArray());
        Util.assertFloatArrayEquals(originalArray, arrayref);
        Util.assertFloatArrayEquals(originalArray, array);
        assertEquals(new Matrix3f(originalArray), copy);
    }

    public void testAdjugate() throws Exception
    {
        Matrix3f matrix = new Matrix3f(
            1, 2, 3,
            4, 4, 6,
            7, 8, 9);
        Matrix3f expected = new Matrix3f(
            -12, 6, 0,
            6, -12, 6,
            4, 6, -4);

        Matrix3f result = Matrix3f.adjugate(matrix);
        assertEquals(expected, result);

        matrix = new Matrix3f(
            2, 4, -1,
            3, 1, 0,
            0, 5, -2);
        expected = new Matrix3f(
            -2, 3, 1,
            6, -4, -3,
            15, -10, -10);

        result = Matrix3f.adjugate(matrix);
        assertEquals(expected, result);
    }

    public void testDeterminant() throws Exception
    {
        Matrix3f matrix = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        float expected = 0.0f;

        float result = Matrix3f.determinant(matrix);

        assertEquals(expected, result);
    }

    public void testInverse() throws Exception
    {
        Matrix3f matrix = new Matrix3f(
            1, 2, 3,
            4, 4, 6,
            7, 8, 9);
        Matrix3f expected = new Matrix3f(
            -1, 0.5f, 0,
            0.5f, -1, 0.5f,
            0.333333333f, 0.5f, -0.333333333f);

        Matrix3f result = Matrix3f.inverse(matrix);

        assertEquals(expected, result);
    }

    public void testTranspose() throws Exception
    {
        Matrix3f matrix = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Matrix3f expected = new Matrix3f(
            1, 4, 7,
            2, 5, 8,
            3, 6, 9);

        Matrix3f result = Matrix3f.transpose(matrix);
        assertEquals(expected, result);
    }

    public void testRotateX() throws Exception
    {
        Vector3f yvec = new Vector3f(0, 2, 0);
        Matrix3f op = Matrix3f.rotate(45, new Vector3f(1, 0, 0));
        Vector3f expected = new Vector3f(0, (float)Math.sqrt(2.0f), (float)Math.sqrt(2.0f));

        Vector3f result = op.mul(yvec);

        assertEquals(expected, result);
    }

    public void testRotateY() throws Exception
    {
        Vector3f zvec = new Vector3f(0, 0, 2);
        Matrix3f op = Matrix3f.rotate(45, new Vector3f(0, 1, 0));
        Vector3f expected = new Vector3f((float)Math.sqrt(2.0f), 0, (float)Math.sqrt(2.0f));

        Vector3f result = op.mul(zvec);

        assertEquals(expected, result);
    }

    public void testRotateZ() throws Exception
    {
        Vector3f xvec = new Vector3f(2, 0, 0);
        Matrix3f op = Matrix3f.rotate(45, new Vector3f(0, 0, 1));
        Vector3f expected = new Vector3f((float)Math.sqrt(2.0f), (float)Math.sqrt(2.0f), 0);

        Vector3f result = op.mul(xvec);

        assertEquals(expected, result);
    }

    public void testRotateXY() throws Exception
    {
        Vector3f vec = new Vector3f(0, 2, 0);
        Matrix3f opX = Matrix3f.rotate(90, new Vector3f(1, 0, 0));
        Matrix3f opY = Matrix3f.rotate(45, new Vector3f(0, 1, 0));
        Matrix3f op = opY.mul(opX);
        Vector3f expected = new Vector3f((float)Math.sqrt(2.0f), 0, (float)Math.sqrt(2.0f));

        Vector3f result = op.mul(vec);

        assertEquals(expected, result);
    }

    public void testRotateXYZ() throws Exception
    {
        Vector3f vec = new Vector3f(0, 3, 0);
        Matrix3f opX = Matrix3f.rotate(90, new Vector3f(1, 0, 0));
        Matrix3f opY = Matrix3f.rotate(90, new Vector3f(0, 1, 0));
        Matrix3f opZ = Matrix3f.rotate(90, new Vector3f(0, 0, 1));
        Matrix3f op = opZ.mul(opY.mul(opX));
        Vector3f expected = new Vector3f(0, 3, 0);

        Vector3f result = op.mul(vec);

        assertEquals(expected, result);
    }

    public void testTilde() throws Exception
    {
        Vector3f a = new Vector3f(1, 0, 0);
        Vector3f b = new Vector3f(0, 0, 1);

        Vector3f expected = new Vector3f(0, -1, 0);
        Vector3f c_cross = a.cross(b);
        Vector3f c_tilde;

        Matrix3f tilde = Matrix3f.tilde(a);
        c_tilde = tilde.mul(b);

        assertEquals(expected, c_cross);
        assertEquals(expected, c_tilde);
    }

    public void testAngleAxis() throws Exception
    {
        Vector3f vec = new Vector3f((float)Math.sqrt(2.0f), 0, (float)Math.sqrt(2.0f));
        Vector3f axis = new Vector3f((float)Math.sqrt(2.0f), 0, -(float)Math.sqrt(2.0f));
        Matrix3f op = Matrix3f.rotate(45, axis);
        Vector3f expected = new Vector3f(1, -(float)Math.sqrt(2.0f), 1);

        Vector3f result = op.mul(vec);

        Util.assertCloseEnough(expected, result);
    }

    public void testTranslate() throws Exception
    {
        Vector2f position = new Vector2f(1, 2);
        Vector2f translation = new Vector2f(5, -5);

        Vector3f result = Matrix3f.translate(translation).mul(new Vector3f(position));
        Vector3f expected = new Vector3f(6, -3, 1);

        assertEquals(expected, result);
    }

    public void testScale() throws Exception
    {
        Vector3f vec = new Vector3f(-1, 3, 2);
        Vector3f scale = new Vector3f(2, 4, 0);
        Vector3f expected = new Vector3f(-2, 12, 0);

        Vector3f result = Matrix3f.scale(scale).mul(vec);

        assertEquals(expected, result);
    }

    public void testGetSet() throws Exception
    {
        Matrix3f mat = new Matrix3f();
        float a = mat.get(0, 0);
        float b = mat.get(1, 1);
        float c = mat.get(0, 1);
        assertEquals(1.0f, a);
        assertEquals(1.0f, b);
        assertEquals(0.0f, c);

        mat.set(0, 0, 1);
        mat.set(0, 1, 2);
        mat.set(1, 0, 3);
        mat.set(1, 1, 4);

        a = mat.get(0, 0);
        b = mat.get(1, 1);
        c = mat.get(0, 1);
        assertEquals(1.0f, a);
        assertEquals(4.0f, b);
        assertEquals(2.0f, c);
    }

    public void testAdd() throws Exception
    {
        Matrix3f left = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Matrix3f right = new Matrix3f(
            5, 2, 4,
            -5, 4, -1,
            2, 3, -7);
        Matrix3f expected = new Matrix3f(
            6, 4, 7,
            -1, 9, 5,
            9, 11, 2);

        Matrix3f result = left.add(right);
        assertEquals(expected, result);
    }

    public void testSub() throws Exception
    {
        Matrix3f left = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Matrix3f right = new Matrix3f(
            5, 2, 4,
            -5, 4, -1,
            2, 3, -7);
        Matrix3f expected = new Matrix3f(
            -4, 0, -1,
            9, 1, 7,
            5, 5, 16);

        Matrix3f result = left.sub(right);
        assertEquals(expected, result);
    }

    public void testMatrixMul() throws Exception
    {
        Matrix3f left = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Matrix3f right = new Matrix3f(
            5, 2, 4,
            -5, 4, -1,
            2, 3, -7);
        Matrix3f expected = new Matrix3f(
            1, 19, -19,
            7, 46, -31,
            13, 73, -43);

        Matrix3f result = left.mul(right);
        assertEquals(expected, result);

        expected = new Matrix3f(
                41, 52, 63,
                4, 2, 0,
                -35, -37, -39);
        result = right.mul(left);
        assertEquals(expected, result);
    }

    public void testScalarMul() throws Exception
    {
        float op = 2.0f;
        Matrix3f matrix = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Matrix3f expected = new Matrix3f(
            2, 4, 6,
            8, 10, 12,
            14, 16, 18);

        Matrix3f result = matrix.mul(op);

        assertEquals(expected, result);
    }

    public void testVectorMul() throws Exception
    {
        Matrix3f op = new Matrix3f(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9);
        Vector3f vec = new Vector3f(7, -6, 2);
        Vector3f expected = new Vector3f(7, 28, 49).add(new Vector3f(-12, -30, -48)).add(new Vector3f(6, 12, 18));

        Vector3f result = op.mul(vec);
        assertEquals(expected, result);
    }
}
