#!/usr/bin/env bash

while ! cqlsh cassandra -u cassandra -p cassandra -e "describe keyspaces" > /dev/null 2>&1; do
    echo "Waiting for Cassandra to be ready..."
    sleep 5
done

INIT_SCRIPT_PATH="/init.sh/init.cql"

if [ -f "$INIT_SCRIPT_PATH" ]; then
    echo "Executing initialization script from $INIT_SCRIPT_PATH"
    cqlsh cassandra -u cassandra -p cassandra -f "$INIT_SCRIPT_PATH"
    echo "Initialization completed"
else
    echo "Initialization script not found at $INIT_SCRIPT_PATH"
    exit 1
fi

# For health check
touch /init.sh/.done
