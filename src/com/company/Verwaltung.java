package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Klasse Verwaltung.
 *
 * @author
 */

public class Verwaltung {
	/*
	Lieber Herr Gutsche (oder wer auch sonst das ließt), wenn Sie das lesen und sich fragen, ob diese Methode nicht "private" sein sollte,
	dann würde ich sagen, dass ich auf dieses Objekt aufgrund der Tests (siehe VerwaltungTest)
	zugreifen möchte. Somit ist das Objekt package-private (https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)

	Liebe Grüße Ayk
	*/
	List<Kontakt> kontaktList = new List<>();
	private final Path path = Paths.get("out/adressbook.csv");

	/**
	 * Konstruktor fuer Objekte der Klasse Verwaltung
	 */
	public Verwaltung() {
		ladeVonDateisystem();
	}

	public void hinzufuegen(Kontakt k) {
		kontaktList.toFirst();
		while (kontaktList.hasAccess()) {
			if (kontaktList.getContent().isGreater(k)) {
				kontaktList.insert(k);
				return;
			}
			kontaktList.next();
		}
		kontaktList.append(k);
	}

	public void hinzufuegenUndSpeichern(Kontakt kontakt) {
		hinzufuegen(kontakt);
		speichereAenderung();
	}

	public void loeschen(Kontakt k) {
		kontaktList.toFirst();
		while (kontaktList.hasAccess()) {
			if (kontaktList.getContent().isEqual(k)) {
				kontaktList.remove();
			}
			kontaktList.next();
		}

		speichereAenderung();
	}


	public Kontakt sucheKontakt(Kontakt k) {
		kontaktList.toFirst();
		while (kontaktList.hasAccess()) {
			if (kontaktList.getContent().isEqual(k)) {
				return kontaktList.getContent();
			}
			kontaktList.next();
		}
		return null;
	}

	public Kontakt[] asArray() {
		int length = 0;

		kontaktList.toFirst();
		while (kontaktList.hasAccess()) {
			length++;
			kontaktList.next();
		}

		Kontakt[] kontakte = new Kontakt[length];
		int counter = 0;

		kontaktList.toFirst();
		while (kontaktList.hasAccess()) {
			kontakte[counter] = kontaktList.getContent();
			counter++;
			kontaktList.next();
		}

		return kontakte;
	}

	public void ladeVonDateisystem() {
		try (
			FileInputStream fileInputStream = new FileInputStream(path.toString());
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
		) {

			String header = reader.readLine();

			String line = reader.readLine();
			while (line != null) {
				Kontakt kontakt = Kontakt.fromCSV(line);
				hinzufuegen(kontakt);

				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void speichereAenderung() {
		Kontakt[] kontakte = asArray();
		String[] lines = Stream.of(kontakte).map(Kontakt::asCSV).toArray(String[]::new);

		try (
			FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)
		) {
			String header = Kontakt.getCSVHeader();

			outputStreamWriter.write(header);
			outputStreamWriter.write(System.lineSeparator());

			for (String line : lines) {
				outputStreamWriter.write(line);
				outputStreamWriter.write(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
