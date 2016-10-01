/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    struct Compare{
      bool operator()(const ListNode* a, const ListNode* b){
          if(a == NULL && b == NULL)    return true;
          if(a == NULL)                 return false;
          if(b == NULL)                 return true;
          return (a->val > b->val);
      }  
    };
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        priority_queue<ListNode*, vector<ListNode*>, Compare> min_heap;
        // push first item into min heap from each lists
        for(auto list_head : lists){
            if(list_head != NULL)
                min_heap.emplace(list_head);
        }
        // create a dummy node
        ListNode* dummy = new ListNode(-1);
        ListNode* prev = dummy;
        while(!min_heap.empty()){
            // Find the min 
            auto t = min_heap.top();
            min_heap.pop();
            prev->next = t;
            prev = prev->next;
            
            // Insert the next item into min_heap
            t = t->next;
            if(t)   
                min_heap.emplace(t);
        }
        return dummy->next;
    }
};