// Time Complexity : O(m * n) In the worst case, every cell may be visited once. Although the recursion will stop early for non-'E' cells, in the worst scenario (like an empty board), all cells may be explored.
// Space Complexity : O(m * n) In the worst case, the recursion stack could go as deep as the total number of cells in the board (m * n).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach:
// If the clicked cell is a mine ('M'), we mark it as 'X' and return the board immediately.
// If the clicked cell is empty ('E'), we perform a Depth-First Search (DFS) from that cell.
// At each step of DFS, we check for the number of adjacent mines using `getCount()`.
// If no adjacent mines are found (i.e., count is 0), we mark the cell as 'B' and continue DFS in all 8 directions.
// If adjacent mines are found, we update the cell with the count (as a character from '1' to '8') and do not recurse further.
// This process continues until all necessary cells are revealed according to Minesweeper rules.

class Solution {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];
        //If the click pos has Mine just update it to X and return the board
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        //Direction array to explore neighbours
        dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}};

        m = board.length;
        n = board[0].length;

        //Call DFS
        dfs(board, r, c);

        return board;
    }

    private void dfs(char[][] board, int r, int c) {

        //if the neighbour is not out of bounds and does not have value 'E'; return;
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'E') return;

        board[r][c] = 'B';

        //Get count of the neighbouring mines
        int count = getCount(board, r, c);
        //If count is 0; i.e no neighbouring mines; recurse on the neighbours
        if (count == 0) {
            //Loop through the neighbours
            for (int[] dir : dirs) {
                //Get row and column index
                int rn = dir[0] + r;
                int cn = dir[1] + c;
                //Recurse
                dfs(board, rn, cn);
            }
        } else //Else just update it's mine count
        {
            board[r][c] = (char) (count + '0');
        }
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