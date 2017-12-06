package trieHybride.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import trieHybride.interfaces.ITrieHybride;

public class TrieHybridePrimitive {
	
	public static ITrieHybride initTrieHybride(){
		return new TrieHybride();
	}
	
	public static void ajoutMot(String word, ITrieHybride th){
		//cas d'arret lorsque le mot a ete ajoute
		if(word.length() == 0) return;
		
		char c = word.charAt(0);

		//si la racine est vide on creer un TH contenant le mot
		if(th.isEmpty()){
			th.setChar(c);
			//si le mot n'est pas une lettre mais un mot
			if(word.length() > 1){
				th.setfc(new TrieHybride(word.substring(1, word.length())));
			}else{
				//si lettre alors on set la value et on sort
				th.turnIntoWord();
			}
			return;
		}
		
		
		//si le mot est une lettre
		if(word.length() == 1){
			//si la lettre == lettre du TH alors on setValue si c'est deja un mot on ne fait rien
			if(c == th.getChar()){
				if(!th.isWord()){
					th.turnIntoWord();
					return;
				}
				return;
			}
			
			//si la lettre ne correspond pas a la lettre du TH et que lettre c > lettre TH
			//alors on va chercher dans le fils droit s'il existe un fils droit
			//sinon on creer un nouveau TH dans le fils droit qui contiendra la lettre
			if(c > th.getChar()){
				if(th.existfd()){
					TrieHybridePrimitive.ajoutMot(word, th.getfd());
				}else{
					th.setfd(new TrieHybride(word));
				}
				
				return;
			}
			
			//de maniere analogue pour le fils gauche
			if(c < th.getChar()){
				if(th.existfg()){
					TrieHybridePrimitive.ajoutMot(word, th.getfg());
				}else{
					th.setfg(new TrieHybride(word));
				}
				return;
			}
			return;
		}
		

		
		//cas general, parcours du TH pour trouver le TH o� inserer le mot
		
		//si le premier caractere du mot a inserer est egal a la lettre du TH
		//alors on descend dans le fils central en retirant la lettre du mot
		//si non existant alors on creer le mot
		if(c == th.getChar()){
			if(th.existfc()){
				//System.out.println(word+" C EX");
				TrieHybridePrimitive.ajoutMot(word.substring(1, word.length()), th.getfc());
				return;
			}
			//System.out.println(word+" C NEX");
			th.setfc(new TrieHybride(word.substring(1, word.length())));
			return;
		}
		
		//de maniere analogue pour le fils gauche
		if(c < th.getChar()){
			if(th.existfg()){
				//System.out.println(word+" G EX");
				TrieHybridePrimitive.ajoutMot(word, th.getfg());
				return;
			}
			//System.out.println(word+" G NEX");
			th.setfg(new TrieHybride(word));
			//System.out.println("YOLO " + th.getfg().getChar()+" "+word+" "+th.getChar());
			return;
		}
		
		//de maniere analogue pour le fils droit
		if(c > th.getChar()){
			if(th.existfd()){
				//System.out.println(word+" D EX");
				TrieHybridePrimitive.ajoutMot(word, th.getfd());
				return;
			}
			//System.out.println(word+" D NEX");
			th.setfd(new TrieHybride(word));
			return;
		}
		return;
		
	}
	
	//methode test
	public static void parcours(ITrieHybride th){
		ITrieHybride tmp = th;
		while(tmp.existfc()){
			tmp = tmp.getfc();
		}
	}
	
	//methode test
	public static void parcours2(ITrieHybride th){
		ITrieHybride tmp = th;
		for(int i = 0; i < 3; i++){
			tmp = tmp.getfc();
		}
		tmp = tmp.getfg();
		tmp = tmp.getfc();
		
	}
	
