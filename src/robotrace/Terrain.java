package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static javax.media.opengl.GL.GL_TRIANGLES;
import javax.media.opengl.GL2;
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
        // code goes here ...
        
        gl.glColor3f(0f, 1f, 0f); 
        gl.glBegin(GL_TRIANGLES);
            float x1 = 0;
            float x2 = 0;
            float y1 = 0;
            float y2 = 0;
            
            int NU = 50;
            int NV = 50;
            
            for (int i = -50; i< 50; i++ )
            {
 
                
                
                /*for (int j = -50; j< 50; j++ ){
                y1 = i;
                y2 = i+1;
                gl.glVertex3f(2/5*i, j, heightAt(i, j));
                gl.glVertex3f(i, j+1, heightAt(i, j+1));
                gl.glVertex3f(i+1, j+1, heightAt(i+1, j+1));
                
                gl.glVertex3f(i, j, heightAt(i, j));
                gl.glVertex3f(i+1, j, heightAt(i+1, j));
                gl.glVertex3f(i+1, j+1, heightAt(i+1, j+1));*/
                for (int j = -50; j< 50; j++ ){
                y1 = i;
                y2 = i+1;
                gl.glVertex3f(20*i/NV, 20*j/NV, heightAt(20*i/NV, 20*j/NV));
                gl.glVertex3f(20*i/NV, 20*(j+1)/NV, heightAt(20*i/NV, 20*(j+1)/NV));
                gl.glVertex3f(20*(i+1)/NV, 20*(j+1)/NV, heightAt(20*(i+1)/NV, 20*(j+1)/NV));
                
                gl.glVertex3f(20*i/NV, 20*j/NV, heightAt(20*i/NV, 20*j/NV));
                gl.glVertex3f(20*(i+1)/NV, 20*j/NV, heightAt(20*(i+1)/NV, 20*j/NV));
                gl.glVertex3f(20*(i+1)/NV, 20*(j+1)/NV, heightAt(20*(i+1)/NV, 20*(j+1)/NV));
                }

                }
            

                   
            
                  
                
        gl.glEnd();
    }

    /**
     * Computes the elevation of the terrain at (x, y).
     */
    public float heightAt(float x, float y) {
        return (float) (0.6 * Math.cos(0.3 * x+0.2 * y)+0.4 * Math.cos(x - 0.5 * y)); // <- code goes here
    }
}
