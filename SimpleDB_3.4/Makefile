JAVAC = javac
SERVER_DIR = bin/server
CLIENT_DIR = bin/client

CLIENTS := $(wildcard simpleclient/*.java simpleclient/**/*.java)

.PHONY: all
all: server clients

.PHONY: clean
clean:
	find bin -name '*.class' -delete

.PHONY: server
server: $(SERVER_DIR)/simpledb/server/StartServer.class

.PHONY: clients
clients: $(patsubst %.java,$(CLIENT_DIR)/%.class,$(CLIENTS))

$(SERVER_DIR)/simpledb/server/StartServer.class: $(wildcard simpledb/server/*.java)
	$(JAVAC) -d $(SERVER_DIR) $^

$(CLIENT_DIR)/%.class: %.java
	$(JAVAC) -d $(CLIENT_DIR) $^
