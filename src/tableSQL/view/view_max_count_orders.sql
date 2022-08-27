create view show_student_with_max_count_orders
    as select s.name as student, count(o.id) as count_orders
	     from students as s
         join orders o on s.id = o.student_id
         group by (s.name) having count(o.id) = (
			 select max(max_count_orders)
			 from (
		 select s.name as student, count(o.id) as max_count_orders
	     from students as s
         join orders o on s.id = o.student_id 
			 group by s.name) foo);
		 
