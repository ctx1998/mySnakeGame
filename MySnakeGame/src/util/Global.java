package util;
/**
 * @author hp 2018/12/2-
 * @version 1.0
 * 一些变量的定义及初始化
 */
public class Global {

	/**
	 * 一个格子的宽度
	 */
	public static final int CELL_WIDTH=20;
	/**
	 * 一个格子的高度
	 */
	public static final int CELL_HEIGHT=20;
	/**
	 * 用格子表示的宽度, 整个游戏画面宽有多少个格子<BR>
	 */
	public static final int WIDTH=35;
	/**
	 * 用格子表示的高度, 整个游戏画面高有多少个格子)<BR>
	 */
	public static final int HEIGHT=20;
	/**
	 * 显示的像素宽度 (格子宽度 * 每一格的宽度)
	 */
	public static final int CANVAS_WIDTH=CELL_WIDTH*WIDTH;
	/**
	 * 显示的像素高度 (格子高度 * 每一格的高度)
	 */
	public static final int CANVAS_HEIGHT=CELL_HEIGHT*HEIGHT;

	/**
	 * 蛇的初始化长度
	 */
	public static final int INIT_LENGTH=3;

	/**
	 * 蛇的初始速度 (单位: 毫秒/格)<BR>
	 */
	public static final int SPEED=200;

	/**
	 * 蛇每次加速或减速的幅度 (单位: 毫秒/格)<BR>
	 */
	public static final int SPEED_STEP=20;
	/**
	 * 蛇的初始成绩 0
	 */
	public static final int SCORE=0;
	/**
	 * 蛇每次成绩的增长幅度 关卡设置为吃一次食物得5分，吃够30次则进入下一关卡
	 */
	public static final int SCORE_STEP=5;
	/**
	 * 一盘的总分为150分
	 */
	public static final int TOTAL_STEP=150;
	/**
	 * 游戏的初始关卡为
	 */
	public static final int LEVEL=1;
	/**
	 * 每次关卡增长幅度
	 */
	public static final int LEVEL_STEP=1;
}
