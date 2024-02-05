package AlgLin;

public class Helder extends SysLin {

	 Matrice l, d, r;
	 
	public Helder(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
		l = new Matrice(matriceSystem.nbLigne(), secondMembre.nbColonne());
	    d = new Matrice(matriceSystem.nbLigne(), secondMembre.nbColonne());
	    r = new Matrice(matriceSystem.nbLigne(), secondMembre.nbColonne());
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		// TODO Auto-generated method stub
		return null;
	}

}
