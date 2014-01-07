# Compilers
JAVAC = javac
RMIC = rmic

# Sources
SERVER = s_rmifs.java
CLIENT = c_rmifs.java
AUTHEN = a_rmifs.java

all: s_rmifs c_rmifs a_rmifs
	$(RMIC) RmiServerImpl RmiAuthenImpl

s_rmifs: $(SERVER)
	$(JAVAC) $<

c_rmifs: $(CLIENT)
	$(JAVAC) $<

a_rmifs: $(AUTHEN)
	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
