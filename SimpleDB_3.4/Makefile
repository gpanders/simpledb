JAVAC = javac
OUTDIR = build
TARGETS := $(wildcard simpleclient/*.java simpleclient/**/*.java simpledb/**/*Test.java)

.PHONY: all
all: targets

.PHONY: clean
clean:
	find $(OUTDIR) -name '*.class' -delete

.PHONY: targets
targets: $(patsubst %.java,$(OUTDIR)/%.class,$(TARGETS))

$(OUTDIR)/%.class: %.java
	$(JAVAC) -d $(OUTDIR) $^
