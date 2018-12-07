package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import util.Global;

/**
 * @author hp 2018/12/2-
 * @version 1.0
 *  有关游戏场景的设计
 */
public class Ground {
	/*
	 *设置地面的游戏场景为墙的周围一圈全是石头那么用一个二维数组比较方便 
	 */
	boolean[][] ground=new boolean[Global.WIDTH][Global.HEIGHT];
	public final Color default_color=new Color(0x666666);
	public Color color=default_color;//当前的石头的颜色
	public  Color default_griddingColor=new Color(0x666666);
	private Color griddingColor=default_griddingColor;
	public boolean drawGridding;
	public Ground() {
		init();
	}
	//地面的初始化函数
    public void init() {
	    for (int x = 0; x < Global.WIDTH; x++)
	         ground[x][0] = ground[x][Global.HEIGHT - 1] = true;
		for (int y = 0; y < Global.HEIGHT; y++)
			ground[0][y] = ground[Global.WIDTH - 1][y] = true;
			drawGridding=false;
	}
	//生成一个不是石头的随机点
	public Point getPoint() {
		Random random = new Random();
		int x=0,y=0;
		do {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		} while (ground[x][y]==true);

		return new Point(x,y);
	}
	public void drawMe(Graphics g){//显示地面的绘图
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++) {
				/* 画石头 */
				if (ground[x][y]) {
					g.setColor(color);
					drawRock(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,
							Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else if (drawGridding) {
					/* 画网格(如果允许) */
					g.setColor(griddingColor);
					drawGridding(g, x * Global.CELL_WIDTH, y
							* Global.CELL_HEIGHT, Global.CELL_WIDTH,
							Global.CELL_HEIGHT);
				}
			}
	}
	//画石头设成3D的原因如蛇设成3D的原因一样
	public void drawRock(Graphics g,int x,int y,int width,int height)
	{
		g.fill3DRect(x, y, width, height,true);
	}
	//画网格
	public void drawGridding(Graphics g,int x,int y,int width,int height)
	{
		g.draw3DRect(x, y, width, height,true);
	}
	public boolean isSnakeEatRock(Snake snake) {//蛇是否吃到了石头判断蛇头的点是否是石头
		return ground[snake.getHead().x][snake.getHead().y];
	}
	public Color getColor() {
		return color;
	}
	public boolean isDrawGridding() {
		return drawGridding;
	}
	public void setDrawGridding(Boolean drawGridding) {
		this.drawGridding = drawGridding;
	}
	public Color getGriddingColor() {
		return griddingColor;
	}
	public void setGriddingColor(Color griddingColor) {
		this.griddingColor = griddingColor;
	}
	public void setColor(Color color) {//石头颜色
		this.color = color;
	}
}
