# Lab 03.02: SCRABBLE® Scorer
## Lab Description:
The purpose of this lab involves several components:
1. Programmers must understand the concept of parallel
lists.
2. Programmers must understand what a lookup table is:
[Wikipedia - Lookup Table](http://en.wikipedia.org/wiki/Lookup_table)
3. Programmers must understand the concept of O-notation
for measuring algorithm efficiency and why searching a
4. sorted set is more efficient than searching an 
unsorted set. 
Programmers must know how to parse string values one
letter at a time.
## Guidelines for Solutions:
To solve this problem, you must:
- Create a Java class and name it ScrabbleScorer.java. 
- Use iteration to parse the user-input word one letter at a time 
- Use an int array called points to store the scoring value for each letter 
- Use an ArrayList of String values called dictionary that checks and validates user input
from an imported text file (use the supplied SCRABBLE_WORDS.txt file)
- Create some sort of invalid input handling statement to confirm diabolic input and prompt for re-entry
- Loop the program indefinitely until the user enters 0
## Sample input/output:
```
Enter a word to score or 0 to quit: quiz
quiz = 22 points
Enter a word to score or 0 to quit: abc
abc is not a valid word in the dictionary
Enter a word: hi there
hi there is not a valid word in the dictionary
Enter a word to score or 0 to quit: abc123
abc123 is not a valid word in the dictionary
Enter a word to score or 0 to quit: hello
hello = 8 points
Exiting the program thanks for playing
```
## Point Values for Scrabble Letters:

|
|  |  |  |  |  |  |
|--|--|--|--|--|--|
| A – 1 | E – 1 | I – 1 | M – 3 | Q – 10 | U – 1 | Y – 4 |
| B – 3 | F – 4 | J – 8 | N – 1 | R – 1 | V – 4 | Z – 10 |
| C – 3 | G – 2 | K – 5 | O – 1 | S – 1 | W – 4 | |
| D – 2 | H – 4 | L – 1 | P – 3 | T – 1 | X – 8 | |



