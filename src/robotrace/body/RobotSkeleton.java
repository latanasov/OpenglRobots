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
    public SkeletonPart head;
    public SkeletonPart bodyCore;
    public SkeletonPart lArm;
    public SkeletonPart rArm;
    public SkeletonPart lLeg;
    public SkeletonPart rLeg;
    SkeletonPart joint;
    SkeletonPart lEye;
    Vector oPos;

    public RobotSkeleton(Vector oPos) {
        this.bodyComposition = new ArrayList<SkeletonPart>();
        this.oPos=oPos;
        this.initSkeleton();
      
    }

    public void initSkeleton() {
       
          this.head = new SkeletonHead(pShape.Cube,oPos);
          bodyComposition.add(this.head);
          this.head.isToBeScaled=true;
          this.bodyCore = new SkeletonBodyCore(pShape.Cube, oPos);
          bodyComposition.add(this.bodyCore);
          
          this.lArm = new SkeletonArm(pShape.Cube, oPos);
          bodyComposition.add(this.lArm);
          this.rArm = new SkeletonArm(pShape.Cube, oPos);
          Vector newPos=new Vector (0,-1,0.5);
          this.rArm.setPos(newPos);
          bodyComposition.add(this.rArm);
          
          this.lLeg = new SkeletonLeg(pShape.Cube, oPos);
          bodyComposition.add(this.lLeg);
          this.rLeg = new SkeletonLeg(pShape.Cube, oPos);
          Vector newPosLeg =new Vector (0,-0.25,-1);
          this.rLeg.setPos(newPosLeg);
          
          this.joint = new SkeletonJoint(pShape.Sphere, oPos);
          bodyComposition.add(this.joint);
          this.lEye = new SkeletonEye(pShape.Sphere, oPos);
          bodyComposition.add(this.lEye);
       
     
    }

}
