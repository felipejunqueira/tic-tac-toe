#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

char arr[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
/* the first element is off to clarify the others
1 2 3
4 5 6
7 8 9
*/

int checkWinner(){
    // Horizontal
    if (arr[1] == arr[2] && arr[2] == arr[3]) return 1;
    else if (arr[4] == arr[5] && arr[5] == arr[6]) return 1;
    else if (arr[7] == arr[8] && arr[8] == arr[9]) return 1;
    // Vertical
    else if(arr[1] == arr[4] && arr[4] == arr[7]) return 1;
    else if(arr[2] == arr[5] && arr[5] == arr[8]) return 1;
    else if(arr[3] == arr[6] && arr[6] == arr[9]) return 1;
    // Diagonal
    else if(arr[1] == arr[5] && arr[5] == arr[9]) return 1;
    else if(arr[3] == arr[5] && arr[5] == arr[7]) return 1;

    else if(arr[1] != '1' && arr[2] != '2' && arr[3] != '3' && arr[4] != '4' && arr[5] != '5' && arr[6] != '6' && arr[7] != '7' && arr[8] != '8' && arr[9] != '9')
        return 0; // a tie happens only if all grid is completed
    
    else return -1; // To continue the game
}

void display_grid(void){
    system("clear");
    printf("       |       |      \n");
    printf("   %c   |   %c   |   %c   \n", arr[1], arr[2], arr[3]);
    printf("       |       |      \n");
    printf("-------|-------|-------\n");
    printf("       |       |      \n");
    printf("   %c   |   %c   |   %c   \n", arr[4], arr[5], arr[6]);
    printf("       |       |      \n");
    printf("-------|-------|-------\n");
    printf("       |       |      \n");
    printf("   %c   |   %c   |   %c   \n", arr[7], arr[8], arr[9]);
    printf("       |       |      \n\n");
}

int main(void) {
    int player = 1;
    int choice, flag;
    char symbol;

    do {
        display_grid();
        do {
            printf("Player %d turn: ", player);
            scanf("%d", &choice);  
            getchar(); // Cleaning the keyboard's buffer
            if (choice < 1 || choice > 9 || !isdigit(arr[choice])) printf("Invalid Input\n");
        } while(choice < 1 || choice > 9 || !isdigit(arr[choice]));
        
        symbol = (player == 1) ? 'X' : 'O';
        arr[choice] = symbol; // All if-else logic simplified
        
        flag = checkWinner();
        player = (player == 1) ? 2 : 1; // Change to the next turn 

    } while(flag == -1);

    display_grid();
    player = (player == 1) ? 2 : 1; // Change again to the right winner
    if (flag == 1) printf("Player %d won!\n", player);
    else if (flag == 0) printf("Game Draw\n");

    return 0;
}