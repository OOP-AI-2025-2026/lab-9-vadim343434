package ua.opnu;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        int i = 0;
        while (i < list.size() - 1) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
            i++;
        }
    }

    public void stutter(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            String value = list.get(i);
            list.add(i + 1, value);
            i += 2;
        }
    }

    public void switchPairs(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String first = list.get(i);
            String second = list.get(i + 1);
            list.set(i, second);
            list.set(i + 1, first);
        }
    }

    public void removeDuplicates(List<String> list) {
        int i = 0;
        while (i < list.size() - 1) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    public void markLength4(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            String value = list.get(i);
            if (value.length() == 4) {
                list.add(i, "****");
                i += 2;
            } else {
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            queue.add(value);
            stack.push(value);
        }

        boolean result = true;

        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            int fromStack = stack.pop();
            if (value != fromStack) {
                result = false;
            }
            queue.add(value);
        }

        return result;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            if (value < 0) {
                stack.push(value);
            } else {
                queue.add(value);
            }
        }

        int positives = queue.size();

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        for (int i = 0; i < positives; i++) {
            queue.add(queue.remove());
        }
    }

    public void rearrange(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            if (value % 2 == 0) {
                queue.add(value);
            } else {
                stack.push(value);
            }
        }

        int oddCount = stack.size();

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        int size2 = queue.size();
        for (int i = 0; i < size2; i++) {
            int value = queue.remove();
            if (i < size2 - oddCount) {
                queue.add(value);
            } else {
                stack.push(value);
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.length() % 2 == 0) {
                it.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        HashSet<Integer> set1 = new HashSet<>(list1);
        HashSet<Integer> set2 = new HashSet<>(list2);

        set1.retainAll(set2);

        return set1.size();
    }

    public boolean isUnique(Map<String, String> map) {
        HashSet<String> values = new HashSet<>();
        for (String value : map.values()) {
            if (values.contains(value)) {
                return false;
            }
            values.add(value);
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (map2.containsKey(key)) {
                Integer otherValue = map2.get(key);
                if (value != null && value.equals(otherValue)) {
                    result.put(key, value);
                }
            }
        }

        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            result.put(value, key);
        }

        return result;
    }

    public int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();

        for (Integer value : map.values()) {
            if (value == null) {
                continue;
            }
            if (freq.containsKey(value)) {
                freq.put(value, freq.get(value) + 1);
            } else {
                freq.put(value, 1);
            }
        }

        int bestValue = 0;
        int bestCount = Integer.MAX_VALUE;
        boolean first = true;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (first) {
                bestValue = value;
                bestCount = count;
                first = false;
            } else {
                if (count < bestCount) {
                    bestCount = count;
                    bestValue = value;
                } else if (count == bestCount && value < bestValue) {
                    bestValue = value;
                }
            }
        }

        return bestValue;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();

        for (Integer value : list) {
            if (freq.containsKey(value)) {
                freq.put(value, freq.get(value) + 1);
            } else {
                freq.put(value, 1);
            }
        }

        int max = 0;
        for (Integer count : freq.values()) {
            if (count > max) {
                max = count;
            }
        }

        return max;
    }

}
