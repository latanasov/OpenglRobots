package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import java.lang.reflect.Array;
import static javax.media.opengl.GL.GL_LINE_LOOP;
import static javax.media.opengl.GL.GL_LINE_STRIP;
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
            
            // initialize the normal vectors
            Vector upperNormal = new Vector(0,0,0);
            Vector lowerNormal = new Vector(0,0,0);
            Vector innerNormal = new Vector(0,0,0);
            Vector outerNormal = new Vector(0,0,0);          
            
            // calculate upper parameters
            upperNormal.z = 1;
            
            // calculate lower parameters
            lowerNormal.z = -1;
            
            // calculate inner normal
           
            // calculate outer normal
       
            // draw lane 1 up and lower surface
            int lane = 1;
            gl.glColor3f(0f, 0f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
             
            // draw lane 2 up and lower surface
            lane = 2;
            gl.glColor3f(1f, 0f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
                        
            // draw lane 3 up and lower surface
            lane = 3;
            gl.glColor3f(1f, 1f, 0f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
           
            // draw lane 4 up and lower surface
            lane = 4;
            gl.glColor3f(1f, 0f, 1f);
            drawUpperLowerSurface(gl,upperNormal,true,lane);
            drawUpperLowerSurface(gl,lowerNormal,false,lane);
            
            // draw track's inner and outer surface
            gl.glColor3f(1f, 0.5f, 1f);
            drawInsideOutsideSurface(gl,innerNormal,true);
            gl.glColor3f(0.5f, 0.5f, 1f);
            drawInsideOutsideSurface(gl,outerNormal,false);
                  
        } else {
            // draw the spline track   
            
            Vector normal = new Vector(0,0,1);
             
            Vector P0 = new Vector(0,0,0);
            Vector P1 = new Vector(0,0,0);
            Vector P2 = new Vector(0,0,0);
            Vector P3 = new Vector(0,0,0);
            Vector P00 = new Vector(0,0,0);
            Vector P11 = new Vector(0,0,0);
            Vector P22 = new Vector(0,0,0);
            Vector P33 = new Vector(0,0,0);
             
            int NU = 20;
            int NV = 20;
            //control points
            gl.glPointSize(8);
            gl.glBegin(GL_POINTS); 
            gl.glColor3f(1f, 0f, 0f); 
            for (int i =0; i< (this.controlPoints.length); i++)
            { 
                gl.glVertex3f((float)this.controlPoints[i].x,(float)this.controlPoints[i].y,(float)this.controlPoints[i].z); 
            }
            gl.glEnd(); 
            
            
            // Loop the control points
            for (int i =0; i< (this.controlPoints.length/16); i++)
            {    
            for (int u = 0; u < NU; u++)
            { 
                // according to 16 control points, calculate 32 Cubic Bezier Points for upper, lower, inner and outer surface
                for (int a=0; a<4; a++)
                {
                   if (a==0)
                   {    
                      // get control points for upper surface
                      P0 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i], this.controlPoints[i*15+i+1], this.controlPoints[i*15+i+2], this.controlPoints[i*15+i+3]);
                      P1 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+4], this.controlPoints[i*15+i+5], this.controlPoints[i*15+i+6], this.controlPoints[i*15+i+7]);
                      P2 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+8], this.controlPoints[i*15+i+9], this.controlPoints[i*15+i+10], this.controlPoints[i*15+i+11]);
                      P3 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+12], this.controlPoints[i*15+i+13], this.controlPoints[i*15+i+14], this.controlPoints[i*15+i+15]);
               
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i], this.controlPoints[i*15+i+1], this.controlPoints[i*15+i+2], this.controlPoints[i*15+i+3]);
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+4], this.controlPoints[i*15+i+5], this.controlPoints[i*15+i+6], this.controlPoints[i*15+i+7]);
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+8], this.controlPoints[i*15+i+9], this.controlPoints[i*15+i+10], this.controlPoints[i*15+i+11]);
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+12], this.controlPoints[i*15+i+13], this.controlPoints[i*15+i+14], this.controlPoints[i*15+i+15]);
                      
                      /* P0 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i], this.controlPoints[i+1], this.controlPoints[i+2], this.controlPoints[i+3]);
                      P1 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i+4], this.controlPoints[i+5], this.controlPoints[i+6], this.controlPoints[i+7]);
                      P2 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i+8], this.controlPoints[i+9], this.controlPoints[i+10], this.controlPoints[i+11]);
                      P3 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i+12], this.controlPoints[i+13], this.controlPoints[i+14], this.controlPoints[i+15]);
               
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i], this.controlPoints[i+1], this.controlPoints[i+2], this.controlPoints[i+3]);
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i+4], this.controlPoints[i+5], this.controlPoints[i+6], this.controlPoints[i+7]);
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i+8], this.controlPoints[i+9], this.controlPoints[i+10], this.controlPoints[i+11]);
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i+12], this.controlPoints[i+13], this.controlPoints[i+14], this.controlPoints[i+15]);*/
                   }
                   else if (a==1)
                   {
                      // update the control points for the lower surface
                      P0 = this.getCubicBezierPoint((float)u/NU, updateLowerControlPoints(this.controlPoints[i*15+i]), updateLowerControlPoints(this.controlPoints[i*15+i+1]), updateLowerControlPoints(this.controlPoints[i*15+i+2]), updateLowerControlPoints(this.controlPoints[i*15+i+3]));
                      P1 = this.getCubicBezierPoint((float)u/NU, updateLowerControlPoints(this.controlPoints[i*15+i+4]), updateLowerControlPoints(this.controlPoints[i*15+i+5]), updateLowerControlPoints(this.controlPoints[i*15+i+6]), updateLowerControlPoints(this.controlPoints[i*15+i+7]));
                      P2 = this.getCubicBezierPoint((float)u/NU, updateLowerControlPoints(this.controlPoints[i*15+i+8]), updateLowerControlPoints(this.controlPoints[i*15+i+9]), updateLowerControlPoints(this.controlPoints[i*15+i+10]), updateLowerControlPoints(this.controlPoints[i*15+i+11]));
                      P3 = this.getCubicBezierPoint((float)u/NU, updateLowerControlPoints(this.controlPoints[i*15+i+12]), updateLowerControlPoints(this.controlPoints[i*15+i+13]), updateLowerControlPoints(this.controlPoints[i*15+i+14]), updateLowerControlPoints(this.controlPoints[i*15+i+15]));
               
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, updateLowerControlPoints(this.controlPoints[i*15+i]), updateLowerControlPoints(this.controlPoints[i*15+i+1]), updateLowerControlPoints(this.controlPoints[i*15+i+2]), updateLowerControlPoints(this.controlPoints[i*15+i+3]));
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, updateLowerControlPoints(this.controlPoints[i*15+i+4]), updateLowerControlPoints(this.controlPoints[i*15+i+5]), updateLowerControlPoints(this.controlPoints[i*15+i+6]), updateLowerControlPoints(this.controlPoints[i*15+i+7]));
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, updateLowerControlPoints(this.controlPoints[i*15+i+8]), updateLowerControlPoints(this.controlPoints[i*15+i+9]), updateLowerControlPoints(this.controlPoints[i*15+i+10]), updateLowerControlPoints(this.controlPoints[i*15+i+11]));
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, updateLowerControlPoints(this.controlPoints[i*15+i+12]), updateLowerControlPoints(this.controlPoints[i*15+i+13]), updateLowerControlPoints(this.controlPoints[i*15+i+14]), updateLowerControlPoints(this.controlPoints[i*15+i+15]));
                   }
                   else if (a==2)
                   {
                      // get control points for inner surface
                      P0 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i],updateInnerControlPoints1(this.controlPoints[i*15+i]),updateInnerControlPoints2(this.controlPoints[i*15+i]),updateInnerControlPoints3(this.controlPoints[i*15+i]));
                      P1 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+4],updateInnerControlPoints1(this.controlPoints[i*15+i+4]),updateInnerControlPoints2(this.controlPoints[i*15+i+4]),updateInnerControlPoints3(this.controlPoints[i*15+i+4]));
                      P2 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+8],updateInnerControlPoints1(this.controlPoints[i*15+i+8]),updateInnerControlPoints2(this.controlPoints[i*15+i+8]),updateInnerControlPoints3(this.controlPoints[i*15+i+8]));
                      P3 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+12],updateInnerControlPoints1(this.controlPoints[i*15+i+12]),updateInnerControlPoints2(this.controlPoints[i*15+i+12]),updateInnerControlPoints3(this.controlPoints[i*15+i+12]));
                      
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i],updateInnerControlPoints1(this.controlPoints[i*15+i]),updateInnerControlPoints2(this.controlPoints[i*15+i]),updateInnerControlPoints3(this.controlPoints[i*15+i]));
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+4],updateInnerControlPoints1(this.controlPoints[i*15+i+4]),updateInnerControlPoints2(this.controlPoints[i*15+i+4]),updateInnerControlPoints3(this.controlPoints[i*15+i+4]));
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+8],updateInnerControlPoints1(this.controlPoints[i*15+i+8]),updateInnerControlPoints2(this.controlPoints[i*15+i+8]),updateInnerControlPoints3(this.controlPoints[i*15+i+8]));
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+12],updateInnerControlPoints1(this.controlPoints[i*15+i+12]),updateInnerControlPoints2(this.controlPoints[i*15+i+12]),updateInnerControlPoints3(this.controlPoints[i*15+i+12]));
                   }
                   else if (a==3)
                   {
                      // get control points for outer surface
                      P0 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+3],updateInnerControlPoints1(this.controlPoints[i*15+i+3]),updateInnerControlPoints2(this.controlPoints[i*15+i+3]),updateInnerControlPoints3(this.controlPoints[i*15+i+3]));
                      P1 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+7],updateInnerControlPoints1(this.controlPoints[i*15+i+7]),updateInnerControlPoints2(this.controlPoints[i*15+i+7]),updateInnerControlPoints3(this.controlPoints[i*15+i+7]));
                      P2 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+11],updateInnerControlPoints1(this.controlPoints[i*15+i+11]),updateInnerControlPoints2(this.controlPoints[i*15+i+11]),updateInnerControlPoints3(this.controlPoints[i*15+i+11]));
                      P3 = this.getCubicBezierPoint((float)u/NU, this.controlPoints[i*15+i+15],updateInnerControlPoints1(this.controlPoints[i*15+i+15]),updateInnerControlPoints2(this.controlPoints[i*15+i+15]),updateInnerControlPoints3(this.controlPoints[i*15+i+15]));
                      
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+3],updateInnerControlPoints1(this.controlPoints[i*15+i+3]),updateInnerControlPoints2(this.controlPoints[i*15+i+3]),updateInnerControlPoints3(this.controlPoints[i*15+i+3]));
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+7],updateInnerControlPoints1(this.controlPoints[i*15+i+7]),updateInnerControlPoints2(this.controlPoints[i*15+i+7]),updateInnerControlPoints3(this.controlPoints[i*15+i+7]));
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+11],updateInnerControlPoints1(this.controlPoints[i*15+i+11]),updateInnerControlPoints2(this.controlPoints[i*15+i+11]),updateInnerControlPoints3(this.controlPoints[i*15+i+11]));
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, this.controlPoints[i*15+i+15],updateInnerControlPoints1(this.controlPoints[i*15+i+15]),updateInnerControlPoints2(this.controlPoints[i*15+i+15]),updateInnerControlPoints3(this.controlPoints[i*15+i+15]));
                      
                      
                      
                     /* P0 = this.getCubicBezierPoint((float)u/NU, updateOuterControlPoints1(this.controlPoints[i*15+i]),updateOuterControlPoints2(this.controlPoints[i*15+i]),updateOuterControlPoints3(this.controlPoints[i*15+i]),updateOuterControlPoints4(this.controlPoints[i*15+i]));
                      P1 = this.getCubicBezierPoint((float)u/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+4]),updateOuterControlPoints2(this.controlPoints[i*15+i+4]),updateOuterControlPoints3(this.controlPoints[i*15+i+4]),updateOuterControlPoints4(this.controlPoints[i*15+i+4]));
                      P2 = this.getCubicBezierPoint((float)u/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+8]),updateOuterControlPoints2(this.controlPoints[i*15+i+8]),updateOuterControlPoints3(this.controlPoints[i*15+i+8]),updateOuterControlPoints4(this.controlPoints[i*15+i+8]));
                      P3 = this.getCubicBezierPoint((float)u/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+12]),updateOuterControlPoints2(this.controlPoints[i*15+i+12]),updateOuterControlPoints3(this.controlPoints[i*15+i+12]),updateOuterControlPoints4(this.controlPoints[i*15+i+12]));
                      
                      P00 = this.getCubicBezierPoint((float)(u+1)/NU, updateOuterControlPoints1(this.controlPoints[i*15+i]),updateOuterControlPoints2(this.controlPoints[i*15+i]),updateOuterControlPoints3(this.controlPoints[i*15+i]),updateOuterControlPoints4(this.controlPoints[i*15+i]));
                      P11 = this.getCubicBezierPoint((float)(u+1)/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+4]),updateOuterControlPoints2(this.controlPoints[i*15+i+4]),updateOuterControlPoints3(this.controlPoints[i*15+i+4]),updateOuterControlPoints4(this.controlPoints[i*15+i+4]));
                      P22 = this.getCubicBezierPoint((float)(u+1)/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+8]),updateOuterControlPoints2(this.controlPoints[i*15+i+8]),updateOuterControlPoints3(this.controlPoints[i*15+i+8]),updateOuterControlPoints4(this.controlPoints[i*15+i+8]));
                      P33 = this.getCubicBezierPoint((float)(u+1)/NU, updateOuterControlPoints1(this.controlPoints[i*15+i+12]),updateOuterControlPoints2(this.controlPoints[i*15+i+12]),updateOuterControlPoints3(this.controlPoints[i*15+i+12]),updateOuterControlPoints4(this.controlPoints[i*15+i+12]));*/
                   }

                // divided the surface into four tracks and draw the lane with different coolor
                if (a==0){
                 if (u == NU/4 || u == 2*NU/4 || u == 3*NU/4 )
                 {
                    gl.glColor3f(0f, 0f, 0f);  
                  //drawTriangles(gl, normal,P41,P42, P43,P44); 
                 }
                 else
                 {  
                    gl.glColor3f(1f, 0f, 1f);
                   //drawTriangles(gl, normal,P41,P42, P43,P44); 
                 }}
                else{
                 gl.glColor3f(0.5f, 0f, 0.5f);
                }
                
                // draw the surface through drawing trangles
                gl.glBegin(GL_TRIANGLES); 
               //gl.glPointSize(3);
               //gl.glBegin(GL_POINTS);  
                for (int v = 0; v < NV; v++)
                {
                   Vector P41 = new Vector(0,0,0);
                   Vector P42 = new Vector(0,0,0);
                   Vector P43 = new Vector(0,0,0);
                   Vector P44 = new Vector(0,0,0);
                   
                   P41 = this.getCubicBezierPoint((float)v/NV, P0, P1, P2, P3);
                   P42 = this.getCubicBezierPoint((float)v/NV, P00, P11, P22, P33);
                   P43 = this.getCubicBezierPoint((float)(v+1)/NV, P0, P1, P2, P3);
                   P44 = this.getCubicBezierPoint((float)(v+1)/NV, P00, P11, P22, P33);
                   //gl.glColor3f(1f, 1f, 0f);  
                   drawTriangles(gl, normal,P41,P42, P43,P44); 
                 // gl.glVertex3f((float)P41.x,(float)P41.y,(float)P41.z);   
                }
                gl.glEnd();
                }
               
            }
        }
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
            
            double a = point2.normalized().y*laneWidth*0.5;
            double b = point2.normalized().y*laneWidth*(lane-1)*1;
        
            point3.x = point1.x + point2.normalized().x*laneWidth*0.5 + point2.normalized().x*laneWidth*(lane-1)*1;
            point3.y = point1.y - 3.0*(lane-1) + point2.normalized().y*laneWidth*lane*0.5+ point2.normalized().y*laneWidth*(lane-1)*1;
            point3.z = point1.z + point2.normalized().z*laneWidth*lane*0.5;

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
           
        double x = (1.0f - t)*(1.0f - t)*(1.0f - t)*P0.x + 3*(1.0f - t)*(1.0f - t)*t*P1.x + 3*(1.0f - t)*t*t*P2.x + t*t*t*P3.x;
        double y = (1.0f - t)*(1.0f - t)*(1.0f - t)*P0.y + 3*(1.0f - t)*(1.0f - t)*t*P1.y + 3*(1.0f - t)*t*t*P2.y + t*t*t*P3.y;
        double z = (1.0f - t)*(1.0f - t)*(1.0f - t)*P0.z + 3*(1.0f - t)*(1.0f - t)*t*P1.z + 3*(1.0f - t)*t*t*P2.z + t*t*t*P3.z;
        
        Vector out_pos =  new Vector(x,y,z);
        
        return out_pos; // <- code goes here
    }
    
    /**
     * Returns a tangent on a bezier segment with control points
     * P0, P1, P2, P3 at 0 <= t < 1.
     */
    private Vector getCubicBezierTangent(double t, Vector P0, Vector P1,
                                                   Vector P2, Vector P3) {
        Vector out_direction =  new Vector(0,0,0);
        Vector start_pos =  new Vector(0,0,0);
        
        start_pos.x = (1.0f - t)*(1.0f - t)*P0.x + 3.0f*(1.0f - t)*(1.0f - t)*t*P1.x + 3.0f*(1.0f - t)*t*t*P2.x +t*t*t*P3.x;
        start_pos.y = (1.0f - t)*(1.0f - t)*P0.y + 3.0f*(1.0f - t)*(1.0f - t)*t*P1.y + 3.0f*(1.0f - t)*t*t*P2.y +t*t*t*P3.x;
        start_pos.z = (1.0f - t)*(1.0f - t)*P0.z + 3.0f*(1.0f - t)*(1.0f - t)*t*P1.z + 3.0f*(1.0f - t)*t*t*P2.z +t*t*t*P3.x;
        
        out_direction.x = -3.0f*(1.0f - t)*(1.0f - t)*P0.x  + 3.0f*((1.0f - t)*(1.0f - t)-2.0f*(1.0f - t)*t)*P1.x +3.0f*(2.0f*(1.0f - t)*t-t*t)*P2.x + 3.0f*t*t*P3.x;
        out_direction.y = -3.0f*(1.0f - t)*(1.0f - t)*P0.y  + 3.0f*((1.0f - t)*(1.0f - t)-2.0f*(1.0f - t)*t)*P1.y +3.0f*(2.0f*(1.0f - t)*t-t*t)*P2.x + 3.0f*t*t*P3.y;
        out_direction.z = -3.0f*(1.0f - t)*(1.0f - t)*P0.z  + 3.0f*((1.0f - t)*(1.0f - t)-2.0f*(1.0f - t)*t)*P1.z +3.0f*(2.0f*(1.0f - t)*t-t*t)*P2.x + 3.0f*t*t*P3.z;
        
        out_direction.x = start_pos.x + 1*out_direction.x;
        out_direction.y = start_pos.y + 1*out_direction.y;
        out_direction.z = start_pos.z + 1*out_direction.z;
        
        return out_direction; // <- code goes here
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
  
        double NU = 100;
        double NV = 100;
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
    
    private Vector updateLowerControlPoints(Vector controlPoints)
    {
        double x = controlPoints.x;
        double y = controlPoints.y;
        double z = controlPoints.z - 0.75;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateInnerControlPoints1(Vector controlPoints)
    {
        double x = controlPoints.x;
        double y = controlPoints.y;
        double z = controlPoints.z - 0.25;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateInnerControlPoints2(Vector controlPoints)
    {
        double x = controlPoints.x;
        double y = controlPoints.y;
        double z = controlPoints.z - 0.5;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateInnerControlPoints3(Vector controlPoints)
    {
        double x = controlPoints.x;
        double y = controlPoints.y;
        double z = controlPoints.z - 0.75;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateOuterControlPoints1(Vector controlPoints)
    {
        double x = controlPoints.x +4;
        double y = controlPoints.y +4;
        double z = controlPoints.z;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateOuterControlPoints2(Vector controlPoints)
    {
        double x = controlPoints.x +4;
        double y = controlPoints.y +4;
        double z = controlPoints.z -0.25;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateOuterControlPoints3(Vector controlPoints)
    {
        double x = controlPoints.x +4;
        double y = controlPoints.y +4;
        double z = controlPoints.z -0.5;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    private Vector updateOuterControlPoints4(Vector controlPoints)
    {
        double x = controlPoints.x +4;
        double y = controlPoints.y +4;
        double z = controlPoints.z-0.75;
        
        Vector points = new Vector(x,y,z);
        return points; 
    }
    
    
}


