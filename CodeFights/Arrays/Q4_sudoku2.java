/*안 품. 

1. 문제 
Sudoku is a number-placement puzzle. The objective is to fill a 9 × 9 grid with numbers in such a way that each column, each row, and each of the nine 3 × 3 sub-grids that compose the grid all contain all of the numbers from 1 to 9 one time.

Implement an algorithm that will check whether the given grid of numbers represents a valid Sudoku puzzle according to the layout rules described above. Note that the puzzle represented by grid does not have to be solvable.

Example

For

grid = [['.', '.', '.', '1', '4', '.', '.', '2', '.'],
        ['.', '.', '6', '.', '.', '.', '.', '.', '.'],
        ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
        ['.', '.', '1', '.', '.', '.', '.', '.', '.'],
        ['.', '6', '7', '.', '.', '.', '.', '.', '9'],
        ['.', '.', '.', '.', '.', '.', '8', '1', '.'],
        ['.', '3', '.', '.', '.', '.', '.', '.', '6'],
        ['.', '.', '.', '.', '.', '7', '.', '.', '.'],
        ['.', '.', '.', '5', '.', '.', '.', '7', '.']]
the output should be
sudoku2(grid) = true;

For

grid = [['.', '.', '.', '.', '2', '.', '.', '9', '.'],
        ['.', '.', '.', '.', '6', '.', '.', '.', '.'],
        ['7', '1', '.', '.', '7', '5', '.', '.', '.'],
        ['.', '7', '.', '.', '.', '.', '.', '.', '.'],
        ['.', '.', '.', '.', '8', '3', '.', '.', '.'],
        ['.', '.', '8', '.', '.', '7', '.', '6', '.'],
        ['.', '.', '.', '.', '.', '2', '.', '.', '.'],
        ['.', '1', '.', '2', '.', '.', '.', '.', '.'],
        ['.', '2', '.', '.', '3', '.', '.', '.', '.']]
the output should be
sudoku2(grid) = false.

The given grid is not correct because there are two 1s in the second column. Each column, each row, and each 3 × 3 subgrid can only contain the numbers 1 through 9 one time.

Input/Output

[execution time limit] 4 seconds (py3)

[input] array.array.char grid

A 9 × 9 array of characters, in which each character is either a digit from '1' to '9' or a period '.'.

[output] boolean

Return true if grid represents a valid Sudoku puzzle, otherwise return false.





2. 풀이 

풀이1
boolean sudoku2(char[][] grid) {
    for (int i = 0; i < 9; i++) {

        char[] row = new char[9];
        char[] square = new char[9];
        char[] column = grid[i].clone();

        for (int j = 0; j < 9; j ++) {
            row[j] = grid[j][i];
            square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
        }
        if (!(validate(column) && validate(row) && validate(square)))
            return false;
    }
    return true;
}

private boolean validate(char[] check) {
    String i = "";
    Arrays.sort(check);
    for (char number : check) {
        if(i.indexOf(number)!=-1 && number!='.')
            return false;
        if(number!='.')
            i+=number;
    }
    return true;
}

2. 풀이2
    boolean sudoku2(char[][] grid) {
        for (int i=0; i<9; i++) {
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if(grid[i][j] != '.') {
                    if (!row.add(grid[i][j])) {
                        return false;
                    }
                }
            }
            Set<Character> column = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if(grid[j][i] != '.') {
                    if (!column.add(grid[j][i])) {
                        return false;
                    }
                }
            }
            Set<Character> square = new HashSet<>();
            int firstRow = (i / 3) * 3;
            int firstColumn = (i % 3) * 3;
            for (int j = firstRow; j < firstRow + 3; j++) {
                for (int k = firstColumn; k < firstColumn + 3; k++) {
                    if(grid[j][k] != '.') {
                        if (!square.add(grid[j][k])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

3. 풀이3
def line_is_valid(row):
    
    tmp_dict = {}    
    valid = True
    
    if len(row) > 0:
        #print(row)
        for r in row:
            if r in tmp_dict:
                tmp_dict[r] += 1
            else:
                tmp_dict[r] = 1
        
        if tmp_dict[max(tmp_dict, key=tmp_dict.get)] > 1:
            valid = False
    
    return valid
                

def grid_is_valid(grid):
    
    valid = True
    
    for i in range(len(grid)):       
        row = [int(x) for x in grid[i] if x != '.']
        #print(row)
        valid = line_is_valid(row)
        if not valid:
            break
                
    return valid

def sudoku2(grid):
    
    result = False
    
    # check rows:
    rows_valid = grid_is_valid(grid)
        
    # check columns:
    col_valid = grid_is_valid(list(zip(*grid)))
          
    # check 3x3 grids:
    grid3x3_valid = True
    for i in range(0,len(grid), 3):
        
        if not grid3x3_valid:
                break
                
        sub_mat = grid[i:i+3]
        
        for j in range(0,len(grid[0]),3):
            
            tmp_list = [x[j:j+3] for x in sub_mat]
            #print(tmp_list)
            tmp_list = [tmp_list[k][l] for k in range(len(tmp_list)) for l in range(len(tmp_list[k]))]
            
            row = [int(x) for x in tmp_list if x != '.']
            grid3x3_valid = line_is_valid(row)
            #print(grid3x3_valid)
            
            if not grid3x3_valid:
                break
        
            
    if rows_valid and col_valid and grid3x3_valid:
        result = True
        
    return result

*/ 




