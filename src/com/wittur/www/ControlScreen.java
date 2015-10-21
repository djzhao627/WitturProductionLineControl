package com.wittur.www;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.lewei.dao.WarningLightDao;
import com.lewei.model.TPLProd;
import com.lewei.model.TPLine;
import com.lewei.model.TPPlan;

public class ControlScreen extends JFrame {

	private JPanel contentPane;
	private JTextField line;
	private JTextField totalNum;
	private JTextField takt;
	private JTextField restTime;
	private JTextField changeTime;
	private JTextField createPeople;
	private JTextPane productList;
	private JTextField product;

	private TPLProd tPLProd = null;
	private List<TPLProd> pList = new ArrayList<TPLProd>();
	private int pListCount = 1;
	private WarningLightDao wld = null;
	private JTextField num;
	private JComboBox ranger;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlScreen frame = new ControlScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ControlScreen() {

		ImageIcon icon = new ImageIcon("images\\Wittur_Logo.gif");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		setTitle("\u5A01\u7279\u4EA7\u7EBF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JSeparator separator = new JSeparator();

		JLabel lineName = new JLabel("产线：");
		lineName.setFont(new Font("宋体", Font.PLAIN, 16));

		line = new JTextField();
		line.setColumns(10);

		JLabel total = new JLabel("总量：");
		total.setFont(new Font("宋体", Font.PLAIN, 16));

		totalNum = new JTextField();
		totalNum.setColumns(10);

		JLabel label_2 = new JLabel("添加产品：");
		label_2.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 17));

		JLabel label_3 = new JLabel("产品名：");

		JLabel label_4 = new JLabel("节拍：");

		takt = new JTextField();
		takt.setColumns(10);
		List<String> list = new ArrayList<String>();
		list.add("铁门");
		list.add("螺钉");

