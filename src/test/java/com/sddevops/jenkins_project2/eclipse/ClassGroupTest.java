package com.sddevops.jenkins_project2.eclipse;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassGroupTest {

    private ClassGroup classGroup;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        classGroup = new ClassGroup(3);

        student1 = new Student(
                1,
                "Ali",
                LocalDate.of(2000, 5, 10));

        student2 = new Student(
                2,
                "Ben",
                LocalDate.of(1998, 3, 15));

        student3 = new Student(
                3,
                "Charlie",
                LocalDate.of(2002, 8, 20));
    }

    @Test
    void testConstructor() {
        assertEquals(3, classGroup.getCapacity());
        assertEquals(0, classGroup.getSize());
        assertNotNull(classGroup.getStudents());
        assertEquals(3, classGroup.getStudents().length);
    }

    @Test
    void testAddStudent() {
        boolean result = classGroup.addStudent(student1);

        assertTrue(result);
        assertEquals(1, classGroup.getSize());
        assertEquals(student1, classGroup.getStudents()[0]);
    }

    @Test
    void testAddMultipleStudents() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        assertEquals(2, classGroup.getSize());
        assertEquals(student1, classGroup.getStudents()[0]);
        assertEquals(student2, classGroup.getStudents()[1]);
    }

    @Test
    void testAddStudentUntilFullCapacity() {
        assertTrue(classGroup.addStudent(student1));
        assertTrue(classGroup.addStudent(student2));
        assertTrue(classGroup.addStudent(student3));

        assertEquals(3, classGroup.getSize());
    }

    @Test
    void testAddStudentWhenClassIsFull() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);
        classGroup.addStudent(student3);

        Student extraStudent = new Student(
                4,
                "David",
                LocalDate.of(2001, 1, 1));

        boolean result = classGroup.addStudent(extraStudent);

        assertFalse(result);
        assertEquals(3, classGroup.getSize());
    }

    @Test
    void testRemoveStudent() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        boolean result = classGroup.removeStudent(1);

        assertTrue(result);
        assertEquals(1, classGroup.getSize());
        assertEquals(student2, classGroup.getStudents()[0]);
    }

    @Test
    void testRemoveStudentFromMiddle() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);
        classGroup.addStudent(student3);

        boolean result = classGroup.removeStudent(2);

        assertTrue(result);
        assertEquals(2, classGroup.getSize());
        assertEquals(student1, classGroup.getStudents()[0]);
        assertEquals(student3, classGroup.getStudents()[1]);
        assertNull(classGroup.getStudents()[2]);
    }

    @Test
    void testRemoveLastStudent() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        boolean result = classGroup.removeStudent(2);

        assertTrue(result);
        assertEquals(1, classGroup.getSize());
        assertEquals(student1, classGroup.getStudents()[0]);
        assertNull(classGroup.getStudents()[1]);
    }

    @Test
    void testRemoveStudentThatDoesNotExist() {
        classGroup.addStudent(student1);

        boolean result = classGroup.removeStudent(99);

        assertFalse(result);
        assertEquals(1, classGroup.getSize());
    }

    @Test
    void testRemoveStudentFromEmptyClassGroup() {
        boolean result = classGroup.removeStudent(1);

        assertFalse(result);
        assertEquals(0, classGroup.getSize());
    }

    @Test
    void testGetOldestStudent() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);
        classGroup.addStudent(student3);

        Student oldest = classGroup.getTheOldestStudent();

        assertEquals(student2, oldest);
    }

    @Test
    void testGetOldestStudentWithOneStudent() {
        classGroup.addStudent(student1);

        Student oldest = classGroup.getTheOldestStudent();

        assertEquals(student1, oldest);
    }

    @Test
    void testGetOldestStudentWhenEmpty() {
        Student oldest = classGroup.getTheOldestStudent();

        assertNull(oldest);
    }

    @Test
    void testGetCapacity() {
        assertEquals(3, classGroup.getCapacity());
    }

    @Test
    void testGetSizeInitiallyZero() {
        assertEquals(0, classGroup.getSize());
    }

    @Test
    void testGetSizeAfterAddingStudents() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        assertEquals(2, classGroup.getSize());
    }

    @Test
    void testGetSizeAfterRemovingStudent() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        classGroup.removeStudent(1);

        assertEquals(1, classGroup.getSize());
    }

    @Test
    void testGetStudents() {
        classGroup.addStudent(student1);
        classGroup.addStudent(student2);

        Student[] students = classGroup.getStudents();

        assertEquals(student1, students[0]);
        assertEquals(student2, students[1]);
        assertNull(students[2]);
    }

    @Test
    void testZeroCapacityClassGroup() {
        ClassGroup emptyGroup = new ClassGroup(0);

        assertEquals(0, emptyGroup.getCapacity());
        assertEquals(0, emptyGroup.getSize());
        assertFalse(emptyGroup.addStudent(student1));
    }
}