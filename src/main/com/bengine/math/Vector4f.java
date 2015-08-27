/**
 * Created by Ben on 8/26/15.
 *
 * 4-component, floating-point vector
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

public class Vector4f implements Comparable<Vector4f>
{
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f()
    {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Vector4f(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(Vector3f vec3, float w)
    {
        this(vec3.x, vec3.y, vec3.z, w);
    }

    public Vector4f(Vector3f vec3)
    {
        this(vec3, 1.0f);
    }

    public void set(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float magnitude()
    {
        return (float)Math.sqrt(
                x * x +
                y * y +
                z * z +
                w * w
        );
    }

    public float dot(Vector4f right)
    {
        return x * right.x +
               y * right.y +
               z * right.z +
               w * right.w;
    }

    public void normalize()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        x /= mag;
        y /= mag;
        z /= mag;
        w /= mag;
    }

    public Vector4f normalized()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        return new Vector4f(
                x / mag,
                y / mag,
                z / mag,
                w / mag
        );
    }

    public Vector4f add(Vector4f right)
    {
        return new Vector4f(
                x + right.x,
                y + right.y,
                z + right.z,
                w + right.w
        );
    }

    public Vector4f add(float right)
    {
        return new Vector4f(
                x + right,
                y + right,
                y + right,
                w + right
        );
    }

    public Vector4f sub(Vector4f right)
    {
        return new Vector4f(
                x - right.x,
                y - right.y,
                z - right.z,
                w - right.w
        );
    }

    public Vector4f sub(float right)
    {
        return new Vector4f(
                x - right,
                y - right,
                z - right,
                w - right
        );
    }

    public Vector4f mul(Vector4f right)
    {
        return new Vector4f(
                x * right.x,
                y * right.y,
                z * right.z,
                w * right.w
        );
    }

    public Vector4f mul(float right)
    {
        return new Vector4f(
                x * right,
                y * right,
                z * right,
                w * right
        );
    }

    public Vector4f div(Vector4f right)
    {
        if(right.x == 0.0f ||
           right.y == 0.0f ||
           right.z == 0.0f ||
           right.w == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector4f(
                x / right.x,
                y / right.y,
                z / right.z,
                w / right.w
        );
    }

    public Vector4f div(float right)
    {
        if(right == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector4f(
                x / right,
                y / right,
                z / right,
                w / right
        );
    }

    public Vector4f copy()
    {
        return new Vector4f(x, y, z, w);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Vector4f))
            return false;
        Vector4f other = (Vector4f)o;
        return x == other.x &&
               y == other.y &&
               z == other.z &&
               w == other.w;
    }

    public String toString()
    {
        return "{" + x + ", " + y + ", " + z + ", " + w + "}";
    }

    @Override
    public int compareTo(@NotNull Vector4f o)
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
        else if (w < o.w)
            return -1;
        else if (w > o.w)
            return 1;
        else
            return 0;
    }
}
