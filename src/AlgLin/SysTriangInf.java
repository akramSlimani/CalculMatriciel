package AlgLin;

public class SysTriangInf extends SysLin {

	public SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);

		for (int i = 0; i < this.getMatriceSystem().nbLigne(); i++) {

			for (int j = 0; j < this.getMatriceSystem().nbColonne(); j++) {

				if (i < j && this.getMatriceSystem().getCoef(i, j) != 0) {

					throw new IrregularSysLinException("La matrice n'est pas triangulaire inférieure !");
				}
			}
		}

		for (int i = 0; i < solution.getTaille(); i++) {
			double s = 0;

			for (int j = 0; j < i; j++) {
				s += this.getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
			}
			solution.remplaceCoef(i, 0,
					(this.getSecondMembre().getCoef(i) - s) / this.getMatriceSystem().getCoef(i, i));

		}

		return solution;
	}

	public static void main(String[] args) throws IrregularSysLinException {
		// exemple du td :
		System.out.println("**********************       EXEMPLE1 : juste       ********************************\n");
		double[][] tab1 = { { 1.0, 0.0, 0.0 }, { 4.0, 1.0, 0.0 }, { 3.0, 0.6, 1.0 } };
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = { 3.0, 5.0, 8.0 };
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysTriangInf s = new SysTriangInf(matriceSys, secondMembre);
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());

		System.out.println("**********************       EXEMPLE2 : test norme     ********************************\n");
		double[][] tab3 = { { 4.0, 0.0, 0.0 }, { 3.0, 1.0, 0.0 }, { 3.0, 0.6, 1.0 } };
		Matrice matriceSys2 = new Matrice(tab3);
		System.out.println("la matrice A2 :\n" + matriceSys2.toString());
		System.out.println("******************************************************\n");

		Vecteur v = s.resolution();
		Vecteur v1 = new Vecteur(tab2.length);
		Vecteur v2 = new Vecteur(tab2.length);

		for (int i = 0; i < tab2.length; i++) {

			v2.remplaceCoef(i, 0, Matrice.produit(matriceSys2, v).getCoef(i, 0));
		}

		v1 = Vecteur.soustraction(v2, secondMembre);

		System.out.println("norme L1 = " + v1.normeL1());
		if (v1.normeL1() >= Matrice.EPSILON) {

			System.out.println("L1: bonne résolution\n ");
		} else {

			System.out.println("mauvaise résolution\n ");
		}

		System.out.println("norme L2 = " + v1.normeL2());
		if (v1.normeL2() >= Matrice.EPSILON) {

			System.out.println("L1: bonne résolution\n ");
		} else {

			System.out.println("mauvaise résolution\n ");

		}

		System.out.println("norme Linfini = " + v1.normeLInfini());

		if (v1.normeLInfini() >= Matrice.EPSILON) {

			System.out.println("L1: bonne résolution\n ");
		} else {

			System.out.println("mauvaise résolution\n");

		}
	}

}
