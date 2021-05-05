/*
 * Une classe qui nous permet de gerer les entity du jeu
 * d'une maniere rapide
 * 
 */

package entities;

import java.awt.*;
import java.util.*;

import entities.creatures.Bateau;
import entities.creatures.Bateau2;
import project_java.Handler;

public class EntityManager {

	private Handler handler;
	private Bateau player;
	private Bateau2 player2;
	private ArrayList<Entity> entities;
	
	//Un constructeur pour le premier bateau
	public EntityManager(Handler handler, Bateau player)
	{
		this.handler = handler;
		this.player = player;
	
		entities = new ArrayList<Entity>(); 
		addEntity(player);
		
	}
	//Un constructeur pour le deuxieme 
	public EntityManager(Handler handler, Bateau2 player)
	{
		this.handler = handler;
		this.player2 = player;
	
		entities = new ArrayList<Entity>(); 
		addEntity(player);
		
	}
	
	
	//une methode tick() pour update les entity

	public void tick()
	{
		for(int i = 0; i < entities.size();i++)
		{
			Entity e = entities.get(i);
			e.tick();
		}
		
	}
	//appele la methode render de l'entity
	public void render(Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
		
	}
	//on ajoute notre bateau dans la liste
	public void addEntity(Entity e)
	{
		entities.add(e);
		
	}
	
	//getters setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Bateau getPlayer() {
		return player;
	}
	public Bateau2 getPlayer2() {
		return player2;
	}

	public void setPlayer(Bateau player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
