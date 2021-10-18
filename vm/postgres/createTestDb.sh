#!/bin/bash

set -e
set -u

if [ -n "$POSTGRES_TEST_DB" ]; then
	echo "  Creating database '$POSTGRES_TEST_DB'"
	psql -v ON_ERROR_STOP=1 -d "$POSTGRES_DB" -c "CREATE DATABASE $POSTGRES_TEST_DB OWNER '$POSTGRES_USER';"
	echo "  Extra database created"
fi
