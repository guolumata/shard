package com.zhd.shard.lee.s0820;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-20
 */
public class Solution1 {
    public static void main(String[] args) {
        int i = 8;
        char c = (char) (i + '0');
        System.out.println(c);
        //        char[][] board = {
        //                {'E', 'E', 'E', 'E', 'E'},
        //                {'E', 'E', 'M', 'E', 'E'},
        //                {'E', 'E', 'E', 'E', 'E'},
        //                {'E', 'E', 'E', 'E', 'E'}};
        //        int[] click = {3, 0};
        //        new Solution1().updateBoard(board, click);
    }

    /**
     * https://leetcode-cn.com/problems/minesweeper/
     */
    public char[][] updateBoard1(char[][] board, int[] click) {
        //点击雷
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        // 'E' 代表一个未挖出的空方块
        // 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
        // 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻
        if (board[click[0]][click[1]] == 'E') {
            around(board, click[0], click[1]);
        }
        return board;
    }

    private void around(char[][] board, int r, int c) {
        board[r][c] = '0';
        int count = 0;
        Boolean b1 = isM(board, r - 1, c - 1);
        Boolean b2 = isM(board, r - 1, c);
        Boolean b3 = isM(board, r - 1, c + 1);
        Boolean b4 = isM(board, r, c - 1);
        Boolean b5 = isM(board, r, c + 1);
        Boolean b6 = isM(board, r + 1, c - 1);
        Boolean b7 = isM(board, r + 1, c);
        Boolean b8 = isM(board, r + 1, c + 1);
        count = b1 == null || !b1 ? count : count + 1;
        count = b2 == null || !b2 ? count : count + 1;
        count = b3 == null || !b3 ? count : count + 1;
        count = b4 == null || !b4 ? count : count + 1;
        count = b5 == null || !b5 ? count : count + 1;
        count = b6 == null || !b6 ? count : count + 1;
        count = b7 == null || !b7 ? count : count + 1;
        count = b8 == null || !b8 ? count : count + 1;
        if (count > 0) {
            board[r][c] = (char) (count + '0');
        } else {
            board[r][c] = 'B';
        }
    }

    /**
     * 左上
     */
    private Boolean isM(char[][] board, int r, int c) {
        if (r < 0) {
            return null;
        }
        if (r > board.length - 1) {
            return null;
        }
        if (c < 0) {
            return null;
        }
        if (c > board[0].length - 1) {
            return null;
        }
        if (board[r][c] == 'M') {
            return true;
        }
        if (board[r][c] == 'E') {
            around(board, r, c);
        }
        return false;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }


    int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    public void dfs(char[][] board, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 8; ++i) {
            int tx = x + dirX[i];
            int ty = y + dirY[i];
            if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                continue;
            }
            // 不用判断 M，因为如果有 M 的话游戏已经结束了
            if (board[tx][ty] == 'M') {
                ++cnt;
            }
        }
        if (cnt > 0) {
            // 规则 3
            board[x][y] = (char) (cnt + '0');
        } else {
            // 规则 2
            board[x][y] = 'B';
            for (int i = 0; i < 8; ++i) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E') {
                    continue;
                }
                dfs(board, tx, ty);
            }
        }
    }
}
