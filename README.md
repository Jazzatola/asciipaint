# Springer Code Problem Notes

## Implementation

I decided to try and implement the solution using a purely functional approach. This is something I am still learning after spending most of my career doing OO development.

As a consequence of this, I wrote my own, minimal implementation of the IO monad to handle purely functional interaction with the user. Unfortunately, my recursive implementation for the repl loop would cause the stack to overflow if the program was used for long enough. A more robust implementation could use continuation passing to avoid this. In most situations I would use the scalaz library rather than roll my own.

As for the canvas, although the commands entered by the user are expressed in terms of width, height and 1-based co-ordinates, I decided to use a more natural 0-based implementation for the drawing operations. This led to some fairly ugly conversion of user input into function calls on the canvas. Also, I’m sure there are more efficient algorithms for drawing, filling and representing the grid but I’m happy with how the use of a one-dimensional array turned out.

## Error Handling

I chose to avoid error handling in my implementation by making the program ignore invalid input. This keeps the implementation simple but results in a poor user experience. There is no feedback if the user enters an invalid command or tries to fill the canvas with the line character, etc.

## Testing

I opted for xUnit style tests but for (at least) the IO implementation, property based testing would be more appropriate. This could be used to verify the IO implementation satisfies the monad laws but as mentioned above, it is probably better to choose a third party implementation like scalaz.
