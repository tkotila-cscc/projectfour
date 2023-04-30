package cscc.tkotila;

public class TodoItem implements Comparable<TodoItem> {
    private String title;
    private String description;
    private int priority;

    public TodoItem(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Description: %s | Priority: %d", title, description, priority);
    }

    @Override
    public int compareTo(TodoItem rhs) {
        int res = this.getPriority() - rhs.getPriority();

        if (res == 0) {
            return this.getTitle().compareTo(rhs.getTitle());
        }

        return res;
    }
}
