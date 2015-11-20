package robotrace;

import robotrace.body.RobotSkeleton;
import com.jogamp.opengl.util.gl2.GLUT;
import javafx.geometry.Point3D;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import robotrace.body.SkeletonPart;
import robotrace.body.pShape;

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
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    
    public void draw(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        
        
        Point3D startPos = new Point3D(this.position.x,this.position.y,this.position.z);
        
        
        
        // Get the start point of the robot
        startPos.add(this.position.x, this.position.y, this.position.z);
        
    
        
        if (stickFigure)
        {
            // draw stcik robot
            gl.glBegin(GL_LINES);
           gl.glVertex3d(0, 0, 0);
           gl.glVertex3d(0, 1, 0);
        gl.glEnd();
        
        }
        else
        {
            // draw robot
        
        
        // code goes here ...
      
        this.Skeleton.initSkeleton();
        
        for (int i=0; i < this.Skeleton.bodyComposition.size(); i++)
        { 
             gl.glPushMatrix();
           SkeletonPart tempPart;
           tempPart=this.Skeleton.bodyComposition.get(i);
            if (tempPart.isToBeScaled)
            {
            this.rotate3D(gl, this.position ,tempPart.partPos ,35);
               //this.scale3D(gl, 2, 1, 1, tempPart.partPos);
            }
                  gl.glTranslatef((float)tempPart.partPos.x,(float) tempPart.partPos.y, (float) tempPart.partPos.z);

           switch (tempPart.partShape) {
               
            // draw head
            case Cube:  
             
      
          glut.glutSolidCube(1);
                     break;
            
            // draw body
            case Sphere:  
                    glut.glutSolidSphere(0.1, 32, 32);
                     break;
            
            // draw arm    
            case Conus:  
                     break;
            
         
        }
                   gl.glPopMatrix();

        }
/*
        // draw body
        
        //this.position.x = 0;
        //this.position.y = 0;
        //this.position.z = 0;
        
        float initPosX = (float) startPos.getX();
        float initPosY = (float) startPos.getY();
        float initPosZ = (float) startPos.getZ();
       
        //body
        gl.glPushMatrix();
            gl.glTranslatef(initPosX, initPosY, initPosZ);
            gl.glScalef(1f, 1.5f, 2f);
            glut.glutSolidCube(1);  
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef((float) (initPosX+0.5), initPosY, (float) (initPosZ+0.3));
            glut.glutSolidSphere(0.1, 32, 32);
            gl.glTranslatef(initPosX, initPosY, (float) (initPosZ-0.6));
            glut.glutSolidSphere(0.1, 32, 32);
            
          
        gl.glPopMatrix();
        
        
        //head
    
        
        gl.glPushMatrix();
            gl.glColor3f(0f, 0f, 1f);
            gl.glTranslatef(initPosX, initPosY, (float) (initPosZ+1.7));
            gl.glScalef(0.5f, 1.2f, 1f);
            glut.glutSolidCube(1);             
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef((float) (initPosX+0.25), (float) (initPosY-0.2), (float) (initPosZ+1.7));
            glut.glutSolidSphere(0.1, 32, 32);
            gl.glTranslatef(initPosX, (float) (initPosY+0.4), initPosZ);
            glut.glutSolidSphere(0.1, 32, 32);  
        gl.glPopMatrix();
       
        gl.glPushMatrix();
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef((float) (initPosX+0.25), initPosY, (float) (initPosZ+1.4));
            glut.glutSolidCone(0.1, 0.2, 32, 32);
        gl.glPopMatrix();
        }      
        
        Point3D p1 = new  Point3D(0.0,0.0,0.0);
        Point3D p2 = new  Point3D(1.0,0.0,0.0);

        float thetaDegree = 90;
        
        float vx = (float) (p2.getX() - p1.getX());
        float vy = (float) (p2.getY() - p1.getY());
        float vz = (float) (p2.getZ() - p1.getZ());
         
        gl.glPushMatrix();
            this.rotate3D(gl, p1, p2, thetaDegree);
            glut.glutSolidCylinder(0.25, 0.2, 32, 32); 
        gl.glPopMatrix();

        */
        
        
        
    }
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
       
    void scale3D (GL2 gl, float sx, float sy, float sz, Vector p1)
    {       
       // gl.glTranslatef((float)p1.x, (float)p1.y,(float)p1.z);
        gl.glScalef(sx, sy, sz);
    }

}
