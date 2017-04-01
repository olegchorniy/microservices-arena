CREATE KEYSPACE IF NOT EXISTS test_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

CREATE TYPE test_keyspace.person (
    age int,
    name text
);

CREATE TABLE test_keyspace.groups (
    name text PRIMARY KEY,
    course int,
    leader person,
    students list<frozen<person>>
);