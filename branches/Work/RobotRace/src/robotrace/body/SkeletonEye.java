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
              for (Shape shape : this.partShape.getShapeCollection()) {
         
 
         //  shape.setToBeAnimated(true);
           //shape.setAngleOfRotation(time*50);
          
        }
    }

}