package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


import listener.SnakeListener;
import util.Global;



public class Snake{
	//监听器组
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	//方向上
	public static final int UP = 1;
	 // 方向下
	public static final int DOWN = -1;

	//方向左
	public static final int LEFT = 2;
	// 方向右
	public static final int RIGHT = -2;

	/* 蛇(多个节点) 蛇可以看成一个像素点的集合所以用LinkedList来表示因为这个获得蛇头和蛇尾容易*/
	private LinkedList<Point> body = new LinkedList<Point>();

	/* 上一次的移动方向 */
	private int oldDirection;

	/* 下一步的方向(有效方向) */
	private int newDirection;

	/* 临时存放蛇头的坐标 */
	private Point head;

	/* 临时存放蛇尾巴的坐标 */
	private Point tail;

	/* 移动速度 */
	private int speed;
	/*初始化成绩*/
	private int score;
	
	/*
	 * 初始化关卡
	 */
	private int level;

	/* 生命, 是否活着 */
	private boolean live;

	/* 是否暂停 */
	private boolean pause;
	/*游戏是否在运行 */
	public boolean running;
	//蛇头的默认颜色
	public static final Color DEFAULT_HEAD_COLOR = new Color(0xcc0033);
	/* 蛇头的颜色 */
	private Color headColor = DEFAULT_HEAD_COLOR;

	public static final Color DEFAULT_BODY_COLOR = new Color(0xcc0033);
	/* 蛇身体的颜色 */
	private Color bodyColor = DEFAULT_BODY_COLOR;
	//没有判断蛇头是否到边界了，因为设置的是边界都是墙
	public void move() {//蛇的移动方法蛇的移动可以理解加头去尾
		/*
		 *当方向并不是相反的时
		 */
		if (oldDirection + newDirection != 0) 
			oldDirection = newDirection;
		/* getLocation 将返回一个新的Point */
		/* tail把尾巴坐标保存下来, 吃到食物时再加上 */
		tail=getTail().getLocation();
		/* 根据蛇头的坐标再 上下左右 */
		head=getHead().getLocation();
		/* 根据方向让蛇移动 */
		switch (oldDirection) {
		case UP:
			head.y--;
			break;
		case DOWN:
			head.y++;
			break;
		case LEFT:
			head.x--;
			break;
		case RIGHT:
			head.x++;
			break;
		}
		/* 添加到头上去 */
		body.addFirst(head);
	   
	}
	public void changeDirection(int direction) {//改变方向的的函数
		this.newDirection=direction;
	}
	public void eatFood() {//蛇吃食物的方法  可以理解为加尾巴，尾巴的位置是上一次移动的点，所以移动的时候要临时保存尾巴的点
		body.addLast(tail);
	}
	public void init() {
		body.clear();
		/* 初始化位置在中间 */
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		for (int i = 0; i < Global.INIT_LENGTH; i++)
			this.body.addFirst(new Point(x, y--));
		/* 设置默认方向为向右 */
		oldDirection = newDirection = UP;
		/* 初始化速度 */
		speed = Global.SPEED;
		/* 初始化等级 */
		level=Global.LEVEL;
		/* 初始化成绩*/
		score=Global.SCORE;
		/* 初始化生命和暂停状态 */
		live = true;
		pause = false;
		running=true;
	}
	public void drawMe(Graphics g) {//画蛇的方法
		//获得蛇的点的坐标然后进行绘制可以先把蛇的结点都先画出来再单独改蛇头的颜色
		for (Point p : body) {
			/* 画蛇身体 */
			g.setColor(bodyColor);
			//画一个矩形的效果并不好，所以画成三维立体的类型让其有边框
			g.fill3DRect( p.x * Global.CELL_WIDTH, p.y * Global.CELL_HEIGHT,
					Global.CELL_WIDTH, Global.CELL_HEIGHT,true);
		}
		/* 画蛇头 */
		g.setColor(headColor);
		g.fill3DRect(getHead().x * Global.CELL_WIDTH, getHead().y
			* Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT,true);
		
	}
	public boolean isEatBody() {//头结点与身体结点一个个进行比较
		/* 要把蛇头排除, body.get(0) 是蛇头 */
		for (int i = 1; i < body.size(); i++)
			if (getHead().equals(body.get(i)))
				return true;
		return false;
	}
	public Point getHead() {//获得蛇头的位置
		Point p=body.getFirst();
		return p;
	}
	public  Point getTail() {//获得蛇尾的位置
		Point p=body.removeLast();
		return p;
	}
	//控制蛇移动的线程内部类
	private class SnakeDriver implements Runnable {
		public void run() {
			while(live) {
				if(pause==false) {
					move();			
					for(SnakeListener l : listeners)
						l.snakeMoved();
				}
				try {	
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 让蛇开始运动<BR>
	 * 开启一个新的线程
	 */
	public void begin() {
		new Thread(new SnakeDriver()).start();
	}
	
	public void New() {
		init();
		begin();
	}
	
	//添加监听器
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
		  listeners.add(l);
		}
	}
	//对于一些量的设置
	public void die()
	{
		live=false;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public Color getHeadColor() {
		return headColor;
	}
	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}
	public Color getBodyColor() {
		return bodyColor;
	}
	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}
	public static Color getDefaultHeadColor() {
		return DEFAULT_HEAD_COLOR;
	}
	public static Color getDefaultBodyColor() {
		return DEFAULT_BODY_COLOR;
	}
	//增加蛇移动的速度 
	public void speedUp()
	{
		speed=speed-Global.SPEED_STEP;
	}
	//减小蛇移动的速度
	public void speedDown()
	{
		speed=speed+Global.SPEED_STEP;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * 更改暂停状态<BR>
	 * 若是暂停状态, 则继续移动<BR>
	 * 若正在移动, 则暂停
	 */
	public void changePause() {
		pause = !pause;
	}
	
}
