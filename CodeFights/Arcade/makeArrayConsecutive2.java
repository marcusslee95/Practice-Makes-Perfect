/*
Ratiorg got statues of different sizes as a present from CodeMaster for his birthday,
 each statue having an non-negative integer size. Since he likes to make things perfect, he wants 
 to arrange them from smallest to largest so that each statue will be bigger than the previous one exactly by 1. 
 He may need some additional statues to be able to accomplish that. Help him figure out the 
 minimum number of additional statues needed.

Example

For statues = [6, 2, 3, 8], the output should be
makeArrayConsecutive2(statues) = 3.

Ratiorg needs statues of sizes 4, 5 and 7.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer statues

An array of distinct non-negative integers.

Guaranteed constraints:
1 ≤ statues.length ≤ 10,
0 ≤ statues[i] ≤ 20.

[output] integer

The minimal number of statues that need to be added to existing statues such that it 
contains every integer size from an interval [L, R] (for some L, R) and no other sizes.
*/




static public int makeArrayConsecutive2(int[] statues) {
        //Part1: Find Max, min
        int max = statues[0];
        int min = statues[0];

        for (int i = 1; i < statues.length; i++) {
            max = Math.max(max, statues[i]);
            min = Math.min(min, statues[i]);
        }
        
        /*
        for (int statue: statues) {
            max = Math.max(max, statue);
            min = Math.min(min, statue);
        }
        */

        //Part2: Make bool Array of length max - min 
        boolean [] missing = new boolean[max - min + 1]; //***LP: let's say min is 0 and max is 10. to fit all requires 11 indexes therefore + 1

        //Part3: fill in array w/true values

        for (int i = 0; i < statues.length; i++) {
            missing[statues[i] - min] = true;
        }
        /*
        for (int statue: statues) {
            missing[statue - min] = true; 
        }
        */

        //part4: count for all False indexes aka. integers that you don't have and need
        int count = 0;
        for (boolean thing: missing) {
            if (!thing) {count++;}
        }

        return count;
    }