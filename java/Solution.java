class Solution {
    public static int trap(int[] height) {
        int max = height[0];
        
        int sum = 0;
        
        for(int x: height)
            if(x>max)
                max = x;
        
        int leftbig = 0;
        int rightbig = 0;
        for(int a = 1; a<height.length -1 ;a++)
        {
            leftbig = 0;
            rightbig = 0;
            System.out.println("a= " +a+ " a height = " + height[a]);
            for(int lefttest = a - 1; lefttest>=0;lefttest--){
                if(height[lefttest]> leftbig)
                    leftbig = height[lefttest];
            }
            for(int righttest = a; righttest<height.length ; righttest++){
                if(height[righttest]>rightbig)
                    rightbig = height[righttest];
            }
            System.out.println("Lb =" +leftbig+ "  RB= " + rightbig);
            if(Math.min(rightbig,leftbig)>height[a] )
            {
                sum += Math.min(rightbig,leftbig) -height[a];
                System.out.println("Sum increased " + (Math.min(rightbig,leftbig) -height[a]) );
            }
                
    
        
        
        
    }
    return sum;
}

    public static void main(String[] args) {

        int a = trap (new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(a);
    }
}