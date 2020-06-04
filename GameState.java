package states;

import java.awt.Graphics;

import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

public class GameState {
	
	private Player player;

	public GameState() {
		player = new Player(new Vector2D(100, 450), Assets.player); //ASIGNAMOS LAS CORDENADAS DEL VECTORDONDE SE REPRESENTARA NUESTRA NAVE
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) { //MOSTRAMOS POR PANTALLA NUESTRA NAVE
		player.draw(g);
	}
	
}
