package anagram;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Main extends JFrame{
	
	final static String[] CHO= "¤¡¤¢¤¤¤§¤¨¤©¤±¤²¤³¤µ¤¶¤·¤¸¤¹¤º¤»¤¼¤½¤¾".split("");
	final static String[] JOONG= "¤¿¤À¤Á¤Â¤Ã¤Ä¤Å¤Æ¤Ç¤È¤É¤Ê¤Ë¤Ì¤Í¤Î¤Ï¤Ð¤Ñ¤Ò¤Ó".split("");
	final static String[] JONG= {" ", "¤¡", "¤¢", "¤£", "¤¤", "¤¥", "¤¦","¤§", "¤©", "¤ª", "¤«", "¤¬", "¤­", "¤®","¤¯", "¤°", "¤±", "¤²", "¤´", "¤µ", "¤¶","¤·", "¤¸", "¤º", "¤»", "¤¼", "¤½", "¤¾"};
	
	JLabel lb1=new JLabel("ÇÑ±¹¾î·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.",JLabel.CENTER);
	JTextField tf1=new JTextField();
	JLabel bt1=new JLabel();
	JLabel lb2=new JLabel();
	
	float dash[]= {1,3f};
	
	public Main() {
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		lb2=new JLabel("",JLabel.CENTER) {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				Graphics2D g2=(Graphics2D) g;
				g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1,dash,0));
				g2.drawLine(0,0,lb2.getWidth(),0);
				g2.drawLine(0,0,0,lb2.getHeight());
				g2.drawLine(lb2.getWidth()-1,0,lb2.getWidth()-1,lb2.getHeight()-2);
				g2.drawLine(0,lb2.getHeight()-2,lb2.getWidth(),lb2.getHeight()-2);
			}
		};
		
		lb1.setBounds(20,20,270,90);
		tf1.setBounds(30,130,425,30);
		bt1.setBounds(160,180,150,40);
		lb2.setBounds(30,240,425,190);
		
		lb2.repaint();
		lb2.setFont(new Font("¸¼Àº °íµñ",0,14));
		tf1.setBorder(new LineBorder(Color.black));
		
		lb1.setIcon(new ImageIcon(new ImageIcon("./¾È³».png").getImage().getScaledInstance(lb1.getWidth(), lb1.getHeight(), Image.SCALE_SMOOTH)));
		bt1.setIcon(new ImageIcon(new ImageIcon("./¹öÆ°0.png").getImage().getScaledInstance(bt1.getWidth(), bt1.getHeight(), Image.SCALE_SMOOTH)));
		
		add(lb1);
		add(tf1);
		add(bt1);
		add(lb2);
		
		setIconImage(new ImageIcon("./·Î°í.png").getImage());
		setVisible(true);
		setTitle("¾î±¸ÀüÃ¶");
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-500)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-500)/2,500,500);
		getContentPane().setBackground(Color.white);
		
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				bt1.setIcon(new ImageIcon(new ImageIcon("./¹öÆ°1.png").getImage().getScaledInstance(bt1.getWidth(), bt1.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				bt1.setIcon(new ImageIcon(new ImageIcon("./¹öÆ°0.png").getImage().getScaledInstance(bt1.getWidth(), bt1.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tf1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ÇÑ±ÛÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.","¿À·ù",JOptionPane.ERROR_MESSAGE);
				}else if(!tf1.getText().matches("^[°¡-ÆR]*$")) {
					JOptionPane.showMessageDialog(null, "ÇÑ±ÛÀ» ÀÔ·ÂÇÏÁö ¾Ê¾Ò°Å³ª ÃÊ¼º ¶Ç´Â ¸ðÀ½¸¸ ÀÔ·ÂÇÏ¿´½À´Ï´Ù.","¿À·ù",JOptionPane.ERROR_MESSAGE);
				}else {
					play();
				}
			}
		});
	}
	
	void play() {
		String a1="";
		
		List<Character> c= new ArrayList<Character>();
		List<Character> m= new ArrayList<Character>();
		
		String txt=tf1.getText().replaceAll(" ", "");
		for (int i = 0; i < txt.length(); i++) {
			char uniVal=txt.charAt(i);
			if(uniVal >= 0xAC00) {
				uniVal=(char)(uniVal-0xAC00);
				char cho = (char)(uniVal/28/21);
				c.add(CHO[cho].charAt(0));
				char joong = (char)((uniVal)/28%21);
				m.add(JOONG[joong].charAt(0));
				char jong = (char) (uniVal%28);
				c.add(JONG[jong].charAt(0));
				a1=lb2.getText()+CHO[cho]+JOONG[joong]+JONG[jong];
			}else {
				a1=uniVal+"";
			}
		}
		
		String a2="";
		int s1=0;
		int s2=0;
		int s3=0;
		
		while(c.size()+m.size()>0) {
			try {
				int n1=0;
				n1=(int)(Math.random()*c.size());
				while(c.get(n1).equals(' ')) {
					n1=(int)(Math.random()*c.size());
				}
				s1=Arrays.asList(CHO).indexOf(c.get(n1)+"");
				c.remove(n1);
				
				int n2=(int)(Math.random()*m.size());
				s2=Arrays.asList(JOONG).indexOf(m.get(n2)+"");
				m.remove(n2);
				
				int n3=(int)(Math.random()*c.size());
				s3=Arrays.asList(JONG).indexOf(c.get(n3)+"");
				c.remove(n3);
				
				a2=a2+(char)((s1*21*28)+(s2*28)+s3+0xAC00);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		lb2.setText(a2);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
