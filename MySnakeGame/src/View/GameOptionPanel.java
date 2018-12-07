package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Action_Controller;
import util.Global;



/** 
 * @author hp
 * @version 1.0
 * 游戏选择面板
 */
public class GameOptionPanel extends JPanel{
	//宽度是游戏面板的宽度 ，高度自己设置一个值 设置为公有的，可以直接让主面板的大小设置为游戏面板的宽，高度设置成游戏面板的高度以及选择面板的高度
	public final int height =150;
	//开始游戏按钮
	private final JButton newGameButton = new JButton();
	//停止游戏按钮
	private final JButton stopGameButton = new JButton();
	//暂停游戏按钮
	private final JButton pauseButton = new JButton();
	//复选框
	private final JCheckBox checkBox_drawGridding = new JCheckBox();
	//网线的颜色按钮
	private final JButton button_griddingColor;
	//背景颜色的按钮
	private final JButton button_backgroundColor;
	//食物颜色的按钮
	private final JButton button_foodColor;
	//蛇头颜色的按钮
	private final JButton button_headColor;
	//提示信息
	private final JTextArea  info_Area;
	//蛇身颜色的按钮
	private final JButton button_bodyColor;
	//恢复默认设置的按钮因为会改食物 背景的颜色，所以每个可以改颜色都设置了一个默认颜色按钮
	private final JButton button_default;
	/**
	 * Create the panel
	 */
	public GameOptionPanel(){
		super();
		setSize((Global.WIDTH+1) * Global.CELL_WIDTH, height);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setFocusable(false);

		final JSeparator separator = new JSeparator();
		/*
		 * setBounds的方法的使用方法  setBounds(int x,int y,int width ,int height )
		 * x,y指的是距离坐标原点的距离  width和height是新组件的宽度和高度
		 */
		separator.setBounds(140, 55, 156, 50);
		add(separator);

		button_griddingColor = new JButton();
		button_griddingColor.setBounds(85, 10, 60, 23);
		separator.add(button_griddingColor);

		button_griddingColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_griddingColor.setFocusable(false);
		button_griddingColor.setText("颜色");
		button_griddingColor.setVisible(false);

		checkBox_drawGridding.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				button_griddingColor.setVisible(checkBox_drawGridding
						.isSelected());
			}
		});
		checkBox_drawGridding.setBounds(5, 10, 78, 23);
		separator.add(checkBox_drawGridding);

		checkBox_drawGridding.setText("显示网格");
		checkBox_drawGridding.setFont(new Font("宋体", Font.PLAIN, 12));

		final JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(10, 55, 119, 33);
		add(separator_6);

		button_backgroundColor = new JButton();
		button_backgroundColor.setBounds(5, 10, 110, 23);
		separator_6.add(button_backgroundColor);

		button_backgroundColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_backgroundColor.setFocusable(false);
		button_backgroundColor.setText("设置背景颜色");

		final JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 135, 286, 39);
		add(separator_4);

		button_foodColor = new JButton();
		button_foodColor.setBounds(5, 10, 111, 23);
		separator_4.add(button_foodColor);

		button_foodColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_foodColor.setFocusable(false);
		button_foodColor.setText("设置食物颜色");


		final JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 95, 286, 39);
		add(separator_1);

		button_headColor = new JButton();
		button_headColor.setBounds(5, 10, 110, 23);
		separator_1.add(button_headColor);

		button_headColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_headColor.setFocusable(false);
		button_headColor.setText("设置蛇头颜色");

		button_bodyColor = new JButton();
		button_bodyColor.setBounds(135, 10, 113, 23);
		separator_1.add(button_bodyColor);

		button_bodyColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_bodyColor.setFocusable(false);
		button_bodyColor.setText("设置蛇身颜色");

		
		final JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(302, 10, 140, 165);
		add(separator_2);

		final JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 70, 125, 95);
		separator_2.add(separator_5);

		stopGameButton.setText("结束游戏");

		stopGameButton.setBounds(11, 10, 101, 23);
		separator_5.add(stopGameButton);
		stopGameButton.setFont(new Font("宋体", Font.PLAIN, 12));
		stopGameButton.setFocusable(false);

		pauseButton.setBounds(10, 40, 101, 23);
		separator_5.add(pauseButton);
		pauseButton.setText("暂停/继续");
		pauseButton.setFont(new Font("宋体", Font.PLAIN, 12));
		pauseButton.setFocusable(false);

		newGameButton.setFont(new Font("宋体", Font.PLAIN, 12));
		newGameButton.setBounds(11, 70, 101, 23);
		separator_5.add(newGameButton);
		newGameButton.setFocusable(false);
		newGameButton.setText("开始新游戏");
		//JSeparator分割线
		final JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 20, 286, 34);
		add(separator_3);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setText("选项：");
		label_2.setBounds(10, 10, 60, 15);
		separator_3.add(label_2);
		button_default = new JButton();
		button_default.setText("恢复默认设置");
		button_default.setFont(new Font("宋体", Font.PLAIN, 12));
		button_default.setBounds(139, 6, 137, 23);
		button_default.setFocusable(false);
		separator_3.add(button_default);
		
		info_Area=new JTextArea();
		
		final JSeparator separator_label = new JSeparator();
		separator_label.setOrientation(SwingConstants.VERTICAL);
		separator_label.setBounds(440, 10, 460, 180);
		add(separator_label);	
		info_Area.setBounds(10, 10, 460, 180);
		info_Area.setBackground(new Color(238, 238, 238));
		info_Area.setFont(new Font("宋体", 0, 18));
		info_Area.setText("游戏规则:\n方向键上下左右键控制蛇的移动   空格键表示暂停 \n开始的方法为点击开始新游戏按钮或者是按键盘上的Y    \n当暂停时按任意有关游戏的键都可以让其重新开始运动\n游戏会自动忽视当前方向的反方向"+
		"\n一定要开始后在进行更改颜色等参数"+"\n pg up键为加速键 pg down为减速键");
		//设置文本域不可编辑
		info_Area.setEditable(false);
		separator_label.add(info_Area);
		
	}
	public JCheckBox getCheckBox_drawGridding() {
		return checkBox_drawGridding;
	}


	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public JButton getStopGameButton() {
		return stopGameButton;
	}

	public JButton getButton_griddingColor() {
		return button_griddingColor;
	}

	public JButton getButton_backgroundColor() {
		return button_backgroundColor;
	}

	public JButton getButton_foodColor() {
		return button_foodColor;
	}

	public JButton getButton_headColor() {
		return button_headColor;
	}

	public JButton getButton_bodyColor() {
		return button_bodyColor;
	}


	public JButton getButton_default() {
		return button_default;
	}
	public JTextArea getInfo_label() {
		return info_Area;
	}
	

}
