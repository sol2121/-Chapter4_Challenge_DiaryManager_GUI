# Personal Diary Manager - JavaFX Application

## Overview
This is a JavaFX-based Personal Diary Manager that allows users to:
- Maintain journal entries
- View entries in a calendar/timeline layout
- Like, share, and save diary entries
- Create new entries dynamically

The UI is interactive and provides immediate visual feedback using a status label.

## Features
1. **Sidebar Navigation**: Quickly switch between Today, Calendar, and Settings sections.
2. **Journal Entries**: ListView shows existing entries; TextArea allows writing new thoughts.
3. **Actions**: Like, Share, Save buttons update the status label.
4. **Calendar View**: Displays days of the month; clicking a day updates the status label.
5. **New Entry**: Floating button creates a new diary entry.

## Design Choices
- **JavaFX VBox, HBox, and BorderPane** layout for flexible UI.
- **Status Label** replaces alerts for non-blocking feedback.
- **DropShadow & Background styling** for modern look.
- **Modular helper methods** for reusable components (cards, buttons, timeline entries).

## Usage
1. Clone the repository:

```bash
git clone https://github.com/yourusername/DiaryManager.git
