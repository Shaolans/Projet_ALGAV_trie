package patriciaTrie.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Un PATRICIA trie est représenté par :
 * - une valeur, qui est un préfixe 
 * - un indice qui est le numéro de la prochaine lettre à évaluer dans les mots de même préfixe.
 * 		Cet indice est aussi la taille du prefixe
 * - un booléen, indiquant si ce noeud est une feuille ou non. Dans le cas où le noeud est une feuille,
 * 		le préfixe est en fait le mot en entier.
 * - un tableau de 27 PATRICIA tries fils, un pour chaque lettre de l'alphabet (le 27ème représente le caractère de fin de mot)  
 *
 */
public class PatriciaTrie {

	private PatriciaTrie[] patTries = new PatriciaTrie[27];
	private int ind;
	private String val;
	private boolean feuille;
	
	public PatriciaTrie(){
		ind=0;
		val="";
	}
	
	public PatriciaTrie(int ind, String val, boolean feuille){
		this.val = val;
		this.ind=ind;
		this.feuille=feuille;
	}

	/**
	 * Fonction de l'ajout d'un mot
	 * Renvoie true si le mot est effectivement ajouté, false sinon.
	 * En d'autres termes, renvoie true si le mot n'est pas déjà dans le pat trie, false sinon
	 * @param p
	 * @param word
	 * @return
	 */
	public static boolean ajouterMot(PatriciaTrie p, String word) {
		
		/*
		 *si la taille du mot à ajouter équivaut à celle du préfixe
		 *et que la pat trie de fin de mot est déjà crée, on renvoie false 
		 */
		if(p.ind==word.length() && p.patTries[26]!=null)
			return false;
		
		else{
			/*
			 * Si la taille du mot est égale à celle du préfixe,
			 * on crée un nouveau pat trie de fin de mot
			 */
			if(p.ind==word.length())
				p.patTries[26] = new PatriciaTrie(p.ind, word, true);
			
			else{
				
				char c = word.charAt(p.ind);
				if(p.patTries[c-97]==null){
					p.patTries[c-97] = new PatriciaTrie(p.ind+1, word, true);
				}
				else{
					if( ! p.patTries[c-97].feuille ){
								
						if(word.startsWith(p.patTries[c-97].val)){
							return ajouterMot(p.patTries[c-97], word);
						}
						else{
							String mot1, mot2;
							if(p.patTries[c-97].val.length()<word.length()){
								mot1=p.patTries[c-97].val;
								mot2=word;
							}
							else{
								mot1=word;
								mot2=p.patTries[c-97].val;
							}
							int j;
							for(j=0; j<mot1.length(); j++){
								if(mot1.charAt(j)!=mot2.charAt(j)){
									break;
								}
							}
							
							int val;
							
							if(j==mot1.length()){
								val = 26;
							}
							else{
								val = word.charAt(j)-97;
							}
									
							String s = p.patTries[c-97].val;
							PatriciaTrie np = new PatriciaTrie(j, s.substring(0, j), false);
							np.patTries[s.charAt(j)-97] = p.patTries[c-97];
							np.patTries[val]=new PatriciaTrie(p.patTries[c-97].ind, word, true);
							p.patTries[c-97] = np;
														
						}
						
					}
					else{
		
						if(word.equals(p.patTries[c-97].val))
							return false;
		
						String mot1, mot2;
						
						
						if(p.patTries[c-97].val.length()<word.length()){
							mot1=p.patTries[c-97].val;
							mot2=word;
						}
						else{
							mot1=word;
							mot2=p.patTries[c-97].val;
						}
						int i;
						for(i=0; i<mot1.length(); i++){
							if(mot1.charAt(i)!=mot2.charAt(i)){
								break;
							}
						}
						if(i==mot1.length()){
							p.patTries[c-97].patTries[26]=new PatriciaTrie(i, mot1, true);
							p.patTries[c-97].patTries[mot2.charAt(i)-97]=new PatriciaTrie(i+1, mot2, true);
						}
						else{
							p.patTries[c-97].patTries[mot1.charAt(i)-97]=new PatriciaTrie(i+1, mot1, true);
							p.patTries[c-97].patTries[mot2.charAt(i)-97]=new PatriciaTrie(i+1, mot2, true);
						}
						p.patTries[c-97].val=mot1.substring(0, i);
						p.patTries[c-97].feuille=false;
						p.patTries[c-97].ind=i;
					}
				}
			}
		}
		return true;
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder word = new StringBuilder();
		for(int i=0; i<27; i++){
			if(patTries[i]!=null){
				word.append("niveau " +((char)(i+97))+" "+ind+"\n");
				if(patTries[i].feuille){
					word.append(patTries[i].val);
					word.append("\n");
				}
				else{
					word.append(patTries[i].toString());
				}
			}
		}
		
		return word.toString();
		
	}

	
	public static boolean recherche(PatriciaTrie p, String word) {

		if(word.length()==p.ind){

			if(p.patTries[26]!=null)
				return true;
			else
				return false;
		}
		
		char c = word.charAt(p.ind);

		if(p.patTries[c-97]==null)
			return false;
		
		if(word.equals(p.patTries[c-97].val))
			return true;
		
		return recherche(p.patTries[c-97], word);
		
	}

	
	public static boolean supprimerMot(PatriciaTrie p, String word) {		
		
		boolean res = true;
		char c ;
	
		if(word.length()==p.ind){
			c= '{';
		}
		else{
			
			if(word.length()<p.ind)
				return false;
			
			c = word.charAt(p.ind);	
		}
			
			if(p.patTries[c-97]==null)
				return false;
			if(p.patTries[c-97].feuille && word.equals(p.patTries[c-97].val)){
				p.patTries[c-97]=null;
				
					int patTriesOqp=0;
					int autrePat=0;
					for(int i=0; i<27; i++){
						if(p.patTries[i]!=null){
							if(i!=c-97)
								autrePat = i;
							patTriesOqp++;
						}
						if(patTriesOqp==2)
							break;
					}
					if(patTriesOqp==1){
						if(!p.patTries[autrePat].feuille){
							p.val = p.patTries[autrePat].val;
							p.ind = p.patTries[autrePat].ind;
							p.patTries = p.patTries[autrePat].patTries;
						}
						else{
							p.val = p.patTries[autrePat].val;
							p.patTries[autrePat]=null;
							p.feuille=true;
						}
					}
				
			}
			else{
				res = supprimerMot(p.patTries[c-97], word);
			}
		
		return res;
	}
	
	
	public static int comptageMots(PatriciaTrie p){
		
		int cpt=0;
		
		if(p.feuille)
			return 1;
		
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				cpt += comptageMots(p.patTries[i]);
			}
		}
		
		return cpt;
	}
	
	public static List<String> listeMots(PatriciaTrie p){
		
		List<String> mots = new ArrayList<String>();
		
		if(p.feuille){
			mots.add(p.val);
			return mots;
		}
		
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				mots.addAll(listeMots(p.patTries[i]));
			}
		}
		
		
		return mots;
	}
	
	
	public static int comptageNil(PatriciaTrie p){
		
		int cpt=0;
		
		for(int i=0; i<27; i++){
			if(p.patTries[i]==null){
				cpt++;
			}
			else{
				cpt += comptageNil(p.patTries[i]);
			}
		}
		
		return cpt;
	}
	
	
	public static int hauteur(PatriciaTrie p){
		
		int h = 0;
		
		if(p.feuille){
			return 0;
		}
		
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				int x = 1 + PatriciaTrie.hauteur(p.patTries[i]);
				if( h < x ){
					h=x;
				}
			}
		}
		
		return h;
	}
	
	
	public static double profondeurMoyenne(PatriciaTrie p){
		
		List<Integer> pfs = new ArrayList<>();
		profondeurs(p, pfs, 0);
		double somme=0;
		for(Integer i : pfs){
			somme+=i;
		}
		return somme/pfs.size();
		
	}
	
	private static void profondeurs(PatriciaTrie p, List<Integer> pfs, int x){

		if(p.feuille){
			pfs.add(x);
		}
		
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				PatriciaTrie.profondeurs(p.patTries[i], pfs, x +1);
			}
		}

	}
	
	public static int prefixe(PatriciaTrie p, String prefixe){
		
		int cpt=0;
		
		if(p.feuille){
			return 1;
		}
		
		if(p.ind >= prefixe.length()){
			for(int i=0; i<27; i++){
				if(p.patTries[i]!=null)
					cpt += prefixe(p.patTries[i], prefixe);
			}
			return cpt;
		}
		
		char c = prefixe.charAt(p.ind);
		
		if(p.patTries[c-97]==null){
			return 0;
		}
		
		return prefixe(p.patTries[c-97], prefixe);
		
	}
	
	
	public static PatriciaTrie fusion(PatriciaTrie p1, PatriciaTrie p2){
		
		PatriciaTrie patfusion = null ;
		
		if(p1.feuille && p2.feuille){
			if(p1.val.equals(p2.val)){
				PatriciaTrie p = new PatriciaTrie(p1.ind, p1.val, p1.feuille);
				ajouterMot(p, p1.val);
				return p;
			}
			else{
				PatriciaTrie p = new PatriciaTrie();
				ajouterMot(p, p1.val);
				ajouterMot(p, p2.val);
				return p.patTries[p1.val.charAt(0)-97];
			}
		}
		
				
		if(p1.val != null && p2.val!=null){
			
			String mot1, mot2;
			PatriciaTrie pmot1, pmot2;
			if(p1.val.length()<p2.val.length()){
				mot1=p1.val;
				mot2=p2.val;
				pmot1 = p1;
				pmot2 = p2;
			}
			else{
				mot1=p2.val;
				mot2=p1.val;
				pmot1 = p2;
				pmot2 = p1;
			}
			
			int j;
			for(j=0; j<mot1.length(); j++){
				if(mot1.charAt(j)!=mot2.charAt(j)){
					break;
				}
			}
			
			
			
			if(j==mot1.length() && mot2.length()!=mot1.length()){
				patfusion = copiePatTrie(pmot1);
				patfusion.patTries[mot2.charAt(j)-97]= fusion(pmot1.patTries[mot2.charAt(j)-97], pmot2);
			}
			else{
				if(j==mot1.length()){
					patfusion = new PatriciaTrie(pmot1.ind, pmot1.val, pmot1.feuille);
					for(int i=0; i<27; i++){
						if(p1.patTries[i]!=null && p2.patTries[i]!=null){
							patfusion.patTries[i] = fusion(p1.patTries[i], p2.patTries[i]);
						}
						else{
							if(p1.patTries[i]==null && p2.patTries[i]!=null)
								patfusion.patTries[i] = copiePatTrie(p2.patTries[i]);
							else{
								if(p1.patTries[i]!=null && p2.patTries[i]==null)
									patfusion.patTries[i] = copiePatTrie(p1.patTries[i]);
							}
								
						}
					}
				}
				else{
					patfusion = new PatriciaTrie(j, mot1.substring(0, j), false);
					patfusion.patTries[mot1.charAt(j)-97] = copiePatTrie(pmot1);
					patfusion.patTries[mot2.charAt(j)-97] = copiePatTrie(pmot2);
				}
			}
			
		}	
			
			
		return patfusion;
		
		
	}
	
	
	
	private static PatriciaTrie copiePatTrie(PatriciaTrie p){
		
		PatriciaTrie copy = new PatriciaTrie(p.ind,p.val,p.feuille);
		
		if(!p.feuille){
			for(int i = 0; i<27; i++){
				if(p.patTries[i]!=null)
					copy.patTries[i] = copiePatTrie(p.patTries[i]);
			}
		}

		return copy;
		
	}
	
	
	public PatriciaTrie[] getPatTries() {
		return patTries;
	}

	public void setPatTries(PatriciaTrie[] patTries) {
		this.patTries = patTries;
	}

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public boolean isFeuille() {
		return feuille;
	}

	public void setFeuille(boolean feuille) {
		this.feuille = feuille;
	}
}
