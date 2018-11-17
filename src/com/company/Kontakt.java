package com.company;

/**
 * Die Klasse Kontakt beschreibt einen Datensatz in einem persönlichen Adressbuch
 * 
 * @author Rudolf Silberberg
 * @version 2009-02-11
 */
public class Kontakt extends Item
{
    public static final String STANDART_CSV_DELIMITER = ",";
    public static final String EMPTY_STRING_CSV = "\"\"";
    // Instanzvariablen
    private String name;
    private String vorname;
    private String telefon;
    private String email;

    /**
     * Konstruktor für Objekte der Klasse Kontakt ohne Parameteruebergabe
     */
    public Kontakt()
    {
        // Instanzvariable initialisieren
        name = "";
        vorname = "";
        telefon = "";
        email = "";
    }
    
    /**
     * Konstruktor für Objekte der Klasse Kontakt mit Parameteruebergabe
     * 
     * @param pName Name
     * @param pVorname Vorname
     * @param pTelefon Telefonnummer
     * @param pEmail Email-Adresse
     */
    public Kontakt(String pName, String pVorname, String pTelefon, String pEmail)
    {
        // Instanzvariable initialisieren
        name = pName;
        vorname = pVorname;
        telefon = pTelefon;
        email = pEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Liefert den Nachnamen
     * 
     * @return Nachname
     */
    public String gibName() {
        return(name);
    }
    
    /**
     * Liefert den Vornamen
     * 
     * @return Vorname
     */
    public String gibVorname() {
        return(vorname);
    }
    
    /**
     * Liefert die Telefonnummer
     * 
     * @return Telefonnummer
     */
    public String gibTelefon() {
        return(telefon);
    }
    
    /**
     * Liefert die Emailadresse
     * 
     * @return Emailadresse
     */
    public String gibEmail() {
        return(email);
    }

    /**
     * Vergleich, ob der Nachname und der Vorname dieses Kontaktes mit denen von pItem uebereinstimmt.
     * 
     * @parame pItem der Vergleichskontakt
     * @return true, falls Uebereinstimmung, sonst false
     */
    public boolean isEqual(Item pItem) {
        String fullName = name.concat(vorname);
        String otherFullName = ((Kontakt)pItem).gibName().concat(((Kontakt)pItem).gibVorname());
        return(fullName.equals(otherFullName));
    }
    
    /**
     * Vergleich, ob der Nachname und der Vorname dieses Kontaktes groesser als von pItem ist.
     * 
     * @parame pItem der Vergleichskontakt
     * @return true, falls der Nachname und der Vorname dieses Kontaktes groesser ist, sonst false
     */
    public boolean isGreater(Item pItem) {
        String fullName = name.concat(vorname);
        String otherFullName = ((Kontakt)pItem).gibName().concat(((Kontakt)pItem).gibVorname());
        return(fullName.compareTo(otherFullName) > 0);
    }
    
    
    /**
     * Vergleich, ob der Nachname und der Vorname dieses Kontaktes kleiner als von pItem ist.
     * 
     * @param pItem der Vergleichskontakt
     * @return true, falls der Nachname und der Vorname dieses Kontaktes kleiner ist, sonst false
     */
    public boolean isLess(Item pItem) {
        String fullName = name.concat(vorname);
        String otherFullName = ((Kontakt)pItem).gibName().concat(((Kontakt)pItem).gibVorname());
        return(fullName.compareTo(otherFullName) < 0);
    }
    
    /**
     * Gibt eine String-Repraesentation dieses Objekts zurueck.
     * 
     * @return Name, Vorname, Telefon, Email
     */
    public String toString() {
        return(name+" "+vorname+" "+telefon+" "+email);
    }

    public static String getCSVHeader() {
        return getCSVHeader(STANDART_CSV_DELIMITER);
    }

    public static String getCSVHeader(String CSV_Delimiter) {
        return "Name" + CSV_Delimiter + "Vorname" + CSV_Delimiter + "Telefon" + CSV_Delimiter + "Email";
    }

    public String asCSV() {
        return asCSV(STANDART_CSV_DELIMITER);
    }

    public String asCSV(String CSVDelimiter) {
        return (emptySafe(name) + CSVDelimiter + emptySafe(vorname) + CSVDelimiter + emptySafe(telefon) + CSVDelimiter + emptySafe(email));
    }

    private static String emptySafe(String s) {
        if (s.isEmpty()) {
            return EMPTY_STRING_CSV;
        }
        return s;
    }

    public static Kontakt fromCSV(String line) {
        return fromCSV(line, STANDART_CSV_DELIMITER);
    }

    public static Kontakt fromCSV(String line,String CSVDelimiter) {
        String[] fields = line.split(CSVDelimiter);

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(EMPTY_STRING_CSV)) {
                fields[i] = "";
            }
        }

        if (fields.length == 4) {
            return new Kontakt(fields[0], fields[1], fields[2], fields[3]);
        }
        return null;
    }
}
