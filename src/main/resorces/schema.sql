create table playlist(
id Serial,
	name varchar(128),
	play_time date,
	UNIQUE (name)
)