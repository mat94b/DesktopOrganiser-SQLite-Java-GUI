import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JCalendar;

public class Frame extends JFrame implements ActionListener {

	private Pathes obj = new Pathes();
	private Dimension size = obj.sizeFrame;
	private String title = obj.titleFrame;
	private JButton addSchemButton, newPacientOddzial, newPacientPoradnia;
	private JCalendar calendar;
	private JLabel dateShow, numberShow;
	private JTextArea inform;
	private JScrollPane sp3;
	

	public Frame() {
		setSize(size);
		setTitle(title);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		setVisible(true);
	}

	private void init() {
		addSchemButton = new JButton("Usuñ");
		addSchemButton.setBounds(0, 60, 200, 50);
		addSchemButton.addActionListener(this);
		add(addSchemButton);

		newPacientOddzial = new JButton("Dodaj Schemat");
		newPacientOddzial.setBounds(0, 0, 200, 50);
		newPacientOddzial.addActionListener(this);
		add(newPacientOddzial);

		
		numberShow = new JLabel("");
		numberShow.setBounds(80, 130,200,100);
		numberShow.setFont(new Font("Arial", Font.ITALIC, 75));
		this.add(numberShow);
		
		dateShow = new JLabel("");
		dateShow.setBounds(220, 10,400,50);
		dateShow.setFont(new Font("Arial", Font.ITALIC, 35));
		this.add(dateShow);
		
		
		inform = new JTextArea("");
		inform.setForeground(Color.WHITE);
		inform.setBackground(Color.GRAY);
		inform.setEditable(false);
		sp3 = new JScrollPane(inform);
		sp3.setBounds(20, 250, 590, 210);
		this.add(sp3);
		
		calendar = new JCalendar();
		calendar.setBounds(210, 60, 400, 200);
		this.add(calendar);
		
		
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				final Calendar c = (Calendar) e.getNewValue();
				int dzien = c.get(Calendar.DAY_OF_MONTH);
				int miesiac = c.get(Calendar.MONTH);
				int rok = c.get(Calendar.YEAR);
				dateShow.setText(dzien+" "+obj.mounthCounter(miesiac)+" "+rok);
				ConnectionDB database = new ConnectionDB();
				inform.setText(database.findPeople2(dzien,miesiac,rok));
				numberShow.setText(Integer.toString(database.pacientCounter(dzien, miesiac, rok)));
				
				
				
			}
		});
		
		
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (addSchemButton == source) {
			New n = new New();
		}

		else if (newPacientOddzial == source) {
			TotalNewPacient tns = new TotalNewPacient();

		}

		else if (newPacientPoradnia == source) {

		}
	}
}
