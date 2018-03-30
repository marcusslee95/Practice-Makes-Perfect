/* 
Approach: almostIncreasingsequence means if u remove one index it'd be strictly increasing. So, remove 문제 index (1 of 2) by....
Create two arrays each w/out a 문제 index.
If both arrays are not sorted - means that u r not could not find way to remove one and have strictly increasing therefore false. 


LESSONS: 
1. Way to initialize variable that have to use later so can't set as rando
aka. int firstCandidate = -1; //만약 set to 0 했으면 and never ran into problem where current index > next index thenn...... 
in later for loop where " if (firstCandidate == i) " would get triggered at 0. No bueno. 
Set to -1 so that if never ran into problem (aka. firstcandidate never change) if (firstCandidate == i) never triggers 

2. Understanding heart of the question = u don't care about any indexes before current index > next index. 
    -therefore in 2nd for loop, 아무생각없이 계속 copy index 하다가 aka. check1[i] = sequence[i]; 
        문제 index에 도달하면, skip it (aka. check1[i] = sequence[i+1];)
        -How to differentiate aka. know you're past skipped index? variable for boolean value else if (ifPastfirstCand) 


3. Better to do different stuff 따로따로 - efficient하지 않을지언정. Refactor later. 



WriteUp: 
Given a sequence of integers as an array, determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.

Example

For sequence = [1, 3, 2, 1], the output should be
almostIncreasingSequence(sequence) = false;

There is no one element in this array that can be removed in order to get a strictly increasing sequence.

For sequence = [1, 3, 2], the output should be
almostIncreasingSequence(sequence) = true.

You can remove 3 from the array to get the strictly increasing sequence [1, 2]. Alternately, you can remove 2 to get the strictly increasing sequence [1, 3].

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer sequence

Guaranteed constraints:
2 ≤ sequence.length ≤ 105,
-105 ≤ sequence[i] ≤ 105.

[output] boolean

Return true if it is possible to remove one element from the array in order to get a strictly increasing sequence, otherwise return false.

*/


boolean almostIncreasingSequence(int[] sequence) {
    int firstCandidate = -1; //***LP: this way firstcandidate is never i in 2nd for loop unless had not right order once. 
    int secondCandidate = -1; 
    
    //look for when not right order aka. current >= next
    for (int i = 0; i < sequence.length - 1 ; i ++) {
        if (sequence[i] >= sequence[i+1]) {
            firstCandidate = i;
            secondCandidate = i+1;
        }
    }
    
    
    if (firstCandidate == -1 && secondCandidate == -1) {
        return true;
    }
    //don't execute code below if everything's in right order
    
        
    //**KEY: Wanted to go through all of sequence indexes but forgot after removed index want to copy the i+1th index to ith of check1. 
    //create two arrays w/out index to remove
    int[] check1 = new int[sequence.length - 1];
    int[] check2 = new int[sequence.length - 1];
    boolean ifPastfirstCand = false;
    boolean ifPastsecondCand = false;
    for (int i = 0; i < sequence.length - 1; i++) {
        //at the index want to remove don't copy
        if (firstCandidate == i) {
            ifPastfirstCand = true;
            check1[i] = sequence[i+1]; //Don't forget cuz let's say i = 0 and firstcandidate was 0. then u want check1[0] = sequence[1]. w/out this statement, would go to i = 1 so would do check1[1] = sequence[2] so check1[0] = sequence[1] forgotten. 
            continue;
        }
        else if (ifPastfirstCand) {
            check1[i] = sequence[i+1];
        }
        else {
        check1[i] = sequence[i]; //want to copy last index of sequence too even though creating array of length sequence.length - 1 therefore loop should be i < sequence.length to cover all index of sequence.
        }
    }
    for (int i = 0; i < sequence.length - 1; i++) {
        if (secondCandidate == i) {
            ifPastsecondCand = true; 
            check2[i] = sequence[i+1];
             continue;
        }
        else if (ifPastsecondCand) {
            check2[i] = sequence[i+1];
        }
        else {
        check2[i] = sequence[i];
        }    
    }
    
    //check if both arrays not sorted. if so false. 
    boolean check1NotSorted = false; 
    for (int i = 0; i < check1.length - 1; i++) {
        if (check1[i] >= check1[i+1]) {check1NotSorted = true;}
    }
    
    boolean check2NotSorted = false;
    for (int i = 0; i < check2.length - 1; i++) {
        if (check2[i] >= check2[i+1]) {check2NotSorted = true;}
    }
    
    if (check1NotSorted && check2NotSorted) {return false;}
    
    return true;
    
    
}