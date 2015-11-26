package robotrace;

import robotrace.body.RobotSkeleton;
import com.jogamp.opengl.util.gl2.GLUT;
import java.nio.FloatBuffer;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import static javax.media.opengl.GL.GL_FRONT;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
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
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    private final Material material;
    
     /** The material from which this robot is built. */
    private final RobotSkeleton Skeleton;

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
        /* add other parameters that characterize this robot */) {
        this.material = material;
        

        // code goes here ...
        this.Skeleton=new RobotSkeleton(position);
                this.Skeleton.initSkeleton();

    }

    Robot(float[] diffuse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    
    public void draw(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, FloatBuffer.wrap(this.material.ambient));
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, FloatBuffer.wrap(this.material.diffuse));
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, FloatBuffer.wrap(this.material.specular));
        gl.glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, this.material.shininess); 
        
        //gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, this.material.shininess);
        
        Point3D startPos = new Point3D(this.position.x,this.position.y,this.position.z);
               
        // Get the start point of the robot
        startPos.add(this.position.x, this.position.y, this.position.z);
         
        if (stickFigure)
        {
            
            // draw stcik robot
            Vector headPos = new Vector(0,0,0);
            Vector bodyPos = new Vector(0,0,0);
            Vector lArmPos = new Vector(0,0,0);
            Vector rArmPos = new Vector(0,0,0);
            Vector lLegPos = new Vector(0,0,0);
            Vector rLegPos = new Vector(0,0,0);
            
            //Get positions of different parts of robot
            headPos =this.Skeleton.head.partShape.shapePos;
            bodyPos = this.Skeleton.bodyCore.partShape.shapePos;
            lArmPos = this.Skeleton.lArm.partShape.shapePos;
            rArmPos = this.Skeleton.rArm.partShape.shapePos;
            lLegPos = this.Skeleton.lLeg.partShape.shapePos;
            rLegPos = this.Skeleton.rLeg.partShape.shapePos;
            
            
            // draw head, body, arm and leg
            gl.glPushMatrix();
               gl.glTranslatef((float)headPos.x, (float)headPos.y, (float)headPos.z);
               glut.glutSolidSphere(0.4, 32, 32);
            gl.glPopMatrix();

            this.drawLine(gl, headPos, bodyPos);
            this.drawLine(gl, lArmPos, rArmPos);
            this.drawLine(gl, bodyPos, lLegPos);
            this.drawLine(gl, bodyPos, rLegPos);           
        
        }
        else
        {
           // draw robot
           for (int i=0; i < this.Skeleton.bodyComposition.size(); i++)
           { 
                gl.glPushMatrix();
                   SkeletonPart tempPart;
                   tempPart=this.Skeleton.bodyComposition.get(i);
                   drawPart(gl,glut,tempPart.partShape);
                gl.glPopMatrix();
        }       
    }
    }
    
    void drawPart(GL2 gl,GLUT glut, Shape partShape){
                   
    gl.glPushMatrix();

            if (partShape.toBeRotated)
            {
                this.rotate3D(gl, this.position ,partShape.shapePos ,35);
            }
            
            if (partShape.toBeScaled)
            {
               this.scale3D(gl, (float)partShape.getScale().x, (float)partShape.getScale().y, (float)partShape.getScale().z);
            }
            
            if(partShape.ShapeType!=ComplexShape)
            {
                gl.glColor3d(partShape.getColor().getRed(),partShape.getColor().getGreen(),partShape.getColor().getBlue());
                gl.glTranslatef((float)partShape.shapePos.x,(float) partShape.shapePos.y, (float) partShape.shapePos.z); 
            }
         
            switch (partShape.ShapeType) {
               
            // draw head
            case Cube:  
             
      
          glut.glutSolidCube(1);
                     break;
            
            // draw body
            case Sphere:  
                    glut.glutSolidSphere(partShape.radius, 100, 100);
                     break;
            
            // draw arm    
            case Cyclinder:  
             glut.glutSolidCylinder(partShape.radius, partShape.height, 100, 100);
                     break;
             case ComplexShape:  
              
                  
         for (int i=0; i<partShape.ShapeCollection.size();i++) {
                drawPart(gl,glut,partShape.ShapeCollection.get(i));
                            
         }
           break; 
        }
          gl.glPopMatrix();
    }
    
    void rotate3D (GL2 gl, Vector p1, Vector p2, float thetaDegree)
    {
        float vx = (float) (p2.x - p1.x);
        float vy = (float) (p2.y - p1.y);
        float vz = (float) (p2.z - p1.z);
            
        gl.glTranslatef((float)p1.x, (float)p1.y,(float)p1.z);
        gl.glRotatef(thetaDegree, vx, vy, vz);
        gl.glTranslatef((float) -p1.x, (float) -p1.y,(float) -p1.z);
    }
       
    void scale3D (GL2 gl, float sx, float sy, float sz)
    {       
       // gl.glTranslatef((float)p1.x, (float)p1.y,(float)p1.z);
        gl.glScalef(sx, sy, sz);
    }
    
    void drawLine (GL2 gl, Vector startPos, Vector endPos)
    {
        gl.glBegin(GL_LINES);
           gl.glVertex3d(startPos.x, startPos.y, startPos.z);
           gl.glVertex3d(endPos.x, endPos.y, endPos.z);
        gl.glEnd();
    }

}
