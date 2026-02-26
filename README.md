# 🛠️ TaskForge – Custom Data Structures Task Management System

**Priority-based Task Scheduler built entirely from scratch**  

![Java](https://img.shields.io/badge/Java-17+-007396?logo=openjdk&logoColor=white)
![Custom DS](https://img.shields.io/badge/Custom_Data_Structures-Stack_Queue_LinkedList_Map-blue)
![Console App](https://img.shields.io/badge/Type-Console_Application-green)

---
## 📋 Project Overview

**TaskForge** is a fully functional **console-based Task Management System** that handles project tasks with priorities, dependencies, and undo operations — all implemented using **custom-built data structures** (no Java built-in `Stack`, `Queue`, `LinkedList`, or `HashMap` allowed).

It demonstrates core Data Structures & Algorithms concepts through real-world task scheduling, making it perfect for learning and showcasing algorithmic thinking.

**Key Highlights:**
- Custom Singly LinkedList for task storage
- Custom Stack for Undo operations (LIFO)
- Custom Queue for priority-based processing (FIFO + priority)
- Custom Map for O(1) task lookup by ID
- Merge Sort for efficient priority sorting
- Full dependency checking before task execution
- Comprehensive menu-driven interface with input validation

---
## ✨ Features

- Add tasks with ID, title, priority (High/Medium/Low), and dependencies
- Add/remove dependencies between tasks
- View tasks sorted by priority (using custom Merge Sort)
- Process tasks in priority order with dependency validation
- Undo last added or deleted task (Custom Stack)
- Search tasks instantly by ID (Custom Map)
- Robust error handling and input validation
- Console menu with clear instructions

---
## 🧠 Concepts Demonstrated (CM1602 Learning Outcomes)

| Concept                        | Implementation Used                     | Complexity     |
|--------------------------------|-----------------------------------------|----------------|
| Custom Linked List             | Singly LinkedList for task storage      | O(n) traversal |
| Custom Stack                   | LIFO for Undo operations                | O(1) push/pop  |
| Custom Queue                   | Priority-aware FIFO processing          | O(1) enqueue   |
| Custom Map                     | Array-based for ID → Task lookup        | O(1) average   |
| Merge Sort                     | Stable sorting of tasks by priority     | O(n log n)     |
| Linear Search + Dependency Check | Task validation before processing     | O(n)           |
| Algorithm Analysis             | Justified in report with Big-O         | —              |

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yumna-prog/TaskmanagementSystem.git
   cd TaskForge
