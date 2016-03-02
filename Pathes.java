import java.awt.Dimension;

public class Pathes {

	public Pathes() {
	}
	
	public Dimension sizeFrame = new Dimension(630, 500);
	public String titleFrame = "Partner 1.1";
	public Dimension sizeNewSch = new Dimension(500, 290);
	public String titleNewSch = "Dodaj Nowy Schemat";
	public String databasePath = "C:\\Calendar\\ojeden.db";
	public Dimension sizeNewPacient = new Dimension(1000, 500);
	public String titleNewPacient = "Nowy Pacient";
	
	
	public String mounthCounter(int m){
		m+=1;
		if(m==1) return "Styczen";
		else if(m==2) return "Luty";
		else if(m==3) return "Marzec";
		else if(m==4) return "Kwiecien";
		else if(m==5) return "Maj";
		else if(m==6) return "Czerwiec";
		else if(m==7) return "Lipiec";
		else if(m==8) return "Sierpien";
		else if(m==9) return "Wrzesien";
		else if(m==10) return "Pazdziernik";
		else if(m==11) return "Listopad";
		else return "Grudzen";
	}
	
	/*
	 * 
	 * Baza calendar.db
	 * Nazwa tabeli: People
	 * Kolumny: imie, nazwisko, schemat, dataDzien, dataMiesiac, dataRok
	 */
}
