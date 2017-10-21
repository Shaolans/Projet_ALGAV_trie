package patriciaTrie.structure;

import java.util.List;

import patriciaTrie.interfaces.IPatriciaTrie;;

public class PatriciaTrie implements IPatriciaTrie{
	
	private Node[26] nodes = new Node();
	
	public PatriciaTrie(){
		first = new Node('~');
	}

	@Override
	public void addWord(String word) {

		int k=0;
		boolean trouve;
		List<Node> fils;

		fils = first.getFils();
		
		for(int i=0; i<word.length(); i++){
			trouve=false;
			k=0;
			while( k < fils.size()){
				if(fils.get(k).getValeur() == word.charAt(i)){
					fils = fils.get(k).getFils();
					trouve=true;
					break;
				}
				k++;
			}
			
			if(trouve==false){
				for(int j=i; j<word.length(); j++){
					Node node = new Node(word.charAt(j));
					fils.add(node);
					fils = node.getFils();
				}
				
				break;
			}
			
		}
		
		
		fils.add(new Node('~'));
		
	}
	
	

	@Override
	public boolean findWord(String word) {
		
		boolean trouve ;
		List<Node> fils = first.getFils();
		
		for(int i=0 ; i<word.length(); i++){
			
			trouve = false;
			
			for(Node n : fils){
				
				if(word.charAt(i)==n.getValeur()){
					fils=n.getFils();
					trouve=true;
					break;
				}
				
			}
			
			if(trouve == false){
				return false;
			}

		}
		
		return true;
	}
	
	
	
	@Override
	public boolean deleteWord(String word){
		
		boolean deleted = false;
		List<Node> fils = first.getFils();
		Node pere = first;
		
		if(findWord(word)==false){
			return false;
		}
		
		for(int i=0 ; i<word.length(); i++){
			
			for(Node n : fils){
				if(n.getValeur()== word.charAt(i)){
					if(n.getFils().size()==1){
						deleted=true;
						break;
					}
					else{
						pere = n;
						fils = n.getFils();
						break;
					}
				}
				if(deleted==true)
					break;
			}
			
		}
		
		return true;
	}

	
	
	@Override
	public String toString(){
		
		StringBuilder word = new StringBuilder();
		
		for(Node n : first.getFils()){
				word.append(n.toString());
				word.append('\n');
			}
		
		return word.toString();
		
	}

	
}
