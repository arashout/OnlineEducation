// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

//Initialize variables
@R2
M=0

@R0 //x
D=M
@x
M=D

@R1
D=M
@n
M=D

(LOOP)
//Check to end loop or not
@n
M=M-1 //Decrement number of times to multiply
D=M
@END //Check if n<0
D;JLT

@x
D=M
@R2
M=M+D //Increase SUM
@LOOP //Continue summing x
0;JMP

(END) //END of program
@END
0;JMP