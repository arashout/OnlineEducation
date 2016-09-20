// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

//Initialize variables
@24576
D=A
@screenend
M=D
@RESET
0;JMP

(LOOP1)
//Make 16 pixels black
@i
A=M
M=-1
@i
M=M+1

//Check if last block of pixels
@i
D=M
@screenend
D=D-M
@RESET
D;JEQ

//Repeat loop
@LOOP1
0;JMP

(LOOP2)
//Make 16 pixels white
@i 
A=M
M=0
@i
M=M+1

//Check if last block of pixels
@i
D=M
@screenend
D=D-M
@RESET
D;JEQ

//Repeat loop
@LOOP2
0;JMP


(RESET)
//Reset variables
@SCREEN
D=A
@i
M=D
@KBD
D=M
@LOOP1
D;JGT
@LOOP2
D;JEQ
@RESET
0;JMP
