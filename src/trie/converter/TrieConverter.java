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
	
	
	private static PatriciaTrie hybrideTrieToPatricia(ITrieHybride th, PatriciaTrie p){
		
		StringBuilder prefixe = new StringBuilder();
		PatriciaTrie [] patTries = p.getPatTries();
		ITrieHybride pTh = th;
		ITrieHybride pThWord = th;
		
		if(th.getfd()!=null){
			p = hybrideTrieToPatricia(pTh.getfd(), p);
		}
		if(th.getfg()!=null){
			p = hybrideTrieToPatricia(pTh.getfg(), p);
		}
		
		prefixe.append(p.getVal());
		
		do{
			prefixe.append(pTh.getChar());
			if(pTh.getfc()==null){
				int ind = p.getInd()+p.getVal().length();
				patTries[pThWord.getChar()-97] = new PatriciaTrie(ind, prefixe.toString(), true);
				return p;
			}
			
			if(pTh.isWord()){
				int ind = p.getInd()+p.getVal().length();
				patTries[th.getChar()-97] = new PatriciaTrie(ind, prefixe.toString(), false);
				patTries = patTries[th.getChar()-97].getPatTries();
				patTries[26] = new PatriciaTrie(ind, prefixe.toString(), true);
				pThWord = pTh.getfc();
				
			}
			
			pTh = pTh.getfc();
		}while(pTh.getfd()==null && pTh.getfg()==null);

		patTries[th.getChar()-97]=new PatriciaTrie(prefixe.length(), prefixe.toString(), false);
		
		patTries[th.getChar()-97] = hybrideTrieToPatricia(pTh, patTries[th.getChar()-97]);
		
		return p;
	}
	
	
}
