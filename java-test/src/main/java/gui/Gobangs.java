package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 五子棋
 * @author JEEP-711
 *
 */
@SuppressWarnings("serial")
public class Gobangs extends JFrame{
    /**
     * 构造方法
     */
    public Gobangs(){
        /**
         * 自定义组件
         */
        this.setLayout(null);
        this.setBounds(610, 140,630,630);//设置宽高
        setTitle("国际五子棋");
        setResizable(false);// 设置窗体是否可以调整大小，参数为布尔值
        /**
         * 开始按钮
         */
        JButton dy = new JButton("开始对弈");//创建开始按钮
        dy.setBounds(138,670,100,50);//设置位置
        dy.setFont(new java.awt.Font("Dialog",1, 15));//设置字体样式
        dy.setForeground(Color.white);//设置字体颜色
        dy.setBackground(new Color(8,163,219));//设置按钮颜色
        //监听器
        dy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Gobangss();//调用五子棋开始方法
            }
        });
        this.add(dy);//添加当前对弈按钮
        /**
         * 悔棋按钮
         */
        JButton hq = new JButton("悔棋");//创建悔棋按钮
        hq.setBounds(270,670,100,50);//设置位置
        hq.setFont(new java.awt.Font("Dialog",1, 15));//设置字体样式
        hq.setForeground(Color.white);//设置字体颜色
        hq.setBackground(new Color(8,163,219));//设置按钮颜色
        //监听器
        hq.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(hq);//添加当前悔棋按钮
        /**
         * 重新开始
         */
        JButton cx = new JButton("重新开始");//创建重新开始对象
        cx.setBounds(410,670,100,50);//设置位置
        cx.setFont(new java.awt.Font("Dialog",1, 15));//设置字体样式
        cx.setForeground(Color.white);//设置字体颜色
        cx.setBackground(new Color(8,163,219));//设置按钮颜色
        //监听器
        cx.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(cx!= null){
                Gobangss();//调用方法
                //}
            }
        });
        this.add(cx);//添加当前对象
        /**
         * 退出游戏
         */
        JButton tc = new JButton("退出游戏");//创建退出按钮
        tc.setBounds(545,670,100,50);//设置位置
        tc.setFont(new java.awt.Font("Dialog",1, 15));//设置字体样式
        tc.setForeground(Color.white);//设置字体颜色
        tc.setBackground(new Color(8,163,219));//设置按钮颜色
        //监听事件
        tc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);//退出游戏界面
            }
        });
        this.add(tc);//添加当前对象
        /**
         * 设置窗口图标
         */
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.getImage("src//image//0x1f3a8.png"); //设置窗口图标路径
        setIconImage(image); //换掉窗体样式
        /**
         * 设置背景图片
         *
         */
        setSize(800,800);//设置大小    board 535, 536
        String path = "src//image//qp.jpg";//设置背景图片的路径
        ImageIcon background = new ImageIcon(path); // 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        label.setBounds(0, 0, this.getWidth(), this.getHeight()); // 把标签的大小位置设置为图片刚好填充整个面板
        JPanel imagePanel = (JPanel) this.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗& 格透明
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));// 把背景图片添加到分层窗格的最底层作为背景
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 用户点击窗口关闭
        this.setVisible(true);//设置是否窗口显示
    }
    /**
     * 创建五子棋开始方法
     */
    public void Gobangss(){
        Draw dr = new Draw();
        //dr.setBounds(140,100,535,510);
        dr.setBounds(140,100,535,510);
        dr.addMouseListener(dr);
        this.setBounds(610, 140,799,799);//设置宽高
        this.add(dr);
    }
    public static void main(String[] args) {
        new Gobangs();//调用方法
        new Draw();//调用绘制五子棋类
    }
}
/**
 * 绘制五子棋盘，继承JComponet类
 * @author JEEP-711
 * 实现接口 MouseListener
 */
