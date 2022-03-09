select *from student
where age>10 AND age<20 // вывод студентов в возрастном промежутке от 10 до 20

select name from student // вывод только имени студентов

select name from student
where name LIKE '%о%'    // вывод студентов с буквой "О" в имени

select * from student
where age < id         // вывод студентов, чей возраст меньше id

select * from student
order by age           // вывод студентов упородяченных по возрасту