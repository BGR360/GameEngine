/**
 * Created by Ben on 8/26/15.
 *
 * 4x4 floating-point matrix.
 * Matrix is stored in row-major order.
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

public class Matrix4f
{
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;

    private float[][] m = new float[NUM_ROWS][NUM_COLS];

    public Matrix4f()
    {
        this(1.0f, 0.0f, 0.0f, 0.0f,
             0.0f, 1.0f, 0.0f, 0.0f,
             0.0f, 0.0f, 1.0f, 0.0f,
             0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    public Matrix4f(
            float r0c0, float r0c1, float r0c2, float r0c3,
            float r1c0, float r1c1, float r1c2, float r1c3,
            float r2c0, float r2c1, float r2c2, float r2c3,
            float r3c0, float r3c1, float r3c2, float r3c3
    )
    {
        m[0][0] = r0c0;     m[0][1] = r0c1;     m[0][2] = r0c2;     m[0][3] = r0c3;
        m[1][0] = r1c0;     m[1][1] = r1c1;     m[1][2] = r1c2;     m[1][3] = r1c3;
        m[2][0] = r2c0;     m[2][1] = r2c1;     m[2][2] = r2c2;     m[2][3] = r2c3;
        m[3][0] = r3c0;     m[3][1] = r3c1;     m[3][2] = r3c2;     m[3][3] = r3c3;
    }

    public Matrix4f(Matrix3f mat3)
    {
        this(
                mat3.get(0, 0), mat3.get(0, 1), mat3.get(0, 2), 0,
                mat3.get(1, 0), mat3.get(1, 1), mat3.get(1, 2), 0,
                mat3.get(2, 0), mat3.get(2, 1), mat3.get(2, 2), 0,
                0, 0, 0, 1
        );
    }

    public Matrix4f(float[][] array)
    {
        setArray(array);
    }

    public static Matrix4f transpose(@NotNull Matrix4f matrix)
    {
        return new Matrix4f(
                matrix.get(0, 0), matrix.get(1, 0), matrix.get(2, 0), matrix.get(3, 0),
                matrix.get(0, 1), matrix.get(1, 1), matrix.get(2, 1), matrix.get(3, 1),
                matrix.get(0, 2), matrix.get(1, 2), matrix.get(2, 2), matrix.get(3, 2),
                matrix.get(0, 3), matrix.get(1, 3), matrix.get(2, 3), matrix.get(3, 3)
        );
    }

    public static Matrix4f scale(@NotNull Vector3f scale)
    {
        return new Matrix4f(Matrix3f.scale(scale));
    }

    public static Matrix4f rotate(float thetaDegrees, Vector3f axis)
    {
        return new Matrix4f(Matrix3f.rotate(thetaDegrees, axis));
    }

    public static Matrix4f rotate(Vector3f eulerAngles)
    {
        return new Matrix4f(Matrix3f.rotate(eulerAngles));
    }

    public static Matrix4f rotate(float degreesX, float degreesY, float degreesZ)
    {
        return new Matrix4f(Matrix3f.rotate(degreesX, degreesY, degreesZ));
    }

    public static Matrix4f translate(Vector3f translation)
    {
        return new Matrix4f(
                1, 0, 0, translation.x,
                0, 1, 0, translation.y,
                0, 0, 1, translation.z,
                0, 0, 0, 1
        );
    }

    public static Matrix4f lookAt(
            Vector3f position,
            Vector3f target,
            Vector3f up
    )
    {
        return new Matrix4f(Matrix3f.lookAt(position, target, up));
    }

    public static Matrix4f lookAt(
            Vector3f position,
            Vector3f target
    )
    {
        return new Matrix4f(Matrix3f.lookAt(position, target));
    }

    public static Matrix4f perspective(float fov, float aspectRatio, float zNear, float zFar)
    {
        return new Matrix4f(
            1.0f / (aspectRatio * (float)Math.tan(fov / 2.0f)), 0, 0, 0,
            0, 1.0f / (float)Math.tan(fov / 2.0f), 0, 0,
            0, 0, (-zNear - zFar) / (zFar - zNear), (-2.0f * zFar * zNear) / (zFar - zNear),
            0, 0, -1, 0
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

    public Matrix4f copy()
    {
        return new Matrix4f(m);
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

    public Matrix4f add(@NotNull Matrix4f right)
    {
        return new Matrix4f(
                m[0][0] + right.get(0, 0),	m[0][1] + right.get(0, 1),  m[0][2] + right.get(0, 2),  m[0][3] + right.get(0, 3),
                m[1][0] + right.get(1, 0),	m[1][1] + right.get(1, 1),  m[1][2] + right.get(1, 2),  m[1][3] + right.get(1, 3),
                m[2][0] + right.get(2, 0),	m[2][1] + right.get(2, 1),  m[2][2] + right.get(2, 2),  m[2][3] + right.get(2, 3),
                m[3][0] + right.get(3, 0),	m[3][1] + right.get(3, 1),  m[3][2] + right.get(3, 2),  m[3][3] + right.get(3, 3)
        );
    }

    public Matrix4f sub(@NotNull Matrix4f right)
    {
        return new Matrix4f(
                m[0][0] - right.get(0, 0),	m[0][1] - right.get(0, 1),  m[0][2] - right.get(0, 2),  m[0][3] - right.get(0, 3),
                m[1][0] - right.get(1, 0),	m[1][1] - right.get(1, 1),  m[1][2] - right.get(1, 2),  m[1][3] - right.get(1, 3),
                m[2][0] - right.get(2, 0),	m[2][1] - right.get(2, 1),  m[2][2] - right.get(2, 2),  m[2][3] - right.get(2, 3),
                m[3][0] - right.get(3, 0),	m[3][1] - right.get(3, 1),  m[3][2] - right.get(3, 2),  m[3][3] - right.get(3, 3)
        );
    }

    public Matrix4f mul(@NotNull Matrix4f right)
    {
        return new Matrix4f(
                m[0][0] * right.get(0, 0) + m[0][1] * right.get(1, 0) + m[0][2] * right.get(2, 0) + m[0][3] * right.get(3, 0),
                m[0][0] * right.get(0, 1) + m[0][1] * right.get(1, 1) + m[0][2] * right.get(2, 1) + m[0][3] * right.get(3, 1),
                m[0][0] * right.get(0, 2) + m[0][1] * right.get(1, 2) + m[0][2] * right.get(2, 2) + m[0][3] * right.get(3, 2),
                m[0][0] * right.get(0, 3) + m[0][1] * right.get(1, 3) + m[0][2] * right.get(2, 3) + m[0][3] * right.get(3, 3),

                m[1][0] * right.get(0, 0) + m[1][1] * right.get(1, 0) + m[1][2] * right.get(2, 0) + m[1][3] * right.get(3, 0),
                m[1][0] * right.get(0, 1) + m[1][1] * right.get(1, 1) + m[1][2] * right.get(2, 1) + m[1][3] * right.get(3, 1),
                m[1][0] * right.get(0, 2) + m[1][1] * right.get(1, 2) + m[1][2] * right.get(2, 2) + m[1][3] * right.get(3, 2),
                m[1][0] * right.get(0, 3) + m[1][1] * right.get(1, 3) + m[1][2] * right.get(2, 3) + m[1][3] * right.get(3, 3),

                m[2][0] * right.get(0, 0) + m[2][1] * right.get(1, 0) + m[2][2] * right.get(2, 0) + m[2][3] * right.get(3, 0),
                m[2][0] * right.get(0, 1) + m[2][1] * right.get(1, 1) + m[2][2] * right.get(2, 1) + m[2][3] * right.get(3, 1),
                m[2][0] * right.get(0, 2) + m[2][1] * right.get(1, 2) + m[2][2] * right.get(2, 2) + m[2][3] * right.get(3, 2),
                m[2][0] * right.get(0, 3) + m[2][1] * right.get(1, 3) + m[2][2] * right.get(2, 3) + m[2][3] * right.get(3, 3),

                m[3][0] * right.get(0, 0) + m[3][1] * right.get(1, 0) + m[3][2] * right.get(2, 0) + m[3][3] * right.get(3, 0),
                m[3][0] * right.get(0, 1) + m[3][1] * right.get(1, 1) + m[3][2] * right.get(2, 1) + m[3][3] * right.get(3, 1),
                m[3][0] * right.get(0, 2) + m[3][1] * right.get(1, 2) + m[3][2] * right.get(2, 2) + m[3][3] * right.get(3, 2),
                m[3][0] * right.get(0, 3) + m[3][1] * right.get(1, 3) + m[3][2] * right.get(2, 3) + m[3][3] * right.get(3, 3)
        );
    }

    public Matrix4f mul(float left)
    {
        return new Matrix4f(
                left * m[0][0], left * m[0][1], left * m[0][2], left * m[0][3],
                left * m[1][0], left * m[1][1], left * m[1][2], left * m[1][3],
                left * m[2][0], left * m[2][1], left * m[2][2], left * m[2][3],
                left * m[3][0], left * m[3][1], left * m[3][2], left * m[3][3]
        );
    }

    public Vector4f mul(@NotNull Vector4f vec)
    {
        return new Vector4f(
                vec.x * m[0][0] + vec.y * m[0][1] + vec.z * m[0][2] + vec.w * m[0][3],
                vec.x * m[1][0] + vec.y * m[1][1] + vec.z * m[1][2] + vec.w * m[1][3],
                vec.x * m[2][0] + vec.y * m[2][1] + vec.z * m[2][2] + vec.w * m[2][3],
                vec.x * m[3][0] + vec.y * m[3][1] + vec.z * m[3][2] + vec.w * m[3][3]
        );
    }

    public boolean equals(@NotNull Object o)
    {
        if(!(o instanceof Matrix4f))
            return false;
        Matrix4f other = (Matrix4f)o;
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
