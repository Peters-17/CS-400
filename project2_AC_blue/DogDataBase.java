// --== CS400 Project Two File Header ==--
// Name: Yang Qiu
// Email: qiu67@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun
import java.util.*;

interface ExtendedSortedCollectionInterface<T extends Comparable<T>, A, B, C>
    extends SortedCollectionInterface<T> {
    // A, B, C are data type of fields in the nodes of the collection

    @Override
    // insert node
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean insert(A name, B year, C type)
        throws NullPointerException, IllegalArgumentException;

    @Override
    public boolean contains(T data);

    //search the input name
    public T[] searchByName(A name);

    public T[] searchByType(C type);

    //search the specific node and change its vaccinationDate
    public boolean changeVaccinationDate(String date, A name, B year, C type);

    public boolean changeColor(String color, A name, B year, C type);

    @Override
    public int size();

    @Override
    public boolean isEmpty();

}


/**
 * This the DogDataBase from the BackEnd developer.
 */
class DogDataBase implements ExtendedSortedCollectionInterface<DogNode, String, Integer, String> {
    RedBlackTree<DogNode> redBlackTree = new RedBlackTree<DogNode>();

    /**
     * This is the method to add one data into the black red tree.
     *
     * @param data to be added into this back red tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *                                  equal data references
     */
    @Override
    public boolean insert(DogNode data) throws NullPointerException, IllegalArgumentException {
        return redBlackTree.insert(data);
    }

    /**
     * This is the method to add one data into the black red tree.
     *
     * @param name name of DogNode
     * @param year year of DogNode
     * @param type type of DogNode
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    @Override
    public boolean insert(String name, Integer year, String type)
        throws NullPointerException, IllegalArgumentException {
        DogNode dogNode = new DogNode(name, year, type);
        return redBlackTree.insert(dogNode);
    }

    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(DogNode data) {
        return redBlackTree.contains(data);
    }

    /**
     * Find all the dogs that have the input name.
     *
     * @param name name of the dog
     * @return an array of all the dogs that have the input name
     */
    @Override
    public DogNode[] searchByName(String name) {
        ArrayList<DogNode> result = new ArrayList<>();
        List<RedBlackTree.Node<DogNode>> nodes = extractValues(this.redBlackTree.root);
        for (RedBlackTree.Node<DogNode> node : nodes) {
            if (node.data.getName().equals(name)) {
                result.add(node.data);
            }
        }
        DogNode[] dogNodes = new DogNode[result.size()];
        for (int i = 0; i < result.size(); i++) {
            dogNodes[i] = result.get(i);
        }
        return dogNodes;
    }


    /**
     * Change BST to array.
     *
     * @param n DogNode
     * @return List<DogNode>
     */
    private static List<RedBlackTree.Node<DogNode>> extractValues(RedBlackTree.Node<DogNode> n) {
        ArrayList<RedBlackTree.Node<DogNode>> result = new ArrayList<>();
        if (n.leftChild != null) {
            result.addAll(extractValues(n.leftChild));
        }

        if (n.rightChild != null) {
            result.addAll(extractValues(n.rightChild));
        }
        result.add(n);
        return result;
    }

    /**
     * Find all the dogs that have the input type.
     *
     * @param type type of the dog
     * @return an array of all the dogs that have the input type
     */
    @Override
    public DogNode[] searchByType(String type) {
        ArrayList<DogNode> result = new ArrayList<>();
        List<RedBlackTree.Node<DogNode>> nodes = extractValues(this.redBlackTree.root);
        for (RedBlackTree.Node<DogNode> node : nodes) {
            if (node.data.getType().equals(type)) {
                result.add(node.data);
            }
        }
        DogNode[] dogNodes = new DogNode[result.size()];
        for (int i = 0; i < result.size(); i++) {
            dogNodes[i] = result.get(i);
        }
        return dogNodes;
    }

    /**
     * Change the VaccinationDate of the specific Dognode.
     *
     * @param date date of the dog
     * @param name name of the dog
     * @param year year of the dog
     * @param type type of the dog
     * @return
     */
    @Override
    public boolean changeVaccinationDate(String date, String name, Integer year, String type) {
        DogNode dogNode = new DogNode(name, year, type);
        if (this.redBlackTree.contains(dogNode)) {
            changeDogDate(dogNode, date);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change the color of the specific Dognode.
     *
     * @param color color of the dog
     * @param name  name of the dog
     * @param year  year of the dog
     * @param type  type of the dog
     * @return
     */
    @Override
    public boolean changeColor(String color, String name, Integer year, String type) {
        DogNode dogNode = new DogNode(name, year, type);
        if (this.redBlackTree.contains(dogNode)) {
            changeColor(dogNode, color);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data  the data value to test for
     * @param color the color to search through
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public void changeColor(DogNode data, String color) {
        // null references will not be stored within this tree
        if (data == null)
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        this.findDogColorHelper(data, this.redBlackTree.root, color);
    }

    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data the data value to test for
     * @param date the date to search through
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public void changeDogDate(DogNode data, String date) {
        // null references will not be stored within this tree
        if (data == null)
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        this.findDogDateHelper(data, this.redBlackTree.root, date);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     *
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @param date    the date to search through
     * @return true of the value is in the subtree, false if not
     */
    private DogNode findDogDateHelper(DogNode data, RedBlackTree.Node<DogNode> subtree,
        String date) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return null;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return findDogDateHelper(data, subtree.leftChild, date);
            } else if (compare > 0) {
                // go right in the tree
                return findDogDateHelper(data, subtree.rightChild, date);
            } else {
                // we found it :)
                subtree.data.setVaccinationDate(date);
                return null;
            }
        }
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     *
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @param color   the color to search through
     * @return true of the value is in the subtree, false if not
     */
    private DogNode findDogColorHelper(DogNode data, RedBlackTree.Node<DogNode> subtree,
        String color) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return null;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return findDogDateHelper(data, subtree.leftChild, color);
            } else if (compare > 0) {
                // go right in the tree
                return findDogDateHelper(data, subtree.rightChild, color);
            } else {
                // we found it :)
                subtree.data.setColor(color);
                return null;
            }
        }
    }

    /**
     * Get the size of the black red tree.
     *
     * @return the size of the black red tree
     */
    @Override
    public int size() {
        return this.redBlackTree.size();
    }

    /**
     * Check whether the black red tree is empty.
     *
     * @return true if the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        if (this.redBlackTree.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<DogNode> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<DogNode>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<RedBlackTree.Node<DogNode>> stack = null;
            RedBlackTree.Node<DogNode> current = redBlackTree.root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public DogNode next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<RedBlackTree.Node<DogNode>>();
                    current = redBlackTree.root;
                }
                // go left as far as possible in the sub tree we are in until we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    RedBlackTree.Node<DogNode> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * @return boolean indicating whether there are more elements / steps for the traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()));
            }

        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     *
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    @Override
    public String toString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<DogNode> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            DogNode data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }
}

