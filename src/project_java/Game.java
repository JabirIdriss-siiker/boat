/*
 * Cette class regroupe tout les composant du jeu 
 * grace a cette class on fait la boucle pricipale du jeu 
 * dans la methode run() une methode qui appelle init() qui permet de charger les images les scene etc... et aussi la methode tick()
 */
package project_java;


import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import gfx.Assets;
import gfx.Camera;
import input.KeyManager;
import input.MouseManager;
import project_java.display.Display;
import states.CreditState;
import states.ExiteState;
import states.GameState;
import states.GameState2;
import states.MenuState;
import states.MortState;
import states.ChoixBateau;
import states.State;

public class Game implements Runnable {
	//Base
	private Display display;
	private int width, height;
	public String title;
	private boolean running;
	private Thread thread;
	
	//le rendere et les graphics
	private BufferStrategy bs;
	private Graphics g;
	
	//scenes
	public State gameState;
	public State menuState;
	public State creditState;
	public State exitState;
	public State mondeState;
	public State ChoixBateau;
	public State mortState; 
	
	// INPUT
	private KeyManager KeyManager;
	private MouseManager mouseManager;
	
	//CAMERA
	
	private Camera camera;
	
	//Handler
	private Handler handler;
	private int f;
	
	//constructeur
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title; 
		KeyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	//methode qui charge tout 
	private void init()
	{
		//fenetre
		display = new Display(title, width, height);
		//on ajoute les interfaces d'input pour notre frame et notre canvas
		display.getFrame().addKeyListener(KeyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		//on charge nos images
		Assets.init();
		
		
		handler = new Handler(this);
		camera = new Camera(handler,0, 0);
		
		//on initialise nos scenes
		gameState = new	GameState(handler);
		menuState = new MenuState(handler);
		creditState = new CreditState(handler);
		exitState = new ExiteState(handler);
		mortState = new MortState(handler);
		mondeState = new GameState2(handler);
		ChoixBateau = new ChoixBateau(handler);
		//on affiche la scene de menu au premier appelle de la boucle du jeu
		State.setState(menuState);
		
	}


	private void tick()
	{
		KeyManager.tick();
		//on appelle la methode tick de la scene qu'on utilise
		State.getState().tick();
		
	}
	
	//dessiner sur le canvas
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear le rendere
		g.clearRect(0, 0, width, height);
		//-----ZONE DE DESSIN-----//
		
		if (State.getState() != null)
		{
			State.getState().render(g);
		}
		
		g.drawString("fps : "+String.valueOf(f), 0, 10);
		
		
		//----FIN ZONE DESSIN----//
		bs.show();
		g.dispose();	
		
	}
	// game loop
	public  void run()
	{
		//initialisation des element une seul fois pour pas surcharger le buffer
		init();
		//un systeme qui permet de rafraichir la map (60 fps) 
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		int ticks = 0;
		long timer = 0;
		
		
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/ timePerTick;
			timer += now - lastTime;
			lastTime = now;
			//on appelle notre tick et render tout les 60 fps
			if (delta >= 1) 
			{
				tick();
				render();
				
				ticks++;
				delta --;
				
			}
			if(timer >= 1000000000)
			{
				
				f=ticks;
				ticks = 0;
				timer = 0;
			}
			
		}
		stop();
	}
	
	//Des getters et setters qui nous permet de recuperer et pouvoir les utilises dans le handler
	public KeyManager getKeyManager()
	{
		return KeyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	


	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	//thread debut - fin
	
	public synchronized void start()
	{
		if(running)
		{
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
			
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
