import java.util.*;

public class Arrays_Strings {
    //Part1: Constructors




    //Part2: Methods

    //Q1: no repeating characters
    public static boolean isUnique(String s) {
        //Main Idea: Info u need to solve = 여태본것을 알아야됨 KNOW WHAT YOU'VE SEEN. 그래서 새로운걸 볼때 '오! 이건봣다 / 안 봤다'하지


        //Algo 1: Use HM to store 여태본것, 새로운것 볼때 check if in HM if so return false
        HashMap<String, String> seen = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (seen.containsKey(s.substring(i,i+1))) {return false;} //벌써 봄.
            else {
                seen.put(s.substring(i, i+1), "doesn't matter what this is"); //can't use charAt cuz wants string.
            }
        }
        return true;



        /*
        //Algo 2: W/out DS.
            //Think: DS 없으면 no way to store the 여러 여태 seen characters
            //Only have loops to work w/. Meaning... can only know current character
            //So just compare current character w/every other character for all characters.
        for (int i = 0; i <s.length(); i++) {
            for (int j = i+1; j < s.length(); j++) {//whatever ith value is jth should be one after that
                if (s.charAt(i) == s.charAt(j)) {return false;}
            }
        }
        return true;
        */

        /*
        //Algo 3: W/out DS 2
            //Sort + Linear check

        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);
        for (int i = 0; i < s.length() - 1; i++) {
            if (sorted[i] == sorted[i+1]) {return false;}
        }
        return true;
        */
    }
    //Q1 end


    //Q2: 2 strings. Permutation or nah.
    public static boolean checkPermutation (String s1, String s2) {
        //Main Idea: Permutation means same stuff just different order.

        /*
        //Algo 1: Just make both have same order using sort. then check if same.
        //RT: O(nlogn) cuz sort is nlogn and loop n
        char [] s1Sorted = s1.toCharArray();
        Arrays.sort(s1Sorted);
        char [] s2Sorted = s2.toCharArray();
        Arrays.sort(s2Sorted);

        if (s1Sorted.length != s2Sorted.length) {return false;} //duh 길이 차이 means one has less stuff than other
        for (int i = 0; i < s1Sorted.length; i++) {
            if (s1Sorted[i] != s2Sorted[i]) {return false;}
        }
        return true;
        */


        //Algo 2: Content만 비교하는 방법. Array where each index represents 차이 of how many times
        // that character appears in s1 and s2
        //if all indexes not 0 then means was 차이 in # times character appeared in each string aka. different content
        // aka. not permutation.

        if (s1.length() != s2.length()) {return false;} //길이차이 implies 내용차이

        int[] difference = new int[128]; //# of possible characters in ASC2

        //increment for one string
        for (int i = 0; i < s1.length(); i++) {
            difference[Character.getNumericValue(s1.charAt(i))]++; //duh. index that corresponds to this character i.e. c = 3rd index
        }
        //decrement for the other
        for (int i = 0; i < s2.length(); i++) {
            difference[Character.getNumericValue(s2.charAt(i))]--; //duh. index that corresponds to this character i.e. c = 3rd index
            //if condition after cuz want to check after decrementing

            //*KEY: if two strings have different stuff. then one of indexes will be -
            //i.e. "abc" "dbc" dth index will be -1
            if (difference[Character.getNumericValue(s2.charAt(i))] < 0) {return false;}
        }
        return true;
    }
    //Q2 end



    //Q3: replace all blanks in string w/%20.
    //KEY LP: 충분한 공간이 있으면 should change array from back to front
    public static String URLify(String og, int lengthOfOGDiscountingBlanksAtEnd) {
        /*
        //ALGO 1: URLIFying by Copying copy string values to og string a. but copying 2 indices down each time see blank
        //b. each time see blank do the %20 thing

        //RT: 2 char arrays + 1 copy string = 3n
        // + for loop lengthOfOGDiscountingBlanksAtEnd times (know it's smaller than n so treat as n)
        //another for loop + n
        //4n + lengthOfOGDiscountingBlanksAtEnd (smaller than n) aka O(n)
        //Space Complexity: 5n....


        //Basic Idea: Tried to replace blank w/%20 but then encountered problem...
        //next two indices were lost (i.e. "Mr John Smith   " Jo would get lost cuz replaced by 20
        //*****How not to lose indices? LP1: Just have copy of string. 5
        //This way as you change og string (more accurately character array of og string cuz strings aren't mutable)
        // won't lose the next values (i.e. J after changing to "Mr%20hn Smith   "
        // cuz it's just next index in copy string.
        // LP2: Duh... each time change blank to %20 in OG will have to copy copy indexes to 2 indexes down in
        //OG. SO HAVE A COUNT for # of blanks (each time see blank should copy 2 down so... [i + blanksSoFar * 2]


        String copy = og.substring(0);
        char[] copyArr = copy.toCharArray();
        char[] ogArr = og.toCharArray();

        //copy copy stuff to og stuff. Special Case: BUT when hit blank not copy stuff but just make indices %,2,0
        //next time around, copy copy stuff to og stuff 2 indices down

        int blanksSoFar = 0;
        //reason not just use normal for loop i < og.length() cuz... string has bunch of blanks at end
        //want to end copying after last character before end blanks appear.
        for (int i = 0; i < lengthOfOGDiscountingBlanksAtEnd; i++) {//마지막 캐릭터 gets visited
            //special case
            if (copyArr[i] == ' ') {
                ogArr[i + blanksSoFar * 2] = '%';
                ogArr[i + blanksSoFar * 2 + 1] = '2';
                ogArr[i + blanksSoFar * 2 + 2] = '0';
                blanksSoFar++;
            }
            //늘 하는 것
            else {
                ogArr[i + blanksSoFar * 2] = copyArr[i]; //each time hit blank copy copy indices 2 indices down
            }

        }
        //String getOGagain = Arrays.toString(ogArr); DOES NOT WORK. This method return string rep of array
            //i.e. if arr is [a,b,c] then returns "[a,b,c]" u want "abc"

        StringBuilder getOGAgain = new StringBuilder();
        for (char c : ogArr) {
            //getOGAgain += c; //Every concatenation copies whole string so inefficent use Stringbuilder instead
            getOGAgain.append(c);
        }
        String returnOfTheOG = getOGAgain.toString();
        return returnOfTheOG;
        */




        char [] ogArr = og.toCharArray();
        //Algo 2: Make URL by starting copying last character to last index and then next last character to next last index
        //Special case: when hit blank u do the %20 thingy and then update where next last character should go to 2 indexes up front


        //RT: a couple less loops O(n)
        //Space: a lil less space. than algo 1.


        //Use # blanks to get last index. 그냥 og.length() - 1 이긴 하지만 shows u understand each time see blank two more spots taken up
        int blanks = 0;
        for (int i = 0; i < lengthOfOGDiscountingBlanksAtEnd; i++) {//look through all characters to see blanks
            if (ogArr[i] == ' ') {blanks++;}
        }
        int whereNextLastCharacterShouldGo = lengthOfOGDiscountingBlanksAtEnd + blanks * 2 - 1;

        //copy last character to last available index
        for (int i = lengthOfOGDiscountingBlanksAtEnd - 1; i >= 0; i--) {
            if (ogArr[i] == ' ') {
                ogArr[whereNextLastCharacterShouldGo] = '0';
                ogArr[whereNextLastCharacterShouldGo - 1] = '2';
                ogArr[whereNextLastCharacterShouldGo - 2] = '%';
                whereNextLastCharacterShouldGo -= 3; //cuz %20 takes up 3 indices
            }
            else {
                ogArr[whereNextLastCharacterShouldGo] = ogArr[i];
                whereNextLastCharacterShouldGo -= 1; //duh... update 가장 마지막 단어가 들어갈 자리 each time.
            }
        }

        StringBuilder getOGAgain = new StringBuilder();
        for (char c : ogArr) {
            getOGAgain.append(c);
        }
        String returnOfTheOG = getOGAgain.toString();
        return returnOfTheOG;
    }
    //Q3 Ends


    //Q4: Is a string a permutation of a palindrome
    //Key LP: 흔히 Palindrome은 앞뒤가 똑같다 or 1st ~ last 2nd ~ 2nd last 똑같다가 definition이지만...
        //안을 들여다보면 알게되는 것: just a bunch of pairs of a character (if string is odd length 하나는 지 혼자 not pair)

    //note does not work for strings w/blanks "tactcoa" 되지만 "tact coa"안됨.
    public static boolean permutationOfPalindrome (String s) {
        //Therefore Main Idea: keep track of # times characters appear (what better way to do than HM?)
        //For even length string if ever have character not appear even # times can't become palindrome
        //for odd if > 1 times then ditto

        // HashMap<char, int> hm = new HashMap<char, int>(); LP: HM can't use primitives
        HashMap<String, Integer> hm = new HashMap<>();

        //Part1: go through whole string & update HM
        //for (String partOfs : s) LP: this abbreviated for loop not work for String
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.substring(i, i+1))) {
                //incrementing value
                Integer value = hm.get(s.substring(i, i+1)); //gets u value
                hm.put(s.substring(i, i+1), value+1); //replaces node value
            }
            else {
                hm.put(s.substring(i, i+1), 1);
            }
        }

        //Part2: Go through HM see if any odd counts exist
        if (s.length() % 2 == 0) {
            for (Integer value: hm.values()) {//goes through each value in HM
                if (value % 2 != 0) {
                    return false;
                }
            }
            return true;

        }

        else {//odd length string
            boolean seenOddNumberOnce = false;
            for (Integer value: hm.values()) {//goes through each value in HM
                if (value % 2 != 0 && seenOddNumberOnce) {
                    return false;
                }
                else if (value % 2 != 0) {seenOddNumberOnce = true;}
            }
            return true;
        }
    }
    //Q4 ends


    //Q5: Check if two strings one edit away
    //Main idea: 1. if length diff > 1 then duh.. false
         // 2. If replace case check if same indexes different more than once aka
            // if (s1.charAt(i) != s2.charAt(i) && seenADiff) happens twice
    // 3. Else it's two other cases: (know 길이차이 of 1 and just have to check if > 1 diff.)
        // a. until there's difference just compare same indexes b. after see difference,
        // compare i+1 index of bigger string to i of shorter string.

    //Key LP: 책에 써있는 algo 그대로 했지만. 책 방법 requires less code.
        //Side note: also quick way of setting variable to one of two strings.
    public static boolean oneEditAway(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {return false;}
        boolean seenADiff = false;



        //Part1: Replace
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i) && seenADiff) {return false; } //2nd time see a diff.
                else if (s1.charAt(i) != s2.charAt(i)) {seenADiff = true;}
            }
            //if here know max 하나차이였다
            return true;
        }

        //Part2: Insert or Remove
        else {
            //idea: Go through string 하면서 1. difference 나올때까진 평범 (compare same indexes)
            //2. 나온후부턴 (Compare diff indexes)
            if (s1.length() > s2.length()) {//s1 bigger
                for (int i = 0; i < s2.length(); i++) {//think in last loop will successfully compare last index of shorter w/last index longer
                    if (seenADiff) {//continue comparing i + 1 to i
                        if (s1.charAt(i + 1) != s2.charAt(i)) {return false;} //another diff means 2 diff.
                    }
                    else {
                        if (s1.charAt(i) != s2.charAt(i)) {//know one diff.
                            if (s1.charAt(i + 1) != s2.charAt(i)) {return false;} //if another diff have 2 diff.
                            seenADiff = true;
                        }
                    }
                }
                return true;
            }

            else {//s2 bigger
                //**왠진 모르겠으나 code was not able to find bigger, smaller that i wanted to create above to not write same code
                for (int i = 0; i < s1.length(); i++) {
                    if (seenADiff) {//continue comparing i + 1 to i
                        if (s2.charAt(i + 1) != s1.charAt(i)) {return false;} //another diff means 2 diff.
                    }
                    else {
                        if (s2.charAt(i) != s1.charAt(i)) {//know one diff.
                            if (s2.charAt(i + 1) != s1.charAt(i)) {return false;} //if another diff have 2 diff.
                            seenADiff = true;
                        }
                    }
                }
                return true;
            }
        }
    }
    //Q5 ends


    //Q6 "aabcccaaaa" -> "a2b1c3a4"
    public static String stringCompression(String s) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        //Main Idea: When two indexes different, add index and it's count to string reset to 0 for next index (which is diff. character)
        //가장 큰 이슈: getting count right....
        for (int i = 0; i < s.length(); i++) {
            countConsecutive++; //each time enter loop see another character (if 같음 ++ 다르더라도, count would be 0 so should ++ to 1)
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i+1)) {//last면 무조건 add to string
                compressed.append(s.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0; //reset count
            }
        }
        String compressedString = compressed.toString();
        if (compressedString.length() >= s.length()) {return s;}
        else {return compressedString;}
    }
    //Q6 ends



    public static void main(String[] args) {
        //Q1 noRepeats test
        /*
        System.out.println(isUnique("abcdefghijklmnop"));
        System.out.println(isUnique("abcb"));
        System.out.println(isUnique("aabb"));
        */


        //Q2 checkPermutation test
        /*
        System.out.println(checkPermutation("abcde", "edcba"));
        System.out.println(checkPermutation("abcde", "caebd"));
        System.out.println(checkPermutation("abcde", "caebf"));
        */


        //check how substring method works. YES! Not change og. just creates new string.
        /*
        String s = "abcd";
        System.out.println(s.substring(1));
        System.out.println(s);


        //Strings are immutable. so can't do...
        s.charAt(0) = s.charAt(1); //unlike array where can do arr[0] = arr[1]
        */

        //character version of " " is ' '
        /*
        String s = "a b";
        char [] sArray = s.toCharArray();
        System.out.println(sArray[1]);
        */

        //Q3
        /*
        System.out.println(URLify("Mr John Smith    ", 13));
        System.out.println(URLify("Lebron will win the championship against GSW            ", 44));
        int l = "Lebron will win the championship against GSW".length();
        System.out.println(l);
        */


        //Q4
        /*
        System.out.println(permutationOfPalindrome("tactcoa"));
        System.out.println(permutationOfPalindrome("mamacici"));
        System.out.println(permutationOfPalindrome("a"));
        System.out.println(permutationOfPalindrome("tactcoab"));
        */

        //Q5
        /*
        System.out.println(oneEditAway("pale", "bale")); //replace
        System.out.println(oneEditAway("pale", "bake"));

        System.out.println(oneEditAway("pales", "pale")); //insert or remove
        System.out.println(oneEditAway("psale", "pale"));
        System.out.println(oneEditAway("psble", "pale"));
        System.out.println(oneEditAway("pale", "ple"));

        System.out.println(oneEditAway("palexy", "pale"));
        */


        //Q6
        /*
        System.out.println(stringCompression("aaaaaa"));
        System.out.println(stringCompression("aaaaab"));
        System.out.println(stringCompression("ab"));
        System.out.println(stringCompression("aabbcc"));
        System.out.println(stringCompression("aabcccccaaa"));
        */


        //Q7 ~ 9 안함. Rotation이라서.

    }
}
