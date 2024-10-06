# Keepnet Labs Test Automation

Created by karya.boyraz on 5.10.2024

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Table: fakerUser.csv

Login with Email and Password Without MFA
-------------------
* Login to Keepnet UI with 2FA

Add New Target User
-------------------
* Login to Keepnet UI with 2FA
* Click Menu Button "Company"
* Click Menu Button "Target Users"
* Click button "NEW"
* Click Element "Add users manually"
* Write <Name> to Element "first name"
* Write <Surname> to Element "last name"
* Write <randomEmail> to Element "email address"
* Click button "SAVE"

Create New User Group and Add Created User To This New Group
-------------------
* Login to Keepnet UI with 2FA
* Click Menu Button "Company"
* Click Menu Button "Target Users"
* Click Tab Button "Groups"
* Click button "NEW"
* Write <GroupName> to Element "group name"
* Click button "SAVE"
* Click Menu Button "Target Users"
* Click Tab Button "People"
* Click Checkbox on Table <randomEmail>
* Click icon button "mdi-account-plus"
* Click Checkbox on Table <GroupName>
* Click button "CONFIRM"

