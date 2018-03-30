/*BETTER APPROACHES: TLDR - 1. Add each value in array into hashmap or set. 2. if it already exists (it'l be the earliest duplicate) so return it. 
Issue w/My sol = forgot that if 처음으로 see something again then it's the earliest duplicate so tried to keep track of duplicate position 
and then compare positions of duplicates later (LOTS OF UNNECESSARY WORK) 

1. USE a SET. (U know if u see something again for the first time that'll be at the lowest index)
def firstDuplicate(a):
    seen = set()
    for i in a:
        if i in  seen:
            return i
        seen.add(i)

    return -1

2. 비슷한 logic using a dictionary (U know if u see something again for the first time that'll be at the lowest index).
*dict key will be array index value because want to see if that is duplicated again.

Two ways to write for loop: 
def firstDuplicate(a):
    temp = {}
    for x in a:
        if x in temp.keys():
            return x
        else: temp[x] = 1
    return -1
    
def firstDuplicate(a):
    counts = dict()
    for i in range(0, len(a)):
        if a[i] in counts: // in java would have done if counts.containsKey(a[i])
            return a[i]
        else:
            counts[a[i]] = 1 //doesn't matter what this is. can make value anything. cuz u're going to return a[i] anyways. 
            
    return -1


*/


import java.util.*;

public class Q1_firstDuplicate {

    public class findingduplicates {
        boolean seenTwice;
        int duplicateIndex;
        //go through hm할때 to tell which duplicate is smallest.

        public findingduplicates () {
            seenTwice = false;
            duplicateIndex = 0;
        }
        public findingduplicates (boolean twice, int dupIndex) {
            seenTwice = true;
            duplicateIndex = dupIndex;
        }

    }

    //CodeFights Q1 Array
    public int firstDuplicate(int[] a) {
        //Part1: create hm tells u if seen twice + dupindex
        HashMap<Integer, findingduplicates> minDupIndex = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!minDupIndex.containsKey(a[i])) {
                minDupIndex.put(a[i], new findingduplicates());
            } else {
                //두번봤으면 duplicate 봤으니 no change
                if (minDupIndex.get(a[i]).seenTwice) {
                    continue;
                }
                //한번봤으면 say 두번봄 and index of dup.
                else {
                    minDupIndex.put(a[i], new findingduplicates(true, i));
                }
            }
        }

        //part2: go through hm to find smallest dupindex
        int rv = -1;
        int smallestindexdup = 10000000;
        //iterate through hm.
        for (Map.Entry<Integer, findingduplicates> entry : minDupIndex.entrySet()) {
            Integer key = entry.getKey();
            findingduplicates value = entry.getValue();
            if (value.seenTwice && value.duplicateIndex < smallestindexdup) {
                smallestindexdup = value.duplicateIndex;
                rv = key;
            }

        }

        return rv;
    }
    //end

    public static void main(String[] args) {
        //Code Fights Array Q1
        Q1_firstDuplicate c = new Q1_firstDuplicate();
        int[] a = new int[]{2, 3, 3, 1, 5, 2};
        /*for (int elem : a) {
            System.out.println(elem);
        }
        */
        System.out.println(c.firstDuplicate(a));
    }

}

