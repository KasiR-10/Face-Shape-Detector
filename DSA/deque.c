#include<stdio.h>
#include<stdlib.h>

int front = -1,rear = -1;
#define size 100
char queue[size];
void insertfront(char ch){
    if (( front == 0 && rear == size - 1) || (front == rear +1 )){
        printf("Queue full");
        return;
    }
    if (front == -1 ) front = rear = 0;
    else if (front == 0 ) front = size -1;
    else front ++;
}
char  deleteAtRear(){

    if(front == -1){
      printf("Dequeue is Empty");
      return;
    }
    
    printf("Element dequeued %d",queue[front]);
    
    if( front == rear) front = rear = -1;
    
    else if ( rear == 0) rear = size -1;
    
    else{
    return queue[rear];
     rear--;
    }
}

int main(){
    char string[10];
    printf("Enter the string : ");
    scanf("%s" ,string);
    for (int i=0;i<10;i++){
        insertfront(string[i]);
    }
    char final;
    for (int i=0;i<10;i++){
        final += deleteAtRear();;
    }
}