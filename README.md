# CSC 207: Text Editor

**Author**: _Drilon Qerimi

## Resources Used

+ _(TODO: fill me in)_
+ Java version 17.0.14
+ Used Netbeans as an IDE
+ I used the classroom page made by prof. Osera as reference
source: https://osera.cs.grinnell.edu/ttap/data-structures-labs/ 
source: https://osera.cs.grinnell.edu/ttap/data-structures
+  Forked the repository from Github, made by Prof.Osera:
https://github.com/psosera/texteditor
+ Got help from Classmate Fui for the function drawBuffer(), in TextEditor.java,
he advised me to use new TextCharacter(character).
+ Classmate Jason helped me with File processing and he pointed out that I need
to insert each character individually in the buffer.
+ Referred to java database site oracle https://docs.oracle.co


### Part 2: Analyzing the Simple String Buffer

- The relevant inputs to insert() are the character ch, the size of the buffer,that we can call n, and the cursor position which we can call p.

- The operations we are tracking are the two buffer.substring() and the stringconcatenation.The way insert works is by getting a substring from 0 to the cursor position(string to the left of cursor), then another substring formed by the string tothe right of the cursor. Then we insert the string at cursor position byconcatenating it in between them.

- So the runtime will be calculated like this: p + (n-p) + n + 1, where p is the operations of first substring, p-n is the second substring operation and n+1
is the concatenate operation meaning that T(n) = 2n + 1. 

- The big O notation for this is O(n).


## Changelog

qerimidr@williamson:texteditor$ git log --oneline
a3f72dc (HEAD -> main, origin/main, origin/HEAD) commented SimpleStringBuffer.java
3a5915f commented gapBuffer.java
38fbad4 fixed drawBuffer
5df0097 gapBuffer edit
ba09afe fixed moveRight's bounds
9477fca text editor commit
1261e47 text editor commit
3a7dc8a fixed gapBuffer
92c5a64 I constructed a basic TUI
83a1af7 Commit for simplestringbuffer tests
15d51ab Implemented the test for GapBuffer and its array expansion
778d82a Implemented the methods for GapBuffer and the array expansion
c20c30a Wrote a complete unit test suite for SimpleStringBuffer.
4bdd13a implemented methods to get cursor position, to move cursor backward or forward, to return character at index i of buffer, and to return buffer as a string.
1b84bec implemented methods insert and delete that move the cursor correctly when inserting or deleting something.
8abbf8c Added String buffer and int cursor to track the buffer and cursor
32a9049 Project files
02dc921 initial commit

