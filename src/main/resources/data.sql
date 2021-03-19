insert into Profile values (-5, 2, 'valeriotor', 'Valeriotor');
insert into Profile values (-4, 2, 'alessandrotie', 'Alessandrotie');
insert into Profile values (-3, 2, 'alessandrover', 'Alessandrover');
insert into Profile values (-2, 2, 'cristianalf', 'Cristianalf');
insert into Profile values (-1, 2, 'fabiocur', 'Fabiocur');

insert into Travel_plan values (-3, -5, '2021-10-01', 'Roma', '2021-10-01', 0);
insert into Travel_plan values (-2, -5, '2021-10-04', 'Zurigo', '2021-10-04', 0);
insert into Travel_plan values (-1, -5, '2021-10-04', 'San Francisco', '2021-10-01', 0);
insert into Travel_plan values (-2, -4, '2021-10-01', 'Parigi', '2021-10-01', 0);
insert into Travel_plan values (-1, -4, '2021-10-01', 'Genova', '2021-10-01', 0);
insert into Travel_plan values (-1, -3, '2021-10-01', 'Casablanca', '2021-10-01', 0);

insert into Day values (-6, 0, '2021-10-01', -3, -5);
insert into Day values (-5, 0, '2021-10-04', -2, -5);
insert into Day values (-4, 0, '2021-10-01', -1, -5);
insert into Day values (-3, 0, '2021-10-02', -1, -5);
insert into Day values (-2, 0, '2021-10-03', -1, -5);
insert into Day values (-1, 0, '2021-10-04', -1, -5);

insert into Location_time values (-1, -6, 0, 0, 'Pantheon', 'dummy', '23:44')