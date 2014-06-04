Test dotCMS Sessions in OSGI
==============================
1.  Hit /app/TestSessionSet in Browser #1
2.  Hit /app/TestSessionGet in Browser #1
3.  Hit /app/TestSessionGet in Browser #2
4.  Hit /app/TestSessionSet in Browser #2
5.  Hit /app/TestSessionGet in Browser #2
6.  Hit /app/TestSessionGet in Browser #1

The way is should work is the same date will be displayed in step #2 and step #6. And a differnt date should be displayed in Step #5.

What is happening is Browser #1's Session gets overwritten by Browser's #2's session in Step #4

