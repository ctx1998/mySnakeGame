package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import util.Global;

/**
 * @author hp 2018/12/2-
 * @version 1.0
 * 有关食物的设计
 */
public class Food extends Point{
	private Color default_Color=new Color(0xcc0033);
	private Color color=default_Color;
	//由不是石头的点生成
	public void newFood(Point p) {
		setLocation(p);
	}
	
	public boolean isFoodEated(Snake snake) {			
		return 	this.equals(snake.getHead());
	}
	public void drawMe(Graphics g)
	{
		g.setColor(color);
		g.fill3DRect(x*Global.CELL_WIDTH,y*Global.CELL_HEIGHT, Global.CELL_WIDTH,Global.CELL_HEIGHT,true);
	}
	public boolean isSnakeEatFood(Snake snake)
	{
		return 	this.equals(snake.getHead());
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
