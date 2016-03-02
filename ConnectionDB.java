import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionDB {

	private Pathes objPath = new Pathes();
	private String path = objPath.databasePath;

	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	public ConnectionDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + path);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewDate(String imie, String nazwisko, String schemat, int dzien, int miesiac, int rok) {
		miesiac += 1;
		String query = "INSERT INTO people(imie, nazwisko, schemat, dataDzien, dataMiesiac, dataRok) values(?,?,?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, imie);
			ps.setString(2, nazwisko);
			ps.setString(3, schemat);
			ps.setInt(4, dzien);
			ps.setInt(5, miesiac);
			ps.setInt(6, rok);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String findPeople(int d, int m, int r) {
		try {
			m += 1;
			String q = "SELECT * FROM people WHERE dataDzien=" + d + " AND dataMiesiac=" + m + " AND dataRok=" + r
					+ ";";
			String finalmessage = "";
			rs = st.executeQuery(q);
			if (rs != null) {
				while (rs.next()) {
					finalmessage = finalmessage + rs.getString("imie") + "\t" + rs.getString("nazwisko") + "\t\t"
							+ rs.getString("schemat") + "\n";
				}
				return finalmessage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "W tym dniu nie ma jeszcze zapisanych pacjentow";

	}

	public String findPeople2(int d, int m, int r) {
		try {
			m += 1;
			String q = "SELECT * FROM people WHERE dataDzien=" + d + " AND dataMiesiac=" + m + " AND dataRok=" + r
					+ ";";
			String finalmessage = "";
			rs = st.executeQuery(q);
			if (rs != null) {
				while (rs.next()) {
					finalmessage = finalmessage + rs.getString("imie") + "\t\t" + rs.getString("nazwisko") + "\t\t\t"
							+ rs.getString("schemat") + "\n";
				}
				return finalmessage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "W tym dniu nie ma jeszcze zapisanych pacjentow";

	}

	
	
	public int pacientCounter(int d, int m, int r) {
		int a=0;
		try {
			m += 1;
			String q = "SELECT * FROM people WHERE dataDzien=" + d + " AND dataMiesiac=" + m + " AND dataRok=" + r
					+ ";";
			rs = st.executeQuery(q);
			if (rs != null) {
				while (rs.next()) {
					++a;
				}
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public void delsuper(String imie, String nazwisko) {
		String q = "DELETE FROM people WHERE imie='" + imie + "' AND nazwisko='" + nazwisko + "';";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delmniejsuper(String imie, String nazwisko, int d, int m, int r) {
		String q = "DELETE FROM people WHERE imie='" + imie + "' AND nazwisko='" + nazwisko + "' AND dataDzien=" + d
				+ " AND dataMiesiac=" + m + " AND dataRok=" + r + ";";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean security(String imie, String nazwisko){
		
		String qrys = "SELECT * FROM people WHERE imie='"+imie+"' AND nazwisko='"+nazwisko+"';";
		int counter=0;
		try{
			rs=st.executeQuery(qrys);
			if(rs.next()){
				counter++;
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (counter==0) return false;
		
		return true;
	}
	
	
	public boolean security(String imie, String nazwisko, int d, int m, int r){
		
		String qrys = "SELECT * FROM people WHERE imie='" + imie + "' AND nazwisko='" + nazwisko + "' AND dataDzien=" + d
				+ " AND dataMiesiac=" + m + " AND dataRok=" + r + ";";
		int counter=0;
		try{
			rs=st.executeQuery(qrys);
			if(rs.next()){
				counter++;
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (counter==0) return false;
		
		return true;
	}

	

	public void connect() {
		try {
			rs = st.executeQuery("SELECT * FROM medicine");
			while (rs.next()) {
				System.out.println(rs.getString("faktura"));
				System.out.println(rs.getFloat("cena"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
