package trie.converter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.implementation.TrieHybride;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverter {

	public static ITrieHybride patToHybridTrie(PatriciaTrie p){
		if(p==null)
			return null;
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

	
	public static PatriciaTrie hybrideTrieToPatricia(ITrieHybride th){
		if(th==null)
			return null;
		return hybrideTrieToPatricia(th, new PatriciaTrie());
	}
	
	
	private static PatriciaTrie hybrideTrieToPatricia(ITrieHybride th, PatriciaTrie pat){
		
		StringBuilder prefixe = new StringBuilder();
		PatriciaTrie [] patTries = pat.getPatTries();
		ITrieHybride pTh = th;
		ITrieHybride pThWord = th;
		PatriciaTrie patBis = null;
		ITrieHybride filsGauche, filsDroit;
		
		if(th.getfd()!=null){
			pat = hybrideTrieToPatricia(pTh.getfd(), pat);
		}
		if(th.getfg()!=null){
			pat = hybrideTrieToPatricia(pTh.getfg(), pat);
		}
		
		prefixe.append(pat.getVal());
		
		do{
			prefixe.append(pTh.getChar());
			if(pTh.getfc()==null){
				int ind = pat.getInd()+pat.getVal().length();
				patTries[pThWord.getChar()-97] = new PatriciaTrie(ind, prefixe.toString(), true);
				return pat;
			}
			
			if(pTh.isWord()){
				
				patTries[pThWord.getChar()-97] = new PatriciaTrie(prefixe.length(), prefixe.toString(), false);
				patBis = patTries[pThWord.getChar()-97];
				patTries = patBis.getPatTries();
				patTries[26] = new PatriciaTrie(prefixe.length(), prefixe.toString(), true);
				pThWord = pTh.getfc();
				
			}
			
			pTh = pTh.getfc();
			
			filsDroit = pTh.getfd();
			filsGauche = pTh.getfg();
			
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

		patTries[th.getChar()-97]=new PatriciaTrie(prefixe.length(), prefixe.toString(), false);
		
		patTries[th.getChar()-97] = hybrideTrieToPatricia(pTh, patTries[th.getChar()-97]);
		
		return pat;
	}
	
	
}
