/**
 * Created by Ben on 8/26/15.
 *
 * 3-component, floating-point vector
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

public class Vector3f implements Comparable<Vector3f>
{
    public float x;
    public float y;
    public float z;

    public Vector3f()
    {
        this(0.0f, 0.0f, 0.0f);
    }

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float magnitude()
    {
        return (float)Math.sqrt(
                x * x +
                y * y +
                z * z
        );
    }

    public float dot(Vector3f right)
    {
        return x * right.x +
               y * right.y +
               z * right.z;
    }

    public void normalize()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        x /= mag;
        y /= mag;
        z /= mag;
    }

    public Vector3f normalized()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        return new Vector3f(
                x / mag,
                y / mag,
                z / mag
        );
    }

    public Vector3f add(Vector3f right)
    {
        return new Vector3f(
                x + right.x,
                y + right.y,
                z + right.z
        );
    }

    public Vector3f add(float right)
    {
        return new Vector3f(
                x + right,
                y + right,
                y + right
        );
    }

    public Vector3f sub(Vector3f right)
    {
        return new Vector3f(
                x - right.x,
                y - right.y,
                z - right.z
        );
    }

    public Vector3f sub(float right)
    {
        return new Vector3f(
                x - right,
                y - right,
                z - right
        );
    }

    public Vector3f mul(Vector3f right)
    {
        return new Vector3f(
                x * right.x,
                y * right.y,
                z * right.z
        );
    }

    public Vector3f mul(float right)
    {
        return new Vector3f(
                x * right,
                y * right,
                z * right
        );
    }

    public Vector3f div(Vector3f right)
    {
        if(right.x == 0.0f ||
           right.y == 0.0f ||
           right.z == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector3f(
                x / right.x,
                y / right.y,
                z / right.z
        );
    }

    public Vector3f div(float right)
    {
        if(right == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector3f(
                x / right,
                y / right,
                z / right
        );
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Vector3f))
            return false;
        Vector3f other = (Vector3f)o;
        return x == other.x &&
               y == other.y &&
               z == other.z;
    }

    public String toString()
    {
        return "{" + x + ", " + y + ", " + z + "}";
    }

    @Override
    public int compareTo(@NotNull Vector3f o)
    {
        if(x < o.x)
            return -1;
        else if(x > o.x)
            return 1;
        else if(y < o.y)
            return -1;
        else if(y > o.y)
            return 1;
        else if (z < o.z)
            return -1;
        else if (z > o.z)
            return 1;
        else
            return 0;
    }
}
