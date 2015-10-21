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

		JLabel lineName = new JLabel("���ߣ�");
		lineName.setFont(new Font("����", Font.PLAIN, 16));

		line = new JTextField();
		line.setColumns(10);

		JLabel total = new JLabel("������");
		total.setFont(new Font("����", Font.PLAIN, 16));

		totalNum = new JTextField();
		totalNum.setColumns(10);

		JLabel label_2 = new JLabel("��Ӳ�Ʒ��");
		label_2.setFont(new Font("����", Font.BOLD | Font.ITALIC, 17));

		JLabel label_3 = new JLabel("��Ʒ����");

		JLabel label_4 = new JLabel("���ģ�");

		takt = new JTextField();
		takt.setColumns(10);
		List<String> list = new ArrayList<String>();
		list.add("����");
		list.add("�ݶ�");

		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// ���߲�Ʒ�����ж�
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// �жϲ�Ʒ�Ƿ���д
				if ("".equals(product.getText())) {
					JOptionPane.showMessageDialog(null, "δ�����Ʒ��", "����",
							JOptionPane.ERROR_MESSAGE);
				}
				// �������δ��д���򵯳���ʾ��
				else if (takt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "����δ��д��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// ����һ�����ݵ������list��
					productList.setText(productList.getText() + pListCount++
							+ ". ��Ʒ��" + product.getText() + "��������" + num.getText() + "�����ģ�" + takt.getText()
							+ ";\n");
					product.setText("");
					// ����ģ��
					tPLProd = new TPLProd();
					// ��Ʒ��
					tPLProd.setProdName(product.getText());
					// ��Ʒ����
					tPLProd.setNum(Integer.parseInt(num.getText()));
					// ��Ʒ����
					tPLProd.setTakt(Double.parseDouble(takt.getText()));
					pList.add(tPLProd);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");

		JLabel label_6 = new JLabel("��Ϣʱ�䣺");

		restTime = new JTextField();
		restTime.setColumns(10);

		JLabel label_7 = new JLabel("����ʱ�䣺");

		changeTime = new JTextField();
		changeTime.setColumns(10);

		JLabel label_8 = new JLabel("��ʱ������ã�10:00-10:10;14:10-14:20");
		label_8.setForeground(new Color(169, 169, 169));

		JButton button_1 = new JButton("�ύ");
		// �ύ�����¼�
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// �������δ��д���򵯳���ʾ��
				if (line.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "����δ��д��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else if (totalNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "����δ��д��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						// ʵ���� WarningLightDao
						wld = new WarningLightDao();
						// ʵ�����ƻ���
						TPPlan tpplan = new TPPlan();
						// ��ȡ�ܲ���
						tpplan.setTotalNum(Integer.parseInt(totalNum.getText()));
						// ��Ӳ�����
						tpplan.setTPLineName(line.getText());
						// ��ȡ���
						tpplan.setRanger(ranger.getSelectedIndex());
						// ��ȡ�����ߣ�δ��д��Ϊ�� ����
						tpplan.setCreatePeople(createPeople.getText().equals(
								"") ? " " : createPeople.getText());
						// ״̬
						tpplan.setStatus(1);
						/** �������ݿ� */
						int autoInckey = 0;
						// �Զ������²������ݵ�����
						autoInckey = wld.insertToTpplan(tpplan);

						// ʵ��������
						TPLine tpline = new TPLine();
						// ���߼ƻ�ID
						tpline.setTPPlanID(autoInckey);
						// ������
						tpline.setTPLineName(line.getText());
						// ���
						tpline.setRanger(ranger.getSelectedIndex());
						// ��Ϣʱ�䣨δ��д��Ϊ�� ����
						tpline.setRestTime(restTime.getText().equals("") ? " "
								: restTime.getText());
						// ����ʱ�䣨δ��д��Ϊ�� ����
						tpline.setChangeTime(changeTime.getText().equals("") ? " "
								: changeTime.getText());
						// ����ʱ��
						tpline.setEndTime("00:00:00");
						// ���ʱ��
						tpline.setLostTime("");
						// ״̬
						tpline.setStatus(1);
						/** �������ݿ� */
						autoInckey = wld.insertToTpline(tpline);
						// ѭ��list �������Ʒ
						for (TPLProd p : pList) {
							// ����ID
							p.setTPLineID(autoInckey);
							// ״̬
							p.setStatus(1);
							/** �������ݿ� */
							wld.insertToTplprod(p);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					line.setText("");// ����
					totalNum.setText("");// ����
					takt.setText("");// ����
					productList.setText("");// ��Ʒ�б�
					restTime.setText("");// ��Ϣʱ��
					changeTime.setText("");// ����ʱ��
					JOptionPane.showMessageDialog(null, "�ƻ��Ѳ��룡", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);

					// �رմ���
					System.exit(0);

				}
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 15));

		JButton button_2 = new JButton("����");
		// ���ð�ť����
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// line.setText("");// ����
				// totalNum.setText("");// ����
				takt.setText("");// ����
				productList.setText("");// ��Ʒ�б�
				restTime.setText("");// ��Ϣʱ��
				changeTime.setText("");// ����ʱ��
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 15));

		JLabel label_9 = new JLabel("�����ˣ�");

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
				new String[] { "���", "�а�", "���" }));
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
		label.setFont(new Font("����", Font.BOLD, 23));
		panel.add(label);
		contentPane.setLayout(gl_contentPane);

	}

	/**
	 * ��̨���²�Ʒ�б�
	 * 
	 * @return
	 */
	private SwingWorker updateProList() {
		SwingWorker updateProductList = new SwingWorker<List<String>, Void>() {

			@Override
			protected List<String> doInBackground() throws Exception {
				wld = new WarningLightDao();
				// �˴����к�ʱ��������Ӱ��UI�̣߳��˴������Բ���UI
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
