package com.dummy.myerp.consumer.dao.impl.db.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;

public class ComptabiliteDaoImplTest {

	private AbstractApplicationContext context ;
	private ComptabiliteDaoImpl comptabiliteDaoImpl;
	
	@Before
	public void test() {
	// context = new ClassPathXmlApplicationContext("com/dummy/myerp/consumer/test/applicationContext.xml");
	 context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

	 comptabiliteDaoImpl = (ComptabiliteDaoImpl) context.getBean("ComptabiliteDaoImpl");
	}
	
	
	@Test
	public void getListCompteComptableTest() {
	      comptabiliteDaoImpl.setSQLgetListCompteComptable("SELECT * FROM myerp.compte_comptable"); 
	      comptabiliteDaoImpl.getListCompteComptable();
	}
	
	
	@Test
	public void getListJournalComptableTest(){
		comptabiliteDaoImpl.setSQLgetListJournalComptable("SELECT * FROM myerp.journal_comptable");
		comptabiliteDaoImpl.getListJournalComptable();
	}
	
	@Test
	public void getListEcritureComptableTest() {
		comptabiliteDaoImpl.setSQLgetListEcritureComptable("SELECT * FROM myerp.ecriture_comptable");
		comptabiliteDaoImpl.getListEcritureComptable();
	}
	
	@Test
	public void getEcritureComptableTest() throws NotFoundException {
		comptabiliteDaoImpl.setSQLgetEcritureComptable("SELECT * FROM myerp.ecriture_comptable\n" + 
				"                WHERE id = :id");
		comptabiliteDaoImpl.getEcritureComptable(-5);
	}
	
	@Test(expected = NotFoundException.class)
	public void getEcritureComptableNotFoundTest() throws NotFoundException {
		comptabiliteDaoImpl.setSQLgetEcritureComptable("SELECT * FROM myerp.ecriture_comptable\n" + 
				"                WHERE id = :id");
		comptabiliteDaoImpl.getEcritureComptable(5555);
	}
	
	@Test
	public void getEcritureComptableByRefTest() throws Throwable  {
		comptabiliteDaoImpl.setSQLgetEcritureComptableByRef("SELECT * FROM myerp.ecriture_comptable\n" + 
				" 				WHERE reference = :reference");	
			comptabiliteDaoImpl.getEcritureComptableByRef("AC-2016/00001");
	}
	
	@Test(expected = NotFoundException.class)
	public void getEcritureComptableByRefNotFoundTest() throws Throwable  {
		comptabiliteDaoImpl.setSQLgetEcritureComptableByRef("SELECT * FROM myerp.ecriture_comptable\n" + 
				" 				WHERE reference = :reference");
			comptabiliteDaoImpl.getEcritureComptableByRef("AC-1889/00001");
	}

	@Test
	public void b_updateEcritureComptableTest() throws NotFoundException {
		comptabiliteDaoImpl.setSQLupdateEcritureComptable(" UPDATE myerp.ecriture_comptable SET\n" + 
				"                    journal_code = :journal_code,\n" + 
				"                    reference = :reference,\n" + 
				"                    date = :date,\n" + 
				"                    libelle = :libelle\n" + 
				"                WHERE\n" + 
				"                    id = :id");
		comptabiliteDaoImpl.setSQLdeleteListLigneEcritureComptable("DELETE FROM myerp.ligne_ecriture_comptable\n" + 
				"                WHERE ecriture_id = :ecriture_id");
		comptabiliteDaoImpl.setSQLinsertEcritureComptable(" INSERT INTO myerp.ligne_ecriture_comptable (\n" + 
				"                    ecriture_id, ligne_id, compte_comptable_numero, libelle, debit,\n" + 
				"                    credit\n" + 
				"                ) VALUES (\n" + 
				"                    :ecriture_id, :ligne_id, :compte_comptable_numero, :libelle, :debit,\n" + 
				"                    :credit\n" + 
				"                )");
		EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(1);
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
		
		comptabiliteDaoImpl.updateEcritureComptable(vEcritureComptable);
	}
	
