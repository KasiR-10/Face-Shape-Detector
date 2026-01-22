#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the structure for a node in the doubly linked list
typedef struct link {
    char url[50];
    struct link *next;
    struct link *prev;
} Node;

// Function to create a new node
Node* create(char url[]) {
    Node *newnode = (Node*)malloc(sizeof(Node));
    strcpy(newnode->url, url);
    newnode->next = NULL;
    newnode->prev = NULL;
    return newnode;
}

// Function to move forward in the history
void forward(Node **head) {
    if ((*head)->next != NULL) {
        printf("Forwarded from %s to %s\n", (*head)->url, (*head)->next->url);
        *head = (*head)->next;
    } else {
        printf("Cannot go forward.\n");
    }
}

// Function to move backward in the history
void backward(Node **head) {
    if ((*head)->prev != NULL) {
        printf("Backwarded from %s to %s\n", (*head)->url, (*head)->prev->url);
        *head = (*head)->prev;
    } else {
        printf("Cannot go backward.\n");
    }
}

// Main function to drive the program
void main() {
    int ch;
    Node *head = NULL, *newnode;

    do {
        printf("\n1. Visit site\n2. Forward\n3. Backward\n4. Present site\n5. Exit\nEnter your choice: ");
        scanf("%d", &ch);

        switch (ch) {
            case 1: {
                char url[50];
                printf("Enter site: ");
                scanf("%s", url);
                newnode = create(url);
                if (head == NULL) {
                    head = newnode;
                } else {
                    head->next = newnode;
                    newnode->prev = head;
                    head = newnode;
                }
                break;
            }
            case 2:
                forward(&head);
                break;
            case 3:
                backward(&head);
                break;
            case 4:
                if (head != NULL) {
                    printf("Present site: %s\n", head->url);
                } else {
                    printf("No site visited yet.\n");
                }
                break;
            case 5:
                printf("Exiting...\n");
                break;
            default:
                printf("Invalid choice!\n");
        }
    } while (ch != 5);
}