package AlgLin;

public class Thomas extends SysLin {

	public static Matrice l, u;

	public Thomas(Mat3Diag matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		Vecteur solution = new Vecteur(this.getMatriceSystem().nbColonne());

		SysTriangInfUnite s1 = new SysTriangInfUnite(l, this.getSecondMembre());
		Vecteur v = s1.resolution();

		SysTriangSup s2 = new SysTriangSup(u, v);
		solution = s2.resolution();

		return solution;
	}

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("********************** Test de la classe Thomas **********************\n");

			double[][] matriceCoeffs = { {2.0, 1.0, 0.0}, {1.0, 2.0, 1.0}, {0.0, 1.0, 2.0} };
			Mat3Diag matriceSystem = new Mat3Diag(matriceCoeffs);

			double[] secondMembreCoeffs = {1.0, 2.0, 3.0};
			Vecteur secondMembre = new Vecteur(secondMembreCoeffs);

			Thomas thomasSystem = new Thomas(matriceSystem, secondMembre);

			Vecteur solution = thomasSystem.resolution();

			System.out.println("Solution du système :");
			System.out.println(solution);

			System.out.println("\nVérification du résultat :");

			System.out.println("Matrice d'origine :");
			System.out.println(matriceSystem);

			System.out.println("Produit de la matrice d'origine avec la solution :");
			Vecteur produit = Mat3Diag.multVect(matriceSystem, solution);
			System.out.println(produit);

			double difference = Vecteur.produit(secondMembre, produit);
			System.out.println("\nNorme de la différence entre le second membre et le produit : " + difference);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
