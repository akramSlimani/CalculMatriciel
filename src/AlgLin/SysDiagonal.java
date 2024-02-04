package AlgLin;

public class SysDiagonal extends SysLin{

	public SysDiagonal(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException  {
		super(matriceSystem, secondMembre);
		if(!estDiagonale(matriceSystem)) {
			throw new IrregularSysLinException("la matrice n'est pas diagonale");
		}
	}

	private boolean estDiagonale(Matrice matrice) {
		for(int i = 0; i < matrice.nbLigne(); i++) {
			for(int j = 0; j < matrice.nbLigne(); j++) {
			    if( i != j && matrice.getCoef(i, j) != 0)  {
				return false;
			}
		  }
		}
		return true;
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		int taille = getOrdre();
		if(!estDiagonale(matriceSystem)) {
	        throw new IrregularSysLinException("Le système n'est pas diagonal.");
		}
		
		Vecteur matriceSolution = new Vecteur(taille);
		for(int i =0; i < taille; i++) {
			double elementDiago = matriceSystem.getCoef(i, i);
			if (elementDiago == 0) {
	            throw new IrregularSysLinException("Le système est irrégulier (élément de la diagonale nul).");
	        }
			
			double elementSecondMembre = secondMembre.getCoef(i, 0);
			matriceSolution.remplaceCoef(i, 0, elementSecondMembre / elementDiago);
		}
		return matriceSolution;
	}

	public static void main(String[] args) throws IrregularSysLinException {
		
		double[][] tab1 = { {2, 0, 0, 0}, {0, 3, 0, 0}, {0, 0, 4, 0}, {0, 0, 0, 2}};
		Matrice matriceSys = new Matrice(tab1);
		
		double[] tab2 = {4, 9, 16, 4};
		Vecteur secondMembre = new Vecteur(tab2);
		
		SysDiagonal s = new SysDiagonal(matriceSys, secondMembre); 
		System.out.println("la solution de ce système :\n" + s.resolution());
	}
}
