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
        return 0.0f;
    }

    public float dot(Vector2f right)
    {
        return 0.0f;
    }

    public void normalize()
    {

    }

    public Vector2f normalized()
    {
        return new Vector2f();
    }

    public Vector2f add(Vector2f right)
    {
        return new Vector2f();
    }

    public Vector2f add(float right)
    {
        return new Vector2f();
    }

    public Vector2f sub(Vector2f right)
    {
        return new Vector2f();
    }

    public Vector2f sub(float right)
    {
        return new Vector2f();
    }

    public Vector2f mul(Vector2f right)
    {
        return new Vector2f();
    }

    public Vector2f mul(float right)
    {
        return new Vector2f();
    }

    public Vector2f div(Vector2f right)
    {
        return new Vector2f();
    }

    public Vector2f div(float right)
    {
        return new Vector2f();
    }

    public boolean equals(Object o)
    {
        return false;
    }

    public String toString()
    {
        return "";
    }

    @Override
    public int compareTo(@NotNull Vector2f o)
    {
        return 0;
    }
}
