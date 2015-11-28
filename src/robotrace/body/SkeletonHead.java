/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import robotrace.shape.Shape;
import javafx.geometry.Point3D;
import robotrace.Vector;

/**
 *
 * @author lyuat
 */
/**
 * Class representation of head*
 */
public class SkeletonHead extends SkeletonPart {

    public SkeletonHead(Shape partShape) {
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
