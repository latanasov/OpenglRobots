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
 * Class representation of Arm*
 */
public class SkeletonArm extends SkeletonPart {

    public SkeletonArm(Shape partShape) {
        super(partShape);

    }

    /**
     *
     * @param time- global time passed
     */
    @Override
    public void Animate(float time) {
        int rSpeed = 4;
        //Iterate through each shape the arm consists of
        for (Shape shape : this.partShape.getShapeCollection()) {
            //Calculate the new angle based on the time passed
            double angle = Math.sin(time * rSpeed) * 45;
            //Set the angle of rotation
            shape.setAngleOfRotation((float) angle);
            //Enable animation for the shape
            shape.setToBeAnimated(true);
        }

    }
}
