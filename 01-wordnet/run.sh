#!/bin/bash

set -x
set -e

# write here directory inside lesson data zip archive
LESSON_DATA_DIR=wordnet

source ../setAndCompile.sh

# run whatever here. example:
#${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" WordNet ${DATA_DIR}/synsets15.txt ${DATA_DIR}/hypernyms15Path.txt
#${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" SAP lesson_data/digraph25.txt
${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" Outcast ${DATA_DIR}/synsets.txt ${DATA_DIR}/hypernyms.txt ${DATA_DIR}/outcast5.txt
#${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" Outcast ${DATA_DIR}/synsets.txt ${DATA_DIR}/hypernyms.txt ${DATA_DIR}/outcast5.txt ${DATA_DIR}/outcast8.txt ${DATA_DIR}/outcast11.txt

zip wordnet src/*.java