package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;



public class ComptabiliteManagerImplTest {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    private ApplicationContext applicationContext ;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void addReferenceUpdateTest() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(1);
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        
        SequenceEcritureComptable vSequence = new SequenceEcritureComptable("AC", 2019, 1);

        DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
        ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
        TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
        
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        Mockito.when(comptabiliteDao.getSequenceEcritureComptable("AC", 2019)).thenReturn(vSequence);
       
        manager.configure(null, daoProxy, transactionManager);
        manager.addReference(vEcritureComptable);

        Assert.assertEquals(vEcritureComptable.toString(), "AC-2019/00002", vEcritureComptable.getReference());
    }

    @SuppressWarnings("static-access")
   	@Test
       public void addReferencenewsequenceTest() throws Exception {
    	
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(1);
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        
        
        DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
        ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
        TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
        
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        Mockito.doThrow(NotFoundException.class).when(comptabiliteDao).getSequenceEcritureComptable("AC", 2019);
        manager.configure(null, daoProxy, transactionManager);
        manager.addReference(vEcritureComptable);
        
        

    }
    @Test
    public void checkEcritureComptable() throws Exception  {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(1);
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        
        
        DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
        ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        
        Mockito.when(comptabiliteDao.getEcritureComptableByRef("AC-2019/00001")).thenReturn(vEcritureComptable);

        AbstractBusinessManager.configure(null, daoProxy, null);
        manager.checkEcritureComptable(vEcritureComptable);  
        
        Assert.assertEquals(vEcritureComptable.toString(), "AC-2019/00001", vEcritureComptable.getReference());
    }
    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, null,
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                null));
        
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }
    
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Code() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AX-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
         
        manager.checkEcritureComptableUnit(vEcritureComptable);
        
    }
    
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Date() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        String errorDate = "2000";
        vEcritureComptable.setReference("AC-" + errorDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
         
        manager.checkEcritureComptableUnit(vEcritureComptable);
        
    }
    
    @Test
    public void checkEcritureComptableContext() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(1);
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setReference("AC-2019/00001");
        vEcritureComptable.setLibelle("Libellé");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
        ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        Mockito.when(comptabiliteDao.getEcritureComptableByRef("AC-2019/00001")).thenReturn(vEcritureComptable);

        AbstractBusinessManager.configure(null, daoProxy, null);
        manager.checkEcritureComptableContext(vEcritureComptable);
        
    }
    
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableContextNullId() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setReference("AC-2019/00001");
        vEcritureComptable.setLibelle("Libellé");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
        ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        Mockito.when(comptabiliteDao.getEcritureComptableByRef("AC-2019/00001")).thenReturn(vEcritureComptable);

        AbstractBusinessManager.configure(null, daoProxy, null);
        manager.checkEcritureComptableContext(vEcritureComptable);
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void a_checkDeleteEcritureComptable() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setId(1);
        vEcritureComptable.setDate(new Date());
        String vDate = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        vEcritureComptable.setReference("AC-" + vDate + "/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
          	
		applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        manager.deleteEcritureComptable(1);

    }
    
    @Test
    public void b_checkGetterList() throws Exception {

        manager.getListCompteComptable();
        manager.getListEcritureComptable();
        manager.getListJournalComptable();
    }
}
