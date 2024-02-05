package AlgLin;

public abstract class SysLin {

	private int ordre;
	protected Matrice matriceSystem;
	protected Vecteur secondMembre;

	public SysLin(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		if (matriceSystem.nbLigne() == matriceSystem.nbColonne()
				&& matriceSystem.nbLigne() == secondMembre.getTaille()) {
			this.ordre = matriceSystem.nbLigne();
			this.matriceSystem = matriceSystem;
			this.secondMembre = secondMembre;
		} else {
			throw new IrregularSysLinException("La matrice doit être carrée et de même taille que le second membre.");
		}

	}

	public int getOrdre() {
		return ordre;
	}

	public Matrice getMatriceSystem() {
		return matriceSystem;
	}

	public Vecteur getSecondMembre() {
		return secondMembre;
	}

	public abstract Vecteur resolution() throws IrregularSysLinException;

}
