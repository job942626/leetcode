class Solution {

    public int maximumGap(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        } 
        int N = nums.length;
        int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
        //todo
       if (min == max) {
			return 0;
		}
        boolean[] has = new boolean[N+1];
        int[] maxs = new int[N+1];
        int[] mins = new int[N+1];
        for(int i  : nums){
            int bucket = whichBucket1(i, N, max, min);
            //int bucket = whichBucket(i, N+1, max, min);
            if(has[bucket]){
                maxs[bucket] = Math.max(maxs[bucket], i);
                mins[bucket] = Math.min(mins[bucket], i);
            }else{
                has[bucket] = true;
                maxs[bucket] = i;
                mins[bucket] = i;
            }
        }
        int ans = 0;
        //最小一定有
        int last = maxs[0];
        //计算每两个之间的
        for(int i = 1; i < N+1; i++){
            if(has[i]){
                int cur = mins[i] - last;
                ans = Math.max(ans, cur);
                last = maxs[i];
            }
        }

        return ans;
    }
    public int whichBucket1(long i, long len ,long max, long min){

    int ans = (int) ((i - min) * len / (max - min));
    return ans;
    }
    //1 3 6 9
    //min1  max9
    //bucket 5
    //总共位置max - min
    //当前到了的位置  i - min
    //当前的占比  （i-min）/（max-min）
    //比例乘以总的桶数
    //0-99
    //1-9   1
    //9/99 = 1/11  总共10个桶 返回0
    //99/99 = 1
    public int whichBucket(int i, int bucketNum ,int max, int min){
        //[)
    return(int) ((i - min) * (bucketNum-1) / (max - min))  ;       
    }
}