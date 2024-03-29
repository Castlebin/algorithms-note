## 9.2 希尔排序
希尔排序是对插入排序的改造，利用了插入排序的简单，同时，改进了插入排序只能进行两个相邻元素的比较和交换导致效率较低的问题

希尔排序进行的是多趟递减间隔的插入排序
最后一趟，就是一间隔的插入排序，也就是原始的插入排序

## 9.3 堆排序
理解堆排序，首先要提到选择排序
选择排序的每一轮，都是从剩下的未排序的元素中选取一个最小的元素，放到已排序到的元素的尾部

小顶堆 中 的堆顶元素是堆中的最小元素，因此，可以借助小顶堆来完成选择的这个步骤，从而改进算法效率

同样的，也可以利用大顶堆，堆顶的元素是最大元素，每次从堆顶选择元素，倒序放入

第9讲讨论题：堆排序最适合解决什么样的问题？
    
    由于堆排序每一步都是前面已经处理过的元素一定是不会发生位置变化的，
    所以适合解决那种在100万个数中找最小的10个数之类的问题
    
唯一的问题，在于，堆排序**可能**需要额外空间（借助原数组来做堆可以避免申请额外空间，算法稍复杂，但可以实现）

堆排序 借助原数组来做堆可以避免申请额外空间  陈越姥姥这里讲的就是这种算法，需要理解（结合 5.1节 堆）

## 9.4 归并排序
归并排序需要额外的O(N)空间，所以一般不用于内排序，适合超大数据量的外排序
