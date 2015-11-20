/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.Vector;

/**
 *
 * @author lyuat
 */
public class SkeletonArm extends SkeletonPart {

    final Vector partOffset = new Vector(0, 0, 0);

    public SkeletonArm(pShape partShape, Vector oPos) {
        super(partShape, oPos);
        this.partPos = this.oPos.add(this.partOffset);

    }

    @Override
    public void calcInitPos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
