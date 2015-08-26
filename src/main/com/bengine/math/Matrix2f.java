/**
 * Created by Ben on 8/26/15.
 *
 * 2x2 floating-point matrix.
 * Matrix is stored in row-major order.
 */

package com.bengine.math;

public class Matrix2f
{
    private float[][] m = new float[2][2];

    public Matrix2f()
    {
        this(1.0f, 0.0f,
             0.0f, 1.0f);
    }

    public Matrix2f(
            float r0c0, float r0c1,
            float r1c0, float r1c1
    )
    {
        m[0][0] = r0c0;     m[0][1] = r0c1;
        m[1][0] = r1c0;     m[1][1] = r1c1;
    }

    public static Matrix2f adjugate(Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static float determinant(Matrix2f matrix)
    {
        return 0.0f;
    }

    public static Matrix2f inverse(Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static Matrix2f transpose(Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static Matrix2f scale(Vector2f scale)
    {
        return new Matrix2f();
    }

    public static Matrix2f rotate(float thetaDegrees)
    {
        return new Matrix2f();
    }

    public float[][] getArray()
    {
        return m;
    }

    public float[][] copyArray()
    {
        /*float[][] copy = new float[2][2];
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                copy[i][j] = m[i][j];
            }
        }
        return copy;*/
        return new float[0][0];
    }

    public float get(int row, int col)
    {
        return 0.0f;
    }

    public void set(int row, int col, float value)
    {

    }

    public Matrix2f add(Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f sub(Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f mul(Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f mul(float left)
    {
        return new Matrix2f();
    }

    public Vector2f mul(Vector2f vec)
    {
        return new Vector2f();
    }
}
