package quete.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import quete.AbstractCaseBean;
import quete.AventurierBean;
import quete.ManagerCarteAuTresor;
import quete.MouvementEnum;
import quete.OrientationEnum;
import quete.TresorBean;
import quete.TypeElementEnum;

/**
 * Classe de test de ManagerCarteAuTresor
 * @author Marjory
 *
 */
public class ManagerCarteAuTresorTest {

	/**
	 * le manager testé
	 */
	ManagerCarteAuTresor manager = new ManagerCarteAuTresor();

	/**
	 * Méthode de test de la méthode tournerADroite
	 */
	@Test
	public void testTournerADroiteOk() {
		AventurierBean bean = new AventurierBean("user Test", 1, 1, null, OrientationEnum.E);
		manager.tournerADroite(bean);
		Assert.assertEquals("En partant de la position Est, en tournant vers la Droite on doit se retouver en position Sud", 
				OrientationEnum.S, bean.getOrientation());
		manager.tournerADroite(bean);
		Assert.assertEquals("En partant de la position Sud, en tournant vers la Droite on doit se retouver en position Ouest", 
				OrientationEnum.O, bean.getOrientation());
		manager.tournerADroite(bean);
		Assert.assertEquals("En partant de la position Ouest, en tournant vers la Droite on doit se retouver en position Nord", 
				OrientationEnum.N, bean.getOrientation());
		manager.tournerADroite(bean);
		Assert.assertEquals("En partant de la position Nord, en tournant vers la Droite on doit se retouver en position Est", 
				OrientationEnum.E, bean.getOrientation());
	}

	/**
	 * Méthode de test de la méthode tournerAGauche
	 */
	@Test
	public void testTournerAGaucheOk() {
		AventurierBean bean = new AventurierBean("user Test", 1, 1, null, OrientationEnum.E);
		manager.tournerAGauche(bean);
		Assert.assertEquals("En partant de la position Est, en tournant vers la Gauche on doit se retouver en position Nord", 
				OrientationEnum.N, bean.getOrientation());
		manager.tournerAGauche(bean);
		Assert.assertEquals("En partant de la position Nord, en tournant vers la Gauche on doit se retouver en position Ouest", 
				OrientationEnum.O, bean.getOrientation());
		manager.tournerAGauche(bean);
		Assert.assertEquals("En partant de la position Ouest, en tournant vers la Gauche on doit se retouver en position Sud", 
				OrientationEnum.S, bean.getOrientation());
		manager.tournerAGauche(bean);
		Assert.assertEquals("En partant de la position Sud, en tournant vers la Gauche on doit se retouver en position Est", 
				OrientationEnum.E, bean.getOrientation());
	}
	
