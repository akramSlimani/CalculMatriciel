package AlgLin;

public class Mat3Diag extends Matrice {

	public Mat3Diag(int dim1, int dim2) throws Exception {
		super(dim1, dim2);
		if (dim1 != 3) {
			throw new Exception("La première dimension doit être égale à 3 !");
		}
	}

	public Mat3Diag(double tableau[][]) throws Exception {
		super(tableau);
		if (tableau.length != 3) {
			throw new Exception("Le tableau doit etre de 3 lignes !");
		}
	}

	public Mat3Diag(int dim) {
		super(3, dim);
	}

	public static Vecteur multVect(Mat3Diag matrice, Vecteur vecteur) throws IrregularSysLinException {

		int dim = matrice.nbLigne();
		if (dim != vecteur.getTaille()) {
			throw new IrregularSysLinException("Les dimensions de la matrice et du vecteur ne sont pas compatibles.");
		}

		Vecteur result = new Vecteur(matrice.nbColonne());
		double somme;

		for (int i = 0; i < matrice.nbColonne(); ++i) {

			somme = 0;

			if (i >= 1) {

				somme += vecteur.getCoef(i - 1) * matrice.getCoef(0, i);
			}

			if (i < matrice.nbColonne() - 1) {

				somme += vecteur.getCoef(i + 1) * matrice.getCoef(2, i);
			}

			somme += vecteur.getCoef(i) * matrice.getCoef(1, i);
			result.remplaceCoef(i, 0, somme);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		
		double[][] tab = {{1.0, 0.0},
						  {2.0, 1.0}, 
						  {0.0, 3.0}};
		
		Mat3Diag mat3D = new Mat3Diag(tab);
		double[] tab2 = {2.0, 3.0, 4.0};
		Vecteur vecteur = new Vecteur(tab2);
		
		Vecteur resultat = multVect(mat3D, vecteur);
		
		System.out.println("resulat de la multiplication: \n"+ resultat);
		
	}

}
