Oxo = Oxo.java
Board = Board.java

%: %.java
	javac $@.java
	java -ea $@
