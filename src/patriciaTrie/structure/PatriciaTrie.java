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
				
				/*
				 * Sinon, on regarde le patracia trie se trouvant à la lettre indiquée par l'indice
				 */
				char c = word.charAt(p.ind);
				
				/*
				 * Si ce pat trie n'existe pas, on crée le pat trie 
				 * qui a pour valeur le mot, et qui est une feuille
				 */
				if(p.patTries[c-97]==null){
					p.patTries[c-97] = new PatriciaTrie(p.ind+1, word, true);
				}
				else{
					/*
					 * Sinon un pat trie existe déjà, alors il faut comparer
					 * sa valeur avec le mot qu'on veut ajouter
					 */
					
					/*
					 * Si ce pat trie n'est pas une feuille
					 */
					if( ! p.patTries[c-97].feuille ){
						
						/*
						 * Si la valeur est un prefixe du mot, 
						 * alos on fait un appel récursif sur ce pat trie
						 */
						if(word.startsWith(p.patTries[c-97].val)){
							return ajouterMot(p.patTries[c-97], word);
						}
						else{
							
							/*
							 * Le mot1 est le mot le plus court entre la valeur et le mot.
							 */
							
							/*
							 * Sinon, on compare la valeur et le mot, jusqu'à
							 * trouver la première lettre où ils diffèrent.
							 * En effet, on doit créer un pat trie qui contient le prefixe commun 
							 * des mot1 et mot2, puis créer des pat tries fils à ce nouveau pat trie, 
							 * qui contiendront les mot1 et mot2.
							 */
							

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
							
							int indice;
							
							/*
							 * Dans le cas où le mot1 est prefixe du mot2
							 * (et alors le mot1 correspond au mot à ajouter et le mot2
							 * est la valeur du pat trie), on doit ajouter un pat trie à la 
							 * case du pat trie de fin de mot
							 */
							if(j==mot1.length()){
								indice = 26;
							}
							
							/*
							 * Sinon on doit ajouter un pat trie à la case de la
							 * première lettre qui diffère entre le mot1 et le mot2
							 */
							else{
								indice = word.charAt(j)-97;
							}
							
							/*
							 * Création des pat tries
							 */
							String s = p.patTries[c-97].val;
							PatriciaTrie np = new PatriciaTrie(j, s.substring(0, j), false);
							np.patTries[s.charAt(j)-97] = p.patTries[c-97];
							np.patTries[indice]=new PatriciaTrie(p.patTries[c-97].ind, word, true);
							p.patTries[c-97] = np;
														
						}
						
					}
					/*
					 * Sinon, ce pat trie est une feuille, et on compare sa valeur avec
					 * le mot à ajouter
					 */
					else{
						
						/*
						 * Si sa valeur est égale au mot à ajouter,
						 * alors il n'est pas nécessaire de l'ajouter comme il est
						 * déjà
						 */
						if(word.equals(p.patTries[c-97].val))
							return false;
						
						
						/*
						 * Le mot1 est le mot le plus court entre la valeur et le mot.
						 */
						
						/*
						 * Sinon, on compare les mot1 et mot2 pour trouver le prefixe commun.
						 */
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
						
						/*
						 * Si l'indice est égale à la longueur du mot1, on crée un 
						 * pat trie de fin de mot, et un pat trie à la case de la lettre
						 * se situant à l'incide i dans le mot2
						 */
						if(i==mot1.length()){
							p.patTries[c-97].patTries[26]=new PatriciaTrie(i, mot1, true);
							p.patTries[c-97].patTries[mot2.charAt(i)-97]=new PatriciaTrie(i+1, mot2, true);
						}
						
						/*
						 * Sinon on crée deux pat tries à la case de la lettre
						 * se situant à l'incide i dans les mot1 et mot2.
						 * On rappelle que les mot1 et mot2 diffèrent à la lettre se situant
						 * à l'indice i
						 */
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

	/**
	 * Rechercher un mot dans le patriacia trie
	 * @param p
	 * @param word
	 * @return
	 */
	public static boolean recherche(PatriciaTrie p, String word) {
		
		/*
		 * Si l'indice du pat trie est égale à la longueur du mot
		 */
		if(word.length()==p.ind){
			
			/*
			 * S'il existe un pat trie de fin de mot, alors ce mot est présent.
			 */
			if(p.patTries[26]!=null)
				return true;
			else
				return false;
		}
		
		if(word.length()<p.ind)
			return false;
		
		/*
		 * On récupère la lettre se trouvant à l'indice du pat trie
		 */
		char c = word.charAt(p.ind);
		
		/*
		 * S'il n'existe pas de pat tries fils à la case correspondant à l'indice,
		 * alors le mot n'est pas présent.
		 */
		if(p.patTries[c-97]==null)
			return false;
		/*
		 * Sinon, si ce pattrie est une feuille,
		 *  si le mot est égale à la valeur de ce pat trie, alors
		 * le mot est présent.
		 */
		if(p.patTries[c-97].feuille){
			 if(word.equals(p.patTries[c-97].val))
				 return true;
			 else
				 return false;
		}
			
		/*
		 * Sinon on continue la recherche à partir de ce pat trie
		 * (on descend en profondeur)
		 */
		return recherche(p.patTries[c-97], word);
		
	}

	/**
	 * Supprimer un mot d'un patricia trie
	 * @param p
	 * @param word
	 * @return
	 */
	public static boolean supprimerMot(PatriciaTrie p, String word) {		
		
		boolean res = true;
		char c ;
		
		/*
		 * Si l'indice est égale à la longueur du mot,
		 * on récupère le caractère de fin de mot
		 */
		if(word.length()==p.ind){
			c= '{';
		}
		else{
			/*
			 * Sinon, si la longueur du mot est inférieure à la longueur du mot,
			 * le mot ne figure pas dans le pat trie et ne peut donc pas être supprimé
			 */
			if(word.length()<p.ind)
				return false;
			/*
			 * Sinon, on récupère le caractère se trouvant à l'indice du pat trie
			 */
			c = word.charAt(p.ind);	
		}
		
		/*
		 * S'il n'existe pas de pat tries fils à la case de la lettre,
		 * c'est que ce mot n'est pas dans le pat trie
		 */
		if(p.patTries[c-97]==null)
			return false;
		
		/*
		 * Sinon, si le pat trie fils est une feuille, et que sa valeur est
		 * égale au mot à supprimer, on le supprime
		 */
		if(p.patTries[c-97].feuille && word.equals(p.patTries[c-97].val)){
			
			//suppression du pat trie contenant le mot
			p.patTries[c-97]=null;
			
			
			/*
			 * On regarde si le pat trie courant à au moins un autre fils.
			 */
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
			
			/*
			 * Si le pat trie a au moins deux fils, on renvoie true.
			 * Dans le cas où il n'en a qu'un seul, le pat trie courant devient le pat trie fils.
			 */
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
		
		/*
		 * Sinon le pat trie fils n'est pas une feuille, on fait
		 * l'appel récursif sur lui (on descend en profondeur)
		 */
		else{
			res = supprimerMot(p.patTries[c-97], word);
		}
	
		return res;
	}
	
	/**
	 * Compte combien de mots possède le pat trie
	 * @param p
	 * @return
	 */
	public static int comptageMots(PatriciaTrie p){
		
		int cpt=0;
		
		/*
		 * Si le pat trie est une feuille, on renvoie 1
		 */
		if(p.feuille)
			return 1;
		
		/*
		 * Sinon, on fait la somme de l'appel récursif de chaque pat trie fils
		 */
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				cpt += comptageMots(p.patTries[i]);
			}
		}
		
		return cpt;
	}
	
	/**
	 * Retourne la liste des mots du pat trie dans l'ordre alphabétique
	 * @param p
	 * @return
	 */
	public static List<String> listeMots(PatriciaTrie p){
		
		List<String> mots = new ArrayList<String>();
		
		/*
		 * Si le pat trie est une feuille, on ajoute le mot à la liste,
		 * et on renvoie la liste
		 */
		if(p.feuille){
			mots.add(p.val);
			return mots;
		}
		
		/*
		 * Sinon, on ajoute à la liste les mots contenus dans la liste de
		 * chaque pat trie fils (par appel récursif)
		 */
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				mots.addAll(listeMots(p.patTries[i]));
			}
		}
		
		
		return mots;
	}
	
	/**
	 * Compte combien de pointeur à nil contient le patricia trie
	 * @param p
	 * @return
	 */
	public static int comptageNil(PatriciaTrie p){
		
		int cpt=0;
		
		/*
		 * Pour chaque pat trie fils null, on incrémente le compteur de 1.
		 * Pour chaque pat trie fils non null, on ajoute au compteur le nombre 
		 * retourné par appel récursif sur ce pat trie fils
		 */
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
	
	
	/**
	 * Retourne la hauteur du patricia trie
	 * @param p
	 * @return
	 */
	public static int hauteur(PatriciaTrie p){
		
		//hauteur
		int h = 0;
		
		/*
		 * Si le pat trie est une feuille, on renvoit 0
		 */
		if(p.feuille){
			return 0;
		}
		
		/*
		 * Sinon, pour chaque pat trie fils, on fait la comparaison entre la hauteur,
		 * et x = 1 + le nombre retourné par appel récursif sur ce pat trie fils
		 * Si la hauteur est plus petite que x, alors la nouvelle hauteur est x
		 */
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
	
	
	/**
	 * Renvoie la profondeur moyenne du patricia trie
	 * @param p
	 * @return
	 */
	public static double profondeurMoyenne(PatriciaTrie p){
		/*
		 * On fait appel à la fonction profondeurs, qui remplit la liste des différentes profondeurs
		 * du patriacia trie.
		 * Ensuite, on fait la moyenne des profondeurs
		 */
		List<Integer> pfs = new ArrayList<>();
		profondeurs(p, pfs, 0);
		double somme=0;
		for(Integer i : pfs){
			somme+=i;
		}
		return somme/pfs.size();
		
	}
	/**
	 * Ajoute dans une liste les différentes profondeurs du pat trie
	 * @param p
	 * @param pfs
	 * @param x
	 */
	private static void profondeurs(PatriciaTrie p, List<Integer> pfs, int x){
		
		/*
		 * Si le pat trie est une feuille, on ajoute la profondeur x à la liste
		 */
		if(p.feuille){
			pfs.add(x);
		}
		
		/*
		 * Sinon, on fait un appel récursifs sur chaque fils du pat trie
		 */
		for(int i=0; i<27; i++){
			if(p.patTries[i]!=null){
				PatriciaTrie.profondeurs(p.patTries[i], pfs, x +1);
			}
		}

	}
	
	/**
	 * Renvoie le nombre de mots du pat trie qui contiennent ce préfixe
	 * @param p
	 * @param prefixe
	 * @return
	 */
	public static int prefixe(PatriciaTrie p, String prefixe){
		
		int cpt=0;
		
		/*
		 * Si le pat trie est une feuille et que sa valeur contient le prefixe, on renvoie 1.
		 * S'il ne contient pas le prefixe, on renvoie 0
		 */
		if(p.feuille){
			if(p.val.contains(prefixe))
				return 1;
			return 0;
		}
		
		/*
		 * Sinon, si l'indice du pat trie est supérieur à la taille du préfixe,
		 * On ajoute au compteur le nombre retourné par l'appel récursif de chaque fils
		 * puis on retourne le compteur
		 */
		if(p.ind >= prefixe.length()){
			for(int i=0; i<27; i++){
				if(p.patTries[i]!=null)
					cpt += prefixe(p.patTries[i], prefixe);
			}
			return cpt;
		}
		
		/*
		 * Sinon, on récupère la lettre dans le préfixe situé à la place de l'indice
		 */
		char c = prefixe.charAt(p.ind);
		
		/*
		 * Si aucun pat trie fils n'existe à cette case, on renvoie 0
		 */
		if(p.patTries[c-97]==null){
			return 0;
		}
		
		/*
		 * Sinon, on fait un appel récursif sur ce pat trie
		 */
		return prefixe(p.patTries[c-97], prefixe);
		
	}
	
	/**
	 * Renvoie un pat trie issu la fusion de deux pat tries
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static PatriciaTrie fusion(PatriciaTrie p1, PatriciaTrie p2){
		
		PatriciaTrie patfusion = null ;
		
		/*
		 * Si les deux pat tries sont des feuilles, alors leurs valeurs respectives 
		 * commencent par la même lettre.
		 */
		if(p1.feuille && p2.feuille){
			
			/*
			 * s'ils ont la même valeur, on renvoie un pat trie contenant le mot
			 */
			if(p1.val.equals(p2.val)){
				PatriciaTrie p = new PatriciaTrie(p1.ind, p1.val, p1.feuille);
				ajouterMot(p, p1.val);
				return p;
			}
			
			/*
			 * sinon, on crée un pat trie auquel on ajoute les deux différents mots, et on
			 * renvoie le pat trie fils du contenant les mots 1 et mot2. En effet, 
			 * les mot1 et mot2 ayant en commun au moins leur première lettre, ils ont été
			 * ajoutés dans le même pat trie
			 */
			else{
				PatriciaTrie p = new PatriciaTrie();
				ajouterMot(p, p1.val);
				ajouterMot(p, p2.val);
				return p.patTries[p1.val.charAt(0)-97];
			}
		}
		
		/*
		 * 	On compare les valeurs des deux pat tries	
		 */
			
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
		
		
		/*
		 * On trouve l'indice où les mots 1 et 2 diffèrent
		 */
		int j;
		for(j=0; j<mot1.length(); j++){
			if(mot1.charAt(j)!=mot2.charAt(j)){
				break;
			}
		}
		
		/*
		 * Si cet indice est égale à la longueur du mot1 et est différente de celle du mot2
		 * Alors on doit ajouter au pat trie 1 (copie) la fusion du pat trie fils à la case de la lettre j du
		 * mot 2, et le pat trie 2
		 */
		if(j==mot1.length() && mot2.length()!=mot1.length()){
			patfusion = copiePatTrie(pmot1);
			patfusion.patTries[mot2.charAt(j)-97]= fusion(pmot1.patTries[mot2.charAt(j)-97], pmot2);
		}
		else{
			/*
			 * Si cet indice est égale à la longueur du mot 1 et du mot2 à la fois,
			 * Alors on doit fusionner leurs fils
			 */
			if(j==mot1.length()){
				patfusion = new PatriciaTrie(pmot1.ind, pmot1.val, false);
				for(int i=0; i<27; i++){
					/*
					 * Si pat 1 et pat 2 possèdent tous les deux des fils à 
					 * cet case, on attribue au nouveau pat fils la fusion
					 * du fils de pat 1 et pat 2
					 */
					if(p1.patTries[i]!=null && p2.patTries[i]!=null){
						patfusion.patTries[i] = fusion(p1.patTries[i], p2.patTries[i]);
					}
					else{
						/*
						 * Si pat 1 n'a pas de fils et que pat 2 en a un,
						 * on attribue au nouveau pat fils la copie du fils de pat 2
						 */
						if(p1.patTries[i]==null && p2.patTries[i]!=null){
							patfusion.patTries[i] = copiePatTrie(p2.patTries[i]);
						}
						/*
						 * Sinon, si pat 2 n'a pas de fils et que pat 1 en a un,
						 * on attribue au nouveau pat fils la copie du fils de pat 1
						 */
						else{
							if(p1.patTries[i]!=null && p2.patTries[i]==null){
								patfusion.patTries[i] = copiePatTrie(p1.patTries[i]);
							}
						}
							
					}
				}
			}
			/*
			 * Si le mot 1 et le mo2 on en commun un préfixe inférieur à la
			 * longueur des mots, alors on crée un pat trie de ce préfixe,
			 * et on copie les pat 1 & 2 aux bonnes cases
			 */
			else{
				patfusion = new PatriciaTrie(j, mot1.substring(0, j), false);
				patfusion.patTries[mot1.charAt(j)-97] = copiePatTrie(pmot1);
				patfusion.patTries[mot2.charAt(j)-97] = copiePatTrie(pmot2);
			}
		}
					
		
	return patfusion;
	
	
	}
	
	
	/**
	 * Renvoie la copie d'un pat trie
	 * @param p
	 * @return
	 */
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
	
	
	/*
	 * Getters et Setters
	 */
	
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
	
	public String parsePointer() {
		String name = super.toString();
		name = name.replaceAll("[.,@]", "");
		return name;
	}
}
