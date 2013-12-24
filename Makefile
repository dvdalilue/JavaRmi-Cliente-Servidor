# Compilers
JAVAC = javac
RMIC = rmic

# Sources
SERVER = s_rmifs.java
CLIENT = c_rmifs.java

all: s_rmifs c_rmifs
	$(RMIC) RmiServerImpl

s_rmifs: $(SERVER)
	$(JAVAC) $<

c_rmifs: $(CLIENT)
	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
