/**
 * 
 */
package package1;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.css.ElementCSSInlineStyle;

import oracle.net.aso.i;
import oracle.net.aso.j;

import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.State;
import java.util.EventListener;
import java.sql.*;

/**
 * @author pc
 *
 */
public class bank extends JFrame implements ActionListener,ChangeListener{
 
	/**
	 * 
	 */
	//数据库连接参数
	Connection con =null;
	PreparedStatement pre = null;
	ResultSet result = null;
	
	// 界面元素定义
	private JTabbedPane tab;
	private JPanel jp[], jp1[], jp2[], jp3[], jp4[], jp5[], jp6[]; 
	private JTextField jtf1[], jtf2[],jtf3[],jtf4[],jtf5[],jtf6[];
	private JTextField ujtf1[], ujtf2[],ujtf3[],ujtf4[];
	private JLabel jl1[], jl2[], jl3[], jl4[], jl5[], jl6[];
	private JLabel ujl1[], ujl2[], ujl3[], ujl4[];
	private JButton jb,jb2,jb3,jb4,jb5,jb6,jb7;
	private JLabel alert,alert2,alert3,alert4,alert5,alert6;
	private JScrollPane js1, js2, js3, js4, js5, js6, js7,js8;
	private JTable jt1, jt2, jt3, jt4, jt5, jt6,jt7,jt8;
	private JComboBox jcb,jcb2,jcb3,jcb4,jcb5,jcb6,jcb7, type;
	
	private int index,index2,index3,index4,index5,index6,index7, nowpanel, tindex, loanindex;
	