@SuppressWarnings("serial")
class Draw extends JComponent implements MouseListener{
    private boolean isblack = true;//标记黑棋子先走
    java.util.List<Pieces> psList = new ArrayList<Pieces>();//创建List对象,存放Pieces内容
    private int pcesX = 0;//当前X棋子的位置
    private int pcesY = 0;//当前Y棋子的位置
    private boolean isNow = false;
    //覆写方法，自定义控件
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.addMouseListener(this);//添加当前,注册自己
        /**
         * 绘制棋盘线,绘制棋子
         * 十行十列,Y轴变换,X轴不变
         */
        for(int i=-1;i<10;i++){
            g.drawLine(0, (i*50+50), 500, (i*50+50));//x轴
            g.drawLine((i*50+50),0,(i*50+50),500);//y轴
        }
        /**
         * 绘制棋子
         * 遍历集合棋子
         */
        for(Pieces p : psList){
            g.setColor(p.getColor());//取得棋子颜色，黑色
            g.fillOval(p.getX(), p.getY(), Pieces.PIECES_SIZE,Pieces.PIECES_SIZE);//设置黑棋子的x，y轴，棋子大小
        }
        iswin();//调用方
        if(isNow == true){
            //g.setFont(new Font("微软雅黑",Font.ITALIC,24));
            g.setColor(Color.WHITE);
            g.setFont(new java.awt.Font("Dialog",1, 60));//设置字体样式
            g.drawString("旗开得胜!", 110, 270);
        }
    }
    /**
     * 判断输赢
     * 东,西,南,北
     * 东北,东南,西南,西北
     */
    public void iswin(){
        /**
         * --北方向
         */
        int nanbei = 0;
        for(int nowY = pcesY-50,i=0;i<5 && nowY >=0;nowY-=50,i++){
            Pieces pieces = findPieces(pcesX,nowY);
            //System.out.println(pieces);
            //不等于空,就有棋子
            if(pieces!= null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        nanbei++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        nanbei++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }if(isblack != true){
            System.out.println("北方向黑棋子个数"+nanbei);
        }else{
            System.out.println("北方向白棋子个数"+nanbei);
        }
        if(nanbei >= 5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
        /**
         * --南方向
         */
        for(int nowY = pcesY,i=0;i<5 && nowY<=500;nowY+=50,i++){
            Pieces pieces = findPieces(pcesX,nowY);
            //System.out.println(pieces);
            //不等于空,就有棋子
            if(pieces!= null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        nanbei++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        nanbei++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }if(isblack != true){
            System.out.println("南方向黑棋子个数"+nanbei);
        }else{
            System.out.println("南方向白棋子个数"+nanbei);
        }
        if(nanbei>=5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
        /*
         *--东方向
         *
         */
        int dongxi = 0;
        for(int nowX = pcesX,i=0;i<5 && nowX<=500;nowX+=50,i++){
            Pieces pieces = findPieces(nowX,pcesY);
            //System.out.println(pieces);
            //不等于空,就有棋子
            if(pieces!= null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongxi++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongxi++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if(isblack != true){
            System.out.println("东方向黑棋子个数"+dongxi);
        }else{
            System.out.println("东方向白棋子个数"+dongxi);
        }
        /**
         * --西方向
         */
        for(int nowX = pcesX-50,i=0;i<5 && nowX>=0;nowX-=50,i++){
            Pieces pieces = findPieces(nowX,pcesY);
            //不等于空,就有棋子
            if(pieces!=null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongxi++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongxi++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if(isblack != true){
            System.out.println("西方向黑棋子个数"+dongxi);
        }else{
            System.out.println("西方向白棋子个数"+dongxi);
        }
        if(dongxi>=5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
        /**
         * -*-东北方向
         */
        int dongbeixinan= 0;
        for(int nowX = pcesX,nowY = pcesY,i=0;nowY >=0 && nowX<=500 && i< 5;nowX+=50,nowY-=50,i++){
            Pieces pieces = findPieces(nowX,nowY);
            //不等于空,就有棋子
            if(pieces!=null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongbeixinan++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongbeixinan++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if(isblack != true){
            System.out.println("东北方向黑棋子个数"+dongbeixinan);
        }else{
            System.out.println("东北方向白棋子个数"+dongbeixinan);
        }
        if(dongbeixinan>=5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
        /**
         * --西南方向
         */
        for(int nowX = pcesX-50,nowY = pcesY+50,i=0;nowY <=500 && nowX>=0 && i< 5;nowX-=50,nowY+=50,i++){
            Pieces pieces = findPieces(nowX,nowY);
            //不等于空,就有棋子
            if(pieces!=null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongbeixinan++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongbeixinan++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }if(isblack != true){
            System.out.println("西南方向黑棋子个数"+dongbeixinan);
        }else{
            System.out.println("西南方向白棋子个数"+dongbeixinan);
        }
        if(dongbeixinan>=5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
        /**
         * --东南方向
         */
        int dongnan = 0;
        for(int nowX = pcesX,nowY = pcesY,i=0;nowY <=500 && nowX<=500 && i< 5;nowX+=50,nowY+=50,i++){
            Pieces pieces = findPieces(nowX,nowY);
            //不等于空,就有棋子
            if(pieces!=null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongnan++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongnan++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }if(isblack != true){
            System.out.println("东南方向黑棋子个数"+dongnan);
        }else{
            System.out.println("东南方向白棋子个数"+dongnan);
        }
        /**
         * --西北方向
         */
        for(int nowX = pcesX-50,nowY = pcesY-50,i=0;nowY >=0 && nowX>=0 && i< 5;nowX-=50,nowY-=50,i++){
            Pieces pieces = findPieces(nowX,nowY);
            //不等于空,就有棋子
            if(pieces!=null){
                //当前棋子等于黑,判断颜色是否相相等
                if(isblack != true){
                    if(pieces.getColor() == Color.BLACK){
                        dongnan++;
                    }else{
                        break;
                    }
                }
                //当前等于白棋,判断颜色是否相等
                if(isblack != false){
                    if(pieces.getColor() == Color.WHITE){
                        dongnan++;
                    }else{
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if(isblack != true){
            System.out.println("西北方向黑棋子个数"+dongnan);
        }else{
            System.out.println("西北方向白棋子个数"+dongnan);
        }
        if(dongnan>=5){
            isNow = true;
            if(isblack != true){
                System.out.println("黑棋赢了");
            }else{
                System.out.println("白棋赢了");
            }
        }
    }
    /**
     * 找点
     */
    public Pieces findPieces(int x,int y){
        for(Pieces p:psList){
            if(p.getX() == x && p.getY() == y){
                return p;//判断坐标是否相同
            }
        }
        return null;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()-Pieces.PIECES_SIZE/2;//取得x轴坐标
        int y = e.getY()-Pieces.PIECES_SIZE/2;//取得y轴坐标
        //算出在哪一条线
        double xianX = Math.round(x/50.0);
        double xianY = Math.round(y/50.0);
        //System.out.println("第几根线"+xianY+"---"+Math.round(xianY));
        x = ((int)xianX)*50-20;
        y = ((int)xianY)*50-20;
        pcesX = x;//记录棋子X当前位置
        pcesY = y;//记录棋子Y当前位置
        boolean isHa = false;
        //去重复棋子
        for(Pieces p:psList){
            if(p.getX() == x && p.getY() == y){
                isHa = true;
            }
        }
        //避免重复棋子
        if(!isHa){
            Pieces ps = new Pieces(x,y); //创建棋子对象,并传x，y值
            /**
             * 找点
             */
            if(isblack == true){
                //黑棋子
                ps.setColor(Color.BLACK);//设置棋子颜色为黑色
                isblack = false;
            }else{
                //白棋子
                ps.setColor(Color.WHITE);//设置棋子颜色为白色
                isblack = true;
            }
            if(isNow==false){
                psList.add(ps);//把棋子保存到数组里
            }
            this.repaint();//让其重新调用,用户触发,重新绘制
        }
        //System.out.println("目前棋子数为:"+psList.size());
        //System.out.println("你点击了,X轴为:"+e.getX()+",Y轴为:"+e.getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
/**
 * 绘制棋子类
 * @author JEEP-711
 *
 */
class Pieces{
    private int x;//x轴点坐标属性
    private int y;//y轴点坐标属性
    private Color color;//棋子颜色属性
    public static final int PIECES_SIZE = 40;//棋子尺寸
    //取得X轴get方法
    public int getX() {
        return x;
    }//设置x轴set方法
    public void setX(int x) {
        this.x = x;
    }//取得y轴方法
    public int getY() {
        return y;
    }//设置y轴方法
    public void setY(int y) {
        this.y = y;
    }//取得棋子颜色方法
    public Color getColor() {
        return color;
    }//设置棋子颜色方法
    public void setColor(Color color) {
        this.color = color;
    }
    //构造器
    public Pieces(int x, int y, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public Pieces(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Pieces [x=" + x + ", y=" + y + ", color=" + color + "]";
    }
}