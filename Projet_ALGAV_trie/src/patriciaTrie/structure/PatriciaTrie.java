package patriciaTrie.structure;


public class PatriciaTrie {
	
	private PatriciaTrie[] patTries = new PatriciaTrie[27];
	private char valeur;
	private int ind;
	private String feuille;
	
	public PatriciaTrie(){
		ind=1;
	}
	
	private PatriciaTrie(char valeur, int ind){
		this.valeur=valeur;
		this.ind=ind;
	}

	
	public void addWord(String word) {
		
		char c = word.charAt(ind);
		PatriciaTrie p = patTries[c-97];
		if(p==null){
			p = new PatriciaTrie(c, c-97);
			p.feuille = word;
			return;
		}
		
		if(p.feuille==null ){
			for(int i=0; i<word.length(); i++){
				
			}
		else{
			String mot1, mot2;
			if(feuille.length()<word.length()){
				mot1=feuille;
				mot2=word;
			}
			else{
				mot1=word;
				mot2=feuille;
			}
			int i;
			for(i=0; i<mot1.length(); i++){
				if(mot1.charAt(i)!=mot2.charAt(i)){
					break;
				}
			}
			if(i==mot1.length()){
				patTries[27]=new PatriciaTrie('~', i);
				patTries[mot2.charAt(i)-97]=new PatriciaTrie(mot2.charAt(i), 1);
			}
			else{
				patTries[mot1.charAt(i)-97]=new PatriciaTrie(mot2.charAt(i), 1);
				patTries[mot2.charAt(i)-97]=new PatriciaTrie(mot2.charAt(i), 1);
			}
			ind=i;
		}
		
	}

	
	public boolean findWord(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deleteWord(String word) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*private Node[] nodes = new Node[26];
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
				//si on atteint un caract�re final dans le noeud
				if(valeurNoeud.charAt(i)==endChar.charAt(0)){
					findEnd=true;
					break;
				}
				
				//quand on atteint le premier caract�re qui diff�re dans le noeud
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
		
	}*/

	
}
