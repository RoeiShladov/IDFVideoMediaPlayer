package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUIConvertingFormats extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConvertingFormats frame = new GUIConvertingFormats();
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
	public GUIConvertingFormats() {
		setTitle("IDF Media Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds( 400, 150, 500, 350 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(66, 56, 53, 32);
		contentPane.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(317, 56, 53, 32);
		contentPane.add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(66, 150, 53, 32);
		contentPane.add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(317, 150, 53, 32);
		contentPane.add(comboBox_4);

		JButton btnNewButton = new JButton("\u05E1\u05D9\u05D5\u05DD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(171, 212, 89, 23);
		contentPane.add(btnNewButton);
	}
}
