This project used to check if credit card number input valid, the rule is from 
https://www.cybersource.com/developers/getting_started/test_and_manage/best_practices/card_type_id/
and check if CVV number input valid, the rule is from:
https://www.cvvnumber.com/cvv.html
The implement for the credit card number check is in CreditCardUtil.java
It calculate the length of input first, then check validaty in each length case.
Use a enum "CardType" to identify the type of credit card.

For UI,
Add a input page with several input edit text, as well as date picker and cvv image for user easily find the cvv number in card.
