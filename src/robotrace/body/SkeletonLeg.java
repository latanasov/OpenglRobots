/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import javafx.geometry.Point3D;
import robotrace.Vector;
import robotrace.shape.Shape;

/**
 *
 * @author lyuat
 */
/**
 * Class Representation of leg*
 */
public class SkeletonLeg extends SkeletonPart {

    final Vector partOffset = new Vector(0, 0.25, -1);

    public SkeletonLeg(Shape partShape) {
        super(partShape);

    }

    /**
     * TODO provide own implementation of animation *
     */
    @Override
    public void Animate(Vector newPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
