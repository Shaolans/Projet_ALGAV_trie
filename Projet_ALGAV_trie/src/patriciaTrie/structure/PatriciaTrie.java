package patriciaTrie.structure;


public class PatriciaTrie {
	
	private PatriciaTrie[] patTries = new PatriciaTrie[27];
	private int ind;
	private String feuille;
	
	public PatriciaTrie(){
		ind=0;
	}
	
	private PatriciaTrie(int ind){
		this.ind=ind;
	}

	
	public static boolean addWord(PatriciaTrie p, String word) {

		if(findWord(p, word)){
			return false;
		}
		
		char c = word.charAt(p.ind);
		if(p.patTries[c-97]==null){
			p.patTries[c-97] = new PatriciaTrie(p.ind+1);
			p.patTries[c-97].feuille = word;
		}
		else{
			if(p.patTries[c-97].feuille==null ){
				for(int i=p.ind; i<word.length(); i++){
					if(p.patTries[word.charAt(i)-97]!=null && i==p.ind){
						addWord(p.patTries[word.charAt(i)-97],word);
						break;
					}
				}
				
			}
			else{
				
				String mot1, mot2;
				if(p.patTries[c-97].feuille.length()<word.length()){
					mot1=p.patTries[c-97].feuille;
					mot2=word;
				}
				else{
					mot1=word;
					mot2=p.patTries[c-97].feuille;
				}
				int i;
				for(i=0; i<mot1.length(); i++){
					if(mot1.charAt(i)!=mot2.charAt(i)){
						break;
					}
				}
				if(i==mot1.length()){
					p.patTries[c-97].patTries[26]=new PatriciaTrie(i);
					p.patTries[c-97].patTries[mot2.charAt(i)-97]=new PatriciaTrie(i);
					p.patTries[c-97].patTries[26].feuille = mot1;
					p.patTries[c-97].patTries[mot2.charAt(i)-97].feuille = mot2;
				}
				else{
					p.patTries[c-97].patTries[mot1.charAt(i)-97]=new PatriciaTrie(i);
					p.patTries[c-97].patTries[mot2.charAt(i)-97]=new PatriciaTrie(i);
					p.patTries[c-97].patTries[mot1.charAt(i)-97].feuille = mot1;
					p.patTries[c-97].patTries[mot2.charAt(i)-97].feuille = mot2;
				}
				p.patTries[c-97].feuille=null;
				p.patTries[c-97].ind=i;
			}
		}
		return true;
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder word = new StringBuilder();
		for(int i=0; i<27; i++){
			if(patTries[i]!=null){
				word.append("niveau " +((char)(i+97))+" "+ind+"\n");
				if(patTries[i].feuille!=null){
					word.append(patTries[i].feuille);
					word.append("\n");
				}
				else{
					word.append(patTries[i].toString());
				}
			}
		}
		
		return word.toString();
		
	}

	
	public static boolean findWord(PatriciaTrie p, String word) {

		if(word.length()==p.ind){

			if(p.patTries[26]!=null)
				return true;
			else
				return false;
		}
		
		char c = word.charAt(p.ind);

		if(p.patTries[c-97]==null)
			return false;
		
		if(word.equals(p.patTries[c-97].feuille))
			return true;
		
		return findWord(p.patTries[c-97], word);
		
	}

	
	public static boolean deleteWord(PatriciaTrie p, String word) {		
		
		if(!findWord(p, word))
			return false;
		
		if(word.length()==p.ind){
			p.patTries[26]=null;
		}
		else{
			char c = word.charAt(p.ind);
			
			
			if(p.patTries[c-97].feuille==word){
				p.patTries[c-97]=null;
				
					int patTriesOqp=0;
					int autrePat=0;
					for(int i=0; i<27; i++){
						if(p.patTries[i]!=null){
							if(i!=c-97)
								autrePat = i;
							patTriesOqp++;
						}
						if(patTriesOqp==2)
							break;
					}
					if(patTriesOqp==1){
						if(p.patTries[autrePat].feuille==null){
							p.ind = p.patTries[autrePat].ind;
							p.patTries = p.patTries[autrePat].patTries;
						}
						else{
							p.feuille = p.patTries[autrePat].feuille;
							p.patTries[autrePat]=null;
						}
					}
				
			}
			else{
				deleteWord(p.patTries[c-97], word);
			}
		}
		
		return true;
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
