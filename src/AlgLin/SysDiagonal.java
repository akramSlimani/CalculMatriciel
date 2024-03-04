package AlgLin;

public class SysDiagonal extends SysLin {

	public SysDiagonal(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);

		for (int i = 0; i < taille; i++) {
			double elementDiago = this.matriceSystem.getCoef(i, i);
			if (elementDiago == 0) {
				throw new IrregularSysLinException("élément de la diagonale nul");
			}

			double elementSecondMembre = this.getSecondMembre().getCoef(i);
			solution.remplaceCoef(i, 0, elementSecondMembre / elementDiago);
		}
		return solution;
	}

	public static void main(String[] args) throws IrregularSysLinException {

		double[][] tab1 = { { 8.0, 0.0, 0.0 }, { 0.0, 4.0, 0.0 }, { 0.0, 0.0, 16 } };

		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = { 4.0, 16.0, 9.0 };
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");


		SysDiagonal s = new SysDiagonal(matriceSys, secondMembre);

		Vecteur solution = null;

		try {

			solution = s.resolution();
			System.out.println("solution de A*x = b :\n" + solution);
		} catch (IrregularSysLinException e) {

			e.printStackTrace();
		}
		
		System.out.println("****************    test de la norme ||Ax -b||    **************\n");

		Vecteur v1 = new Vecteur(tab2.length);
		Vecteur v2 = new Vecteur(tab2.length);

		for (int i = 0; i < tab2.length; i++) {

			v2.remplaceCoef(i, 0, Matrice.produit(matriceSys, solution).getCoef(i, 0));
		}

		v1 = Vecteur.soustraction(v2, secondMembre);

		System.out.println("norme L1 = " + v1.normeL1());
		System.out.println("norme L2 = " + v1.normeL2());
		System.out.println("norme Linfini = " + v1.normeLInfini());

		if (v1.normeLInfini() <= Matrice.EPSILON) {

			System.out.println("bonne résolution");
		} else {

			System.out.println("mauvaise résolution");
		}
	}
}
