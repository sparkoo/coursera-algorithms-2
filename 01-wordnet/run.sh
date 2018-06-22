#!/bin/bash

set -x
set -e

# write here directory inside lesson data zip archive
LESSON_DATA_DIR=wordnet

source ../setAndCompile.sh

# run whatever here. example:
#${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" WordNet ${DATA_DIR}/synsets15.txt ${DATA_DIR}/hypernyms15Path.txt
# ${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" Outcast
${JAVA} -cp "${ALGS4JAR}${SEP}${OUT_DIR}" SAP lesson_data/digraph25.txt

zip wordnet src/*.java