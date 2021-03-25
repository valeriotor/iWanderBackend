

insert into Profile values (-5, 2, 'valeriotor', 'Valeriotor');
insert into Profile values (-4, 2, 'alessandrotie', 'Alessandrotie');
insert into Profile values (-3, 2, 'alessandrover', 'Alessandrover');
insert into Profile values (-2, 2, 'cristianalf', 'Cristianalf');
insert into Profile values (-1, 2, 'fabiocur', 'Fabiocur');

insert into Travel_plan (id, user_id, name, start_date, visibility) values (-6, -5, 'Roma', '2021-09-01', 0);
insert into Travel_plan (id, user_id, name, start_date, visibility) values (-5, -5, 'Zurigo', '2021-10-04', 0);
insert into Travel_plan (id, user_id, name, start_date, visibility) values (-4, -5, 'San Francisco', '2021-10-01', 0);
insert into Travel_plan (id, user_id, name, start_date, visibility) values (-3, -4, 'Parigi', '2021-10-01', 0);
insert into Travel_plan (id, user_id, name, start_date, visibility) values (-2, -4, 'Genova', '2021-10-01', 0);
insert into Travel_plan (id, user_id, name, start_date, visibility) values (-1, -3, 'Casablanca', '2021-10-01', 0);

insert into Day (id, travel_plan_id, date, city_id) values (-6, -6, '2021-09-01', 0);
insert into Day (id, travel_plan_id, date, city_id) values (-5, -5, '2021-10-04', 0);
insert into Day (id, travel_plan_id, date, city_id) values (-4, -4, '2021-10-01', 0);
insert into Day (id, travel_plan_id, date, city_id) values (-3, -4, '2021-10-02', 0);
insert into Day (id, travel_plan_id, date, city_id) values (-2, -4, '2021-10-03', 0);
insert into Day (id, travel_plan_id, date, city_id) values (-1, -4, '2021-10-04', 0);

insert into Location_time (location_time_id, day_id, latitude, longitude, name, name_id, time_stamp) values (-2, -6, 0, 0, 'Pantheon', 'dummy', '23:44');
insert into Location_time (location_time_id, day_id, latitude, longitude, name, name_id, time_stamp) values (-1, -4, 0, 0, 'Silicon Valley', 'dummy', '23:44');