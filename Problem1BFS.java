// Time Complexity : O(m * n) Each cell is visited at most once in the worst case, such as when all cells are empty and need to be revealed.
// Space Complexity : O(m * n) In the worst case, the queue can hold all the cells in the board, which contributes to the space complexity.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach:
// If the user clicks on a mine ('M'), we immediately return the board after setting that cell to 'X'.
// If the clicked cell is not a mine, we perform a breadth-first search (BFS) starting from the clicked cell.
// For each cell processed, we count how many mines are in its 8 neighboring cells using a helper method `getCount()`.
// If there are no neighboring mines, we mark it as 'B' and add all its valid, unrevealed neighbors to the queue for further processing.
// If there are mines nearby, we mark the cell with the number of adjacent mines, represented as a character from '1' to '8'.
// This process continues until the queue is empty and all necessary cells have been updated.

class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];
        //If the click pos has Mine just update it to X and return the board
        if(board[r][c] == 'M'){
            board[r][c] = 'X';
            return board;
        }

        //Direction array to explore neighbours
        dirs = new int[][]{{-1,-1},{-1,0},{-1,1},
                {0,-1},{0,1},
                {1,-1},{1,0},{1,1}};

        m = board.length;
        n = board[0].length;

        //Creating queue and adding the first element
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c});
        board[r][c] = 'B';

        //Loop till queue is empty
        while (!q.isEmpty()){
            //POP the element
            int[] curr = q.poll();

            //Get count of the neighbouring mines
            int count = getCount(board,curr[0],curr[1]);
            //If count is 0; i.e no neighbouring mines; add the neighbours to the queue and mark them as 'B'
            if(count == 0){
                //Loop through the neighbours
                for (int[] dir : dirs){
                    //Get row and column index
                    r = dir[0] + curr[0];
                    c = dir[1] + curr[1];

                    //if the neighbour is not out of bounds and has value 'E'; Add to queue
                    if(r>=0 && r<m && c>=0 && c<n && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }else //Else just update it's mine count
            {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }

    private int getCount(char[][] board, int r1, int c1) {
        int count =0;
        for (int[] dir : dirs){
            //Get row and column index
            int r = dir[0] + r1;
            int c = dir[1] + c1;

            //if the neighbour is not out of bounds and has value 'M'; increase count
            if(r>=0 && r<m && c>=0 && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}