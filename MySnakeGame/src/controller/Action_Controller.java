package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import View.GameOptionPanel;
import View.GamePanel;
import entities.Ground;
import entities.Snake;
/**
 * @author hp
 * @version 1.0
   * 处理鼠标按键的事件放一放 具体一个实现了其他的也可以实现了
 */
public class Action_Controller implements ActionListener{
	GameOptionPanel gameOptionPanel=null;
	GamePanel gamePanel=null;
	Controller controller=null;
	Snake snake=null;
	/*
	 * 要根据按的按钮改变游戏面板里的蛇的信息
	 */
    public Action_Controller(GamePanel gamePanel,GameOptionPanel gameOptionPanel,Controller controller) {
		this.gamePanel=gamePanel;
		this.snake=gamePanel.snake;
		this.gameOptionPanel=gameOptionPanel;
		this.controller=controller;
		//让其失去焦点，否则无法移动
		gameOptionPanel.getCheckBox_drawGridding().setFocusable(false);
		//给所有按钮添加监听ActionListener不能在Panel上只能加在组件上
		{//设置背景颜色按钮
			gameOptionPanel.getButton_backgroundColor().addActionListener(this);
			//设置蛇身颜色
			gameOptionPanel.getButton_bodyColor().addActionListener(this);
			//设置蛇头颜色
			gameOptionPanel.getButton_headColor().addActionListener(this);
			//恢复默认设置
			gameOptionPanel.getButton_default().addActionListener(this);
			//设置食物颜色
			gameOptionPanel.getButton_foodColor().addActionListener(this);
			//停止游戏按钮
			gameOptionPanel.getStopGameButton().addActionListener(this);
			//暂停/继续按钮
			gameOptionPanel.getPauseButton().addActionListener(this);
			//开始新游戏按钮
			gameOptionPanel.getNewGameButton().addActionListener(this);
			//网格颜色的按钮
			gameOptionPanel.getButton_griddingColor().addActionListener(this);
			
		}
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//利用getActionCommand获得按钮名称
		//设置背景颜色添加监听了
		String s=e.getActionCommand();
		//设置背景颜色按钮添加了事件监听 
		if(s=="设置背景颜色")
			{		//颜色选择器
			Color backgroundColor = JColorChooser
					.showDialog(gameOptionPanel, "请选择背景的颜色",
							new Color(0xcfcfcf));
			if (backgroundColor != null)
				gamePanel.setBackgroundColor(backgroundColor);
			}
		else if(s=="设置蛇身颜色")
		{
			Color backgroundColor= JColorChooser.showDialog(gameOptionPanel,
					"请选择蛇身的颜色", new Color(0xcfcfcf));
			if(backgroundColor!=null)
				gamePanel.snake.setBodyColor(backgroundColor);
		}
		else if(s=="设置蛇头颜色")
		{
			Color backgroundColor = JColorChooser
					.showDialog(gameOptionPanel, "请选择蛇头的颜色",
							new Color(0xcfcfcf));
			if (backgroundColor != null)
				gamePanel.snake.setHeadColor(backgroundColor);
		}
		else if(s=="恢复默认设置")
		{
			gamePanel.setBackgroundColor(GamePanel.DEFAULT_BACKGROUND_COLOR);
			gameOptionPanel.getCheckBox_drawGridding().setSelected(false);
			gamePanel.ground.drawGridding=false;
			gamePanel.ground.setGriddingColor(gamePanel.ground.default_griddingColor);
			gamePanel.snake.setHeadColor(Snake.DEFAULT_HEAD_COLOR);
			gamePanel.snake.setBodyColor(Snake.DEFAULT_BODY_COLOR);
			
		}
		//设置食物颜色
		else if(s=="设置食物颜色")
		{
			Color foodColor = JColorChooser.showDialog(
					gameOptionPanel, "请选择食物的颜色", Color.DARK_GRAY);
			if (foodColor != null)
				gamePanel.food.setColor(foodColor);
		}
		//颜色有点问题
		else if(s=="颜色")
		{
			Color griddingColor = JColorChooser.showDialog(
					gameOptionPanel, "请选择网格的颜色", Color.LIGHT_GRAY);
			if (griddingColor != null) {
				gamePanel.ground.setDrawGridding(true);
				gamePanel.ground.setGriddingColor(griddingColor);
				}
		}
		else if(s=="开始新游戏")
		{
			controller.NewGame();
		}
		else if(s=="暂停/继续")
		{
			gamePanel.snake.changePause();
		}
		else if(s=="结束游戏")
		{ 
			controller.stopGame();
		}
	}
}
	



