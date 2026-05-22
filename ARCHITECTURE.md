# Daily Reflections - Architecture Documentation

## Software Architecture Overview

Daily Reflections uses the **MVVM (Model-View-ViewModel)** architecture pattern, which provides clean separation of concerns and makes the app testable and maintainable.

## Architecture Layers

### 1. **UI Layer (View)**

Responsible for displaying data and capturing user interactions.

**Components:**
- **Activities**: Screen controllers
  - `MainActivity` - Displays list of entries
  - `AddEditEntryActivity` - Add or edit entry form
  - `EntryDetailActivity` - Display single entry details

**View Binding**: Auto-generated binding classes for type-safe view access

### 2. **ViewModel Layer**

Manages UI-related data and provides lifecycle-aware data to the UI.

**Components:**
- **EntryViewModel**: Central coordinator
  - Holds LiveData for all entries
  - Manages single entry queries
  - Handles search operations
  - Communicates with Repository

**Key Features:**
- Survives configuration changes (screen rotation)
- Observable data with LiveData
- Lifecycle-aware (doesn't leak memory)

### 3. **Repository Layer**

Abstracts data sources and provides a clean API for data access.

**Components:**
- **EntryRepository**: Single access point for all data operations
  - Abstracts database operations
  - Handles background threading
  - Provides data to ViewModel

**Benefits:**
- Single source of truth for data
- Thread management
- Easy to test (can mock)

### 4. **Data Layer (Model & Database)**

Handles all data persistence.

**Components:**

#### a) **Model Class**
```java
@Entity(tableName = "journal_entries")
public class JournalEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private long createdAt;
    private long updatedAt;
    private String tags;
    private String mood;
}
```

#### b) **Data Access Object (DAO)**
```java
@Dao
public interface EntryDao {
    @Insert
    long insert(JournalEntry entry);
    
    @Query("SELECT * FROM journal_entries ORDER BY createdAt DESC")
    LiveData<List<JournalEntry>> getAllEntries();
    
    @Query("SELECT * FROM journal_entries WHERE id = :id")
    LiveData<JournalEntry> getEntryById(int id);
}
```

#### c) **Database Class**
```java
@Database(entities = {JournalEntry.class}, version = 1)
public abstract class JournalDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
    
    private static volatile JournalDatabase INSTANCE;
    
    public static JournalDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (JournalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        JournalDatabase.class,
                        "journal_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
```

## Data Flow Architecture

```
┌─────────────────────┐
│   UI (Activities)   │
├─────────────────────┤
│  MainActivity       │
│  AddEditEntry...    │
│  EntryDetail...     │
└──────────┬──────────┘
           │ observes
           ▼
┌─────────────────────┐
│   ViewModel         │
├─────────────────────┤
│ EntryViewModel      │
│ - getAllEntries()   │
│ - insert()          │
│ - update()          │
│ - delete()          │
└──────────┬──────────┘
           │ uses
           ▼
┌─────────────────────┐
│   Repository        │
├─────────────────────┤
│ EntryRepository     │
│ - Thread mgmt       │
│ - Data access       │
└──────────┬──────────┘
           │ queries
           ▼
┌─────────────────────┐
│   Database Layer    │
├─────────────────────┤
│ JournalDatabase     │
│ EntryDao            │
│ JournalEntry (Room) │
│ SQLite              │
└─────────────────────┘
```

## Navigation Flow

```
Main Entry List
    ▲   │
    │   ├─► Add New Entry
    │   │   AddEditEntryActivity
    │   │       │
    │   │       └─► Save ──┐
    │   │                  │
    │   └◄─────────────────┘
    │
    └─ Click on Entry
       EntryDetailActivity
           │
           ├─► Edit Entry
           │   AddEditEntryActivity
           │       │
           │       └─► Update ──┐
           │                    │
           │          Back ─────┘
           │
           └─► Delete Entry
               Back to Main
```

## Component Interaction

### Adding a New Entry

1. **User Action**: Clicks FAB in MainActivity
2. **UI Layer**: Launches AddEditEntryActivity
3. **User Input**: Enters title, content, mood, tags
4. **Save Action**: Calls viewModel.insert(entry)
5. **ViewModel**: Passes to repository.insert()
6. **Repository**: Executes on background thread
7. **DAO**: Inserts into Room database
8. **Response**: LiveData observes change
9. **UI Update**: MainActivity list refreshes automatically

## Threading Model

**Main Thread**:
- UI updates
- LiveData observation
- User interactions

**Background Thread**:
- Database insert/update/delete operations
- Handled in Repository with `new Thread(() -> {...}).start()`

## Summary

The MVVM architecture provides:

1. **Separation of Concerns** - Each layer has single responsibility
2. **Testability** - Easy to unit test without UI
3. **Maintainability** - Clear structure for modifications
4. **Scalability** - Easy to add features or change data sources
5. **Lifecycle Safety** - LiveData handles orientation changes
6. **Thread Safety** - Repository manages background operations
7. **Code Reusability** - Repository can be shared between multiple UIs
