/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.Vector;
import robotrace.shape.Shape;

/**
 *
 * @author s116525
 */
public class SkeletonJoint extends SkeletonPart {

    final Vector partOffset = new Vector(0, 2, 0.5);
    
    public SkeletonJoint(Shape partShape) {
        super(partShape);
     

    }

    @Override
    public void calcInitPos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPos(Vector newPos) {

    }
    
}
