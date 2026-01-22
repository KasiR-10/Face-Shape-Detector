#include <stdio.h>

void firstfit(int blocksize[],int m,int processSize[],int n){
    int alloc[n];
    int i,j;
    for( i = 0; i<n ; i++){
        alloc[i] = -1;
    }
    for ( i = 0; i<n ; i++){
        for ( j = 0 ; j<m ; j++){
            if (blocksize[j] >= processSize[i] ){
                alloc[i] = j;
                blocksize[j] -= processSize[i];
                break;
            }
        }
    }
    printf("\nProcess no\tProcessSize\tBlockNo\n");
    for ( i=0 ; i<n ; i++){
        printf("%d\t\t%d\t\t",i+1,processSize[i]);
        if ( alloc[i] != -1) printf("%d\n",alloc[i]+1);
        else printf("Not allocated\n");
    }

}

int main() {
    int blockSize[] = {100, 500, 200, 300, 600};
    int processSize[] = {212, 417, 112, 426};
    int m = sizeof(blockSize) / sizeof(blockSize[0]);  // number of blocks
    int n = sizeof(processSize) / sizeof(processSize[0]);  // number of processes

    firstfit(blockSize, m, processSize, n);

    return 0;
}