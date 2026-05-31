from os import system

class TicTacToe:

    def __init__(self):
        self.board = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] # The first one will be off
        self.current_player = 'X'
        self.winning_combos = [
            [1, 2, 3], [4, 5, 6], [7, 8, 9],  # Rows
            [1, 4, 7], [2, 5, 8], [3, 6, 9],  # Columns
            [1, 5, 9], [3, 5, 7]               # Diagonals
        ]

    def display_board(self):
        system("clear")
        print("\n")
        print(f" {self.board[1]} | {self.board[2]} | {self.board[3]}")
        print("---|---|---")
        print(f" {self.board[4]} | {self.board[5]} | {self.board[6]}")
        print("---|---|---")
        print(f" {self.board[7]} | {self.board[8]} | {self.board[9]}")
        print("\n")

    def is_valid_move(self, position):
        if position < 1 or position > 9 or not str(self.board[position]).isdigit(): return False
        return True
    
    def make_move(self, position):
        self.board[position] = self.current_player

    def check_winner(self):
        for combo in self.winning_combos:
            if all(self.board[pos] == self.current_player for pos in combo):
                return True
        return False
    
    def is_board_full(self):
        return not any(str(self.board[pos]).isdigit() for pos in range(1, 10))
    
    def switch_player(self):
        self.current_player = 'O' if self.current_player == 'X' else 'X'

    def get_player_move(self):
        while True:
            try:
                position = int(input(f"Player {self.current_player}, enter position (1-9): "))
                if self.is_valid_move(position):
                    return position
                else: 
                    raise ValueError("Invalid move!")
            except ValueError as e:
                print(f"{e} Enter a number between 1-9")
    
    def play(self):
        while True:
            self.display_board()
            position = self.get_player_move()
            self.make_move(position)

            if self.check_winner():
                self.display_board()
                print(f"Player {self.current_player} won!")
                break
            
            if self.is_board_full():
                self.display_board()
                print("Game Draw!")
                break

            self.switch_player()


if __name__ == "__main__":
    game = TicTacToe()
    game.play()