	/**
	 * Recherche si un mot est contenu dans le TH
	 * @param th TH dans lequel on cherche
	 * @param word le mot a chercher
	 * @return true s'il existe, false sinon
	 */
	public static boolean recherche(ITrieHybride th, String word){
		if(th == null) return false;
		//si on a parcourut et qu'on n'a pas trouv� le mot alors false
		if(word.length() == 0) return false;
		
		char c = word.charAt(0);
		
		//si la derniere lettre est concordante avec le TH et que cela correspond a un mot alors true
		if(word.length() == 1 && c == th.getChar() && th.isWord()) return true;
		
		//si le premier caractere du mot est concordant avec le caractere du TH
		//si le fils central est vide alors false
		//sinon on continue la recherche dans le fils central avec le mot prive de son premier caractere
		if(c == th.getChar()){
			if(th.getfc() == null) return false;
			return TrieHybridePrimitive.recherche(th.getfc(), word.substring(1, word.length()));
		}
		
		//de maniere analogue pour le fils gauche
		if(c < th.getChar()){
			if(th.getfg() == null) return false;
			return TrieHybridePrimitive.recherche(th.getfg(), word);
		}
		
		//de maniere analogue pour le fils droit
		if(c > th.getChar()){
			if(th.getfd() == null) return false;
			return TrieHybridePrimitive.recherche(th.getfd(), word);
		}
		return false;
		
	}
	
	/**
	 * Compte le nombre de mot dans le TH
	 * @param th TH dans lequel on va compter
	 * @return le nombre de mot dans le TH
	 */
	public static int comptageMots(ITrieHybride th){
		if(th == null) return 0;
		int cpt = 0;
		//System.out.println(th.getChar()+" "+th.getValue());
		if(th.isWord()){
			cpt++;
		}
		if(th.existfc()){
			cpt += TrieHybridePrimitive.comptageMots(th.getfc());
		}
		if(th.existfg()){
			cpt += TrieHybridePrimitive.comptageMots(th.getfg());
		}
		if(th.existfd()){
			cpt += TrieHybridePrimitive.comptageMots(th.getfd());
		}
		return cpt;
	}
	
	/**
	 * Compte le nombre de pointeur null dans le TH
	 * @param th
	 * @return nombre de pointeur null
	 */
	public static int comptageNil(ITrieHybride th){
		int cpt = 0;
		if(!th.existfc()){
			cpt++;
		}else{
			cpt += TrieHybridePrimitive.comptageNil(th.getfc());
		}
		
		if(!th.existfg()){
			cpt++;
		}else{
			cpt += TrieHybridePrimitive.comptageNil(th.getfg());
		}
		
		if(!th.existfd()){
			cpt++;
		}else{
			cpt += TrieHybridePrimitive.comptageNil(th.getfd());
		}
		
		return cpt;
	}
	
	/**
	 * Calcul la hauteur du TH
	 * @param th
	 * @return hauteur du TH
	 */
	public static int hauteur(ITrieHybride th){
		//si feuille alors on retourne 0
		//cas d'arret
		if(th.isLeaf()) return 0;
		
		int h = 0;
		int hfc = 0;
		int hfd = 0;
		int hfg = 0;
		
		//on calcul recursivement la hauteur du fils central
		if(th.existfc()){
			hfc = TrieHybridePrimitive.hauteur(th.getfc());
		}
		
		//on calcul recursivement la hauteur du fils gauche
		if(th.existfg()){
			hfg = TrieHybridePrimitive.hauteur(th.getfg());
		}
		
		//on calcul recursivement la hauteur du fils droit
		if(th.existfd()){
			hfd = TrieHybridePrimitive.hauteur(th.getfd());
		}
		
		//on prend le max des trois TH fils + 1
		h = Math.max(hfc, hfd);
		h = Math.max(h, hfg) + 1;
		return h;
	}
	
	/**
	 * Calcul la liste des mots du TH
	 * @param th
	 * @return liste des mots du TH
	 */
	public static List<String> listeMots(ITrieHybride th){
		List<String> listword = new ArrayList<String>();
		TrieHybridePrimitive.listeMotsAux(th, "", listword);
		return listword;
	}
	
