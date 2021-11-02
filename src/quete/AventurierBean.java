/**
 * 
 */
package quete;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean pour les cases des aventuriers 
 * @author Marjory
 *
 */
public class AventurierBean extends AbstractCaseBean {
	/**
	 * La liste de mouvements
	 */
	private List<MouvementEnum> mouvements;
	
	/**
	 * L'orientation de l'aventurier
	 */
	private OrientationEnum orientation;
	
	/**
	 * Le nom de l'aventurier
	 */
	private String nom;
	
	/**
	 * Le nombre de trésors ramassés par l'aventurier
	 */
	private Integer nbTresorRamasses;

	/**
	 * Le constructeur
	 * @param type le type de l'element
	 * @param nom le nom de l'aventurier
	 * @param positionH la position Horizontale
	 * @param positionV la position verticale
	 * @param mouvements la sequence de mouvements
	 * @param orientation l'orientation
	 */
	public AventurierBean(String nom, Integer positionH, Integer positionV, List<MouvementEnum> mouvements,
			OrientationEnum orientation) {
		super(TypeElementEnum.A, positionH, positionV);
		this.nom = nom;
		this.mouvements = mouvements;
		this.orientation = orientation;
		nbTresorRamasses = 0;
	}
	
	/**
	 * Getter pour la liste de mouvements
	 * @return the mouvements
	 */
	public List<MouvementEnum> getMouvements() {
		return mouvements;
	}
	
	/**
	 * Setter pour la liste des mouvements
	 * @param mouvements the mouvements to set
	 */
	public void setMouvements(String mouvementsString) {
		mouvements = new ArrayList<>(); 
		for (int i = 0; i < mouvementsString.length(); i++) {
			mouvements.add(MouvementEnum.valueOf(String.valueOf(mouvementsString.charAt(i))));
		}
	}
	
	/**
	 * Getter pour l'orientation
	 * @return the orientation
	 */
	public OrientationEnum getOrientation() {
		return orientation;
	}
	
	/**
	 * Setter orientation
	 * @param orientation the orientation to set
	 */
	public void setOrientation(OrientationEnum orientation) {
		this.orientation = orientation;
	}

	/**
	 * Getter pour le nombre de trésors ramassés 
	 * @return the nbTresorRamasses
	 */
	public Integer getNbTresorRamasses() {
		return nbTresorRamasses;
	}

	/**
	 * Setter pour le nombre de trésors ramassés 
	 * @param nbTresorRamassee the nbTresorRamasses to set
	 */
	public void setNbTresorRamasses(Integer nbTresorRamasses) {
		this.nbTresorRamasses = nbTresorRamasses;
	}

	/**
	 * Add a tresor to nbTresorRamasses
	 */
	public void addTresor() {
		this.nbTresorRamasses++;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mouvements == null) ? 0 : mouvements.hashCode());
		result = prime * result + ((nbTresorRamasses == null) ? 0 : nbTresorRamasses.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AventurierBean other = (AventurierBean) obj;
		if (mouvements == null) {
			if (other.mouvements != null)
				return false;
		} else if (!mouvements.equals(other.mouvements))
			return false;
		if (nbTresorRamasses == null) {
			if (other.nbTresorRamasses != null)
				return false;
		} else if (!nbTresorRamasses.equals(other.nbTresorRamasses))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (orientation != other.orientation)
			return false;
		return true;
	}	
}