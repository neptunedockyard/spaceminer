package lwjgl_demo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class cam_fps {
	public Vector3f position = null;
	private float yaw = 0.0f;
	private float pitch = 0.0f;
	//private long dtime = System.currentTimeMillis();
	boolean jumping = false;
	boolean falling = false;
   
	public cam_fps(float x, float y, float z){
		position = new Vector3f(x,y-3.5f,z+20.0f);
	}
	
	public void yaw(float amount){
		yaw += amount;
	}

	public void pitch(float amount){
		if(amount <=180 && amount >= -180){
			pitch -= amount;   
		}
	}

	public void walkForward(float distance){
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw));
	}

	public void walkBackwards(float distance){
		position.x += distance * (float)Math.sin(Math.toRadians(yaw));
		position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void strafeLeft(float distance){
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}

	public void strafeRight(float distance){
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw+90));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw+90));
	}

	public void jump(float distance){
		position.y -= distance * (float)Math.sin(Math.toRadians(yaw));
	}

	public void lookThrough(){
	// Pitch up.
		if(pitch >= -50){
			GL11.glRotatef(pitch,1.0f,0.0f,0.0f);
		}else if (pitch <= -50){
			pitch+= (8) + (Math.sin(8));
	}
	// Pitch down.
		if(pitch <= 50){
			GL11.glRotatef(pitch,1.0f,0.0f,0.0f);
		}else if (pitch >= 50){
			pitch-= (8) - (Math.sin(8));
		}
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(position.x, position.y, position.z);
	}
}
