/*안품

I. 문제 
****START
A cryptarithm is a mathematical puzzle for which the goal is to find the correspondence between letters and digits, such that the given arithmetic equation consisting of letters holds true when the letters are converted to digits.

You have an array of strings crypt, the cryptarithm, and an an array containing the mapping of letters and digits, solution. The array crypt will contain three non-empty strings that follow the structure: [word1, word2, word3], which should be interpreted as the word1 + word2 = word3 cryptarithm.

If crypt, when it is decoded by replacing all of the letters in the cryptarithm with digits using the mapping in solution, becomes a valid arithmetic equation containing no numbers with leading zeroes, the answer is true. If it does not become a valid arithmetic solution, the answer is false.

Example

For crypt = ["SEND", "MORE", "MONEY"] and

solution = [['O', '0'],
            ['M', '1'],
            ['Y', '2'],
            ['E', '5'],
            ['N', '6'],
            ['D', '7'],
            ['R', '8'],
            ['S', '9']]
the output should be
isCryptSolution(crypt, solution) = true.

When you decrypt "SEND", "MORE", and "MONEY" using the mapping given in crypt, you get 9567 + 1085 = 10652 which is correct and a valid arithmetic equation.

For crypt = ["TEN", "TWO", "ONE"] and

solution = [['O', '1'],
            ['T', '0'],
            ['W', '9'],
            ['E', '5'],
            ['N', '4']]
the output should be
isCryptSolution(crypt, solution) = false.

Even though 054 + 091 = 145, 054 and 091 both contain leading zeroes, meaning that this is not a valid solution.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.string crypt

An array of three non-empty strings containing only uppercase English letters.

Guaranteed constraints:
crypt.length = 3,
1 ≤ crypt[i].length ≤ 14.

[input] array.array.char solution

An array consisting of pairs of characters that represent the correspondence between letters and numbers in the cryptarithm. The first character in the pair is an uppercase English letter, and the second one is a digit in the range from 0 to 9.

Guaranteed constraints:
solution[i].length = 2,
'A' ≤ solution[i][0] ≤ 'Z',
'0' ≤ solution[i][1] ≤ '9',
solution[i][0] ≠ solution[j][0], i ≠ j,
solution[i][1] ≠ solution[j][1], i ≠ j.

It is guaranteed that solution only contains entries for the letters present in crypt and that different letters have different values.

[output] boolean

Return true if the solution represents the correct solution to the cryptarithm crypt, otherwise return false
*****END 







II. 풀이 

1. 
import java.util.*; 




boolean isCryptSolution(String[] crypt, char[][] solution) {
	    HashMap<Character, Character> map = new HashMap<>();
	    for(int i=0; i< solution.length; i++){
	        map.put(solution[i][0], solution[i][1]);
	    }
	    List<String> list = new ArrayList<>(); 
	    for(String str: crypt){
	        if(map.get(str.charAt(0))=='0' && str.length()>1) return false;
	        StringBuilder s = new StringBuilder();
	        for(char c: str.toCharArray()){
	            s.append(map.get(c)+"");
	        }
	        list.add(s.toString());
	    } 
	    System.out.println(list.toString());
	    double num1= Double.parseDouble(list.get(0));
	    double num2= Double.parseDouble(list.get(1));
	    double num3= Double.parseDouble(list.get(2));
	    if(num1+num2==num3) return true;
	    return false;
	        
	}

2. 
boolean isCryptSolution(String[] crypt, char[][] solution) {
    for(char[] arr : solution){
        for(int i = 0; i < crypt.length; i++){
            crypt[i]=crypt[i].replace(arr[0],arr[1]);
        }
        System.out.println(Arrays.toString(crypt));
    }
    
    for(int i = 0; i < crypt.length; i++){
        if(!crypt[i].equals("0")&&crypt[i].startsWith("0"))
            return false;
    }
    
    return Long.parseLong(crypt[0])+Long.parseLong(crypt[1])==Long.parseLong(crypt[2]);
}

3. 
def isCryptSolution(crypt, solution):
    dic = {ord(c): d for c, d in solution}
    *v, = map(lambda x: x.translate(dic), crypt)
    return not any(x != "0" and x.startswith("0") for x in v) and \
        int(v[0]) + int(v[1]) == int(v[2])

4. 

def isCryptSolution(crypt, solution):
    table = str.maketrans(dict(solution))
    t = tuple(s.translate(table) for s in crypt)
    zeroes = any(s[0] == '0' for s in t if len(s) > 1)
    return not zeroes and int(t[0]) + int(t[1]) == int(t[2])


5.
def isCryptSolution(crypt, solution):
    crypt_s = crypt
    for i in range(0, 3):
        for s in solution:
            crypt_s[i] = crypt_s[i].replace(s[0], s[1])
        
        if crypt_s[i] != '0' and crypt_s[i][0] == '0':
            return False
        
    if int(crypt_s[0]) + int(crypt_s[1]) != int(crypt_s[2]):
        return False
    
    return True


*/
