import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> maxHeap;
    private HashMap<String, Task> taskMap;

    public PriorityQueueWithHeap() {
        maxHeap = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        taskMap = new HashMap<>();
    }

    public void addTask(String name, int priority) {
        if (taskMap.containsKey(name)) {
            throw new IllegalArgumentException("Task already exists");
        }
        Task task = new Task(name, priority);
        maxHeap.add(task);
        taskMap.put(name, task);
    }

    public String executeNext() {
        if (maxHeap.isEmpty()) {
            throw new NoSuchElementException("No tasks available");
        }
        Task task = maxHeap.poll();
        taskMap.remove(task.getName());
        return task.getName();
    }

    public String peek() {
        if (maxHeap.isEmpty()) {
            throw new NoSuchElementException("No tasks available");
        }
        return maxHeap.peek().getName();
    }

    public void changePriority(String name, int newPriority) {
        if (!taskMap.containsKey(name)) {
            throw new NoSuchElementException("Task not found");
        }
        
        Task oldTask = taskMap.get(name);
        maxHeap.remove(oldTask);
        Task newTask = new Task(name, newPriority);
        maxHeap.add(newTask);
        taskMap.put(name, newTask);
    }

    private static class Task {
        private String name;
        private int priority;

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public int getPriority() {
            return priority;
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        
        // 添加測試任務
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);
        
        // 測試執行順序
        System.out.println("執行順序:");
        System.out.println(pq.executeNext()); // 緊急修復
        System.out.println(pq.executeNext()); // 更新
        System.out.println(pq.executeNext()); // 備份
        
        // 測試修改優先級
        pq.addTask("測試", 2);
        pq.changePriority("測試", 4);
        System.out.println(pq.executeNext()); // 測試
    }
}