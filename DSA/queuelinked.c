#include<stdio.h>
#include<stdlib.h>

struct node{
   
    int data;
    struct node* next;
};

struct node* front = NULL;
struct node* rear = NULL;
 
void enqueue(int value){

 struct node* newnode = (struct node*)malloc(sizeof(struct node));
 if (newnode == NULL)
 {
   printf("Memory allocation failed");
   return;
 }
 newnode->data = value;
 newnode ->next = NULL;
 
 if( rear == NULL)
 {
   front = rear = newnode;
 }
 else{
   
   rear->next = newnode;
   rear = newnode;
 }
 printf("Value enqueued");
}

void dequeue()
{
  if (front == NULL )
  {
    printf("Queue Underflow");
    return;
  }
  struct node* temp = front;
  printf("Element dequeued : %d",temp->data);
  front = front->next;
  if( front == NULL ) rear = NULL;
  free(temp);
}
void display()
{
  if( front == NULL )
  {
     printf("Queue Underflow");
     return;
  }
  struct node* temp = front;
  while( temp != NULL)
  {
     printf("%d <--",temp->data);
     temp = temp->next;
  }
  printf("\n");
}

void peek()
{

  if (front == NULL )
  {
    printf("Queue Underflow");
    return;
  }
  printf("Top element is %d",front->data);
}

int main()
{
  int ch,value;
  while(1)
  {
    printf("\n \nQueue Operations\n 1.ENQUEUE \n 2.DEQUEUE \n 3.PEEK \n 4.DISPLAY \n 5.EXIT \n Enter your choice : ");
    scanf("%d",&ch);
    
    switch(ch)
    {
      case 1:
          printf("Enter the value to be enqueued : ");
          scanf("%d",&value);
          enqueue(value);
          break;
     case 2:
          dequeue();
          break;
     case 3:
          peek();
          break;
     case 4:
          display();
          break;
     case 5:
          printf("Exiting menu Bye bye \n");
          exit(0);
          break;
     default:
          printf("Enter a valid choice ");
          break;
    }
  }
  return 0;
}