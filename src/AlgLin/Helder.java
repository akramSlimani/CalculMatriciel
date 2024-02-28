package AlgLin;

public class Helder extends SysLin {

	Matrice l, d, r;

	public Helder(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
		l = new Matrice(matriceSystem.nbLigne(), matriceSystem.nbColonne());
		d = new Matrice(matriceSystem.nbLigne(), matriceSystem.nbColonne());
		r = new Matrice(matriceSystem.nbLigne(), matriceSystem.nbColonne());
	}

	public void factorLDR() {

		Matrice m = new Matrice(this.getMatriceSystem().nbLigne(), this.getMatriceSystem().nbColonne());

		/*stocker la copie de la matrice système dans m*/
		for (int i = 0; i < this.getMatriceSystem().nbLigne(); i++) {

			for (int j = 0; j < this.getMatriceSystem().nbLigne(); j++) {

				m.remplacecoef(i, j, this.getMatriceSystem().getCoef(i, j));
			}
		}

		for (int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {

			l.remplacecoef(i, i, 1);

			for (int j = i + 1; j < this.getMatriceSystem().nbLigne(); j++) {

				double facteur = m.getCoef(j, i) / m.getCoef(i, i);

				l.remplacecoef(j, i, facteur);

				for (int k = i; k < this.getMatriceSystem().nbLigne(); k++) {

					m.remplacecoef(j, k, m.getCoef(j, k) - facteur * m.getCoef(i, k));
				}
			}
		}

		for (int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {

			d.remplacecoef(i, i, m.getCoef(i, i));

			for (int j = i; j < this.getMatriceSystem().nbLigne(); ++j) {

				r.remplacecoef(i, j, m.getCoef(i, j) / d.getCoef(i, i));
			}
		}

		System.out.println("Matrice L : \n" + l);

		System.out.println("Matrice D : \n" + d);

		System.out.println("Matrice R : \n" + r);

		Matrice a = new Matrice(this.getMatriceSystem().nbLigne(), this.getMatriceSystem().nbLigne());
		a = Matrice.produit(l, Matrice.produit(d, r));

		//System.out.println("Résultat de L * D * R  : \n" + a);
	}

	public Vecteur resolutionPartielle() throws IrregularSysLinException {

		SysTriangInfUnite sysUnitinf = new SysTriangInfUnite(l, this.getSecondMembre());
		Vecteur v1 = sysUnitinf.resolution();

		SysDiagonal sysdiago = new SysDiagonal(d, v1);
		Vecteur v2 = sysdiago.resolution();

		SysTriangSupUnite sysUnitSup = new SysTriangSupUnite(r, v2);
		Vecteur resultat = sysUnitSup.resolution();

		return resultat;
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {

		factorLDR();

		SysTriangInfUnite sysUnitinf = new SysTriangInfUnite(l, this.getSecondMembre());
		Vecteur v1 = sysUnitinf.resolution();

		SysDiagonal sysdiago = new SysDiagonal(d, v1);
		Vecteur v2 = sysdiago.resolution();

		SysTriangSupUnite sysUnitSup = new SysTriangSupUnite(r, v2);
		Vecteur resultat = sysUnitSup.resolution();

		return resultat;
	}

	public void setSecondMembre(Vecteur secondMembre2) throws IrregularSysLinException {

		if (this.getSecondMembre().getTaille() != secondMembre2.getTaille()) {

			throw new IrregularSysLinException("Les deux vecteurs sont de tailles différentes");
		}

		for (int i = 0; i < this.getSecondMembre().getTaille(); i++) {

			this.getSecondMembre().remplaceCoef(i, 0, secondMembre2.getCoef(i));
		}
	}

	public static void main(String[] args) throws IrregularSysLinException {

		System.out.println("**********************       test Helder       ********************************\n");
		Matrice premierMembre = new Matrice("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\matrice1.txt");
		Vecteur secondMembre = new Vecteur("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\vecteur1.txt");

		System.out.println("la matrice A :\n" + premierMembre.toString());
		System.out.println("******************************************************\n");
		
		System.out.println("le second membre :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		
	
		Helder systeme = new Helder(premierMembre, secondMembre);
		systeme.factorLDR();
		
		Vecteur resultPartiel = systeme.resolutionPartielle();
		System.out.println("En utilisant la resolution partielle aprés factorisation :\n" + resultPartiel);
	   
		Vecteur resultResol = systeme.resolution();
		System.out.println("En utilisant la resolution normale :\n" + resultResol);
		
		double[] tab2 = { 3.0, 5.0, 8.0 };
		Vecteur secondMembre2 = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre2.toString());
		systeme.setSecondMembre(secondMembre2);
		System.out.println("En utilisant un nouveau SecondMembre :\n" + resultResol);
		
		
		System.out.println("********************** Calcul des normes pour A * x - b **********************\n");
		
		// Création du vecteur représentant Ax - b
		Matrice solution = systeme.resolution();
		double[] tabSolution = new double[solution.nbLigne()]; 
		for (int i = 0; i < solution.nbLigne(); i++) {
			tabSolution[i] = solution.getCoef(i, 0); 
		}
		Vecteur solutionVecteur = new Vecteur(tabSolution); 
		Vecteur axMoinsB = Vecteur.soustraction(solutionVecteur, secondMembre); 
		System.out.println("Vecteur Ax - b :\n" + axMoinsB);

		

		// Calcul de la norme L1
		double normeL1 = axMoinsB.normeL1();
		System.out.println("Norme L1 de Ax - b : " + normeL1);
		if (normeL1 < Matrice.EPSILON) {
		    System.out.println("L1: bonne résolution\n");
		} else {
		    System.out.println("mauvaise résolution\n");
		}

		// Calcul de la norme L2
		double normeL2 = axMoinsB.normeL2();
		System.out.println("Norme L2 de Ax - b : " + normeL2);
		if (normeL2 < Matrice.EPSILON) {
		    System.out.println("L2: bonne résolution\n");
		} else {
		    System.out.println("mauvaise résolution\n");
		}

		// Calcul de la norme Linfini
		double normeLinfini = axMoinsB.normeLInfini();
		System.out.println("Norme Linfini de Ax - b : " + normeLinfini);
		if (normeLinfini < Matrice.EPSILON) {
		    System.out.println("Linfini: bonne résolution\n");
		} else {
		    System.out.println("mauvaise résolution\n");
		}
		
		
		//System.out.println("********************** Calcul des normes pour A²*x - b **********************\n");

		
	}
}