	/**
	 * Methode auxiliaire pour parcourir le TH et recuperer les mots
	 * @param th TH courant
	 * @param word accumulateur de mot
	 * @param l liste de mot
	 */
	private static void listeMotsAux(ITrieHybride th, String word, List<String> l){
		//cas d'arret
		if(th == null) return;
		
		//on ajoute la lettre dans l'accumulateur de mot
		StringBuilder sb = new StringBuilder(word);
		sb.append(th.getChar());
		
		//si la derniere lettre est un mot alors on l'ajoute dans la liste
		if(th.isWord()){
			l.add(sb.toString());
		}
		
		//on parcours le fils central avec la lettre ajoute au mot
		if(th.existfc()){
			TrieHybridePrimitive.listeMotsAux(th.getfc(), sb.toString(), l);
		}
		
		//on parcours le fils gauche et droite avec le mot sans avoir ajoute
		//la lettre du TH courant car il n'appartient pas aux mots de ces fils
		if(th.existfg()){
			TrieHybridePrimitive.listeMotsAux(th.getfg(), word, l);
		}
		if(th.existfd()){
			TrieHybridePrimitive.listeMotsAux(th.getfd(), word, l);
		}
		return;
	}
	
	/**
	 * Calcul le nombre de mot contenant ce prefixe
	 * @param th le TH
	 * @param word le prefixe
	 * @return nombre de prefixe
	 */
	public static int prefixe(ITrieHybride th, String word){
		//cas d'arret
		if(th == null) return 0;
		
		//lorsqu'on atteint la derniere lettre du prefixe on compte le nombre de mot dans le fils central
		//fils central car tout les mots de ce fils ont ce prefixe
		if(word.charAt(0) == th.getChar() && word.length() == 1){
			if(th.isWord()){
				return 1 + TrieHybridePrimitive.comptageMots(th.getfc());
			}
			return TrieHybridePrimitive.comptageMots(th.getfc());
		}
		
		char c = word.charAt(0);
		
		//si la premiere lettre du mot concorde avec la lettre du TH
		//on descend dans le fils central et on retire la premiere lettre du mot
		if(c == th.getChar()){
			return TrieHybridePrimitive.prefixe(th.getfc(), word.substring(1, word.length()));
		}
		
		//sinon on parcours les fils gauche et droite avec le mot sans retrait
		if(c < th.getChar()){
			return TrieHybridePrimitive.prefixe(th.getfg(), word);
		}
		if(c > th.getChar()){
			return TrieHybridePrimitive.prefixe(th.getfd(), word);
		}
		
		return 0;
	}
	
	
	/**
	 * Calcul la profondeur moyenne du TH
	 * @param th le TH
	 * @return profondeur moyenne
	 */
	public static int profondeurMoyenne(ITrieHybride th){
		List<Integer> l = new ArrayList<Integer>();
		TrieHybridePrimitive.profondeurMoyenneAux(th, 0, l);
		int prof = 0;
		for(Integer i: l){
			prof += i.intValue();
		}
		prof = prof/l.size();
		return prof;
	}
	
	/**
	 * Methode auxiliaire pour parcourir le TH et calculer la profondeur
	 * @param th le TH
	 * @param acc accumulateur de profondeur
	 * @param l la liste des profondeurs des feuilles
	 */
	private static void profondeurMoyenneAux(ITrieHybride th, int acc, List<Integer> l){
		//quand on atteint une feuille on ajoute la profondeur dans la liste
		if(th.isLeaf()){
			l.add(new Integer(acc));
		}
		
		
		//on parcours tout le TH
		if(th.existfc()){
			TrieHybridePrimitive.profondeurMoyenneAux(th.getfc(), acc+1, l);
		}
		if(th.existfg()){
			TrieHybridePrimitive.profondeurMoyenneAux(th.getfg(), acc+1, l);
		}
		if(th.existfd()){
			TrieHybridePrimitive.profondeurMoyenneAux(th.getfd(), acc+1, l);
		}
		
	}
	
	
	/**
	 * Supprime un mot dans le TH
	 * @param th le TH
	 * @param word le mot a supprimer
	 * @return TH sans le mot
	 */
	public static ITrieHybride supression(ITrieHybride th, String word){
		TrieHybridePrimitive.suppressionAux(th, th, word);
		//TrieHybridePrimitive.purgeNoWordsBranch(newTrie);
		return th;
	}
	

