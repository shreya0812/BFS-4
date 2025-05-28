// Time Complexity : O(n^2), where n is the board size(n*n)
// Space Complexity : O(n^2), for the BFS queue and in-place marking on the board
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Instead of flattening the board, we use a helper function `getPos()` to convert a 1D index to a 2D position accounting for the given zigzag pattern.
// BFS is used to simulate dice rolls from cell 1 to the final cell n*n, while accounting for snakes and ladders.
// At each level of BFS, we explore possible next positions (1 to 6 steps ahead).
// If a cell has a ladder or snake, we jump directly to the destination.
// Visited cells are marked as -2 to avoid revisiting.
// If we reach the last cell, the current BFS level is returned as the minimum number of moves.
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        //Queue
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int level = 0;

        //Traverse through the arr
        while (!q.isEmpty()){

            //Take snapshot of the size
            int size = q.size();
            //Loop till the size
            for (int j = 0; j < size; j++) {
                //Pop current index
                int currIdx = q.poll();

                //Roll the dice 1->6, add the indices into the queue
                for (int k = 1; k <= 6; k++) {
                    int newIdx = currIdx + k;

                    //Get the position of r and c
                    int[]  temp =  getPos(newIdx, n);
                    int r = temp[0];
                    int c = temp[1];

                    //If we reach our destination that is n*n-1; return the level
                    if(newIdx == n*n-1 || board[r][c] == n*n) return level+1;

                    //To prevent adding visited indices in the queue; mark the visited ones as -2
                    if(board[r][c] != -2) {
                        //If the value at index is -1; Add the index to the queue
                        if (board[r][c] == -1) {
                            q.add(newIdx);
                        } else { // Else add the value at the index
                            q.add(board[r][c]-1);
                        }
                        board[r][c] = -2;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private int[] getPos(int newIdx, int n) {
        int r = newIdx / n;
        int c = newIdx % n;

        if(r % 2 == 0){
            return new int[]{n-r-1, c};
        }else{
            return new int[]{n-r-1, n-c-1};
        }
    }
}