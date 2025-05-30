#include <stdio.h>

typedef struct item {
    char string[16];
    struct item *next;
} Item;

void printList(Item *);
void appendItem(Item *root, Item *newItem);

int main()
{
  Item d = {"Müller", NULL};
  Item c = {"Meier", &d};
  Item b = {"Schulze", &c};
  Item root = {"ROOT", &b};
  
  printList(&root);
  
  Item e = {"Schröder", NULL};
  appendItem(&root, &e);

  printf("Erweiterte Liste: \n");
  printList(&root);
}

void printList(Item *root)
{
  Item *item = root->next;
  while(item) {
    printf("%s\n", item->string);
    item = item->next;
  }
}

void appendItem(Item *root, Item *newItem)
{
  while(root->next != NULL)
    root = root->next;

  root->next = newItem;
}