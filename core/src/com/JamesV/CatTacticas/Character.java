package com.JamesV.CatTacticas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character {

	Texture texture;
	Vector2 pos;
	int Movement = 3;
	boolean hasAttacked = false, hasMoved = false;
	
	public void init(int type, Vector2 pos) {
		
		this.pos = pos;
		
		switch(type) {
			case 0:
				texture = new Texture("player1.png");
				break;
			case 1:
				texture = new Texture("player2.png");
				break;		
		}
	}
	
	public void update() {
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, pos.x * 32, pos.y * 32);
	}
	
}
