package trie.visualizer;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.interfaces.ITrieHybride;

public class TrieVisualizer {

	/**
	 * Affiche la representation graphique du trie hybride
	 * @param th trie hybride a afficher
	 */
	public static void visualizeTrieHybride(ITrieHybride th){
		TrieVisualizer.visualizeTrieHybrideAux("", true, false, th);
	}
	
	/**
	 * Affiche la representation graphique du trie hybride
	 * @param prefix le chaine de caractere � afficher
	 * @param isLast indique si le noeud correspond au dernier fils (fils droit)
	 * @param isEmpty indique si le noeud est un arbre vide
	 * @param th noeud courant
	 */
	public static void visualizeTrieHybrideAux(String prefix, boolean isLast, boolean isEmpty, ITrieHybride th){
		//si le noeud correspond a un arbre vide alors on l'affiche
		if(isEmpty){
			System.out.println(prefix + (isLast ? "'---" : "|---") + "�");
			return;
		}
		//on affiche le noeud
		System.out.println(prefix + (isLast ? "'---" : "|---") + th.getChar() + (th.isWord()?","+th.getValue():""));
		
		//si fils gauche existe
		//si noeud courant est un fils droit (le dernier fils) alors pour la ligne suivante on ne represente pas une branche suite
		//sinon on repr�sente en tant que suite, et on appel recursivement pour le fils gauche
		//sinon si pas de fils gauche alors isEmpty on print un arbre vide dans l'appel recursif
		if(th.existfg()){
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), false, false, th.getfg());
		}else{
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), false, true, null);
		}
		
		//analogue au fils gauche
		if(th.existfc()){
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), false, false, th.getfc());
		}else{
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), false, true, null);
		}
		
		//analogue au fils gauche
		if(th.existfd()){
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), true, false, th.getfd());
		}else{
			TrieVisualizer.visualizeTrieHybrideAux(prefix + (isLast ? "    " : "|   "), true, true, null);
		}
	}
	
	
	public static void visualizePatriciaTrie(PatriciaTrie pt){
		TrieVisualizer.visualizePatriciaTrieAux("", true, pt);
	}
	
	public static void visualizePatriciaTrieAux(String prefix, boolean isLast, PatriciaTrie pt){
		System.out.println(prefix + "    " + (isLast ? "└────" : "├────") + (!pt.isFeuille()?pt.getInd()+",":"")  + (pt.getVal()==""?"ROOT":pt.getVal()));
		String tableau = "[";
		PatriciaTrie pattable[] = pt.getPatTries();
		
		//genere la string de tableau
		for(int i = 0; i < 27; i++) {
			if(pattable[i] != null) {
				tableau += (char)(i+97)+"│";
			}
		}
		
		//retire le "|" en trop s'il y a
		if(tableau.length() > 1) {
			tableau = tableau.substring(0, tableau.length()-1);
		}
		tableau += "]";
		
		//affiche le tableau
		System.out.println(prefix + "    " + (isLast?" ":"│" ) + "   " + tableau);
		
		//recherche du dernier element pour fermer "'|---"
		int last = 0;
		for(int i = 0; i < 27; i++) {
			if(pattable[i] != null) {
				last = i;
			}
		}
		
		//appel recursif
		for(int i = 0; i < 27; i++) {
			if(pattable[i] != null && last != i) {
				TrieVisualizer.visualizePatriciaTrieAux(prefix +"    "+ (isLast ? "    " : "│   "), false, pattable[i]);
			}
			
		}
		
		//appel du dernier element
		if(pattable[last] != null) {
			TrieVisualizer.visualizePatriciaTrieAux(prefix +"    "+ (isLast ? "    " : "│   "), true, pattable[last]);
		}
		
	}
}
