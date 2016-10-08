/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/

public class SnakeGame {
    int w, h;
    // Use food index as a score
    int curFoodIndex;
    int[][] food;
    // Board position is encoded as 0, 1, 2, ..., w*h - 1
    // updating body of the snake
    // front of the deque contains the head and end of the deque contains the tail
    Deque<Integer> snake;
    // to check whether bites its body
    Set<Integer> visited;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        w = width;
        h = height;
        curFoodIndex = 0;
        this.food = food;
        // initial position
        visited = new HashSet<>();
        visited.add(0);
        snake = new LinkedList<>();
        snake.addFirst(0);
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if(curFoodIndex == -1)              return -1;
        // Current snake head
        int r = snake.peekFirst() / w;
        int c = snake.peekFirst() % w;
        
        if(direction.equals("U"))            r--;
        else if(direction.equals("L"))       c--;           
        else if(direction.equals("R"))       c++;           
        else if(direction.equals("D"))       r++;           

        int newHead = r*w + c;

        /// New head can be stationed at previous tail's position
        visited.remove(snake.peekLast());
        if(r < 0 || r >= h || c < 0 || c >= w || visited.contains(newHead)){
            curFoodIndex = -1;
            return -1;
        }
        
        // Add new head
        snake.addFirst(newHead);
        visited.add(newHead);
        // Eating food, add head and increase score
        if(curFoodIndex < food.length && r == food[curFoodIndex][0] && c == food[curFoodIndex][1]){
            // Previous tail unchanged, add it back to the set
            visited.add(snake.peekLast());
            curFoodIndex++;
            return curFoodIndex;
        }
        
        // Remove tail and add head (already done)
        // tail is removed from visited array already before checking boundary or eats it's own body condition
        snake.removeLast();
        return curFoodIndex;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */