package trie.converter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverter {

	
	public static PatriciaTrie convertIntoPatriciaTrie(ITrieHybride th) {
		PatriciaTrie newPat = new PatriciaTrie();
		TrieConverter.convertIntoPatriciaTrieAux(th, "", newPat);
		return newPat;
	}
	
	
	private static void convertIntoPatriciaTrieAux(ITrieHybride th, String word, PatriciaTrie pt) {
		if(th == null) return;
		
		//appel recursif sur le sous arbre gauche, on garde le meme mot et le meme patricia
		//car le mot commence par une lettre differente de celui du th et donc appartient au meme patricia
		if(th.existfg()) {
			System.out.println(word+" "+th.getfg().getChar());
			TrieConverter.convertIntoPatriciaTrieAux(th.getfg(), word, pt);
		}
		
		
		//de meme pour le sous arbre droit
		if(th.existfd()) {
			TrieConverter.convertIntoPatriciaTrieAux(th.getfd(), word, pt);
		}
		
		//si n'a pas de fils central alors pas d'appel
		//car on construit les patricia a partir des fils centraux
		if(!th.existfc()) {
			if(th.isWord()) {
				if(pt.isFeuille()) {
					pt.getPatTries()[26] = new PatriciaTrie(pt.getInd(), pt.getVal(), true);
				}
				pt.setFeuille(false);
				pt.setInd(word.length());
				pt.setVal(word);
				pt.getPatTries()[th.getChar()-97] = new PatriciaTrie(pt.getInd()+1, word+th.getChar(), true);
				
				return;
			}
			
		}
		
		
		char letter = th.getChar();
		String current = word+letter;
		ITrieHybride pere = th;
		ITrieHybride tmp = th.getfc();
		
		//on recupere le mot le plus long qui forme un pr�fixe
		//on ajoute pas la derniere lettre
		while(!tmp.existfg() && !tmp.existfd() && tmp.existfc() && !tmp.isWord()) {
			current += tmp.getChar();
			pere = tmp;
			tmp = tmp.getfc();
		}
		
		/*System.out.println(current);
		System.out.println(pere.getChar());
		System.out.println(tmp.getChar());*/
		
		//determine si le curseur s'est deplace
		//sert a choisir quel patricia donner en recursion
		//si pas de mouvement alors on donne le patricia en argument
		//sinon on donne un nouveau patricia forme par l'accumulation des lettres precedentes
		PatriciaTrie newPat = pt;
	
		
		//si deplacement
		if(tmp != th) {
			//si un fils existe alors le patricia sera un nouveau patricia du mot accumule sans la derniere lettre
			newPat = new PatriciaTrie(current.length(), current, false);
			
			PatriciaTrie pattable[] = pt.getPatTries();
			
			if(pt.isFeuille()){
				pattable[26] = new PatriciaTrie(pt.getInd(), pt.getVal(), true);
			}
			
			pt.setFeuille(false);
			
			
			if(tmp.isWord()) {
				newPat = new PatriciaTrie(current.length()+1, current+tmp.getChar(), true);
				pt.setInd(word.length());
				pt.setVal(word);
			}
			
			pattable[letter-97] = newPat;
		}

		
		//si la derniere lettre correspond a un mot on creer dans la case 26 un
		//nouveau patricia contenant ce mot
		/*if(tmp.isWord()) {
			PatriciaTrie pattable[] = newPat.getPatTries();
			pattable[26] = new PatriciaTrie(current.length()+1, current+tmp.getChar(), true);
		}*/

		if(pere.existfc()) {
			if(tmp.isWord()) {
				TrieConverter.convertIntoPatriciaTrieAux(tmp.getfc(), current+tmp.getChar(), newPat);	
			}else {
				TrieConverter.convertIntoPatriciaTrieAux(pere.getfc(), current, newPat);
			}
		
		}
		
		
		if(tmp.existfc()) {
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfc(), current, newPat);
		}
		
		if(tmp.existfg()) {
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfg(), current, newPat);
		}
			
		if(tmp.existfd()) {
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfd(), current, newPat);
		}
		
		
		
		System.out.println(current+" "+tmp.getChar());		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//PROTOTYPE
	private static void convertIntoPatriciaTrieAux2(ITrieHybride th, String word, PatriciaTrie pt) {
		if(th == null) return;
		
		//appel recursif sur le sous arbre gauche, on garde le meme mot et le meme patricia
		//car le mot commence par une lettre differente de celui du th et donc appartient au meme patricia
		if(th.existfg()) {
			TrieConverter.convertIntoPatriciaTrieAux(th.getfg(), word, pt);
		}
		
		
		//de meme pour le sous arbre droit
		if(th.existfd()) {
			TrieConverter.convertIntoPatriciaTrieAux(th.getfd(), word, pt);
		}
		
		//si n'a pas de fils central alors pas d'appel
		//car on construit les patricia a partir des fils centraux
		if(!th.existfc()) {
			if(th.isWord()) {
				pt.getPatTries()[th.getChar()-97] = new PatriciaTrie(word.length()+1, word+th.getChar(), false);
				pt.getPatTries()[th.getChar()-97].getPatTries()[26] = new PatriciaTrie(word.length()+1, word+th.getChar(), true);
				return;
			}
			
		}
		
		
		char letter = th.getChar();
		String current = word+letter;
		ITrieHybride pere = th;
		ITrieHybride tmp = th.getfc();
		
		//on recupere le mot le plus long qui forme un pr�fixe
		//on ajoute pas la derniere lettre
		while(!tmp.existfg() && !tmp.existfd() && tmp.existfc() && !tmp.isWord()) {
			current += tmp.getChar();
			pere = tmp;
			tmp = tmp.getfc();
		}
		
		/*System.out.println(current);
		System.out.println(pere.getChar());
		System.out.println(tmp.getChar());*/
		
		//determine si le curseur s'est deplace
		//sert a choisir quel patricia donner en recursion
		//si pas de mouvement alors on donne le patricia en argument
		//sinon on donne un nouveau patricia forme par l'accumulation des lettres precedentes
		PatriciaTrie newPat = pt;
	
		
		//si deplacement
		if(tmp != th) {
			//si un fils existe alors le patricia sera un nouveau patricia du mot accumule sans la derniere lettre
			newPat = new PatriciaTrie(current.length(), current, false);
			
			if(tmp.isWord()) {
				newPat = new PatriciaTrie(current.length()+1, current+tmp.getChar(), false);
			}
			
			PatriciaTrie pattable[] = pt.getPatTries();
			pattable[letter-97] = newPat;
		}

		
		//si la derniere lettre correspond a un mot on creer dans la case 26 un
		//nouveau patricia contenant ce mot
		if(tmp.isWord()) {
			PatriciaTrie pattable[] = newPat.getPatTries();
			pattable[26] = new PatriciaTrie(current.length()+1, current+tmp.getChar(), true);
		}

		if(pere.existfc()) {
			if(tmp.isWord()) {
				TrieConverter.convertIntoPatriciaTrieAux(tmp.getfc(), current+tmp.getChar(), newPat);	
			}else {
				TrieConverter.convertIntoPatriciaTrieAux(pere.getfc(), current, newPat);
			}
		
		}
		
		/*if(tmp.existfg()) {
			TrieConverter.convertIntoPatriciaTrieAux(pere.getfg(), current, newPat);
		}
			
		if(tmp.existfd()) {
			TrieConverter.convertIntoPatriciaTrieAux(pere.getfd(), current, newPat);
		}*/
		
	}
	
	
	private static void convertIntoPatriciaTrieAux3(ITrieHybride th, String word, PatriciaTrie pt) {
		if(th == null) return;
		
		if(th.existfg()){
			TrieConverter.convertIntoPatriciaTrieAux(th.getfg(), word, pt);
		}
		
		if(th.existfd()){
			TrieConverter.convertIntoPatriciaTrieAux(th.getfd(), word, pt);
		}
		
		PatriciaTrie pattable[] = pt.getPatTries();
		
		if(th.isWord()){
			if(pt.isFeuille()){
				pattable[26] = new PatriciaTrie(pt.getInd(), pt.getVal()+th.getChar(), true);
				pt.setFeuille(false);
			}else{
				pattable[th.getChar()-97] = new PatriciaTrie(pt.getInd(), pt.getVal()+th.getChar(), true);
			}
		}
		
		if(!th.existfc()){
			return;
		}
		
		
		char letter = th.getChar();
		String current = word;
		String acc = ""+letter;
		ITrieHybride pere = th;
		ITrieHybride tmp = th.getfc();
		
		
		
		//on recupere le mot le plus long qui forme un pr�fixe
		//on ajoute pas la derniere lettre
		while(!tmp.existfg() && !tmp.existfd() && tmp.existfc() && !tmp.isWord()) {
			acc += tmp.getChar();
			pere = tmp;
			tmp = tmp.getfc();
		}
		
		PatriciaTrie newPat;
		
		if(th != pere){
			if(pt.isFeuille()){
				pattable[26] = new PatriciaTrie(pt.getInd(), pt.getVal(), true);
				pt.setFeuille(false);
			}
			
			
			if(tmp.isWord()){
				newPat = new PatriciaTrie(acc.length()+1, current+acc+tmp.getChar(), true);
			}else{
				newPat = new PatriciaTrie(acc.length()+1, current+acc, false);
			}
			
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfg(), current+acc, newPat);
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfd(), current+acc, newPat);
			TrieConverter.convertIntoPatriciaTrieAux(tmp.getfc(), current+acc+tmp.getChar(), newPat);
			pattable[th.getChar()-97] = newPat;
		}
		
		
		return;
	}
	
	

	
	public static int calculDiffLetter(String str1, String str2) {
		String s1;
		String s2;
		if(str1.length() < str2.length()) {
			s1 = str1;
			s2 = str2;
		}else {
			s1 = str2;
			s2 = str1;
		}
		
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				return i;
			}
		}
		
		return s1.length()+1;
		
	}
	
}
