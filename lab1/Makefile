#
# Coursera
# Algorithms 1, Princeton
# Assignment 1
#

CC     = javac-algs4
XC	   = java-algs4
NAME = problem62

all:
	$(CC) Percolation.java
	$(CC) PercolationStats.java
test:
	$(XC) PercolationStats 200 100

zip:
	rm -f percolation.zip
	zip percolation.zip Percolation.java PercolationStats.java

pmd:
	pmd-algs4 .

style:
	checkstyle-coursera Percolation.java PercolationStats.java

bugs:
	findbugs-coursera Percolation.class PercolationStats.class

clean:
	rm -f *.class
