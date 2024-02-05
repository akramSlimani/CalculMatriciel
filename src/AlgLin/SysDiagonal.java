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
		double[][] tab1 = { {2.0, 0.0, 0.0, 0.0}, {0.0, 3.0, 0.0, 0.0}, {0.0, 0.0, 4.0, 0.0}, {0.0, 0.0, 0.0, 2.0}};
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = {4.0, 9.0, 16.0, 4.0};
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");
		
		try {
		SysDiagonal s = new SysDiagonal(matriceSys, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());
		}catch(IrregularSysLinException e){
	        System.out.println("Exception levée : " + e.getMessage());
		}
		
		System.out.println("**********************       EXEMPLE2 : test norme     ********************************\n");
		double[][] tab3 = {{4.0, 0.0, 0.0, 0.0},{0.0, 8.0 , 0.0, 0.0},{0.0, 0.0, 6.0, 0.0}, {0.0, 0.0, 0.0, 4.0}};
		Matrice matriceSys2 = new Matrice(tab3);
		System.out.println("la matrice A2 :\n" + matriceSys2.toString());
		System.out.println("******************************************************\n");
		
		Vecteur v1 = new Vecteur(tab2.length);
		Vecteur v2 = new Vecteur(tab2.length);
		for(int i = 0; i < tab2.length; i++) {
		      
		      v2.remplaceCoef(i, 0, Matrice.produit(matriceSys, secondMembre).getCoef(i, 0));
		    }
		
		v1 = Vecteur.soustraction(v2, secondMembre);
		
		System.out.println("norme L1 = " + v1.normeL1());
	    System.out.println("norme L2 = " + v1.normeL2()); 
	    System.out.println("norme Linfini = " + v1.normeLInfini());
	    
	    if(v1.normeL2() <= Matrice.EPSILON) {
	        
	        System.out.println("La résolution est bonne");
	      } else {
	        
	        System.out.println("La résolution n'est pas bonne");
	      }
	    }
	    
	}

