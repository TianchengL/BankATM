# CS611-Bank ATM

Name
-------------------------------------------------------------------------------------------------
--Tiancheng Liang--
--U39385958--

--Xinyu Liu--
--U--11663535--

--Sahana Subramanya Kowshik--
--U43929102--

Files
-------------------------------------------------------------------------------------------------
All class description is in the start of each class.

Notes:
-------------------------------------------------------------------------------------------------
1. All csv file should be inside the data directory

3. Things instructions to note
   Design Pattern that we used:
   1): Singleton Pattern: We used the singleton design pattern for concrete Currency class that means that once a certain currency has been created, there is no need to create again. For each collection class, we also used a singleton pattern.
   2): Factory: we used a factory pattern for creating each type of account, users could simply call methods inside the factory class to create an account.
   How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the directory after downloading the project

   After downloading the project, find the Main class that is where main method located. And execute that class.

2. Run the following instructions on command line:
   javac *.java
   java Main.java

-------------------------------------------------------------------------------------------------
Other Notes:
Stock list should be manually added by manager during first running.
Transactions to other users are defined by user id,instead of account id.
