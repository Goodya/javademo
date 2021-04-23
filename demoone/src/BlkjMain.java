
public class BlkjMain {
    public static void main(String[] args) {
        int[][] ints = new int[][]{{0,0,0,0},{1,1,1,1}};

        maxAreaOfIsland(ints);
    }


    public int[] coinChange (int coin) {
        int[] moneys = new int[]{1,5,10,20,50,100};
        int[] result = new int[6];
        for (int i=5 ; i>=0 ;){
            while (coin>=moneys[i]){
                coin = coin-moneys[i];
                result[i]++;
            }
            i--;
        }
        return result;
    }


    public boolean searchMatrix (int[][] matrix, int target) {
        // write code here
        if (matrix.length < 1 || matrix[0].length<1){
            return false;
        }
        int x = matrix[0].length;
        int l = 0;
        int r = matrix.length * matrix[0].length-1;
        while (l<=r){
            int mid = r + (l-r)/2;
            if (matrix[mid/x-1][mid%x] > target){
                r = mid-1;
            }else if (matrix[mid/x-1][mid%x] < target){
                l = mid+1;
            }else {
                return true;
            }
        }
        return false;
    }

    public static int maximumValue (int[] arr) {
        // write code here
        if (arr.length<1){
            return 0;
        }
        if (arr.length<2){
            return arr[1];
        }
        return getResult(arr,0,true,0,0);

    }

    public static int getResult (int[] arr,int i, Boolean boo,int res ,int maxres){
        maxres = Math.max(maxres,res);
        if (i < arr.length){
            if (!boo){
                getResult(arr,i+1,true,res,maxres);
            }
            getResult(arr,i+1,false,res+arr[i],maxres);
            getResult(arr,i+1,true,res,maxres);
        }
        return maxres;
    }


    /**
     * 求数组中连续为1的面积
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland (int[][] grid) {
        // write code here
        int w = grid[0].length;
        int h = grid.length;
        if (w == 0){
            return 0;
        }
        int result = 0;
//        if (h==0){
//            int j = 0;
//            int k = 0;
//            while (j<grid[0].length){
//                while (j<grid[0].length-1 && grid[0][j] == 0){
//                    j++;
//                }
//                k=j;
//                while (j<grid[0].length-1 && grid[0][j] == 1){
//                    j++;
//                }
//                result = Math.max(result,(j-k));
//            }
//            return result;
//        }
            int len = w*h;
        if (h == 0){
            len = w;
        }
        int[] gid = new int[len]; // 代表当前位置是否遍历过
        for (int i=0 ; i<len ; i++){
            if (grid[i%w][i/w] == 0 || gid[i] == 1){
                continue;
            }
            result = Math.max(result,searchGrid(grid,i%w,i/w,0,gid,w,h));
        }
        return result;
    }

    public static int searchGrid(int[][] grid,int x,int y, int maxArea,int[] gid,int w,int h){
        gid[x*w + y] = 1;
        while (gid[x*w+y] == 0 && x<w-1 && grid[x+1][y] == 1){
            searchGrid(grid,x+1,y,++maxArea,gid,w,h);
        }
        while (gid[x*w+y] == 0 && y<h-1 && grid[x][y+1] == 1){
            searchGrid(grid,x,y+1,++maxArea,gid,w,h);
        }
        while (gid[x*w+y] == 0 && x>0 && grid[x-1][y] == 1){
            searchGrid(grid,x-1,y,++maxArea,gid,w,h);
        }
        while (gid[x*w+y] == 0 && y>0 && grid[x][y-1] == 1){
            searchGrid(grid,x,y-1,++maxArea,gid,w,h);
        }
        return maxArea;
    }


}
