/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.shape.Shape;

/**
 *
 * @author s116525
 */
/**
 * Class representation of eye*
 */
public class SkeletonEye extends SkeletonPart {

    public SkeletonEye(Shape partShape) {
        super(partShape);

    }

    /**
     * TODO provide own implementation of animation *
     */
    @Override
    public void Animate(float time) {
        int rSpeed = 10;
        //Iterate through each shape the eye consists of
        for (Shape shape : this.partShape.getShapeCollection()) {
            //Calculate the new angle based on the time passed
            double angle = Math.sin(time * rSpeed) * 5;
            //Set the angle of rotation
            shape.setAngleOfRotation((float) angle);
            //Enable animation for the shape
            shape.setToBeAnimated(true);
        }

    }

}
