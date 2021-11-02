/**
 * 
 */
package quete;

/**
 * Une case de tresor
 * @author Marjory
 *
 */
public class TresorBean extends AbstractCaseBean {
	
	/**
	 * Le nombre de tresor restant
	 */
	private Integer nbTresor;
	
	/**
	 * Aventurier qui serait pr�sent sur la m�me case que le tr�sor
	 */
	private AventurierBean aventurierPresent;

	/**
	 * Le constructeur
	 * @param type le type de l'element
	 * @param positionH la position Horizontale
	 * @param positionV la position verticale
	 * @param nbTresor
	 */
	public TresorBean(Integer positionH, Integer positionV, Integer nbTresor) {
		super(TypeElementEnum.T, positionH, positionV);
		this.nbTresor = nbTresor;
	}

	/**
	 * Getter pour le nombre de tresor restant sur la case
	 * @return the nbTresor
	 */
	public Integer getNbTresor() {
		return nbTresor;
	}

	/**
	 * Setter pour le nombre de tresor restant sur la case
	 * @param nbTresor the nbTresor to set
	 */
	public void setNbTresor(Integer nbTresor) {
		this.nbTresor = nbTresor;
	}

	/**
	 * Getter pour l'aventurier
	 * @return the aventurierPresent
	 */
	public AventurierBean getAventurierPresent() {
		return aventurierPresent;
	}

	/**
	 * Setter pour l'aventurier
	 * @param aventurierPresent the aventurierPresent to set
	 */
	public void setAventurierPresent(AventurierBean aventurierPresent) {
		this.aventurierPresent = aventurierPresent;
	}
	
	/**
	 * M�thode qui retire un tr�sor s'il y en a 
	 */
	public void removeTresor() {
		if (nbTresor > 0)
			nbTresor--;
	}
	
	/**
	 * M�thode qui retire l'aventurier de la case du tresor
	 */
	public void removeAventurier() {
		aventurierPresent = null;
	}
}
