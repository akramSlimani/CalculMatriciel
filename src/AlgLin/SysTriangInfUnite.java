package AlgLin;

public class SysTriangInfUnite extends SysLin{

	public SysTriangInfUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
			
			int taille = this.getMatriceSystem().nbLigne();
			Vecteur solution = new Vecteur(taille);
			
			 for(int i = 0; i < this.getMatriceSystem().nbLigne(); i++) {
			      
			      for(int j = 0; j < this.getMatriceSystem().nbColonne(); j++) {
			        
			        if(i < j && this.getMatriceSystem().getCoef(i, j) != 0) {
			          
			          throw new IrregularSysLinException("La matrice n'est pas triangulaire inférieure !");
			          
			        }else if( this.getMatriceSystem().getCoef(i, i) != 1){
			        	
			        	 throw new IrregularSysLinException("Les éléments diagonaux ne sont pas tous égaux à 1 !");
			        }
			      }
			    }
			 
			for (int i = 0; i < solution.getTaille(); i++) {
	           double s=0;
	           
	           for(int j = 0; j < i; j++) {
	        	   s += this.getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
	           }
	           solution.remplaceCoef(i, 0, (this.getSecondMembre().getCoef(i) - s)/this.getMatriceSystem().getCoef(i, i));

	        }
			
	        return solution;
		}
	
	public static void main(String[] args) throws IrregularSysLinException {
		
		// exemple du td : (les diagonaux sont tous égaux à 1 ) :
		System.out.println("**********************       EXEMPLE1 : juste       ********************************\n");		
		double[][] tab1 = {{1.0, 0.0, 0.0},{4.0, 1.0 , 0.0},{3.0, 0.6, 1.0}};
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A1 :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = {3.0, 5.0, 8.0};
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysTriangInfUnite s = new SysTriangInfUnite(matriceSys, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());	
	
		System.out.println("**********************       EXEMPLE2 : erroné     ********************************\n");
		// exemple éronné : (les diagonaux sont pas tous égaux à 1 ) une exception doit être levée:
		double[][] tab3 = {{1.0, 0.0, 0.0},{4.0, 1.0 , 0.0},{3.0, 0.6, 4.0}};
		Matrice matriceSys2 = new Matrice(tab3);
		System.out.println("la matrice A2 :\n" + matriceSys2.toString());
		System.out.println("******************************************************\n");

		SysTriangInfUnite s2 = new SysTriangInfUnite(matriceSys2, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s2.resolution());
	
		
	}
	
	}