		JButton button = new JButton("增加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 产线产品加入列队
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// 判断产品是否填写
				if ("".equals(product.getText())) {
					JOptionPane.showMessageDialog(null, "未输入产品！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
				// 如果节拍未填写，则弹出提示框
				else if (takt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "节拍未填写！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// 插入一条数据到下面的list框
					productList.setText(productList.getText() + pListCount++
							+ ". 产品：" + product.getText() + "，数量：" + num.getText() + "，节拍：" + takt.getText()
							+ ";\n");
					product.setText("");
					// 存入模型
					tPLProd = new TPLProd();
					// 产品名
					tPLProd.setProdName(product.getText());
					// 产品数量
					tPLProd.setNum(Integer.parseInt(num.getText()));
					// 产品节拍
					tPLProd.setTakt(Double.parseDouble(takt.getText()));
					pList.add(tPLProd);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");

		JLabel label_6 = new JLabel("休息时间：");

		restTime = new JTextField();
		restTime.setColumns(10);

		JLabel label_7 = new JLabel("换班时间：");

		changeTime = new JTextField();
		changeTime.setColumns(10);

		JLabel label_8 = new JLabel("多时间段设置：10:00-10:10;14:10-14:20");
		label_8.setForeground(new Color(169, 169, 169));

		JButton button_1 = new JButton("提交");
		// 提交监听事件
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// 如果存在未填写，则弹出提示框
				if (line.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "产线未填写！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else if (totalNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "总量未填写！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						// 实例化 WarningLightDao
						wld = new WarningLightDao();
						// 实例化计划表
						TPPlan tpplan = new TPPlan();
						// 获取总产量
						tpplan.setTotalNum(Integer.parseInt(totalNum.getText()));
						// 添加产线名
						tpplan.setTPLineName(line.getText());
						// 获取班次
						tpplan.setRanger(ranger.getSelectedIndex());
						// 获取操作者（未填写则为“ ”）
						tpplan.setCreatePeople(createPeople.getText().equals(
								"") ? " " : createPeople.getText());
						// 状态
						tpplan.setStatus(1);
						/** 插入数据库 */
						int autoInckey = 0;
						// 自动返回新插入数据的主键
						autoInckey = wld.insertToTpplan(tpplan);

						// 实例化产线
						TPLine tpline = new TPLine();
						// 产线计划ID
						tpline.setTPPlanID(autoInckey);
						// 产线名
						tpline.setTPLineName(line.getText());
						// 班次
						tpline.setRanger(ranger.getSelectedIndex());
						// 休息时间（未填写则为“ ”）
						tpline.setRestTime(restTime.getText().equals("") ? " "
								: restTime.getText());
						// 换班时间（未填写则为“ ”）
						tpline.setChangeTime(changeTime.getText().equals("") ? " "
								: changeTime.getText());
						// 结束时间
						tpline.setEndTime("00:00:00");
						// 损耗时间
						tpline.setLostTime("");
						// 状态
						tpline.setStatus(1);
						/** 插入数据库 */
						autoInckey = wld.insertToTpline(tpline);
						// 循环list ，插入产品
						for (TPLProd p : pList) {
							// 产线ID
							p.setTPLineID(autoInckey);
							// 状态
							p.setStatus(1);
							/** 插入数据库 */
							wld.insertToTplprod(p);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					line.setText("");// 产线
					totalNum.setText("");// 总量
					takt.setText("");// 节拍
					productList.setText("");// 产品列表
					restTime.setText("");// 休息时间
					changeTime.setText("");// 换班时间
					JOptionPane.showMessageDialog(null, "计划已插入！", "成功",
							JOptionPane.INFORMATION_MESSAGE);

					// 关闭窗口
					System.exit(0);

				}
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton button_2 = new JButton("重置");
		// 重置按钮监听
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// line.setText("");// 产线
				// totalNum.setText("");// 总量
				takt.setText("");// 节拍
				productList.setText("");// 产品列表
				restTime.setText("");// 休息时间
				changeTime.setText("");// 换班时间
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));

		JLabel label_9 = new JLabel("操作人：");

		createPeople = new JTextField();
		createPeople.setColumns(10);

		product = new JTextField();
		product.setColumns(10);

		JLabel label_1 = new JLabel("\u6570\u91CF\uFF1A");

		num = new JTextField();
		num.setText("1");
		num.setColumns(10);

		JLabel label_5 = new JLabel("\u73ED\u6B21\uFF1A");

		ranger = new JComboBox();
		ranger.setModel(new DefaultComboBoxModel(
				new String[] { "早班", "中班", "晚班" }));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																864,
																Short.MAX_VALUE)
														.addComponent(
																separator,
																GroupLayout.DEFAULT_SIZE,
																864,
																Short.MAX_VALUE)
														.addComponent(label_2)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																864,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				label_6)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				restTime,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				label_7)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				changeTime,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				462,
																				Short.MAX_VALUE)
																		.addComponent(
																				label_9)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				createPeople,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				label_8)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				498,
																				Short.MAX_VALUE)
																		.addComponent(
																				button_2)
																		.addGap(18)
																		.addComponent(
																				button_1))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_3)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												product,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												label_1)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												num,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												lineName)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												line,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												total,
																												GroupLayout.PREFERRED_SIZE,
																												48,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												totalNum,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(23)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_5)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												ranger,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label_4)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												takt,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												button)))))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(5)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 13,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lineName)
														.addComponent(
																line,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																total,
																GroupLayout.PREFERRED_SIZE,
																19,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																totalNum,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_5)
														.addComponent(
																ranger,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(label_2)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_3)
														.addComponent(label_4)
														.addComponent(
																takt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button)
														.addComponent(
																product,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_1)
														.addComponent(
																num,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												235, GroupLayout.PREFERRED_SIZE)
										.addGap(20)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_6)
														.addComponent(
																restTime,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_7)
														.addComponent(
																changeTime,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																createPeople,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_9))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_8)
														.addComponent(button_1)
														.addComponent(button_2))
										.addContainerGap(23, Short.MAX_VALUE)));

		productList = new JTextPane();
		productList.setEditable(false);
		scrollPane.setViewportView(productList);

		JLabel label = new JLabel("\u4EA7\u7EBF\u89C4\u5212\u8868");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("宋体", Font.BOLD, 23));
		panel.add(label);
		contentPane.setLayout(gl_contentPane);

	}

	/**
	 * 后台更新产品列表
	 * 
	 * @return
	 */
	private SwingWorker updateProList() {
		SwingWorker updateProductList = new SwingWorker<List<String>, Void>() {

			@Override
			protected List<String> doInBackground() throws Exception {
				wld = new WarningLightDao();
				// 此处进行耗时操作，不影响UI线程，此处不可以操作UI
				List<String> list = wld.getProductNames();
				return list;
			}

			@Override
			protected void done() {
				super.done();
			}
		};
		return updateProductList;
	}
}
