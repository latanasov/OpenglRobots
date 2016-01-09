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

            // Vector newPost=new Vector (shape.getShapePos().x()+time*0.0025,shape.getShapePos().y(),shape.getShapePos().z());
            //shape.setShapePos(newPost);
            shape.setToBeAnimated(true);
            float angle = shape.getAngleOfRotation();
            if ((angle >= -75) && (goBack == false)) {
                 angle=angle-5;
                 if (angle==-75)
                 {
                     goBack=true;
                 }
 
  
            }
             if ((angle <= 55) && (goBack == true)) {
                 if (angle==55) {
                     goBack=false;
                 }
                    angle=angle+5;
            }

            shape.setAngleOfRotation(angle);
        }

    }

}
