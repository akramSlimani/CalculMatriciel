package AlgLin;

public class SysTriangSupUnite extends SysLin {

	public SysTriangSupUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);

		for (int i = 0; i < this.getMatriceSystem().nbLigne(); i++) {

			for (int j = 0; j < this.getMatriceSystem().nbColonne(); j++) {

				if (i > j && this.getMatriceSystem().getCoef(i, j) != 0) {

					throw new IrregularSysLinException("La matrice n'est pas triangulaire supérieure !");

				} else if (this.getMatriceSystem().getCoef(i, i) != 1) {

					throw new IrregularSysLinException("Les éléments diagonaux ne sont pas tous égaux à 1 !");
				}
			}
		}

		for (int i = solution.getTaille() - 1; i >= 0; i--) {
			double s = 0;

			for (int j = i + 1; j < taille; j++) {
				s += this.getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
			}
			solution.remplaceCoef(i, 0,
					(this.getSecondMembre().getCoef(i) - s) / this.getMatriceSystem().getCoef(i, i));

		}

		return solution;
	}

	public static void main(String[] args) throws IrregularSysLinException {

		double[][] tab1 = { { 1.0, 1.0, -2.0 }, { 0.0, 1.0, -1.5 }, { 0.0, 0.0, 1.0 } };
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = { 3.0, 5.0, 8.0 };
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysTriangSupUnite s = new SysTriangSupUnite(matriceSys, secondMembre);

		Vecteur solution = null;

		try {

			solution = s.resolution();
			System.out.println("solution de A*x = b  :\n" + solution);
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
