package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceEcritureComptableTest {
	
	private static SequenceEcritureComptable sequenceEcritureComptable;
	private static Integer annee = 2019;
	private static Integer derniereValeur = 3639;
	private static String journalCode = "AC";

	@Test
	public void testSequenceEcritureComptable() {
		
		sequenceEcritureComptable = new SequenceEcritureComptable();
		
		sequenceEcritureComptable.setAnnee(annee);
		sequenceEcritureComptable.setDerniereValeur(derniereValeur);
		
		assertEquals(annee, sequenceEcritureComptable.getAnnee());
		
	}

	@Test
	public void testToString() {
		sequenceEcritureComptable = new SequenceEcritureComptable(journalCode,annee,derniereValeur); 
		System.out.println(sequenceEcritureComptable.toString());
		assertEquals(sequenceEcritureComptable.toString(),
                "SequenceEcritureComptable" +
                        "{journalCode=AC, annee=2019, derniereValeur=3639}");
	}

}
