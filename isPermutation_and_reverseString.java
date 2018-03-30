/*
Approach: What does Permutation mean? 같은 글자 just different order. 
  Use this property aka. check if both strings have same # of same 글자. 
  Do via count array ( increments for 1 string decrements for another) 

LP1: getnumericvalue method - c to 2 a to 0 
Increment index of count using int value of character in str1 aka. str1.charAt(i)
Character.getNumericValue(str1.charAt(i)) 

count[Character.getNumericValue(str1.charAt(i))]++; 

LP2: if (letterCounts[Character.getNumericValue(str2_arr[i])] < 0) because if (letterCounts[Character.getNumericValue(str2_arr[i])] > 0) 는 mistakenly executes since 첫째 string땜에 index increment 했잖아. 

i.e. "ddddd" "ddddd" then index of count for d aka. 4 would be 5 then u decrement 1 so becomes 4 so does if statement returns false..

LP3: Strings are immutable. therefore use substring method in reverse recurse
*/

 public static boolean isPermutation(String str1, String str2) {
    // your code here 
    if (str1.length() != str2.length()) {
      return false; 
    }
    
    
    int[] letterCounts = new int[128]; 
    
    //incrementing for 1st string
    // Character.getNumericValue(c)
    char[] str1_arr = str1.toCharArray();
    for (char c: str1_arr) {
      letterCounts[Character.getNumericValue(c)]++;
    }
    
    //decrementing for 2nd string
    char[] str2_arr = str2.toCharArray();
    for (int i = 0; i < str2.length(); i++) {
      letterCounts[Character.getNumericValue(str2_arr[i])]--;
      if (letterCounts[Character.getNumericValue(str2_arr[i])] < 0) {
        return false;
      }
    }
    return true; 
  }



  public static String reverseIterative(String str) {
    // your code here 
    String reverse = "";
    for (int i = str.length() - 1; i >= 0;  i--) {
      reverse += str.charAt(i);
    
    }
    return reverse;
  }
  
  public static String reverseRecursive(String str) {
    // your code here
    if (str.length() == 1 || str.length() == 0) {return str;}
    
    else {
      return str.charAt(str.length() - 1) + reverseRecursive(str.substring(0, str.length() - 1));
    }
    
  }
  