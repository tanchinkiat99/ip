# User Guide

Mr. Tutu is a desktop app for managing and keeping track of your daily tasks, optimized for use via a Graphical User Interface (GUI). 

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
    - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding an Event: `event`](#adding-an-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Marking a task: `mark`](#marking-a-task-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Finding tasks by keywords: `find`](#finding-tasks-by-keywords-find)
    - [Viewing schedule for specific dates: `schedule`](#viewing-schedule-for-specific-dates-schedule)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
    - [Editing the data](#editing-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest mrtutu.jar from [here](https://github.com/tanchinkiat99/ip/releases/tag/A-Jar).
3. Copy the file to the folder you want to use as the home folder for Mr. Tutu.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
5. Type the command in the command box and press Enter or click on the "Send" button to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:
   - `list`: Lists all tasks in the task list. 
   - `todo wash clothes` : Adds a to-do task "wash clothes" to the list. 
   - `mark 1` : Marks the first task as done. 
   - `delete 3` : Deletes the third task in the list. 
   - `bye` : Exits the app. 


## Features 

### Adding a Todo task: `todo`

Adds a to-do task into the task list. 

Format: `todo TASK`

Examples:
- `todo wash clothes`
- `todo feed dog`

### Adding a Deadline: `deadline`

Adds a task with a deadline into the task list.

Format: `deadline TASK /by DATE TIME`

- The date must in the format YYYY-MM-DD e.g. `2022-02-14`
- The time must be in the 24-hour format HHMM e.g. `1800`

Examples:
- `deadline water plant /by 2021-02-15 2100`
- `deadline buy cat /by 2021-03-21 1800`

### Adding an event: `event`

Adds an event with a date, start time and end time into the task list.

Format: `event TASK /at DATE START_TIME to END_TIME`

- The date must in the format YYYY-MM-DD e.g. `2022-02-14`
- The start time and end time must both be in the 24-hour format HHMM e.g. `1800`

Examples:
- `event wash car /at 2022-03-01 1800 to 1900`
- `event paint wall /at 2022-04-02 2000 to 2150`

### Listing all tasks: `list`

Lists all tasks in the task list.

Format: `list`

### Deleting a task: `delete`

Deletes a specified task from the task list.

Format: `delete TASK_NUMBER`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, …​
- The index must be within the range of the number of tasks in the task list e.g. `delete 4` with 3 tasks in the list will return an error. 

Examples:
- `delete 2`

### Marking a task: `mark`

Marks a task as done.

Format: `mark TASK_NUMBER`

- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be within the range of the number of tasks in the task list e.g. `delete 4` with 3 tasks in the list will return an error

Examples:
- `mark 4`

### Unmarking a task: `unmark`

Marks a task as not done.

Format: `unmark TASK_NUMBER`

- Marks the task at the specified `INDEX` as not done. The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
- The index must be within the range of the number of tasks in the task list e.g. `delete 4` with 3 tasks in the list will return an error.

Examples:
- `unmark 1`

### Finding tasks by keywords: `find`

Finds all tasks that match a given keyword.

Format: `find KEYWORD`

- The keyword is case-sensitive e.g. `Cat` will not match `cat`
- The order of the keywords matters. e.g. `car wash` will not match `wash car`
- Only the task description is searched.
- Full words will be matched e.g. `was` will match tasks with `wash`
- Only tasks matching the entire keyword input will be matched e.g. `wash car` will not match `wash clothes`

Example:

`find cat`

Expected outcome:

Finds all tasks with description containing `cat`.

```
Here are the matching tasks in your list:
1. [E][X] event buy cat (at: Aug 8 2023, from 5:00am to 6:00am)
3. [D][ ] deadline find cat (by: Aug 8 2023, 9:00am)
```

### Viewing schedule for specific dates: `schedule`

View all tasks to be done on a specified date, in chronological order.

Format: `schedule for DATE`

- Shows a list of tasks to be done on `DATE` in chronological order. 
- The date must in the format YYYY-MM-DD e.g. `2022-02-14`

Example:

`schedule for 2023-08-08`

Expected outcome:

Shows the tasks happening on 8th August 2023 in chronological order.

```
Here is your schedule for 2023-08-08:
0500: [E][ ] event buy cake (at: Aug 8 2023, from 5:00am to 6:00am)
0900: [D][ ] deadline find candle (by: Aug 8 2023, 9:00am)
1000: [D][ ] deadline submit testimonial (by: Aug 8 2023, 10:00am)
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Data in Mr. Tutu is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data

AddressBook data are saved as a .txt file [JAR file location]/data/tutu.txt. Advanced users are welcome to update data directly by editing that data file.

## FAQ

**Q:** How do I transfer my data to another Computer?

>**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Mr. Tutu home folder.

## Command Summary

| Action    | Format, Examples |
| ------    | ---------------- | 
| **Add to-do task** | `todo TASK` e.g. `todo wash cat`|
| **Add deadline** | `deadline TASK /by DATE TIME` e.g. `deadline water plant /by 2021-02-15 2100`|
| **Add event** | `event TASK /at DATE START_TIME to END_TIME` e.g. `event wash car /at 2022-03-01 1800 to 1900`|
| **List** | `list` |
| **Delete** | `delete TASK_NUMBER` e.g. `delete 2`|
| **Mark** | `mark TASK_NUMBER` e.g. `mark 3`|
| **Unmark** | `unmark TASK_NUMBER` e.g. `unmark 2`|
| **Find** | `find KEYWORD` e.g. `find cat`|
| **View schedule** | `schedule for DATE` e.g. `schedule for 2023-08-09`|
| **Exit** | `bye`|


