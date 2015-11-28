/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.shape.Shape;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import robotrace.Base;
import robotrace.Vector;

/**
 *
 * @author lyuat
 */
/**
 * Abstract class for a skeleton part, serves as the "mother" class from which
 * all body parts inherit their properties and provide implementation for the
 * methods
 *
 * @author lyuat
 */
public abstract class SkeletonPart {

    public Shape partShape;

    SkeletonPart(Shape partShape) {
        this.partShape = partShape;

    }

    public abstract void Animate(Vector newPos);
}
