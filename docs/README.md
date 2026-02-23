# Mortis User Guide

Mortis is a simple desktop application that allows you to keep track of your tasks. 
It has an efficient CLI input system, while having a friendly GUI.

## Getting started
1. Ensure you have Java 17 installed.
2. Download the jar file.
3. Copy the file to the folder you want to use as the home folder for the application.
4. Run it by double clicking, or open a command terminal, 
cd into the folder you put the jar file in, and use the java -jar mortis.jar command to run the application.

Inputting any non-keyword, such as "help" or "hi",
will display a list of commands available.

## List of commands
- add
- list
- find
- delete
- mark
- unmark
- bye

## Adding tasks : ```add```
Adds a task to your list.

You can add three types of tasks:
1. Todo tasks
2. Deadline tasks (containing deadline time)
3. Event tasks (containing start and end time)

### Adding a todo task
```add todo, <description>, <(optional) do-after description>```

### Adding a deadline task
```add deadline, <description>, <(optional) do-after description>, <deadline time>```

### Adding an event task
```add event, <description>, <(optional) do-after description>, <start time>, <end time>```

Example: `add deadline, write project report, 2026-02-23 2359`

```
Successfully added the following task: [D][ ] write project report (by: Feb 23 23:59, 2026)
```

Include an optional parameter ```<after (description)>``` when adding a task to specify if it should be done after something.

Example: `add todo, eat breakfast, after brushing teeth`

```
Successfully added the following task: [T][ ] eat breakfast (after brushing teeth)
```

## Listing all tasks: ```list```
Shows a list of all of your tasks.

Format: ```list```

## Finding task(s): ```find```
Displays all tasks whose description contains your input keyword.

Example: `find project`

```
Task(s) that match your search:
[D][ ] write project report (by: Feb 23 23:59, 2026)
[E][ ] project meeting (from: Feb 24 18:00, 2026 to: Feb 24 19:00, 2026)
(END OF LIST)
```

## Deleting a task: ```delete```
Deletes a task from your list.

Format: ```delete <index>```
- This deletes the task at the specified index in your list.
- The list is 1-indexed.

## Marking a task as done: ```mark```
Marks a task as done.

Format: ```mark <index>```
- This marks the task at the specified index in your list as done.
- The list is 1-indexed.

## Marking a task as not done: ```unmark```
Marks a task as not done.

Format: ```unmark <index>```
- This marks the task at the specified index in your list as not done.
- The list is 1-indexed.

## Exiting the program: ```bye```
Exits the program.

## Saving and loading
Your data is loaded from data/Mortis.txt upon initialisation, 
and saved back automatically after any command that changes it. 
There is no need to save manually.