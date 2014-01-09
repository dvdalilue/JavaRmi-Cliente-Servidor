# Compilers
JAVAC = javac
RMIC = rmic

# Sources
AUTHEN = a_rmifs.java
SERVER = s_rmifs.java
CLIENT = c_rmifs.java

all: a_rmifs s_rmifs c_rmifs
	$(RMIC) RmiAuthenImpl RmiServerImpl

a_rmifs: $(SERVER)
	$(JAVAC) $<

s_rmifs: $(CLIENT)
	$(JAVAC) $<

c_rmifs: $(AUTHEN)
	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
