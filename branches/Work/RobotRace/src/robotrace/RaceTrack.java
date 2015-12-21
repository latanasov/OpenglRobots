package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import java.lang.reflect.Array;
import static javax.media.opengl.GL.GL_LINE_LOOP;
import static javax.media.opengl.GL.GL_POINTS;
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
    private Vector[] lanesStatPoints = new Vector[4];

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
            
            Vector upperNormal = new Vector(0,0,0);
            Vector lowerNormal = new Vector(0,0,0);
            Vector innerNormal = new Vector(0,0,0);
            Vector outerNormal = new Vector(0,0,0);

            int lane = 1;
            
            // calculate upper parameters
            upperNormal.z = 1;
            
            // calculate lower parameters
            lowerNormal.z = -1;
            
            // calculate inner normal
           
            // calculate outer normal
       
            // lane 1
            gl.glColor3f(0f, 0f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
             
            // lane 2
            lane = 2;
            gl.glColor3f(1f, 0f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
                        
            // lane 3
            lane = 3;
            gl.glColor3f(1f, 1f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
           
            // lane 4
            lane = 4;
            gl.glColor3f(1f, 0f, 1f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
            
            gl.glColor3f(1f, 0.5f, 1f);
            drawInsideOutsideSurface(gl,innerNormal,true);
            gl.glColor3f(0.5f, 0.5f, 1f);
            drawInsideOutsideSurface(gl,outerNormal,false);
                  
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
            Vector point1 = new Vector(0,0,0);
            Vector point2 = new Vector(0,0,0);
            Vector point3 = new Vector(0,0,1);
            Vector tagent = this.getTangent(t);
            
            point1 = this.getPoint(t,true);
            point2 = this.getTangent(t).cross(point3);
            
            double a = point2.normalized().x*laneWidth*0.5;
            double b = point2.normalized().x*laneWidth*(lane-1)*1;
        
            point3.x = point1.x + point2.normalized().x*laneWidth*0.5 + point2.normalized().x*laneWidth*(lane-1)*1;
            point3.y = point1.y - 3*(lane-1) + point2.normalized().y*laneWidth*lane*0.5+ point2.normalized().y*laneWidth*(lane-1)*1;
            point3.z = point1.z + point2.normalized().z*laneWidth*lane*0.5;
            
  
            //point2 = calcuateReflectionPoint(tagent, point1, lane);
            
            /*if (t == 0)
            {*/
                /*point3.x = point1.x + laneWidth*(lane-1) + (point2.x - point1.x + laneWidth*(lane-1) )/2;
                point3.y = point1.y + laneWidth*(lane-1) + (point2.y - point1.y)/2;
                point3.z = point1.z;*/
            /*}
            else
            {
            point3.x = point1.x + laneWidth*(lane-1) + (point2.x - point1.x)/2;
            point3.y = point1.y + laneWidth*(lane-1) + (point2.y - point1.y)/2;
           // point3.z = point1.z + (point2.z - point1.z)/2;
            point3.z = point1.z;
            }*/
            return point3; // <- code goes here
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
            Vector tagentVector  = new Vector(0,0,0);
            tagentVector = this.getTangent(t);
            return tagentVector; 
        } else {
            return Vector.O; // <- code goes here
        }
    }

    /**
     * Returns a point on the test track at 0 <= t < 1.
     */
    private Vector getPoint(double t, boolean upper) {   
        Vector points = this.getDefaultPos(t,upper);
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
    
    private Vector getDefaultPos(double t, boolean upper)
    {
        double x,y,z;
        if (upper)
        {    
           x = 10*Math.cos(2*Math.PI*t);
           y = 14*Math.sin(2*Math.PI*t);
           z = 1;
        }   
        else
        {
           x = 10*Math.cos(2*Math.PI*t);
           y = 14*Math.sin(2*Math.PI*t);
           z = -1;    
        }   
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    

    
    private void drawUpperLowerSurface(GL2 gl,Vector normal, boolean upper, int lane )
    {
        int i,j;
  
        double NU = 50;
        double NV = 50;
        double du = 1/NU;
        double dv = 1/NV;
        double number = 0;
        
        Vector point1 = new Vector(0,0,0);
        Vector point2 = new Vector(0,0,0);
        Vector point3 = new Vector(0,0,0);
        Vector point4 = new Vector(0,0,0);
        Vector basePoint1 = new Vector(0,0,0);
        Vector basePoint2 = new Vector(0,0,0);
        
        Vector tagent1 = new Vector(0,0,0);
        Vector tagent2 = new Vector(0,0,0);
        
        
                
        for (i = 0; i< NU; i++)
        {
            number = number + i*du;
            
            gl.glBegin(GL_TRIANGLES);  
            for (j=0; j< NV; j++)
            {
                if (lane ==1 )
                {
                    tagent1 = this.getTangent(number+ dv*j);
                    tagent2 = this.getTangent(number + dv*(j+1));
                    point1 = this.getPoint(number+ dv*j,upper);
                    point2 = this.getPoint(number + dv*(j+1),upper);
                    point3 = this.calcuateReflectionPoint(tagent1,point1,lane);
                    point4 = this.calcuateReflectionPoint(tagent2,point2,lane);
                    drawTriangles(gl, normal,point1,point2, point3,point4);
                }
                else
                {
                    basePoint1 = this.getPoint(number+ dv*j,upper);
                    basePoint2 = this.getPoint(number + dv*(j+1),upper);
                    tagent1 = this.getTangent(number+ dv*j);
                    tagent2 = this.getTangent(number + dv*(j+1));
                    point1 = this.calcuateReflectionPoint(tagent1,basePoint1,lane-1);
                    point2 = this.calcuateReflectionPoint(tagent2,basePoint2,lane-1);
                    point3 = this.calcuateReflectionPoint(tagent1,basePoint1,lane);
                    point4 = this.calcuateReflectionPoint(tagent2,basePoint2,lane);
                    drawTriangles(gl, normal,point1,point2, point3,point4);    
                }                                  
            }
  
            gl.glEnd(); 
        }
    }
    
    private void drawTriangles (GL2 gl, Vector normal,Vector point1,Vector point2,Vector point3,Vector point4)
    {
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point1.x, (float)point1.y, (float)point1.z);
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point2.x, (float)point2.y, (float)point2.z);
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point3.x, (float)point3.y, (float)point3.z);
                gl.glNormal3d(normal.x,normal.y,normal.z);
      
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point3.x, (float)point3.y, (float)point3.z);
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point2.x, (float)point2.y, (float)point2.z);
                gl.glNormal3d(normal.x,normal.y,normal.z);
                gl.glVertex3f((float)point4.x, (float)point4.y, (float)point4.z);
    }
    
     private void drawInsideOutsideSurface(GL2 gl,Vector normal, boolean inner)
    {
       int i,j;
  
        double NU = 50;
        double NV = 50;
        double du = 1/NU;
        double dv = 1/NV;
        double number = 0;
        
        Vector point1 = new Vector(0,0,0);
        Vector point2 = new Vector(0,0,0);
        Vector point3 = new Vector(0,0,0);
        Vector point4 = new Vector(0,0,0);
        Vector basePoint1 = new Vector(0,0,0);
        Vector basePoint2 = new Vector(0,0,0);
        Vector basePoint3 = new Vector(0,0,0);
        Vector basePoint4 = new Vector(0,0,0);
        
        Vector tagent1 = new Vector(0,0,0);
        Vector tagent2 = new Vector(0,0,0);
                
        for (i = 0; i< NU; i++)
        {
            number = number + i*du;
            
            gl.glBegin(GL_TRIANGLES);  
            for (j=0; j< NV; j++)
            {
                if (inner)
                {    
                   point1 = this.getDefaultPos1(number+ dv*j);
                   point2 = this.getDefaultPos1(number + dv*(j+1));
                   point3 = this.getDefaultPos2(number+ dv*j);
                   point4 = this.getDefaultPos2(number + dv*(j+1));
                   drawTriangles(gl, normal,point1,point2, point3,point4);  
                }
                else
                {
                   basePoint1 = this.getDefaultPos1(number+ dv*j);
                   basePoint2 = this.getDefaultPos1(number + dv*(j+1));
                   basePoint3 = this.getDefaultPos2(number+ dv*j);
                   basePoint4 = this.getDefaultPos2(number + dv*(j+1));
                   tagent1 = this.getTangent(number+ dv*j);
                   tagent2 = this.getTangent(number + dv*(j+1));
                  
                   point1 = this.calcuateReflectionPoint(tagent1,basePoint1,4);
                   point2 = this.calcuateReflectionPoint(tagent2,basePoint2,4);
                   point3 = this.calcuateReflectionPoint(tagent1,basePoint3,4);
                   point4 = this.calcuateReflectionPoint(tagent2,basePoint4,4);
                   drawTriangles(gl, normal,point1,point2, point3,point4);
                }                                      
            }
  
            gl.glEnd(); 
        }     
    }
     
     private Vector getDefaultPos1(double t)
    {

           double x = 10*Math.cos(2*Math.PI*t);
           double y = 14*Math.sin(2*Math.PI*t);
           double z = 1;    

        
        Vector points = new Vector(x,y,z);
        return points; 
    }
     
          private Vector getDefaultPos2(double t)
    {

           double x = 10*Math.cos(2*Math.PI*t);
           double y = 14*Math.sin(2*Math.PI*t);
           double z = -1;    

        
        Vector points = new Vector(x,y,z);
        return points; 
    }
          
    private Vector calcuateReflectionPoint(Vector tangent, Vector startPos, int lane)
    {
        Vector P2 = new Vector(0,0,0); 
        Vector P4 = new Vector(0,0,1);
        
        P2 = tangent.cross(P4);
        
        double x = startPos.x + P2.normalized().x*laneWidth*lane;
        double y = startPos.y + P2.normalized().y*laneWidth*lane;
        double z = startPos.z + P2.normalized().z*laneWidth*lane;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
}


