/**
 * 
 */
package quete;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Classe main de la carte au tr�sor
 * @author Marjory
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenue dans la carte au tr�sor ");  
		Scanner sc= new Scanner(System.in); 
		System.out.print("Entrer le chemin complet du fichier d'entr�e: ");  
		//On r�cup�re le chemin du fichier d'entr�e
		String fichierDEntree = sc.nextLine();                 
		System.out.print("Entrer le chemin complet du fichier de sortie: "); 
		//On r�cup�re le chemin du fichier de sortie  
		String fichierDeSortie = sc.nextLine();   
		sc.close();
		String separateur = " - ";
		int maxNbrMvt = 0;
		List<AventurierBean> aventuriers = new ArrayList<>();
		List<AbstractCaseBean> casesFigees = new ArrayList<>();
		List<TresorBean> tresors = new ArrayList<>();
		ManagerCarteAuTresor manager = new ManagerCarteAuTresor();	
		AbstractCaseBean[][] carte = new AbstractCaseBean[1][1]; //initialisation quelconque
		try (Stream<String> stream = Files.lines(Paths.get(fichierDEntree), Charset.forName("ISO-8859-1"))) {
			Iterator<String> iterator = stream.iterator();
			while (iterator.hasNext()) {
				String ligne = iterator.next();
				String[] values = ligne.split(separateur);
				// Les lignes commen�ant par # sont ignor�e
				if (!ligne.startsWith("#")) {
					if (values[0].equalsIgnoreCase("C")) {
						// On initialise la carte
						carte = new AbstractCaseBean[Integer.valueOf(values[1])][Integer.valueOf(values[2])];
						casesFigees.add(new AbstractCaseBean(TypeElementEnum.C, Integer.valueOf(values[1]), Integer.valueOf(values[2])));
					} else {
						// Ajout de montagnes ou tr�sors ou aventuriers
						maxNbrMvt = manager.remplirListeDonnees (aventuriers, values, carte, casesFigees, tresors);
					}
				}
			}
			//on d�place les aventurier pr�sents
			for (int i = 0; i < maxNbrMvt; i++) {
				for (AventurierBean aventurier : aventuriers) {
					if (!aventurier.getMouvements().isEmpty()) {
						manager.deplacerAventurier(aventurier, carte, aventurier.getMouvements().get(i));
					}
				}
			}
			//cr�ation du fichier de sortie
			manager.creerFichierDeSortie(fichierDeSortie, aventuriers, casesFigees, tresors, carte);

			System.out.printf("C'est termin�e, retrouv�e le fichier de sortie � l'emplacement suivant: %s", fichierDeSortie);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
