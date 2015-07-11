package com.JamesV.CatTacticas;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class World {
	
	Grid grid;
	Character player1 = new Character();
	Character player2 = new Character();
	
	
	
	public void init() {
		grid = new Grid();
		player1.init(0, new Vector2(1, 1));
		player2.init(1, new Vector2(6, 5));
		
		grid.printTiles = grid.spanTile(player2.pos, 5);
		//v-- used to find node to route to (Max Square 49)
		Node n2 = grid.printTiles.get(23);
		grid.pathTiles = grid.routePath(grid.printTiles, n2.x, n2.y);
		
		// Move Player2
		//player2.pos.x = n2.x;
		//player2.pos.y = n2.y;
				
	}
	
	public void update() {
		
	}
	
	
	public void render(SpriteBatch batch) {
		grid.renderGrid(batch);
		
		player1.render(batch);
		player2.render(batch);
	}
	

}
