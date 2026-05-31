-- TicTacToe using Magic Square method! 

local game = {
    board = {1, 2, 3, 4, 5, 6, 7, 8, 9},
    current_player = "X",
    player_moves = {
        X = {}, -- Stores positions (and stories) where X played
        O = {} -- I promise, it is not biographies, only tables!
    }
}

local MAGIC = {
    2, 7, 6,
    9, 5, 1,
    4, 3, 8
}

local function display_board()
    os.execute("clear")
    print("\n")
    print(string.format(" %s | %s | %s", game.board[1], game.board[2], game.board[3]))
    print("---|---|---")
    print(string.format(" %s | %s | %s", game.board[4], game.board[5], game.board[6]))
    print("---|---|---")
    print(string.format(" %s | %s | %s", game.board[7], game.board[8], game.board[9]))
    print("\n")
end


local function is_valid_move(position)
    if position < 1 or position > 9 then return false end
    return type(game.board[position]) == "number"
end


local function make_move(position)
    game.board[position] = game.current_player
    table.insert(game.player_moves[game.current_player], position)
end


local function has_sum_15(values)
    local n = #values
    if n < 3 then return false end -- At least 3 moves to win
    for i = 1, n-2 do
        for j = i+1, n-1 do
            for k = j+1, n do  
                if values[i] + values[j] + values[k] == 15 then return true
                end
            end
        end
    end
    return false
end


local function checkWinner()
    local moves = game.player_moves[game.current_player]
    local magic_values = {}
    for _, position in ipairs(moves) do -- We do not want the index
        table.insert(magic_values, MAGIC[position])
    end
    return has_sum_15(magic_values)
end


local function is_board_full()
    for i = 1, 9 do
        if type(game.board[i]) == "number" then return false end
    end
    return true
end


local function switch_player()
    game.current_player = game.current_player == "X" and "O" or "X"
end


local function get_player_move()
    while true do 
        io.write(string.format("Player %s, enter position (1-9): ", game.current_player))
        local input = tonumber(io.read())

        if input and is_valid_move(input) then return input
        else print("Invalid move!") end
    end
end

local function play_game()
    while true do
        display_board()
        local position = get_player_move()
        make_move(position)
        
        if checkWinner() then
            display_board()
            print(string.format("Player %s won!", game.current_player))
            break
        end

        if is_board_full() then
            display_board()
            print("Game draw!")
            break
        end

        switch_player()
    end
end

play_game()