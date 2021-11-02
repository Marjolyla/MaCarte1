/**
 * 
 */
package quete;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager pour le traitement de la carte au trésor
 * @author Marjory
 *
 */
public final class ManagerCarteAuTresor {

	/**
	 * Constructeur par défaut
	 */
	public ManagerCarteAuTresor() {
		super();
	}

	/**
	 * Cette méthode gère la création du fichier de sortie
	 * @param separateur le separateur
	 * @param separateur 
	 * @param aventuriers la liste des aventuriers
	 * @param casesFigees la liste des donées figées
	 * @param tresors la liste des trésors
	 * @param carte la carte
	 * @throws IOException
	 */
	public void creerFichierDeSortie(String fichierDeSortie, List<AventurierBean> aventuriers,
			List<AbstractCaseBean> casesFigees, List<TresorBean> tresors, AbstractCaseBean[][] carte)
			throws IOException {
		String separateur = " - ";
		File file = new File(fichierDeSortie);
		
		// créer le fichier s'il n'existe pas
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		// crée les lignes C et M
		for (AbstractCaseBean bean : casesFigees) {
			StringBuilder builder = new StringBuilder();
			builder.append(bean.getType().name()).append(separateur).append(bean.getPositionH()).append(separateur)
				.append(bean.getPositionV());
			bw.write(builder.toString());
			bw.newLine();
		}
		// crée les lignes T
		bw.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
		bw.newLine();
		for (TresorBean bean : tresors) {
			StringBuilder builder = new StringBuilder();
			Integer tresorRestants = ((TresorBean) carte[bean.getPositionH()][bean.getPositionV()]).getNbTresor();
			if (tresorRestants > 0) {
				builder.append(bean.getType().name()).append(separateur).append(bean.getPositionH()).append(separateur)
					.append(bean.getPositionV()).append(separateur).append(tresorRestants);
				bw.append(builder.toString());
				bw.newLine();
			}
		}
		// crée les lignes A
		bw.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}");
		bw.newLine();
		for (AventurierBean bean : aventuriers) {
			StringBuilder builder = new StringBuilder();
			builder.append(bean.getType().name()).append(separateur).append(bean.getNom()).append(separateur).append(bean.getPositionH())
				.append(separateur).append(bean.getPositionV()).append(separateur).append(bean.getOrientation()).append(separateur)
				.append(bean.getNbTresorRamasses());
			bw.append(builder.toString());
			bw.newLine();
		}
		bw.close();
	}
	
	/**
	 * récupère les infos d'une ligne de données
	 * @param aventuriers la liste des données
	 * @param tresors 
	 * @param casesFigees 
	 * @param ligne la ligne de données
	 * @return 
	 */
	public int remplirListeDonnees (List<AventurierBean> aventuriers, String[] donneesLigne, AbstractCaseBean[][] carte, List<AbstractCaseBean> casesFigees, List<TresorBean> tresors) {
		int maxMvtAventurier = 0;
		TypeElementEnum type;
		try {
			type = TypeElementEnum.valueOf(donneesLigne[0].trim());
		
			switch (type) {
			case M:
				AbstractCaseBean montagne = new AbstractCaseBean(type, Integer.valueOf(donneesLigne[1]), Integer.valueOf(donneesLigne[2]));
				carte[Integer.valueOf(donneesLigne[1])][Integer.valueOf(donneesLigne[2])] = montagne;
				casesFigees.add(montagne);
				break;
			case T:
				TresorBean tresor = new TresorBean(Integer.valueOf(donneesLigne[1]), Integer.valueOf(donneesLigne[2]), Integer.valueOf(donneesLigne[3]));
				carte[Integer.valueOf(donneesLigne[1])][Integer.valueOf(donneesLigne[2])] = tresor;
				tresors.add(tresor);
				break;
			case A:
				List<MouvementEnum> listMvt = getListMvt(donneesLigne[5].trim());
				AventurierBean bean = new AventurierBean(donneesLigne[1], Integer.valueOf(donneesLigne[2]), Integer.valueOf(donneesLigne[3]), 
						listMvt, OrientationEnum.valueOf(donneesLigne[4]));
				carte[Integer.valueOf(donneesLigne[2])][Integer.valueOf(donneesLigne[3])] = bean;
				aventuriers.add(bean);
				maxMvtAventurier = Integer.max(maxMvtAventurier, listMvt.size());
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException e) {
			System.out.printf("Le type de cette ligne de données est inconnue", donneesLigne.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.printf("La position de l'élément est en dehors de la carte", donneesLigne.toString());
		}
		
		return maxMvtAventurier;
	}
	
	/**
	 * recupère la liste de mouvement à partir d'une string
	 * @param mvtString string 
	 * @return la liste des mouvement
	 */
	public List<MouvementEnum> getListMvt(String mvtString){
		List<MouvementEnum> listMvt = new ArrayList<>();
		for (int i = 0; i < mvtString.length(); i++) {
			listMvt.add(MouvementEnum.valueOf(mvtString.substring(i, i+1)));
		}
		return listMvt;
	}
	
	/**
	 * Methode qui gère le déplacement aventuriers
	 * @param bean l'aventurier
	 * @param carte la carte
	 * @return l'aventurier
	 */
	public void deplacerAventurier (AventurierBean bean, AbstractCaseBean[][] carte, MouvementEnum currentMvt) {
		
		switch (currentMvt) {
		case D :
			tournerADroite(bean);
			break;
		case G:
			tournerAGauche(bean);
			break;
		case A:
			Integer newPositionH = bean.getPositionH();
			Integer newPositionV = bean.getPositionV();
			Integer oldPositionH = bean.getPositionH();
			Integer oldPositionV = bean.getPositionV();
			switch (bean.getOrientation()) {
			case E:
				newPositionH++;
				break;
			case N:
				newPositionV--;
				break;
			case O:
				newPositionH--;
				break;
			case S:
				newPositionV++;
				break;
			}
			if (newPositionH < carte.length && newPositionH >= 0 
					&& newPositionV < carte[0].length && newPositionV >= 0) {
				AbstractCaseBean cible = carte[newPositionH][newPositionV];
				if (cible == null) {
					bean.setPositionH(newPositionH);
					bean.setPositionV(newPositionV);
					carte[newPositionH][newPositionV] = bean;
					nettoyerAncienneCase(carte, oldPositionH, oldPositionV);
				} else if (TypeElementEnum.T.equals(cible.getType())) {
					TresorBean tresor = (TresorBean) cible;
					if (tresor.getAventurierPresent() == null) {
						bean.setPositionH(newPositionH);
						bean.setPositionV(newPositionV);
						if (tresor.getNbTresor() > 0)
							bean.addTresor();
						tresor.setAventurierPresent(bean);
						tresor.removeTresor();
					}
					nettoyerAncienneCase(carte, oldPositionH, oldPositionV);
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Methode qui nettoie l'ancien emplacement de l'aventurier
	 * @param carte
	 * @param oldPositionH
	 * @param oldPositionV
	 */
	public void nettoyerAncienneCase(AbstractCaseBean[][] carte, Integer oldPositionH, Integer oldPositionV) {
		if (TypeElementEnum.T.equals(carte[oldPositionH][oldPositionV].getType())) {
			TresorBean source = (TresorBean) carte[oldPositionH][oldPositionV];
			source.removeAventurier();
		} else {
			carte[oldPositionH][oldPositionV] = null;
		}
	}

	/**
	 * Méthode qui permet à l'aventurier de pivoter vers la droite
	 * @param bean l'aventurier
	 */
	public void tournerADroite(AventurierBean bean) {
		switch (bean.getOrientation()) {
		case N:
			bean.setOrientation(OrientationEnum.E);
			break;
		case E:
			bean.setOrientation(OrientationEnum.S);
			break;
		case S:
			bean.setOrientation(OrientationEnum.O);
			break;
		case O:
			bean.setOrientation(OrientationEnum.N);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Méthode qui permet à l'aventurier de pivoter vers la gauche
	 * @param bean l'aventurier
	 */
	public void tournerAGauche(AventurierBean bean) {
		switch (bean.getOrientation()) {
		case N:
			bean.setOrientation(OrientationEnum.O);
			break;
		case O:
			bean.setOrientation(OrientationEnum.S);
			break;
		case S:
			bean.setOrientation(OrientationEnum.E);
			break;
		case E:
			bean.setOrientation(OrientationEnum.N);
			break;
		default:
			break;
		}
	}

}
