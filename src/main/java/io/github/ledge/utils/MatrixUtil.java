package io.github.ledge.utils;

import org.lwjgl.BufferUtils;

import javax.vecmath.Matrix4f;
import java.nio.FloatBuffer;

public class MatrixUtil {

    public static FloatBuffer toFloatBuffer(Matrix4f matrix4f) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        return toFloatBuffer(matrix4f, buffer);
    }

    public static FloatBuffer toFloatBuffer(Matrix4f matrix4f, FloatBuffer buffer) {
        buffer.put(matrix4f.m00);
        buffer.put(matrix4f.m10);
        buffer.put(matrix4f.m20);
        buffer.put(matrix4f.m30);

        buffer.put(matrix4f.m01);
        buffer.put(matrix4f.m11);
        buffer.put(matrix4f.m21);
        buffer.put(matrix4f.m31);

        buffer.put(matrix4f.m02);
        buffer.put(matrix4f.m12);
        buffer.put(matrix4f.m22);
        buffer.put(matrix4f.m32);

        buffer.put(matrix4f.m03);
        buffer.put(matrix4f.m13);
        buffer.put(matrix4f.m23);
        buffer.put(matrix4f.m33);

        buffer.flip();
        return buffer;
    }
}
