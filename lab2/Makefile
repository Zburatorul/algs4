#
# Coursera
# Algorithms 1, Princeton
# Assignment 2
#

CC     = javac-algs4
XC	   = java-algs4

all:
	$(CC) Deque.java
	$(CC) Permutation.java
	$(CC) RandomizedQueue.java

test:
	$(XC) PercolationStats 200 100

zip:
	rm -f queues.zip
	zip queues.zip Permutation.java Deque.java RandomizedQueue.java

pmd:
	pmd-algs4 .

style:
	checkstyle-coursera *.java

bugs:
	findbugs-coursera *.class

clean:
	rm -f *.class
