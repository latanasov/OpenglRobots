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

/**
 *
 * @author lyuat
 */
public class RobotSkeleton {

    /**
     * List containing all robot parts*
     */
    public List<SkeletonPart> bodyComposition;
    /**
     * Head part*
     */
    public SkeletonPart head;
    /**
     * body core part*
     */
    public SkeletonPart bodyCore;
    /**
     * left arm part*
     */
    public SkeletonPart lArm;
    /**
     * right arm part*
     */
    public SkeletonPart rArm;
    /**
     * left leg part*
     */
    public SkeletonPart lLeg;
    /**
     * right leg part*
     */
    public SkeletonPart rLeg;
    /**
     * eye body part*
     */
    SkeletonPart lEye;
    /**
     * origin of the robot (the center of the body-core is considered as origin*
     */
    Vector oPos;

    int a;

    /**
     * Robot Constructor*
     */
    public RobotSkeleton(Vector oPos) {
        /**
         * Initialize a new list*
         */
        this.bodyComposition = new ArrayList<SkeletonPart>();
        /**
         * Assign value to the origin position*
         */
        this.oPos = oPos;

        this.initSkeleton();

    }

    /**
     * The method is used to initialize (construct) the body skeleton
     * composition The following sequence is used 1.Determine the position of
     * the body part 2.Create object(s) for the shape(s) the part consists of
     * 3.Create a new object for the body part and add it to the list
     * bodyComposition
     *
     */
    public void initSkeleton() {
        /**
         * Initialise all body parts*
         */
        initHead();
        initEye();
        initBody();
        initLeg();
        initArm();

    }

    /**
     * Head is made of a single shape on a relative position to the body
     */
    private void initHead() {
        /**
         * *Build Head**
         */

        Vector headPosition = new Vector(0, 0, 1.2);
        Shape headShape = new Shape(ShapeEnum.Sphere, Color.YELLOW, headPosition, this.oPos);
        headShape.setRadius(1);
        this.head = new SkeletonHead(headShape);
        bodyComposition.add(head);
    }

    /**
     * Eye is made of multiple shapes all on a relative position to the body *
     */
    private void initEye() {
        /**
         * *Build EYE**
         */
        Vector eye1Position = new Vector(0.8, 0, 1.2);
        Shape Eye = new Shape(ShapeEnum.ComplexShape, Color.YELLOW, eye1Position, this.oPos);

        Shape eyep1 = new Shape(ShapeEnum.Sphere, Color.CYAN, eye1Position, this.oPos);
        eyep1.setRadius(0.5);

        Vector eye2Position = new Vector(1.2, 0.15, 1.3);
        Shape eyep2 = new Shape(ShapeEnum.Sphere, Color.BLUE, eye2Position, this.oPos);
        eyep2.setRadius(0.2);

        Eye.getShapeCollection().add(eyep1);
        Eye.getShapeCollection().add(eyep2);

        this.lEye = new SkeletonEye(Eye);
        bodyComposition.add(lEye);
    }

    /**
     * Body is made of multiple shapes all on a relative position to the robot
     * (origin) position *
     */
    private void initBody() {
        /**
         * *Build Body**
         */
        Vector bodyShap1Position = new Vector(0, 0, 0);
        Shape robotBody = new Shape(ShapeEnum.ComplexShape, Color.YELLOW, bodyShap1Position, this.oPos);

        Shape bodyShape1 = new Shape(ShapeEnum.Cyclinder, Color.YELLOW, bodyShap1Position, this.oPos);
        bodyShape1.setRadius(1);
        bodyShape1.setHeight(1.1);

        Vector bodyShap2Position = new Vector(0, 0, -0.15);
        Shape bodyShape2 = new Shape(ShapeEnum.Sphere, Color.BLUE, bodyShap2Position, this.oPos);
        bodyShape2.setRadius(1);
        Vector scaler = new Vector(1, 1, 0.8);
        bodyShape2.setScale(scaler);
        bodyShape2.setToBeScaled(true);

        robotBody.getShapeCollection().add(bodyShape1);
        robotBody.getShapeCollection().add(bodyShape2);

        this.bodyCore = new SkeletonBodyCore(robotBody);
        bodyComposition.add(bodyCore);
    }

