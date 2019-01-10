/*
 * 归并排序可视化
 */

import java.util.ArrayList;

public class MergeSortAnimation {
   public static void main(String[] args) {
      Canvas c = new Canvas();
      Formats f = new Formats();
      HistogramData d = new HistogramData();

      ArrayList<double[]> state = new ArrayList<>();
      double nums[] = generateRandomNums(100);
      state.add(nums.clone());
      MergeSort(nums, state);
      showNums(nums);
      d.state = state;
      Histogram h = new Histogram(c, f, d);
      h.animate();
   }

   public static double[] generateRandomNums(int n) {
      double[] nums = new double[n];
      for (int i = 0; i < n; i++) {
         nums[i] = Math.random()*100;
      }
      return nums;
   }

   public static void showNums(double nums[]) {
      for (double item:nums) {
         System.out.println(item);
      }
   }

   private static void MergeSort(double[] a, ArrayList<double[]> state) {
      Sort(a, 0, a.length - 1, state);
   }

   private static void Sort(double[] a, int left, int right, ArrayList<double[]> state) {
      // 归并排序的关键代码
      if(left>=right)
         return;

      int mid = (left + right) / 2;
      Sort(a, left, mid, state);
      Sort(a, mid + 1, right, state);
      merge(a, left, mid, right, state);
   }


   private static void merge(double[] a, int left, int mid, int right, ArrayList<double[]> state) {

      double[] tmp = new double[a.length];
      int r1 = mid + 1;
      int tIndex = left;
      int cIndex=left;

      while(left <=mid && r1 <= right) {
         if (a[left] <= a[r1])
            tmp[tIndex++] = a[left++];
         else
            tmp[tIndex++] = a[r1++];
      }

      while (left <=mid) {
         tmp[tIndex++] = a[left++];
      }
      state.add(a.clone()); // 记录左半部分归并状态

      while ( r1 <= right ) {
         tmp[tIndex++] = a[r1++];
      }

      while(cIndex<=right){
         a[cIndex]=tmp[cIndex];
         cIndex++;
      }
      state.add(a.clone()); // 记录右半部分归并状态
   }

}
