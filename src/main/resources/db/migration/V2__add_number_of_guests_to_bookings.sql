alter table bookings add column number_of_guests integer not null default 1;
alter table bookings add constraint chk_booking_number_of_guests check (number_of_guests > 0);