package robotrace;

import com.jogamp.common.nio.Buffers;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_FLOAT;
import static javax.media.opengl.GL.GL_LINES;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_POSITION;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;

/**
 * Handles all of the RobotRace graphics functionality, which should be extended
 * per the assignment.
 *
 * OpenGL functionality: - Basic commands are called via the gl object; -
 * Utility commands are called via the glu and glut objects;
 *
 * GlobalState: The gs object contains the GlobalState as described in the
 * assignment: - The camera viewpoint angles, phi and theta, are changed
 * interactively by holding the left mouse button and dragging; - The camera
 * view width, vWidth, is changed interactively by holding the right mouse
 * button and dragging upwards or downwards; - The center point can be moved up
 * and down by pressing the 'q' and 'z' keys, forwards and backwards with the
 * 'w' and 's' keys, and left and right with the 'a' and 'd' keys; - Other
 * settings are changed via the menus at the top of the screen.
 *
 * Textures: Place your "track.jpg", "brick.jpg", "head.jpg", and "torso.jpg"
 * files in the same folder as this file. These will then be loaded as the
 * texture objects track, bricks, head, and torso respectively. Be aware, these
 * objects are already defined and cannot be used for other purposes. The
 * texture objects can be used as follows:
 *
 * gl.glColor3f(1f, 1f, 1f); track.bind(gl); gl.glBegin(GL_QUADS);
 * gl.glTexCoord2d(0, 0); gl.glVertex3d(0, 0, 0); gl.glTexCoord2d(1, 0);
 * gl.glVertex3d(1, 0, 0); gl.glTexCoord2d(1, 1); gl.glVertex3d(1, 1, 0);
 * gl.glTexCoord2d(0, 1); gl.glVertex3d(0, 1, 0); gl.glEnd();
 *
 * Note that it is hard or impossible to texture objects drawn with GLUT. Either
 * define the primitives of the object yourself (as seen above) or add
 * additional textured primitives to the GLUT object.
 */
public class RobotRace extends Base {

    /**
     * Array of the four robots.
     */
    private final Robot[] robots;

    /**
     * Instance of the camera.
     */
    private final Camera camera;

    /**
     * Instance of the race track.
     */
    private final RaceTrack[] raceTracks;

    /**
     * Instance of the terrain.
     */
    private final Terrain terrain;
    
     

