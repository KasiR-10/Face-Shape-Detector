\documentclass[a4paper,12pt]{extarticle} % extarticle allows bigger base font sizes

% --- MARGINS ---
\usepackage[margin=1in]{geometry}

% --- HEADER/FOOTER ---
\usepackage{fancyhdr}
\pagestyle{fancy}
\fancyhf{} % clear default header/footer

% --- Custom date ---
\newcommand{\mydate}{14-07-2025} % set your custom date here
\fancyhead[R]{\textnormal{\mydate}} % top-right date
\renewcommand{\headrulewidth}{0pt} % remove header line

% --- TIMES-LIKE FONT ---
\usepackage{mathptmx} % Times-like font

% --- For code/output ---
\usepackage{listings}
\lstset{
    basicstyle=\rmfamily\fontsize{12}{14}\selectfont, % 12pt code
    numbers=left,
    numberstyle=\tiny,
    stepnumber=1,
    frame=none,
    breaklines=true,
    tabsize=4,
    showstringspaces=false
}

% --- TikZ for page border ---
\usepackage{tikz}
\newcommand{\drawborder}{%
    \begin{tikzpicture}[remember picture, overlay]
        \draw[line width=1pt]
            ([xshift=1cm,yshift=-1cm]current page.north west)
            rectangle
            ([xshift=-1cm,yshift=1cm]current page.south east);
    \end{tikzpicture}%
}

\begin{document}

% ---------------- PAGE 1: PROGRAM + OUTPUT (was 2nd) ----------------
\drawborder

\section*{{\fontsize{14}{18}\selectfont \textbf{PROGRAM :}}}
\begin{verbatim}
#include<stdio.h>
void main()
{
   int arr[50],n,found=0;
   printf("Enter the length of the array");
   scanf("%d",&n);
   int i;
   printf("\n Enter array elements : ");
   for(i=0;i<n;i++)
   {
     scanf("%d",&arr[i]);
   }
   int k;
   printf("Enter the element to be searched");
   scanf("%d",&k);
   for(i=0;i<n;i++)
   {
     if(k==arr[i])
     {
     printf("Element found at %d\n",i+1);
     found=1;
     }
    }
    if(found==0)
    printf("Element not found");
    
}
\end{verbatim}

\vspace{0.4cm}
\section*{{\fontsize{14}{20}\selectfont \textbf{OUTPUT :}}}
\begin{verbatim}
Enter the length of the array : 5

Enter array elements : 23
12
6
5
3
Enter the element to be searched : 23
Element found at 1
\end{verbatim}

\newpage

% ---------------- PAGE 2: ALGORITHM + RESULT (was 1st) ----------------
\drawborder

\begin{center}
    {\fontsize{16}{20}\selectfont \textbf{PROGRAM - 1}} \\
    \vspace{0.5cm}
    {\fontsize{16}{20}\selectfont \textbf{LINEAR SEARCH }}
\end{center}

\vspace{0.5cm}
\noindent
{\fontsize{14}{18}\selectfont {\textbf{AIM}  :  {\fontsize{14}{18}\selectfont  Write a program to implement linear search in C}}} \\
\vspace{0.15cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{ALGORITHM:}}}
\vspace{0.1cm}
\fontsize{12}{16}\begin{verbatim}
1.Start
2.Input the size of the array n
3.Input the array elements arr[0] to arr[n-1]
4.Input the element to be searched k
5.Initialize a variable found = 0
6.For i = 0 to n-1 do:
    1.If arr[i] == k then:
    2.Print "Element found at position i+1"
    3.Set found = 1
7.After the loop, if found == 0 then:
8.Print "Element not found"
9.End
\end{verbatim}

\vspace{5cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{RESULT :}}}
\begin{verbatim}
The program was succesfully executed and output was obtained.
\end{verbatim}

\newpage
\drawborder

