#include<stdio.h>
#include<stdlib.h>

void swap(int *a,int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

int partition(int arr[],int low,int high){
    int pivot= arr[high];
    int i = low-1;
    for (int j = low;j<high;j++)
    {
        if(arr[j]<pivot){
            i++;
            swap(&arr[i],&arr[j]);
        }
       
    }
     swap(&arr[i+1],&arr[high]);
        return (i+1);
}
void quicksort(int arr[],int low,int high){
    if (low<high){
        int par=partition(arr,low,high);
        quicksort(arr,low,par-1);
        quicksort(arr,par+1,high);
    }
}
int main(){
    int arr[5]={54,24,12,63,2};
    quicksort(arr,0,5);
    for(int i=0;i<5;i++){
        printf(" %d",arr[i]);
    }
    return 0;
}