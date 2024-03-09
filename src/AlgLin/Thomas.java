package AlgLin;

public class Thomas extends SysLin {

	private static Matrice l, u;

	public Thomas(Matrice matrice, Vecteur secMembre) throws IrregularSysLinException {

		super(matrice, secMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		Vecteur result = new Vecteur(this.getMatriceSystem().nbColonne());

		SysTriangInfUnite s1 = new SysTriangInfUnite(l, this.getSecondMembre());
		Vecteur inter = s1.resolution();

		SysTriangSup s2 = new SysTriangSup(u, inter);
		result = s2.resolution();

		return result;
	}

	public static void factorLU(Mat3Diag matrice) {

		/* create l & u */
		l = new Matrice(matrice.nbColonne(), matrice.nbColonne());
		u = new Matrice(matrice.nbColonne(), matrice.nbColonne());

		/* init l & u */
		u.remplacecoef(0, 0, matrice.getCoef(1, 0));
		u.remplacecoef(0, 1, matrice.getCoef(2, 0));
		l.remplacecoef(0, 0, 1);
		l.remplacecoef(1, 0, matrice.getCoef(0, 1) / u.getCoef(0, 0));

		/* boucle pour finir la factorisation */
		for (int i = 1; i < matrice.nbColonne(); ++i) {

			u.remplacecoef(i, i, matrice.getCoef(1, i) - l.getCoef(i, i - 1) * u.getCoef(i - 1, i));
			l.remplacecoef(i, i, 1);

			if (i < matrice.nbColonne() - 1) {

				u.remplacecoef(i, i + 1, matrice.getCoef(2, i));
				l.remplacecoef(i + 1, i, matrice.getCoef(0, i + 1) / u.getCoef(i, i));
			}
		}
	}

	public static void main(String[] args) throws Exception {

		double[][] tableau = { { 0, 1, 2, 1, 4 }, { 3, 2, 2, 1, 2 }, { 2, 1, 1, 2, 0 } };
		Mat3Diag matrice = new Mat3Diag(tableau);

		double[] v = { 2, 1, 1, 4, 3 };
		Vecteur vect = new Vecteur(v);

		factorLU(matrice);

		Thomas sys = new Thomas(Matrice.produit(l, u), vect);
		Vecteur result = sys.resolution();

		System.out.println("Matrice de base : ");
		System.out.println(sys.getMatriceSystem());
		System.out.println();
		System.out.println("Vecteur de base : ");
		System.out.println(vect);
		System.out.println();
		System.out.println("Résultat de Ax = b avec A la matrice de base et b le vecteur de base :");
		System.out.println(result);

		/* Vérification */

		Vecteur verif = new Vecteur(v.length);
		Vecteur inter = new Vecteur(v.length);

		for (int i = 0; i < v.length; ++i) {

			inter.remplaceCoef(i, 0, Matrice.produit(sys.getMatriceSystem(), result).getCoef(i, 0));
		}

		verif = Vecteur.soustraction(inter, vect);

		System.out.println("norme L1 = " + verif.normeL1());
		System.out.println("norme L2 = " + verif.normeL2());
		System.out.println("norme Linfini = " + verif.normeLInfini());

		if (verif.normeLInfini() <= Matrice.EPSILON) {

			System.out.println("La résolution est bonne");
		} else {

			System.out.println("La résolution n'est pas bonne");
		}
	}
}