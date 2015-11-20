package robotrace;

import robotrace.body.RobotSkeleton;
import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

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
        
        
         // Translated, rotated, scaled box.
       for (int i =0;i < this.Skeleton.bodyComposition.size();i++)
       {
        gl.glPushMatrix();
           /*
        switch (this.Skeleton.bodyComposition.get(i).partShape) {
            case 1
                     break;
            case 2:  
                     break;
            case 3:  
                     break;
           }
            */
           /**Example of drawing cube** to be added within the switch statement**/
            gl.glTranslatef(i, 0, 0);
            glut.glutWireCube(1);
       
            gl.glPopMatrix();
           
        
             
       }
       
    }
}
