#
# Coursera
# Algorithms 1, Princeton
# Assignment 4
#

CC     = javac-algs4
XC	   = java-algs4

all:
	$(CC) Board.java
	$(CC) Solver.java
	$(CC) SolverClient.java

test:
	$(XC) SolverClient 8puzzle/puzzle04.txt


zip:
	rm -f 8puzzle.zip
	zip 8puzzle.zip Board.java Solver.java

pmd:
	pmd-algs4 .

style:
	checkstyle-coursera *.java

bugs:
	findbugs-coursera *.class

clean:
	rm -f *.class
