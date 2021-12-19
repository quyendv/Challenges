// https://www.geeksforgeeks.org/find-element-appears-array-every-element-appears-twice/
// tiêu biểu nhất là dùng XOR: a ^ a = 0, a ^ 0 = a
// Nhưng chỉ áp dụng nếu các phần tử duplicate kia chỉ xuất hiện 2 lần (hoặc chẵn lần)

public class SingleElementInArray {
    int findSingle(int[] arr, int ar_size)
    {
        // Do XOR of all elements and return
        int res = arr[0];
        for (int i = 1; i < ar_size; i++)
            res = res ^ arr[i];
        return res;
    }
}
