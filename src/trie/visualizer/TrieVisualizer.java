package trie.visualizer;

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
	 * @param prefix le chaine de caractere à afficher
	 * @param isLast indique si le noeud correspond au dernier fils (fils droit)
	 * @param isEmpty indique si le noeud est un arbre vide
	 * @param th noeud courant
	 */
	public static void visualizeTrieHybrideAux(String prefix, boolean isLast, boolean isEmpty, ITrieHybride th){
		//si le noeud correspond a un arbre vide alors on l'affiche
		if(isEmpty){
			System.out.println(prefix + (isLast ? "'---" : "|---") + "ø");
			return;
		}
		//on affiche le noeud
		System.out.println(prefix + (isLast ? "'---" : "|---") + th.getChar() + (th.isWord()?","+th.getValue():""));
		
		//si fils gauche existe
		//si noeud courant est un fils droit (le dernier fils) alors pour la ligne suivante on ne represente pas une branche suite
		//sinon on représente en tant que suite, et on appel recursivement pour le fils gauche
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
}
