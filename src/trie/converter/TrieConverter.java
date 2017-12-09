package trie.converter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.implementation.TrieHybride;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverter {
	
	/**
	 * Conversion d'un Patricia Trie vers un Trie Hybride
	 * @param p
	 * @return
	 */
	public static ITrieHybride patToHybridTrie(PatriciaTrie p){
		
		if(p==null)
			return null;
		
		//appel à la méthode privée 
		return  patToHybridTrie( p, null);
	}

	
/**
 * Conversion d'un Patricia Trie vers un Trie Hybride
 * @param p
 * @param th
 * @return
 */
private static ITrieHybride patToHybridTrie(PatriciaTrie p, ITrieHybride th){
	
		/*
		 * Note : on ajoute toujours dans le fils droit du trie hybride,
		 * car on parcourt les mots du patricia trie par ordre alphabétique.
		 */
	
		PatriciaTrie [] patTries = p.getPatTries(); //on récupère les patricia tries fils du patricia trie courant
		boolean first = true; //booleen permettant de savoir si le prochain mot ajouté dans le trie hybride est le premier mot ajouté
		ITrieHybride nextTh = null;
		
		
		/*si on a un patricia trie fils signalant qu'on a un mot égale le prefixe
		 * Le cas échéant, on doit alors marqué la lettre courante, pointée par th, comme un mot
		 */
		if(patTries[26]!=null){
			th.turnIntoWord();
		}
		
		/*
		 * On parcourt tous les patricia tries fils (hormi celui du cas traité précédemment
		 */
		for(int i=0; i<26; i++){
			
			if(patTries[i]!=null){
				
				//si ce patricia trie est une feuille
				if(patTries[i].isFeuille()){
					
					//si c'est le premier mot à ajouter
					if(first){
						//on crée un nouveau trie hybride contenant tout le reste du mot (à partir de l'indice)
						th = new TrieHybride(patTries[i].getVal().substring(p.getInd()));
						first=false;
						//c'est à ce nouveau trie hybride qu'on fera les ajouts éventuels
						nextTh=th;
					}
					//sinon
					else{
						/*on attritue au fils droit un nouveau trie hybride	*/
						nextTh.setfd(new TrieHybride(patTries[i].getVal().substring(p.getInd())));
						nextTh=nextTh.getfd(); //c'est à ce nouveau trie hybride qu'on fera les ajouts éventuels
					}
				}
				//sinon, ce patricia trie n'est pas une feuille
				else{
					
					//on récupère la chaine de caractère qui doit être ajoutée au trie hybride
					String wordPart = patTries[i].getVal().substring(p.getInd());
					
					ITrieHybride pTh; //pointeur fin du tri hybride
					
					//si c'est le premier mot à ajouter
					if(first){
						
						/*
						 * on crée un nouveau trie hybride de taille 1, 
						 * contenant le premier caractère de la chaine de caractères à ajouter
						 */
						th = new TrieHybride(wordPart.charAt(0), -1, null, null, null);
						first=false;
						pTh = th;
						nextTh=th; //c'est à ce nouveau trie hybride qu'on fera les ajouts éventuels
					}
					else{
						/*on attritue au fils droit un nouveau trie hybride	*/
						nextTh.setfd(new TrieHybride(wordPart.charAt(0), -1, null, null,null));
						pTh=nextTh.getfd();
						nextTh = pTh;	//c'est à ce nouveau trie hybride qu'on fera les ajouts éventuels
					}
					
					
					/*
					 * On contruit le fils centrale du tri hybride, qui contiendra les lettres de la chaine de caractère
					 */
					for(int k=1; k<wordPart.length(); k++){
						pTh.setfc(new TrieHybride(wordPart.charAt(k), -1, null, null, null));
						pTh = pTh.getfc();
					}
					
					/*
					 * On ajoute en fils central un tri hybride, par appel récursif à la fonction
					 */
					pTh.setfc(patToHybridTrie(patTries[i], pTh));
					
				}
			}
		}
		
		return th;
	}

	/**
	 * Conversion d'un Trie Hybride vers un Patricia Trie
	 * @param th
	 * @return
	 */
	public static PatriciaTrie hybrideTrieToPatricia(ITrieHybride th){
		
		if(th==null)
			return null;
		
		//appel à la méthode privée
		return hybrideTrieToPatricia(th, new PatriciaTrie());
	}
	
	/**
	 * Conversion d'un Trie Hybride vers un Patricia Trie
	 * @param th
	 * @param pat
	 * @return
	 */
	private static PatriciaTrie hybrideTrieToPatricia(ITrieHybride th, PatriciaTrie pat){
		
		StringBuilder prefixe = new StringBuilder(); //prefixe courant
		PatriciaTrie [] patTries = pat.getPatTries();
		ITrieHybride pTh = th;
		ITrieHybride pThWord = th;
		PatriciaTrie patBis = null;
		ITrieHybride filsGauche, filsDroit;
		
		/*
		 * Si le noeud courant un/des fils droit ou/et gauche, on fait les appels récursifs sur ce/ces fils.
		 */
		if(th.getfd()!=null){
			pat = hybrideTrieToPatricia(pTh.getfd(), pat);
		}
		if(th.getfg()!=null){
			pat = hybrideTrieToPatricia(pTh.getfg(), pat);
		}
		
		//le prefixe courant est la valeur du patricia trie actuel
		prefixe.append(pat.getVal());
		
		/*
		 * Boucle do while : tant que le noeud n'a pas de fils gauche ni droit,
		 * c'est-à-dire tant qu'on a qu'une suite de noeuds centraux.
		 */
		do{
			prefixe.append(pTh.getChar()); //on ajoute la valeur du noeud courant au préfixe
			
			/*
			 * Si le noeud courant n'a pas de fils central,
			 * alors le prefixe est en faire le mot en entier.
			 * On ajoute alors au patricia trie courant un patricia trie fils, qui est donc une feuille,
			 * et on le renvoie.
			 */
			if(pTh.getfc()==null){
				int ind = pat.getInd()+pat.getVal().length();
				patTries[pThWord.getChar()-97] = new PatriciaTrie(ind, prefixe.toString(), true);
				return pat;
			}
			
			/*
			 * Sinon, le noeud courant possède un fils central
			 */
			
			/*
			 * Si le noeud signale la fin d'un mot, on crée un nouveau patricia trie fils du pat trie courant.
			 * Ce nouveau fils a pour valeur le prefixe et n'est pas une feuille.
			 * A ce nouveau fils, on crée un pat trie à la case de mot de pat trie formant un mot, de valeur prefixe 
			 * (et qui est une feuille).
			 * 
			 */
			if(pTh.isWord()){
				
				patTries[pThWord.getChar()-97] = new PatriciaTrie(prefixe.length(), prefixe.toString(), false);
				patBis = patTries[pThWord.getChar()-97];
				patTries = patBis.getPatTries();
				patTries[26] = new PatriciaTrie(prefixe.length(), prefixe.toString(), true);
				pThWord = pTh.getfc();
				
			}
			
			//On prend le noeud du fils central comme noeud courant
			pTh = pTh.getfc();
			
			filsDroit = pTh.getfd();
			filsGauche = pTh.getfg();
			
			/*
			 * Si on vient de créer un noeud mot, alors on regarde si le noeud courant a des fils gauche ou droit. 
			 * Le cas échéant, on doit alors faire des appels recursifs sur ces fils, avec le nouveau patricia trie fils.
			 *   
			 */
			if(pThWord==pTh){
				if(filsDroit!=null){
					patBis = hybrideTrieToPatricia(pTh.getfd(), patBis);
					filsDroit = null;
				}
				if(filsGauche!=null){
					patBis = hybrideTrieToPatricia(pTh.getfg(), patBis);
					filsGauche=null;
				}
			}
			
		}while(filsDroit==null && filsGauche==null);
		
		/*
		 * on crée un nouveau patricia trie fils au patricia trie courant de valeur prefixe
		 * comme ce noeud a au moins soit un fils droit ou un fils gauche, ce pat trie n'est pas une feuille,
		 * et on fait les appels récursifs. 
		 */
		patTries[th.getChar()-97]=new PatriciaTrie(prefixe.length(), prefixe.toString(), false);
		
		patTries[th.getChar()-97] = hybrideTrieToPatricia(pTh, patTries[th.getChar()-97]);
		
		return pat;
	}
	
	
}
