# Compilers
JAVAC = javac
RMIC = rmic

# Sources
AUTHEN = a_rmifs.java
SERVER = s_rmifs.java
CLIENT = c_rmifs.java

all: todo #a_rmifs s_rmifs c_rmifs
	$(RMIC) RmiAuthenImpl RmiServerImpl

todo: $(AUTHEN) $(SERVER) $(CLIENT)
	$(JAVAC) $^

# a_rmifs: $(AUTHEN)
# 	$(JAVAC) $<

# s_rmifs: $(SERVER)
# 	$(JAVAC) $<

# c_rmifs: $(CLIENT)
# 	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
