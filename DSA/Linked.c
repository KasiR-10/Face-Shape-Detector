#include<stdio.h>
#include<stdlib.h>
struct node{
    int data;
    struct node* next;
};
struct node* head = NULL;
void insertF(int val){
    struct node* new = (struct node*) malloc(sizeof(struct node));
    new->data = val;
    new->next = NULL;
   if (head == NULL ) head = new;
   else{
      new->next = head;
      head = new;
   }
}

void deleteF(){
    if (head == NULL ){
        printf("Empty");
        return;
    }
    else{
        struct node* temp = head;
        head = head->next;
        free(temp);
    }
}

void insertR(int val){
    struct node* new = (struct node*) malloc(sizeof(struct node));
    new->data = val;
    new->next = NULL;
    if (head == NULL ) head = new;
    else {
        struct node* temp = head;
        while (temp->next != NULL ) temp = temp->next;
        temp->next = new;
    }

}

void deleteR(){
    if (head == NULL ){
        printf("Empty");
        return;
    }
    if (head->next == NULL ){
        struct node* temp = head;
        head = head->next;
        free(temp);
    }
    else{
        struct node* prev = head;
        struct node* cur = head->next;
        while (cur->next != NULL ){
           prev = cur;
           cur = cur->next;
        }
        prev->next = NULL;
        free(cur);
    }
}

void insertA(int val,int key){
    struct node* new = (struct node*) malloc(sizeof(struct node));
    new->data = val;
    if (head == NULL ){
        printf("Empty");
        return;
    }
    struct node* temp = head;
    while (temp->data != key && temp ->next != NULL ) temp = temp->next;
    if (temp->data != key )  printf("key not found");
    else{
        new->next = temp->next;
        temp->next = new;
    }
}
void deleteA(int key){
    if (head == NULL ){
        printf("Empty");
        return;
    }
    if (head->data == key ){
        struct node* temp = head;
        head = head->next;
        free(temp);

    }
    else{
        struct node* prev= head;
        struct node*cur = head->next;
        while (cur->data != key && cur->next != NULL ){
            prev = cur;
            cur= cur->next;
        }
        if (cur->data != key ) printf("Key not found");
        else {
            prev->next = cur->next;
            free(cur);
        }
    }
}
void display(){
    if (head == NULL ){
        return;
    }
    else {
        struct node* temp = head;
        while (temp!= NULL ){
            printf(" %d",temp->data);
            temp = temp->next;
        }
    }
}
int main() {
    int choice, val, key;

    while (1) {
        printf("\n\n--- Singly Linked List Menu ---\n");
        printf("1. Insert at Front\n");
        printf("2. Insert at Rear\n");
        printf("3. Delete from Front\n");
        printf("4. Delete from Rear\n");
        printf("5. Insert After Key\n");
        printf("6. Delete by Key\n");
        printf("7. Display\n");
        printf("8. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter value: ");
                scanf("%d", &val);
                insertF(val);
                break;
            case 2:
                printf("Enter value: ");
                scanf("%d", &val);
                insertR(val);
                break;
            case 3:
                deleteF();
                break;
            case 4:
                deleteR();
                break;
            case 5:
                printf("Enter key after which to insert: ");
                scanf("%d", &key);
                printf("Enter value: ");
                scanf("%d", &val);
                insertA(val, key);
                break;
            case 6:
                printf("Enter key to delete: ");
                scanf("%d", &key);
                deleteA(key);
                break;
            case 7:
                display();
                break;
            case 8:
                exit(0);
            default:
                printf("Invalid choice!\n");
        }
    }

    return 0;
}
