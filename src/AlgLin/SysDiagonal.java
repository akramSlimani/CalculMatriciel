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
		// exemple du td : (les diagonaux sont tous égaux à 1 ) :
		System.out.println("**********************       EXEMPLE1 : juste       ********************************\n");
		double[][] tab1 = { { 2.0, 0.0, 0.0, 0.0 }, { 0.0, 3.0, 0.0, 0.0 }, { 0.0, 0.0, 4.0, 0.0 },
				{ 0.0, 0.0, 0.0, 2.0 } };
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = { 3.0, 1.0, 2.0, 1.0 };
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");
		SysDiagonal s = new SysDiagonal(matriceSys, secondMembre);

		try {
			System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());
		} catch (IrregularSysLinException e) {
			System.out.println("Exception levée : " + e.getMessage());
		}

		System.out.println("**********************       EXEMPLE2 : test norme     ********************************\n");
		double[][] matriceDiagonale = { { 2, 0, 0 }, { 0, 3, 0 }, { 0, 0, 4 } };
		double[] secondMembre2 = { 8, 12, 10 };

		Matrice matrice = new Matrice(matriceDiagonale);
		Vecteur vecteurB = new Vecteur(secondMembre2);

		System.out.println("la matrice A :\n" + matrice.toString());
		System.out.println("le vecteur B :\n" + vecteurB.toString());

		try {
			SysDiagonal systemDiagonal = new SysDiagonal(matrice, vecteurB);
			Vecteur solution = systemDiagonal.resolution();

			System.out.println("Solution du système diagonal Ax = b :");
			System.out.println(solution);

			// Calcul de Ax
			Vecteur produitAx = matrice.produit(solution);

			// Calcul de Ax - b
			Vecteur difference = Vecteur.soustraction(produitAx, vecteurB);

			System.out.println("Vecteur Ax - b :");
			System.out.println(difference);
			double normeL1 = difference.normeL1();
			System.out.println("Norme L1 de Ax - b : " + normeL1);

			double normeL2 = difference.normeL2();
			System.out.println("Norme L2 de Ax - b : " + normeL2);

			double normeLInfini = difference.normeLInfini();
			System.out.println("Norme LInfini de Ax - b : " + normeLInfini + "\n");

			// Calcul de A(Ax)
			Vecteur produitAAx = matrice.produit(produitAx);

			// Calcul de A²x - b
			Vecteur difference2 = Vecteur.soustraction(produitAAx, vecteurB);

			System.out.println("Vecteur A²x - b :");
			System.out.println(difference2);

			double normeL12 = difference2.normeL1();
			System.out.println("Norme L1 de A²x - b : " + normeL12);

			double normeL22 = difference2.normeL2();
			System.out.println("Norme L2 de A²x - b : " + normeL22);

			double normeLInfini2 = difference2.normeLInfini();
			System.out.println("Norme LInfini de A²x - b : " + normeLInfini2);

		} catch (IrregularSysLinException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
}
