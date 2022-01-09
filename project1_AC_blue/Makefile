run: compile
	java SongData
	java SearchBackEnd

compile: SearchBackEnd.class Songdata.class

SearchBackEnd.class: SearchBackEnd.java
	javac SongData.java
	javac SearchBackEnd.java

SongData.class: SongData.java
	javac SongData.java
clean:
	rm *.class
