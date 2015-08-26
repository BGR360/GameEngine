/**
 * Created by Ben on 8/26/15.
 *
 * 2x2 floating-point matrix.
 * Matrix is stored in row-major order.
 *
 * TODO: Add @NotNull annotations to all methods of matrices and vectors
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

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

    public static Matrix2f adjugate(@NotNull Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static float determinant(@NotNull Matrix2f matrix)
    {
        return 0.0f;
    }

    public static Matrix2f inverse(@NotNull Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static Matrix2f transpose(@NotNull Matrix2f matrix)
    {
        return new Matrix2f();
    }

    public static Matrix2f scale(@NotNull Vector2f scale)
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

    public Matrix2f add(@NotNull Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f sub(@NotNull Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f mul(@NotNull Matrix2f right)
    {
        return new Matrix2f();
    }

    public Matrix2f mul(float left)
    {
        return new Matrix2f();
    }

    public Vector2f mul(@NotNull Vector2f vec)
    {
        return new Vector2f();
    }

    public boolean equals(@NotNull Object o)
    {
        if(!(o instanceof Matrix2f))
            return false;
        Matrix2f other = (Matrix2f)o;
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                if(m[i][j] != other.get(i, j))
                    return false;
            }
        }
        return true;
    }

    public String toString()
    {
        String str = "{";
        for(int i = 0; i < 2; i++)
        {
            str += "{";
            for(int j = 0; j < 2; j++)
            {
                str += m[i][j];
                if(j + 1 < 2)
                    str += ", ";
            }
            str += "}";
            if(i + 1 < 2)
                str += ", ";
        }
        str += "}";
        return str;
    }
}
