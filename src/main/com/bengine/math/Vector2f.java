/**
 * Created by Ben on 8/26/15.
 *
 * 2-component, floating-point vector
 */

package com.bengine.math;

import org.jetbrains.annotations.NotNull;

public class Vector2f implements Comparable<Vector2f>
{
    public float x;
    public float y;

    public Vector2f()
    {
        this(0.0f, 0.0f);
    }

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float magnitude()
    {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f right)
    {
        return x * right.x +
               y * right.y;
    }

    public void normalize()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        x /= mag;
        y /= mag;
    }

    public Vector2f normalized()
    {
        float mag = magnitude();
        if(mag == 0.0f)
            throw new IllegalStateException("Cannot normalize a zero-vector.");
        return new Vector2f(
                x / mag,
                y / mag
        );
    }

    public Vector2f add(Vector2f right)
    {
        return new Vector2f(
                x + right.x,
                y + right.y
        );
    }

    public Vector2f add(float right)
    {
        return new Vector2f(
                x + right,
                y + right
        );
    }

    public Vector2f sub(Vector2f right)
    {
        return new Vector2f(
                x - right.x,
                y - right.y
        );
    }

    public Vector2f sub(float right)
    {
        return new Vector2f(
                x - right,
                y - right
        );
    }

    public Vector2f mul(Vector2f right)
    {
        return new Vector2f(
                x * right.x,
                y * right.y
        );
    }

    public Vector2f mul(float right)
    {
        return new Vector2f(
                x * right,
                y * right
        );
    }

    public Vector2f div(Vector2f right)
    {
        if(right.x == 0.0f || right.y == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector2f(
                x / right.x,
                y / right.y
        );
    }

    public Vector2f div(float right)
    {
        if(right == 0.0f)
            throw new IllegalArgumentException("Divide by zero.");
        return new Vector2f(
                x / right,
                y / right
        );
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Vector2f))
            return false;
        Vector2f other = (Vector2f)o;
        return x == other.x &&
               y == other.y;
    }

    public String toString()
    {
        return "{" + x + ", " + y + "}";
    }

    @Override
    public int compareTo(@NotNull Vector2f o)
    {
        if(x < o.x)
            return -1;
        else if(x > o.x)
            return 1;
        else if(y < o.y)
            return -1;
        else if(y > o.y)
            return 1;
        else
            return 0;
    }
}
