#
# Coursera
# Algorithms 1, Princeton
# Assignment 3
#

CC     = javac-algs4
XC	   = java-algs4

all:
	$(CC) Point.java
	$(CC) PointClient.java
	$(CC) FastCollinearPoints.java
	$(CC) BruteCollinearPoints.java

test:
	$(XC) PointClient input6.txt

test2:
		$(XC) PointClient input8.txt

zip:
	rm -f collinear.zip
	zip collinear.zip BruteCollinearPoints.java FastCollinearPoints.java Point.java

pmd:
	pmd-algs4 .

style:
	checkstyle-coursera *.java

bugs:
	findbugs-coursera *.class

clean:
	rm -f *.class
