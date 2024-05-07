# Simple-Java-Compiler-and-Interpreter
I made this as a project for Compiler Techniques while in university.
This Repo contains a simple Java Compiler and Interpreter that takes in strings of letters and operators

The Program below is written in Java to check for errors, evaluate and print out P and R.

#Inputs:
    START
    INTEGER M, N, K, P, R, H, |, g, k, m
    READ M, N, K
    ASSIGN N = M -/ K
    READ g, H, |, m
    temp = <s%*h -j/w-d+*$&;
    P = g/H-l+m*N/k
    R= M+N/K
    WRITE P
    WRITEE R;
    STOP

The Grammar and Production rules:
E -> E Rule1 (R1)
E -> |E+E|E/E|E*E|E+E| R2 
E -> |E1]E2/E3]...|E26| R3 
E -> [E1|E2|E3|...|E26] -> {A|B|C|...]Z|alb]c]...|z]} R4

Note the following additional conditions in the above program:
e Words in capital letters are Keywords
e Words in small letters are Identifiers. Words in single letters from A — Z and a to z are also Identifiers
e +,-,/,* are Operators
e Equals =, and semi colon ; are Symbols
e Any string/line must contain: Keywords, Identifiers, Operators, or Symbols
e Symbols such as: %,$, &, <, >,; not allowed and would give Semantic error
e Two operators must not be combined such as: +* not allowed and would give Syntax error,
same with other operators that are combined, for example -/, */, *+
e Semi colon ; at the end of a line not allowed and would give Syntax error
e Numbers 0,1 to 9 are not allowed and would give Syntax error
e The acceptable keywords are: INTEGER, ASSIGN, READ, WRITE, START, STOP.
e Misspelling in the keywords such as: WRITEE not allowed and would give Lexical error
e Any other character on the keyboard different from all the above will give Syntax error.
e Note that some of the inputs have errors, This is intentional


(Question 1 Branch) contains a Java program that will go through the following stages of compiler to translate the above program (iteratively) - line by line.

(Question Branch 2) contains a Java program that will go through the following stages of compiler to translate the above program (iteratively) — all at once.

Note: Only lines 6 and 7 should go through all the seven (7) stages of compiler:
P=g/H-ltm*N/k — and R= M+N/K

The other lines should just check for errors and report the error (if any)

OUR SEVEN STAGES OF COMPILER
1) Lexical Analysis (Scanner)
2) Syntax Analysis (Parser)
3) Semantic Analysis (Syntactic Analysis)
4) Intermediate Code Representation (ICR)
5) Code Generation (CG)
6) Code Optimization (CO)
7) Target Machine code (TMC) in Binary 