\section*{{\fontsize{14}{18}\selectfont \textbf{PROGRAM:}}}
\begin{verbatim}
#include <stdio.h>
int main() {
    int i, j, key, n, arr[100], mid, k, found = 0, low, high;
    printf("Enter the length of the array: ");
    scanf("%d", &n);
    printf("Enter array elements:\n");
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
    printf("Enter element to be searched: ");
    scanf("%d", &k);
    low = 0;
    high = n - 1;
    while (low <= high) {
        mid = (low + high) / 2;

        if (arr[mid] == k) {
            printf("Element found at index %d\n", mid);
            found = 1;
            break;
        } else if (k < arr[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    if (!found) {
        printf("Element not found in the array.\n");
    } return 0; }
    

\end{verbatim}
\drawborder

\vspace{-1cm}
\begin{center}
    {\fontsize{16}{20}\selectfont \textbf{PROGRAM - 2}} \\
    \vspace{0.5cm}
    {\fontsize{16}{20}\selectfont \textbf{BINARY SEARCH }}
\end{center}

\vspace{0.5cm}
\noindent
{\fontsize{14}{18}\selectfont {\textbf{AIM}  :  {\fontsize{14}{18}\selectfont  Write a program to implement binary search in C}}} \\
\vspace{0.15cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{ALGORITHM:}}}
\vspace{0.1cm}
\fontsize{12}{16}\begin{verbatim}
1.Start.
2.Input array size n and elements arr[0…n-1].
3.Sort array using Insertion Sort:
   1.For i = 1 to n-1:
   2.key = arr[i], j = i-1
   3.While j >= 0 and arr[j] > key: arr[j+1] = arr[j], j--
   4.arr[j+1] = key
4.Input element k to search.
5.Binary Search:
   1.low = 0, high = n-1
   2.While low <= high:
       1.mid = (low + high)/2
       2.If arr[mid] == k: print index, found = 1, break
       3.Else if k < arr[mid]: high = mid-1
       4.Else: low = mid+1
6.If not found, print "Element not found".
7.End.
\end{verbatim}
\newpage
\drawborder
\section*{{\fontsize{14}{20}\selectfont \textbf{OUTPUT:}}}
\begin{verbatim}
Enter the length of the array: 5
Enter array elements:
12
32
12
5
23
Enter element to be searched: 23
Element found at index 3

\end{verbatim}
\newpage
\drawborder

\section*{{\fontsize{14}{20}\selectfont \textbf{RESULT:}}}
\begin{verbatim}
The program was succesfully executed and output was obtained.
\end{verbatim}

\newpage
\drawborder

\section*{{\fontsize{14}{18}\selectfont \textbf{PROGRAM:}}}
\begin{verbatim}
#include<stdio.h>
void main(){
  int arr[100],n,i,j,temp;
  printf("Enter the length of the array : ");
  scanf("%d",&n);
  printf("Enter array elements : ");
  for(i=0;i<n;i++)
  {
    scanf("%d",&arr[i]);
  }
  for(i=0;i<n-1;i++)
  {
    for(j=0;j<n-i-1;j++)
    {
      if(arr[j]>arr[j+1])
      {
        temp=arr[j];
        arr[j]=arr[j+1];
        arr[j+1]=temp;
      }
    }
  }
  printf("Array after sorting!!\n");
  for(i=0;i<n;i++)
  {
    printf(" %d",arr[i]);
  }
}
\end{verbatim}
\section*{{\fontsize{14}{20}\selectfont \textbf{OUTPUT:}}}
\begin{verbatim}
Enter the length of the array : 5
Enter array elements : 21
56
53
23
9
Array after sorting!!
 9 21 23 53 56

\end{verbatim}

\drawborder

\vspace{-1cm}
\begin{center}
    {\fontsize{16}{20}\selectfont \textbf{PROGRAM - 3}} \\
    \vspace{0.5cm}
    {\fontsize{16}{20}\selectfont \textbf{BUBBLE SORT }}
\end{center}

\vspace{0.5cm}
\noindent
{\fontsize{14}{18}\selectfont {\textbf{AIM}  :  {\fontsize{14}{18}\selectfont  Write a program to implement bubble sort in C}}} \\
\vspace{0.15cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{ALGORITHM:}}}
\vspace{0.1cm}
\fontsize{12}{16}\begin{verbatim}
1.Start.
2.Input the size of the array n.
3.Input array elements arr[0…n-1].
4.Sort array using Bubble Sort:
    1.For i = 0 to n-2:
    2.For j = 0 to n-i-2:
    3.If arr[j] > arr[j+1], swap arr[j] and arr[j+1]
5.Print the sorted array.
6.End.
\end{verbatim}
\vspace{7cm}
\section*{{\fontsize{14}{20}\selectfont \textbf{RESULT:}}}
\begin{verbatim}
The program was succesfully executed and output was obtained.
\end{verbatim}
\newpage
\drawborder
\section*{{\fontsize{14}{18}\selectfont \textbf{PROGRAM:}}}
\begin{verbatim}
#include<stdio.h>
void main(){
  int i,j,n,arr[100],min,temp;
  printf("Enter the length of the array : ");
  scanf("%d",&n);
  printf("Enter the array elements : ");
  for(i=0;i<n;i++){}
    scanf("%d",&arr[i]);
  }
  for(i=0;i<n-1;i++){
     min=i;
     for(j=i+1;j<n;j++){
       if(arr[j]<arr[min]){
          min=j;
       }
     }
   if(min!=i){
     temp=arr[i];
     arr[i]=arr[min];
     arr[min]=temp;
   }
  }
  printf("Array after sorting !\n");
  for(i=0;i<n;i++){
    printf(" %d",arr[i]);
  }
}
\end{verbatim}
\section*{{\fontsize{14}{20}\selectfont \textbf{OUTPUT:}}}
\begin{verbatim}
Enter the length of the array : 5
Enter the array elements : 32
12
5
3
76
Array after sorting !
 3 5 12 32 76

