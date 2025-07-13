# 📝 Note AI Agent

This project is an intelligent note-taking application powered by an AI agent. It supports two main
functionalities:

1. **Add Notes**
2. **Search Notes**

---

## ➕ Add Notes

- When the user selects **"Add Notes"** and types into the search box, the input is sent to the AI
  agent.
- The AI agent analyzes the query and checks the existing list of notes for relevance.
- If no relevant notes are found, the agent **creates a new note** in **JSON format** with the
  following fields:
    - `title`: A concise, descriptive title.
    - `content`: The main body of the note.
    - `tags`: A list of 1–5 relevant tags (as strings) extracted from the context.

- Once the note is created:
    - It is stored using **Room Database**.
    - The user is notified that the note has been added.

---

## 🔍 Search Notes

- When the user selects **"Search Notes"** and enters a query:
    - The AI agent analyzes the query and searches the existing notes for a match.
    - Upon finding a match, it **summarizes the relevant note** in **1–2 lines** that directly
      address the user's query.

---

## 💡 Technologies Used

- **Jetpack Compose**
- **Room Database**
- **AI Agent Integration** (LLM)
- **MVVM Architecture**

---

## 📌 Coming Soon

- Note editing and deletion
- Smart tag suggestions
- Multi-modal inputs (voice/image)
