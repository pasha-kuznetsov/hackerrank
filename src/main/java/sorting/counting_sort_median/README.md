# Counting Sort Median

> **See also [Tests](../../../../test/groovy/sorting/counting_sort_median)**

## "Fraudulent Activity Notifications" at [HR](https://www.hackerrank.com/challenges/fraudulent-activity-notifications) `Medium`

HackerLand National Bank has a simple policy for warning clients about possible 
fraudulent account activity. If the amount spent by a client on a particular 
day is greater than or equal to `2x` the client's median spending for the last 
`d` days, they send the client a notification about potential fraud. The bank 
doesn't send the client any notifications until they have at least `d` prior 
days of transaction data.

Given the value of `d` and a client's total daily expenditures for a period of 
`n` days, find and print the number of times the client will receive 
a notification over all `n` days.

Note: The median of a list of numbers can be found by arranging all the numbers 
from smallest to greatest. If there is an odd number of numbers, the middle one 
is picked. If there is an even number of numbers, median is then defined to be 
the average of the two middle values. ([Wikipedia](https://en.wikipedia.org/wiki/Median#Basic_procedure))

### Input Format

The first line contains two space-separated integers denoting the respective 
values of `n` (the number of days there is transaction data for) and 
`d` (the number of prior days the bank uses to calculate median spending). 

The second line contains `n` space-separated non-negative integers where each 
integer `i` denotes _expenditure_<sub>`i`</sub> (i.e., the client's total 
_expenditure_ for day `i`).

### Constraints

Values                          | Constraints
:---                            | :---
`n`                             | [1 ... 2 x 10<sup>5</sup>]
`d`                             | [1 ... `n`]
_expenditure_<sub>`i`</sub>     | [0 ... 200] 

### Output Format

Print an integer denoting the total number of times the client receives 
a notification over a period of `n` days.
