/*
내 잘못: Did not know string.lastindexof(character) method exist. If lastindexof a characcter and firstindexof character same then, not repeated. 
1. There's a indexOf and lastindexOF method. (if a character's first index and last index are same then by def. not repeat. )
    somestring.indexOf(character)

char firstNotRepeatingCharacter(String s) {
    char[] c=s.toCharArray(); // unnecessary cuz could just use s.charAt(i) instead of c[i]
for(int i=0;i<s.length();i++){
    if(s.indexOf(c[i])==s.lastIndexOf(c[i]))
        return c[i];
}
    return '_';
}

//same idea in python (rindex is lastIndexOf)
def firstNotRepeatingCharacter(s):
    for c in s:
        if s.index(c) == s.rindex(c):
            return c
    return '_'


*/



import java.util.*;

public class Q2_findingduplicates {

//CodeFights Q1 + Q2 Arrays
    public class findingduplicates {
        boolean seenTwice;
        int duplicateIndex;
        //go through hm할때 to tell which duplicate is smallest.
        
        public findingduplicates (int nonDup) {
            seenTwice = false;
            duplicateIndex = nonDup;
        }
    }


    char firstNotRepeatingCharacter(String s) {
        //fill hm
        HashMap<String, findingduplicates> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //처음봄 therefore add
            if (!hm.containsKey(s.substring(i,i+1))) {
                hm.put(s.substring(i,i+1), new findingduplicates(i));
            }
            //두번째봄 therefore change duplicate attribute
            else {
                hm.get(s.substring(i,i+1)).seenTwice = true;
            }
        }

        char rv = '_';
        int smallestIndex = 100000000;
        //go through hm to find not duplicate w/lowest index
        for (Map.Entry<String, findingduplicates> entry : hm.entrySet()) {
            String key = entry.getKey();
            findingduplicates value = entry.getValue();

            //not duplicate w/lowest index
            if (!value.seenTwice && value.duplicateIndex < smallestIndex) {
                smallestIndex = value.duplicateIndex;
                rv = key.charAt(0);
            }

        }
        return rv;
    }
    //end

    public static void main(String[] args) {
        Q2_findingduplicates c = new Q2_findingduplicates();
        String s1 = "abacabad";
        //c
        String s2 = "abacabaabacaba";
        //_
        System.out.println(c.firstNotRepeatingCharacter(s1));
        System.out.println(c.firstNotRepeatingCharacter(s2));
    }
}

