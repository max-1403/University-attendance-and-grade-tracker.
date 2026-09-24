# University-attendance-and-grade-tracker.
# 📊 University Grade & Attendance Tracker

A command-line Java application designed to help university students or instructors track individual academic performance and daily lecture attendance records in real-time. 

The system leverages optimized in-memory data structures (`java.util.HashMap`) to store records with fast retrieval speeds.

## 👥 Authors
* **[Aarav Dagur]** - Roll No: `[2620030496]`
* **[K.Eshanth]** - Roll No: `[2620030492]

## 🚀 Key Features
* **In-Memory Student Database:** Add unique Student IDs to an optimized database registry.
* **Dynamic Attendance Logging:** Mark student presence (`1`) or absence (`0`) to dynamically calculate real-time attendance percentages.
* **Continuous Grade Tracking:** Input ongoing assignment/exam scores to automatically evaluate cumulative average scores.
* **On-Demand Performance Reports:** Instantly generate clean summaries showcasing attendance rates and overall Average GPA.
* **Input Validation:** Error-handling mechanisms to intercept accidental non-numeric inputs and prevent terminal crashes.

## 🛠️ Tech Stack & Architecture
* **Language:** Java (JDK 8 or higher) [1]
* **Core Collection API:** `java.util.HashMap` (configured with a 128-bucket initial capacity for fast indexing) [1]
* **I/O Engine:** `java.util.Scanner` for safe console streams [1]

## 🎮 How it Works (Menu Options)
When you launch the program, you will navigate via a terminal interactive menu:
* `1` - **Add Student:** Register a new unique identifier.
* `2` - **Log Attendance:** Input `1` for Present, or `0` for Absent.
* `3` - **Log Grade:** Add modern float numerical scores.
* `4` - **View Report:** Print an aggregated breakdown of attendance metrics and calculated GPA.
* `5` - **Exit:** Safely flush data streams and close the session.
