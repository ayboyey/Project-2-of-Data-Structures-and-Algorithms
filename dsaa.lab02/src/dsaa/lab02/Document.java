package dsaa.lab02;

import java.util.Scanner;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links;

	public Document(String name, Scanner scan) {
		this.name = name;
		links = new OneWayLinkedList<Link>();
		load(scan);
	}

	public void load(Scanner scan) {
		String line = "";
		do{
			line = scan.next().toLowerCase();
			if (correctLink(line))
				links.add(new Link(line.substring(5)));
		}
		while (!line.equals("eod"));
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static boolean correctLink(String link) {
		return link.startsWith("link=") && link.substring(5).matches("[a-z][a-z0-9_]*");
	}
	
	@Override
	public String toString() {
		String s = "Document: " + name;
		for (Link link : links) {
			s += "\n" + link.ref;
		}
		return s;
	}
}
