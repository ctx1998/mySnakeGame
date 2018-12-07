package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import entities.Food;
import entities.Ground;
import entities.Snake;
import util.Global;

/**
 * 
 * @author hp 2018/12/2 -
 * @version 1.0
 * 游戏界面的显示
 */
public class GamePanel extends JPanel{
	public Ground ground;
	public Snake snake;
	public Food food;
	/**
	 * 背景颜色
	 */
	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0xcfcfcf);
	private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;
	private Label info_label=new  Label();
	public GamePanel() {
		/* 设置大小和布局 */
		this.setSize(Global.WIDTH * Global.CELL_WIDTH, Global.HEIGHT
				* Global.CELL_HEIGHT);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.setFocusable(true);
		
	}
	
	public void display(Snake snake,Food food,Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		repaint();
	}
	//清空游戏面板（擦除效果）
	public void clearDraw(Graphics g) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, Global.WIDTH*Global.CELL_WIDTH, Global.HEIGHT*Global.CELL_HEIGHT);
	}
	
	
	@Override
	public void paint(Graphics g) {
			clearDraw(g);
			//重新显示		
			if(ground != null)
			    ground.drawMe(g);	
			if(snake!=null)
				snake.drawMe(g);
			if(food !=null)
			   food.drawMe(g);
		}

	/**
	 * 得到当前的背景颜色
	 * 
	 * @return
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	/**
	 * 设置当前的背景颜色
	 * 
	 * @param backgroundColor
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
