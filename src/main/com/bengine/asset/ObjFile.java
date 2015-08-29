/**
 * Created by Ben on 8/28/15.
 *
 * Represents a .obj file used for storing
 * 3-dimensional mesh data such as vertices,
 * vertex normals, and texture coordinates.
 * Acts as an intermediary between a .obj
 * file and a Mesh.
 */

package com.bengine.asset;

import com.bengine.math.Vector2f;
import com.bengine.math.Vector3f;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ObjFile extends Asset
{
    private String mFilePath;

    /*
    A .obj file lists vertices as "v",
    texture coordinates as "vt", and
    vertex normals as "vn". So we have
    an ArrayList for each of these.
     */
    private ArrayList<Vector3f> m_v_list;   // Vertex positions
    private ArrayList<Vector2f> m_vt_list;  // Texture coordinates
    private ArrayList<Vector3f> m_vn_list;  // Vertex normals

    /*
    Once the all the "v", "vt", and "vn"
    fields of the .obj file have been read,
    they get organized into a list of unique
    triplets based on the "f" fields of the
    .obj file.
     */
    private ArrayList<ObjVertex> mObjVertices;  // A list of UNIQUE vertices

    public ObjFile(@NotNull String filePath)
    {
        mFilePath = filePath;
    }

    @Override
    public void load()
    {

    }

    @Override
    public void release()
    {
        m_v_list.clear();
        m_vt_list.clear();
        m_vn_list.clear();
        mObjVertices.clear();
    }

    /**
     * Represents a complete vertex by storing
     * the index of the vertex's position,
     * texture coordinate, and normal.
     * For example, an ObjVertex with
     * v_index = 1, vt_index = 0, and vn_index = 3
     * would represent a vertex that has
     * position = m_v_list.get(1),
     * texCoord = m_vt_list.get(0), and
     * normal = m_vn_list.get(3)
     */
    public static class ObjVertex
    {
        public int v_index = -1;
        public int vt_index = -1;
        public int vn_index = -1;
    }
}
