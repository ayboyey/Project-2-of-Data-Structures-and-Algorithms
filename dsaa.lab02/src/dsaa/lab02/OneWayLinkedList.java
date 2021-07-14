package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
	
	private class Element{
		E object;
		Element next = null;

		public Element(E obj) {
			this.object = obj;
		}

		private void setNext(Element elem){
			this.next = elem;
		}
		private void setData(E data){
			this.object = data;
		}
	}
	
	Element sentinel;
	
	private class InnerIterator implements Iterator<E>{
		Element current;

		public InnerIterator() {
			current = sentinel;
		}

		@Override
		public boolean hasNext() {
			return current.next != null;
		}
		
		@Override
		public E next() {
			if (hasNext()){
				current = current.next;
				return current.object;
			}
			else return null;
		}

		private Element nextElement(){
			current = current.next;
			return current;
		}
	}
	
	public OneWayLinkedList() {
		this.sentinel = new Element(null);
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		while (itera.hasNext()) {
			last = itera.nextElement();
		}
		last.setNext(new Element(e));
		return true;
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {
		if (index < 0) throw new NoSuchElementException("Index out of bounds");
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		for (int i = 0; i < index; i++) {
			if (itera.hasNext()){
				last = itera.nextElement();
			}
			else throw new NoSuchElementException("Index out of bounds");
		}
		Element current = new Element(element);
		current.setNext(last.next);
		last.setNext(current);
	}

	@Override
	public void clear() {
		sentinel.setNext(null);
	}

	@Override
	public boolean contains(E element) {
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		while (itera.hasNext()) {
			last = itera.nextElement();
			if (last.object.equals(element)) return true;
		}
		return false;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
		if (index < 0) throw new NoSuchElementException("Index out of bounds");
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		for (int i = 0; i < index + 1; i++) {
			if (itera.hasNext()){
				last = itera.nextElement();
			}
			else throw new NoSuchElementException("Index out of bounds");
		}
		return last.object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
		if (index < 0) throw new NoSuchElementException("Index out of bounds");
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		for (int i = 0; i < index + 1; i++) {
			if (itera.hasNext()){
				last = itera.nextElement();
			}
			else throw new NoSuchElementException("Index out of bounds");
		}
		E curr = last.object;
		last.setData(element);
		return curr;
	}

	@Override
	public int indexOf(E element){
		InnerIterator itera = new InnerIterator();
		Element last = sentinel;
		int num = -1;
		while (itera.hasNext()) {
			last = itera.nextElement();
			num++;
			if (last.object.equals(element)) return num;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next == null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		InnerIterator iter = new InnerIterator();
		Element last = sentinel;
		if (index == 0 && !iter.hasNext()) throw new NoSuchElementException("Index out of bounds");
		if (index == size() || index < 0) throw new NoSuchElementException("Index out of bounds");
		for (int i = 0; i < index; i++) {
			if (iter.hasNext()){
				last = iter.nextElement();
			}
			else throw new NoSuchElementException("Index out of bounds");
		}
		Element elem = last.next;
		last.setNext(elem.next);
		return elem.object;
	}

	@Override
	public boolean remove(E element) {
		InnerIterator itera= new InnerIterator();
		Element last = sentinel;
		while (itera.hasNext()) {
			if (last.next.object.equals(element)) {
				last.setNext(last.next.next);
				return true;
			}
			last = itera.nextElement();
		}
		return false;
	}

	@Override
	public int size() {
		InnerIterator itera = new InnerIterator();
		int num = 0;
		while (itera.hasNext()) {
			itera.nextElement();
			num++;
		}
		return num;
	}
}
