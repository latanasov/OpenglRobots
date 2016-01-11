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
     * TODO provide own implementation of animation *
     */
    @Override
    public void Animate(float time) {
        
        for (Shape shape : this.partShape.getShapeCollection()) {
         
          Vector newPost=new Vector (shape.getShapePos().x()+time*0.0025,shape.getShapePos().y(),shape.getShapePos().z());
            
           shape.setShapePos(newPost);
          
        }
    }

}