    /**
     * Leg is made of multiple shapes all on a relative position to the body *
     */
    private void initLeg() {
        /**
         * *Build leftLeg**
         */
        Vector legpart1Position = new Vector(0, -0.5, -1.16);
        Shape leftLeg = new Shape(ShapeEnum.ComplexShape, Color.BLUE, legpart1Position, this.oPos);
        Shape leftLegp1 = new Shape(ShapeEnum.Cyclinder, Color.BLUE, legpart1Position, this.oPos);
        leftLegp1.setRadius(0.17);
        leftLegp1.setHeight(2);

        Vector legpart2Position = new Vector(0, -0.5, -1.5);
        Shape leftLegp2 = new Shape(ShapeEnum.Cyclinder, Color.BLACK, legpart2Position, this.oPos);
        leftLegp2.setRadius(0.17);
        leftLegp2.setHeight(0.34);

        Vector legpart3Position = new Vector(0.17, -0.5, -1.35);
        Shape leftLegp3 = new Shape(ShapeEnum.Sphere, Color.BLACK, legpart3Position, this.oPos);
        Vector scaler2 = new Vector(1.5, 1, 1);
        leftLegp3.setScale(scaler2);
        leftLegp3.setToBeScaled(true);
        leftLegp3.setRadius(0.17);

        leftLeg.getShapeCollection().add(leftLegp1);
        leftLeg.getShapeCollection().add(leftLegp2);
        leftLeg.getShapeCollection().add(leftLegp3);

        this.lLeg = new SkeletonLeg(leftLeg);
        bodyComposition.add(lLeg);

        /**
         * *Build rightLeg**
         */
        Vector rlegpart1Position = new Vector(0, 0.5, -1.16);
        Shape rleftLeg = new Shape(ShapeEnum.ComplexShape, Color.BLUE, rlegpart1Position, this.oPos);
        Shape rleftLegp1 = new Shape(ShapeEnum.Cyclinder, Color.BLUE, rlegpart1Position, this.oPos);
        rleftLegp1.setRadius(0.17);
        rleftLegp1.setHeight(2);

        Vector rlegpart2Position = new Vector(0, 0.5, -1.5);
        Shape rleftLegp2 = new Shape(ShapeEnum.Cyclinder, Color.BLACK, rlegpart2Position, this.oPos);
        rleftLegp2.setRadius(0.17);
        rleftLegp2.setHeight(0.34);

        Vector rlegpart3Position = new Vector(0.17, 0.5, -1.35);
        Shape rleftLegp3 = new Shape(ShapeEnum.Sphere, Color.BLACK, rlegpart3Position, this.oPos);
        Vector rscaler2 = new Vector(1.5, 1, 1);
        rleftLegp3.setScale(scaler2);
        rleftLegp3.setToBeScaled(true);
        rleftLegp3.setRadius(0.17);

        rleftLeg.getShapeCollection().add(rleftLegp1);
        rleftLeg.getShapeCollection().add(rleftLegp2);
        rleftLeg.getShapeCollection().add(rleftLegp3);
        this.rLeg = new SkeletonLeg(rleftLeg);
        bodyComposition.add(rLeg);
    }

    /**
     * Arm is made of multiple shapes all on a relative position to the body *
     */
    private void initArm() {
        // right arm
        Vector rArm1Position = new Vector(0, 1.1, 0.3);
        Vector rArm2Position = new Vector(0, 1.1, -0.8);

        Shape srArm = new Shape(ShapeEnum.ComplexShape, Color.BLUE, rArm1Position, this.oPos);
        Shape rArm1 = new Shape(ShapeEnum.Sphere, Color.GREEN, rArm1Position, this.oPos);
        Shape rArm2 = new Shape(ShapeEnum.Cyclinder, Color.GREEN, rArm2Position, this.oPos);

        rArm1.setRadius(0.1);
        rArm2.setRadius(0.1);
        rArm2.setHeight(1.1);

        srArm.getShapeCollection().add(rArm1);
        srArm.getShapeCollection().add(rArm2);

        this.rArm = new SkeletonArm(srArm);
        bodyComposition.add(rArm);

        // left arm
        Vector lArm1Position = new Vector(0, -1.1, 0.3);
        Vector lArm2Position = new Vector(0, -1.1, -0.8);

        Shape slArm = new Shape(ShapeEnum.ComplexShape, Color.BLUE, lArm1Position, this.oPos);
        Shape lArm1 = new Shape(ShapeEnum.Sphere, Color.GREEN, lArm1Position, this.oPos);
        Shape lArm2 = new Shape(ShapeEnum.Cyclinder, Color.GREEN, lArm2Position, this.oPos);

        lArm1.setRadius(0.1);
        lArm2.setRadius(0.1);
        lArm2.setHeight(1.1);

        slArm.getShapeCollection().add(lArm1);
        slArm.getShapeCollection().add(lArm2);

        this.lArm = new SkeletonArm(slArm);
        bodyComposition.add(lArm);
    }

    private void updateRobotPos() {
        for (SkeletonPart part : this.bodyComposition) {
            part.partShape.updateShapePos();

        }

    }
}
