/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.Vector;

/**
 *
 * @author s116525
 */
public class SkeletonEye extends SkeletonPart{

    final Vector partOffset = new Vector(0.7, 0.2, 1.5);
    
    public SkeletonEye(pShape partShape, Vector oPos) {
        super(partShape, oPos);
        this.partPos = this.oPos.add(this.partOffset);
    }

    @Override
    public void calcInitPos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPos(Vector newPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
