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

		System.out.println("Résultat de L * D * R  : \n" + a);
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

		Matrice prodPremierMembre = Matrice.produit(premierMembre, premierMembre);
		System.out.println("la matrice A² :\n" + prodPremierMembre.toString());
		System.out.println("******************************************************\n");
	
		Helder systeme = new Helder(prodPremierMembre, secondMembre);
		systeme.factorLDR();
		
		Vecteur resultPartiel = systeme.resolutionPartielle();
		System.out.println("En utilisant la resolution partielle aprés factorisation :\n" + resultPartiel);
	   
		Vecteur resultResol = systeme.resolution();
		System.out.println("En utilisant la resolution normale :\n" + resultResol);
		
		Vecteur secondMembre2 = new Vecteur("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\vecteur1.txt");
		systeme.setSecondMembre(secondMembre2);
		System.out.println("En utilisant un nouveau SecondMembre :\n" + resultResol);

		
	}
}
