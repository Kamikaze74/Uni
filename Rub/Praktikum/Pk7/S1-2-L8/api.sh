#!/usr/bin/env bash

#Konfiguration
LATITUDE="51.5"         # z. B. Dortmund
LONGITUDE="7.45"

#Start- und Enddatum im Format YYYY-MM-DD
START_DATE="${1:-2025-06-01}"
END_DATE="${2:-2025-06-17}"

#Parameter (z. B. Temperatur, Niederschlag)
PARAMS="temperature_2m_max,temperature_2m_min,precipitation_sum"

#CSV-Download
URL="https://archive-api.open-meteo.com/v1/archive?latitude=$LATITUDE&longitude=$LONGITUDE&start_date=$START_DATE&end_date=$END_DATE&daily=$PARAMS&timezone=Europe%2FBerlin&format=csv"

curl -s "$URL"