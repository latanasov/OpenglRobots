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

    boolean goBack=false;
    /**
     * TODO provide own implementation of animation *
     */
    @Override
    public void Animate(float time) {
        
       for (Shape shape : this.partShape.getShapeCollection()) {

            
            shape.setToBeAnimated(true);
            float angle = shape.getAngleOfRotation();
            if ((angle >= -55) && (goBack == false)) {
                 angle=angle-5;
                 if (angle==-55)
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
