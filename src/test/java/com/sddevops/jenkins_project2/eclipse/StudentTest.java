package com.sddevops.jenkins_project2.eclipse;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    private Student student;
    private Student friend;

    @BeforeEach
    void setUp() {
        friend = new Student(
                2,
                "Ali",
                LocalDate.of(2001, 5, 10));

        student = new Student(
                1,
                "Aidil",
                LocalDate.of(2000, 1, 15),
                friend);
    }

    @Test
    void testConstructorWithFriend() {
        assertEquals(1, student.getId());
        assertEquals("Aidil", student.getName());
        assertEquals(LocalDate.of(2000, 1, 15), student.getBirthday());
        assertEquals(friend, student.getFriend());
    }

    @Test
    void testConstructorWithoutFriend() {
        Student studentWithoutFriend = new Student(
                3,
                "John",
                LocalDate.of(2002, 3, 20));

        assertEquals(3, studentWithoutFriend.getId());
        assertEquals("John", studentWithoutFriend.getName());
        assertEquals(LocalDate.of(2002, 3, 20), studentWithoutFriend.getBirthday());
        assertNull(studentWithoutFriend.getFriend());
    }

    @Test
    void testSetId() {
        student.setId(100);

        assertEquals(100, student.getId());
    }

    @Test
    void testSetName() {
        student.setName("Adam");

        assertEquals("Adam", student.getName());
    }

    @Test
    void testSetBirthday() {
        LocalDate newBirthday = LocalDate.of(1999, 12, 25);

        student.setBirthday(newBirthday);

        assertEquals(newBirthday, student.getBirthday());
    }

    @Test
    void testSetFriend() {
        Student newFriend = new Student(
                4,
                "Ben",
                LocalDate.of(2000, 6, 10));

        student.setFriend(newFriend);

        assertEquals(newFriend, student.getFriend());
    }

    @Test
    void testSetFriendToNull() {
        student.setFriend(null);

        assertNull(student.getFriend());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(student, student);
    }

    @Test
    void testEqualsSameDetails() {
        Student student2 = new Student(
                1,
                "Aidil",
                LocalDate.of(2000, 1, 15));

        assertEquals(student, student2);
    }

    @Test
    void testEqualsDifferentId() {
        Student student2 = new Student(
                99,
                "Aidil",
                LocalDate.of(2000, 1, 15));

        assertNotEquals(student, student2);
    }

    @Test
    void testEqualsDifferentName() {
        Student student2 = new Student(
                1,
                "Adam",
                LocalDate.of(2000, 1, 15));

        assertNotEquals(student, student2);
    }

    @Test
    void testEqualsDifferentBirthday() {
        Student student2 = new Student(
                1,
                "Aidil",
                LocalDate.of(2001, 1, 15));

        assertNotEquals(student, student2);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(student, null);
    }

    @Test
    void testEqualsDifferentObjectType() {
        assertNotEquals(student, "Aidil");
    }

    @Test
    void testHashCodeSameDetails() {
        Student student2 = new Student(
                1,
                "Aidil",
                LocalDate.of(2000, 1, 15));

        assertEquals(student.hashCode(), student2.hashCode());
    }

    @Test
    void testCompareByNameAlphabeticalOrder() {
        Student student1 = new Student(
                1,
                "Adam",
                LocalDate.of(2000, 1, 1));

        Student student2 = new Student(
                2,
                "Ben",
                LocalDate.of(2000, 1, 1));

        assertTrue(Student.compareByName.compare(student1, student2) < 0);
    }

    @Test
    void testCompareByNameIgnoreCase() {
        Student student1 = new Student(
                1,
                "adam",
                LocalDate.of(2000, 1, 1));

        Student student2 = new Student(
                2,
                "ADAM",
                LocalDate.of(2000, 1, 1));

        assertEquals(0, Student.compareByName.compare(student1, student2));
    }

    @Test
    void testCompareByBirthdayOlderFirst() {
        Student older = new Student(
                1,
                "Adam",
                LocalDate.of(1999, 1, 1));

        Student younger = new Student(
                2,
                "Ben",
                LocalDate.of(2001, 1, 1));

        assertTrue(Student.compareByBirthday.compare(older, younger) < 0);
    }

    @Test
    void testCompareByBirthdaySameDate() {
        Student student1 = new Student(
                1,
                "Adam",
                LocalDate.of(2000, 1, 1));

        Student student2 = new Student(
                2,
                "Ben",
                LocalDate.of(2000, 1, 1));

        assertEquals(0, Student.compareByBirthday.compare(student1, student2));
    }

    @Test
    void testToStringWithFriend() {
        String result = student.toString();

        assertTrue(result.contains("Aidil"));
        assertTrue(result.contains("Ali"));
        assertTrue(result.contains("2000-01-15"));
    }

    @Test
    void testToStringWithoutFriend() {
        Student studentWithoutFriend = new Student(
                3,
                "John",
                LocalDate.of(2002, 3, 20));

        String result = studentWithoutFriend.toString();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("no best friend"));
    }

    @Test
    void testAssignRandomUsernameMinimumLength() {
        Random random = new Random(1);

        student.assignRandomUsername(random);

        assertTrue(student.getName().length() >= 5);
    }

    @Test
    void testAssignRandomUsernameMaximumLength() {
        Random random = new Random(10);

        student.assignRandomUsername(random);

        assertTrue(student.getName().length() <= 10);
    }

    @Test
    void testAssignRandomUsernameValidCharacters() {
        Random random = new Random(5);

        student.assignRandomUsername(random);

        assertTrue(student.getName()
                .matches("[A-Za-z0-9_]+"));
    }
}