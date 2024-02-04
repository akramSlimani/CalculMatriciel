package AlgLin;

public class SysDiagonal extends SysLin{

	public SysDiagonal(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException  {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		
		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);
		
		for(int i =0; i < taille; i++) {
			double elementDiago = this.matriceSystem.getCoef(i, i);
			if (elementDiago == 0) {
	            throw new IrregularSysLinException("Le système est irrégulier (élément de la diagonale nul).");
	        }
			
			double elementSecondMembre = this.getSecondMembre().getCoef(i);
			solution.remplaceCoef(i, 0, elementSecondMembre / elementDiago);
		}
		return solution;
	}

	public static void main(String[] args) throws IrregularSysLinException {
		
		double[][] tab1 = { {2.0, 0.0, 0.0, 0.0}, {0.0, 3.0, 0.0, 0.0}, {0.0, 0.0, 4.0, 0.0}, {0.0, 0.0, 0.0, 2.0}};
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = {4.0, 9.0, 16.0, 4.0};
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysDiagonal s = new SysDiagonal(matriceSys, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());
	}
}
