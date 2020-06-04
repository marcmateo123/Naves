package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class GameObject {

	protected BufferedImage texture;//EL VALOR PROTECTED OBLIGA A QUE SOLO PUEDAN ACCEDER LOS MIEMBROS DE LA MISMA CLASE
	protected Vector2D position;
	
	public GameObject(Vector2D position, BufferedImage texture) { //EN ESTA CLASE DEFINIREMOS LA TEXTURA (IMG) Y LA POSICION
		this.position= position;
		this.texture= texture;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
}
