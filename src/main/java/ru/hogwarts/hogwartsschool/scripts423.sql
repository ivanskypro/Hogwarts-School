SELECT student.name, student.age, faculty.name
FROM student
         RIGHT JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age
FROM student
         INNER JOIN avatar ON student.id = avatar.id