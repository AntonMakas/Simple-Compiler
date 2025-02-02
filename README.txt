Author: Anton Makasevych
Login: xmakasa00

Used language: Java

About project:
-For lexical analysis I use enum of all possible symbols and class for one token, then I add all tokens to the list of tokens. In this part of project it also check for correct written numbers and operands
-For syntax analysis I use already created list of tokens from lexical analysis. From list I got Node tree of all tokens. In one node stores main node and children (left and right node | for tern operand its left middle and right node). In this part of code it checks for correct location of commas, brackets, correct position of tokens.
-For last "build of output" part I use Node tree from previous step. In this steps it check level of precedence and assotiative of operand, add in correct place brackets.  

Start of project: 
1. Go to folder "Project_Compiler"
2. Copy file "Compiler.jar" to the Merlin server
3. To run an application use this command:
	java -jar Compiler.jar "add(5,4)"

AI code:
	lexical_analyzer/Lexer.java line 68 
	-String pattern = "^[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?$";

Libraries:
	-List
	-regex.Pattern

Testing results:
	Input: add(5, mul(3, sub(10, pow(6, 4))))
	Output: 5+3*(10-6^4)

	Input: pow(pow(2,3),1)
	Output: (2^3)^1

	Input: tern(5e2, 2.7,1)
	Output: 5e2?2.7:1

	Input: tern(1,tern(2, 3, 4) ,5)
	Output: 1?2?3:4:5