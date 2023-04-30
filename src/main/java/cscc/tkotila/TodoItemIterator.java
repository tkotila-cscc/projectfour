package cscc.tkotila;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class TodoItemIterator implements Iterable<TodoItem> {
    private List<TodoItem> items;

    public TodoItemIterator() {
        items = new ArrayList<>();
    }

    public void saveTasks(String fileName) {
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(fileName));
            gson.toJson(items, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("There was a problem saving the tasks.");
        }
    }

    public void loadTasks(String fileName) {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            items = Arrays.stream(gson.fromJson(reader, TodoItem[].class)).toList();
            reader.close();
        } catch (IOException e) {
            System.out.println("There was a problem loading the tasks.");
        }
    }

    public Optional<TodoItem> getTask(String title) {
        return items.stream().filter(rhs -> (Objects.equals(title, rhs.getTitle()))).findFirst();
    }

    public void add(TodoItem item) {
        items.add(item);
    }

    public int size() {
        return items.size();
    }

    public void deleteByTitle(String title) {
        items.removeIf(item -> Objects.equals(item.getTitle(), title));
    }

    @Override
    public Iterator<TodoItem> iterator() {
        return items.iterator();
    }
}
