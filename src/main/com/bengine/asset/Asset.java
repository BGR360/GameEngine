/**
 * Created by Ben on 8/28/15.
 *
 * The base class for all types of assets to
 * be used in a typical game (textures, meshes,
 * audio clips, etc.).
 */

package com.bengine.asset;

public abstract class Asset
{
    public abstract void load();
    public abstract void release();
}
