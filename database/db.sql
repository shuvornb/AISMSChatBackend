create table ai_sms_session
(
    id                bigserial not null
        constraint ai_sms_activity_pk
            primary key,
    title             varchar(50),
    description       text,
    questions         jsonb,
    associated_key    varchar(50),
    intervention_type varchar(10)
);


create table ai_sms_session_log
(
    id                 bigserial not null
        constraint ai_sms_activity_response_pk
            primary key,
    participant_id     bigint,
    session_id         bigint,
    response           jsonb,
    end_time           bigint,
    start_time         bigint,
    meeting_id         bigint,
    status             varchar(20),
    meeting_start_time bigint,
    meeting_duration   varchar(20),
    session_type       varchar(10),
    meeting_url        varchar(100)
);


create table ai_sms_primary_assessment_log
(
    id                 serial not null,
    participant_id     bigint,
    assessment_type    varchar(100),
    status             varchar(20),
    meeting_id         bigint,
    meeting_start_time bigint,
    meeting_duration   varchar(20),
    actual_end_time    bigint,
    meeting_url        varchar(100)
);


create table ai_sms_rwat
(
    id                        bigserial                      not null
        constraint user_rwat_pkey
            primary key,
    user_id                   bigint                         not null,
    assess_date               timestamp default CURRENT_DATE not null,
    assess_result             text,
    cog_flex_score            integer,
    empathy_score             integer,
    hope_score                integer,
    p_cope_score              integer,
    e_cope_score              integer,
    d_cope_score              integer,
    positive_relation_count   integer,
    positive_relation_percent integer,
    cloest_relation_score     double precision,
    most_time_relation_score  double precision,
    perception_of_worth_score integer,
    daily_activity            text,
    com_and_leisure_score     integer,
    education_score           double precision,
    employ_score              double precision
);