/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import java.util.ArrayList;
import java.util.List;
import robotrace.Vector;
import robotrace.body.pShape;

/**
 *
 * @author lyuat
 */
public class RobotSkeleton {

    public List<SkeletonPart> bodyComposition;
    SkeletonPart head;
    SkeletonPart bodyCore;
    SkeletonPart lArm;
    SkeletonPart rArm;
    SkeletonPart lLeg;
    SkeletonPart rLeg;
    Vector oPos;

    public RobotSkeleton(Vector oPos) {
        this.bodyComposition = new ArrayList<SkeletonPart>();
        this.oPos=oPos;
        this.initSkeleton();
      
    }

    public void initSkeleton() {
       
          this.head = new SkeletonHead(pShape.Cube,oPos);
          bodyComposition.add(this.head);
          this.bodyCore = new SkeletonHead(pShape.Cube, oPos);
          bodyComposition.add(this.bodyCore);
       
       
    }

}
