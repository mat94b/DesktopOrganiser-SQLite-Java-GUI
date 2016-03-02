import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import com.toedter.calendar.JCalendar;

public class TotalNewPacient extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pathes obj = new Pathes();
	private Dimension size = obj.sizeNewPacient;
	private String title = obj.titleNewPacient;
	private JLabel name, surname, scheme, bigDate, shortinf;
	private JTextArea information;
	private JTextArea nameText, surnameText, textScheme, registered;
	private JCalendar calendar;
	private JButton saveButton, backButton;
	private DatesPack[] datespackTable;
	private int counter = 0;
	private JScrollPane sp, sp2;
	private JButton del;
	private ConnectionDB db = new ConnectionDB();

	public TotalNewPacient() {
		datespackTable = new DatesPack[100];
		setSize(size);
		setTitle(title);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		initTNS();
		shortinf = new JLabel("");
		shortinf.setBounds(620, 50, 300, 50);
		this.add(shortinf);
		setVisible(true);
	}

	private String dispDB(int d, int m, int r) {
		return db.findPeople(d, m, r);
	}

	private void initTNS() {
		saveButton = new JButton("Zapisz");
		saveButton.setBounds(20, 330, 240, 50);
		saveButton.addActionListener(this);
		this.add(saveButton);

		backButton = new JButton("Cofnij");
		backButton.setBounds(300, 200, 100, 25);
		backButton.addActionListener(this);
		this.add(backButton);

		del = new JButton("Usuñ");
		del.setBounds(880, 65, 100, 35);
		del.addActionListener(this);
		this.add(del);

		name = new JLabel("Imiê:");
		name.setBounds(20, 240, 60, 20);
		name.setForeground(Color.WHITE);
		this.add(name);

		surname = new JLabel("Nazwisko:");
		surname.setBounds(20, 270, 60, 20);
		surname.setForeground(Color.WHITE);
		this.add(surname);

		nameText = new JTextArea();
		nameText.setBounds(100, 240, 150, 20);
		this.add(nameText);

		surnameText = new JTextArea();
		surnameText.setBounds(100, 270, 150, 20);
		this.add(surnameText);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 400, 200);
		this.add(calendar);

		scheme = new JLabel("Schemat");
		textScheme = new JTextArea();
		scheme.setBounds(20, 300, 70, 20);
		textScheme.setBounds(100, 300, 50, 20);
		scheme.setForeground(Color.WHITE);
		this.add(scheme);
		this.add(textScheme);

		information = new JTextArea("Wybrane Daty:");
		information.setForeground(Color.WHITE);
		information.setBackground(Color.GRAY);
		information.setEditable(false);
		sp = new JScrollPane(information);
		sp.setBounds(410, 0, 130, 500);
		this.add(sp);

		registered = new JTextArea("");
		registered.setForeground(Color.BLACK);
		registered.setBackground(Color.WHITE);
		registered.setEditable(false);
		sp2 = new JScrollPane(registered);
		sp2.setBounds(560, 100, 420, 370);
		this.add(sp2);

		bigDate = new JLabel("");
		bigDate.setBounds(600, 20, 400, 50);
		bigDate.setForeground(Color.PINK);
		bigDate.setFont(new Font("Arial", Font.ITALIC, 35));
		this.add(bigDate);

		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				final Calendar c = (Calendar) e.getNewValue();
				int dzien = c.get(Calendar.DAY_OF_MONTH);
				int miesiac = c.get(Calendar.MONTH);
				int rok = c.get(Calendar.YEAR);
				bigDate.setText(dzien + " " + obj.mounthCounter(miesiac) + " " + rok);
				DatesPack o = new DatesPack(dzien, miesiac, rok);
				datespackTable[counter] = o;
				String tmp = information.getText().toString();
				tmp = tmp + "\n" + dzien + " " + obj.mounthCounter(miesiac) + " " + rok;
				information.setText(tmp);
				++counter;
				shortinf.setText("Zapisanych pacientow w danym dniu: "+Integer.toString(db.pacientCounter(dzien, miesiac, rok)));
				registered.setText(dispDB(dzien, miesiac, rok));
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (saveButton == e.getSource()) {
			String imie = nameText.getText().toString();
			String nazwisko = surnameText.getText().toString();
			String schemat = textScheme.getText().toString();
			ConnectionDB objCon = new ConnectionDB();
			while (counter != 0) {
				--counter;
				objCon.addNewDate(imie, nazwisko, schemat, datespackTable[counter].day, datespackTable[counter].mounth,
						datespackTable[counter].year);
			}
			JOptionPane.showMessageDialog(null, "Daty zapisano pomyslnie w bazie danych!");
			dispose();
		}

		else if (backButton == e.getSource()) {
			if (counter == 0)
				JOptionPane.showMessageDialog(null, "Lista dat jest pusta!");
			else {
				--counter;
				datespackTable[counter] = null;
				try {
					String content = information.getDocument().getText(0, information.getDocument().getLength());
					int lastLineBreak = content.lastIndexOf('\n');
					information.getDocument().remove(lastLineBreak,
							information.getDocument().getLength() - lastLineBreak);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (del == e.getSource()) {

			New n = new New();
		}
	}
}
