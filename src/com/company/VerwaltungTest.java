package com.company;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VerwaltungTest {

	private Verwaltung verwaltung;
	private Kontakt testKontakt;

	@BeforeEach
	void setUp() {
		verwaltung = new Verwaltung();
		testKontakt = new Kontakt("Borstelmann", "Ayk", "", "");
	}

	@Test
	public void test_hinzufuegen() {
		Kontakt testKontakt1 = new Kontakt("Ceberat","Liam","","");
		Kontakt testKontakt2 = new Kontakt("Dasberot", "Dominic", "", "");
		Kontakt testKontakt3 = new Kontakt("Dasberot","Jalen","","");

		verwaltung.hinzufuegen(testKontakt3);
		verwaltung.hinzufuegen(testKontakt);
		verwaltung.hinzufuegen(testKontakt2);
		verwaltung.hinzufuegen(testKontakt1);

		verwaltung.kontaktList.toFirst();
		assertEquals(testKontakt, verwaltung.kontaktList.getContent());
		verwaltung.kontaktList.next();
		assertEquals(testKontakt1, verwaltung.kontaktList.getContent());
		verwaltung.kontaktList.next();
		assertEquals(testKontakt2, verwaltung.kontaktList.getContent());
		verwaltung.kontaktList.next();
		assertEquals(testKontakt3, verwaltung.kontaktList.getContent());

	};

	@Test
	public void test_loeschen() {
		verwaltung.hinzufuegen(testKontakt);
		verwaltung.loeschen(testKontakt);

		verwaltung.kontaktList.toFirst();
		assertNotEquals(testKontakt,verwaltung.kontaktList.getContent());

	}

	@Test
	public void test_sucheKontakt() {
		verwaltung.hinzufuegen(testKontakt);
		verwaltung.sucheKontakt(testKontakt);

		verwaltung.kontaktList.toFirst();
		assertEquals(testKontakt,verwaltung.kontaktList.getContent());

	}

	@Test
	void test_asArray() {
		Kontakt testKontakt1 = new Kontakt("Ceberat","Liam","","");
		Kontakt testKontakt2 = new Kontakt("Dasberot", "Dominic", "", "");
		Kontakt testKontakt3 = new Kontakt("Dasberot","Jalen","","");

		verwaltung.hinzufuegen(testKontakt3);
		verwaltung.hinzufuegen(testKontakt);
		verwaltung.hinzufuegen(testKontakt2);
		verwaltung.hinzufuegen(testKontakt1);

		Kontakt[] kontakte = verwaltung.asArray();
		assertArrayEquals(new Kontakt[] {testKontakt,testKontakt1,testKontakt2,testKontakt3},kontakte);
	}
}