package patriciaTrie.structure;

import java.util.List;

import patriciaTrie.interfaces.IPatriciaTrie;;

public class PatriciaTrie implements IPatriciaTrie{
	
	private Node[] nodes = new Node[26];
	String endChar = "~";
	
	public PatriciaTrie(){	}

	@Override
	public void addWord(String word) {

		Node currentNode ;
		int idx_first = (int)word.charAt(0)-97;
		
		if(nodes[idx_first]==null){
			nodes[idx_first] = new Node(word+endChar);
			return;
		}
		
		currentNode = nodes[idx_first];
		int i=1, j=1;
		boolean findEnd = false;
		String valeurNoeud = currentNode.getValeur();
		boolean nextNodeFound =true;
		
		while(nextNodeFound){
			i=1;
			valeurNoeud = currentNode.getValeur();

			while(i<valeurNoeud.length() && j<word.length() ){
				//si on atteint un caractère final dans le noeud
				if(valeurNoeud.charAt(i)==endChar.charAt(0)){
					findEnd=true;
					break;
				}
				
				//quand on atteint le premier caractère qui diffère dans le noeud
				if(valeurNoeud.charAt(i)!=word.charAt(j)){
					break;
				}
				i++;
				j++;
			}
			
			if(i==word.length()){
				currentNode.getFils().add(new Node(valeurNoeud.substring(i)));
				currentNode.getFils().add(new Node(endChar));
				currentNode.setValeur(word);
				return;
			}
			
			if(findEnd){
				currentNode.getFils().add(new Node(word.substring(j)+endChar));
				currentNode.getFils().add(new Node(endChar));
				currentNode.setValeur(valeurNoeud.substring(0, i));
				return;
			}
			
			nextNodeFound=false;
			for(Node n : currentNode.getFils()){
				if(n.getValeur().charAt(0)==word.charAt(j)){
					currentNode = n;
					valeurNoeud = currentNode.getValeur();
					j++;
					nextNodeFound=true;
					break;
				}
			}			
			
		}
		
		currentNode.getFils().add(new Node(word.substring(j)+endChar));
		if(i<valeurNoeud.length()){
			currentNode.getFils().add(new Node(valeurNoeud.substring(i)));
			currentNode.setValeur(valeurNoeud.substring(0, i));
		}
		
		
	}
	
	

	@Override
	public boolean findWord(String word) {
		
		return true;
	}
	
	
	
	@Override
	public boolean deleteWord(String word){
		
		
		return true;
	}

	
	
	@Override
	public String toString(){
		
		StringBuilder word = new StringBuilder();
		
		for(int i=0; i<26; i++){
			if(nodes[i]!=null){
				word.append(nodes[i].toString());
				word.append('\n');
			}
		}
		
		return word.toString();
		
	}

	
}
