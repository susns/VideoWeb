use web;

drop table comment;
drop table likes;
drop table collect;
drop table video;
drop table user;

create table if not exists user
(
    user_id     varchar(6)  charset utf8 not null primary key,# 6位id
    user_name   varchar(50) charset utf8 not null,
    password    varchar(30) charset utf8 not null,
    user_avatar varchar(30) charset utf8 not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table if not exists video
(
    video_id            varchar(30)     charset utf8 not null primary key,# 用户id+年月日时分秒+文件后缀
    user_id             varchar(6)      charset utf8 not null,
    video_title         varchar(30)     charset utf8 not null,
    video_cover         varchar(30)     charset utf8 not null,
    video_publish_time  datetime        not null,   # 时间格式：xxxx-xx-xx xx:xx:xx
    video_type          varchar(20)     charset utf8 not null,
    video_brief_introduction    varchar(400) charset utf8 not null,
    video_duration      time            not null    # 时间格式：xx:xx:xx
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table if not exists comment
(
    comment_id      varchar(45)     charset utf8 not null primary key,
    video_id        varchar(30)     charset utf8 not null,
    user_id         varchar(6)      charset utf8 not null,
    comment_time    datetime        not null,
    comment_content varchar(400)    charset utf8 not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table if not exists likes
(
    like_id     varchar(30) charset utf8 not null primary key,
    video_id    varchar(30) charset utf8 not null,
    user_id     varchar(6)  charset utf8 not null,  # 点赞人的id
    like_time   datetime    not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table if not exists collect
(
    collect_id  varchar(20) charset utf8 not null primary key,
    user_id     varchar(6)  charset utf8 not null,  # 收藏人的id
    video_id    varchar(30) charset utf8 not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

alter table video add constraint FK_video_on_user_user_id foreign key(user_id) REFERENCES user(user_id);
alter table comment add constraint FK_comment_on_user_user_id foreign key(user_id) REFERENCES user(user_id);
alter table comment add constraint FK_comment_on_video_video_id foreign key(video_id) REFERENCES video(video_id);
alter table likes add constraint FK_likes_on_user_user_id foreign key(user_id) REFERENCES user(user_id);
alter table likes add constraint FK_likes_on_video_video_id foreign key(video_id) REFERENCES video(video_id);
alter table collect add constraint FK_collect_on_user_user_id foreign key(user_id) REFERENCES user(user_id);
alter table collect add constraint FK_collect_on_video_video_id foreign key(video_id) REFERENCES video(video_id);



