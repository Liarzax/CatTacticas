package com.JamesV.CatTacticas;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Grid {

	private static final int 
		GRID_WIDTH = 20, 
		GRID_HEIGHT = 15,
		TILE_WIDTH = 32,
		TILE_HEIGHT = 32;
	
	private Texture redTile = new Texture("redTile.png");
	private Texture greenTile = new Texture("greenTile.png");
	private Texture blueTile = new Texture("blueTile.png");
	
	LinkedList<Node> printTiles = new LinkedList<Node>();
	LinkedList<Node> pathTiles = new LinkedList<Node>();
	
	private static final byte 
		F = 0,
		W = 1;
			
	private byte[][] gridData = 
		{
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,W,W,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,F,W,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,W,F,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,W,W,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,F,F,F,F,F,F,F,F,W,F,F,F,F,W},
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
		};
	
	private Texture[] tileTextures =
		{
			new Texture("floor.png"),
			new Texture("wall.png")
		};
	
	public Grid() {
		/*printTiles = spanTile(new Vector2(6, 5), 5);
		Node n2 = printTiles.get(43);
		pathTiles = routePath(printTiles, n2.x, n2.y);*/
		
		/*for(Node n : printTiles){
			System.out.println(n.x + " : " + n.y);
		}
		System.out.println(printTiles.size());*/
	}
	
	public byte[][] getGridData() {
		return this.gridData;
	}
	
	
	public byte getGridLocation(int x, int y) {
		return this.gridData[x][y];
	}
	
	public Texture getGridTexture(int type) {
		return this.tileTextures[type];
	}
	
	public void renderGrid(SpriteBatch batch) {
		for(int x = 0; x < GRID_WIDTH ; ++x){
			for(int y = 0; y < GRID_HEIGHT; ++y){
				batch.draw(
					tileTextures[gridData[x][y]],
					x * TILE_WIDTH,
					y * TILE_HEIGHT
				);
			}
		}
		
		//printTiles - Red/Green.
		for(Node n : printTiles) {
			batch.draw(greenTile, n.x*TILE_WIDTH, n.y*TILE_HEIGHT);
		}
		for(Node n : pathTiles) {
			batch.draw(blueTile, n.x*TILE_WIDTH, n.y*TILE_HEIGHT);
		}
		
	}
	
	public Texture getRedTile() {
		return redTile;
	}
	public Texture getGreenTile() {
		return greenTile;
	}
	
	public LinkedList<Node> routePath(LinkedList<Node> printTiles, int destX, int destY) {
		
		LinkedList<Node> path = new LinkedList<Node>();
		Node temp = null;
		
		for(Node n : printTiles){
			if(n.x == destX && n.y == destY) {
				temp = n;
				break;
			}
		}
		
		if(temp != null){
			while(temp.parent != null){
				path.add(temp);
				temp = temp.parent;
			}
			path.add(temp);
		}
		
		return path;
	}
	
	public LinkedList<Node> spanTile(Vector2 spanFrom, int movement) {
		
		int pX = (int)spanFrom.x;
		int pY = (int)spanFrom.y;
		
		int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
		
		for(int x = 0; x < GRID_WIDTH; ++x){
			for(int y = 0; y < GRID_HEIGHT; ++y){
				grid[x][y] = 255;
			}
		}
		
		Stack<Node> openNodes = new Stack<Node>();
		//Stack<Node> closedNodes = new Stack<Node>();
		LinkedList<Node> closedNodes = new LinkedList<Node>();
		Node n;
		
		if(inBounds(pX, pY) && gridData[pX][pY] == F){
				grid[pX][pY] = 0;
				n = new Node();
				n.x = pX;
				n.y = pY;
				n.distance = 0;
				openNodes.push(n);
		}else{
			return closedNodes;
		}
		
		int nX, nY, distance;
		Node child;
		
		while(openNodes.size() > 0){
			n = openNodes.pop();
			
			pX = n.x;
			pY = n.y;
			
			boolean found = false;
			
			for(Node n2 : closedNodes) {
				if(pX == n2.x && pY == n2.y) {
					n2.distance = n.distance;
					found = true;
				}
			}
			
			if(found == false){
				closedNodes.push(n);
			}
			
			distance = n.distance+1;
			if(distance <= movement){
				//check up
				nX = pX;
				nY = pY+1;
				
				child = floodFill(grid, nX, nY, distance);
				if(child != null){
					child.parent = n;
					openNodes.push(child);
				}
				
				//check down
				nX = pX;
				nY = pY-1;
				
				child = floodFill(grid, nX, nY, distance);
				if(child != null){
					child.parent = n;
					openNodes.push(child);
				}
				
				//check right
				nX = pX+1;
				nY = pY;
				
				child = floodFill(grid, nX, nY, distance);
				if(child != null){
					child.parent = n;
					openNodes.push(child);
				}
				
				//check left
				nX = pX-1;
				nY = pY;
				
				child = floodFill(grid, nX, nY, distance);
				if(child != null){
					child.parent = n;
					openNodes.push(child);
				}
			}
		}
		return closedNodes;
	}
	
	private boolean inBounds(int x, int y){
		if(x > -1 && x < GRID_WIDTH){
			if(y > -1 && y < GRID_HEIGHT){
				return true;
			}
		}
		return false;
	}
	
	private Node floodFill(int[][] grid, int nX, int nY, int distance){
		if(inBounds(nX, nY)){
			if(distance < grid[nX][nY]){
				if(gridData[nX][nY] == F){
					Node n = new Node();
					n.x = nX;
					n.y = nY;
					n.distance = distance;
					grid[n.x][n.y] = distance;
					return n;
				}
			}
		}
		return null;
	}
	
}
