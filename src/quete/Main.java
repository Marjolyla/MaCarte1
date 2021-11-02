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
 * Classe main de la carte au trésor
 * @author Marjory
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenue dans la carte au trésor ");  
		Scanner sc= new Scanner(System.in); 
		System.out.print("Entrer le chemin complet du fichier d'entrée: ");  
		//On récupère le chemin du fichier d'entrée
		String fichierDEntree = sc.nextLine();                 
		System.out.print("Entrer le chemin complet du fichier de sortie: "); 
		//On récupère le chemin du fichier de sortie  
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
				// Les lignes commençant par # sont ignorée
				if (!ligne.startsWith("#")) {
					if (values[0].equalsIgnoreCase("C")) {
						// On initialise la carte
						carte = new AbstractCaseBean[Integer.valueOf(values[1])][Integer.valueOf(values[2])];
						casesFigees.add(new AbstractCaseBean(TypeElementEnum.C, Integer.valueOf(values[1]), Integer.valueOf(values[2])));
					} else {
						// Ajout de montagnes ou trésors ou aventuriers
						maxNbrMvt = manager.remplirListeDonnees (aventuriers, values, carte, casesFigees, tresors);
					}
				}
			}
			//on déplace les aventurier présents
			for (int i = 0; i < maxNbrMvt; i++) {
				for (AventurierBean aventurier : aventuriers) {
					if (!aventurier.getMouvements().isEmpty()) {
						manager.deplacerAventurier(aventurier, carte, aventurier.getMouvements().get(i));
					}
				}
			}
			//création du fichier de sortie
			manager.creerFichierDeSortie(fichierDeSortie, aventuriers, casesFigees, tresors, carte);

			System.out.printf("C'est terminée, retrouvée le fichier de sortie à l'emplacement suivant: %s", fichierDeSortie);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
