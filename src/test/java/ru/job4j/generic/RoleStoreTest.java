package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsService() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        Role result = store.findById("1");
        assertThat(result.getDepartmentName(), is("Service"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindDepartmentNameIsService() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        store.add(new Role("1", "Delivery"));
        Role result = store.findById("1");
        assertThat(result.getDepartmentName(), is("Service"));
    }

    @Test
    public void whenReplaceThenDepartmentNameIsDelivery() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        store.replace("1", new Role("1", "Delivery"));
        Role result = store.findById("1");
        assertThat(result.getDepartmentName(), is("Delivery"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeDepartmentName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        store.replace("10", new Role("10", "Delivery"));
        Role result = store.findById("1");
        assertThat(result.getDepartmentName(), is("Service"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenDepartmentNameIsService() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Service"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getDepartmentName(), is("Service"));
    }
}