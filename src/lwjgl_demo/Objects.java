package lwjgl_demo;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Objects {
	Game game;
	
	public Objects(Game game){
		this.game = game;
		System.out.println("Objects created");
	}
	
	public void floor(Texture tex, float size, float tiles){
		for(float i=1;i<=tiles;i++)
		{
			this.floor(i*size, 0, 0, size, tex);
		}
	}
	
	public void floor(float x, float y, float z, float size, Texture tex){
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		tex.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f * size, 0.0f, -1.0f * size);
		
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f * size, 0.0f, -1.0f * size);
		
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f * size, 0.0f, 1.0f * size);
		
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f * size, 0.0f, 1.0f * size);
		
		GL11.glEnd();
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}
		
	public void cube_sm(float x, float y, float z, float size, Texture tex){
		GL11.glPushMatrix();
		size /= 2.0f;
		GL11.glTranslatef(x, y, z);
		//GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		
		tex.bind();
		GL11.glBegin(GL11.GL_QUADS); 
		
		//cube top
		GL11.glTexCoord2f(0,0); GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		
		//cube bottom
		GL11.glTexCoord2f(0,0);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);
		
		//cube back
		GL11.glTexCoord2f(0,0);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		
		//cube front
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0);	GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		
		GL11.glTexCoord2f(0,0);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		
		GL11.glTexCoord2f(0,0);	GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(1,0);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(1,1);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,1);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);
		
		GL11.glEnd();
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}
	
	public void skybox(float x, float y, float z, float size, Texture tex){
		GL11.glPushMatrix();
		size /= 2.0f;
		GL11.glTranslatef(x, y, z);
		//GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		
		tex.bind();
		GL11.glBegin(GL11.GL_QUADS); 
		
		//cube top
		GL11.glTexCoord2f(0.25f,0.25f); GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		
		//cube bottom
		GL11.glTexCoord2f(0.25f,0.25f);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);
		
		//cube back
		GL11.glTexCoord2f(0.25f,0.25f);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		
		//cube front
		GL11.glTexCoord2f(0.25f,0.25f);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		
		GL11.glTexCoord2f(0.25f,0.25f);	GL11.glVertex3f(-1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f(-1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f(-1.0f * size,-1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f(-1.0f * size,-1.0f * size, 1.0f * size);
		
		GL11.glTexCoord2f(0.25f,0.25f);	GL11.glVertex3f( 1.0f * size, 1.0f * size,-1.0f * size);
		GL11.glTexCoord2f(0.25f,0.5f);	GL11.glVertex3f( 1.0f * size, 1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.5f);	GL11.glVertex3f( 1.0f * size,-1.0f * size, 1.0f * size);
		GL11.glTexCoord2f(0,0.25f);	GL11.glVertex3f( 1.0f * size,-1.0f * size,-1.0f * size);

		GL11.glEnd();
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}
	
	public void icosahedron(float x, float y, float z, float radius, int segments, Texture tex) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		tex.bind();

		float radius1 = 0;
		float radius2 = 0;
		float z1, c1, z2, c2, cda, sda;
		float angle = 0;
		float dAngle = (float) (Math.PI / segments);
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		for(int i = 0; i < segments; i++) {
			angle = (float) (Math.PI/2 - i*dAngle);
			radius1 = (float) (radius * Math.cos(angle));
			z1 = (float) (radius * Math.sin(angle));
	        c1 = (float) ((Math.PI / 2 + angle) / Math.PI);   //calculate a colour

	        angle = (float) (Math.PI/2 - (i+1)*dAngle);
	        radius2 = (float) (radius * Math.cos(angle));
	        z2 = (float) (radius * Math.sin(angle));

	        c2 = (float) ((Math.PI / 2 + angle) / Math.PI);   //calculate a colour

	        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
	        
	       for(int j = 0; j <= 2*segments; j++) // loop longitude
	       {
	            cda = (float) Math.cos(j * dAngle);
	            sda = (float) Math.sin(j * dAngle);

	            x = (float) (radius1 * cda);
	            y = (float) (radius1 * sda);
	            //GL11.glColor4f(c1, c1, c1,0.7f);
	            GL11.glTexCoord4f(c1, c1, c1,0.7f);
	            GL11.glVertex3f(x, y,z1);
	            x = (float) (radius2 * cda);
	            y = (float) (radius2 * sda);
	            //GL11.glColor4f(c2, c2,c2,0.7f);
	            GL11.glTexCoord4f(c2, c2,c2,0.7f);
	            GL11.glVertex3f(x, y,z2);
	       }
	        GL11.glEnd();
		}
		
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}
}
