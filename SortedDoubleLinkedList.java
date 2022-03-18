
/**
*@Author Chris Duong
*/


import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {

    private Comparator<T> comparator;

    public SortedDoubleLinkedList (Comparator<T> comparator) {
    	
        super();
        this.comparator = comparator;
        
    }

    /**
     * Adds elements to the SortedDoubleLinkedList
     * @param data
     * @return creates list
     */
    
    public SortedDoubleLinkedList<T> add(T data) {

        Node t;
        Node next = headNode;
        
        while(next != null && comparator.compare(data, next.getData()) >= 0) {
            next = next.getNext();
            
        }

        Node oldPrevious = (next != null ? next.getPrevious() : null);

        if (size == 0) {
        	        
           t = new Node(data, null, null);
           headNode = endNode = t;
           
        }
        
        else if( oldPrevious == null && next != null) {
        	
            t = new Node(data, oldPrevious, next);
            next.setPrevious(t);
            headNode = t;
            
        }
  
        else if(next == null) {
            t = new Node(data, endNode, null);
            endNode.setNext(t);
            endNode = t;
            
        }
     
        else {
        	
            t = new Node(data, oldPrevious, next);
            oldPrevious.setNext(t);
            next.setPrevious(t);
            
        }

        size++;
        return this;
        
    }

    /**
     * @param data
     * @return
     * @throws attempts to throw UnsupportedOperationException
     */
    
    @Override
    public SortedDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException { 
    	throw new UnsupportedOperationException(); 
    	
    }

    /**
     * @param adds info by nodes
     * @return 
     * @throws attempts to throw UnsupportedOperationException
     */
    
    @Override
    public SortedDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException { 
    	throw new UnsupportedOperationException(); 
    
    }

}
