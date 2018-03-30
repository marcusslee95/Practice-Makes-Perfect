//Biggest product of adjacent elements 
	//Have Variable (product) that keeps track of largest product so far. 

int adjacentElementsProduct(int[] inputArray) {
    int product = inputArray[0] * inputArray[1]; 
    for (int i = 0; i < inputArray.length - 2; i++) { //LP: avoid indexoutofarray 
        product = Math.max(product, inputArray[i+1] * inputArray[i+2]);
    }
    return product;
}
