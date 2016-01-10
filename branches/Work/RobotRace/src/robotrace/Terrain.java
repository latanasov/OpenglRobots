package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import static javax.media.opengl.GL.GL_LINEAR;
import static javax.media.opengl.GL.GL_LINES;
import static javax.media.opengl.GL.GL_RGBA;
import static javax.media.opengl.GL.GL_RGBA8;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL.GL_UNSIGNED_BYTE;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import static javax.media.opengl.GL2GL3.GL_TEXTURE_1D;
import javax.media.opengl.glu.GLU;

/**
 * Implementation of the terrain.
 */
class Terrain {
    private Object gl;
    
    /*array list contains all color*/
    private final Color[] texture;

    /**
     * Can be used to set up a display list.
     */
    public Terrain() { 
        // code goes here ...
        texture = new Color[3];

        // color for Water
        texture[0] = new Color(0, 0, 200, 255);        

        // color for Sand
        texture[1] = new Color(201, 153, 4, 255);

        // color for Grass 
        texture[2] = new Color(0, 200, 0, 255);        
        
    }

    /**
     * Draws the terrain.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        // code goes here ..
        
        // initialize 1D texture 
        initTerrainTexture(gl,texture);

            // define the segements 
            int NV = 100;
            
            // loop over x and y between -20 to 20 in order to calculate the height
            for (int i = -NV; i<= NV; i++ )
            {
                for (int j = -NV; j<= NV; j++ )
                {
                    // calculate correct coordinates for texturing 
                    float a = (float) (0.8*(heightAt(20*i/NV, 20*j/NV)+1)/2+0.1) ;
                    float b = (float) (0.8*(heightAt(20*i/NV, 20*(j+1)/NV)+1)/2+0.1);
                    float c = (float) (0.8*(heightAt(20*(i+1)/NV, 20*(j+1)/NV)+1)/2+0.1);
                    float d = (float) (0.8*(heightAt(20*(i+1)/NV, 20*j/NV)+1)/2+0.1);
                    
                    // coloring with 1D texturing map
                    gl.glBegin(GL_QUADS);
                       gl.glTexCoord1f(a);
                       gl.glVertex3f(20*i/NV, 20*j/NV, heightAt(20*i/NV, 20*j/NV));
                       gl.glTexCoord1f(b);
                       gl.glVertex3f(20*i/NV, 20*(j+1)/NV, heightAt(20*i/NV, 20*(j+1)/NV));
                       gl.glTexCoord1f(c);
                       gl.glVertex3f(20*(i+1)/NV, 20*(j+1)/NV, heightAt(20*(i+1)/NV, 20*(j+1)/NV));
                       gl.glTexCoord1f(d);
                       gl.glVertex3f(20*(i+1)/NV, 20*j/NV, heightAt(20*(i+1)/NV, 20*j/NV));
                    gl.glEnd();
                }

            }        
        gl.glEnd();

        // disable 1D texture
        gl.glDisable(GL_TEXTURE_1D);
        
        // draw three trees
        drawTress(gl,glut);
        
    }
    
    // this function is used to initialize 1D texture 
    private void initTerrainTexture(GL2 gl, Color[] colors)
    {
        // define a buffer
        ByteBuffer textLine = ByteBuffer.allocateDirect(colors.length * 4).order(ByteOrder.nativeOrder());
        
        // save all color into the buffer
        for (Color color : colors) {
            int pixel = color.getRGB();
            textLine.put((byte) ((pixel >> 16) & 0xFF)); // Red 
            textLine.put((byte) ((pixel >> 8) & 0xFF));  // Green
            textLine.put((byte) (pixel & 0xFF));         // Blue
            textLine.put((byte) ((pixel >> 24) & 0xFF)); // Alpha 
        }
        textLine.flip();
        
        
        gl.glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
        // Load 1D texture map
        gl.glTexImage1D(GL_TEXTURE_1D, 0, GL_RGBA, 3, 0, GL_RGBA, GL_UNSIGNED_BYTE, textLine);
        
        gl.glDisable(GL_TEXTURE_2D);
        gl.glEnable(GL_TEXTURE_1D);
    
    }        
    
    /**
     * Computes the elevation of the terrain at (x, y).
     */
    public float heightAt(float x, float y) {
        return (float) (0.6 * Math.cos(0.3 * x+0.2 * y)+0.4 * Math.cos(x - 0.5 * y)); // <- code goes here
    }
    
    public void drawTress(GL2 gl, GLUT glut)
    {
       // draw first tree
        gl.glPushMatrix();
           gl.glColor3f(124/255f, 75/255f, 0f);
           gl.glTranslatef(2f, 5f, 0f);
           glut.glutSolidCylinder(0.2, 1, 60, 60);
           gl.glColor3f(0f, 51/255f, 0f);
           gl.glTranslatef(0f, 0f, 1f);
           glut.glutSolidCone(1, 2, 60, 60);
           gl.glTranslatef(0f, 0f, 0.5f);
           glut.glutSolidCone(1, 2, 60, 60);
           gl.glTranslatef(0f, 0f, 0.7f);
           glut.glutSolidCone(0.8, 2, 60, 60);
        gl.glPopMatrix();
        
        // draw second tree
        gl.glPushMatrix();
           gl.glColor3f(124/255f, 75/255f, 0f);
           gl.glTranslatef(-2f, 7f, 0f);
           glut.glutSolidCylinder(0.3, 2, 60, 60);
           gl.glColor3f(12/255f, 137/255f, 24/255f);
           gl.glTranslatef(0f, 0f, 2f);
           gl.glScalef(1.25f, 1.25f, 0.75f);
           glut.glutSolidSphere(1, 60, 60);
           gl.glTranslatef(0f, 0f, 1f);
           glut.glutSolidSphere(0.8, 60, 60);
           gl.glTranslatef(0f, 0f, 0.6f);
           gl.glScalef(1/1.25f, 1/1.25f, 1/0.75f);
           glut.glutSolidSphere(0.5, 60, 60);           
        gl.glPopMatrix();
        
        // draw third tree
        gl.glPushMatrix();
           gl.glColor3f(124/255f, 75/255f, 0f);
           gl.glTranslatef(16f, -18.5f, 0.5f);
           glut.glutSolidCylinder(0.2, 1.5, 60, 60);
           gl.glColor3f(176/255f, 194/255f,58/255f);
           gl.glTranslatef(-0.5f, 0f, 1f);
           glut.glutSolidCube(1);
           gl.glColor3f(240/255f, 197/255f,102/255f);
           gl.glTranslatef(0.5f, 0.5f, 0.5f);
           glut.glutSolidCube(0.8f);
           gl.glColor3f(67/255f, 92/255f, 17/255f);
           gl.glTranslatef(-0.2f, -0.8f, 0f);
           glut.glutSolidCube(1.5f);
          
        gl.glPopMatrix();
    }        
}
