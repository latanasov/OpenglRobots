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
            boolean goBack = false;

    /**
     * TODO provide own implementation of animation *
     */
    @Override
    public void Animate(float time) {

        for (Shape shape : this.partShape.getShapeCollection()) {

            double angle =Math.sin(time*4)*45;
            shape.setAngleOfRotation((float) angle);
            shape.setToBeAnimated(true);
    }

}

}