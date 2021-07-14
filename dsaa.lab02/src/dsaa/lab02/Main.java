package dsaa.lab02;

import java.util.*;

public class Main {

	static Scanner scan; 
	public static void main(String[] args) {
		System.out.println("START");
		scan=new Scanner(System.in);
		Document[] doc=null;
		int currentDocNo=0;
		int maxNo=-1;
		boolean halt=false;
		while(!halt) {
			String line=scan.nextLine();
		
			if(line.length()==0 || line.charAt(0)=='#')
				continue;
			
			System.out.println("!"+line);
			String[] word=line.split(" ");
			
			if(word[0].equalsIgnoreCase("go") && word.length==2) {
				maxNo=Integer.parseInt(word[1]);
				doc = new Document[maxNo];
				continue;
			}
			
			if(word[0].equalsIgnoreCase("ch") && word.length==2) {
				currentDocNo=Integer.parseInt(word[1]);
				continue;
			}

		
			if(word[0].equalsIgnoreCase("ld") && word.length==2) {
				doc[currentDocNo]=new Document(word[1],scan);
				continue;
			}
			
			if(word[0].equalsIgnoreCase("ha") && word.length==1) {
				halt=true;
				continue;
			}
			
			if(word[0].equalsIgnoreCase("clear") && word.length==1) {
				doc[currentDocNo].links.clear();
				continue;
			}
		
			if(word[0].equalsIgnoreCase("show") && word.length==1) {
				System.out.println(doc[currentDocNo].toString());
				continue;
			}			
		
			if(word[0].equalsIgnoreCase("size") && word.length==1) {
				System.out.println(doc[currentDocNo].links.size());
				continue;
			}			
			
			if(word[0].equalsIgnoreCase("add") && word.length==2) {
				System.out.println(doc[currentDocNo].links.add(new Link(word[1])));
				continue;
			}			
		
			if(word[0].equalsIgnoreCase("addi") && word.length==3) {
				int index=Integer.parseInt(word[1]);
				try {
					doc[currentDocNo].links.add(index, new Link(word[2]));
				}
				catch (NoSuchElementException e) {
					System.out.println("error");
				}
				continue;
			}			
	
			if(word[0].equalsIgnoreCase("get") && word.length==2) {
				int index=Integer.parseInt(word[1]);
				try {
					Link l=doc[currentDocNo].links.get(index);
					System.out.println(l.ref);
				}
				catch(NoSuchElementException e) {
					System.out.println("error");
				}
				continue;
			}			
		
			if(word[0].equalsIgnoreCase("set") && word.length==3) {
				int index=Integer.parseInt(word[1]);
				try {
					Link l=doc[currentDocNo].links.set(index,new Link(word[2]));
					System.out.println(l.ref);
				}
				catch(NoSuchElementException e) {
					System.out.println("error");
				}
				continue;
			}			
		
			if(word[0].equalsIgnoreCase("index") && word.length==2) {
				int index=doc[currentDocNo].links.indexOf(new Link(word[1]));
				System.out.println(index);
				continue;
			}	
		
			if(word[0].equalsIgnoreCase("remi") && word.length==2) {
				int index=Integer.parseInt(word[1]);
				try {
					Link l=doc[currentDocNo].links.remove(index);
					System.out.println(l.ref);
				}
				catch(NoSuchElementException e) {
					System.out.println("error");
				}
				continue;
			}	
		
			if(word[0].equalsIgnoreCase("rem") && word.length==2) {
				System.out.println(doc[currentDocNo].links.remove(new Link(word[1])));
				continue;
			}				
			System.out.println("Wrong command");			
		}
		System.out.println("END OF EXECUTION");
		scan.close();
	}
}
