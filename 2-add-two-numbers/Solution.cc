class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        // Create a dummy node 
        ListNode* dummy = new ListNode(-1);
        ListNode* prev = dummy;
        
        int sum = 0;
        int carry = 0;
        
        while(l1 != NULL || l2 != NULL || carry){
            sum = carry;
            
            if(l1 != NULL){
                sum += l1->val;
                l1 = l1->next;
            }
            
            if(l2 != NULL){
                sum += l2->val;
                l2 = l2->next;
            }
            
            carry = sum / 10;
            sum = sum % 10;
            
            prev->next = new ListNode(sum);
            prev = prev->next;
        }
        return dummy->next;
    }
};