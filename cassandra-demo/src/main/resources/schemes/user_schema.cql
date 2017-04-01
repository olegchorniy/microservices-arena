CREATE KEYSPACE IF NOT EXISTS test_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

CREATE TYPE IF NOT EXISTS test_keyspace.address (
    city text,
    street text,
    house int
);

CREATE TABLE IF NOT EXISTS test_keyspace.users (
    id uuid,
    name text,
    address address,
    age int,
    PRIMARY KEY (id, name)
);