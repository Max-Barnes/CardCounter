-- reset leaderboard every time this runs

drop table if exists leaderboard;

create table leaderboard (
	player_id serial,
	player_name varchar(40) not null,
	hands_played int default 0,
	hands_won int default 0,
	gross_winnings int default 0,
	
	primary key (player_id)
);

insert into leaderboard(player_name) 
values('steve');
