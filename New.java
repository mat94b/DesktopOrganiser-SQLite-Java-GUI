import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class New extends JFrame implements ActionListener {

	private Pathes obj = new Pathes();
	private Dimension size = obj.sizeNewSch;
	private String title = obj.titleNewSch;
	private JButton buttL = new JButton("Usun wszystko");
	private JButton buttR = new JButton("Usun date");
	private JLabel imie = new JLabel("Imie:");
	private JLabel inf = new JLabel("*Data jest wymagana tylko w przypadku chêci usuniecia konkretnego terminu");
	private JLabel nazwisko = new JLabel("Nazwisko:");
	private JLabel data = new JLabel("DD/MM/RR:");
	private JTextArea imieT = new JTextArea();
	private JTextArea nazwiskoT = new JTextArea();
	private JTextArea dataTD = new JTextArea();
	private JTextArea dataTM = new JTextArea();
	private JTextArea dataTR = new JTextArea();

	public New() {
		setSize(size);
		setTitle(title);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		initTNS();
		setVisible(true);
	}

	private void initTNS() {
		imie.setBounds(20, 20, 50, 20);
		nazwisko.setBounds(20, 45, 70, 20);
		data.setBounds(20, 70, 70, 20);
		imieT.setBounds(100, 20, 100, 20);
		nazwiskoT.setBounds(100, 45, 100, 20);
		dataTD.setBounds(100, 70, 20, 20);
		dataTM.setBounds(121, 70, 20, 20);
		dataTR.setBounds(142, 70, 30, 20);
		buttL.setBounds(20, 100, 200, 50);
		buttR.setBounds(20, 155, 200, 50);
		buttL.addActionListener(this);
		buttR.addActionListener(this);
		inf.setBounds(20, 200, 500, 50);

		this.add(inf);
		this.add(imie);
		this.add(nazwisko);
		this.add(data);
		this.add(imieT);
		this.add(nazwiskoT);
		this.add(dataTD);
		this.add(dataTM);
		this.add(dataTR);
		this.add(buttL);
		this.add(buttR);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		ConnectionDB dbcon = new ConnectionDB();
		if (source == buttL) {
			String name = imieT.getText().toString();
			String sur = nazwiskoT.getText().toString();
			if (dbcon.security(name, sur) == true) {
				dbcon.delsuper(name, sur);
				JOptionPane.showMessageDialog(null, "Pomyœlnie usuniêto WSZYSTKIE terminy!");
				dispose();
			}

			else
				JOptionPane.showMessageDialog(null, "TAKA OSOBA W BAZIE DANYCH NIE FIGURUJE!");
		}

		else if (source == buttR) {
			String name = imieT.getText().toString();
			String sur = nazwiskoT.getText().toString();
			int d = Integer.parseInt(dataTD.getText());
			int m = Integer.parseInt(dataTM.getText());
			int r = Integer.parseInt(dataTR.getText());

			if (dbcon.security(name, sur, d, m, r)==true) {
				dbcon.delmniejsuper(name, sur, Integer.parseInt(dataTD.getText()), Integer.parseInt(dataTM.getText()),
						Integer.parseInt(dataTR.getText()));
				JOptionPane.showMessageDialog(null, "Pomyœlnie usuniêto PODANY TERMIN!");
				dispose();
			}
			
			else JOptionPane.showMessageDialog(null, "TAKI TERMIN TEJ OSOBY NIE ISTNIEJE");
		}
	}
}