	String[][] tcount1 = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, }; // 初始表
	String[][] tcount2 = {{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""}};
	String[][] tcount3 = {{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},{"","","","","","","",""},};
	String[][] tcount4 = {{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""}, };
	String[][] tcount5 = {{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""}};
	String[][] tcount6 = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
	String[][] tcount7 = {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};
	String[][] tcount8 = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, }; // 初始表
	
	private String actype[] = {"储蓄账户 ","支票账户"};
	private String operation[] = {"新增          ", "删除          ", "更新          ", "查找         "};
	private String loanop[] = {"新增贷款","删除贷款","查询贷款","查询贷款发放","查询贷款人"};
	private String depart1[] = {"储蓄    ","贷款    "};
	private String depart2[] = {"年     ","季   ","月    "};
	private String text1[] = {"支行名称", "支行城市", "支行资金 "};
	private String text3[] = {"身份证号", "姓名", "联系电话", "家庭住址", "联系人姓名", "联系人手机", "联系人Email", "与客户关系"};
	private String text2[] = {"身份证号", "姓名", "联系电话", "家庭住址", "聘用时间", "经理身份证号"};
	private String text4[] = {"开户支行", "账户号", "持有人", "负责人","余额", "开户日期", "最近访问日期", "利率", "货币类型","透支额"};
	private String text5[] = {"贷款号", "发放支行", "贷款金额", "贷款负责人","贷款状态","剩余金额"};
	private String text51[] = {"贷款号","发放支行","贷款金额","贷款负责人","贷款人1","贷款人2","贷款人3"};
	private String text6[] = {"贷款号","发放时间","发放金额"};
	private String text7[] = {"贷款号","贷款客户"};
	private String text8[] = {"时间","业务数","业务金额"};
	
	public bank() {
		// TODO Auto-generated constructor stub

		jp = new JPanel[6];
		
		//  支行管理界面
		jcb = new JComboBox(operation);
		jcb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index = jcb.getSelectedIndex();

			}
		});
		jb = new JButton("确定");
		jb.addActionListener(this);
	    alert = new JLabel("");
	    //tips = new JLabel("");
		jp1 = new JPanel[4];  //  2/2
		jl1 = new JLabel[3];
		jtf1 = new JTextField[3];
		ujl1 = new JLabel[3];
		ujtf1 = new JTextField[3];
		
		for(int i=0;i<3;i++) {
			jl1[i] = new JLabel(text1[i]);
			jtf1[i] = new JTextField();
			ujl1[i] = new JLabel("  修改后");
			ujtf1[i] = new JTextField();
		}
		
		jp1[0] = new JPanel();
		jp1[0].add(jcb);
		GridLayout glayout1 = new GridLayout(3, 4);
		glayout1.setVgap(5);
		jp1[1] = new JPanel(glayout1);
		for(int i=0; i<3; i++)
		{
			jp1[1].add(jl1[i]);
			jp1[1].add(jtf1[i]);
			jp1[1].add(ujl1[i]);
			jp1[1].add(ujtf1[i]);
		}
		jp1[2] = new JPanel(new BorderLayout());
		jp1[2].add("North", jp1[0]);
		jp1[2].add("Center", jp1[1]);
		jp1[2].add("South", jb);
		
		jt1 = new JTable(tcount1, text1);
		js1 = new JScrollPane(jt1);
		jt1.setFillsViewportHeight(true);

		
		jp1[3] = new JPanel();
		jp1[3].add(js1);
		jp1[0].add(alert);
		alert.setForeground(Color.RED);
		
		jp[0] = new JPanel();
		jp[0].add(jp1[2]);
		jp[0].add(jp1[3]);
		
		
		//员工管理界面
		jcb2 = new JComboBox(operation);
		jcb2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index2 = jcb2.getSelectedIndex();

			}
		});
		jb2 = new JButton("确定");
		jb2.addActionListener(this);
	    alert2 = new JLabel("");
		jp2 = new JPanel[4];  //  2/2
		jl2 = new JLabel[6];
		jtf2 = new JTextField[6];
		ujl2 = new JLabel[6];
		ujtf2 = new JTextField[6];
		
		for(int i=0;i<6;i++) {
			jl2[i] = new JLabel(text2[i]);
			jtf2[i] = new JTextField();
			ujl2[i] = new JLabel("  修改后");
			ujtf2[i] = new JTextField();
		}
		
		jp2[0] = new JPanel();
		jp2[0].add(jcb2);
		GridLayout glayout2 = new GridLayout(6, 4);
		glayout2.setVgap(5);
		jp2[1] = new JPanel(glayout2);
		for(int i=0; i<6; i++)
		{
			jp2[1].add(jl2[i]);
			jp2[1].add(jtf2[i]);
			jp2[1].add(ujl2[i]);
			jp2[1].add(ujtf2[i]);
		}
		jp2[2] = new JPanel(new BorderLayout());
		jp2[2].add("North", jp2[0]);
		jp2[2].add("Center", jp2[1]);
		jp2[2].add("South", jb2);
		
		jt2 = new JTable(tcount2, text2);
		js2 = new JScrollPane(jt2);
		jt2.setFillsViewportHeight(true);

		
		jp2[3] = new JPanel();
		jp2[3].add(js2);
		jp2[0].add(alert2);
		alert2.setForeground(Color.RED);
		
		
		jp[1] = new JPanel();
		jp[1].add(jp2[2]);
		jp[1].add(jp2[3]);
		
		//客户管理界面
		jcb3 = new JComboBox(operation);
		jcb3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index3 = jcb3.getSelectedIndex();

			}
		});
		jb3 = new JButton("确定");
		jb3.addActionListener(this);
	    alert3 = new JLabel("");
		jp3 = new JPanel[4];  //  2/2
		jl3 = new JLabel[8];
		jtf3 = new JTextField[8];
		ujl3 = new JLabel[8];
		ujtf3 = new JTextField[8];
		
		for(int i=0;i<8;i++) {
			jl3[i] = new JLabel(text3[i]);
			jtf3[i] = new JTextField();
			ujl3[i] = new JLabel("  修改后");
			ujtf3[i] = new JTextField();
		}
		
		jp3[0] = new JPanel();
		jp3[0].add(jcb3);
		GridLayout glayout3 = new GridLayout(8, 4);
		glayout3.setVgap(5);
		jp3[1] = new JPanel(glayout3);
		for(int i=0; i<8; i++)
		{
			jp3[1].add(jl3[i]);
			jp3[1].add(jtf3[i]);
			jp3[1].add(ujl3[i]);
			jp3[1].add(ujtf3[i]);
		}
		jp3[2] = new JPanel(new BorderLayout());
		jp3[2].add("North", jp3[0]);
		jp3[2].add("Center", jp3[1]);
		jp3[2].add("South", jb3);
		
		jt3 = new JTable(tcount3, text3);
		js3 = new JScrollPane(jt3);
		jt3.setFillsViewportHeight(true);

		
		jp3[3] = new JPanel();
		jp3[3].add(js3);
		jp3[0].add(alert3);
		alert3.setForeground(Color.RED);
		
		
		jp[2] = new JPanel();
		jp[2].add(jp3[2]);
		jp[2].add(jp3[3]);
		
		// 账户管理界面
		jcb4 = new JComboBox(operation);
		jcb4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index4 = jcb4.getSelectedIndex();

			}
		});
		type = new JComboBox(actype);
		type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				tindex = type.getSelectedIndex();
			}
		});
		jb4 = new JButton("确定");
		jb4.addActionListener(this);
		
	    alert4 = new JLabel("");
		jp4 = new JPanel[4];  //  2/2
		jl4 = new JLabel[10];
		jtf4 = new JTextField[10];
		ujl4 = new JLabel[10];
		ujtf4 = new JTextField[10];
		
		for(int i=0;i<10;i++) {
			jl4[i] = new JLabel(text4[i]);
			jtf4[i] = new JTextField();
			ujl4[i] = new JLabel("  修改后");
			ujtf4[i] = new JTextField();
		}
		
		jp4[0] = new JPanel();
		jp4[0].add(jcb4);
		jp4[0].add(type);
		GridLayout glayout4 = new GridLayout(10, 4);
		glayout4.setVgap(5);
		jp4[1] = new JPanel(glayout4);
		for(int i=0; i<10; i++)
		{
			jp4[1].add(jl4[i]);
			jp4[1].add(jtf4[i]);
			jp4[1].add(ujl4[i]);
			jp4[1].add(ujtf4[i]);
		}
		jp4[2] = new JPanel(new BorderLayout());
		jp4[2].add("North", jp4[0]);
		jp4[2].add("Center", jp4[1]);
		jp4[2].add("South", jb4);
		
		jt4 = new JTable(tcount4, text4);
		js4 = new JScrollPane(jt4);
		jt4.setFillsViewportHeight(true);

		jp4[3] = new JPanel();
		jp4[3].add(js4);
		jp4[0].add(alert4);
		alert4.setForeground(Color.RED);
		
		jp[3] = new JPanel();
		jp[3].add(jp4[2]);
		jp[3].add(jp4[3]);
		
		
		// 贷款管理界面
		jcb5 = new JComboBox(loanop);
		jcb5.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index5 = jcb5.getSelectedIndex();

			}
		});
		jb5 = new JButton("确定");
		jb5.addActionListener(this);
		jb6 = new JButton("发放");
		jb6.addActionListener(this);
		
	    alert5 = new JLabel("");
		jp5 = new JPanel[5];  //  2/3
		jl5 = new JLabel[7];
		jtf5 = new JTextField[7];
		jl6 = new JLabel[3];
		jtf6 = new JTextField[3];

		
		for(int i=0;i<7;i++) {
			jl5[i] = new JLabel(text51[i]);
			jtf5[i] = new JTextField();
		}
		for(int i=0;i<3;i++) {
			jl6[i] = new JLabel(text6[i]);
			jtf6[i] = new JTextField();
		}
		// North
		jp5[0] = new JPanel();
		jp5[0].add(jcb5);
		jp5[0].add(alert5);
		alert5.setForeground(Color.RED);
		// center
		GridLayout glayout5 = new GridLayout(7, 2);
		glayout5.setVgap(5);
		jp5[1] = new JPanel(glayout5);
		for(int i=0; i<7; i++)
		{
			jp5[1].add(jl5[i]);
			jp5[1].add(jtf5[i]);
		}
		// first panel
		jp5[2] = new JPanel(new BorderLayout());
		jp5[2].add("North", jp5[0]);
		jp5[2].add("Center", jp5[1]);
		jp5[2].add("South", jb5);
		
		//second panel
		jp5[4] = new JPanel(new BorderLayout());
		GridLayout gLayout6 = new GridLayout(3, 2);
		gLayout6.setVgap(5);
		jp5[3] = new JPanel(gLayout6);
		for(int i=0;i<3;i++) {
			jp5[3].add(jl6[i]);
			jp5[3].add(jtf6[i]);
		}
		jp5[4].add("Center", jp5[3]);
		jp5[4].add("South", jb6);
		
		// 三个查询结果表
		jt5 = new JTable(tcount5, text5);
		js5 = new JScrollPane(jt5);
		jt5.setFillsViewportHeight(true);
		
		jt6 = new JTable(tcount6, text6);
		js6 = new JScrollPane(jt6);
		jt6.setFillsViewportHeight(true);
		
		jt7 = new JTable(tcount7, text7);
		js7 = new JScrollPane(jt7);
		jt7.setFillsViewportHeight(true);

		
		jp[4] = new JPanel(null);
		jp[4].add(jp5[2]);
		jp[4].add(jp5[4]);
		jp[4].add(js5);
		jp[4].add(js6);
		jp[4].add(js7);
		
		jp5[2].setBounds(90, 5, 140, 200);
		jp5[4].setBounds(280, 20, 140, 100);
		js5.setBounds(0, 210, 480, 100);
		js6.setBounds(0, 320, 480, 100);
		js7.setBounds(0, 440, 480, 100);

		// 业务统计界面
		jb7 = new JButton("开始统计");
		jb7.addActionListener(this);
		
		jcb6 = new JComboBox(depart1);
		jcb6.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index6 = jcb6.getSelectedIndex();
			}
		});
		jcb7 = new JComboBox(depart2);
		jcb7.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				index7 = jcb7.getSelectedIndex();
			}
		});
		jt8 = new JTable(tcount8, text8);
		js8 = new JScrollPane(jt8);
		jt8.setFillsViewportHeight(true);
		
		jp[5] = new JPanel();
		jp[5].add(jcb6);
		jp[5].add(jcb7);
		jp[5].add(jb7);
		jp[5].add(js8);
		
		// 选项卡
		tab = new JTabbedPane();
		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				nowpanel = tab.getSelectedIndex();
			}
		});
		
		tab.add("支行管理", jp[0]);
		tab.add("员工管理", jp[1]);
		tab.add("客户管理", jp[2]);
		tab.add("账户管理", jp[3]);
		tab.add("贷款管理", jp[4]);
		tab.add("业务统计", jp[5]);
		
		this.add(tab);
		this.setSize(480, 620);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new bank();
	}

	void Init() {
		alert.setText("");
		alert2.setText("");
		alert3.setText("");
		alert4.setText("");
		alert5.setText("");
//		alert6.setText("");
		for(int i=0;i<10;i++) {
			for(int j=0;j<3;j++)
				tcount1[i][j] = "";
			for(int j=0;j<6;j++)
				tcount2[i][j] = "";
			for(int j=0;j<8;j++)
				tcount3 [i][j] = "";
			for(int j=0; j<10; j++)
				tcount4[i][j] = "";
			for(int j=0;j<6;j++)
				tcount5[i][j] = "";
			for(int j=0;j<3;j++)
				tcount6[i][j] = "";
			for(int j=0;j<2;j++)
				tcount7[i][j] = "";
			for(int j=0;j<3;j++)
				tcount8[i][j] ="";
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("tian"+ index2+ "tian" + nowpanel);
		Init();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:"+"thin:@localhost:1521:orcl";
			String user = "system";
			String password = "db12345";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("连接OK"); 
			
			switch(nowpanel) {
			case 0:
				String b_name = jtf1[0].getText();
				String city = jtf1[1].getText();
				String money = jtf1[2].getText();
				String ub_name = ujtf1[0].getText();
				String ucity = ujtf1[1].getText();
				String umoney = ujtf1[2].getText();
				
				if(index == 0) {
					if(b_name.length() == 0 || city.length() == 0) {
						alert.setText("input error");
						return;
					}
					if(money.length() == 0) money = "0";
					String sql = "insert into branch values('"+b_name+"','"+city+"',"+money+")";
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i==0) alert.setText("insert fault");
					else alert.setText("OK");
				}
				else if(index == 1) {
					if(b_name.length() == 0) {
						alert.setText("input error");
						return;
					}
					String sql = "select * from loan where b_name = '" + b_name + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert.setText("delete error");	return;	}
					sql = "select * from save_account where b_name = '" + b_name + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert.setText("delete error");	return;	}
					sql = "select * from check_account where b_name = '" + b_name + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert.setText("delete error");	return;	}
					
					sql = "delete from branch where b_name = '" + b_name + "'";
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i == 0) alert.setText("delete fault");
					else alert.setText("OK");
				}
				else if(index == 2) {
					if(b_name.length() == 0) {
						alert.setText("input error");
						return;
					}
					if(umoney.length()!=0) {
						String sql = "update branch set money = " + umoney + " where b_name = '" + b_name + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert.setText("update fault");
						else alert.setText("OK");
					}
					if(ucity.length()!=0) {
						String sql = "update branch set city = '" + ucity + "'" + " where b_name = '" + b_name + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert.setText("update fault");
						else alert.setText("OK");
					}
					if(ub_name.length()!=0) {
						String sql = "update branch set b_name = '" + ub_name + "'" + " where b_name = '" + b_name + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert.setText("update fault");
						else alert.setText("OK");
					}
				}
				else if(index == 3) {
					if(b_name.length()!=0) {
						String sql = "select * from branch where b_name = '" + b_name + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount1[i][j] = result.getString(j+1);
						}
					}
					else if(city.length()!= 0) {
						String sql = "select * from branch where city = '" + city + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount1[i][j] = result.getString(j+1);
						}
					}
					else {
						String sql = "select * from branch";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount1[i][j] = result.getString(j+1);
						}
					}
				}
				jp[0].setVisible(false); 
				jp[0].setVisible(true);
				break;
			case 1:
				String s_id = jtf2[0].getText();
				String s_name = jtf2[1].getText();
				String s_phone = jtf2[2].getText();
				String s_address = jtf2[3].getText();
				String start_time = jtf2[4].getText();
				String manager = jtf2[5].getText();
				String us_id = ujtf2[0].getText();
				String us_name = ujtf2[1].getText();
				String us_phone = ujtf2[2].getText();
				String us_address = ujtf2[3].getText();
				String ustart_time = ujtf2[4].getText();
				String umanager = ujtf2[5].getText();
				
				if(index2 == 0) {
					if(s_id.length()==0 || s_name.length()==0 || s_phone.length()==0 || s_address.length()==0 || start_time.length()==0 || manager.length()==0) {
						alert2.setText("input error");
						return;
					}
					String sql = "insert into staff values('"+s_id+"','"+s_name+"','"+s_phone+"','"+s_address+"',to_date('"+start_time+"','dd-mm-yyyy'),'"+manager+"')";
					//System.out.println(sql);
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i==0) alert2.setText("insert fault");
					else alert2.setText("OK");
				}
				else if(index2 == 1) {
					if(s_id.length() == 0) {
						alert.setText("input error");
						return;
					}
					String sql = "select * from staff where manager = '"+ s_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert2.setText("delete error");	return;	}
					sql = "select * from loan where s_id = '"+ s_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert2.setText("delete error");	return;	}
					sql = "select * from save_account where s_id = '"+ s_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert2.setText("delete error");	return;	}
					sql = "select * from check_account where s_id = '"+ s_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert2.setText("delete error");	return;	}
					
					sql = "delete from staff where s_id = '" + s_id + "'";
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i == 0) alert2.setText("delete fault");
					else alert2.setText("OK");
				}
				else if(index2 == 2) {
					if(s_id.length() == 0) {
						alert2.setText("input error");
						return;
					}
					if(us_name.length()!=0) {
						String sql = "update staff set s_name = '" + us_name + "' where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
					if(us_phone.length()!=0) {
						String sql = "update staff set s_phone = '" + us_phone + "' where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
					if(us_address.length()!=0) {
						String sql = "update staff set s_address = '" + us_address + "' where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
					if(ustart_time.length()!=0) {
						String sql = "update staff set start_time = to_date('" + ustart_time + "','dd-mm-yyyy') where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
					if(umanager.length()!=0) {
						String sql = "update staff set manager = '" + umanager + "' where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
					if(us_id.length()!=0) {
						String sql = "update staff set s_id = '" + us_id + "' where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert2.setText("update fault");
						else alert2.setText("OK");
					}
				}
				else if(index2 == 3) {
					if(s_id.length()!=0) {
						String sql = "select * from staff where s_id = '" + s_id + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<6; j++)
								tcount2[i][j] = result.getString(j+1);
						}
					}
					else if(s_name.length()!= 0) {
						String sql = "select * from staff where s_name = '" + s_name + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<6; j++)
								tcount2[i][j] = result.getString(j+1);
						} 
					}
					else if(manager.length()!=0) {
						String sql = "select * from staff where manager = '" + manager + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<6; j++)
								tcount2[i][j] = result.getString(j+1);
						}
					}
					else {
						String sql = "select * from staff";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<6; j++)
								tcount2[i][j] = result.getString(j+1);
						}
					}
				}
				jp[1].setVisible(false); 
				jp[1].setVisible(true);
				break;
			case 2:
				String c_id = jtf3[0].getText();
				String c_name = jtf3[1].getText();
				String c_phone = jtf3[2].getText();
				String c_address = jtf3[3].getText();
				String r_name = jtf3[4].getText();
				String r_phone = jtf3[5].getText();
				String r_email = jtf3[6].getText();
				String relationship = jtf3[7].getText();
				String uc_id = ujtf3[0].getText();
				String uc_name = ujtf3[1].getText();
				String uc_phone = ujtf3[2].getText();
				String uc_address = ujtf3[3].getText();
				String ur_name = ujtf3[4].getText();
				String ur_phone = ujtf3[5].getText();
				String ur_email = ujtf3[6].getText();
				String urelationship = ujtf3[7].getText();
				
				if(index3 == 0) {
					if(c_id.length()==0 || c_name.length()==0 || c_phone.length()==0 || c_address.length()==0) {
						alert3.setText("input error");
						return;
					}
					String sql = "insert into customer values('"+c_id+"','"+c_name+"','"+c_phone+"','"+c_address+"','"+r_name+"','"+r_phone+"','"+r_email+"','"+relationship+"')";
					//System.out.println(sql);
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i==0) alert3.setText("insert fault");
					else alert3.setText("OK");
				}
				else if(index3 == 1) {
					if(c_id.length() == 0) {
						alert.setText("input error");
						return;
					}

					String sql = "select * from loanp where c_id = '"+ c_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert3.setText("delete error");	return;	}
					sql = "select * from save_account where c_id = '"+ c_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert3.setText("delete error");	return;	}
					sql = "select * from check_account where c_id = '"+ c_id + "'";
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					if(result.next()) { alert3.setText("delete error");	return;	}
					
					sql = "delete from customer where c_id = '" + c_id + "'";
					pre = con.prepareStatement(sql);
					int i = pre.executeUpdate(sql);
					if(i == 0) alert3.setText("delete fault");
					else alert3.setText("OK");
				}
				else if(index3 == 2) {
					if(c_id.length() == 0) {
						alert3.setText("input error");
						return;
					}
					if(uc_name.length()!=0) {
						String sql = "update customer set c_name = '" + uc_name + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(uc_phone.length()!=0) {
						String sql = "update customer set c_phone = '" + uc_phone + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(uc_address.length()!=0) {
						String sql = "update customer set c_address = '" + uc_address + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(ur_name.length()!=0) {
						String sql = "update customer set r_name = '" + ur_name + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(ur_phone.length()!=0) {
						String sql = "update customer set r_phone = '" + ur_phone + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(ur_email.length()!=0) {
						String sql = "update customer set r_email = '" + ur_email + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(urelationship.length()!=0) {
						String sql = "update customer set relationship = '" + urelationship + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
					if(uc_id.length()!=0) {
						String sql = "update customer set c_id = '" + uc_id + "' where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert3.setText("update fault");
						else alert3.setText("OK");
					}
				}
				else if(index3 == 3) {
					if(c_id.length()!=0) {
						String sql = "select * from customer where c_id = '" + c_id + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<8; j++)
								tcount3[i][j] = result.getString(j+1);
						}
					}
					else if(c_name.length()!= 0) {
						String sql = "select * from customer where c_name = '" + c_name + "'";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<8; j++)
								tcount3[i][j] = result.getString(j+1);
						} 
					}
					else {
						String sql = "select * from customer";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<8; j++)
								tcount3[i][j] = result.getString(j+1);
						}
					}
				}
				jp[2].setVisible(false); 
				jp[2].setVisible(true);
				break;
			case 3:
				b_name = jtf4[0].getText();
				String a_id = jtf4[1].getText();
				c_id = jtf4[2].getText();
				s_id = jtf4[3].getText();
				String total_num = jtf4[4].getText();
				String state_date = jtf4[5].getText();
				String last_op = jtf4[6].getText();
				String rate = jtf4[7].getText();
				String money_type = jtf4[8].getText();
				String overdraft = jtf4[9].getText();
				ub_name = ujtf4[0].getText();
				String ua_id = ujtf4[1].getText();
				uc_id = ujtf4[2].getText();
				us_id = ujtf4[3].getText();
				String utotal_num = ujtf4[4].getText();
				String ustat_date = ujtf4[5].getText();
				String ulast_op = ujtf4[6].getText();
				String urate = ujtf4[7].getText();
				String umoney_type = ujtf4[8].getText();
				String uoverdraft = ujtf4[9].getText();
				
				if(index4 == 0) {
					System.out.println(a_id+"   "+b_name+ "  "+ c_id);
					if(a_id.length()==0 || b_name.length()==0) {
						alert4.setText("input error");
						return;
					}
					if(tindex == 0) {
						String sql = "insert into save_account values('"+b_name+"','"+a_id+"','"+c_id+"','"+total_num
								+"',to_date('"+state_date+"','dd-mm-yyyy'),to_date('"+last_op+"','dd-mm-yyyy'),'"+rate+"','"+money_type+"','"+s_id+"')";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i==0) alert4.setText("insert fault");
						else alert4.setText("OK");
					}
					else if(tindex == 1) {
						String sql = "insert into check_account values('"+b_name+"','"+a_id+"','"+c_id+"','"+total_num
								+"',to_date('"+state_date+"','dd-mm-yyyy'),to_date('"+last_op+"','dd-mm-yyyy'),'"+overdraft+"','"+s_id+"')";
						//System.out.println(sql);
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i==0) alert4.setText("insert fault");
						else alert4.setText("OK");
					}
				}
				else if(index4 == 1) {
					if(a_id.length() == 0) {
						alert.setText("input error");
						return;
					}
					if(tindex == 0) {
						String sql = "delete from save_account where a_id = '" + a_id +"'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert4.setText("delete fault");
						else alert4.setText("OK");
					}
					else if(tindex == 1) {
						String sql = "delete from check_account where a_id = '" + a_id + "',b_name = '"+b_name+"'";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert4.setText("delete fault");
						else alert4.setText("OK");
					}
				}
				else if(index4 == 2) {
					if(a_id.length() == 0 ) {
						alert4.setText("input error");
						return;
					}
					if(tindex == 0) {
						if(uc_id.length()!=0) {
							String sql = "update save_account set c_id = '" + uc_id + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(utotal_num.length()!=0) {
							String sql = "update save_account set total_num = '" + utotal_num + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(ulast_op.length()!=0) {
							String sql = "update save_account set last_op = to_date('" + utotal_num + "','dd-mm-yyyy') where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(us_id.length()!=0) {
							String sql = "update save_account set s_id = '" + us_id + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(urate.length()!=0) {
							String sql = "update save_account set rate = '" + urate + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
					}
					else if(tindex == 1) {
						if(uc_id.length()!=0) {
							String sql = "update check_account set c_id = '" + uc_id + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(utotal_num.length()!=0) {
							String sql = "update check_account set total_num = '" + utotal_num + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(ulast_op.length()!=0) {
							String sql = "update check_account set last_op = to_date('" + utotal_num + "','dd-mm-yyyy') where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(us_id.length()!=0) {
							String sql = "update check_account set s_id = '" + us_id + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
						if(uoverdraft.length()!=0) {
							String sql = "update check_account set overdraft = '" + uoverdraft + "' where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							int i = pre.executeUpdate(sql);
							if(i == 0) alert4.setText("update fault");
							else alert4.setText("OK");
						}
					}
					
				}
				else if(index4 == 3) {
					if(tindex == 0) {
						if(a_id.length()!=0) {
							String sql = "select * from save_account where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(9);
								for(int j=4;j<9;j++)
									tcount4[i][j] = result.getString(j);
							}
						}
						else if(b_name.length()!=0) {
							String sql = "select * from save_account where b_name = '" + b_name + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(9);
								for(int j=4;j<9;j++)
									tcount4[i][j] = result.getString(j);
							}
						}
						else if(c_id.length()!= 0) {
							String sql = "select * from save_account where c_id = '" + c_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(9);
								for(int j=4;j<9;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][9] = "";
							}
						}
						else if(s_id.length()!=0) {
							String sql = "select * from save_account where s_id = '" + s_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(9);
								for(int j=4;j<9;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][9] = "";
							}
						}
						else {
							String sql = "select * from save_account";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(9);
								for(int j=4;j<9;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][9] = "";
							}
						}
					}
					if(tindex == 1) {
						if(a_id.length()!=0) {
							String sql = "select * from check_account where a_id = '" + a_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(8);
								for(int j=4;j<7;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][7] = "";
								tcount4[i][8] = "";
								tcount4[i][9] = result.getString(7);
							}
						}
						else if(b_name.length()!=0) {
							String sql = "select * from check_account where b_name = '" + b_name + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(8);
								for(int j=4;j<7;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][7] = "";
								tcount4[i][8] = "";
								tcount4[i][9] = result.getString(7);
							}
						}
						else if(c_id.length()!= 0) {
							String sql = "select * from check_account where c_id = '" + c_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(8);
								for(int j=4;j<7;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][7] = "";
								tcount4[i][8] = "";
								tcount4[i][9] = result.getString(7);
							}
						}
						else if(s_id.length()!=0) {
							String sql = "select * from check_account where s_id = '" + s_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(8);
								for(int j=4;j<7;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][7] = "";
								tcount4[i][8] = "";
								tcount4[i][9] = result.getString(7);
							}
						}
						else {
							String sql = "select * from check_account";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount4[i][j] = result.getString(j+1);
								tcount4[i][3] = result.getString(8);
								for(int j=4;j<7;j++)
									tcount4[i][j] = result.getString(j);
								tcount4[i][7] = "";
								tcount4[i][8] = "";
								tcount4[i][9] = result.getString(7);
							}
						}
					}
				}
				jp[3].setVisible(false); 
				jp[3].setVisible(true);
				break;
			case 4:
				String loan_id = jtf5[0].getText();
				b_name = jtf5[1].getText();
				String loan_num  =jtf5[2].getText();
				s_id = jtf5[3].getText();
				String c_id1 = jtf5[4].getText();
				String c_id2 = jtf5[5].getText();
				String c_id3 = jtf5[6].getText();
				String loan_id1 = jtf6[0].getText();
				String pay_date = jtf6[1].getText();
				String pay_num = jtf6[2].getText();
				if(arg0.getSource() == jb5) {
					//System.out.println("first");
					if(index5 == 0) {
						if(loan_id.length()==0) {
							alert5.setText("input error");
							return;
						}
						String sql = "insert into loan values("+loan_id+",'"+b_name+"',"+loan_num+",'"+s_id+"','尚未发放',"+ loan_num + ")";
						//System.out.println(sql);
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i==0) alert5.setText("insert fault");
						else alert5.setText("OK");
//						if(c_id1.length()!=0) {
//							sql = "insert into loanp values("+loan_id+",'"+c_id1+ "')";
//							pre = con.prepareStatement(sql);
//							i = pre.executeUpdate(sql);
//							if(i==0) alert5.setText("give out fault");
//							else alert5.setText("OK");
//						}
//						if(c_id2.length()!=0) {
//							sql = "insert into loanp values("+loan_id+",'"+c_id2+ "')";
//							pre = con.prepareStatement(sql);
//							i = pre.executeUpdate(sql);
//							if(i==0) alert5.setText("give out fault");
//							else alert5.setText("OK");
//						}
//						if(c_id3.length()!=0) {
//							sql = "insert into loanp values("+loan_id+",'"+c_id3+ "')";
//							pre = con.prepareStatement(sql);
//							i = pre.executeUpdate(sql);
//							if(i==0) alert5.setText("give out fault");
//							else alert5.setText("OK");
//						}
					}
					else if(index5 == 1) {
						if(loan_id.length() == 0) {
							alert.setText("input error");
							return;
						}

						String sql = "select state from loan where loan_id = "+ loan_id;
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						result.next();
						if(!(result.getString(1).equals("发放完成"))) { alert5.setText("can't delete");	return;	}
						
						sql = "delete from loan where loan_id = " + loan_id;
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i == 0) alert5.setText("delete fault");
						else alert5.setText("OK");
					}
					else if(index5 == 2) {
						if(s_id.length()!=0) {
							String sql = "select * from loan where s_id = '" + s_id + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<6; j++)
									tcount5[i][j] = result.getString(j+1);
							}
						}
						else if(loan_id.length()!=0 && b_name.length()!=0) {
							String sql = "select * from loan where loan_id = " + loan_id + ",b_name = '"+ b_name + "'";
							pre = con.prepareStatement(sql); 
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<6; j++)
									tcount5[i][j] = result.getString(j+1);
							}
						}
						else if(loan_id.length()!=0) {
							String sql = "select * from loan where loan_id = " + loan_id;
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<6; j++)
									tcount5[i][j] = result.getString(j+1);
							}
						}
						else if(b_name.length()!=0) {
							String sql = "select * from loan where b_name = '" + b_name + "'";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<6; j++)
									tcount5[i][j] = result.getString(j+1);
							}
						}
						else {
							String sql = "select * from loan";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<6; j++)
									tcount5[i][j] = result.getString(j+1);
							}
						}
					}
					else if(index5 == 3) {
						if(loan_id.length()!=0) {
							String sql = "select * from payout where loan_id = " + loan_id;
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount6[i][j] = result.getString(j+1);
							}
						}
						else {
							String sql = "select * from payout";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<3; j++)
									tcount6[i][j] = result.getString(j+1);
							}
						}
					}
					else if(index5 == 4) {
						if(loan_id.length()!=0) {
							String sql = "select * from loanp where loan_id = " + loan_id;
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<2; j++)
									tcount7[i][j] = result.getString(j+1);
							}
						}
						else {
							String sql = "select * from loanp";
							pre = con.prepareStatement(sql);
							result = pre.executeQuery();
							for(int i=0; result.next(); i++) {
								for(int j=0; j<2; j++)
									tcount7[i][j] = result.getString(j+1);
							}
						}
					}
				} // if jb5
				else if(arg0.getSource() == jb6) {
					//System.out.println("second");
					if(loan_id1.length()==0 || pay_date.length()==0 || pay_num.length()==0) {
						alert5.setText("input error");
						return;
					}
					
					String sql = "select remain from loan where loan_id = " + loan_id1;
					pre = con.prepareStatement(sql);
					result = pre.executeQuery();
					result.next();
					if(result.getDouble(1)<Double.parseDouble(pay_num)) {
						alert5.setText("num fault");
						return;
					}
					else {
						sql = "insert into payout values('"+loan_id1+"',to_date('"+pay_date+"','dd-mm-yyyy'),"+pay_num+")";
						pre = con.prepareStatement(sql);
						int i = pre.executeUpdate(sql);
						if(i==0) alert5.setText("insert fault");
						else alert5.setText("OK");
					}
				}
				
				jp[4].setVisible(false); 
				jp[4].setVisible(true);
				break;
			case 5:
				if(index6 == 0) {
					if(index7 == 0) {
						String sql = "select to_char(state_date, 'yyyy'), count(*), sum(total_num) from save_account group by to_char(state_date, 'yyyy')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
					else if(index7 == 1) {
						String sql = "select to_char(state_date, 'yyyy-q'), count(*), sum(total_num) from save_account group by to_char(state_date, 'yyyy-q')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
					else if(index7 == 2) {
						String sql = "select to_char(state_date, 'yyyy-mm'), count(*), sum(total_num) from save_account group by to_char(state_date, 'yyyy-mm')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
				}
				else if(index6 ==1) {
					if(index7 == 0) {
						String sql = "select to_char(pay_date, 'yyyy'), count(*), sum(pay_num) from payout group by to_char(pay_date, 'yyyy')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
					else if(index7 == 1) {
						String sql = "select to_char(pay_date, 'yyyy-q'), count(*), sum(pay_num) from payout group by to_char(pay_date, 'yyyy-q')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
					else if(index7 == 2) {
						String sql = "select to_char(pay_date, 'yyyy-mm'), count(*), sum(pay_num) from payout group by to_char(pay_date, 'yyyy-mm')";
						pre = con.prepareStatement(sql);
						result = pre.executeQuery();
						for(int i=0; result.next(); i++) {
							for(int j=0; j<3; j++)
								tcount8[i][j] = result.getString(j+1);
						}
					}
				}
				jp[5].setVisible(false); 
				jp[5].setVisible(true);
			}// wiitch

			
//			pre = con.prepareStatement(sql);
//			result = pre.executeQuery();
//			while(result.next()) {
//				System.out.print("ID:" + result.getString(1));
//				System.out.println("name:"+ result.getString(2));
//				System.out.println();
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(result != null)
					result.close();
				if(pre != null)
					pre.close();
				if(con != null)
					con.close();
				for(int i=0;i<6;i++) {
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	}

}
