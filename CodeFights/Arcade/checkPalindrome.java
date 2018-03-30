//Compare first and last


boolean checkPalindrome(String inputString) {
    char[] c = inputString.toCharArray(); 
    int last = inputString.length() - 1;
    for (int i = 0; i < inputString.length() / 2; i++) {
        if (c[i] != c[last]) {return false;}
        last--;
    }
    //LP1: NOTE if odd # characters then doesn't matter what middle one is. If all other pairs are equal then... it's palindrome.
    return true; 
}

boolean checkPalindrome(String inputString) {
    for(int i = 0; i < inputString.length()/2; i++){
        if(inputString.charAt(i) != inputString.charAt(inputString.length()-i-1)) //LP2: NOTE i커지니까 just use i instead of last variable. 
            return false;
    }
    return true;
}