    /**
     * Constructs this robot race by initializing robots, camera, track, and
     * terrain.
     */
    public RobotRace() {

        // Create a new array of four robots
        robots = new Robot[4];

        // Initialize robot 0
        robots[0] = new Robot(Material.GOLD, new Vector(0, 0, 0));

        // Initialize robot 1
        robots[1] = new Robot(Material.SILVER, new Vector(0, 3, 0));

        // Initialize robot 2
        robots[2] = new Robot(Material.WOOD, new Vector(0, 6, 0));

        // Initialize robot 3
        robots[3] = new Robot(Material.ORANGE, new Vector(0, 9, 0));

        // Initialize the camera
        camera = new Camera();

        // Initialize the race tracks
        raceTracks = new RaceTrack[5];

        // Test track
        raceTracks[0] = new RaceTrack();
        
        // O-track
        raceTracks[1] = new RaceTrack(new Vector[]{ /* add control points like:
            new Vector(10, 0, 1), new Vector(10, 5, 1), new Vector(5, 10, 1),
            new Vector(..., ..., ...), ...
         */  
           
            
            
           /* new Vector(14, 0, 0),new Vector(14+4.0/3, 0, 0), new Vector(14+8.0/3, 0, 0),new Vector(14+12.0/3, 0, 0),
            new Vector(14, 14*4*(Math.sqrt(2)-1)/3, 0),new Vector(14+4.0/3 ,(14+4.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(14+8.0/3, (14+8.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(14+12.0/3, (14+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(14*(Math.sqrt(2)-1)/3*4, 14, 0),new Vector((14+4.0/3)*4*(Math.sqrt(2)-1)/3, 14+4.0/3, 0),new Vector((14+8.0/3)*4*(Math.sqrt(2)-1)/3, 14+8.0/3, 0),new Vector((14+12.0/3)*4*(Math.sqrt(2)-1)/3, 14+12.0/3, 0),
            new Vector(0, 14, 0),new Vector(0, 14+4.0/3, 0),new Vector(0, 14+8.0/3, 0),new Vector(0, 14+12.0/3, 0),
            
            new Vector(-14, 0, 0),new Vector(-(14+4.0/3), 0, 0), new Vector(-(14+8.0/3), 0, 0),new Vector(-(14+12.0/3), 0, 0),
            new Vector(-14, 14*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+4.0/3) ,(14+4.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+8.0/3), (14+8.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+12.0/3), (14+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(-14*(Math.sqrt(2)-1)/3*4, 14, 0),new Vector(-(14+4.0/3)*4*(Math.sqrt(2)-1)/3, 14+4.0/3, 0),new Vector(-(14+8.0/3)*4*(Math.sqrt(2)-1)/3, 14+8.0/3, 0),new Vector(-(14+12.0/3)*4*(Math.sqrt(2)-1)/3, 14+12.0/3, 0),
            new Vector(0, 14, 0),new Vector(0, (14+4.0/3), 0),new Vector(0, (14+8.0/3), 0),new Vector(0, (14+12.0/3), 0),
            
            new Vector(14, 0, 0),new Vector((14+4.0/3), 0, 0), new Vector((14+8.0/3), 0, 0),new Vector((14+12.0/3), 0, 0),
            new Vector(14, -14*4*(Math.sqrt(2)-1)/3, 0),new Vector((14+4.0/3) ,-(14+4.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector((14+8.0/3), -(14+8.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector((14+12.0/3), -(14+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(14*(Math.sqrt(2)-1)/3*4, -14, 0),new Vector((14+4.0/3)*4*(Math.sqrt(2)-1)/3, -(14+4.0/3), 0),new Vector((14+8.0/3)*4*(Math.sqrt(2)-1)/3, -(14+8.0/3), 0),new Vector((14+12.0/3)*4*(Math.sqrt(2)-1)/3, -(14+12.0/3), 0),
            new Vector(0, -14, 0),new Vector(0, -(14+4.0/3), 0),new Vector(0, -(14+8.0/3), 0),new Vector(0, -(14+12.0/3), 0),
            
            new Vector(-14, 0, 0),new Vector(-(14+4.0/3), 0, 0), new Vector(-(14+8.0/3), 0, 0),new Vector(-(14+12.0/3), 0, 0),
            new Vector(-14, -14*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+4.0/3) ,-(14+4.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+8.0/3), -(14+8.0/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(14+12.0/3), -(14+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(-14*(Math.sqrt(2)-1)/3*4, -14, 0),new Vector(-(14+4.0/3)*4*(Math.sqrt(2)-1)/3, -(14+4.0/3), 0),new Vector(-(14+8.0/3)*4*(Math.sqrt(2)-1)/3, -(14+8.0/3), 0),new Vector(-(14+12.0/3)*4*(Math.sqrt(2)-1)/3, -(14+12.0/3), 0),
            new Vector(0, -14, 0),new Vector(0, -(14+4.0/3), 0),new Vector(0, -(14+8.0/3), 0),new Vector(0, -(14+12.0/3), 0),*/
            
            //bottom right
            new Vector((9+0.2)+5, 0+5, 0),new Vector((9+4.0/3+0.2)+5, 0+5, 0), new Vector((9+8.0/3+0.2)+5, 0+5, 0),new Vector((9+12.0/3+0.2)+5, 0+5, 0),
            new Vector((9+5), 4*(Math.sqrt(2)-1)/3*9+5, 0),new Vector((9+4.0/3)+5,4*(Math.sqrt(2)-1)/3*(9+4.0/3)+5, 0),new Vector(((9+8.0/3))+5, 4*(Math.sqrt(2)-1)/3*(9+8.0/3)+5, 0),new Vector(((9+12.0/3))+5, 4*(Math.sqrt(2)-1)/3*(9+12.0/3)+5, 0),
            new Vector((9*(Math.sqrt(2)-1)/3*4)+5, 9+5, 0),new Vector((4*(Math.sqrt(2)-1)/3*(9+4.0/3))+5, (9+4.0/3)+5, 0),new Vector((4*(Math.sqrt(2)-1)/3*(9+8.0/3))+5, (9+8.0/3)+5, 0),new Vector((4*(Math.sqrt(2)-1)/3*(9+12.0/3))+5, (9+12.0/3)+5, 0),
            new Vector(0+5, 9+0.2+5, 0),new Vector(0+5, 9+4.0/3+0.2+5, 0),new Vector(0+5, 9+8.0/3+0.2+5, 0),new Vector(0+5, 9+12.0/3+0.2+5, 0),
            
            new Vector(-((9+0.2)+5), 0+5, 0),new Vector(-((9+4.0/3+0.2)+5), 0+5, 0), new Vector(-((9+8.0/3+0.2)+5), 0+5, 0),new Vector(-((9+12.0/3+0.2)+5), 0+5, 0),
            new Vector(-(9+5), 4*(Math.sqrt(2)-1)/3*9+5, 0),new Vector(-((9+4.0/3)+5),4*(Math.sqrt(2)-1)/3*(9+4.0/3)+5, 0),new Vector(-(((9+8.0/3))+5), 4*(Math.sqrt(2)-1)/3*(9+8.0/3)+5, 0),new Vector(-(((9+12.0/3))+5), 4*(Math.sqrt(2)-1)/3*(9+12.0/3)+5, 0),
            new Vector(-((9*(Math.sqrt(2)-1)/3*4)+5), 9+5, 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+4.0/3))+5), (9+4.0/3)+5, 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+8.0/3))+5), (9+8.0/3)+5, 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+12.0/3))+5), (9+12.0/3)+5, 0),
            new Vector(-(0+5), 9+0.2+5, 0),new Vector(-(0+5), 9+4.0/3+0.2+5, 0),new Vector(-(0+5), 9+8.0/3+0.2+5, 0),new Vector(-(0+5), 9+12.0/3+0.2+5, 0),

            new Vector(((9+0.2)+5), -(0+5), 0),new Vector(((9+4.0/3+0.2)+5), -(0+5), 0), new Vector(((9+8.0/3+0.2)+5), -(0+5), 0),new Vector(((9+12.0/3+0.2)+5), -(0+5), 0),
            new Vector((9+5), -(4*(Math.sqrt(2)-1)/3*9+5), 0),new Vector(((9+4.0/3)+5),-(4*(Math.sqrt(2)-1)/3*(9+4.0/3)+5), 0),new Vector((((9+8.0/3))+5), -(4*(Math.sqrt(2)-1)/3*(9+8.0/3)+5), 0),new Vector((((9+12.0/3))+5), -(4*(Math.sqrt(2)-1)/3*(9+12.0/3)+5), 0),
            new Vector(((9*(Math.sqrt(2)-1)/3*4)+5), -(9+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+4.0/3))+5), -((9+4.0/3)+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+8.0/3))+5), -((9+8.0/3)+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+12.0/3))+5), -((9+12.0/3)+5), 0),
            new Vector((0+5), -(9+0.2+5), 0),new Vector((0+5), -(9+4.0/3+0.2+5), 0),new Vector((0+5), -(9+8.0/3+0.2+5), 0),new Vector((0+5), -(9+12.0/3+0.2+5), 0),
            
            new Vector(-((9+0.2)+5), -(0+5), 0),new Vector(-((9+4.0/3+0.2)+5), -(0+5), 0), new Vector(-((9+8.0/3+0.2)+5), -(0+5), 0),new Vector(-((9+12.0/3+0.2)+5), -(0+5), 0),
            new Vector(-(9+5), -(4*(Math.sqrt(2)-1)/3*9+5), 0),new Vector(-((9+4.0/3)+5),-(4*(Math.sqrt(2)-1)/3*(9+4.0/3)+5), 0),new Vector(-(((9+8.0/3))+5), -(4*(Math.sqrt(2)-1)/3*(9+8.0/3)+5), 0),new Vector(-(((9+12.0/3))+5), -(4*(Math.sqrt(2)-1)/3*(9+12.0/3)+5), 0),
            new Vector(-((9*(Math.sqrt(2)-1)/3*4)+5), -(9+5), 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+4.0/3))+5), -((9+4.0/3)+5), 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+8.0/3))+5), -((9+8.0/3)+5), 0),new Vector(-((4*(Math.sqrt(2)-1)/3*(9+12.0/3))+5), -((9+12.0/3)+5), 0),
            new Vector(-(0+5), -(9+0.2+5), 0),new Vector(-(0+5), -(9+4.0/3+0.2+5), 0),new Vector(-(0+5), -(9+8.0/3+0.2+5), 0),new Vector(-(0+5), -(9+12.0/3+0.2+5), 0),
            
            new Vector(-(0+5), (9+0.2+5), 0),new Vector(-(0+5), (9+4.0/3+0.2+5), 0),new Vector(-(0+5), (9+8.0/3+0.2+5), 0),new Vector(-(0+5), (9+12.0/3+0.2+5), 0),
            new Vector((-(9*(Math.sqrt(2)-1)/3*4)/2+5), (9+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2+5), (9+4.0/3+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2+5), (9+8.0/3+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2+5), (9+12.0/3+0.1+5), 0),
            new Vector(((9*(Math.sqrt(2)-1)/3*4)/2-5), (9+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2-5), (9+4.0/3+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2-5), (9+8.0/3+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2-5), (9+12.0/3+0.1+5), 0),
            new Vector((0+5), (9+0.2+5), 0),new Vector((0+5), (9+4.0/3+0.2+5), 0),new Vector((0+5), (9+8.0/3+0.2+5), 0),new Vector((0+5), (9+12.0/3+0.2+5), 0),
            
            new Vector(-(0+5), -(9+0.2+5), 0),new Vector(-(0+5), -(9+4.0/3+0.2+5), 0),new Vector(-(0+5), -(9+8.0/3+0.2+5), 0),new Vector(-(0+5), -(9+12.0/3+0.2+5), 0),
            new Vector((-(9*(Math.sqrt(2)-1)/3*4)/2+5), -(9+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2+5), -(9+4.0/3+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2+5), -(9+8.0/3+0.1+5), 0),new Vector((-(4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2+5), -(9+12.0/3+0.1+5), 0),
            new Vector(((9*(Math.sqrt(2)-1)/3*4)/2-5), -(9+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2-5), -(9+4.0/3+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2-5), -(9+8.0/3+0.1+5), 0),new Vector(((4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2-5), -(9+12.0/3+0.1+5), 0),
            new Vector((0+5), -(9+0.2+5), 0),new Vector((0+5), -(9+4.0/3+0.2+5), 0),new Vector((0+5), -(9+8.0/3+0.2+5), 0),new Vector((0+5), -(9+12.0/3+0.2+5), 0),
            
            new Vector((9+0.2+5), -(0+5), 0),new Vector((9+4.0/3+0.2+5), -(0+5), 0),new Vector((9+8.0/3+0.2+5) ,-(0+5) , 0),new Vector((9+12.0/3+0.2+5) ,-(0+5) , 0),
            new Vector((9+0.1+5), (-(9*(Math.sqrt(2)-1)/3*4)/2+5), 0),new Vector((9+4.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2+5), 0),new Vector((9+8.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2+5), 0),new Vector((9+12.0/3+0.1+5),(-(4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2+5), 0),
            new Vector((9+0.1+5), ((9*(Math.sqrt(2)-1)/3*4)/2-5), 0),new Vector((9+4.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2-5), 0),new Vector((9+8.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2-5), 0),new Vector((9+12.0/3+0.1+5),((4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2-5), 0),
            new Vector((9+0.2+5), (0+5), 0),new Vector((9+4.0/3+0.2+5), (0+5), 0),new Vector((9+8.0/3+0.2+5) ,(0+5) , 0),new Vector((9+12.0/3+0.2+5) ,(0+5) , 0),
            
            new Vector((9+0.2+5), -(0+5), 0),new Vector((9+4.0/3+0.2+5), -(0+5), 0),new Vector((9+8.0/3+0.2+5) ,-(0+5) , 0),new Vector((9+12.0/3+0.2+5) ,-(0+5) , 0),
            new Vector((9+0.1+5), (-(9*(Math.sqrt(2)-1)/3*4)/2+5), 0),new Vector((9+4.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2+5), 0),new Vector((9+8.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2+5), 0),new Vector((9+12.0/3+0.1+5),(-(4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2+5), 0),
            new Vector((9+0.1+5), ((9*(Math.sqrt(2)-1)/3*4)/2-5), 0),new Vector((9+4.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2-5), 0),new Vector((9+8.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2-5), 0),new Vector((9+12.0/3+0.1+5),((4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2-5), 0),
            new Vector((9+0.2+5), (0+5), 0),new Vector((9+4.0/3+0.2+5), (0+5), 0),new Vector((9+8.0/3+0.2+5) ,(0+5) , 0),new Vector((9+12.0/3+0.2+5) ,(0+5) , 0),
            
            new Vector(-(9+0.2+5), -(0+5), 0),new Vector(-(9+4.0/3+0.2+5), -(0+5), 0),new Vector(-(9+8.0/3+0.2+5) ,-(0+5) , 0),new Vector(-(9+12.0/3+0.2+5) ,-(0+5) , 0),
            new Vector(-(9+0.1+5), (-(9*(Math.sqrt(2)-1)/3*4)/2+5), 0),new Vector(-(9+4.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2+5), 0),new Vector(-(9+8.0/3+0.1+5), (-(4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2+5), 0),new Vector(-(9+12.0/3+0.1+5),(-(4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2+5), 0),
            new Vector(-(9+0.1+5), ((9*(Math.sqrt(2)-1)/3*4)/2-5), 0),new Vector(-(9+4.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+4.0/3))/2-5), 0),new Vector(-(9+8.0/3+0.1+5), ((4*(Math.sqrt(2)-1)/3*(9+8.0/3))/2-5), 0),new Vector(-(9+12.0/3+0.1+5),((4*(Math.sqrt(2)-1)/3*(9+12.0/3))/2-5), 0),
            new Vector(-(9+0.2+5), (0+5), 0),new Vector(-(9+4.0/3+0.2+5), (0+5), 0),new Vector(-(9+8.0/3+0.2+5) ,(0+5) , 0),new Vector(-(9+12.0/3+0.2+5) ,(0+5) , 0),
            
           
        });

        // L-track
        raceTracks[2] = new RaceTrack(new Vector[]{ 
            /* add control points */         
            // inner
            new Vector(0+8, (4.0)+10, 0),new Vector(0+8, (4+4.0/3)+10, 0), new Vector(0+8, (4+8.0/3)+10, 0),new Vector(0+8, (8)+10, 0),
            new Vector(-4*4*(Math.sqrt(2)-1)/3+8, 4+10, 0),new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3+8, (4+4/3)+10, 0), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3+8, (4+8.0/3)+10, 0), new Vector(-8.0*4.0*(Math.sqrt(2)-1)/3+8, 8+10, 0),
            new Vector(-4+8, 4*4*(Math.sqrt(2)-1)/3+10, 0),new Vector(-(4+4.0/3)+8, (4+4/3)*4*(Math.sqrt(2)-1)/3+10, 0), new Vector(-(4+8.0/3)+8, (4+8.0/3)*4*(Math.sqrt(2)-1)/3+10, 0), new Vector(-8+8, 8.0*4.0*(Math.sqrt(2)-1)/3+10, 0),
            new Vector(-(4+0.2)+8, 0+10, 0),new Vector(-(4+4.0/3+0.2)+8, 0+10, 0), new Vector(-(4+8.0/3+0.2)+8, 0+10, 0),new Vector(-(4+12.0/3+0.2)+8, 0+10, 0),
            
            new Vector(-(4+0.2)+8, 0+10, 0),new Vector(-(4+4.0/3+0.2)+8, 0+10, 0), new Vector(-(4+8.0/3+0.2)+8, 0+10, 0),new Vector(-(4+12.0/3+0.2)+8, 0+10, 0),
            new Vector(-(4+0.2+0.1)+8, -4*4*(Math.sqrt(2)-1)/3/2+10, 0),new Vector(-(4+4.0/3+0.2+0.1)+8, -(4+4/3)*4*(Math.sqrt(2)-1)/3/2+10, 0), new Vector(-(4+8.0/3+0.2+0.1)+8, -(4+8.0/3)*4*(Math.sqrt(2)-1)/2/3+10, 0),new Vector(-(4+12.0/3+0.2+0.1)+8, -8.0*4.0*(Math.sqrt(2)-1)/3/2+10, 0),
            new Vector(-(4+0.2+0.1)+8, -2+10, 0),new Vector(-(4+4.0/3+0.2+0.1)+8, -2+10, 0), new Vector(-(4+8.0/3+0.2+0.1)+8, -2+10, 0),new Vector(-(4+12.0/3+0.2+0.1)+8, -2+10, 0),
            new Vector(-(4+0.2+0.1)+8, -5+10, 0),new Vector(-(4+4.0/3+0.2+0.1)+8, -5+10, 0), new Vector(-(4+8.0/3+0.2+0.1)+8, -5+10, 0),new Vector(-(4+12.0/3+0.2+0.1)+8, -5+10, 0),
            
            new Vector(-(4+0.2+0.1)+8, -5+10, 0),new Vector(-(4+4.0/3+0.2+0.1)+8, -5+10, 0), new Vector(-(4+8.0/3+0.2+0.1)+8, -5+10, 0),new Vector(-(4+12.0/3+0.2+0.1)+8, -5+10, 0),
            new Vector(-(4+0.2+0.1)+8, -5+10, 0),new Vector(-(4+4.0/3+0.2+0.1)+8, -5+10, 0), new Vector(-(4+8.0/3+0.2+0.1)+8, -5+10, 0),new Vector(-(4+12.0/3+0.2+0.1)+8, -5+10, 0),
            new Vector((4+12.0/3)-0.1-12+8, 8.0*4.0*(Math.sqrt(2)-1)/3/2, 0),new Vector((4+8.0/3)-0.1-12+8, (4+8.0/3)*4*(Math.sqrt(2)-1)/3/2, 0),new Vector((4+4.0/3)-0.1-12+8, (4+4/3)*4*(Math.sqrt(2)-1)/3/2, 0), new Vector(4-0.1-12+8, 4*4*(Math.sqrt(2)-1)/3/2, 0),
            new Vector((4+12.0/3)-0.2-12+8, 0, 0),new Vector((4+8.0/3)-0.2-12+8, 0, 0),new Vector((4+4.0/3)-0.2-12+8, 0, 0),new Vector(4-0.2-12+8, 0, 0),

            new Vector((4+12.0/3)-0.2-12+8, 0, 0),new Vector((4+8.0/3)-0.2-12+8, 0, 0),new Vector((4+4.0/3)-0.2-12+8, 0, 0),new Vector(4-0.2-12+8, 0, 0),
            new Vector(8-12+8, -8.0*4.0*(Math.sqrt(2)-1)/3, 0),new Vector((4+8.0/3)-12+8, -(4+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((4+4.0/3)-12+8, -(4+4/3)*4*(Math.sqrt(2)-1)/3, 0),new Vector(4-12+8, -4*4*(Math.sqrt(2)-1)/3, 0), 
            new Vector(8.0*4.0*(Math.sqrt(2)-1)/3-12+8, -8, 0),new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3-12+8, -(4+8.0/3), 0), new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3-12+8, -(4+4/3), 0), new Vector(4*4*(Math.sqrt(2)-1)/3-12+8, -4, 0),
            new Vector(0-12+8,-(8+0.2), 0),new Vector(0-12+8, -(4+8.0/3+0.2), 0),new Vector(0-12+8, -(4+4.0/3), 0), new Vector(0-12+8, -(4.0+0.2), 0),
            
            new Vector(0-4-16, (4.0)-12+0.2, 0),new Vector(0-4-16, (4+4.0/3)-12+0.2, 0), new Vector(0-4-16, (4+8.0/3)-12+0.2, 0),new Vector(0-4-16, (8)-12+0.2, 0),
            new Vector(-4*4*(Math.sqrt(2)-1)/3-4-16, 4-12, 0),new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3-4-16, (4+4/3)-12, 0), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3-4-16, (4+8.0/3)-12, 0), new Vector(-8.0*4.0*(Math.sqrt(2)-1)/3-4-16, 8-12, 0),
            new Vector(-4-4-16, 4*4*(Math.sqrt(2)-1)/3-12, 0),new Vector(-(4+4.0/3)-4-16, (4+4/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector(-(4+8.0/3)-4-16, (4+8.0/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector(-8-4-16, 8.0*4.0*(Math.sqrt(2)-1)/3-12, 0),
            new Vector(-(4)-4-16, 0-12, 0),new Vector(-(4+4.0/3)-4-16, 0-12, 0), new Vector(-(4+8.0/3)-4-16, 0-12, 0),new Vector(-(4+12.0/3)-4-16, 0-12, 0),
            
            new Vector(0-4-16, (4.0)-12+0.2, 0),new Vector(0-4-16, (4+4.0/3)-12+0.2, 0), new Vector(0-4-16, (4+8.0/3)-12+0.2, 0),new Vector(0-4-16, (8)-12+0.2, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3/2-8-12, (4.0)-12+0.3, 0),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3/2-8-12, (4+4.0/3)-12+0.3, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3/2-8-12, (4+8.0/3)-12+0.3, 0),new Vector((4+12.0/3)*4*(Math.sqrt(2)-1)/3/2-8-12, (8)-12+0.3, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3/2-4-4, (4.0)-12-0.3, 0),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3/2-4-4, (4+4.0/3)-12-0.3, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3/2-4-4, (4+8.0/3)-12-0.3, 0),new Vector((4+12.0/3)*4*(Math.sqrt(2)-1)/3/2-4-4, (8)-12-0.3, 0),
            new Vector(0-4, (4.0)-12-0.2, 0),new Vector(0-4, (4+4.0/3)-12-0.2, 0), new Vector(0-4, (4+8.0/3)-12-0.2, 0),new Vector(0-4, (8)-12-0.2, 0),
            
            new Vector(0-4-16, -(4.0)-12-0.2, 0),new Vector(0-4-16, -(4+4.0/3)-12-0.2, 0), new Vector(0-4-16, -(4+8.0/3)-12-0.2, 0),new Vector(0-4-16, -(8)-12-0.2, 0),
            new Vector(-4*4*(Math.sqrt(2)-1)/3-4-16, -4-12, 0),new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3-4-16, -(4+4/3)-12, 0), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3-4-16, -(4+8.0/3)-12, 0), new Vector(-8.0*4.0*(Math.sqrt(2)-1)/3-4-16, -8-12, 0),
            new Vector(-4-4-16, -4*4*(Math.sqrt(2)-1)/3-12, 0),new Vector(-(4+4.0/3)-4-16, -(4+4/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector(-(4+8.0/3)-4-16, -(4+8.0/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector(-8-4-16, -8.0*4.0*(Math.sqrt(2)-1)/3-12, 0),
            new Vector(-(4)-4-16, 0-12, 0),new Vector(-(4+4.0/3)-4-16, 0-12, 0), new Vector(-(4+8.0/3)-4-16, 0-12, 0),new Vector(-(4+12.0/3)-4-16, 0-12, 0),
            
            new Vector(0-4-16, -(4.0)-12-0.2, 0),new Vector(0-4-16, -(4+4.0/3)-12-0.2, 0), new Vector(0-4-16, -(4+8.0/3)-12-0.2, 0),new Vector(0-4-16, -(8)-12-0.2, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3/2-12-8, -(4.0)-12-0.3, 0),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3/2-12-8, -(4+4.0/3)-12-0.3, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3/2-12-8, -(4+8.0/3)-12-0.3, 0),new Vector((4+12.0/3)*4*(Math.sqrt(2)-1)/3/2-12-8, -(8)-12-0.3, 0),
            new Vector(-4*4*(Math.sqrt(2)-1)/3/2+8, -(4.0)-12-0.3, 0),new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3/2+8, -(4+4.0/3)-12-0.3, 0), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3/2+8, -(4+8.0/3)-12-0.3, 0),new Vector(-(4+12.0/3)*4*(Math.sqrt(2)-1)/3/2+8, -(8)-12-0.3, 0),
            new Vector(0+8, -(4.0)-12-0.2, 0),new Vector(0+8, -(4+4.0/3)-12-0.2, 0), new Vector(0+8, -(4+8.0/3)-12-0.2, 0),new Vector(0+8, -(8)-12-0.2, 0),
            //new Vector(0-12+8,-(8+0.2), 0),new Vector(0-12+8, -(4+8.0/3+0.2), 0),new Vector(0-12+8, -(4+4.0/3), 0), new Vector(0-12+8, -(4.0+0.2), 0),
            //new Vector(0-12+8+8.0*4.0*(Math.sqrt(2)-1)/3/2,-(8+0.2)-0.1, 0),new Vector(0-12+8+(4+8.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+8.0/3+0.2)-0.1, 0),new Vector(0-12+8+(4+4.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+4.0/3)-0.1, 0), new Vector(0-12+8+4*4*(Math.sqrt(2)-1)/3/2, -(4.0+0.2)-0.1, 0),
            //new Vector(0-12+8+8.0*4.0*(Math.sqrt(2)-1)/3/2,-(8+0.2)-0.1, 0),new Vector(0-12+8+(4+8.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+8.0/3+0.2)-0.1, 0),new Vector(0-12+8+(4+4.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+4.0/3)-0.1, 0), new Vector(0-12+8+4*4*(Math.sqrt(2)-1)/3/2, -(4.0+0.2)-0.1, 0),
            //new Vector(0-12+8+8.0*4.0*(Math.sqrt(2)-1)/3/2,-(8+0.2)-0.1, 0),new Vector(0-12+8+(4+8.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+8.0/3+0.2)-0.1, 0),new Vector(0-12+8+(4+4.0/3)*4*(Math.sqrt(2)-1)/3/2, -(4+4.0/3)-0.1, 0), new Vector(0-12+8+4*4*(Math.sqrt(2)-1)/3/2, -(4.0+0.2)-0.1, 0),
           
           
           
            // outer
            new Vector(0+8, (4.0)+10, 0),new Vector(0+8, (4+4.0/3)+10, 0), new Vector(0+8, (4+8.0/3)+10, 0),new Vector(0+8,(8)+10, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3+8, 4+10, 0),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3+8, (4+4/3)+10, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3+8, (4+8.0/3)+10, 0), new Vector(8.0*4.0*(Math.sqrt(2)-1)/3+8, 8+10, 0),
            new Vector(4+8, 4*4*(Math.sqrt(2)-1)/3+10, 0),new Vector((4+4.0/3)+8, (4+4/3)*4*(Math.sqrt(2)-1)/3+10, 0), new Vector((4+8.0/3)+8, (4+8.0/3)*4*(Math.sqrt(2)-1)/3+10, 0), new Vector(8+8, 8.0*4.0*(Math.sqrt(2)-1)/3+10, 0),
            new Vector(4+0.2+8, 0+10, 0),new Vector((4+4.0/3)+0.2+8, 0+10, 0), new Vector((4+8.0/3)+0.2+8, 0+10, 0),new Vector((4+12.0/3)+0.2+8, 0+10, 0),
            
            new Vector(4+0.2+8, 0+10, 0),new Vector((4+4.0/3)+0.2+8, 0+10, 0), new Vector((4+8.0/3)+0.2+8, 0+10, 0),new Vector((4+12.0/3)+0.2+8, 0+10, 0),
            new Vector(4+0.2+0.1+8, -4*4*(Math.sqrt(2)-1)/3/2+10, 0),new Vector((4+4.0/3)+0.2+0.1+8, -(4+4/3)*4*(Math.sqrt(2)-1)/3/2+10, 0), new Vector((4+8.0/3)+0.2+0.1+8, -(4+8.0/3)*4*(Math.sqrt(2)-1)/2/3+10, 0),new Vector((4+12.0/3)+0.2+0.1+8, -8.0*4.0*(Math.sqrt(2)-1)/3/2+10, 0),
            new Vector(4+0.2+0.1+8, -4*4*(Math.sqrt(2)-1)/3/2-12, 0),new Vector((4+4.0/3)+0.2+0.1+8, -(4+4/3)*4*(Math.sqrt(2)-1)/3/2-12, 0), new Vector((4+8.0/3)+0.2+0.1+8, -(4+8.0/3)*4*(Math.sqrt(2)-1)/2/3-12, 0),new Vector((4+12.0/3)+0.2+0.1+8, -8.0*4.0*(Math.sqrt(2)-1)/3/2-12, 0),
            new Vector(4+0.2+8, 0-12, 0),new Vector((4+4.0/3)+0.2+8, 0-12, 0), new Vector((4+8.0/3)+0.2+8, 0-12, 0),new Vector((4+12.0/3)+0.2+8, 0-12, 0),

            new Vector(4+0.2+8, 0-12, 0),new Vector((4+4.0/3)+0.2+8, 0-12, 0), new Vector((4+8.0/3)+0.2+8, 0-12, 0),new Vector((4+12.0/3)+0.2+8, 0-12, 0),
            new Vector(4+8, -4*4*(Math.sqrt(2)-1)/3-12, 0),new Vector((4+4.0/3)+8, -(4+4/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector((4+8.0/3)+8, -(4+8.0/3)*4*(Math.sqrt(2)-1)/3-12, 0), new Vector(8+8, -8.0*4.0*(Math.sqrt(2)-1)/3-12, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3+8, -4-12, 0),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3+8, -(4+4/3)-12, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3+8, -(4+8.0/3)-12, 0), new Vector(8.0*4.0*(Math.sqrt(2)-1)/3+8, -8-12, 0),
            new Vector(0+8, -(4.0+0.2)-12, 0),new Vector(0+8, -(4+4.0/3)-12, 0), new Vector(0+8, -(4+8.0/3+0.2)-12, 0),new Vector(0+8,-(8+0.2)-12, 0),
            
        });

        // C-track
        raceTracks[3] = new RaceTrack(new Vector[]{ /* add control points */
            //bottom
            new Vector(2-8, 0, 0), new Vector((2+4.0/3)-8, 0, 0), new Vector((2+8.0/3)-8, 0, 0), new Vector((2+12.0/3)-8, 0, 0),
            new Vector(2-8, 2*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+4.0/3)-8, (2+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+8.0/3)-8, (2+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+12.0/3)-8, (2+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(2*4*(Math.sqrt(2)-1)/3-8, 2, 0), new Vector((2+4.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+4.0/3), 0), new Vector((2+8.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+8.0/3), 0), new Vector((2+12.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+12.0/3), 0),
            new Vector(0-8, 2, 0), new Vector(0-8, (2+4.0/3), 0), new Vector(0-8, (2+8.0/3), 0), new Vector(0-8, (2+12.0/3), 0),
            
            new Vector(-2-8, 0, 0), new Vector(-(2+4.0/3)-8, 0, 0), new Vector(-(2+8.0/3)-8, 0, 0), new Vector(-(2+12.0/3)-8, 0, 0),
            new Vector(-2-8, 2*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+4.0/3)-8, (2+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+8.0/3)-8, (2+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+12.0/3)-8, (2+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(-2*4*(Math.sqrt(2)-1)/3-8, 2, 0), new Vector(-(2+4.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+4.0/3), 0), new Vector(-(2+8.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+8.0/3), 0), new Vector(-(2+12.0/3)*4*(Math.sqrt(2)-1)/3-8, (2+12.0/3), 0),
            new Vector(0-8, 2, 0), new Vector(0-8, (2+4.0/3), 0), new Vector(0-8, (2+8.0/3), 0), new Vector(0-8, (2+12.0/3), 0),
            
            //top
            new Vector(2+8, 0, 0), new Vector((2+4.0/3)+8, 0, 0), new Vector((2+8.0/3)+8, 0, 0), new Vector((2+12.0/3)+8, 0, 0),
            new Vector(2+8, 2*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+4.0/3)+8, (2+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+8.0/3)+8, (2+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((2+12.0/3)+8, (2+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(2*4*(Math.sqrt(2)-1)/3+8, 2, 0), new Vector((2+4.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+4.0/3), 0), new Vector((2+8.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+8.0/3), 0), new Vector((2+12.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+12.0/3), 0),
            new Vector(0+8, 2, 0), new Vector(0+8, (2+4.0/3), 0), new Vector(0+8, (2+8.0/3), 0), new Vector(0+8, (2+12.0/3), 0),
            
            new Vector(-2+8, 0, 0), new Vector(-(2+4.0/3)+8, 0, 0), new Vector(-(2+8.0/3)+8, 0, 0), new Vector(-(2+12.0/3)+8, 0, 0),
            new Vector(-2+8, 2*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+4.0/3)+8, (2+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+8.0/3)+8, (2+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(2+12.0/3)+8, (2+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(-2*4*(Math.sqrt(2)-1)/3+8, 2, 0), new Vector(-(2+4.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+4.0/3), 0), new Vector(-(2+8.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+8.0/3), 0), new Vector(-(2+12.0/3)*4*(Math.sqrt(2)-1)/3+8, (2+12.0/3), 0),
            new Vector(0+8, 2, 0), new Vector(0+8, (2+4.0/3), 0), new Vector(0+8, (2+8.0/3), 0), new Vector(0+8, (2+12.0/3), 0),
            
            
            
            //inner
            new Vector(12, 0, 0), new Vector((12+4.0/3), 0, 0), new Vector((12+8.0/3), 0, 0), new Vector((12+12.0/3), 0, 0),
            new Vector(12, 12*4*(Math.sqrt(2)-1)/3, 0), new Vector((12+4.0/3), (12+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((12+8.0/3), (12+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector((12+12.0/3), (12+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(12*4*(Math.sqrt(2)-1)/3, 12, 0), new Vector((12+4.0/3)*4*(Math.sqrt(2)-1)/3, (12+4.0/3), 0), new Vector((12+8.0/3)*4*(Math.sqrt(2)-1)/3, (12+8.0/3), 0), new Vector((12+12.0/3)*4*(Math.sqrt(2)-1)/3, (12+12.0/3), 0),
            new Vector(0, 1, 0), new Vector(0, (12+4.0/3), 0), new Vector(0, (12+8.0/3), 0), new Vector(0, (12+12.0/3), 0),
            
            new Vector(-12, 0, 0), new Vector(-(12+4.0/3), 0, 0), new Vector(-(12+8.0/3), 0, 0), new Vector(-(12+12.0/3), 0, 0),
            new Vector(-12, 8*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(12+4.0/3), (12+4.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(12+8.0/3), (12+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(12+12.0/3), (12+12.0/3)*4*(Math.sqrt(2)-1)/3, 0),
            new Vector(-12*4*(Math.sqrt(2)-1)/3, 12, 0), new Vector(-(12+4.0/3)*4*(Math.sqrt(2)-1)/3, (12+4.0/3), 0), new Vector(-(12+8.0/3)*4*(Math.sqrt(2)-1)/3, (12+8.0/3), 0), new Vector(-(12+12.0/3)*4*(Math.sqrt(2)-1)/3, (12+12.0/3), 0),
            new Vector(0, 12, 0), new Vector(0, (12+4.0/3), 0), new Vector(0, (12+8.0/3), 0), new Vector(0, (12+12.0/3), 0),





                    });
        
            
            
        // Custom track
        raceTracks[4] = new RaceTrack(new Vector[]{
        /* add control points */
            //bottom straight
            /*new Vector(0,0,0),new Vector(4.0/3,0,0), new Vector(8.0/3,0,0),new Vector(4.0,0,0),
            new Vector(0,0,0),new Vector(4.0/3, 4.0*Math.sqrt(3)/9,0), new Vector(8.0/3, (8.0*Math.sqrt(3)/9),0), new Vector(4, (4.0*Math.sqrt(3)/3),0),
            new Vector(0,0,0),new Vector(4.0*Math.sqrt(3)/9,4.0/3, 0), new Vector((8.0*Math.sqrt(3)/9),8.0/3, 0), new Vector((4.0*Math.sqrt(3)/3),4, 0),
            new Vector(0,0,0),new Vector(0,4.0/3,0), new Vector(0,8.0/3,0),new Vector(0,4,0),*/
            
            
            
            //8
            new Vector(4, 0, 0),new Vector(4+4.0/3, 0, 0), new Vector(4+8.0/3, 0, 0),new Vector(4+12.0/3, 0, 0),
            new Vector(4, -4*4*(Math.sqrt(2)-1)/3, 0),new Vector(4+4.0/3, -(4+4/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(4+8.0/3, -(4+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(8, -8.0*4.0*(Math.sqrt(2)-1)/3, 0),
            new Vector(4*4*(Math.sqrt(2)-1)/3, -4, 3),new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3, -(4+4/3), 3), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3, -(4+8.0/3), 3), new Vector(8.0*4.0*(Math.sqrt(2)-1)/3, -8, 3),
            new Vector(0, -(4.0+0.2), 3),new Vector(0, -(4+4.0/3+0.2), 3), new Vector(0, -(4+8.0/3+0.2), 3),new Vector(0, -(8+0.2), 3),
            
           
            new Vector(0, -(4.0+0.2), 3),new Vector(0, -(4+4.0/3+0.2), 3), new Vector(0, -(4+8.0/3+0.2), 3),new Vector(0, -(8+0.2), 3),
            new Vector(-8.0*4.0*(Math.sqrt(2)-1)/3, -4.2, 3), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3, -5.53, 3), new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3,-6.66, 3),new Vector(-4*4*(Math.sqrt(2)-1)/3, -8, 3),
            new Vector(-8, 8.0*4.0*(Math.sqrt(2)-1)/3-12.2, 0), new Vector(-(4+8.0/3), (4+8.0/3)*4*(Math.sqrt(2)-1)/3-12.2, 0), new Vector(-(4+4.0/3), (4+4/3)*4*(Math.sqrt(2)-1)/3-12.2, 0), new Vector(-4, 4*4*(Math.sqrt(2)-1)/3-12.2, 0),
            new Vector(-(4+12.0/3), -12.2, 0), new Vector(-(4+8.0/3), -12.2, 0),new Vector(-(4+4.0/3), -12.2, 0), new Vector(-4, -12.2, 0),
            
            new Vector(-4, 0, 0),new Vector(-(4+4.0/3), 0, 0), new Vector(-(4+8.0/3), 0, 0),new Vector(-(4+12.0/3), 0, 0),
            new Vector(-4, -4*4*(Math.sqrt(2)-1)/3, 0),new Vector(-(4+4.0/3), -(4+4/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-(4+8.0/3), -(4+8.0/3)*4*(Math.sqrt(2)-1)/3, 0), new Vector(-8, -8.0*4.0*(Math.sqrt(2)-1)/3, 0),
            new Vector(-4*4*(Math.sqrt(2)-1)/3, -4, 0),new Vector(-(4+4.0/3)*4*(Math.sqrt(2)-1)/3, -(4+4/3), 0), new Vector(-(4+8.0/3)*4*(Math.sqrt(2)-1)/3, -(4+8.0/3), 0), new Vector(-8.0*4.0*(Math.sqrt(2)-1)/3, -8, 0),
            new Vector(0, -(4.0+0.2), 0),new Vector(0, -(4+4.0/3+0.2), 0), new Vector(0, -(4+8.0/3+0.2), 0),new Vector(0, -(8+0.2), 0),
            
           
            new Vector(0, -(4.0+0.2), 0),new Vector(0, -(4+4.0/3+0.2), 0), new Vector(0, -(4+8.0/3+0.2), 0),new Vector(0, -(8+0.2), 0),
            new Vector(8.0*4.0*(Math.sqrt(2)-1)/3, -4.4, 0), new Vector((4+8.0/3)*4*(Math.sqrt(2)-1)/3, -5.73, 0), new Vector((4+4.0/3)*4*(Math.sqrt(2)-1)/3,-6.86, 0),new Vector(4*4*(Math.sqrt(2)-1)/3, -8.2, 0),
            new Vector(8, 8.0*4.0*(Math.sqrt(2)-1)/3-12.2, 0), new Vector((4+8.0/3), (4+8.0/3)*4*(Math.sqrt(2)-1)/3-12.2, 0), new Vector((4+4.0/3), (4+4/3)*4*(Math.sqrt(2)-1)/3-12.2, 0), new Vector(4, 4*4*(Math.sqrt(2)-1)/3-12.2, 0),
            new Vector((4+12.0/3), -12.2, 0), new Vector((4+8.0/3), -12.2, 0),new Vector((4+4.0/3), -12.2, 0), new Vector(4, -12.2, 0),
            
            /*new Vector(0,0,0),new Vector(4.0/3,0,0), new Vector(8.0/3,0,0),new Vector(4.0,0,0),
            new Vector(0,0,0),new Vector(4.0/3, 4.0*Math.sqrt(3)/9,0), new Vector(8.0/3, (8.0*Math.sqrt(3)/9),0), new Vector(4, (4.0*Math.sqrt(3)/3),0),
            new Vector(0,0,0),new Vector(4.0*Math.sqrt(3)/9,4.0/3, 0), new Vector((8.0*Math.sqrt(3)/9),8.0/3, 0), new Vector((4.0*Math.sqrt(3)/3),4, 0),
            new Vector(0,0,0),new Vector(0,4.0/3,0), new Vector(0,8.0/3,0),new Vector(0,4,0),*/
           /* new Vector(0,0,0),new Vector(1,0,0), new Vector(2,0,0),new Vector(3,0,0),
            new Vector(0,3-1,0),new Vector(1,3-1,0), new Vector(2,3-1,0),new Vector(3,3-1,0),
            new Vector(6,3+1,0),new Vector(7,3+1,0), new Vector(8,3+1,0),new Vector(9,3+1,0),
            new Vector(6,6,0),new Vector(7,6,0), new Vector(8,6,0),new Vector(9,6,0),*/
            
            
           
           
            
            
            //bottom right
            /*new Vector(4+0.2+2.5, 0+2, 0.5),new Vector(5+0.2+2.5, 0+2, 0.5), new Vector(6+0.2+2.5, 0+2, 0.5),new Vector(7+0.2+2.5, 0+2, 0.5),
            new Vector(4+2.5, 4*(Math.sqrt(2)-1)/3*4+2, 0),new Vector(5+2.5,4*(Math.sqrt(2)-1)/3*5+2, 0),new Vector(6+2.5, 4*(Math.sqrt(2)-1)/3*6+2, 0),new Vector(7+2.5, 4*(Math.sqrt(2)-1)/3*7+2, 0),
            new Vector(4*(Math.sqrt(2)-1)/3*4+2.5, 4+2, 0),new Vector(4*(Math.sqrt(2)-1)/3*5+2.5, 5+2, 0),new Vector(4*(Math.sqrt(2)-1)/3*6+2.5, 6+2, 0),new Vector(4*(Math.sqrt(2)-1)/3*7+2.5, 7+2, 0),
            new Vector(0+2.5, 4+2+0.2, 0),new Vector(0+2.5, 5+2+0.2, 0),new Vector(0+2.5, 6+2+0.2, 0),new Vector(0+2.5, 7+2+0.2, 0),
            
            //right straight
            new Vector(0+2.5, 4+2+0.2, 0),new Vector(0+2.5, 5+2+0.2, 0),new Vector(0+2.5, 6+2+0.2, 0),new Vector(0+2.5, 7+2+0.2, 0),
            new Vector(1.3954, 6.3, 0),new Vector(1.1193, 7.3, 0),new Vector(0.8437, 8.3, 0), new Vector(0.5670, 9.3, 0),
            new Vector(-1.3954, 6.3, 0),new Vector(-1.1193, 7.3, 0),new Vector(-0.8437, 8.3, 0), new Vector(-0.5670, 9.3, 0),
            new Vector(0-2.5, 4+2+0.2, 0),new Vector(0-2.5, 5+2+0.2, 0),new Vector(0-2.5, 6+2+0.2, 0),new Vector(0-2.5, 7+2+0.2, 0),
            
            //top right
            new Vector(-(4+0.2+2.5), 0+2, 0),new Vector(-(5+0.2+2.5), 0+2, 0), new Vector(-(6+0.2+2.5), 0+2, 0),new Vector(-(7+0.2+2.5), 0+2, 0),
            new Vector(-(4+2.5), 4*(Math.sqrt(2)-1)/3*4+2, 0),new Vector(-(5+2.5),4*(Math.sqrt(2)-1)/3*5+2, 0),new Vector(-(6+2.5), 4*(Math.sqrt(2)-1)/3*6+2, 0),new Vector(-(7+2.5), 4*(Math.sqrt(2)-1)/3*7+2, 0),
            new Vector(-(4*(Math.sqrt(2)-1)/3*4+2.5), 4+2, 0),new Vector(-(4*(Math.sqrt(2)-1)/3*5+2.5), 5+2, 0),new Vector(-(4*(Math.sqrt(2)-1)/3*6+2.5), 6+2, 0),new Vector(-(4*(Math.sqrt(2)-1)/3*7+2.5), 7+2, 0),
            new Vector(0-2.5, 4+2+0.2, 0),new Vector(0-2.5, 5+2+0.2, 0),new Vector(0-2.5, 6+2+0.2, 0),new Vector(0-2.5, 7+2+0.2, 0),
            
            //left straight
            new Vector(0+2.5, -(4+2+0.2), 0),new Vector(0+2.5, -(5+2+0.2), 0),new Vector(0+2.5, -(6+2+0.2), 0),new Vector(0+2.5, -(7+2+0.2), 0),
            new Vector(1.3954, -6.3, 0),new Vector(1.1193, -7.3, 0),new Vector(0.8437, -8.3, 0), new Vector(0.5670, -9.3, 0),
            new Vector(-1.3954, -6.3, 0),new Vector(-1.1193, -7.3, 0),new Vector(-0.8437, -8.3, 0), new Vector(-0.5670, -9.3, 0),
            new Vector(0-2.5, -(4+2+0.2), 0),new Vector(0-2.5, -(5+2+0.2), 0),new Vector(0-2.5, -(6+2+0.2), 0),new Vector(0-2.5, -(7+2+0.2), 0),
            
            //top left
            new Vector(-(4+0.2+2.5), 0-2, 0),new Vector(-(5+0.2+2.5), 0-2, 0), new Vector(-(6+0.2+2.5), 0-2, 0),new Vector(-(7+0.2+2.5), 0-2, 0),
            new Vector(-(4+2.5), -(4*(Math.sqrt(2)-1)/3*4+2), 0),new Vector(-(5+2.5),-(4*(Math.sqrt(2)-1)/3*5+2), 0),new Vector(-(6+2.5), -(4*(Math.sqrt(2)-1)/3*6+2), 0),new Vector(-(7+2.5), -(4*(Math.sqrt(2)-1)/3*7+2), 0),
            new Vector(-(4*(Math.sqrt(2)-1)/3*4+2.5),-(4+2), 0),new Vector(-(4*(Math.sqrt(2)-1)/3*5+2.5), -(5+2), 0),new Vector(-(4*(Math.sqrt(2)-1)/3*6+2.5), -(6+2), 0),new Vector(-(4*(Math.sqrt(2)-1)/3*7+2.5), -(7+2), 0),
            new Vector(0-2.5, -(4+2+0.2), 0),new Vector(0-2.5, -(5+2+0.2), 0),new Vector(0-2.5, -(6+2+0.2), 0),new Vector(0-2.5, -(7+2+0.2), 0),
            
            //top straight
            new Vector(-(4+0.2+2.5), 0+2, 0),new Vector(-(5+0.2+2.5), 0+2, 0), new Vector(-(6+0.2+2.5), 0+2, 0),new Vector(-(7+0.2+2.5), 0+2, 0),
            new Vector(-(4.3+2.5), 0.8955,0),new Vector(-(5.3+2.5), 0.6193,0), new Vector(-(6.3+2.5), 0.3432,0), new Vector(-(7.3+2.5), 0.0670,0),
            new Vector(-(4.3+2.5), -0.8955,0),new Vector(-(5.3+2.5), -0.6193,0), new Vector(-(6.3+2.5), -0.3432,0), new Vector(-(7.3+2.5), -0.0670,0),
            new Vector(-(4+0.2+2.5), 0-2, 0),new Vector(-(5+0.2+2.5), -0-2, 0), new Vector(-(6+0.2+2.5), -0-2, 0),new Vector(-(7+0.2+2.5), -0-2, 0),
            
            
            new Vector((4+0.2+2.5), 0-2, 0),new Vector((5+0.2+2.5), 0-2, 0), new Vector((6+0.2+2.5), 0-2, 0),new Vector((7+0.2+2.5), 0-2, 0),
            new Vector((4+2.5), -(4*(Math.sqrt(2)-1)/3*4+2), 0),new Vector((5+2.5),-(4*(Math.sqrt(2)-1)/3*5+2), 0),new Vector((6+2.5), -(4*(Math.sqrt(2)-1)/3*6+2), 0),new Vector((7+2.5), -(4*(Math.sqrt(2)-1)/3*7+2), 0),
            new Vector((4*(Math.sqrt(2)-1)/3*4+2.5),-(4+2), 0),new Vector((4*(Math.sqrt(2)-1)/3*5+2.5), -(5+2), 0),new Vector((4*(Math.sqrt(2)-1)/3*6+2.5), -(6+2), 0),new Vector((4*(Math.sqrt(2)-1)/3*7+2.5), -(7+2), 0),
            new Vector(0+2.5, -(4+2+0.2), 0),new Vector(0+2.5, -(5+2+0.2), 0),new Vector(0+2.5, -(6+2+0.2), 0),new Vector(0+2.5, -(7+2+0.2), 0), */
        });

        // Initialize the terrain
        terrain = new Terrain();

    }

    /**
     * Called upon the start of the application. Primarily used to configure
     * OpenGL.
     */
    @Override
    public void initialize() {

        // Enable blending.
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Enable depth testing.
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);

        // Normalize normals.
        gl.glEnable(GL_NORMALIZE);

        // Enable textures. 
        gl.glEnable(GL_TEXTURE_2D);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        gl.glBindTexture(GL_TEXTURE_2D, 0);

        // Try to load four textures, add more if you like.
        track = loadTexture("track.jpg");
        brick = loadTexture("brick.jpg");
        head = loadTexture("head.jpg");
        torso = loadTexture("torso.jpg");
    }

    /**
     * Configures the viewing transform.
     */
    @Override
    public void setView() {
        // Select part of window.
        gl.glViewport(0, 0, gs.w, gs.h);

        // Set projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Set the perspective.       
        // Calculate the correct value for aspect_ratio
        double aspect_ratio = (float) gs.w / (float) gs.h;

        // Calculate the correct value for fovy
        double fovy = Math.toDegrees(2 * Math.atan((gs.vWidth / (2 * gs.vDist))) / aspect_ratio);

        // Set correct parameters for gluPerspective
       //glu.gluPerspective(fovy, aspect_ratio, 0.1 * gs.vDist, 10 * gs.vDist);
       glu.gluPerspective(30, aspect_ratio, 0.1 * gs.vDist, 10 * gs.vDist);

        // Set camera.
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();

        // Update the view according to the camera mode and robot of interest.
        // For camera modes 1 to 4, determine which robot to focus on.
        camera.update(gs, robots[0]);
        glu.gluLookAt(camera.eye.x(), camera.eye.y(), camera.eye.z(),
                camera.center.x(), camera.center.y(), camera.center.z(),
                camera.up.x(), camera.up.y(), camera.up.z());

        // Endable light source
        //enableLight();
    }

    /**
     * Draws the entire scene.
     */
    @Override
    public void drawScene() {
        // Background color.
        gl.glClearColor(1f, 1f, 1f, 0f);

        // Clear background.
        gl.glClear(GL_COLOR_BUFFER_BIT);

        // Clear depth buffer.
        gl.glClear(GL_DEPTH_BUFFER_BIT);

        // Set color to black.
        gl.glColor3f(0f, 0f, 0f);

        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        // Draw the axis frame.
        if (gs.showAxes) {
            drawAxisFrame();
        }
/*
        // Get the position and direction of the first robot.
        Vector a = raceTracks[gs.trackNr].getLanePoint(1, 0);
        // test track
        robots[0].position = raceTracks[gs.trackNr].getLanePoint(1, 0.25*gs.tAnim);
        robots[0].direction = raceTracks[gs.trackNr].getLaneTangent(1, 0.25*gs.tAnim);
        
        // move object
        
        //gl.glTranslatef((float) ( this.position.x), (float) ( this.position.y), (float) ( this.position.z+0.75));
       
        Vector b = raceTracks[gs.trackNr].getLanePoint(2, 0);
        robots[1].position = raceTracks[gs.trackNr].getLanePoint(2,0.21*gs.tAnim);
        robots[1].direction = raceTracks[gs.trackNr].getLaneTangent(2, 0.21*gs.tAnim);
        
        Vector c = raceTracks[gs.trackNr].getLanePoint(3, 0.5*gs.tAnim);
        robots[2].position = raceTracks[gs.trackNr].getLanePoint(3, 0.11*gs.tAnim);
        robots[2].direction = raceTracks[gs.trackNr].getLaneTangent(3, 0.11*gs.tAnim);
        
        Vector d = raceTracks[gs.trackNr].getLanePoint(4, 0.08*gs.tAnim);
        robots[3].position = raceTracks[gs.trackNr].getLanePoint(4, 0.05*gs.tAnim);
        robots[3].direction = raceTracks[gs.trackNr].getLaneTangent(4, 0.05*gs.tAnim);*/
        
        // Get the position and direction of the first robot.
        
        // test track
        Vector a = raceTracks[gs.trackNr].getLanePoint(1, 0.25);
        robots[0].position = raceTracks[gs.trackNr].getLanePoint(1, 0.25);
        robots[0].direction = raceTracks[gs.trackNr].getLaneTangent(1, 0.25);
        
        // move object
        
        //gl.glTranslatef((float) ( this.position.x), (float) ( this.position.y), (float) ( this.position.z+0.75));
       
       Vector b = raceTracks[gs.trackNr].getLanePoint(2, 0.25);
        robots[1].position = raceTracks[gs.trackNr].getLanePoint(2,0.25);
        robots[1].direction = raceTracks[gs.trackNr].getLaneTangent(2, 0.25);
        
        Vector c = raceTracks[gs.trackNr].getLanePoint(3, 0.25);
        robots[2].position = raceTracks[gs.trackNr].getLanePoint(3, 0.25);
        robots[2].direction = raceTracks[gs.trackNr].getLaneTangent(3, 0.25);
        
        Vector d = raceTracks[gs.trackNr].getLanePoint(4, 0.25);
        robots[3].position = raceTracks[gs.trackNr].getLanePoint(4, 0.25);
        robots[3].direction = raceTracks[gs.trackNr].getLaneTangent(4, 0.25);

        // O track
        
        // L track
        
        // C track
        
        // Customer track
       
        robots[0].draw(gl, glu, glut, gs.showStick, gs.tAnim);

        // Draw the second robot.
        robots[1].draw(gl, glu, glut, gs.showStick, gs.tAnim);

        // Draw the third robot.
        robots[2].draw(gl, glu, glut, gs.showStick, gs.tAnim);

        // Draw the forth robot.
        robots[3].draw(gl, glu, glut, gs.showStick, gs.tAnim);

        // Draw the race track.
        raceTracks[gs.trackNr].draw(gl, glu, glut);

        // Draw the terrain.
        terrain.draw(gl, glu, glut);

        // Unit box around origin.
        // glut.glutWireCube(1f);
/*
        // Move in x-direction.
        gl.glTranslatef(2f, 0f, 0f);
        
        // Rotate 30 degrees, around z-axis.
        gl.glRotatef(30f, 0f, 0f, 1f);
        
        // Scale in z-direction.
        gl.glScalef(1f, 1f, 2f);

        // Translated, rotated, scaled box.
       // glut.glutWireCube(1f);*/
    }

    /* This function is used to endable light source*/
    private void enableLight() {

        //Calculate the angle difference in radians
        float angleDifference = (10 * 2 * (float) Math.PI) / 360;
        // Set correct position for light source
        float lightPos[] = {(float) (gs.vDist * Math.cos(gs.theta - angleDifference) * Math.cos(gs.phi + angleDifference)+gs.cnt.x),
            (float) (gs.vDist * Math.sin(gs.theta - angleDifference) * Math.cos(gs.phi + angleDifference)+gs.cnt.y),
            (float) (gs.vDist * Math.sin(gs.phi + angleDifference)+gs.cnt.z)};

        float dif[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float amb[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float spec[] = {1.0f, 1.0f, 1.0f, 1.0f};
        // Use smooth shading
        gl.glShadeModel(GL_SMOOTH);

        // Enable lighting
        gl.glEnable(GL_LIGHTING);

        // Enable light source #0
        gl.glEnable(GL_LIGHT0);

        gl.glLightfv(GL_LIGHT0, GL_POSITION, lightPos, 0);

        gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, dif, 0);

        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, amb, 0);
        gl.glLightfv(GL_LIGHT0, GL_SPECULAR, spec, 0);
        
        gl.glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);

    }

    /**
     * Draws the x-axis (red), y-axis (green), z-axis (blue), and origin
     * (yellow).
     */
    public void drawAxisFrame() {
        // draw the origin with a yellow sphere
        gl.glColor3f(1f, 1f, 0f);
        glut.glutSolidSphere(0.05, 32, 16);

        // draw x-axis (red) with a block cone at the end
        gl.glColor3f(1f, 0f, 0f);
        gl.glBegin(GL_LINES);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(1, 0, 0);
        gl.glEnd();

        // first move to location (1,0,0) and then roated 90 and then draw the cone
        gl.glPushMatrix();
        gl.glTranslatef(1f, 0f, 0f);
        gl.glRotatef(90f, 0f, 1f, 0f);
        glut.glutSolidCone(0.05, 0.1, 32, 16);
        gl.glPopMatrix();

        // draw y-axis (green) with a block cone at the end
        gl.glColor3f(0f, 1f, 0f);
        gl.glBegin(GL_LINES);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 1, 0);
        gl.glEnd();

        // first move to location (0,1,0) and then roated -90 and then draw the cone
        gl.glPushMatrix();
        gl.glTranslatef(0f, 1f, 0f);
        gl.glRotatef(-90f, 1f, 0f, 0f);
        glut.glutSolidCone(0.05, 0.1, 32, 16);
        gl.glPopMatrix();

        // draw z-axis (blue) with a block cone at the end
        gl.glColor3f(0f, 0f, 1f);
        gl.glBegin(GL_LINES);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 0, 1);
        gl.glEnd();

        // first move to location (0,0,1) and then draw the cone
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0f, 1f);
        glut.glutSolidCone(0.05, 0.1, 32, 16);
        gl.glPopMatrix();
    }

    /**
     * Main program execution body, delegates to an instance of the RobotRace
     * implementation.
     */
    public static void main(String args[]) {
        RobotRace robotRace = new RobotRace();
        robotRace.run();
    }
}
