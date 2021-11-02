/**
 * 
 */
package quete;

/**
 * L'abstract des cases de la carte
 * @author Marjory
 *
 */
public class AbstractCaseBean {
	/**
	 * le type d'element
	 */
	private TypeElementEnum type;
	
	/**
	 * Position Horizontale
	 */
	private Integer positionH;
	
	/**
	 * Position verticale
	 */
	private Integer positionV;

	/**
	 * Le constructeur
	 * @param type le type de l'element
	 * @param positionH la position Horizontale
	 * @param positionV la position verticale
	 */
	public AbstractCaseBean(TypeElementEnum type, Integer positionH, Integer positionV) {
		super();
		this.type = type;
		this.positionH = positionH;
		this.positionV = positionV;
	}

	/**
	 * Getter du type de l'element
	 * @return the type
	 */
	public TypeElementEnum getType() {
		return type;
	}

	/**
	 * Getter de la position Horizontale
	 * @return the positionH
	 */
	public Integer getPositionH() {
		return positionH;
	}

	/**
	 * Setter de la position Horizontale
	 * @param positionH the positionH to set
	 */
	public void setPositionH(Integer positionH) {
		this.positionH = positionH;
	}

	/**
	 * Getter de la position verticale
	 * @return the positionV
	 */
	public Integer getPositionV() {
		return positionV;
	}

	/**
	 * Setter de la position verticale
	 * @param positionV the positionV to set
	 */
	public void setPositionV(Integer positionV) {
		this.positionV = positionV;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((positionH == null) ? 0 : positionH.hashCode());
		result = prime * result + ((positionV == null) ? 0 : positionV.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCaseBean other = (AbstractCaseBean) obj;
		if (positionH == null) {
			if (other.positionH != null)
				return false;
		} else if (!positionH.equals(other.positionH))
			return false;
		if (positionV == null) {
			if (other.positionV != null)
				return false;
		} else if (!positionV.equals(other.positionV))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
