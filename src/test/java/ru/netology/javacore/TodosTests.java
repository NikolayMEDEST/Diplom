package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class TodosTests {

    @org.junit.jupiter.api.Test
    public void testAdd_validArgument_success() {
        final Todos originalTodos = new Todos();
        originalTodos.addTask("Зарядка");
        final List<String> expected = new ArrayList<>();
        expected.add("Зарядка");
        final List<String> result = originalTodos.tasks;
        Assertions.assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    public void testRemove_validArgument_success() {
        final Todos originalTodos = new Todos();
        originalTodos.addTask("Зарядка");
        originalTodos.addTask("Пробежка");
        originalTodos.removeTask("Зарядка");
        final List<String> expected = new ArrayList<>();
        expected.add("Пробежка");
        final List<String> result = originalTodos.tasks;
        Assertions.assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAllTasks_validArgument_success() {
        final Todos originalTodos = new Todos();
        originalTodos.addTask("Пробежка");
        originalTodos.addTask("Зарядка");
        final String result = originalTodos.getAllTasks();
        final String expected = "Зарядка Пробежка ";
        Assertions.assertEquals(expected, result);
    }
}

