package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CompteComptableTest {

	private static CompteComptable compteComptable;
	private static List<CompteComptable> list;
	private static int numero = 5;
	private static String libelle ="libelle";
	
	@Test
	public void testGetByNumero() {
		compteComptable = new CompteComptable();
		list =  new ArrayList<>(0);
		
		compteComptable.setNumero(numero);
		compteComptable.setLibelle(libelle);
		
		list.add(compteComptable);
		
		assertEquals(CompteComptable.getByNumero(list, 5), compteComptable);
		
		
	}
	
	@Test
	public void testToString() {
		compteComptable = new CompteComptable(numero,libelle);

        assertEquals(compteComptable.toString(),
                "CompteComptable" +
                        "{numero=5, libelle='libelle'}");
}

}
