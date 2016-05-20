INCLUDES = -I ./ -I/usr/local/include
LDLIBS = -lm
LDFLAGS = -L/usr/lib/
CFLAGS  = -Wall
CXX = g++

SOURCES = $(wildcard *.cc)
OBJECTS = $(SOURCES:.cc=.o)

TARGET = trainer

all: $(TARGET)

$(TARGET): 
	$(CXX) $(CFLAGS) $(INCLUDES) $(LDFLAG) $(LDLIBS) -o $(TARGET) $(SOURCES)

#.cpp.o:
        #$(CXX) $(CFLAGS) -o $@ $<

clean:
	rm $(TARGET)

.PHONY: all $(TARGET) clean