	@Test
	public void c_deleteEcritureComptableTest() {
		comptabiliteDaoImpl.setSQLdeleteEcritureComptable("DELETE FROM myerp.ecriture_comptable\n" + 
				"                WHERE id = :id");
		comptabiliteDaoImpl.setSQLdeleteListLigneEcritureComptable("DELETE FROM myerp.ligne_ecriture_comptable\n" + 
				"                WHERE ecriture_id = :ecriture_id");
		comptabiliteDaoImpl.deleteEcritureComptable(-5);
	}
	
	@Test
	public void getSequenceEcritureComptableTest() throws Throwable {
		comptabiliteDaoImpl.setSQLgetSequenceEcritureComptable("SELECT * FROM myerp.sequence_ecriture_comptable\n" + 
				"                WHERE journal_code = :journal_code AND annee = :annee");
		comptabiliteDaoImpl.getSequenceEcritureComptable("AC", 2016);
	}
	
	@Test(expected = NotFoundException.class)
	public void getSequenceEcritureComptableNotfoundTest() throws Throwable {
		comptabiliteDaoImpl.setSQLgetSequenceEcritureComptable("SELECT * FROM myerp.sequence_ecriture_comptable\n" + 
				"                WHERE journal_code = :journal_code AND annee = :annee");
		comptabiliteDaoImpl.getSequenceEcritureComptable("XX", 1889); 
	}
	
	@Test
	public void a_insertSequenceEcritureComptableTest() throws Throwable  {
		SequenceEcritureComptable s = new SequenceEcritureComptable("AC",1887,1);
		comptabiliteDaoImpl.setSQLinsertSequenceEcritureComptable("INSERT INTO myerp.sequence_ecriture_comptable (\n" + 
				"                    journal_code, annee, derniere_valeur\n" + 
				"                ) VALUES (\n" + 
				"                    :journal_code, :annee, :derniere_valeur\n" + 
				"                )");
		comptabiliteDaoImpl.insertSequenceEcritureComptable(s);
	}
	
	@Test 
	public void b_updateSequenceEcritureComptableTest() throws Throwable {
		comptabiliteDaoImpl.setSQLgetSequenceEcritureComptable("SELECT * FROM myerp.sequence_ecriture_comptable\n" + 
				"                WHERE journal_code = :journal_code AND annee = :annee");
		SequenceEcritureComptable s = comptabiliteDaoImpl.getSequenceEcritureComptable("AC",2016);
		s.setDerniereValeur(s.getDerniereValeur()+1);
		comptabiliteDaoImpl.setSQLupdateSequenceEcritureComptable("UPDATE myerp.sequence_ecriture_comptable SET\n" + 
				"                    derniere_valeur = :derniere_valeur\n" + 
				"                WHERE\n" + 
				"                    journal_code = :journal_code AND\n" + 
				"                    annee = :annee");
		comptabiliteDaoImpl.updateSequenceEcritureComptable(s);
	}
	
	@Test
	public void c_deleteSequenceEcritureComptableTest() throws Throwable {
		comptabiliteDaoImpl.setSQLgetSequenceEcritureComptable("SELECT * FROM myerp.sequence_ecriture_comptable\n" + 
				"                WHERE journal_code = :journal_code AND annee = :annee");
		SequenceEcritureComptable s = comptabiliteDaoImpl.getSequenceEcritureComptable("AC",2016);
		
		comptabiliteDaoImpl.setSQLdeleteSequenceEcritureComptable("DELETE FROM myerp.sequence_ecriture_comptable\n" + 
				"                WHERE journal_code = :journal_code AND annee = :annee");
		comptabiliteDaoImpl.deleteSequenceEcritureComptable(s);
	}
	
	@Test
	public void c_loadListLigneEcriture() throws Exception {
		comptabiliteDaoImpl.setSQLloadListLigneEcriture(" SELECT * FROM myerp.ligne_ecriture_comptable\n" + 
				"                WHERE ecriture_id = :ecriture_id\n" + 
				"                ORDER BY ligne_id");
		EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(-5);
		comptabiliteDaoImpl.loadListLigneEcriture(vEcritureComptable);
	}
	
	@After
	public void tearDown() throws Exception {
		context.close();
	}
}