\end{verbatim}

\newpage
\drawborder

\vspace{-1cm}
\begin{center}
    {\fontsize{16}{20}\selectfont \textbf{PROGRAM - 4}} \\
    \vspace{0.5cm}
    {\fontsize{16}{20}\selectfont \textbf{SELECTION SORT }}
\end{center}

\vspace{0.5cm}
\noindent
{\fontsize{14}{18}\selectfont {\textbf{AIM}  :  {\fontsize{14}{18}\selectfont  Write a program to implement selection sort in C}}} \\
\vspace{0.15cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{ALGORITHM:}}}
\vspace{0.1cm}
\fontsize{12}{16}\begin{verbatim}
1.Start.
2.Input the size of the array n.
3.Input array elements arr[0…n-1].
4.Sort array using Selection Sort:
    1.For i = 0 to n-2:
       1.Set min = i
       2.For j = i+1 to n-1:
          1.If arr[j] < arr[min], set min = j
       3.If min != i, swap arr[i] and arr[min]
5.Print the sorted array.
6.End.
\end{verbatim}
\vspace{7cm}
\section*{{\fontsize{14}{20}\selectfont \textbf{RESULT:}}}
\begin{verbatim}
The program was succesfully executed and output was obtained.
\end{verbatim}

\newpage
\drawborder
\section*{{\fontsize{14}{18}\selectfont \textbf{PROGRAM:}}}
\begin{verbatim}
#include<stdio.h>
void main()
{
  int i,j,key,n,arr[100];
  printf("Enter the length of the array : ");
  scanf("%d",&n);
  printf("Enter array elements : ");
  for(i=0;i<n;i++)
  {
    scanf("%d",&arr[i]);
  }
  for(i=1;i<n-1;i++)
  {
    key=arr[i];
    j=i-1;
    while(j>=0 && arr[j]>key)
    {
      arr[j+1]=arr[j];
      j=j-1;
    }
   arr[j+1]=key;
   }
  printf("Array after sorting!\n");
  for(i=0;i<n;i++)
  {
    printf(" %d",arr[i]);
  }
}
\end{verbatim}
\section*{{\fontsize{14}{20}\selectfont \textbf{OUTPUT:}}}
\begin{verbatim}
Enter the length of the array : 5
Enter array elements : 32
12
6
23
89
Array after sorting!
 6 12 23 32 89

\end{verbatim}

\drawborder
\vspace{-1cm}
\begin{center}
    {\fontsize{16}{20}\selectfont \textbf{PROGRAM - 5}} \\
    \vspace{0.5cm}
    {\fontsize{16}{20}\selectfont \textbf{INSERTION SORT }}
\end{center}

\vspace{0.5cm}
\noindent
{\fontsize{14}{18}\selectfont {\textbf{AIM}  :  {\fontsize{14}{18}\selectfont  Write a program to implement insertion sort in C}}} \\
\vspace{0.15cm}

\section*{{\fontsize{14}{20}\selectfont \textbf{ALGORITHM:}}}
\vspace{0.1cm}
\fontsize{12}{16}\begin{verbatim}
1.Start.
2.Input the size of the array n.
3.Input array elements arr[0…n-1].
4.Sort array using Insertion Sort:
     1.For i = 1 to n-1:
     2.Set key = arr[i] and j = i-1
     3.While j >= 0 and arr[j] > key:
          1.arr[j+1] = arr[j]
          2.j = j - 1
     4.arr[j+1] = key
5.Print the sorted array.
6.End.
\end{verbatim}
\vspace{7cm}
\section*{{\fontsize{14}{20}\selectfont \textbf{RESULT:}}}
\begin{verbatim}
The program was succesfully executed and output was obtained.
\end{verbatim}
\end{document}