	/**
	 * Méthode de test de la méthode nettoyerAncienneCase
	 */
	@Test
	public void testNettoyerAncienneCaseTresorOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		TresorBean tresor = new TresorBean(2, 3, 2);
		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);
		tresor.setAventurierPresent(bean);
		carte[2][3] = tresor;
		
		manager.nettoyerAncienneCase(carte, 2, 3);
		TresorBean tresorResult = (TresorBean) carte[2][3];
		Assert.assertTrue("Il ne devrait plus y avaoir de d'aventurier sur la case du trésor", tresorResult.getAventurierPresent() == null);
		
	}
	
	/**
	 * Méthode de test de la méthode nettoyerAncienneCase
	 */
	@Test
	public void testNettoyerAncienneCaseAventurierOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);
		carte[2][3] = bean;
		
		manager.nettoyerAncienneCase(carte, 2, 3);
		Assert.assertTrue("Il ne devrait plus y avaoir de d'aventurier sur la case du trésor", carte[2][3] == null);	
	}
	
	/**
	 * Test mouvement D
	 */
	@Test
	public void testDeplacerAventurierDOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][3] = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);

		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.S);
		
		manager.deplacerAventurier((AventurierBean) carte[2][3], carte, MouvementEnum.D);
		Assert.assertTrue("La nouvelle orientation est erronnée", bean.equals(carte[2][3]));
	}
	
	/**
	 * Test mouvement G
	 */
	@Test
	public void testDeplacerAventurierGOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][3] = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);

		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.N);
		
		manager.deplacerAventurier((AventurierBean) carte[2][3], carte, MouvementEnum.G);
		Assert.assertTrue("La nouvelle orientation est erronnée", bean.equals(carte[2][3]));
	}
	
	/**
	 * Test avancer Est
	 */
	@Test
	public void testDeplacerAventurierAvancerEstOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][3] = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);

		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.E);
		
		bean.setPositionH(3);
		manager.deplacerAventurier((AventurierBean) carte[2][3], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[3][3]));
	}

	/**
	 * Test avancer est impossible, limite sup horizontale atteinte
	 */
	@Test
	public void testDeplacerAventurierAvancerEstImpossibleOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[3][3] = new AventurierBean("user Test", 3, 3, null, OrientationEnum.E);

		AventurierBean bean = new AventurierBean("user Test", 3, 3, null, OrientationEnum.E);
		
		manager.deplacerAventurier((AventurierBean) carte[3][3], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[3][3]));
	}

	/**
	 * Test avancer Nord
	 */
	@Test
	public void testDeplacerAventurierAvancerNordOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[3][3] = new AventurierBean("user Test", 3, 3, null, OrientationEnum.N);

		AventurierBean bean = new AventurierBean("user Test", 3, 2, null, OrientationEnum.N);
		
		manager.deplacerAventurier((AventurierBean) carte[3][3], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[3][2]));
	}

	/**
	 * Test avancer Ouest
	 */
	@Test
	public void testDeplacerAventurierAvancerOuestOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[3][2] = new AventurierBean("user Test", 3, 2, null, OrientationEnum.O);

		AventurierBean bean = new AventurierBean("user Test", 2, 2, null, OrientationEnum.O);
		
		manager.deplacerAventurier((AventurierBean) carte[3][2], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[2][2]));
	}

	/**
	 * Test avancer Sud
	 */
	@Test
	public void testDeplacerAventurierAvancerSudOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][2] = new AventurierBean("user Test", 2, 2, null, OrientationEnum.S);

		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.S);
		
		manager.deplacerAventurier((AventurierBean) carte[2][2], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[2][3]));
	}

	/**
	 * Test avancer sud impossible, limite sup verticale atteinte
	 */
	@Test
	public void testDeplacerAventurierAvancerSudImpossibleOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][3] = new AventurierBean("user Test", 2, 3, null, OrientationEnum.S);

		AventurierBean bean = new AventurierBean("user Test", 2, 3, null, OrientationEnum.S);
		
		manager.deplacerAventurier((AventurierBean) carte[2][3], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[2][3]));
	}

	/**
	 * Test avancer Ouest impossible, limite inf atteinte
	 */
	@Test
	public void testDeplacerAventurierAvancerOuestImpossibleOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[0][0] = new AventurierBean("user Test", 0, 0, null, OrientationEnum.O);

		AventurierBean bean = new AventurierBean("user Test", 0, 0, null, OrientationEnum.O);
		
		carte[0][0] = new AventurierBean("user Test", 0, 0, null, OrientationEnum.O);
		manager.deplacerAventurier((AventurierBean) carte[0][0], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[0][0]));
	}

	/**
	 * Test avancer nord impossible, limite inf atteinte
	 */
	@Test
	public void testDeplacerAventurierAvancerNordImpossibleOk() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[0][0] = new AventurierBean("user Test", 0, 0, null, OrientationEnum.N);

		AventurierBean bean = new AventurierBean("user Test", 0, 0, null, OrientationEnum.N);
		
		manager.deplacerAventurier((AventurierBean) carte[0][0], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[0][0]));
	}
	
	/**
	 * Test avancer Sud sur tresor
	 */
	@Test
	public void testDeplacerAventurierAvancerSud2Ok() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][2] = new AventurierBean("user Test", 2, 2, null, OrientationEnum.S);
		carte[2][3] = new TresorBean(2, 3, 3);

		TresorBean tresor = new TresorBean(2, 3, 2);
		tresor.setAventurierPresent(new AventurierBean("user Test", 2, 3, null, OrientationEnum.S));
		
		manager.deplacerAventurier((AventurierBean) carte[2][2], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", tresor.equals(carte[2][3]));
	}
	
	/**
	 * Test avancer Sud sur tresor
	 */
	@Test
	public void testDeplacerAventurierAvancerSud3Ok() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][2] = new AventurierBean("user Test", 2, 2, null, OrientationEnum.S);
		carte[2][3] = new TresorBean(2, 3, 0);

		TresorBean tresor = new TresorBean(2, 3, 2);
		tresor.setAventurierPresent(new AventurierBean("user Test", 2, 3, null, OrientationEnum.S));
		
		manager.deplacerAventurier((AventurierBean) carte[2][2], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", tresor.equals(carte[2][3]));
	}
	
	/**
	 * Test avancer Sud montagne
	 */
	@Test
	public void testDeplacerAventurierAvancerSud4Ok() {
		AbstractCaseBean[][] carte = new AbstractCaseBean[4][4];
		carte[2][2] = new AventurierBean("user Test", 2, 2, null, OrientationEnum.S);
		carte[2][3] = new AbstractCaseBean(TypeElementEnum.M, 2, 3);

		AventurierBean bean = new AventurierBean("user Test", 2, 2, null, OrientationEnum.S);
		
		manager.deplacerAventurier((AventurierBean) carte[2][2], carte, MouvementEnum.A);
		Assert.assertTrue("La nouvelle position est erronnée", bean.equals(carte[2][2]));
	}
	
	/**
	 * Test de la méthode getListMvt
	 */
	@Test
	public void testGetListMvtOk() {
		List<MouvementEnum> listMvtExpected = Arrays.asList(MouvementEnum.A, MouvementEnum.G, MouvementEnum.D);
		Assert.assertEquals("La liste de mouvements retournée est erronnée", listMvtExpected, manager.getListMvt("AGD"));
	}
	
	/**
	 * Test de la méthode remplirListeDonnees pour une montagne
	 */
	@Test
	public void testRemplirListeDonneesAventurierOk() {
		List<AventurierBean> aventuriers = new ArrayList<>();
		String[] donneesLigne = {"A", "user Test", "1", "3", "S", "AGDA" };
		AbstractCaseBean[][] carte = new AbstractCaseBean[3][5];
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		AventurierBean bean = new AventurierBean("user Test", 1, 3, Arrays.asList(MouvementEnum.A, MouvementEnum.G,
				MouvementEnum.D, MouvementEnum.A), OrientationEnum.S);
		int maxMouvementsAventurier = manager.remplirListeDonnees(aventuriers, donneesLigne, carte, casesFigees, tresors);
		Assert.assertEquals("Un aventurier a été ajouté a la liste", 1, aventuriers.size());
		Assert.assertEquals("L'aventurier ajouté est correct", bean, aventuriers.get(0));
		Assert.assertEquals("L'aventurier a été ajouté à la bonne place", bean, carte[1][3]);
		Assert.assertEquals("Le max de mouvements d'aventurier est 4", 4, maxMouvementsAventurier);
		Assert.assertEquals("La liste des cases figées est vide", 0, casesFigees.size());
		Assert.assertEquals("La liste des trésors est vide", 0, tresors.size());
		
	}
	
	/**
	 * Test de la méthode remplirListeDonnees pour une montagne
	 */
	@Test
	public void testRemplirListeDonneesAventurierKo() {
		List<AventurierBean> aventuriers = new ArrayList<>();
		String[] donneesLigne = {"A", "user Test", "1", "13", "S", "AGDA" };
		AbstractCaseBean[][] carte = new AbstractCaseBean[3][5];
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		int maxMouvementsAventurier = manager.remplirListeDonnees(aventuriers, donneesLigne, carte, casesFigees, tresors);
		Assert.assertEquals("Le max de mouvements d'aventurier est 0", 0, maxMouvementsAventurier);
		Assert.assertEquals("La liste des cases figées est vide", 0, casesFigees.size());
		Assert.assertEquals("La liste des trésors est vide", 0, tresors.size());
		Assert.assertEquals("La liste des aventuriers est vide", 0, aventuriers.size());
		
	}
	
	/**
	 * Test de la méthode remplirListeDonnees pour une montagne
	 */
	@Test
	public void testRemplirListeDonneesMontagneOk() {
		List<AventurierBean> aventuriers = new ArrayList<>();
		String[] donneesLigne = {"M", "1", "3"};
		AbstractCaseBean[][] carte = new AbstractCaseBean[3][5];
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		AbstractCaseBean bean = new AbstractCaseBean(TypeElementEnum.M, 1, 3);
		int maxMouvementsAventurier = manager.remplirListeDonnees(aventuriers, donneesLigne, carte, casesFigees, tresors);
		Assert.assertEquals("Une montagne e été ajouté a la liste", 1, casesFigees.size());
		Assert.assertEquals("La montagne ajoutée est correct", bean, casesFigees.get(0));
		Assert.assertEquals("La montagne a été ajoutée à la bonne place", bean, carte[1][3]);
		Assert.assertEquals("Le max de mouvements d'aventurier est 0", 0, maxMouvementsAventurier);
		Assert.assertEquals("La liste des aventuriers est vide", 0, aventuriers.size());
		Assert.assertEquals("La liste des trésors est vide", 0, tresors.size());
	}
	
	/**
	 * Test de la méthode remplirListeDonnees pour une montagne
	 */
	@Test
	public void testRemplirListeDonneesTresorOk() {
		List<AventurierBean> aventuriers = new ArrayList<>();
		String[] donneesLigne = {"T", "1", "3", "5"};
		AbstractCaseBean[][] carte = new AbstractCaseBean[3][5];
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		TresorBean bean = new TresorBean( 1, 3, 5);
		int maxMouvementsAventurier = manager.remplirListeDonnees(aventuriers, donneesLigne, carte, casesFigees, tresors);
		Assert.assertEquals("Un trésor e été ajouté a la liste", 1, tresors.size());
		Assert.assertEquals("Le trésor ajouté est correct", bean, tresors.get(0));
		Assert.assertEquals("Le trésor a été ajouté à la bonne place", bean, carte[1][3]);
		Assert.assertEquals("Le max de mouvements d'aventurier est 0", 0, maxMouvementsAventurier);
		Assert.assertEquals("La liste des cases figées est vide", 0, casesFigees.size());
		Assert.assertEquals("La liste des aventuriers est vide", 0, aventuriers.size());
	}
	
	/**
	 * Test de la méthode remplirListeDonnees pour une montagne
	 */
	@Test
	public void testRemplirListeDonneesCarteOk() {
		List<AventurierBean> aventuriers = new ArrayList<>();
		String[] donneesLigne = {"L", "3", "5"};
		AbstractCaseBean[][] carteBase = new AbstractCaseBean[1][1];
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		
		int maxMouvementsAventurier = manager.remplirListeDonnees(aventuriers, donneesLigne, carteBase, casesFigees, tresors);
		Assert.assertEquals("Le max de mouvements d'aventurier est 0", 0, maxMouvementsAventurier);
		Assert.assertEquals("La liste des trésors est vide", 0, tresors.size());
		Assert.assertEquals("La liste des aventuriers est vide", 0, aventuriers.size());
	}
}
