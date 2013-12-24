# Compilers
JAVAC = javac
RMIC = rmic

# Sources
SERVER = RmiServerImpl.java
CLIENT = RmiClient.java

all: RmiServer RmiClient
	$(RMIC) RmiServerImpl

RmiServer: $(SERVER)
	$(JAVAC) $<

RmiClient: $(CLIENT)
	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
