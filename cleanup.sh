#!/usr/bin/env bash

cf delete webtrader-kyle -f
cf delete portfolio-kyle -f
cf delete accounts-kyle -f
cf delete quotes-kyle -f

cf delete-service trader-db -f
cf delete-service trader-registry -f
cf delete-service config-server -f
cf delete-service trader-dashboard -f
