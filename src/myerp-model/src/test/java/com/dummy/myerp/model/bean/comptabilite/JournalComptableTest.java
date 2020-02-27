package com.dummy.myerp.model.bean.comptabilite;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JournalComptableTest {

	private static JournalComptable journal;
    private static List<JournalComptable> list;
    private static String code = "code";
    private static String libelle = "libelle";
    


	@Test
	public void testGetByCode() {
		journal = new JournalComptable();
		list =  new ArrayList<>(0);
		
		journal.setCode(code);
		journal.setLibelle(libelle);
		
		list.add(journal);
		 
		assertEquals(JournalComptable.getByCode(list, "code"), journal);
		
		 
	}

	@Test
	public void testToString() {
		journal = new JournalComptable(code,libelle);

        assertEquals(journal.toString(),
                "JournalComptable" +
                        "{code='code', libelle='libelle'}");
}
	
}
