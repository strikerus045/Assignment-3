import java.util.*;

/**
 * Generic BasicDoubleLinkedList class implements an iterable
 * @author Chris Duong
 * @param <T>
 */

public class BasicDoubleLinkedList<T> implements Iterable<T> {

    protected Node headNode, endNode;
    protected int size;
    protected DoubleLinkedListIterator iterator;
    BasicDoubleLinkedList() {
        headNode = null;
        endNode = null;
        size = 0;
       // Constructor that is not paramatized 
    }

    public BasicDoubleLinkedList<T> addToEnd (T data) {
    	
        if (endNode == null) {
            headNode = new Node (data);
             endNode = headNode;
             
        }
        
        else {
        	
            Node newNode = new Node (data, endNode, null);
             endNode.setNext(newNode);
              endNode = newNode;
              
         }
        
        	size++;
        	return this;
        	
    }
 
    public BasicDoubleLinkedList<T> addToFront(T data) {
    	
        if (headNode == null) {
             headNode = new Node(data);
              endNode = headNode;
              
        }
        
        else {
        	
            Node newNode = new Node(data, null, headNode);
             headNode.setPrevious(newNode);
              headNode = newNode;
              
        }
        
        size++;
        return this;
        
    }

    /**
     * @return  T and headnode
     * @throws  attempts to throw NoSuchElementException
     */
    
    public T getFirst() throws NoSuchElementException {
    	
        if (headNode == null) {
        	
            throw new NoSuchElementException();
            
        }
        
        return headNode.getData();
    }

    /**
     * @return endNode data
     * @throws attemtps to throw NoSuchElementException
     */
    
    public T getLast() throws NoSuchElementException {
    	 
        if(endNode == null) {
            throw new NoSuchElementException();
        }
        
        return endNode.getData();
    }

    /**
     * @return attempts to return size
     */
    
    public int getSize() {
    	
        return this.size;
    }

    /** 
     * @return DoubleLinkedListIterator
     * @throws attemtps to throw UnsupportedOperationException
     * @throws attemtps to throw NoSuchElementException
     */
    
    @Override
    public DoubleLinkedListIterator iterator() throws UnsupportedOperationException,NoSuchElementException {
        return new DoubleLinkedListIterator();
        
    }

    //creates DoubleLunkListIterator
    
    public class DoubleLinkedListIterator implements ListIterator<T> {

        Node present;

        DoubleLinkedListIterator() {
        	
            present = null; 
            
        }

        @Override
        public boolean hasNext() {
        	
           return (present == null && headNode != null) || ((present != null) && (present.next != null));
           
        }

        @Override
        public boolean hasPrevious() {
        	
            return (present == null && endNode != null) || ((present != null) );
            
        }

        @Override
        public T previous() {
        	
            T returneddata = null;
            if (!hasPrevious() || size == 0) {
            	
                throw new NoSuchElementException();
                
            }
            
            if (present == null) {
            	
                throw new NoSuchElementException();
                
            }
            
            returneddata = present.getData();
            present = present.previous;
            return returneddata;
            
        }

        @Override
        public T next() throws NoSuchElementException {

            if (!hasNext() || size == 0) {
                throw new NoSuchElementException();
            }
            
            if (present == null && headNode != null){
            	present = headNode;
            }
            
            else if((present!= null) && (present.next != null)){
            	present = present.next;
            }
            
            else {
            	
                throw new NoSuchElementException();
            }
            
            return present.getData();
        }

        @Override
        public int nextIndex() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
            
        }

        @Override
        public int previousIndex() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
            
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
            
        }

        @Override
        public void set(T t) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
            
        }

        @Override
        public void add(T t) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();

        }
        
    }

    /**
     * uses comparator to remove unneccasary elements 
     * @param targetData
     * @param comparator
     * @return
     */
    
    public BasicDoubleLinkedList<T> remove(T mainData, Comparator<T> comparator) {

        Node tempNode;
        if (size == 0) {
        	
            return this;
        }
        
        else if( size == 1) {
        	
            if(comparator.compare(headNode.getData(), mainData) == 0) {
            	
            headNode = null;
            endNode = null;
            size--;
            
        }
            
            return this;
            
        }
        
        tempNode = headNode;

        while(tempNode != null) {

            if(comparator.compare(tempNode.getData(),mainData) == 0) {
            	
            	if(size == 1) {
            		size = 0;
            		headNode = null;
            		endNode = null;
            		size --;
            	break;
                
            }
            
            if(tempNode.equals(headNode)) {
            	
                headNode = headNode.getNext();
                headNode.setPrevious(null);
                
            }
            
            else if(tempNode.equals(endNode)) {
            
                endNode = endNode.getPrevious();
                endNode.setNext(null);
            }
            
            else {
            	
                tempNode.getPrevious().setNext(tempNode.getNext());
                tempNode.getNext().setPrevious(tempNode.getPrevious());
            }
            
            size--;
            break;

            }
            
            tempNode = tempNode.getNext();
            
        }

        return this;
        
    }

    /**
     * @return element of headNode
     */
    
    public T retrieveFirstElement() {
    	
        T firstElement;

        if(headNode != null) {
        	
            firstElement = headNode.getData();
        }
        else {
        	
            firstElement = null;
        }
        
        if(size > 1) {
        	
            headNode = headNode.getNext();
            headNode.setPrevious(null);
        }
        
        else {
        	
            headNode = null;
            endNode = null;
        }
        
        if (size > 0) {
        	
            size --;
        }
        
        return firstElement;
    }

    /**
     * Retrieves last data
     * @return data of last element
     */
    
    public T retrieveEndElement() {
        T endElement;

        if(endNode != null) {
        	
            endElement = endNode.getData();
            
        }
        else {
        	
            endElement = null;
        }
        
        endNode = endNode.getPrevious();

        if (size > 0) {
        	
            size --;
        }
        
        return endElement;
        
    }

    /**
     * displays array list
     * @return arraylist of generic type created
     */
    
    public ArrayList<T> toArrayList() {

        ArrayList<T> list = (ArrayList<T>) new ArrayList<Object>();
        iterator = iterator();

        while(iterator.hasNext()) {
        	
            iterator.next();
            list.add(iterator.present.getData());
            
        }

        return list;

    }


    /**
     * will generate neccsary nodes 
     */
    
    class Node {
    	
        private T data;
        private Node previous, next;

    /**
     * initialized next and previous
     * @param next
     * @param previous
     * @param data
     */
        
        Node(T data, Node previous, Node next) {
        	
            this.data = data;
            this.previous = previous;
            this.next = next;
            
        }

        Node(T data) {
        	
            this.data = data;
            this.previous = null;
            this.next = null;
            
        }

        public void setData(T data) {
        	
            this.data = data;
        }

        public T getData() {
        	
            return this.data;
            
        }

        public void setNext(Node next) {
        	
            this.next = next;
            	if (next != null) next.previous = this;
            
        }

        public Node getNext() {
        	
            return this.next;
            
        }

        public boolean hasNext() {
        	
        if (this.next == null) {
        	
            return false;
        }
        
        else {
        	
            return true;
        }
        
        }
        public void setPrevious(Node previous) {
        	
            this.previous = previous;
            	if(previous != null) previous.next = this;
        }

        public Node getPrevious() {
        	
            return this.previous;
        }

        public boolean hasPrevious() {
        	
            if (this.previous == null) {
            	
                return false;
            }
            
            else {
            	
                return true;
            }
            
        }

    }
    
}