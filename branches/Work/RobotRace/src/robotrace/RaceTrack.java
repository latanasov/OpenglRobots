package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static javax.media.opengl.GL.GL_TRIANGLES;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_QUAD_STRIP;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import javax.media.opengl.glu.GLU;

/**
 * Implementation of a race track that is made from Bezier segments.
 */
class RaceTrack {
    
    /** The width of one lane. The total width of the track is 4 * laneWidth. */
    private final static float laneWidth = 1.22f;

    /** Array with 3N control points, where N is the number of segments. */
    private Vector[] controlPoints = null;
    
    /**
     * Constructor for the default track.
     */
    public RaceTrack() {
    }
    
    /**
     * Constructor for a spline track.
     */
    public RaceTrack(Vector[] controlPoints) {
        this.controlPoints = controlPoints;
    }

    /**
     * Draws this track, based on the control points.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        if (null == controlPoints) {
            // draw the test track
            int NU = 12;
            int NV = 12;
            int i,j;
            
            int maxdu = 1;
            int mindu = 0;
            int maxdv = 1;
            int mindv = 0;
            
            float du = (maxdu - mindu)/NU;
            float dv = (maxdv - mindv)/NV;
            
            for (j=0; j< NV; j++)
            {
                gl.glBegin(GL_TRIANGLES);
                for (i = 0; i<= NU; i++)
                    
                {
                    Vector point=this.getPoint(mindu+i*du);
                    
                    Vector direction =this.getTangent(i);
                    Vector direction2 =this.getTangent(i+1);
                    //gl.glVertex3f((float)point.x, (float)point.y, (float)point.z);
                   // gl.glVertex3f((float)point.x+j, (float)point.y+j, (float)point.z);
                     gl.glVertex3f(i,1,5);
                     gl.glVertex3f(j,4,3);
  
              
                }
             gl.glEnd();
            // gl.glBegin(GL_QUAD_STRIP);

 
                    //gl.glVertex3f((float)point.x, (float)point.y, (float)point.z);
                   // gl.glVertex3f((float)point.x+j, (float)point.y+j, (float)point.z);
                    //gl.glVertex3f(0,0,0);
                   
  
             //  gl.glEnd();
            
            }
            
        } else {
            // draw the spline track
        }
    }
    
    /**
     * Returns the center of a lane at 0 <= t < 1.
     * Use this method to find the position of a robot on the track.
     */
    public Vector getLanePoint(int lane, double t) {
        if (null == controlPoints) {
            return Vector.O; // <- code goes here
        } else {
            return Vector.O; // <- code goes here
        }
    }
    
    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    public Vector getLaneTangent(int lane, double t) {
        if (null == controlPoints) {
            return Vector.O; // <- code goes here
        } else {
            return Vector.O; // <- code goes here
        }
    }

    /**
     * Returns a point on the test track at 0 <= t < 1.
     */
    private Vector getPoint(double t) {   
        Vector points = this.getDefaultPos(t);
        return points; 
    }

    /**
     * Returns a tangent on the test track at 0 <= t < 1.
     */
    private Vector getTangent(double t) {
        double pi = Math.PI;
        double x = -20*pi*Math.sin(2*pi*t);
        double y = 28*pi*Math.cos(2*pi*t);
        double z = 0;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    /**
     * Returns a point on a bezier segment with control points
     * P0, P1, P2, P3 at 0 <= t < 1.
     */
    private Vector getCubicBezierPoint(double t, Vector P0, Vector P1,
                                                 Vector P2, Vector P3) {
        return Vector.O; // <- code goes here
    }
    
    /**
     * Returns a tangent on a bezier segment with control points
     * P0, P1, P2, P3 at 0 <= t < 1.
     */
    private Vector getCubicBezierTangent(double t, Vector P0, Vector P1,
                                                   Vector P2, Vector P3) {
        return Vector.O; // <- code goes here
    }
    
    private Vector getDefaultPos(double t)
    {
        double x = 10*Math.cos(2*Math.PI*t);
        double y = 14*Math.sin(2*Math.PI*t);
        double z = 1;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
}
