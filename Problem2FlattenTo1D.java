// Time Complexity : O(n^2), where n is the board size(n*n)
// Space Complexity : O(n^2), for the 1D flattened board and the queue used in BFS
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// The 2D board is first flattened into a 1D array following pattern (left-to-right and right-to-left alternation).
// BFS is used to simulate dice rolls from cell 1 to the final cell n*n, while accounting for snakes and ladders.
// At each level of BFS, we explore possible next positions (1 to 6 steps ahead).
// If a cell has a ladder or snake, we jump directly to the destination.
// Visited cells are marked as -2 to avoid revisiting.
// If we reach the last cell, the current BFS level is returned as the minimum number of moves.

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        //Initialise 1D array
        int[] arr = new int[n*n];

        //Flatten the 2D array into 1D array
        //Index for 1D array
        int i = 0;
        //Row & column index
        int r = n-1;
        int c = 0;
        //Flag to manage the traversal direction
        boolean flag = true;

        while (i < n*n){
            //Add values
            if(board[r][c] == -1){
                arr[i] = -1;
            }else{
                arr[i] = board[r][c] - 1;
            }
            i++;
            //Update the row and colum index
            if(flag){ //Left to Right
                c++;
                if(c == n){
                    r--;
                    c--;
                    flag = false;
                }
            }else { //Right to Left
                c--;
                if(c < 0){
                    r--;
                    c++;
                    flag = true;
                }
            }
        }

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

                    //If we reach our destination that is n*n-1; return the level
                    if(newIdx == n*n-1 || arr[newIdx] == n*n-1) return level+1;

                    //To prevent adding visited indices in the queue; mark the visited ones as -2
                    if(arr[newIdx] != -2) {
                        //If the value at index is -1; Add the index to the queue
                        if (arr[newIdx] == -1) {
                            q.add(newIdx);
                        } else { // Else add the value at the index
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}