/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import javafx.geometry.Point3D;
import robotrace.Vector;

/**
 *
 * @author lyuat
 */
public class SkeletonHead extends SkeletonPart {

   

    public SkeletonHead(pShape partShape, Vector oPos) {
        super(partShape, oPos);
        this.partOffset=new Vector(0,0,1);
        this.partPos = this.oPos.add(this.partOffset);

    }

    @Override
    public void calcInitPos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
