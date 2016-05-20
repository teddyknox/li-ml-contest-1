#include <iostream>
using namespace std;

int main(int argc, char* argv[]) {
  if( argc != 2 ) {
    printf("trainer needs tsv dataset filename argument.\n"); 
    return 1;
  }

  char* filename = argv[1];


  return 0;
}
