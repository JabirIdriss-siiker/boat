/*
 * Class des entity elle definie les methodes de bases de la class creature
 * 
 */



package entities;

import java.awt.Graphics;

import java.awt.Rectangle;


import project_java.Handler;



public abstract class Entity {
		
	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected Rectangle limites;
	
	
	
	public Entity(Handler handler,float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		limites  = new Rectangle(0,0, width, height);
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void render (Graphics g);
	
}
