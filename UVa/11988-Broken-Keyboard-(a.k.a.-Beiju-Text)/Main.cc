#include <iostream>
#include <list>

using namespace std;

int main(){
  list<char> result;
  char s[100000];

  while(scanf("%s", s) != -1){
    result.clear(); 
    list<char>::iterator it = result.begin();

    for(int i=0; s[i]; i++){
      if(s[i] =='[') 
        it = result.begin();
      else if(s[i] ==']') 
        it = result.end();
      else
        result.insert(it, s[i]);
    }
    for(it = result.begin(); it != result.end(); ++it)
      printf("%c", (*it));
    printf("\n");
  }

  return 0;
}