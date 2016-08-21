package lwjgl_demo;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
//imports for audio
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
//imports for fonts
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class Game {

	final static int width = 800, height = 600;
	final static int frameRate = 60;
	boolean[] keys = new boolean[256];
	Camera camera;
	Objects objects;
	Texture texWhite;
	Texture texFloor;
	Texture texBrick;
	Texture texCoal;
	Texture texGrass;
	Texture texStar;
	SimplexNoise simplex;
	double time = 0.0f;
	float dt = 0.0f;
	double lastTime = 0.0f;
	objLoader objLoader;
	
	//audio related
	private Audio oggStream;
	
	//2d text
	TrueTypeFont font;
	
	
	public static void main(String[] args) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width,height));
		Display.setTitle("Space Miners v0.1");
		Display.create();
		Game game = new Game();
		game.init3D();
		game.initSound();
		System.out.println("Game started");
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			game.render();
			game.update();
			Display.update();
			Display.sync(frameRate);
		}
		Display.destroy();
		AL.destroy();
		System.exit(0);
	}
	
	public Game(){
		camera = new Camera(this, 0.0f, 0.0f, 0.0f);
		objects = new Objects(this);
		objLoader = new objLoader(this);
		simplex = new SimplexNoise(this);
		loadTextures();
		loadSounds();
		loadFonts();
	}
	
	private void loadTextures(){
		try{
			texWhite = 	TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/Textures/white.jpg"));
			texFloor = 	TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/Textures/floor.jpg"));
			texBrick = 	TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Textures/brick.png"));
			texCoal =  	TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Textures/coal_block.png"));
			texGrass = 	TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Textures/cobblestone_mossy.png"));
			texStar = 	TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Textures/starfield1.png"));
			//texStar = 	TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/Textures/starfield2.jpg"));
			System.out.println("Textures loaded");
		}catch(Exception e){
			System.err.println("Failed to load texture: ");
			e.printStackTrace();
		}
	}
	
	private void loadSounds(){
		try{
			oggStream = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("res/Audio/infini1.ogg"));
		}catch(Exception e){
			System.err.println("Faild to load sounds: ");
			e.printStackTrace();
		}
	}
	
	private void loadFonts(){
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public void render(){
		clearScreen();
		camera.translatePostion();
		
		//draw starfield
		//objects.icosahedron(0.0f, 0.0f, 0.0f, 5.0f, 30, texStar);
		objects.skybox(0.0f, 0.0f, 0.0f, 1000.0f, texStar);
		
		//write hud text to screen
		//writeHUD();
		
		//Render a textured rectangular floor at 0,0 to 10,10
		//draw floor
		objects.floor(texFloor, 0.1f, 20);
		//draw test cube
		//objects.testCube(texWhite);
		//draw cube at location
		objects.cube_sm(0.0f, 0.1f, 0.0f, 0.1f, texBrick);
		objects.cube_sm(0.0f, 0.3f, 0.0f, 0.1f, texCoal);
	}
	
	public void update(){
		mapKeys();
		mapMouse();
		camera.update(getTime());
		SoundStore.get().poll(0);
	}
	
	private void mapKeys(){
		//Update keys
		for(int i=0;i<keys.length;i++){
			keys[i] = Keyboard.isKeyDown(i);
		}
	}
	
	private void mapMouse(){
		//update mouse
		int x = Mouse.getX();
		int y = Mouse.getY();
		boolean leftMouse = Mouse.isButtonDown(0);
		boolean rightMouse = Mouse.isButtonDown(1);
		
		if(leftMouse)
			//objects.cube(0.0f, 0.6f, 0.0f, 0.1f, texGrass);
			objects.icosahedron(0.0f, 0.0f, 0.0f, 0.25f, 30, texStar);
		if(rightMouse)
			System.out.println("X:Y " + x + ":" + y);
		
	}
	
	
	public void init3D(){
		//Start 3D Stuff
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		GLU.gluPerspective((float) 100, width / height, 0.001f, 1000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		GL11.glClearDepth(1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		
		//these below allow alpha blending
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
	}
	
	public void clearScreen(){
		//Clear the screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
	}
	
	public float getTime(){
		time = Sys.getTime();
		dt = (float)(time - lastTime)/1000.0f;
		lastTime = time;
		Display.setTitle("fps: "+ 1/dt);
		return dt;
	}
	
	public void initSound(){
		//start playing ogg stream
		oggStream.playAsMusic(1.0f, 1.0f, true);
	}
	
	/*
	 * Currently hud writing doesn't work at all, since the 2d canvas isn't static
	 * the text actually stays in one place while the camera moves
	 * TODO: change camera style, it's stupid
	 */
	public void writeHUD(){
		font.drawString(100, 50, "test");
	}
	
	public void writeHUD(float value){
		String fps = Float.toString(value);
		font.drawString(100, 50, fps);
	}
}
