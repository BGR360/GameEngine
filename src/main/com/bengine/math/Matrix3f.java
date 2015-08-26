/**
 * Created by Ben on 8/26/15.
 *
 * 3x3 floating-point matrix.
 * Matrix is stored in row-major order.
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

public class Matrix3f
{
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 3;

    private float[][] m = new float[NUM_ROWS][NUM_COLS];

    public Matrix3f()
    {
        this(1.0f, 0.0f, 0.0f,
             0.0f, 1.0f, 0.0f,
             0.0f, 0.0f, 1.0f);
    }

    public Matrix3f(
            float r0c0, float r0c1, float r0c2,
            float r1c0, float r1c1, float r1c2,
            float r2c0, float r2c1, float r2c2
    )
    {
        m[0][0] = r0c0;     m[0][1] = r0c1;     m[0][2] = r0c2;
        m[1][0] = r1c0;     m[1][1] = r1c1;     m[1][2] = r1c2;
        m[2][0] = r2c0;     m[2][1] = r2c1;     m[2][2] = r2c2;
    }

    public Matrix3f(float[][] array)
    {
        setArray(array);
    }

    public static Matrix3f adjugate(@NotNull Matrix3f matrix)
    {
        return Matrix3f.transpose(Matrix3f.cofactor(matrix));
    }

    public static Matrix3f cofactor(@NotNull Matrix3f matrix)
    {
        return new Matrix3f(
                matrix.get(1, 1) * matrix.get(2, 2) - matrix.get(1, 2) * matrix.get(2, 1),
                -1 * (matrix.get(1, 0) * matrix.get(2, 2) - matrix.get(1, 2) * matrix.get(2, 0)),
                matrix.get(1, 0) * matrix.get(2, 1) - matrix.get(1, 1) * matrix.get(2, 0),

                -1 * (matrix.get(0, 1) * matrix.get(2, 2) - matrix.get(0, 2) * matrix.get(2, 1)),
                matrix.get(0, 0) * matrix.get(2, 2) - matrix.get(0, 2) * matrix.get(2, 0),
                -1 * (matrix.get(0, 0) * matrix.get(2, 1) - matrix.get(0, 1) * matrix.get(2, 0)),

                matrix.get(0, 1) * matrix.get(1, 2) - matrix.get(0, 2) * matrix.get(1, 1),
                -1 * (matrix.get(0, 0) * matrix.get(1, 2) - matrix.get(0, 2) * matrix.get(1, 0)),
                matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0)
        );
    }

    public static float determinant(@NotNull Matrix3f matrix)
    {
        return matrix.get(0, 0) * matrix.get(1, 1) * matrix.get(2, 2)
               + matrix.get(0, 1) * matrix.get(1, 2) * matrix.get(2, 0)
               + matrix.get(0, 2) * matrix.get(1, 0) * matrix.get(2, 1)
               - matrix.get(0, 2) * matrix.get(1, 1) * matrix.get(2, 0)
               - matrix.get(0, 0) * matrix.get(1, 2) * matrix.get(2, 1)
               - matrix.get(0, 1) * matrix.get(1, 0) * matrix.get(2, 2);
    }

    public static Matrix3f inverse(@NotNull Matrix3f matrix)
    {
        return Matrix3f.adjugate(matrix).mul(
                (1.0f / Matrix3f.determinant(matrix))
        );
    }

    public static Matrix3f tilde(@NotNull Vector3f vec)
    {
        return new Matrix3f(
                0, -vec.z, vec.y,
                vec.z, 0, -vec.x,
                -vec.y, vec.x, 0
        );
    }

    public static Matrix3f transpose(@NotNull Matrix3f matrix)
    {
        return new Matrix3f(
                matrix.get(0, 0), matrix.get(1, 0), matrix.get(2, 0),
                matrix.get(0, 1), matrix.get(1, 1), matrix.get(2, 1),
                matrix.get(0, 2), matrix.get(1, 2), matrix.get(2, 2)
        );
    }

    public static Matrix3f scale(@NotNull Vector3f scale)
    {
        return new Matrix3f(
                scale.x, 0, 0,
                0, scale.y, 0,
                0, 0, scale.z
        );
    }

    public static Matrix3f rotate(float thetaDegrees, Vector3f axis)
    {
        Matrix3f I = new Matrix3f();
        float c = (float)Math.cos(Math.toRadians(thetaDegrees));
        float s = (float)Math.sin(Math.toRadians(thetaDegrees));
        float t = 1.0f - c;
        Vector3f a = axis.normalized();
        Matrix3f tildeAxis = tilde(a);

        //I + s * tildeAxis + t * (tildeAxis * tildeAxis)
        return I.add(tildeAxis.mul(s).add(
                tildeAxis.mul(tildeAxis).mul(t)
        ));
    }

    public static Matrix3f rotate(Vector3f eulerAngles)
    {
        return rotate(eulerAngles.x, eulerAngles.y, eulerAngles.z);
    }

    public static Matrix3f rotate(float degreesX, float degreesY, float degreesZ)
    {
        Matrix3f rotateX = rotate(degreesX, new Vector3f(1, 0, 0));
        Matrix3f rotateY = rotate(degreesY, new Vector3f(0, 1, 0));
        Matrix3f rotateZ = rotate(degreesZ, new Vector3f(0, 0, 1));

        return rotateZ.mul(rotateY.mul(rotateX));
    }

    public static Matrix3f translate(Vector2f translation)
    {
        return new Matrix3f(
                1, 0, translation.x,
                0, 1, translation.y,
                0, 0, 1
        );
    }

    public static Matrix3f lookAt(
        Vector3f position,
        Vector3f target,
        Vector3f up
    )
    {
        //N: lookAt vector
        Vector3f N = target.sub(position).normalized();
        //U: right vector
        Vector3f U = N.cross(up).normalized();
        //V: up vector
        Vector3f V = U.cross(N);

        //Construct the matrix
        return new Matrix3f(
                U.x, U.y, U.z,
                V.x, V.y, V.z,
                -N.x, -N.y, -N.z);
    }

    public static Matrix3f lookAt(
            Vector3f position,
            Vector3f target
    )
    {
        return lookAt(position, target, new Vector3f(0, 1, 0));
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

    public Matrix3f copy()
    {
        return new Matrix3f(m);
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

    public Matrix3f add(@NotNull Matrix3f right)
    {
        return new Matrix3f(
                m[0][0] + right.get(0, 0),	m[0][1] + right.get(0, 1),  m[0][2] + right.get(0, 2),
                m[1][0] + right.get(1, 0),	m[1][1] + right.get(1, 1),  m[1][2] + right.get(1, 2),
                m[2][0] + right.get(2, 0),	m[2][1] + right.get(2, 1),  m[2][2] + right.get(2, 2)
        );
    }

    public Matrix3f sub(@NotNull Matrix3f right)
    {
        return new Matrix3f(
                m[0][0] - right.get(0, 0),	m[0][1] - right.get(0, 1),  m[0][2] - right.get(0, 2),
                m[1][0] - right.get(1, 0),	m[1][1] - right.get(1, 1),  m[1][2] - right.get(1, 2),
                m[2][0] - right.get(2, 0),	m[2][1] - right.get(2, 1),  m[2][2] - right.get(2, 2)
        );
    }

    public Matrix3f mul(@NotNull Matrix3f right)
    {
        return new Matrix3f(
                m[0][0] * right.get(0, 0) + m[0][1] * right.get(1, 0) + m[0][2] * right.get(2, 0),
                m[0][0] * right.get(0, 1) + m[0][1] * right.get(1, 1) + m[0][2] * right.get(2, 1),
                m[0][0] * right.get(0, 2) + m[0][1] * right.get(1, 2) + m[0][2] * right.get(2, 2),

                m[1][0] * right.get(0, 0) + m[1][1] * right.get(1, 0) + m[1][2] * right.get(2, 0),
                m[1][0] * right.get(0, 1) + m[1][1] * right.get(1, 1) + m[1][2] * right.get(2, 1),
                m[1][0] * right.get(0, 2) + m[1][1] * right.get(1, 2) + m[1][2] * right.get(2, 2),

                m[2][0] * right.get(0, 0) + m[2][1] * right.get(1, 0) + m[2][2] * right.get(2, 0),
                m[2][0] * right.get(0, 1) + m[2][1] * right.get(1, 1) + m[2][2] * right.get(2, 1),
                m[2][0] * right.get(0, 2) + m[2][1] * right.get(1, 2) + m[2][2] * right.get(2, 2)
        );
    }

    public Matrix3f mul(float left)
    {
        return new Matrix3f(
                left * m[0][0], left * m[0][1], left * m[0][2],
                left * m[1][0], left * m[1][1], left * m[1][2],
                left * m[2][0], left * m[2][1], left * m[2][2]
        );
    }

    public Vector3f mul(@NotNull Vector3f vec)
    {
        return new Vector3f(
                vec.x * m[0][0] + vec.y * m[0][1] + vec.z * m[0][2],
                vec.x * m[1][0] + vec.y * m[1][1] + vec.z * m[1][2],
                vec.x * m[2][0] + vec.y * m[2][1] + vec.z * m[2][2]
        );
    }

    public boolean equals(@NotNull Object o)
    {
        if(!(o instanceof Matrix3f))
            return false;
        Matrix3f other = (Matrix3f)o;
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
