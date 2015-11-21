/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.body;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import robotrace.Vector;
import robotrace.shape.Shape;
import robotrace.shape.ShapeEnum;
import static robotrace.shape.ShapeEnum.ComplexShape;

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
      /***Build Head***/
      Vector headPosition= new Vector (0,0,1.2);
      Shape headShape= new Shape(ShapeEnum.Sphere,Color.YELLOW,headPosition);
      headShape.radius=1;
      SkeletonHead robotHead=new SkeletonHead(headShape);
      bodyComposition.add(robotHead);
      
      
      /***Build EYE***/
      Vector eye1Position= new Vector (0.8,0,1.2);
      Shape Eye= new Shape(ShapeEnum.ComplexShape,Color.YELLOW,headPosition);
      
      Shape eyep1= new Shape(ShapeEnum.Sphere,Color.CYAN,eye1Position);
      eyep1.radius=0.5;
      
      Vector eye2Position= new Vector (1.2,0.15,1.3);
      Shape eyep2= new Shape(ShapeEnum.Sphere,Color.BLUE,eye2Position);
      eyep2.radius=0.2;
     
      Eye.ShapeCollection.add(eyep1);
      Eye.ShapeCollection.add(eyep2);

      SkeletonEye robotEye=new SkeletonEye(Eye);
      bodyComposition.add(robotEye);
      
      /***Build Body***/
      Vector bodyShap1Position= new Vector (0,0,0);
      Shape robotBody= new Shape(ShapeEnum.ComplexShape,Color.YELLOW,bodyShap1Position);
      
      Shape bodyShape1= new Shape(ShapeEnum.Cyclinder,Color.YELLOW,bodyShap1Position);
      bodyShape1.radius=1;
      bodyShape1.height=1.1;
      
      Vector bodyShap2Position= new Vector (0,0,-0.15);
      Shape bodyShape2= new Shape(ShapeEnum.Sphere,Color.BLUE,bodyShap2Position);
      bodyShape2.radius=1;
      Vector scaler= new Vector (1,1,0.8);
      bodyShape2.setScale(scaler);
      bodyShape2.toBeScaled=true;
     
      robotBody.ShapeCollection.add(bodyShape1);
      robotBody.ShapeCollection.add(bodyShape2);

      SkeletonBodyCore robotBodyCore=new SkeletonBodyCore(robotBody);
      bodyComposition.add(robotBodyCore);
       
       
     /***Build leftLeg***/
       Vector legpart1Position= new Vector (0,-0.5,-1.16);
      Shape leftLeg= new Shape(ShapeEnum.ComplexShape,Color.BLUE,legpart1Position);
      Shape leftLegp1= new Shape(ShapeEnum.Cyclinder,Color.BLUE,legpart1Position);
       leftLegp1.radius=0.17;
      leftLegp1.height=2;

      Vector legpart2Position= new Vector (0,-0.5,-1.5);
      Shape leftLegp2= new Shape(ShapeEnum.Cyclinder,Color.BLACK,legpart2Position);
      leftLegp2.radius=0.17;
      leftLegp2.height=0.34;
      
      Vector legpart3Position= new Vector (0.17,-0.5,-1.35);
      Shape leftLegp3= new Shape(ShapeEnum.Sphere,Color.BLACK,legpart3Position);
      Vector scaler2= new Vector (1.5,1,1);
      leftLegp3.setScale(scaler2);
      leftLegp3.toBeScaled=true;
      leftLegp3.radius=0.17;
      
     
      leftLeg.ShapeCollection.add(leftLegp1);
      leftLeg.ShapeCollection.add(leftLegp2);
      leftLeg.ShapeCollection.add(leftLegp3);
      
      SkeletonLeg lLeg=new SkeletonLeg(leftLeg);
      bodyComposition.add(lLeg);
       

      /***Build rightLeg***/
       Vector rlegpart1Position= new Vector (0,0.5,-1.16);
      Shape rleftLeg= new Shape(ShapeEnum.ComplexShape,Color.BLUE,rlegpart1Position);
      Shape rleftLegp1= new Shape(ShapeEnum.Cyclinder,Color.BLUE,rlegpart1Position);
       rleftLegp1.radius=0.17;
      rleftLegp1.height=2;

      Vector rlegpart2Position= new Vector (0,0.5,-1.5);
      Shape rleftLegp2= new Shape(ShapeEnum.Cyclinder,Color.BLACK,rlegpart2Position);
      rleftLegp2.radius=0.17;
      rleftLegp2.height=0.34;
      
      Vector rlegpart3Position= new Vector (0.17,0.5,-1.35);
      Shape rleftLegp3= new Shape(ShapeEnum.Sphere,Color.BLACK,rlegpart3Position);
      Vector rscaler2= new Vector (1.5,1,1);
      rleftLegp3.setScale(scaler2);
      rleftLegp3.toBeScaled=true;
      rleftLegp3.radius=0.17;
      
     
      rleftLeg.ShapeCollection.add(rleftLegp1);
      rleftLeg.ShapeCollection.add(rleftLegp2);
      rleftLeg.ShapeCollection.add(rleftLegp3);
      SkeletonLeg rLeg=new SkeletonLeg(rleftLeg);
      bodyComposition.add(rLeg);
      
      // right arm
      Vector rArm1Position= new Vector (0,1.1,0.3);
      Vector rArm2Position= new Vector (0,1.1,-0.8);
      
      Shape rArm= new Shape(ShapeEnum.ComplexShape,Color.BLUE,rArm1Position);
      Shape rArm1= new Shape(ShapeEnum.Sphere,Color.GREEN,rArm1Position);
      Shape rArm2= new Shape(ShapeEnum.Cyclinder,Color.GREEN,rArm2Position);
           
      rArm1.radius=0.1; 
      rArm2.radius=0.1;
      rArm2.height=1.1;      
     
      rArm.ShapeCollection.add(rArm1);
      rArm.ShapeCollection.add(rArm2);
   
      SkeletonArm robotArm=new SkeletonArm(rArm);
      bodyComposition.add(robotArm);
      
      // left arm
      Vector lArm1Position= new Vector (0,-1.1,0.3);
      Vector lArm2Position= new Vector (0,-1.1,-0.8);
      
      Shape lArm= new Shape(ShapeEnum.ComplexShape,Color.BLUE,lArm1Position);
      Shape lArm1= new Shape(ShapeEnum.Sphere,Color.GREEN,lArm1Position);
      Shape lArm2= new Shape(ShapeEnum.Cyclinder,Color.GREEN,lArm2Position);
           
      lArm1.radius=0.1; 
      lArm2.radius=0.1;
      lArm2.height=1.1;      
     
      lArm.ShapeCollection.add(lArm1);
      lArm.ShapeCollection.add(lArm2);
   
      SkeletonArm robotlArm=new SkeletonArm(lArm);
      bodyComposition.add(robotlArm);
       
    }

}
