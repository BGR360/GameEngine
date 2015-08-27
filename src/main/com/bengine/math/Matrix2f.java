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
    private static final int NUM_ROWS = 2;
    private static final int NUM_COLS = 2;

    private float[][] m = new float[NUM_ROWS][NUM_COLS];

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

    public Matrix2f(float[][] array)
    {
        setArray(array);
    }

    public static Matrix2f adjugate(@NotNull Matrix2f matrix)
    {
        return new Matrix2f(
                matrix.get(1, 1)		,	-1 * matrix.get(0, 1),
                -1 * matrix.get(1, 0)	,	matrix.get(0, 0)
        );
    }

    public static float determinant(@NotNull Matrix2f matrix)
    {
        return matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0);
    }

    public static Matrix2f inverse(@NotNull Matrix2f matrix)
    {
        return Matrix2f.adjugate(matrix).mul(
                (1.0f / Matrix2f.determinant(matrix))
        );
    }

    public static Matrix2f transpose(@NotNull Matrix2f matrix)
    {
        return new Matrix2f(
                matrix.get(0, 0), matrix.get(1, 0),
                matrix.get(0, 1), matrix.get(1, 1)
        );
    }

    public static Matrix2f scale(@NotNull Vector2f scale)
    {
        return new Matrix2f(
                scale.x, 0,
                0, scale.y
        );
    }

    public static Matrix2f rotate(float thetaDegrees)
    {
        float thetaRadians = (float)Math.toRadians(thetaDegrees);
        return new Matrix2f(
                (float)Math.cos(thetaRadians), -1 * (float)Math.sin(thetaRadians),
                (float)Math.sin(thetaRadians), (float)Math.cos(thetaRadians)
        );
    }

    public float[][] getArray()
    {
        return m;
    }

    public float[][] copyArray()
    {
        float[][] copy = new float[NUM_ROWS][NUM_COLS];
        for(int i = 0; i < NUM_ROWS; i++)
        {
            System.arraycopy(m[i], 0, copy[i], 0, NUM_COLS);
        }
        return copy;
    }

    public Matrix2f copy()
    {
        return new Matrix2f(m);
    }

    public float get(int row, int col)
    {
        if(row < 0 || row > NUM_ROWS)
            throw new ArrayIndexOutOfBoundsException(
                    "Cannot access row " + row + " of " +
                    NUM_ROWS + "x" + NUM_COLS + " matrix."
            );
        if(col < 0 || col > NUM_COLS)
            throw new ArrayIndexOutOfBoundsException(
                    "Cannot access column " + col + " of " +
                    NUM_ROWS + "x" + NUM_COLS + " matrix."
            );
        return m[row][col];
    }

    public void set(int row, int col, float value)
    {
        if(row < 0 || row > NUM_ROWS)
            throw new ArrayIndexOutOfBoundsException(
                    "Cannot access row " + row + " of " +
                    NUM_ROWS + "x" + NUM_COLS + " matrix."
            );
        if(col < 0 || col > NUM_COLS)
            throw new ArrayIndexOutOfBoundsException(
                    "Cannot access column " + col + " of " +
                    NUM_ROWS + "x" + NUM_COLS + " matrix."
            );
        m[row][col] = value;
    }

    public void setArray(float[][] array)
    {
        if(array.length == 0)
            throw new IllegalArgumentException("Empty array passed as argument.");
        if(array.length < NUM_ROWS || array[0].length < NUM_COLS)
            throw new IllegalArgumentException(
                    "Array must be " + NUM_ROWS + "x" + NUM_COLS + ". Actual dimensions: " +
                    array.length + "x" + array[0].length
            );
        m = array;
    }

    public Matrix2f add(@NotNull Matrix2f right)
    {
        return new Matrix2f(
                m[0][0] + right.get(0, 0),		m[0][1] + right.get(0, 1),
                m[1][0] + right.get(1, 0),		m[1][1] + right.get(1, 1)
        );
    }

    public Matrix2f sub(@NotNull Matrix2f right)
    {
        return new Matrix2f(
                m[0][0] - right.get(0, 0), m[0][1] - right.get(0, 1),
                m[1][0] - right.get(1, 0), m[1][1] - right.get(1, 1)
        );
    }

    public Matrix2f mul(@NotNull Matrix2f right)
    {
        return new Matrix2f(
                m[0][0] * right.get(0, 0) + m[0][1] * right.get(1, 0),	m[0][0] * right.get(0, 1) + m[0][1] * right.get(1, 1),

                m[1][0] * right.get(0, 0) + m[1][1] * right.get(1, 0),	m[1][0] * right.get(0, 1) + m[1][1] * right.get(1, 1)
        );
    }

    public Matrix2f mul(float left)
    {
        return new Matrix2f(
                left * m[0][0], left * m[0][1],
                left * m[1][0], left * m[1][1]
        );
    }

    public Vector2f mul(@NotNull Vector2f vec)
    {
        return new Vector2f(
                vec.x * m[0][0] + vec.y * m[0][1],
                vec.x * m[1][0] + vec.y * m[1][1]
        );
    }

    public boolean equals(@NotNull Object o)
    {
        if(!(o instanceof Matrix2f))
            return false;
        Matrix2f other = (Matrix2f)o;
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
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
        for(int i = 0; i < NUM_ROWS; i++)
        {
            str += "{";
            for(int j = 0; j < NUM_COLS; j++)
            {
                str += m[i][j];
                if(j + 1 < NUM_COLS)
                    str += ", ";
            }
            str += "}";
            if(i + 1 < NUM_ROWS)
                str += ", ";
        }
        str += "}";
        return str;
    }
}