	/**
	 * Methode auxiliaire permettant de parcourir l'arbre et de supprimer un mot
	 * @param root la racine du TH
	 * @param th le TH courant
	 * @param thfather le pere du th
	 * @param word le mot a supprimer
	 * @return
	 */
	private static void suppressionAux(ITrieHybride th, ITrieHybride thfather, String word){
		//si TH null alors arret
		if(th == null) return;
		int cptfc = 0;
		int cptfg = 0;
		int cptfd = 0;
		char c = word.charAt(0);
		
		//lorsque l'on atteint la derniere lettre du mot a supprimer
		//le TH contient la value qui definit un mot
		if(word.length() == 1){
			//si c'est un mot et que la lettre concorde avec la lettre du TH
			if(th.isWord() && c == th.getChar()){
				//on ne le considere plus comme un mot
				th.setValue(-1);
				
				//on cherche s'il y a un mot dependant de ce TH
				//cptfc = TrieHybridePrimitive.comptageMots(th.getfc());
				//cptfg = TrieHybridePrimitive.comptageMots(th.getfg());
				//cptfd = TrieHybridePrimitive.comptageMots(th.getfd());
				
				//cas ou il n'y a pas de mot, on detruit le TH
				/*if(cptfc == 0 && cptfg == 0 && cptfd == 0){
					if(thfather.getfc() == th){
						thfather.setfc(null);
					}
					if(thfather.getfg() == th){
						thfather.setfg(null);
					}
					if(thfather.getfd() == th){
						thfather.setfd(null);
					}
					
				}*/
				return;
			}
			
			//si la lettre ne concorde pas on continue la recherche dans le fils gauche ou droit
			if(c > th.getChar()){
				suppressionAux(th.getfd(), th, word);
			}
			
			if(c < th.getChar()){
				suppressionAux(th.getfg(), th, word);
			}
		}
		
		
		//parcours du TH
		//si la premiere lettre du mot concorde avec la lettre du TH alors on descend dans le fils central
		//et on retire la premiere lettre du mot
		if(c == th.getChar()){
			TrieHybridePrimitive.suppressionAux(th.getfc(), th, word.substring(1, word.length()));
		}
		
		//sinon dans les fils gauche ou droite on cherche un TH dont la lettre concorde
		if(c < th.getChar()){
			TrieHybridePrimitive.suppressionAux(th.getfg(), th, word);
			
		}
		
		if(c > th.getChar()){
			TrieHybridePrimitive.suppressionAux(th.getfd(), th, word);
		}
		
		cptfc = TrieHybridePrimitive.comptageMots(th.getfc());
		cptfg = TrieHybridePrimitive.comptageMots(th.getfg());
		cptfd = TrieHybridePrimitive.comptageMots(th.getfd());
		
		if(cptfc == 0) th.setfc(null);
		if(cptfd == 0) th.setfd(null);
		if(cptfg == 0) th.setfg(null);
		
	}
	
	
	//detruit les sous arbres ne contenant pas de mot
	public static void purgeNoWordsBranch(ITrieHybride th) {
		if(th == null) return;
		int cptwords;
		
		if(th.existfg()) {
			cptwords = TrieHybridePrimitive.comptageMots(th.getfg());
			if(cptwords == 0) {
				th.setfg(null);
			}else {
				TrieHybridePrimitive.purgeNoWordsBranch(th.getfg());
			}
		}
		
		if(th.existfc()) {
			cptwords = TrieHybridePrimitive.comptageMots(th.getfc());
			if(cptwords == 0) {
				th.setfc(null);
			}else {
				TrieHybridePrimitive.purgeNoWordsBranch(th.getfc());
			}
		}
		
		if(th.existfg()) {
			cptwords = TrieHybridePrimitive.comptageMots(th.getfd());
			if(cptwords == 0) {
				th.setfd(null);
			}else {
				TrieHybridePrimitive.purgeNoWordsBranch(th.getfd());
			}
		}
		
	}
	
	
	/**
	 * Extrait tout les noeuds qui sont accessibles uniquement par les fils gauches et droits
	 * @param th TrieHybride dont lequel on parcourt
	 * @param l la liste des noeuds
	 */
	public static void extractLRNode(ITrieHybride th, List<ITrieHybride> l){
		if(th == null) return;
		l.add(th);
		TrieHybridePrimitive.extractLRNode(th.getfg(), l);
		TrieHybridePrimitive.extractLRNode(th.getfd(), l);
	}
	
