package robotrace;

import robotrace.body.RobotSkeleton;
import com.jogamp.opengl.util.gl2.GLUT;
import java.nio.FloatBuffer;
import javafx.geometry.Point3D;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;
import javax.media.opengl.glu.GLU;
import robotrace.body.SkeletonPart;
import robotrace.shape.Shape;
import robotrace.shape.ShapeEnum;
import static robotrace.shape.ShapeEnum.ComplexShape;

/**
 * Represents a Robot, to be implemented according to the Assignments.
 */
class Robot {

    /**
     * The position of the robot.
     */
    public Vector position = new Vector(0, 0, 0);

    /**
     * The direction in which the robot is running.
     */
    public Vector direction = new Vector(1, 0, 0);

    /**
     * The material from which this robot is built.
     */
    private final Material material;

    /**
     * The robot skeleton.
     */
    private final RobotSkeleton Skeleton;
    /**
     * Robot angle of rotation
     */
    public float robotAngle;

    private boolean robotIsDrawn = false;

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
    /* add other parameters that characterize this robot */) {
        this.material = material;

        // code goes here ...
        this.Skeleton = new RobotSkeleton(this.position);
        //Initialize the robot skeleton
        this.Skeleton.initSkeleton();

    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        this.walk(tAnim);
        //Apply the matterials 
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, FloatBuffer.wrap(this.material.ambient));
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, FloatBuffer.wrap(this.material.diffuse));
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, FloatBuffer.wrap(this.material.specular));
        gl.glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, this.material.shininess);

        Point3D startPos = new Point3D(this.position.x, this.position.y, this.position.z);

        // Get the start point of the robot
        startPos.add(this.position.x, this.position.y, this.position.z);
        if (stickFigure) {

            drawStickRobot(gl, glut);

        } else {

            drawRobot(gl, glut);

        }
    }

    private void drawStickRobot(GL2 gl, GLUT glut) {
        // draw stcik robot
        Vector headPos = new Vector(0, 0, 0);
        Vector bodyPos = new Vector(0, 0, 0);
        Vector lArmPos = new Vector(0, 0, 0);
        Vector rArmPos = new Vector(0, 0, 0);
        Vector lLegPos = new Vector(0, 0, 0);
        Vector rLegPos = new Vector(0, 0, 0);

        //Get positions of different parts of robot
        headPos = this.Skeleton.head.partShape.getShapePos();
        bodyPos = this.Skeleton.bodyCore.partShape.getShapePos();
        lArmPos = this.Skeleton.lArm.partShape.getShapePos();
        rArmPos = this.Skeleton.rArm.partShape.getShapePos();
        lLegPos = this.Skeleton.lLeg.partShape.getShapePos();
        rLegPos = this.Skeleton.rLeg.partShape.getShapePos();

        // draw head, body, arm and leg
        gl.glPushMatrix();
        gl.glTranslatef((float) headPos.x, (float) headPos.y, (float) headPos.z);
        glut.glutSolidSphere(0.4, 32, 32);
        gl.glPopMatrix();

        this.drawLine(gl, headPos, bodyPos);
        this.drawLine(gl, lArmPos, rArmPos);
        this.drawLine(gl, bodyPos, lLegPos);
        this.drawLine(gl, bodyPos, rLegPos);
    }

    void drawRobot(GL2 gl, GLUT glut) {

        //Iterate through robot parts
        gl.glPushMatrix();

        gl.glTranslatef((float) (this.position.x), (float) (this.position.y), (float) (this.position.z + 0.4));
        this.robotAngle = (float) (Math.PI - Math.atan2(direction.x(), direction.y()));
        double rAngle = this.robotAngle * (180.0 / Math.PI);
        gl.glRotated(rAngle, 0, 0, 1);
        gl.glScaled(0.8, 0.8, 0.8);

        for (int i = 0; i < this.Skeleton.bodyComposition.size(); i++) {

            SkeletonPart tempPart;
            tempPart = this.Skeleton.bodyComposition.get(i);

            //Draw the part
            drawPart(gl, glut, tempPart.partShape);
        }

        gl.glPopMatrix();
    }

    public void walk(float time) {

        this.Skeleton.lLeg.Animate(time);
        this.Skeleton.rLeg.Animate(time);
        this.Skeleton.lArm.Animate(time);
        this.Skeleton.rArm.Animate(time);
    }

    void drawPart(GL2 gl, GLUT glut, Shape partShape) {

        gl.glPushMatrix();

        if (partShape.getShapeType() != ComplexShape) {
            gl.glColor3d(partShape.getColor().getRed(), partShape.getColor().getGreen(), partShape.getColor().getBlue());
            gl.glTranslatef((float) partShape.getShapePos().x, (float) partShape.getShapePos().y, (float) partShape.getShapePos().z);
        }
        //Check if the shape has to be rotated and if yes apply rotation
        if (partShape.isToBeRotated()) {
            this.rotate3D(gl, this.position, partShape.getShapePos(), partShape.getAngleOfRotation());
        }

        //Check if the shape has to be rotated and if yes apply rotation
        if (partShape.isToBeScaled()) {
            this.scale3D(gl, (float) partShape.getScale().x, (float) partShape.getScale().y, (float) partShape.getScale().z);
        }

        switch (partShape.getShapeType()) {

            //If shape is cube draw cube
            case Cube:
                glut.glutSolidCube(1);
                break;

            //If shape is sphere draw sphere
            case Sphere:
                glut.glutSolidSphere(partShape.getRadius(), 60, 60);
                break;

            //If shape is cylynder draw cylinder   
            case Cyclinder:
                glut.glutSolidCylinder(partShape.getRadius(), partShape.getHeight(), 60, 60);
                break;
            case ComplexShape:
                for (int j = 0; j < partShape.getShapeCollection().size(); j++) {
                    //Draw the part
                    drawPart(gl, glut, partShape.getShapeCollection().get(j));

                }
                break;
        }
        gl.glPopMatrix();
    }

    void rotate3D(GL2 gl, Vector p1, Vector p2, float thetaDegree
    ) {
        float vx = (float) (p2.x - p1.x);
        float vy = (float) (p2.y - p1.y);
        float vz = (float) (p2.z - p1.z);

        gl.glTranslatef((float) p1.x, (float) p1.y, (float) p1.z);
        gl.glRotatef(thetaDegree, vx, vy, vz);
        gl.glTranslatef((float) -p1.x, (float) -p1.y, (float) -p1.z);
    }

    void scale3D(GL2 gl, float sx, float sy, float sz
    ) {
        gl.glScalef(sx, sy, sz);
    }

    void drawLine(GL2 gl, Vector startPos, Vector endPos
    ) {
        gl.glBegin(GL_LINES);
        gl.glVertex3d(startPos.x, startPos.y, startPos.z);
        gl.glVertex3d(endPos.x, endPos.y, endPos.z);
        gl.glEnd();
    }

}
