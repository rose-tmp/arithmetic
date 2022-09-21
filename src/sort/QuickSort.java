package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 22:43
 * @Description:快速排序：选择一个基准，通过一次遍历，确定基准的最终位置， 其中基准左边的数都小于基准，右边的数都大于基准 然后再递归基准左边的元素和右边的元素
 * 相关算法：左神GetSmallSum和FlagOfHolland
 * 快排做不到稳定 因为partition过程做不到稳定
 */
public class QuickSort {
    /**
     * @param low  当前数组需要进行快速排序的区域的第一个位置(包含下标low)
     * @param high 当前数组需要进行快速排序的区域的最后一个位置(包含下标high)
     */
    public void quickSort2(int[] nums, int low, int high) {
        if (nums.length < 2) {
            return;
        }
        int[] targetPos = partation2(nums, low, high);
        quickSort2(nums, low, targetPos[0]);
        quickSort2(nums, targetPos[1], high);
    }

    /**
     * 确定nums[low,high]上中间位置位置的那个元素的最终位置
     *
     * @return arr[0]:第一个该元素的位置下标 arr[1]:最后一个该元素的位置下标
     * 因为这个元素可能出现重复，所以就使用arr对其左右边界下标进行记录
     */
    public int[] partation2(int[] nums, int low, int high) {
        int target = nums[low + (high - low) / 2];
        //初始化 因为一开始还没有进行排序所以左右边界(包含当前位置)都在当前边界的基础上-1
        int left = low - 1;//nums[0,left]都是比target小的(包含left)
        int right = high + 1;//nums[right,nums.length-1]都是比target大的(包含right)
        int cur = low;
        /**
         * 当前指针撞上有边界的时候停止
         * 此时就形成了[0...left]中都是<target的元素
         * [left+1,right-1]中都是==target的元素
         * [right...]都是>target的元素的局面
         * */
        while (cur < right) {
            if (nums[cur] == target) {
                cur++;
            }
            /**
             * 1. 左边界的下一个位置和当前位置交换元素
             * 2. 左边界向右扩
             * 3. cur++
             * */
            if (nums[cur] < target) {
                int temp = nums[left + 1];
                nums[cur] = temp;
                nums[left + 1] = nums[cur];
                left++;
            }
            /**
             * 1. 右边界的上一个位置和当前位置交换元素
             * 2. 右边界向左扩
             * 3. cur不+1,因为交换位置后这个位置上的新元素还没有和target进行过比较
             * */
            if (nums[cur] > target) {
                int temp = nums[right - 1];
                nums[right - 1] = nums[cur];
                nums[cur] = temp;
                right--;
            }
        }
        return new int[]{left, right};
    }


    public void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length < 2)
            return;
        if (low < high) {
            int[] pivotPos = partition(arr, low, high);//得到基准的位置
            quickSort(arr, low, pivotPos[0]);
            quickSort(arr, pivotPos[1], high);
        }
    }

    /**
     * @Author: ZwZ
     * @Description: 此方法是在经典快速排序上进行的优化，经典快速排序每次只是确定一个元素的位置，然后再对左右两块子数组的元素进行快排
     * 但是，如果第一次的基准在数组中存在重复多次的值，那么就可以一次遍历之后，确定多个元素的位置
     * 灵感来源：左神FlagOfHolland
     * @Param: [arr, low, high]
     * @return: int[]
     * @Date: 2020/3/23-15:48
     */
    private int[] partition(int[] arr, int low, int high) {
        int left = low - 1;//下标left及其左边的元素，都是小于基准的
        int right = high + 1;//下标right及其右边的元素都是大于基准的
        Swap.swap(arr, low + (int) (Math.random() * (high - low + 1)), low); //使得快排变成随机快排  即pivot的下标是随机的 使得快排的效率不依赖于原始数据
        int pivot = arr[low];//基准
        int index = low;
        while (index < right) {
            if (arr[index] > pivot) {
                Swap.swap(arr, --right, index);
            } else if (arr[index] < pivot)
                Swap.swap(arr, ++left, index++);
            else
                index++;
        }
        /*while (low < high){
            while(arr[high] > pivot)
                --high;
            arr[low] = arr[high];
            while (arr[low] < pivot)
                ++low;
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;*/
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = {25, 84, 21, 25, 15, 27, 68, 35, 20};
        sort.quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
