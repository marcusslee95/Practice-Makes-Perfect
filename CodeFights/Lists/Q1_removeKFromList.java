//TLDR: To remove something in list need to reassign pointers.
// In particular need, previous node.next to point to current.next (so that previous' nodes .next could skip current)


// Definition for singly-linked list:
// class ListNode<T> {
//   ListNode(T x) {
//     value = x;
//   }
//   T value;
//   ListNode<T> next;
// }
//
ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
    //Part1: Create Previous node (need so that will reassign previous.next to current.next if value same)
    ListNode<Integer> newHead = new ListNode<>(null);
    newHead.next = l;
    
    ListNode<Integer> current = l;
    ListNode<Integer> previous = newHead;

    //Part2: If-Else 두가지 길. 1. current value == k then skip current and update current (no need to update prev cuz it's still prev)
    //2. current value != k then update previous and current (aka. move on. )
    while(current != null) {
        if(current.value == k) {
            previous.next = current.next;
            current = current.next;
        }
        else {
            previous = current;
            current = current.next;
        }
    }
    
    //PArt3: NOT l because u don't want l (let's say first value is k then want that eliminated) want start of list to be whatever dummynode.next poiints to.
    return newHead.next;
}
