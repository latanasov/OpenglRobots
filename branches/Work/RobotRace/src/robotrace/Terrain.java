package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import java.nio.Buffer;
import java.nio.ByteBuffer;
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

    /**
     * Can be used to set up a display list.
     */
    public Terrain() { 
        // code goes here ...
        
        
    }

    /**
     * Draws the terrain.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        // code goes here ..
        byte textLine[]=new byte[16];
        textLine = calculateTextureColor(0);
        Buffer buf=ByteBuffer.wrap(textLine);
        
        gl.glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
        //gl.glTexImage1D(GL_TEXTURE_1D, 0, GL_RGBA, 4, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
     
        
        gl.glDisable(GL_TEXTURE_2D);
        gl.glEnable(GL_TEXTURE_1D);
        gl.glTexImage1D(GL_TEXTURE_1D, 0, GL_RGBA, 4, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
        
        //gl.glBegin(GL_TRIANGLES);
            float x1 = 0;
            float x2 = 0;
            float y1 = 0;
            float y2 = 0;
            
            int NU = 50;
            int NV = 50;
            
            for (int i = -50; i< 50; i++ )
            {
                for (int j = -50; j< 50; j++ )
                {
                   //y1 = i;
                   //y2 = i+1;
                   if (heightAt(20*i/NV, 20*j/NV) <0){
                   gl.glBegin(GL_TRIANGLES);
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*i/NV, 20*j/NV, heightAt(20*i/NV, 20*j/NV));
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*i/NV, 20*(j+1)/NV, heightAt(20*i/NV, 20*(j+1)/NV));
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*(i+1)/NV, 20*(j+1)/NV, heightAt(20*(i+1)/NV, 20*(j+1)/NV));
                
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*i/NV, 20*j/NV, heightAt(20*i/NV, 20*j/NV));
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*(i+1)/NV, 20*j/NV, heightAt(20*(i+1)/NV, 20*j/NV));
                   gl.glTexCoord1f(0.1f);
                   gl.glVertex3f(20*(i+1)/NV, 20*(j+1)/NV, heightAt(20*(i+1)/NV, 20*(j+1)/NV));
                   gl.glEnd();}
                }

            }        
        //gl.glEnd();
        
        
        
        /*gl.glBegin(GL_QUADS);
           gl.glTexCoord1f(0f);
           gl.glVertex3f(0.0f,0.0f,0.0f);
           gl.glTexCoord1f(0.1f);
           gl.glVertex3f(5.0f,0.0f,0.0f);
           gl.glTexCoord1f(0.1f);
           gl.glVertex3f(5.0f,5.0f,0.0f);
           gl.glTexCoord1f(0f);
           gl.glVertex3f(0f,5.0f,0.0f);
        gl.glEnd();*/
        
        gl.glDisable(GL_TEXTURE_1D);
        
        
        
        
    }
    
    private byte[] calculateTextureColor(int color)
    {
        int k =0;
        //int water[] = {0,0,255,255};
        //int sand[] = {255,128,0,255};
        //int grass[] = {0,153,0,255};
        
        int texture[]={0,0,0,0};
        
        if (color == 0)
        {
           texture= new int[] {0,0,255,255}; // color for water
        }
        else if (color == 1)
        {
           texture= new int[] {255,128,0,255}; // color for sand
        }
        else if (color == 2)
        {
           texture= new int[] {0,153,0,255}; // color for grass
        } 
        
        byte textLine[]=new byte[16];
        
        for (k=0; k<=2; k+=2)
        {
            textLine[4*k] = (byte) texture[0];
            textLine[4*k+1] = (byte) texture[1];
            textLine[4*k+2] = (byte) texture[2];
            textLine[4*k+3] = (byte) texture[3];   
        }
        return textLine;
    } 

    /**
     * Computes the elevation of the terrain at (x, y).
     */
    public float heightAt(float x, float y) {
        return (float) (0.6 * Math.cos(0.3 * x+0.2 * y)+0.4 * Math.cos(x - 0.5 * y)); // <- code goes here
    }
}
