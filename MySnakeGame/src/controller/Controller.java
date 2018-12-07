package controller;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import util.Global;
import View.GameOptionPanel;
import View.GamePanel;
import entities.Food;
import entities.Ground;
import entities.Snake;
import listener.SnakeListener;


/** 
 * @author hp
 * @version 1.0
 * 有关蛇的按键处理及其逻辑处理
 */
public class Controller extends KeyAdapter implements SnakeListener{
	private GamePanel gamepanel=null;
	private Snake snake=null;
	private Ground ground=null;
	private Food food=null;
	private GameOptionPanel gameOptionPanel=null;
	private static 	String s = null;
	private JTextArea jaArea=null;
	public Controller(GamePanel gamepanel,Snake snake,Ground ground,Food food,JTextArea jaArea,GameOptionPanel gameOptionPanel) {
		this.gamepanel=gamepanel;
		this.food=food;
		this.snake=snake;
		this.ground=ground;
		this.jaArea=jaArea;
		this.gameOptionPanel=gameOptionPanel;
	}
	//重写按键的方法
	/**
	 * 处理按键事件<BR>
	 * 接受按键, 根据按键不同, 发出不同的指令<BR>
	 * UP: 改变蛇的移动方向为向上<BR>
	 * DOWN: 改变蛇的移动方向为向下<BR>
	 * LEFT: 改变蛇的移动方向为向左 <BR>
	 * RIGHT: 改变蛇的移动方向为向右<BR>
	 * SPACE: 暂停/继续<BR>
	 * PAGE UP: 加快蛇的移动速度<BR>
	 * PAGE DOWN: 减慢蛇的移动速度<BR>
	 * Y: 重新开始游戏
	 */
	public void keyPressed(KeyEvent e) {

		// TODO Auto-generated method stub
		/* 根据按键不同, 让蛇改变不同的方向 */
		switch (e.getKeyCode()){
		case KeyEvent.VK_Y:
			NewGame();
			break;
		/* 方向键 上 */
		case KeyEvent.VK_UP:
			if (snake.isPause()) {
				snake.changePause();
			}
		    snake.changeDirection(Snake.UP);
			break;
		/* 方向键 下 */
		case KeyEvent.VK_DOWN:
			if (snake.isPause()) {
				snake.changePause();
			}
	        snake.changeDirection(Snake.DOWN);
			break;
		/* 方向键 左 */
		case KeyEvent.VK_LEFT:
			if (snake.isPause()) {
				snake.changePause();
			}
			snake.changeDirection(Snake.LEFT);
			break;
		/* 方向键 右 */
		case KeyEvent.VK_RIGHT:
			if (snake.isPause()) {
				snake.changePause();
			}
		    snake.changeDirection(Snake.RIGHT);
			break;
		/* 回车或空格 (暂停) */
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			snake.changePause();
			break;
		/* PAGE_UP 加速 */
		case KeyEvent.VK_PAGE_UP:
		    snake.speedUp();
			break;
		/* PAGE_DOWN 减速 */
		case KeyEvent.VK_PAGE_DOWN:
			snake.speedDown();
			break;
		}
	}
	public void NewGame() {
		//初始化一个蛇并让它自动的动起来
		 snake.New();
		 food.newFood(ground.getPoint());
		 setGameInfoLabel();
		 gameOptionPanel.getStopGameButton().setEnabled(true);
		 gameOptionPanel.getPauseButton().setEnabled(true);
	}
	
	//暂停游戏
	public void pauseGame(){
		snake.setPause(true);
	}
	public void continueGame(){
		snake.setPause(false);
	}
	@Override
	public void snakeMoved() {
		// TODO Auto-generated method stub
		//每移动一次，就更新一次面板
		if(snake.isLive()) {
		gamepanel.display(snake,food,ground);
		setGameInfoLabel();
		if(food.isSnakeEatFood(snake)) {
			snake.eatFood();
			food.newFood(gamepanel.ground.getPoint());
			snake.setScore(snake.getScore()+5);//当前分加5分
		}
	   else if(ground.isSnakeEatRock(snake)) {
		   stopGame();
		}
	   else if(snake.isEatBody()) {
		   stopGame();
		}
	}
	}
	
	public void setGameInfoLabel() {
		this.jaArea.setSize(200, Global.HEIGHT * Global.CELL_HEIGHT);
		this.jaArea.setFont(new Font("宋体", Font.PLAIN, 24));
		String s="每吃一次食物加5分,\n当吃够食物30次,等\n级加一级,且速度加\n快";
		if(snake.getScore()==150)
		{
			snake.setScore(0);
			snake.setLevel(snake.getLevel()+1);
			snake.speedUp();
		}
		jaArea.setText("当前的关卡为：\n"+snake.getLevel()+ "\n当前的成绩为:\n"+snake.getScore()
		+"\n当前的速度为：\n"+snake.getSpeed()+"ms/格"+"\n"+s);
	}
	public JTextArea getGameInfo() {
		return jaArea;
	}
	public void stopGame()
	{
		snake.die();
		gameOptionPanel.getStopGameButton().setEnabled(false);
		gameOptionPanel.getPauseButton().setEnabled(false);
		JOptionPane.showMessageDialog(null, "game over", "game over",JOptionPane.ERROR_MESSAGE);
	}
	public boolean isPausingGame() {
		// TODO Auto-generated method stub
		return snake.isPause();
	}
}
