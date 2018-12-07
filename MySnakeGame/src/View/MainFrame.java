package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * 显示主页面
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import controller.Controller;
import entities.Food;
import entities.Ground;
import entities.Snake;
import controller.Action_Controller;
import util.Global;

public class MainFrame extends JFrame{
	GamePanel gamePanel=null;
	GameOptionPanel gameOptionPanel=null;
	public MainFrame() {
	  	gamePanel=new GamePanel();
		gameOptionPanel=new GameOptionPanel();
	    //新建蛇 石头 食物 对象
	    Snake snake=new Snake();
	    Ground ground =new Ground();
	    Food food=new Food();
	    //弄成文本域的形式因为要显示的有三行
	    JTextArea jaArea=new JTextArea();
	    //设为不可编辑
	    jaArea.setEditable(false);
	    //设置文本域的背景颜色
	    jaArea.setBackground(new Color(238, 238, 238));
		Controller controller=new Controller(gamePanel, snake, ground, food,jaArea,gameOptionPanel);	
	    //新建一个分割面板
	    JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane2.setLeftComponent(gamePanel);
	    //显示成绩和关卡的Label
	    splitPane2.setRightComponent(jaArea);
	    //设置的是一个面板占整个屏幕的大小
	  	splitPane2.setDividerLocation(((Global.WIDTH)* Global.CELL_WIDTH));
	  	//设置splitPanel不可移动
	  	splitPane2.setEnabled(false);
		//新建一个分割面板，让其是上下排列的
		final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//上方的面板是
		splitPane.setTopComponent(splitPane2);
	    //下方的面板是
	    splitPane.setBottomComponent(gameOptionPanel);
	    //设置的是一个面板占整个屏幕的大小
	  	splitPane.setDividerLocation(((Global.HEIGHT)* Global.CELL_HEIGHT));
	    //设置splitPanel不可移动
	  	splitPane.setEnabled(false);
	    //添加监听
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		//JPanel必须要加上这么一句，否则可能无法进行监听
		gamePanel.requestFocus(false);
		this.add(splitPane, BorderLayout.CENTER);
		//对于面板的设置
		this.setTitle("第一版贪吃蛇大作战");
		//显示其可见
		this.setVisible(true);
		//防止内存泄漏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体不可改大小
        this.setResizable(false);
		this.setSize((Global.WIDTH) * Global.CELL_WIDTH+200, ((Global.HEIGHT)
					* Global.CELL_HEIGHT)+gameOptionPanel.height+80);
		/* 让窗口居中 */
		this.setLocation(this.getToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, this.getToolkit().getScreenSize().height
				/ 2 - this.getHeight() / 2);
		
		//创建按钮的监听器对象
		Action_Controller action_Controller=new Action_Controller(gamePanel, gameOptionPanel, controller);
	}
}
