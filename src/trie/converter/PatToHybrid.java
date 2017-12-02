package trie.converter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.implementation.TrieHybride;
import trieHybride.interfaces.ITrieHybride;

public class PatToHybrid {
	
	public static ITrieHybride patToHybridTrie(PatriciaTrie p, ITrieHybride th){
		
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
					ITrieHybride pTh;
					
					if(first){
						
						th = new TrieHybride(wordPart.charAt(0), -1, null, null, null);
						first=false;
						pTh = th;
					}
					else{
						th.setfd(new TrieHybride(wordPart.charAt(0), -1, null, null,null));
						pTh=th;
						
					}
					
					
					for(int k=1; k<wordPart.length(); k++){
						pTh.setfc(new TrieHybride(wordPart.charAt(k), -1, null, null, null));
						pTh = pTh.getfc();
					}
					if(th.getfc()==null){
						th.setfc(patToHybridTrie(patTries[i], pTh));
					}
					else
						th.getfc().setfc(patToHybridTrie(patTries[i], pTh));
				}
			}
		}
		
		return th;
	}
	
}