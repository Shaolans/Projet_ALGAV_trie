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
	
private static ITrieHybride patToHybridTrie(PatriciaTrie p, ITrieHybride th){
		
		PatriciaTrie [] patTries = p.getPatTries();
		boolean first = true;
		
		
		if(patTries[26]!=null){
			ITrieHybride pTh = th;
			while(pTh.getfc()!=null)
				pTh = pTh.getfc();
			pTh.turnIntoWord();
		}
		
		for(int i=0; i<26; i++){
			if(patTries[i]!=null){
				if(patTries[i].isFeuille()){
					if(first){
						th = new TrieHybride(patTries[i].getVal().substring(p.getInd()));
						first=false;
					}
					else{
						th.setfd(new TrieHybride(patTries[i].getVal().substring(p.getInd())));
					}
				}
				else{
					
					String wordPart = patTries[i].getVal().substring(p.getInd());
					ITrieHybride pTh, bonTh;
					
					if(first){
						
						th = new TrieHybride(wordPart.charAt(0), -1, null, null, null);
						first=false;
						pTh = th;
						bonTh = th;
					}
					else{
						
						th.setfd(new TrieHybride(wordPart.charAt(0), -1, null, null,null));
						pTh=th.getfd();
						bonTh=pTh;
					}
					
					
					for(int k=1; k<wordPart.length(); k++){
						pTh.setfc(new TrieHybride(wordPart.charAt(k), -1, null, null, null));
						pTh = pTh.getfc();
					}
					if(bonTh.getfc()==null){
						bonTh.setfc(patToHybridTrie(patTries[i], pTh));
					}
					else
						bonTh.getfc().setfc(patToHybridTrie(patTries[i], pTh));
					
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