	/**
	 * Compte le nombre de noeuds qui sont accessibles uniquement par les fils gauches et droits
	 * @param th TrieHybride a compter
	 * @return le nombre de noeuds
	 */
	public static int countLRNode(ITrieHybride th) {
		if(th == null) return 0;
		return 1 + TrieHybridePrimitive.countLRNode(th.getfg()) + TrieHybridePrimitive.countLRNode(th.getfd());
	}
	
	/**
	 * Calcul si le noeud courant de ce TrieHybride est equilibre
	 * @param th Le TrieHybride a considerer
	 * @return true si equilibre, false sinon
	 */
	public static boolean checkBalanceAux(ITrieHybride th) {
		int left = TrieHybridePrimitive.countLRNode(th.getfg());
		int right = TrieHybridePrimitive.countLRNode(th.getfd());
		if(Math.abs(left-right) > 1) return false;
		return true;
	}
	
	/**
	 * Calcul si un TrieHybride est entierement equilibre
	 * @param th Le TrieHybride a considerer
	 * @return true si equilibre, false sinon
	 */
	public static boolean checkBalance(ITrieHybride th) {
		if(th == null) return true;
		if(!TrieHybridePrimitive.checkBalanceAux(th)) return false;
		List<ITrieHybride> l = new ArrayList<>();
		TrieHybridePrimitive.extractLRNode(th, l);
		for(ITrieHybride tmp: l) {
			if(!TrieHybridePrimitive.checkBalance(tmp.getfc())) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Equilibre un TrieHybride
	 * @param th Le TrieHybride a considerer
	 */
	public static void balanceTrieHybride(ITrieHybride th) {
		//si le TrieHybride est null alors il est equilibre
		if(th == null) return;
		
		/**
		 * Si le TrieHybride est deja equilibre alors on recupere les fils centraux
		 * des noeuds gauches et droits et on rappel le reequilibrage sur ces fils centraux
		 */
		if(checkBalanceAux(th)) {
			List<ITrieHybride> l = new ArrayList<>();
			TrieHybridePrimitive.extractLRNode(th, l);
			for(ITrieHybride tmp: l) {
				if(tmp.existfc()) {
					TrieHybridePrimitive.balanceTrieHybride(tmp.getfc());
				}
			}
			return;
		}
		
		/**
		 * On recupere les noeuds gauches et droits on les tri dans l'ordre alphabetique
		 */
		List<ITrieHybride> l = new ArrayList<>();
		TrieHybridePrimitive.extractLRNode(th, l);
		Collections.sort(l,(th1,th2)->(th1.getChar()<th2.getChar())?-1:1);

		
		/**
		 * On detruit les liens des fils gauches et droits de tout ces noeuds
		 */
		for(ITrieHybride tmp: l) {
			tmp.setfd(null);
			tmp.setfg(null);
		}
		
		/**
		 * On calcul le nouveau fils central (point d'entree de recherche) qui est donc le trieHybride qui
		 * decoupe en 2 ensembles plus ou moins egaux
		 * On echange l'ancien noeud par le nouveau (impossible de faire autrement avec les pointeurs sauf si on a un pere)
		 */
		int indicemilieu = (int)Math.floor(l.size()/2.0);
		ITrieHybride tmp = new TrieHybride(th.getChar(), th.getValue(), null, th.getfc(), null);
		ITrieHybride tmp2 = l.get(indicemilieu);
		
		th.setChar(tmp2.getChar());
		th.setValue(tmp2.getValue());
		th.setfc(tmp2.getfc());
		
		tmp2.setChar(tmp.getChar());
		tmp2.setValue(tmp.getValue());
		tmp2.setfc(tmp.getfc());
		
		Collections.sort(l,(th1,th2)->(th1.getChar()<th2.getChar())?-1:1);

		
		/**
		 * On transforme la liste en tableau
		 */
		ITrieHybride[] tab = new ITrieHybride[l.size()];
		for(int i = 0; i < l.size(); i++) {
			tab[i] = l.get(i);
		}
		
		/**
		 * On coupe le tableau en deux sous-ensembles
		 * subtabl correpond aux TrieHybride qui peuvent se trouver au fils gauche
		 * du TrieHybride de milieu calcule
		 * subtabr correspond aux TrieHybride qui peuvent se trouver au fils droit
		 * du TrieHybride de milieu calcule
		 */
		int split = (int)Math.floor(tab.length/2.0);
		ITrieHybride subtabl[] = Arrays.copyOfRange(tab, 0, split);
		ITrieHybride subtabr[] = Arrays.copyOfRange(tab, split+1, tab.length);
		/** On effectue spliting qui va generer en quelque sorte
		 * un arbre binaire equilibre
		 */
		
		TrieHybridePrimitive.spliting(tab[split], subtabl, subtabr);
		
		/**
		 * On recupere les fils centraux des noeuds gauches et droits
		 * On fait un appel recursif au reequilibrage
		 */
		for(ITrieHybride tmp3: l) {
			if(tmp3.existfc()) {
				TrieHybridePrimitive.balanceTrieHybride(tmp3.getfc());
			}
		}

		return;
		

	}
	
	
	/**
	 * Genere un TrieHybride equilibre
	 * @param th le TrieHybride dont lequel on va donner le fils gauche et droit
	 * @param tabl les TrieHybride possibles pour le fils gauche
	 * @param tabr les TrieHybride possibles pour le fils droit
	 */
	public static void spliting(ITrieHybride th, ITrieHybride tabl[], ITrieHybride tabr[]) {
		boolean recl = true;
		boolean recr = true;
		
		//Si le tableau vide alors on n'effectue pas d'attribution
		if(tabl.length == 0) {
			recl = false;
		}
		
		if(tabr.length == 0) {
			recr = false;
		}
		
		
		/**
		 * On calcul l'indice du fils gauche et droit qui doit
		 * etre le milieu des sous tableaux
		 */
		int splitl = (int)Math.floor(tabl.length/2.0);
		int splitr = (int)Math.floor(tabr.length/2.0);
		ITrieHybride subtabl[];
		ITrieHybride subtabr[];
		
		/*System.out.println(th.getChar());
		System.out.println("mid l = "+splitl);
		System.out.println("mid r = "+splitr);*/
		
		
		/**
		 * Si le sous tableau n'est pas vide alors on donne en tant que fils gauche
		 * le fils gauche calcule (l'element qui se trouve au milieu du sous tableau gauche)
		 * puis on redecoupe le sous tableau gauche en 2 sous tableaux et on rappel recursivement
		 * sur le fils gauche calcule
		 */
		if(recl) {
			th.setfg(tabl[splitl]);
			subtabl = Arrays.copyOfRange(tabl, 0, splitl);
			subtabr = Arrays.copyOfRange(tabl, splitl+1, tabl.length);
			/*System.out.println("LEFT "+tabl[splitl]);
			for(ITrieHybride t: subtabl) System.out.print(t.getChar()+" ");
			System.out.println();
			for(ITrieHybride t: subtabr) System.out.print(t.getChar()+" ");
			System.out.println();
			System.out.println();*/
			TrieHybridePrimitive.spliting(th.getfg(), subtabl, subtabr);
		}
		
		
		if(recr) {
			th.setfd(tabr[splitr]);
			subtabl = Arrays.copyOfRange(tabr, 0, splitr);
			subtabr = Arrays.copyOfRange(tabr, splitr+1, tabr.length);
			/*System.out.println("RIGHT "+tabl[splitr]);
			for(ITrieHybride t: subtabl) System.out.print(t.getChar()+" ");
			for(ITrieHybride t: subtabr) System.out.print(t.getChar()+" ");
			System.out.println();
			System.out.println();*/
			TrieHybridePrimitive.spliting(th.getfd(), subtabl, subtabr);
		}
	}
	
	
	
}
