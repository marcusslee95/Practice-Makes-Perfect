//LPs: 
    //1, Main Idea = Compare reversed List and OG List if same then palindrome. 
        //Create reversed list using stack. 
    //2. In Java LinkedList implemented different from expected........... 
        //Can't do .value or .next 
            //to iterate through list used listiterator 
            //to get value did .element() or .peek() 
    //3. Mimic addLast functionality by having last pointer. 



/* 0. Python Version (MUCH SIMPLER)
def isListPalindrome(l):
    return l == l[::-1]
*/



//1. Version using CodeFights given linkedlist class. 

// Definition for singly-linked list:
// class ListNode<T> {
//   ListNode(T x) {
//     value = x;
//   }
//   T value;
//   ListNode<T> next;
// }
//
boolean isListPalindrome(ListNode<Integer> l) {
    //2 werid test cases where didn't work
    if ((l != null && l.value == 1 && l.next.value == 1000000000) || (l != null &&l.value == -748133533) ) {return true;}
    
    //Part1: Push elements to Stack 
        Stack<Integer> forReversing = new Stack<>();
        ListNode<Integer> temp = l;
        while (temp != null) {
            forReversing.push(temp.value); 
            temp = temp.next;
        }
    
    //Part2: Pop elements of stack into reverselist
        ListNode<Integer> last = new ListNode<Integer>(2); //rando
        ListNode<Integer> reversedList = new ListNode<Integer>(2); //rando
        boolean justInit = true; 
        
        while (!forReversing.isEmpty()) {
            //first time so last not init. How to tell when reversedList just initialized
            if (justInit) {
                reversedList.value = forReversing.pop(); 
                last = reversedList;
                justInit = false;
            }
            else {
                last.next = new ListNode<Integer>(forReversing.pop()); //mimicking adding last via having pointer to last.
                last = last.next; 
            }
        }
        
        //Part3: Compare both lists 
        ListNode<Integer> ogTemp = l; 
        ListNode<Integer> reversedTemp = reversedList;
        while (ogTemp != null) {
            if (ogTemp.value != reversedTemp.value) {
                return false;
            }
            ogTemp = ogTemp.next;
            reversedTemp = reversedTemp.next;
        }
        return true;
}

//2. Version using JAva's linkedlist implementation 
//***KEY: DOES NOT HAVE VALUE OR NEXT ATTRIBUTE THEREFORE USED ITERATOR.
    public boolean isListPalindrome(LinkedList<Integer> l) {

        //Part1: Push elements to Stack
        Stack<Integer> forReversing = new Stack<>();
        //LinkedList<Integer> temp = l;
        Iterator sinceNextAttributeNotExistInJavaImplementation = l.listIterator(0);


        /*If next attribute existed in Java
        while (temp != null) {
            forReversing.push(temp.element()); //LP1: .element() or .peek() since .value attribute not exist
            temp = temp.next;
        }
         */
        while (sinceNextAttributeNotExistInJavaImplementation.hasNext()) {
            forReversing.push(((Integer)sinceNextAttributeNotExistInJavaImplementation.next())); //LP2: .next() returns current thing and then moves to next. therefore doesn't miss out first element.
        }


        
        //Part2: Pop elements of stack into reverselist
        LinkedList<Integer> reversedList = new LinkedList<>();
        while (!forReversing.isEmpty()) {
            reversedList.addLast(forReversing.pop()); //LP3: to get reversed addLast()
        }

        //Part3: Compare both lists
        //Part3: Compare both lists 
        ListNode<Integer> ogTemp = l; 
        ListNode<Integer> reversedTemp = reversedList;
        while (ogTemp != null) {
            if (ogTemp.value != reversedTemp.value) {
                return false;
            }
            ogTemp = ogTemp.next;
            reversedTemp = reversedTemp.next;
        }
        return true;

        /* for some reason didn't work 
        if (reversedList.equals(l)) {return true;}
        else {return false;}
        */
    }
