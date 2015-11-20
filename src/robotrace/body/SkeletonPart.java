/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import robotrace.Base;
import robotrace.Vector;
/**
 *
 * @author lyuat
 */


public abstract class SkeletonPart  {
public pShape partShape;
public Vector partPos;
public Vector oPos;
public Vector partOffset;
public boolean isToBeScaled;
SkeletonPart(pShape partShape,Vector oPos)
{
    this.partShape=partShape;
    this.oPos=oPos;
   
}

public abstract void calcInitPos();
public abstract void setPos(Vector newPos);
    
}

