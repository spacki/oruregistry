package org.openehealth.ipf.ws.oru.cda2xds.beans;

import java.util.*;

public class EventCodeList implements List<EventCodeBean> {
	private List<EventCodeBean> eventCodes = new ArrayList<EventCodeBean>();
	
	/**
	 * Returns a list of strings that represents all the unique eventCodeDisplayNames
	 * @return
	 */
	public List<String> getEventCodeDisplayNames(){
		ArrayList<String> displayNames = new ArrayList<String>();
		
		Iterator<EventCodeBean> it = this.eventCodes.iterator();
		
		while(it.hasNext()){
			String displayName = it.next().getDisplayName();
			
			if(!displayNames.contains(displayName))
				displayNames.add(displayName);
		}
		
		return displayNames;
	}
	
	/**
	 * Returns a list of strings that represents all the unique eventCodeNodeRepresentations
	 * @return
	 */
	public List<String> getEventCodeNodeRepresentations(){
		ArrayList<String> nodeRepresentations = new ArrayList<String>();
		
		Iterator<EventCodeBean> it = this.eventCodes.iterator();
		
		while(it.hasNext()){
			String nodeRep = it.next().getNodeRepresentation();
			
			if(!nodeRepresentations.contains(nodeRep))
				nodeRepresentations.add(nodeRep);
		}
		
		return nodeRepresentations;
	}
	
	/**
	 * Returns a list of strings that represents all the unique eventCodeCodingSchemes
	 * @return
	 */
	public List<String> getEventCodeCodingSchemes(){
		ArrayList<String> codingSchemes = new ArrayList<String>();
		
		Iterator<EventCodeBean> it = this.eventCodes.iterator();
		
		while(it.hasNext()){
			String codingScheme = it.next().getCodingScheme();
			
			if(!codingSchemes.contains(codingScheme))
				codingSchemes.add(it.next().getCodingScheme());
		}
		
		return codingSchemes;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("eventCodeList:\n");
		
		Iterator<EventCodeBean> it = this.eventCodes.iterator();
		int count = 0;
		while(it.hasNext()){
			builder.append("\tEvent Code " + count + ": " + it.next().toString() + "\n");
			count++;
		}
		
		return builder.toString();
	}

	@Override
	public boolean add(EventCodeBean e) {		
		return this.eventCodes.add(e);
	}

	@Override
	public void add(int index, EventCodeBean element) {
		this.eventCodes.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends EventCodeBean> c) {
		return this.eventCodes.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends EventCodeBean> c) {
		return this.eventCodes.addAll(index, c);
	}

	@Override
	public void clear() {
		this.eventCodes.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.eventCodes.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.eventCodes.containsAll(c);
	}

	@Override
	public EventCodeBean get(int index) {
		return this.eventCodes.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return this.eventCodes.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return this.eventCodes.isEmpty();
	}

	@Override
	public Iterator<EventCodeBean> iterator() {
		return this.eventCodes.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.eventCodes.lastIndexOf(o);
	}

	@Override
	public ListIterator<EventCodeBean> listIterator() {
		return this.eventCodes.listIterator();
	}

	@Override
	public ListIterator<EventCodeBean> listIterator(int index) {
		return this.eventCodes.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return this.eventCodes.remove(o);
	}

	@Override
	public EventCodeBean remove(int index) {
		return this.eventCodes.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.eventCodes.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.eventCodes.retainAll(c);
	}

	@Override
	public EventCodeBean set(int index, EventCodeBean element) {
		return this.eventCodes.set(index, element);
	}

	@Override
	public int size() {
		return this.eventCodes.size();
	}

	@Override
	public List<EventCodeBean> subList(int fromIndex, int toIndex) {
		return this.eventCodes.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return this.eventCodes.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.eventCodes.toArray(a);
	}
}
